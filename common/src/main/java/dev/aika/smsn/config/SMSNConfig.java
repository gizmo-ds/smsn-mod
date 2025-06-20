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

    @Category("qol")
    @LoaderSpecific(LoaderType.NEOFORGE)
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
