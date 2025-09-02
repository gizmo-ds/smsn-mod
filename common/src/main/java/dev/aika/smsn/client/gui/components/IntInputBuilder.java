package dev.aika.smsn.client.gui.components;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntFieldBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public final class IntInputBuilder extends AbstractComponentBuilder<Integer> {
    public IntInputBuilder(ConfigEntryBuilder entryBuilder, Object object, Field field) {
        super(entryBuilder, object, field);
    }

    @Override
    public AbstractConfigListEntry<Integer> build() {
        IntFieldBuilder builder = entryBuilder.startIntField(fieldNameKey(), getValue())
                .setSaveConsumer(this::setValue);
        fieldBuilderInit(builder);
        return builder.build();
    }
}
