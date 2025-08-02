package dev.aika.smsn.client.gui.components;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.reflect.Field;

@Accessors(chain = true)
@Environment(EnvType.CLIENT)
public class ComponentBuilder {
    @Getter
    protected final ConfigEntryBuilder entryBuilder;
    @Getter
    protected final Object configObject;
    @Setter
    protected Class<?> defaultObject;
    @Setter
    protected String modId;

    public ComponentBuilder(ConfigEntryBuilder entryBuilder, Object configObject) {
        this.entryBuilder = entryBuilder;
        this.configObject = configObject;
    }

    public SwitchBuilder switchBuilder(Field field) {
        SwitchBuilder builder = new SwitchBuilder(entryBuilder, configObject, field);
        builder.setModId(modId);
        if (defaultObject != null) builder.setDefaultObject(defaultObject);
        return builder;
    }
}