package org.wso2.custom.authenticator.x509Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;
import org.wso2.carbon.identity.application.authentication.framework.exception.AuthenticationFailedException;
import org.wso2.carbon.identity.authenticator.x509Certificate.X509CertificateAuthenticator;

import java.security.cert.X509Certificate;

public class CustomX509CertificateAuthenticator extends X509CertificateAuthenticator {

    private static final Log log = LogFactory.getLog(CustomX509CertificateAuthenticator.class);
    public static final String AUTHENTICATOR_FRIENDLY_NAME = "CustomX509Certificate";
    public static final String AUTHENTICATOR_NAME = "CustomX509CertificateAuthenticator";

    @Override
    protected String getMatchedAlternativeName(X509Certificate cert, AuthenticationContext authenticationContext)
            throws AuthenticationFailedException {

        String alternativeName = super.getMatchedAlternativeName(cert, authenticationContext);

        if (log.isDebugEnabled()) {
            log.debug("@carbon.super tenant domain appended to alternativeName");
        }

        return new StringBuilder(alternativeName).append("@carbon.super").toString();
    }

    @Override
    public String getFriendlyName() {
        return AUTHENTICATOR_FRIENDLY_NAME;
    }

    @Override
    public String getName() {
        return AUTHENTICATOR_NAME;
    }
}
