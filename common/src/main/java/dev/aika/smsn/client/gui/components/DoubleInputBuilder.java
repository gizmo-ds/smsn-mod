package dev.aika.smsn.client.gui.components;

import dev.aika.smsn.annotation.RequiresRestart;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.DoubleFieldBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public final class DoubleInputBuilder extends AbstractComponentBuilder<Double> {
    public DoubleInputBuilder(ConfigEntryBuilder entryBuilder, Object object, Field field) {
        super(entryBuilder, object, field);
    }

    @Override
    public AbstractConfigListEntry<Double> build() {
        DoubleFieldBuilder builder = entryBuilder.startDoubleField(fieldNameKey(), getValue())
                .setSaveConsumer(this::setValue);
        if (defaultObject != null) builder = builder.setDefaultValue(getDefaultValue());
        builder.requireRestart(field.getAnnotation(RequiresRestart.class) != null);
        return builder.build();
    }
}
