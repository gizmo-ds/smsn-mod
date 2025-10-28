package dev.aika.smsn.config;

import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.annotation.MixinList;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@Getter
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class SMSNConfig extends ModConfig {
    public boolean aetherMoaSkinsFeature = true;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean immersiveEngineeringSpecialRevolvers = true;
    public boolean ipnUpdateCheckAndUserTracking = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean petrolparkBadgeCheck = false;
    public boolean supplementariesCreditsCheck = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean bagusLibSupportersCheck = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean placeboTrails = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean placeboWings = false;
    @LoaderSpecific(LoaderType.FABRIC)
    public boolean irisUpdateCheck = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean actuallyAdditionsSpecialPeopleStuff = false;
    public boolean exposureGoldenCameraSkin = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean titaniumReward = false;
    public boolean ribbitsSupporterHat = false;
    public boolean xaeroMapVersionCheck = true;
    public boolean xaeroMapPatreonCheck = false;
    public boolean mruApi = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean projecteUUIDCheck = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean hexereiSupporter = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean additionalStructuresSupporterCheck = false;
    public boolean tenshilibPatreon = false;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean arsnouveauRewards = false;
    public boolean immersiveportalsModInfoChecking = false;

    @Category("qol")
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean immersiveCavesDiscordMessage = false;

    @MixinList(value = @Category("mixins"), prefix = "dev.aika.smsn.")
    public Set<String> disabledMixins = new HashSet<>();

    @SneakyThrows
    public static SMSNConfig load() {
        if (!CONFIG_FILE.exists() && OLD_CONFIG_FILE.exists())
            Files.move(OLD_CONFIG_FILE.toPath(), CONFIG_FILE.toPath());
        if (CONFIG_FILE.exists()) return SMSNConfig.load(SMSNConfig.class);
        final var defaultConfig = new SMSNConfig();
        defaultConfig.save();
        return defaultConfig;
    }
}
