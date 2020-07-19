
package acme.features.administrator.inquirie;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.inquiries.Inquirie;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorInquireRepository extends AbstractRepository {

	@Query("select i from Inquirie i where i.id = ?1")
	Inquirie findOneById(int id);

	@Query("select i from Inquirie i where i.id = ?1 and i.deadline >= CURRENT_TIMESTAMP")
	Inquirie findOneByIdActive(int id);

	@Query("select i from Inquirie i")
	Collection<Inquirie> findManyAll();

	@Query("select i from Inquirie i where i.deadline >= ?1")
	Collection<Inquirie> findManyAllActive(Date date);

}
