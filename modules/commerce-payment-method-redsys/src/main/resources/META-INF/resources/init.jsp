
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@
page import="com.liferay.commerce.payment.method.redsys.configuration.RedsysPaymentMethodCardGroupServiceConfiguration" %><%@
page import="com.liferay.commerce.payment.method.redsys.constants.RedsysCommercePaymentMethodConstants" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.URLCodec" %><%@
page import="com.liferay.petra.string.StringPool"%>
<liferay-frontend:defineObjects />
<liferay-theme:defineObjects />
<%
String redirect = ParamUtil.getString(request, "redirect");
%>
