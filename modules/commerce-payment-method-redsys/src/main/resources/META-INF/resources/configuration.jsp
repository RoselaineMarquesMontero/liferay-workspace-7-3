<%@ include file="/init.jsp" %>

<%
	RedsysPaymentMethodCardGroupServiceConfiguration paymentMethodCardGroupServiceConfiguration = (RedsysPaymentMethodCardGroupServiceConfiguration)request.getAttribute(RedsysPaymentMethodCardGroupServiceConfiguration.class.getName());
	long commerceChannelId = ParamUtil.getLong(request, RedsysCommercePaymentMethodConstants.COMMERCE_CHANNEL_ID);
%>

<portlet:actionURL name="editCommercePaymentMethodConfiguration" var="editCommercePaymentMethodActionURL" />

<aui:form action="<%= editCommercePaymentMethodActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="<%= RedsysCommercePaymentMethodConstants.COMMERCE_CHANNEL_ID %>" type="hidden" value="<%= commerceChannelId %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<commerce-ui:panel>
		<commerce-ui:info-box title="authentication">
			<div class="alert alert-info">
				<%= LanguageUtil.format(resourceBundle, "redsys-configuration-help", new Object[] {"<a href=\"https://pagosonline.redsys.es\" target=\"_blank\">", "</a>"}, false) %>
			</div>

			<aui:input id="redsys-ds-signature-version" label="ds-signature-version" name="<%= RedsysCommercePaymentMethodConstants.SETTINGS_DS_SIGNATURE_VERSION %>" value="<%= paymentMethodCardGroupServiceConfiguration.dsSignatureVersion() %>" />
			<aui:input id="redsys-signature-secret" label="signature-secret" name="<%= RedsysCommercePaymentMethodConstants.SETTINGS_CLIENT_SECRET %>" value="<%= paymentMethodCardGroupServiceConfiguration.clientSecret() %>" />
			<aui:input id="redsys-merchant-code" label="merchant-code" name="<%= RedsysCommercePaymentMethodConstants.SETTINGS_MERCHANT_CODE %>"  value="<%= paymentMethodCardGroupServiceConfiguration.merchantCode() %>" />
			<aui:input id="redsys-terminal" label="terminal" name="<%= RedsysCommercePaymentMethodConstants.SETTINGS_TERMINAL %>"  value="<%= paymentMethodCardGroupServiceConfiguration.terminal() %>" />
			<aui:select id="redsys-settings--mode" label="mode" name="<%= RedsysCommercePaymentMethodConstants.SETTINGS_MODE %>">

				<%
					for (String mode : RedsysCommercePaymentMethodConstants.MODES) {
				%>
					<aui:option label="<%= mode %>" selected="<%= mode.equals(paymentMethodCardGroupServiceConfiguration.mode()) %>" value="<%= mode %>" />
				<%
				}
				%>

			</aui:select>
			<aui:select id="redsys-settings--type-of-transaction" label="type-of-transaction" name="<%= RedsysCommercePaymentMethodConstants.SETTINGS_TYPES_OF_TRANSACTION %>">

				<%
					for (String typeTransaction : RedsysCommercePaymentMethodConstants.TYPES_OF_TRANSACTION) {
				%>
					<aui:option label="<%= typeTransaction %>" selected="<%= typeTransaction.equals(paymentMethodCardGroupServiceConfiguration.typeTransaction()) %>" value="<%= typeTransaction %>" />
				<%
				}
				%>

			</aui:select>
		</commerce-ui:info-box>
	</commerce-ui:panel>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>