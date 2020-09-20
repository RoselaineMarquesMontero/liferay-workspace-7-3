   <%@ include file="/init.jsp" %>

  <%
  CommerceDiscountRuleByAgeDisplayContext commerceDiscountRuleByAgeDisplayContext = (CommerceDiscountRuleByAgeDisplayContext)request.getAttribute("view.jsp-commerceDiscountRuleByAgeDisplayContext");
  String value = commerceDiscountRuleByAgeDisplayContext.getTypeSettings();
  %>

    <div class="col-12">
        <div class="card d-flex flex-column">
            <h4 class="align-items-center card-header d-flex justify-content-between py-3">
            <liferay-ui:message key="add-minimum-age-to-discount" />
            </h4>
            <div class="card-body flex-fill">
            <aui:input label="minimum-age-to-discount" name="typeSettings" type="text" value="<%= value %>">
              <aui:validator name="digits" />
              <aui:validator name="min">30</aui:validator>
            </aui:input>
            </div>
        </div>
    </div>
