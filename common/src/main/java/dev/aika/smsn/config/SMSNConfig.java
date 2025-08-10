package dev.aika.smsn.config;

import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;

@Getter
@SuppressWarnings("unused")
public class SMSNConfig extends ModConfig {
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean aetherMoaSkinsFeature = SMSNConfigDefault.aetherMoaSkinsFeature;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean immersiveEngineeringSpecialRevolvers = SMSNConfigDefault.immersiveEngineeringSpecialRevolvers;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean petrolparkBadgeCheck = SMSNConfigDefault.petrolparkBadgeCheck;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
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
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean exposureGoldenCameraSkin = SMSNConfigDefault.exposureGoldenCameraSkin;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean titaniumReward = SMSNConfigDefault.titaniumReward;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean ribbitsSupporterHat = SMSNConfigDefault.ribbitsSupporterHat;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean xaeroMapVersionCheck = SMSNConfigDefault.xaeroMapVersionCheck;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean xaeroMapPatreonCheck = SMSNConfigDefault.xaeroMapPatreonCheck;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean mruApi = SMSNConfigDefault.mruApi;
    @LoaderSpecific(LoaderType.NEOFORGE)
    public boolean projecteUUIDCheck = SMSNConfigDefault.projecteUUIDCheck;

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
