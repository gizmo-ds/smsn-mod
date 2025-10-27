package dev.aika.smsn.config;

import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;

@Getter
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class SMSNConfig extends ModConfig {
    public boolean aetherMoaSkinsFeature = SMSNConfigDefault.aetherMoaSkinsFeature;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean immersiveEngineeringSpecialRevolvers = SMSNConfigDefault.immersiveEngineeringSpecialRevolvers;
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean petrolparkBadgeCheck = SMSNConfigDefault.petrolparkBadgeCheck;
    public boolean supplementariesCreditsCheck = SMSNConfigDefault.supplementariesCreditsCheck;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean bagusLibSupportersCheck = SMSNConfigDefault.bagusLibSupportersCheck;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean placeboTrails = SMSNConfigDefault.placeboTrails;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean placeboWings = SMSNConfigDefault.placeboWings;
    @LoaderSpecific(LoaderType.FABRIC)
    public boolean irisUpdateCheck = SMSNConfigDefault.irisUpdateCheck;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean actuallyAdditionsSpecialPeopleStuff = SMSNConfigDefault.actuallyAdditionsSpecialPeopleStuff;
    public boolean exposureGoldenCameraSkin = SMSNConfigDefault.exposureGoldenCameraSkin;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean titaniumReward = SMSNConfigDefault.titaniumReward;
    public boolean ribbitsSupporterHat = SMSNConfigDefault.ribbitsSupporterHat;
    public boolean xaeroMapVersionCheck = SMSNConfigDefault.xaeroMapVersionCheck;
    public boolean xaeroMapPatreonCheck = SMSNConfigDefault.xaeroMapPatreonCheck;
    public boolean mruApi = SMSNConfigDefault.mruApi;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean projecteUUIDCheck = SMSNConfigDefault.projecteUUIDCheck;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean hexereiSupporter = SMSNConfigDefault.hexereiSupporter;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean additionalStructuresSupporterCheck = SMSNConfigDefault.additionalStructuresSupporterCheck;
    public boolean tenshilibPatreon = SMSNConfigDefault.tenshilibPatreon;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean arsnouveauRewards = false;

    @Category("qol")
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean immersiveCavesDiscordMessage = SMSNConfigDefault.immersiveCavesDiscordMessage;

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
