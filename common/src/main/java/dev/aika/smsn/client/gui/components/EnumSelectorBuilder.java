package dev.aika.smsn.client.gui.components;

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
        super(entryBuilder, configObject);

        this.field = field;
        this.enumClass = enumClass;
    }

    @Override
    public AbstractConfigListEntry<E> build() {
        var builder = entryBuilder.startEnumSelector(
                        Component.translatable(String.format("%s.%s", translatableKeyPrefix(), field.getName())),
                        enumClass, getValue())
                .setSaveConsumer(this::setValue)
                .setEnumNameProvider(this::enumNameProvider);
        if (defaultObject != null) builder = builder.setDefaultValue(getDefaultValue());
        return builder.build();
    }

    private Component enumNameProvider(Enum<?> e) {
        return Component.translatable(String.format("%s.%s.%s", translatableKeyPrefix(), field.getName(), e.name()));
    }
}
