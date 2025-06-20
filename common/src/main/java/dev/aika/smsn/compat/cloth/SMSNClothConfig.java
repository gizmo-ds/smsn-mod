package dev.aika.smsn.compat.cloth;

import dev.aika.smsn.ModConstants;
import dev.aika.smsn.SMSN;
import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import dev.aika.smsn.config.SMSNConfigDefault;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
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
                .setTitle(Component.translatable("config.smsn.title"))
                .setSavingRunnable(SMSN.CONFIG::save);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        final ConfigCategory general = builder.getOrCreateCategory(Component.translatable("config.smsn.general"));
        general.addEntry(sponsorDescription(entryBuilder));

        final ConfigCategory qol = builder.getOrCreateCategory(Component.translatable("config.smsn.qol"));
        qol.addEntry(sponsorDescription(entryBuilder));

        Field[] fields = SMSN.CONFIG.getClass().getDeclaredFields();
        for (Field field : fields) {
            addEntry(builder, entryBuilder, field);
        }

        builder.transparentBackground();
        return builder.build();
    }

    private static BooleanListEntry makeOption(ConfigEntryBuilder builder, String category, String key) {
        return builder.startBooleanToggle(
                        Component.translatable(String.format("config.smsn.%s.%s", category, key)),
                        getConfigValueByFieldName(key))
                .setDefaultValue(getConfigDefaultValueByFieldName(key))
                .setSaveConsumer(value -> setConfigValueByFieldName(key, value))
                .setYesNoTextSupplier((v -> v ?
                        Component.translatable("config.smsn.option.yes")
                                .withStyle(s -> s.withColor(ChatFormatting.GREEN)) :
                        Component.translatable("config.smsn.option.no")
                                .withStyle(s -> s.withColor(ChatFormatting.RED))))
                .build();
    }

    private static boolean getConfigValueByFieldName(String name) {
        try {
            Field field = SMSN.CONFIG.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return (boolean) field.get(SMSN.CONFIG);
        } catch (Exception e) {
            log.error(marker, "Could not get config value for {}", name, e);
            return false;
        }
    }

    private static void setConfigValueByFieldName(String name, boolean value) {
        try {
            Class<?> clazz = SMSN.CONFIG.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(SMSN.CONFIG, value);
        } catch (Exception e) {
            log.error(marker, "Could not set config value for {}", name, e);
        }
    }

    private static boolean getConfigDefaultValueByFieldName(String name) {
        try {
            Field field = SMSNConfigDefault.class.getDeclaredField(name);
            field.setAccessible(true);
            return (boolean) field.get(null);
        } catch (Exception e) {
            log.error(marker, "Could not get config default value for {}", name, e);
            return false;
        }
    }

    private static void addEntry(ConfigBuilder builder, ConfigEntryBuilder entryBuilder, Field field) {
        if (Modifier.isFinal(field.getModifiers())) return;

        final Category categoryAnnotation = field.getAnnotation(Category.class);
        final String category = categoryAnnotation != null ? categoryAnnotation.value() : "general";

        final List<LoaderType> loaders = new ArrayList<>();
        final LoaderSpecific loaderAnnotation = field.getAnnotation(LoaderSpecific.class);
        if (loaderAnnotation != null) loaders.addAll(List.of(loaderAnnotation.value()));
        else loaders.add(LoaderType.getCurrentLoader());
        if (!loaders.contains(LoaderType.getCurrentLoader())) return;

        final ConfigCategory configCategory = builder.getOrCreateCategory(Component.translatable("config.smsn." + category));

        BooleanListEntry entry = makeOption(entryBuilder, category, field.getName());
        configCategory.addEntry(entry);
    }

    public static TextListEntry sponsorDescription(ConfigEntryBuilder entryBuilder) {
        return entryBuilder.startTextDescription(
                Component.translatable("config.smsn.sponsor.description",
                        Component.translatable("modmenu.nameTranslation.smsn")
                                .withStyle(s -> s.withColor(ChatFormatting.AQUA).withBold(true)),
                        Component.translatable("config.smsn.sponsor.description.afdian")
                                .withStyle(s -> s.withColor(ChatFormatting.DARK_PURPLE).withBold(true)
                                        .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal(ModConstants.SponsorUrl)))
                                        .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, ModConstants.SponsorUrl)))
                )).build();
    }
}
