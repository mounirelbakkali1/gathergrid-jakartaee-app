package ma.youcode.gathergrid.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.PasswordHash;
import org.apache.commons.codec.digest.DigestUtils;
@ApplicationScoped
public class PasswordHashUtil implements PasswordHash {

    @Override
    public String generate(char[] chars) {
        return DigestUtils.sha256Hex(chars.toString());
    }

    @Override
    public boolean verify(char[] chars, String s) {
        return DigestUtils.sha256Hex(chars.toString()).equals(s);
    }
}
