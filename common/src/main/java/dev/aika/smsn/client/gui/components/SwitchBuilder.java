package dev.aika.smsn.client.gui.components;

import dev.aika.smsn.annotation.Components;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public final class SwitchBuilder extends AbstractComponentBuilder<Boolean> {
    private final Components.Switch annotation;

    public static final String DefaultCheckedText = "true";
    public static final String DefaultUncheckedText = "false";

    SwitchBuilder(ConfigEntryBuilder entryBuilder, Object object, Field field) {
        super(entryBuilder, object);

        this.field = field;
        this.annotation = field.getAnnotation(Components.Switch.class);
    }

    @Override
    public AbstractConfigListEntry<Boolean> build() {
        BooleanToggleBuilder builder = entryBuilder
                .startBooleanToggle(
                        new TranslatableComponent(String.format("%s.%s", translatableKeyPrefix(), field.getName())),
                        getValue())
                .setSaveConsumer(this::setValue)
                .setYesNoTextSupplier(this::yesNoTextSupplier);
        if (defaultObject != null) builder = builder.setDefaultValue(getDefaultValue());
        return builder.build();
    }

    private Component yesNoTextSupplier(Boolean value) {
        if (annotation == null)
            return new TranslatableComponent(
                    String.format("config.%s.@switch.%s", modId, (value ? DefaultCheckedText : DefaultUncheckedText))
            );
        return new TranslatableComponent(
                String.format("config.%s.@switch.%s", modId, (value ? annotation.checked() : annotation.unchecked()))
        );
    }
}