package dev.aika.smsn.client.gui;

import dev.aika.smsn.annotation.MixinList;
import dev.aika.smsn.api.ModPlatform;
import dev.aika.smsn.mixin.ModMixinInfo;
import dev.aika.smsn.mixin.ModMixinManager;
import dev.aika.smsn.utils.ComponentUtils;
import lombok.SneakyThrows;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConfigMixinList {
    private final ComponentBuilder componentBuilder;
    private final Field field;
    private final ConfigCategory category;
    private final String modId;
    private final ModMixinManager mixinManager;

    private final Lock lock = new ReentrantLock();
    private Set<String> disabledMixins;

    public ConfigMixinList(
            String modId, ModMixinManager mixinManager,
            ComponentBuilder componentBuilder, Field field, ConfigCategory category) {
        this.modId = modId;
        this.mixinManager = mixinManager;
        this.componentBuilder = componentBuilder;
        this.field = field;
        this.category = category;
        this.init();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private void init() {
        field.setAccessible(true);
        disabledMixins = (Set<String>) field.get(componentBuilder.getConfigObject());
    }

    private boolean isEnable(String mixinClass) {
        return !disabledMixins.contains(mixinClass);
    }

    private void setEnable(String mixinClass, boolean enable) {
        lock.lock();
        if (enable) disabledMixins.remove(mixinClass);
        else disabledMixins.add(mixinClass);
        lock.unlock();
    }

    @SneakyThrows
    public void save() {
        lock.lock();
        field.setAccessible(true);
        field.set(componentBuilder.getConfigObject(), disabledMixins);
        lock.unlock();
    }

    public void create() {
        final MixinList annotation = field.getAnnotation(MixinList.class);
        final var builder = componentBuilder.getEntryBuilder();
        final var infos = mixinManager.getMixinInfos();
        for (ModMixinInfo info : infos) {
            final var subTitle = Objects.requireNonNull(ComponentUtils.createWithFallback(
                            String.format("%s.mod.%s", modId, info.getModId()),
                            "modmenu.nameTranslation." + info.getModId(),
                            ComponentUtils.literal(ModPlatform.getModName(info.getModId())),
                            ComponentUtils.literal(info.getModId())
                    ))
                    .withStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD));
            if (ModPlatform.isModLoaded(info.getModId()))
                subTitle.append(Component.translatable(String.format("config.%s.mixins.modLoaded", modId)));
            category.addEntry(
                    builder.startTextDescription(subTitle)
                            .setTooltip(Component.translatable(
                                    String.format("config.%s.mixins.modIdTooltip", modId),
                                    info.getModId()))
                            .build()
            );
            for (String mixinClass : info.getMixinClasses()) {
                final String shortName = mixinClass.replaceFirst(annotation.prefix(), "");
                category.addEntry(
                        builder.startBooleanToggle(Component.literal(shortName), isEnable(mixinClass))
                                .setSaveConsumer(v -> setEnable(mixinClass, v))
                                .setYesNoTextSupplier(v ->
                                        Component.translatable(
                                                String.format("config.%s.@switch.%s", modId, (v ? "enable" : "disabled"))
                                        ))
                                .setTooltip(Component.literal(mixinClass))
                                .requireRestart()
                                .build()
                );
            }
        }
    }
}
