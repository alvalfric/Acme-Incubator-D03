
package acme.features.administrator.banner;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBannerCreateService implements AbstractCreateService<Administrator, Banner> {

	@Autowired
	private AdministratorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "holderName", "number", "brand", "expirationDate", "CVV");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		assert request != null;

		Banner result;

		result = new Banner();

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean creditCardField = !entity.getHolderName().isEmpty() || entity.getHolderName() != null || !entity.getNumber().isEmpty() || entity.getNumber() != null || !entity.getBrand().isEmpty() || entity.getBrand() != null
			|| entity.getExpirationDate().isEmpty() || entity.getExpirationDate() != null || entity.getCVV() != null;

		if (!errors.hasErrors("holderName")) {
			errors.state(request, !entity.getHolderName().isEmpty() && entity.getHolderName() != null && creditCardField, "holderName", "administrator.banner.error.field.empty");
		}

		if (!errors.hasErrors("number")) {
			errors.state(request, !entity.getNumber().isEmpty() && entity.getNumber() != null && creditCardField, "number", "administrator.banner.error.field.empty");

			String regexNumber = "^[0-9]+$";
			if (entity.getNumber().matches(regexNumber)) {
				errors.state(request, this.checkLuhnCreditCardNumber(entity.getNumber()), "number", "administrator.banner.error.number.invalid");
			} else {
				errors.state(request, false, "number", "administrator.banner.error.number.number");
			}
		}

		if (!errors.hasErrors("brand")) {
			errors.state(request, !entity.getBrand().isEmpty() && entity.getBrand() != null && creditCardField, "brand", "administrator.banner.error.field.empty");
		}

		if (!errors.hasErrors("expirationDate")) {
			errors.state(request, !entity.getExpirationDate().isEmpty() && entity.getExpirationDate() != null && creditCardField, "expirationDate", "administrator.banner.error.field.empty");

			if (!entity.getExpirationDate().matches("^(0[1-9]|1[0-2])\\/20([0-9]{2})$")) {
				errors.state(request, false, "expirationDate", "administrator.banner.error.expirationDate.format");
			} else {
				boolean validExpirationDate = false;

				int year = Calendar.getInstance().get(Calendar.YEAR);
				int month = Calendar.getInstance().get(Calendar.MONTH);

				if (year == Integer.valueOf(entity.getExpirationDate().substring(3))) {
					validExpirationDate = Integer.valueOf(entity.getExpirationDate().substring(0, 2)) > month;
				} else if (Integer.valueOf(entity.getExpirationDate().substring(3)) > year) {
					validExpirationDate = true;
				}

				errors.state(request, validExpirationDate, "expirationDate", "administrator.banner.error.expirationDate.valid");
			}
		}

		if (!errors.hasErrors("CVV")) {
			errors.state(request, entity.getCVV() != null && creditCardField, "CVV", "administrator.banner.error.field.empty");

			if (entity.getCVV().toString().length() != 3) {
				errors.state(request, false, "CVV", "administrator.banner.error.cvv.format");
			}
		}
	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	private boolean checkLuhnCreditCardNumber(final String ccNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = n % 10 + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return sum % 10 == 0;
	}
}
