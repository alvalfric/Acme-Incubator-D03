<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<acme:form readonly="true">
	<acme:form-textbox code="administrator.dashboard.label.totalNumberOfNotices" path="totalNumberOfNotices" />
	<acme:form-textbox code="administrator.dashboard.label.totalNumberOfTechnologyRecords" path="totalNumberOfTechnologyRecords" />
	<acme:form-textbox code="administrator.dashboard.label.totalNumberOfToolRecords" path="totalNumberOfToolRecords" />
	<acme:form-textbox code="administrator.dashboard.label.minimumMoneyIntervalsOfActiveInquiries" path="minimumMoneyIntervalsOfActiveInquiries" />
	<acme:form-textbox code="administrator.dashboard.label.maximunMoneyIntervalsOfActiveInquiries" path="maximunMoneyIntervalsOfActiveInquiries" />
	<acme:form-textbox code="administrator.dashboard.label.averageMoneyIntervalsOfActiveInquiries" path="averageMoneyIntervalsOfActiveInquiries" />
	<acme:form-textbox code="administrator.dashboard.label.standardDeviationMoneyIntervalsOfActiveInquiries" path="standardDeviationMoneyIntervalsOfActiveInquiries" />
	<acme:form-textbox code="administrator.dashboard.label.minimumMoneyIntervalsOfActiveOvertures" path="minimumMoneyIntervalsOfActiveOvertures" />
	<acme:form-textbox code="administrator.dashboard.label.maximunMoneyIntervalsOfActiveOvertures" path="maximunMoneyIntervalsOfActiveOvertures" />
	<acme:form-textbox code="administrator.dashboard.label.averageMoneyIntervalsOfActiveOvertures" path="averageMoneyIntervalsOfActiveOvertures" />
	<acme:form-textbox code="administrator.dashboard.label.standardDeviationMoneyIntervalsOfActiveOvertures" path="standardDeviationMoneyIntervalsOfActiveOvertures" />

<b><acme:message code="administrator.dashboard.chart.label.technologyTool"/></b>
<canvas id="myChart" width="250" height="50"></canvas>
<script>
	var ctx = document.getElementById("myChart").getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : [
				"<jstl:out value="${chartTechnologyTool.get(0).get(0)}" escapeXml="false"/>",
				<jstl:forEach var="label" items="${chartTechnologyTool.get(0)}" begin="1">
					"<jstl:out value="${label}" escapeXml="false"/>",
				</jstl:forEach>
			],
			datasets : [
				{
					data : [
						<jstl:out value="${Double.parseDouble(chartTechnologyTool.get(1).get(0))}" escapeXml="false"/>
						<jstl:forEach var="technologyRecord" items="${chartTechnologyTool.get(1)}" begin="1">
							,<jstl:out value="${Double.parseDouble(technologyRecord)}" escapeXml="false"/>
						</jstl:forEach>
					],
					label: 'Technology Records/Registros Tecnológicos',
					backgroundColor : 'rgba(255, 99, 132, 0.2)',
					borderColor : 'rgba(255,99,132,1)',
					borderWidth : 1
				},
				{
					data : [
						<jstl:out value="${Double.parseDouble(chartTechnologyTool.get(2).get(0))}" escapeXml="false"/>
						<jstl:forEach var="toolRecord" items="${chartTechnologyTool.get(2)}" begin="1">
							,<jstl:out value="${Double.parseDouble(toolRecord)}" escapeXml="false"/>
						</jstl:forEach>
					],
					label: 'Tool Records/Registros de Herramientas',
					backgroundColor : 'rgba(54, 162, 235, 0.2)',
					borderColor : 'rgba(54, 162, 235, 1)',
					borderWidth : 1
				}
			]
		},
		options : {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : true
			}
		}
	});
</script>
	<acme:form-return code="administrator.dashboard.form.button.return"/>
</acme:form>
