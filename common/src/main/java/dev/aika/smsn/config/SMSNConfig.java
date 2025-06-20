package dev.aika.smsn.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.Jankson;
import blue.endless.jankson.annotation.SerializedName;
import dev.aika.smsn.ModConstants;
import dev.aika.smsn.SMSN;
import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class SMSNConfig extends ModConfig {
    @SerializedName("yuumi")
    @Comment("❤ Loving " + SMSN.MOD_NAME + " ? Consider supporting development on " + ModConstants.SponsorUrl + " to help keep updates coming! ❤")
    private static final String sponsor = ModConstants.MyCat;

    public boolean aetherMoaSkinsFeature = SMSNConfigDefault.aetherMoaSkinsFeature;
    public boolean immersiveEngineeringSpecialRevolvers = SMSNConfigDefault.immersiveEngineeringSpecialRevolvers;
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    public boolean petrolparkBadgeCheck = SMSNConfigDefault.petrolparkBadgeCheck;
    public boolean supplementariesCreditsCheck = SMSNConfigDefault.supplementariesCreditsCheck;
    public boolean bagusLibSupportersCheck = SMSNConfigDefault.bagusLibSupportersCheck;
    public boolean placeboTrails = SMSNConfigDefault.placeboTrails;
    public boolean placeboWings = SMSNConfigDefault.placeboWings;
    public boolean irisUpdateCheck = SMSNConfigDefault.irisUpdateCheck;
    public boolean actuallyAdditionsSpecialPeopleStuff = SMSNConfigDefault.actuallyAdditionsSpecialPeopleStuff;
    public boolean exposureGoldenCameraSkin = SMSNConfigDefault.exposureGoldenCameraSkin;
    public boolean titaniumReward = SMSNConfigDefault.titaniumReward;
    public boolean ribbitsSupporterHat = SMSNConfigDefault.ribbitsSupporterHat;

    @Category("qol")
    @LoaderSpecific(LoaderType.FORGE)
    public boolean immersiveCavesDiscordMessage = SMSNConfigDefault.immersiveCavesDiscordMessage;

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
