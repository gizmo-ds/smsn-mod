package dev.aika.smsn.config;

import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.annotation.MixinList;
import dev.aika.smsn.annotation.RequiresRestart;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.*;

@Getter
@SuppressWarnings("unused")
public class SMSNConfig extends ModConfig {
    //NOTE: Not adding the @LoaderSpecific annotation means the field applies to all Mod Loaders.
    //NOTE: Not adding the @Category annotation means using the "general" category.

    public boolean aetherMoaSkinsFeature = true;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean immersiveEngineeringSpecialRevolvers = true;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean quarkContributorCheck = false;
    public boolean ipnUpdateCheckAndUserTracking = false;
    public boolean xaeroMapPatreonCheck = false;
    public boolean xaeroMapVersionCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean alexModsContributorCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean petrolparkBadgeCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean obscureModsCheck = false;
    public boolean supplementariesCreditsCheck = false;
    public boolean botaniaContributorCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean bagusLibSupportersCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean enigmaticLegacyUpdateCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean enigmaticLegacyFetchDevotedBelievers = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean placeboTrails = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean placeboWings = false;
    @LoaderSpecific(LoaderType.FABRIC)
    public boolean irisUpdateCheck = false;
    public boolean adAstraStation = false;
    public boolean exposureGoldenCameraSkin = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean titaniumReward = false;
    public boolean ribbitsSupporterHat = false;
    public boolean mruApi = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean projecteUUIDCheck = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean baCheckUpdate = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean hexereiSupporter = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean voidscapeDonator = false;
    public boolean tenshilibPatreon = false;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean additionalStructuresSupporterCheck = false;
    public boolean createTramAdditionsTTL = true;

    @Category("qol")
    @RequiresRestart
    @LoaderSpecific(LoaderType.FORGE)
    public QuarkCelebration quarkCelebration = QuarkCelebration.HideLGBTQIA;
    @Category("qol")
    @LoaderSpecific(LoaderType.FORGE)
    public boolean citadelAprilFoolsContent = false;
    @Category("qol")
    @LoaderSpecific(LoaderType.FORGE)
    public boolean immersiveCavesDiscordMessage = false;

    @MixinList(@Category("mixins"))
    public Set<String> disabledMixins = new HashSet<>();

    @SneakyThrows
    public static SMSNConfig load() {
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
