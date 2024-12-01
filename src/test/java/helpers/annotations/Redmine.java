package helpers.annotations;

import io.qameta.allure.LinkAnnotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@LinkAnnotation(type = "redmine")
public @interface Redmine {
    String value();
}