package dev.aika.smsn.client.gui.components;

import dev.aika.smsn.annotation.RequiresRestart;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public final class EnumSelectorBuilder<E extends Enum<?>> extends AbstractComponentBuilder<E> {
    private final Class<E> enumClass;

    public EnumSelectorBuilder(ConfigEntryBuilder entryBuilder, Object configObject,
                               Class<E> enumClass, Field field) {
        super(entryBuilder, configObject, field);

        this.enumClass = enumClass;
    }

    @Override
    public AbstractConfigListEntry<E> build() {
        var builder = entryBuilder.startEnumSelector(fieldNameKey(), enumClass, getValue())
                .setSaveConsumer(this::setValue)
                .setEnumNameProvider(this::enumNameProvider);
        if (defaultObject != null) builder = builder.setDefaultValue(getDefaultValue());
        builder.requireRestart(field.getAnnotation(RequiresRestart.class) != null);
        return builder.build();
    }

    private Component enumNameProvider(Enum<?> e) {
        return Component.translatable(String.format("%s.%s.%s", translatableKeyPrefix(), field.getName(), e.name()));
    }
}
