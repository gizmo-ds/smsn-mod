package dev.aika.smsn.client.gui;

import dev.aika.smsn.SMSNPlatform;
import dev.aika.smsn.mixin.ModMixinInfo;
import dev.aika.smsn.mixin.ModMixinManager;
import dev.aika.smsn.utils.ComponentUtils;
import lombok.SneakyThrows;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConfigMixinList {
    private final ComponentBuilder componentBuilder;
    private final Field field;
    private final ConfigCategory category;

    private final Lock lock = new ReentrantLock();
    private Set<String> disabledMixins;

    public ConfigMixinList(ComponentBuilder componentBuilder, Field field, ConfigCategory category) {
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
        final var builder = componentBuilder.getEntryBuilder();
        final var infos = ModMixinManager.getMixinInfos();
        for (ModMixinInfo info : infos) {
            final var subTitle = ComponentUtils.createWithFallback(
                            String.format("smsn.mod.%s", info.getModid()),
                            "modmenu.nameTranslation." + info.getModid(),
                            ComponentUtils.literal(SMSNPlatform.getModName(info.getModid())),
                            ComponentUtils.literal(info.getModid())
                    )
                    .withStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD));
            if (SMSNPlatform.isModLoaded(info.getModid())) {
                subTitle.append(Component.translatable("config.smsn.mixins.modLoaded"));
            }
            category.addEntry(
                    builder.startTextDescription(subTitle)
                            .setTooltip(Component.translatable("config.smsn.mixins.modIdTooptip", info.getModid()))
                            .build()
            );
            for (String mixinClass : info.getMixinClasses()) {
                final String shortName = mixinClass.replaceFirst("dev.aika.smsn.", "");
                category.addEntry(
                        builder.startBooleanToggle(Component.literal(shortName), isEnable(mixinClass))
                                .setSaveConsumer(v -> setEnable(mixinClass, v))
                                .setYesNoTextSupplier(v ->
                                        Component.translatable(
                                                String.format("config.smsn.@switch.%s", (v ? "enable" : "disabled"))
                                        ))
                                .setTooltip(Component.literal(mixinClass))
                                .requireRestart()
                                .build()
                );
            }
        }
    }
}
