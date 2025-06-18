package dev.aika.smsn.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.Jankson;
import blue.endless.jankson.annotation.SerializedName;
import dev.aika.smsn.ModConstants;
import dev.aika.smsn.SMSN;
import lombok.Getter;

@Getter
public class SMSNConfig extends ModConfig {
    @SerializedName("yuumi")
    @Comment("❤ Loving " + SMSN.MOD_NAME + " ? Consider supporting development on " + ModConstants.SponsorUrl + " to help keep updates coming! ❤")
    public String sponsor = ModConstants.MyCat;

    public boolean aetherMoaSkinsFeature = SMSNConfigDefault.aetherMoaSkinsFeature;
    public boolean immersiveEngineeringSpecialRevolvers = SMSNConfigDefault.immersiveEngineeringSpecialRevolvers;
    public boolean quarkContributorCheck = SMSNConfigDefault.quarkContributorCheck;
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    public boolean xaeroMapPatreonCheck = SMSNConfigDefault.xaeroMapPatreonCheck;
    public boolean xaeroMapVersionCheck = SMSNConfigDefault.xaeroMapVersionCheck;
    public boolean citadelAprilFoolsContent = SMSNConfigDefault.citadelAprilFoolsContent;
    public boolean alexModsContributorCheck = SMSNConfigDefault.alexModsContributorCheck;
    public boolean petrolparkBadgeCheck = SMSNConfigDefault.petrolparkBadgeCheck;
    public boolean obscureModsCheck = SMSNConfigDefault.obscureModsCheck;
    public boolean supplementariesCreditsCheck = SMSNConfigDefault.supplementariesCreditsCheck;
    public boolean botaniaContributorCheck = SMSNConfigDefault.botaniaContributorCheck;
    public boolean bagusLibSupportersCheck = SMSNConfigDefault.bagusLibSupportersCheck;
    public boolean enigmaticLegacyUpdateCheck = SMSNConfigDefault.enigmaticLegacyUpdateCheck;
    public boolean enigmaticLegacyFetchDevotedBelievers = SMSNConfigDefault.enigmaticLegacyFetchDevotedBelievers;
    public boolean placeboTrails = SMSNConfigDefault.placeboTrails;
    public boolean placeboWings = SMSNConfigDefault.placeboWings;
    public boolean irisUpdateCheck = SMSNConfigDefault.irisUpdateCheck;
    public boolean immersiveCavesDiscordMessage = SMSNConfigDefault.immersiveCavesDiscordMessage;
    public boolean adAstraStation = SMSNConfigDefault.adAstraStation;
    public boolean exposureGoldenCameraSkin = SMSNConfigDefault.exposureGoldenCameraSkin;

    private static final Jankson JANKSON = Jankson.builder().build();

    public SMSNConfig() {
        super(JANKSON);
    }

    public static SMSNConfig load() {
        if (CONFIG_FILE.exists()) return SMSNConfig.load(SMSNConfig.class, JANKSON);
        final var defaultConfig = new SMSNConfig();
        defaultConfig.save();
        return defaultConfig;
    }
}
