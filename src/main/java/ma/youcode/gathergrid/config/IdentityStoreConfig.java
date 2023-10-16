package ma.youcode.gathergrid.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.repositories.UserRepository;
import ma.youcode.gathergrid.repositories.UserRepositoryImpl;
import ma.youcode.gathergrid.utils.PasswordHashUtil;

import java.util.HashSet;
import java.util.Optional;

import static java.util.Arrays.asList;

@ApplicationScoped
public class IdentityStoreConfig implements IdentityStore {
    @Inject
    private PasswordHashUtil pwdUtil;
    @Inject
    private UserRepository userRepository;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential passwordCredential = (UsernamePasswordCredential) credential;
        Optional<User> optionalUser = userRepository.findByUsername(passwordCredential.getCaller());
        if(optionalUser.isEmpty()) return CredentialValidationResult.INVALID_RESULT;
        else {
            User user = optionalUser.get();
            System.out.println("user: " + user);
            System.out.println("credential: " + passwordCredential.getCaller() + " " + passwordCredential.getPasswordAsString());
            if (passwordCredential.compareTo(user.getUsername(), user.getPassword())) {
                return new CredentialValidationResult(user.getName(),new HashSet<>(asList(user.getRole().getName())));
            }
        }
        return CredentialValidationResult.INVALID_RESULT;
    }


}
