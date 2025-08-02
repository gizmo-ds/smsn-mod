package dev.aika.smsn.annotation;

import dev.aika.smsn.client.gui.components.SwitchBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Components {
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Switch {
        String checked() default SwitchBuilder.DefaultCheckedText;

        String unchecked() default SwitchBuilder.DefaultUncheckedText;
    }
}
