<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<acme:form>
	<acme:form-textbox code="administrator.inquirie.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="administrator.inquirie.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>	
	<acme:form-textbox code="administrator.inquirie.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.inquirie.form.label.body" path="body"/>
	<acme:form-textbox code="administrator.inquirie.form.label.minMoney" path="minMoney"/>
	<acme:form-textbox code="administrator.inquirie.form.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="administrator.inquirie.form.label.contactEmail" path="contactEmail"/>

	<acme:form-submit test="${command == 'show'}"
		code="administrator.inquirie.form.button.update" 
		action="/administrator/inquirie/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.inquirie.form.button.delete" 
		action="/administrator/inquirie/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.inquirie.form.button.create" 
		action="/administrator/inquirie/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.inquirie.form.button.update" 
		action="/administrator/inquirie/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.inquirie.form.button.delete" 
		action="/administrator/inquirie/delete"/>
	<acme:form-return code="authenticated.inquirie.form.button.return"/>
</acme:form>