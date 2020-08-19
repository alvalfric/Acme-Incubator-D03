
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(n) from Notice n")
	int totalNumberOfNotices();

	@Query("select count(t) from TechnologyRecord t")
	int totalNumberOfTechnologyRecords();

	@Query("select count(t) from ToolRecord t")
	int totalNumberOfToolRecords();

	@Query("select min(i.maxMoney.amount - i.minMoney.amount) from Inquirie i where i.deadline >= CURRENT_TIMESTAMP")
	double minimumMoneyIntervalsOfActiveInquiries();

	@Query("select max(i.maxMoney.amount - i.minMoney.amount) from Inquirie i where i.deadline >= CURRENT_TIMESTAMP")
	double maximunMoneyIntervalsOfActiveInquiries();

	@Query("select avg(i.maxMoney.amount - i.minMoney.amount) from Inquirie i where i.deadline >= CURRENT_TIMESTAMP")
	double averageMoneyIntervalsOfActiveInquiries();

	//Standard Deviation
	@Query("select sqrt(sum((i.maxMoney.amount - i.minMoney.amount-?1)*(i.maxMoney.amount - i.minMoney.amount-?1))) from Inquirie i where i.deadline >= CURRENT_TIMESTAMP")
	double standardDeviationSumMoneyIntervalsOfActiveInquiries(double avg);

	@Query("select ?1/sqrt(count(i)) from Inquirie i where i.deadline >= CURRENT_TIMESTAMP")
	double standardDeviationMoneyIntervalsOfActiveInquiries(double standardDeviationSum);

	@Query("select min(o.maxMoney.amount - o.minMoney.amount) from Overture o where o.deadline >= CURRENT_TIMESTAMP")
	double minimumMoneyIntervalsOfActiveOvertures();

	@Query("select max(o.maxMoney.amount - o.minMoney.amount) from Overture o where o.deadline >= CURRENT_TIMESTAMP")
	double maximunMoneyIntervalsOfActiveOvertures();

	@Query("select avg(o.maxMoney.amount - o.minMoney.amount) from Overture o where o.deadline >= CURRENT_TIMESTAMP")
	double averageMoneyIntervalsOfActiveOvertures();

	//Standard Deviation
	@Query("select sqrt(sum((o.maxMoney.amount - o.minMoney.amount-?1)*(o.maxMoney.amount - o.minMoney.amount-?1))) from Overture o where o.deadline >= CURRENT_TIMESTAMP")
	double standardDeviationSumMoneyIntervalsOfActiveOvertures(double avg);

	@Query("select ?1/sqrt(count(o)) from Overture o where o.deadline >= CURRENT_TIMESTAMP")
	double standardDeviationMoneyIntervalsOfActiveOvertures(double standardDeviationSum);

	@Query("select t.activitySector, count(t) from TechnologyRecord t group by t.activitySector")
	String[][] totalNumberOfTechnologiesGroupedByActivitySector();

	@Query("select (count(t)*1.0)/(select count(tt)*1.0 from TechnologyRecord tt where tt.sourceType = 'Closed-Source') from TechnologyRecord t where t.sourceType = 'Open-Source'")
	double ratioOfOpenSourceTechnologiesVSClosedSourceTechnologies();

	@Query("select t.activitySector, count(t) from ToolRecord t group by t.activitySector")
	String[][] totalNumberOfToolsGroupedByActivitySector();

	@Query("select (count(t)*1.0)/(select count(tt)*1.0 from ToolRecord tt where tt.sourceType = 'Closed-Source') from ToolRecord t where t.sourceType = 'Open-Source'")
	double ratioOfOpenSourceToolsVSClosedSourceTools();
}
