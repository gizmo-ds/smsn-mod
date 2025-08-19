package dev.aika.smsn.client.gui.components;

import dev.aika.smsn.annotation.Components;
import dev.aika.smsn.annotation.RequiresRestart;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public final class SwitchBuilder extends AbstractComponentBuilder<Boolean> {
    private final Components.Switch annotation;

    public static final String DefaultCheckedText = "true";
    public static final String DefaultUncheckedText = "false";

    public SwitchBuilder(ConfigEntryBuilder entryBuilder, Object object, Field field) {
        super(entryBuilder, object, field);

        this.annotation = field.getAnnotation(Components.Switch.class);
    }

    @Override
    public AbstractConfigListEntry<Boolean> build() {
        BooleanToggleBuilder builder = entryBuilder
                .startBooleanToggle(fieldNameKey(), getValue())
                .setSaveConsumer(this::setValue)
                .setYesNoTextSupplier(this::yesNoTextSupplier);
        if (defaultObject != null) builder = builder.setDefaultValue(getDefaultValue());
        builder.requireRestart(field.getAnnotation(RequiresRestart.class) != null);
        return builder.build();
    }

    private Component yesNoTextSupplier(Boolean value) {
        if (annotation == null)
            return Component.translatable(
                    String.format("config.%s.@switch.%s", modId, (value ? DefaultCheckedText : DefaultUncheckedText))
            );
        return Component.translatable(
                String.format("config.%s.@switch.%s", modId, (value ? annotation.checked() : annotation.unchecked()))
        );
    }
}
