package dev.aika.smsn.client.gui;

import dev.aika.smsn.client.gui.components.AbstractComponentBuilder;
import dev.aika.smsn.client.gui.components.EnumSelectorBuilder;
import dev.aika.smsn.client.gui.components.SwitchBuilder;
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
    protected Object defaultConfigObject;
    @Getter
    @Setter
    protected String modId;

    public ComponentBuilder(ConfigEntryBuilder entryBuilder, Object configObject) {
        this.entryBuilder = entryBuilder;
        this.configObject = configObject;
    }

    private <T extends AbstractComponentBuilder<?>> T createBuilder(String category, T builder) {
        builder.setModId(modId).setCategory(category);
        if (defaultConfigObject != null) builder.setDefaultConfigObject(defaultConfigObject);
        return builder;
    }

    public SwitchBuilder switchBuilder(Field field, String category) {
        return createBuilder(category, new SwitchBuilder(entryBuilder, configObject, field));
    }

    public <E extends Enum<?>> EnumSelectorBuilder<?> enumSelectorBuilder(Field field, String category, Class<E> enumClass) {
        return createBuilder(category, new EnumSelectorBuilder<>(entryBuilder, configObject, enumClass, field));
    }
}
