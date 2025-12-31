package dev.aika.smsn.client.gui.components;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.annotation.RequiresRestart;
import dev.aika.smsn.utils.ComponentUtils;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.AbstractFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Accessors(chain = true)
@Environment(EnvType.CLIENT)
public abstract class AbstractComponentBuilder<T> {
    private static final Logger log = SMSN.LOGGER;
    private static final Marker marker = MarkerFactory.getMarker("AbstractComponentBuilder");

    protected final ConfigEntryBuilder entryBuilder;
    protected final Object configObject;
    @Setter
    protected Object defaultConfigObject;
    protected final Field field;
    private final int modifiers;
    @Setter
    protected String category;
    @Setter
    protected String modId;

    public AbstractComponentBuilder(ConfigEntryBuilder entryBuilder, Object configObject, Field field) {
        this.entryBuilder = entryBuilder;
        this.configObject = configObject;
        this.field = field;
        this.modifiers = field.getModifiers();
    }


    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected T getValue() {
        field.setAccessible(true);
        return Modifier.isStatic(modifiers) ? (T) field.get(null) : (T) field.get(configObject);
    }

    @SneakyThrows
    protected void setValue(T value) {
        if (Modifier.isFinal(modifiers)) {
            log.warn(marker, "Cannot set value of final field: {}", field.getName());
            return;
        }
        field.setAccessible(true);
        field.set(configObject, value);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected T getDefaultValue() {
        if (defaultConfigObject == null) return getValue();

        final Field _field;
        try {
            _field = defaultConfigObject.getClass().getDeclaredField(field.getName());
        } catch (NoSuchFieldException e) {
            return getValue();
        }
        _field.setAccessible(true);
        return Modifier.isStatic(modifiers) ? (T) _field.get(null) : (T) _field.get(defaultConfigObject);
    }

    protected String translatableKeyPrefix() {
        return String.format("config.%s.%s", modId, category);
    }

    protected Component fieldNameKey() {
        return Component.translatable(String.format("%s.%s", translatableKeyPrefix(), field.getName()));
    }

    protected <A extends AbstractConfigListEntry<T>, SELF extends FieldBuilder<T, A, SELF>> void fieldBuilderInit(AbstractFieldBuilder<T, A, SELF> builder) {
        if (defaultConfigObject != null) builder.setDefaultValue(getDefaultValue());

        final String translationKey = String.format("%s.%s.@tooltip", translatableKeyPrefix(), field.getName());
        if (ComponentUtils.hasTranslation(translationKey)) builder.setTooltip(Component.translatable(translationKey));

        builder.requireRestart(field.getAnnotation(RequiresRestart.class) != null);
    }

    public abstract AbstractConfigListEntry<T> build();
}
