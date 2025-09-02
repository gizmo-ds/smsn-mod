package dev.aika.smsn.client.gui.components;

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
        fieldBuilderInit(builder);
        return builder.build();
    }
}
