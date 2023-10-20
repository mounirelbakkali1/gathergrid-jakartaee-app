package ma.youcode.gathergrid.config;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER,TYPE})
@Retention(RUNTIME)
@Qualifier
public @interface MyQualifier {
    String value() default "default";
}
