package com.liferay.commerce.payment.method.redsys.taglib.ui;
import com.liferay.commerce.payment.constants.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.method.redsys.RedsysCommercePaymentMethod;
import com.liferay.commerce.payment.method.redsys.configuration.RedsysPaymentMethodCardGroupServiceConfiguration;
import com.liferay.commerce.payment.method.redsys.constants.RedsysCommercePaymentMethodConstants;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Roselaine Marques
 */
@Component(
        property = "screen.navigation.category.order:Integer=20",
        service = ScreenNavigationEntry.class
)
public class RedsysCommercePaymentMethodConfigurationScreenNavigationEntry implements ScreenNavigationEntry<CommercePaymentMethodGroupRel> {

    public static final String ENTRY_KEY_CARD_COMMERCE_PAYMENT_METHOD_CONFIGURATION = "redsys-configuration";

    @Override
    public String getEntryKey() {
        return ENTRY_KEY_CARD_COMMERCE_PAYMENT_METHOD_CONFIGURATION;
    }

    @Override
    public String getCategoryKey() {
        return CommercePaymentScreenNavigationConstants.CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_CONFIGURATION;
    }

    @Override
    public String getScreenNavigationKey() {
        return CommercePaymentScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_PAYMENT_METHOD;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, CommercePaymentScreenNavigationConstants.CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_CONFIGURATION);
    }

    @Override
    public boolean isVisible(User user, CommercePaymentMethodGroupRel commercePaymentMethod) {

        if (commercePaymentMethod == null) {
            return false;
        }

        if (RedsysCommercePaymentMethod.KEY.equals(commercePaymentMethod.getEngineKey())) {

            return true;
        }

        return false;
    }

    @Override
    public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        try {

            long commerceChannelId = ParamUtil.getLong(httpServletRequest, RedsysCommercePaymentMethodConstants.COMMERCE_CHANNEL_ID);

            CommerceChannel commerceChannel =_commerceChannelService.fetchCommerceChannel(commerceChannelId);

            if (Validator.isNotNull(commerceChannel)) {

                RedsysPaymentMethodCardGroupServiceConfiguration redsysPaymentMethodCardGroupServiceConfiguration =
                        _configurationProvider.getConfiguration(
                                RedsysPaymentMethodCardGroupServiceConfiguration.class,
                                new ParameterMapSettingsLocator(httpServletRequest.getParameterMap(),
                                        new GroupServiceSettingsLocator(commerceChannel.getGroupId(),
                                                RedsysCommercePaymentMethodConstants.SERVICE_NAME))
                        );

                httpServletRequest.setAttribute(
                        RedsysPaymentMethodCardGroupServiceConfiguration.class.getName(), redsysPaymentMethodCardGroupServiceConfiguration);
            }else {
                if (_log.isDebugEnabled()) {
                    _log.debug("CommerceChannel null with the channelId " + commerceChannelId );
                }
            }

        }catch (Exception ex) {
            _log.error(ex,ex);
        }

        _jspRenderer.renderJSP(
                _servletContext, httpServletRequest, httpServletResponse,
                "/configuration.jsp");

    }

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference(target="(osgi.web.symbolicname=com.liferay.commerce.payment.method.redsys)")
    private ServletContext _servletContext;

    @Reference
    private CommerceChannelService _commerceChannelService;

    @Reference
    private ConfigurationProvider _configurationProvider;

    private static final Log _log = LogFactoryUtil.getLog(RedsysCommercePaymentMethodConfigurationScreenNavigationEntry.class);
}
