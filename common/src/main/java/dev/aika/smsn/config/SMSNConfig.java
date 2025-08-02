package dev.aika.smsn.config;

import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;

@Getter
@SuppressWarnings("unused")
public class SMSNConfig extends ModConfig {
    @LoaderSpecific(LoaderType.FORGE)
    public boolean immersiveEngineeringSpecialRevolvers = SMSNConfigDefault.immersiveEngineeringSpecialRevolvers;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean quarkContributorCheck = SMSNConfigDefault.quarkContributorCheck;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean xaeroMapPatreonCheck = SMSNConfigDefault.xaeroMapPatreonCheck;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean xaeroMapVersionCheck = SMSNConfigDefault.xaeroMapVersionCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean alexModsContributorCheck = SMSNConfigDefault.alexModsContributorCheck;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean botaniaContributorCheck = SMSNConfigDefault.botaniaContributorCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean bagusLibSupportersCheck = SMSNConfigDefault.bagusLibSupportersCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean enigmaticLegacyUpdateCheck = SMSNConfigDefault.enigmaticLegacyUpdateCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean enigmaticLegacyFetchDevotedBelievers = SMSNConfigDefault.enigmaticLegacyFetchDevotedBelievers;
    @LoaderSpecific(LoaderType.FABRIC)
    public boolean irisUpdateCheck = SMSNConfigDefault.irisUpdateCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean titaniumReward = SMSNConfigDefault.titaniumReward;

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
