package dev.aika.smsn.compat.cloth;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import dev.aika.smsn.client.gui.components.ComponentBuilder;
import dev.aika.smsn.config.SMSNConfigDefault;
import lombok.SneakyThrows;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SMSNClothConfig {
    private static final Logger log = SMSN.LOGGER;
    private static final Marker marker = MarkerFactory.getMarker("SMSNClothConfig");

    public static Screen ConfigScreen(Screen parent) {
        final ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableComponent("config.smsn.title"))
                .setSavingRunnable(SMSN.CONFIG::save);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        final ConfigCategory general = builder.getOrCreateCategory(new TranslatableComponent("config.smsn.general"));
        general.addEntry(sponsorDescription(entryBuilder));

//        final ConfigCategory qol = builder.getOrCreateCategory(new TranslatableComponent("config.smsn.qol"));
//        qol.addEntry(sponsorDescription(entryBuilder));

        final ComponentBuilder componentBuilder = new ComponentBuilder(entryBuilder, SMSN.CONFIG)
                .setModId(SMSN.MOD_ID)
                .setDefaultObject(SMSNConfigDefault.class);

        final Field[] fields = SMSN.CONFIG.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isIgnored(field)) continue;
            addEntry(builder, componentBuilder, field);
        }

        builder.transparentBackground();
        return builder.build();
    }

    private static boolean isIgnored(Field field) {
        if (Modifier.isFinal(field.getModifiers())) return true;

        final List<LoaderType> loaders = new ArrayList<>();
        final LoaderSpecific loaderAnnotation = field.getAnnotation(LoaderSpecific.class);
        if (loaderAnnotation != null) loaders.addAll(List.of(loaderAnnotation.value()));
        else loaders.add(LoaderType.getCurrentLoader());
        return !loaders.contains(LoaderType.getCurrentLoader());
    }

    private static void addEntry(ConfigBuilder builder, ComponentBuilder componentBuilder, Field field) {
        final Category categoryAnnotation = field.getAnnotation(Category.class);
        final String category = categoryAnnotation != null ? categoryAnnotation.value() : "general";

        final ConfigCategory configCategory = builder.getOrCreateCategory(
                new TranslatableComponent(String.format("config.smsn.%s", category)));

        Class<?> fieldType = field.getType();
        if (fieldType == Boolean.class || fieldType == boolean.class)
            configCategory.addEntry(componentBuilder.switchBuilder(field).setCategory(category).build());
        else log.warn(marker, "Unsupported field type: {}", fieldType);
    }

    @SneakyThrows
    public static TextListEntry sponsorDescription(ConfigEntryBuilder entryBuilder) {
        return entryBuilder.startTextDescription(
                new TranslatableComponent("config.smsn.sponsor.description",
                        new TranslatableComponent("modmenu.nameTranslation.smsn")
                                .withStyle(s -> s.withColor(ChatFormatting.AQUA).withBold(true)),
                        new TranslatableComponent("config.smsn.sponsor.description.afdian")
                                .withStyle(s -> s.withColor(ChatFormatting.DARK_PURPLE).withBold(true)
                                        .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent(SMSN.SponsorUrl)))
                                        .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, SMSN.SponsorUrl)))
                )
        ).build();
    }
}
