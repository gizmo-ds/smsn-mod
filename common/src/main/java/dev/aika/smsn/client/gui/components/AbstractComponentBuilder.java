package dev.aika.smsn.client.gui.components;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;

@Accessors(chain = true)
@Environment(EnvType.CLIENT)
public abstract class AbstractComponentBuilder<T> {
    protected final ConfigEntryBuilder entryBuilder;
    protected final Object configObject;
    @Setter
    protected Class<?> defaultObject;
    protected Field field;
    @Setter
    protected String category;
    @Setter
    protected String modId;

    public AbstractComponentBuilder(ConfigEntryBuilder entryBuilder, Object configObject, Field field) {
        this.entryBuilder = entryBuilder;
        this.configObject = configObject;
        this.field = field;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected T getValue() {
        field.setAccessible(true);
        return (T) field.get(configObject);
    }

    @SneakyThrows
    protected void setValue(T value) {
        field.setAccessible(true);
        field.set(configObject, value);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected T getDefaultValue() {
        Field _field;
        try {
            _field = defaultObject.getDeclaredField(field.getName());
        } catch (NoSuchFieldException e) {
            return getValue();
        }
        _field.setAccessible(true);
        return (T) _field.get(null);
    }

    protected String translatableKeyPrefix() {
        return String.format("config.%s.%s", modId, category);
    }

    protected Component fieldNameKey() {
        return Component.translatable(String.format("%s.%s", translatableKeyPrefix(), field.getName()));
    }

    public abstract AbstractConfigListEntry<T> build();
}
