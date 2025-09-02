package dev.aika.smsn.client.gui.components;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.FloatFieldBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public final class FloatInputBuilder extends AbstractComponentBuilder<Float> {
    public FloatInputBuilder(ConfigEntryBuilder entryBuilder, Object object, Field field) {
        super(entryBuilder, object, field);
    }

    @Override
    public AbstractConfigListEntry<Float> build() {
        FloatFieldBuilder builder = entryBuilder.startFloatField(fieldNameKey(), getValue())
                .setSaveConsumer(this::setValue);
        fieldBuilderInit(builder);
        return builder.build();
    }
}
