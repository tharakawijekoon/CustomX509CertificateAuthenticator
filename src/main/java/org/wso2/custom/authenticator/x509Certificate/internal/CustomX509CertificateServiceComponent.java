package org.wso2.custom.authenticator.x509Certificate.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.custom.authenticator.x509Certificate.CustomX509CertificateAuthenticator;
import org.wso2.carbon.identity.application.authentication.framework.ApplicationAuthenticator;
import org.wso2.carbon.user.core.service.RealmService;


@Component(name = "org.wso2.custom.authenticator.x509Certificate.internal.CustomX509CertificateServiceComponent", service = CustomX509CertificateServiceComponent.class , immediate = true)
public class CustomX509CertificateServiceComponent {

    private static Log log = LogFactory.getLog(CustomX509CertificateServiceComponent.class);

    private static RealmService realmService;

    public static RealmService getRealmService() {
        return realmService;
    }

    @Activate
    protected void activate(ComponentContext ctxt) {
        try {
            CustomX509CertificateAuthenticator authenticator = new CustomX509CertificateAuthenticator();
            ctxt.getBundleContext().registerService(ApplicationAuthenticator.class.getName(), authenticator, null);
            if (log.isDebugEnabled()) {
                log.info("CustomX509CertificateServiceComponent bundle is activated");
            }
        } catch (Throwable e) {
            log.error("CustomX509CertificateServiceComponent bundle activation Failed", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.info("CustomX509CertificateServiceComponent bundle is deactivated");
        }
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        CustomX509CertificateServiceComponent.realmService = null;
    }
}
