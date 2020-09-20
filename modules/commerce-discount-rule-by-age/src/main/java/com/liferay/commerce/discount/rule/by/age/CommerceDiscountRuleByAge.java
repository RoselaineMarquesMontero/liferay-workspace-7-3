package com.liferay.commerce.discount.rule.by.age;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleType;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

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

        CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

        if (commerceOrder == null){
            _log.debug("Commerce Oder is null");
            return false;
        }

        User user = userLocalService.fetchUser(commerceOrder.getUserId());

        Calendar birthdayCal = CalendarFactoryUtil.getCalendar();
        birthdayCal.setTime(user.getBirthday());

        int birthdayMonth = birthdayCal.get(Calendar.MONTH) +1;
        int birthdayDay = birthdayCal.get(Calendar.DATE);
        int birthdayYear = birthdayCal.get(Calendar.YEAR);

        LocalDate birthdayLocalDate = LocalDate.of(birthdayYear, birthdayMonth, birthdayDay);
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthdayLocalDate, now);

        if(period.getYears() >= 70){
            return true;
        }

        _log.debug("Age is: "+ period.getYears() +" does not comply the expected value");
        return false;
    }

    @Override
    public String getKey() {
        return CommerceDiscountRuleByAge.KEY;
    }

    @Override
    public String getLabel(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle( "content.Language", locale, getClass());
        return LanguageUtil.get(resourceBundle, "discount-rule-by-age");    }

    @Reference
    protected UserLocalService userLocalService;

    private static final Log _log = LogFactoryUtil.getLog(CommerceDiscountRuleByAge.class);
}