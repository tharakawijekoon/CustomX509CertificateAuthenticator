# CustomX509CertificateAuthenticator
A Custom X509 Certificate based Authenticator written by extending the default Authenticator.

There are cases were you want to use a email usernames and normal usernames in WSO2 Identity Server this requirement can be achived using two approches which are discussed in detail in the following blog post:-http://xacmlinfo.org/2014/10/07/email-username-with-identity-server/

If the second approach mentioned in the blog is used and you are using X509 Certificate based authentication, then for users in the @carbon.super tenant you will have to append the @carbon.super tenant domain when using email usernames. In such a case if you the certificates are already made with email username without the @carbon.super tenant domain appended, you can write a custom x509 authenticator extending the orginal authenticator to append the @carbon.super tenant domain.

In this situation it is assumed that all the X509 Certificate based authentication users are in the super tenant and are using SAN for certificate authentication.

# Build, Deploy & Run

## Build
Execute the following command to build the project

```mvn clean install```

## Deploy

Copy and place the built JAR artifact from the /target/org.wso2.custom.authenticator.x509Certificate-1.0.0.jar to the <IS_HOME>/repository/components/dropins directory. Navigate to <IS_HOME>/repository/conf/identity/application-authentication.xml and add the following new AuthenticatorConfig under the \<AuthenticatorConfigs> element.
```
<AuthenticatorConfig name="CustomX509CertificateAuthenticator" enabled="true">
       <Parameter name="AuthenticationEndpoint">https://localhost:8443/x509-certificate-servlet</Parameter>
       <Parameter name="AlternativeNamesRegex">[a-zA-Z0-9@._-|//]{3,30}$</Parameter>
       <Parameter name="SearchAllUserStores">true</Parameter>
       <Parameter name="LoginClaimURIs">http://wso2.org/claims/emailaddress,http://wso2.org/claims/mobile</Parameter>
 </AuthenticatorConfig>
 ```

