package com.liferay.commerce.payment.method.redsys;

import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Roselaine Marques
 */
@Component(
        immediate = true,
        property = "commerce.payment.engine.method.key="+ RedsysCommercePaymentMethod.KEY,
        service = CommercePaymentMethod.class
)
public class RedsysCommercePaymentMethod implements CommercePaymentMethod {

    public static final String KEY = "redsys";

    @Override
    public String getKey() {
        return KEY;
    }
    @Override
    public String getDescription(Locale locale) {

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(
                resourceBundle, "this-is-payment-method-with-card-redsys");
    }

    @Override
    public String getName(Locale locale) {

            ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                    "content.Language", locale, getClass());

            return LanguageUtil.get(resourceBundle, "redsys-payment-with-card");
    }

    @Override
    public int getPaymentType() {
        return 0;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public boolean isCancelEnabled() {
        return false;
    }

    @Override
    public boolean isCompleteEnabled() {
        return false;
    }

    @Override
    public boolean isProcessPaymentEnabled() {
        return false;
    }

    @Override
    public CommercePaymentResult cancelPayment(CommercePaymentRequest commercePaymentRequest) throws Exception {
        return null;
    }

    @Override
    public CommercePaymentResult completePayment(CommercePaymentRequest commercePaymentRequest) throws Exception {
        return null;
    }

    @Override
    public CommercePaymentResult processPayment(CommercePaymentRequest commercePaymentRequest) throws Exception {
        return null;
    }
}
