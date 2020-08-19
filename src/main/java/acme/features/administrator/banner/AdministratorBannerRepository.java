
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneById(int id);

	@Query("select b from Banner b")
	Collection<Banner> findManyAll();

}
