
package acme.features.administrator.banner;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBannerUpdateService implements AbstractUpdateService<Administrator, Banner> {

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

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "holderName", "number", "brand", "expirationDate", "CVV");
	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean creditCardFieldsEmpty = entity.getHolderName().isEmpty() && entity.getNumber().isEmpty() && entity.getBrand().isEmpty() && entity.getExpirationDate().isEmpty() && entity.getCVV().isEmpty();

		if (!errors.hasErrors("holderName")) {
			if (!creditCardFieldsEmpty) {
				errors.state(request, !entity.getHolderName().isEmpty(), "holderName", "administrator.banner.error.field.empty");
			}
		}

		if (!errors.hasErrors("number")) {
			String regexCreditCardNumber = "^(?:4[0-9]{12}(?:[0-9]{3})?|(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|6(?:011|5[0-9]{2})[0-9]{12}|(?:2131|1800|35\\d{3})\\d{11})$";
			if (entity.getNumber().isEmpty()) {
				errors.state(request, creditCardFieldsEmpty, "number", "administrator.banner.error.field.empty");
			} else if (entity.getNumber().matches("^[0-9]+$")) {
				errors.state(request, this.checkLuhnCreditCardNumber(entity.getNumber()) && entity.getNumber().matches(regexCreditCardNumber), "number", "administrator.banner.error.number.invalid");
			} else {
				errors.state(request, false, "number", "administrator.banner.error.number.number");
			}
		}

		if (!errors.hasErrors("brand")) {
			if (!creditCardFieldsEmpty) {
				errors.state(request, !entity.getBrand().isEmpty(), "brand", "administrator.banner.error.field.empty");
			}
		}

		if (!errors.hasErrors("expirationDate")) {

			if (entity.getExpirationDate().isEmpty() && entity.getExpirationDate() != null) {
				errors.state(request, creditCardFieldsEmpty, "expirationDate", "administrator.banner.error.field.empty");
			} else if (!entity.getExpirationDate().matches("^(0[1-9]|1[0-2])\\/20([0-9]{2})$")) {

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
			if (entity.getCVV().isEmpty() && entity.getCVV() != null) {
				errors.state(request, creditCardFieldsEmpty, "CVV", "administrator.banner.error.field.empty");
			} else if (entity.getCVV().toString().length() != 3) {
				errors.state(request, false, "CVV", "administrator.banner.error.cvv.format");
			}
		}

	}

	@Override
	public void update(final Request<Banner> request, final Banner entity) {
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
