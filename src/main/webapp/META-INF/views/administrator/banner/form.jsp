<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<acme:form>
	<acme:form-textbox code="administrator.banner.form.label.picture" path="picture" placeholder="https://picture.com/"/>
	<acme:form-textbox code="administrator.banner.form.label.slogan" path="slogan" placeholder="Slogan"/>
	<acme:form-textbox code="administrator.banner.form.label.url" path="url" placeholder="https://example.com/"/>
	<b><acme:message code="administrator.banner.form.label.creditCard"/></b>
	<br><br>
	<acme:form-textbox code="administrator.banner.form.label.holderName" path="holderName" placeholder=""/>
	<acme:form-integer code="administrator.banner.form.label.number" path="number" placeholder="1111111111111111"/>
	<acme:form-textbox code="administrator.banner.form.label.brand" path="brand" placeholder="Brand"/>
	<acme:form-textbox code="administrator.banner.form.label.expirationDate" path="expirationDate" placeholder="MM/YYYY"/>
	<acme:form-integer code="administrator.banner.form.label.CVV" path="CVV" placeholder="123"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.banner.form.button.create" 
		action="/administrator/banner/create/" />
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.banner.form.button.update" 
		action="/administrator/banner/update/" />
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.banner.form.button.update" 
		action="/administrator/banner/update/" />
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.banner.form.button.delete" 
		action="/administrator/banner/delete/" />
	<acme:form-submit test="${command == 'delete'}" 
		code="administrator.banner.form.button.delete" 
		action="/administrator/banner/delete/" />
	<acme:form-return code="administrator.banner.form.button.return" />
</acme:form>