package com.liferay.commerce.discount.rule.by.age;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleType;
import com.liferay.portal.kernel.exception.PortalException;
import org.osgi.service.component.annotations.Component;
import java.util.Locale;

/**
 * @author roselainedefaria
 */
@Component(
        immediate = true,
        property = {
                "commerce.discount.rule.type.key=" + CommerceDiscountRuleByAge.KEY,
                "commerce.discount.rule.type.order=:Integer=61"
        },service = CommerceDiscountRuleType.class
)
public class CommerceDiscountRuleByAge implements CommerceDiscountRuleType {

    public static final String KEY = "key-rule-gold-person";

    @Override
    public boolean evaluate(CommerceDiscountRule commerceDiscountRule, CommerceContext commerceContext) throws PortalException {
        return false;
    }

    @Override
    public String getKey() {
        return CommerceDiscountRuleByAge.KEY;
    }

    @Override
    public String getLabel(Locale locale) {
        return "New Custom Discount Type";
    }
}