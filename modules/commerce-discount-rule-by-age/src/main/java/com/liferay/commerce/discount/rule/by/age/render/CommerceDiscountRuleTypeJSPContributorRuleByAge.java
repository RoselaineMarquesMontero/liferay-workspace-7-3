package com.liferay.commerce.discount.rule.by.age.render;

import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleTypeJSPContributor;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component(
        immediate = true,
        property = {
                "commerce.discount.rule.type.jsp.contributor.key="+CommerceDiscountRuleTypeJSPContributorRuleByAge.KEY
        },service = CommerceDiscountRuleTypeJSPContributor.class
)
public class CommerceDiscountRuleTypeJSPContributorRuleByAge implements CommerceDiscountRuleTypeJSPContributor {

    public static final String KEY = "key-rule-gold-person";

    @Override
    public void render(long commerceDiscountId, long commerceDiscountRuleId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        _jspRenderer.renderJSP(
                _servletContext, httpServletRequest, httpServletResponse,
                "/view.jsp");
    }

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference(target="(osgi.web.symbolicname=com.liferay.commerce.discount.rule.by.age)")
    private ServletContext _servletContext;
}
