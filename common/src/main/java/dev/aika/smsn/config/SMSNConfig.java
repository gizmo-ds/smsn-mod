package dev.aika.smsn.config;

import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.annotation.RequiresRestart;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@SuppressWarnings("unused")
public class SMSNConfig extends ModConfig {
    //NOTE: Not adding the @LoaderSpecific annotation means the field applies to all Mod Loaders.
    //NOTE: Not adding the @Category annotation means using the "general" category.

    @LoaderSpecific(LoaderType.FORGE)
    public boolean immersiveEngineeringSpecialRevolvers = SMSNConfigDefault.immersiveEngineeringSpecialRevolvers;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean quarkContributorCheck = SMSNConfigDefault.quarkContributorCheck;
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    public boolean xaeroMapPatreonCheck = SMSNConfigDefault.xaeroMapPatreonCheck;
    public boolean xaeroMapVersionCheck = SMSNConfigDefault.xaeroMapVersionCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean alexModsContributorCheck = SMSNConfigDefault.alexModsContributorCheck;
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
    @LoaderSpecific(LoaderType.FORGE)
    public boolean projecteUUIDCheck = SMSNConfigDefault.projecteUUIDCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean voidscapeDonator = SMSNConfigDefault.voidscapeDonator;
    public boolean tenshilibPatreon = SMSNConfigDefault.tenshilibPatreon;

    @Category("qol")
    @RequiresRestart
    @LoaderSpecific(LoaderType.FORGE)
    public QuarkCelebration quarkCelebration = SMSNConfigDefault.quarkCelebration;

    @SneakyThrows
    public static SMSNConfig load() {
        if (!CONFIG_FILE.exists() && OLD_CONFIG_FILE.exists())
            Files.move(OLD_CONFIG_FILE.toPath(), CONFIG_FILE.toPath());
        if (CONFIG_FILE.exists()) return SMSNConfig.load(SMSNConfig.class);
        final var defaultConfig = new SMSNConfig();
        defaultConfig.save();
        return defaultConfig;
    }

    public enum QuarkCelebration {
        ShowAll(),
        HideAll("all"),
        @SuppressWarnings("SpellCheckingInspection")
        HideLGBTQIA(
                "iad", "iad2", "idr", "ld", "lvd", "ncod", "nbpd", "ppad", "tdr", "tdv", "zdd",
                "pm", "baw", "taw"
        );

        private final List<String> CELEBRATIONS = new ArrayList<>();

        QuarkCelebration(String... celebrations) {
            CELEBRATIONS.addAll(Arrays.asList(celebrations));
        }

        public boolean isHide(String name) {
            if (CELEBRATIONS.size() == 1 && CELEBRATIONS.get(0).equals("all")) return true;
            return CELEBRATIONS.contains(name);
        }
    }
}
