package dev.aika.smsn.compat;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.Ignored;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.annotation.MixinList;
import dev.aika.smsn.api.LoaderType;
import dev.aika.smsn.client.gui.ComponentBuilder;
import dev.aika.smsn.client.gui.ConfigMixinList;
import dev.aika.smsn.config.ModConfig;
import dev.aika.smsn.mixin.ModMixinManager;
import dev.aika.smsn.utils.ComponentUtils;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClothConfigScreen {
    private static final Logger log = SMSN.LOGGER;

    public static ClothConfigScreenBuilder builder() {
        return new ClothConfigScreenBuilder();
    }

    @Accessors(chain = true)
    public static class ClothConfigScreenBuilder {
        private static final Marker marker = MarkerFactory.getMarker("ClothConfigScreenBuilder");

        @Setter
        private String modId;
        @Setter
        private Screen parent;
        @Setter
        private ModConfig config;
        @Setter
        private Class<?> defaultConfigClass;
        @Setter
        private ModMixinManager mixinManager;

        private final List<Runnable> saveRunnables = new ArrayList<>();
        private final ConfigBuilder builder = ConfigBuilder.create();
        private ComponentBuilder componentBuilder;

        private ClothConfigScreenBuilder() {
        }

        @SneakyThrows
        public Screen build() {
            builder.setParentScreen(parent)
                    .setSavingRunnable(() -> saveRunnables.forEach(Runnable::run))
                    .setTitle(Component.translatable("config." + modId + ".@title"));

            saveRunnables.add(config::save);

            componentBuilder = new ComponentBuilder(builder.entryBuilder(), config).setModId(modId)
                    .setDefaultConfigObject(Objects.requireNonNullElseGet(defaultConfigClass, () -> config.getClass()).getDeclaredConstructor().newInstance());

            final Field[] fields = config.getClass().getDeclaredFields();
            for (Field field : fields) addEntry(field);

            builder.transparentBackground();
            return builder.build();
        }

        @SuppressWarnings("unchecked")
        private void addEntry(Field field) {
            if (isIgnored(field)) return;

            final String categoryKey = getCategoryKey(field);
            final ConfigCategory configCategory = getConfigCategory(categoryKey);

            if (field.getAnnotation(MixinList.class) != null) {
                if (mixinManager == null) {
                    log.warn(marker, "ModMixinManager not set");
                    return;
                }
                final var mixinList = new ConfigMixinList(modId, mixinManager, componentBuilder, field, configCategory);
                saveRunnables.add(mixinList::save);
                mixinList.create();
                return;
            }

            final Class<?> fieldType = field.getType();
            if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                configCategory.addEntry(componentBuilder.switchBuilder(field, categoryKey).build());
            } else if (fieldType.equals(String.class)) {
                configCategory.addEntry(componentBuilder.stringInputBuilder(field, categoryKey).build());
            } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                configCategory.addEntry(componentBuilder.intInputBuilder(field, categoryKey).build());
            } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                configCategory.addEntry(componentBuilder.floatInputBuilder(field, categoryKey).build());
            } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                configCategory.addEntry(componentBuilder.doubleInputBuilder(field, categoryKey).build());
            } else if (fieldType.isEnum()) {
                configCategory.addEntry(componentBuilder.enumSelectorBuilder(
                                field, categoryKey, (Class<? extends Enum<?>>) fieldType)
                        .build());
            } else {
                configCategory.addEntry(unsupportedDescription(fieldType).build());
                log.warn(marker, "Unsupported field type: {}", fieldType);
            }
        }

        private boolean isIgnored(Field field) {
            if (Modifier.isFinal(field.getModifiers())) return true;
            if (field.getAnnotation(Ignored.class) != null) return true;

            final LoaderSpecific loaderAnnotation = field.getAnnotation(LoaderSpecific.class);
            if (loaderAnnotation != null)
                return !List.of(loaderAnnotation.value()).contains(LoaderType.getCurrentLoader());
            else return false;
        }

        private String getCategoryKey(Field field) {
            final Category categoryAnnotation;

            final MixinList mixinList = field.getDeclaredAnnotation(MixinList.class);
            if (mixinList != null) {
                categoryAnnotation = mixinList.value();
            } else {
                categoryAnnotation = field.getAnnotation(Category.class);
            }
            return categoryAnnotation != null ? categoryAnnotation.value() : "general";
        }

        private ConfigCategory getConfigCategory(String categoryKey) {
            final ConfigCategory category = builder.getOrCreateCategory(
                    Component.translatable("config." + modId + "." + categoryKey)
            );
            final String descriptionKey = String.format("config.%s.%s.@description", modId, categoryKey);
            if (ComponentUtils.hasTranslation(descriptionKey)) {
                category.setDescription(new FormattedText[]{Component.translatable(descriptionKey)});
            }
            return category;
        }

        private FieldBuilder<?, ?, ?> unsupportedDescription(Class<?> clazz) {
            return componentBuilder.getEntryBuilder().startTextDescription(
                            Component.translatable(String.format("config.%s.unsupported.description", modId),
                                    clazz.getSimpleName()))
                    .setTooltip(Component.literal(clazz.getName()));
        }
    }
}
