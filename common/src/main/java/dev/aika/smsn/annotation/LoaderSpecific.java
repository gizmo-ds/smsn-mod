package dev.aika.smsn.annotation;

import dev.aika.smsn.api.LoaderType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoaderSpecific {
    LoaderType[] value();
}
