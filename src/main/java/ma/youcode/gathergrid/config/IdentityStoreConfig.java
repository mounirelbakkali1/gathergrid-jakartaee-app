package ma.youcode.gathergrid.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.HashSet;

import static java.util.Arrays.asList;

@ApplicationScoped
public class IdentityStoreConfig implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println("validating --------------------");
        UsernamePasswordCredential passwordCredential = (UsernamePasswordCredential) credential;
        System.out.println(passwordCredential.compareTo("admin","admin"));
        if(passwordCredential.compareTo("admin","admin")){
            return new CredentialValidationResult("admin",new HashSet<>(asList("admin","agent")));
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
