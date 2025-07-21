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

    @LoaderSpecific(LoaderType.FORGE)
    public boolean aetherMoaSkinsFeature = SMSNConfigDefault.aetherMoaSkinsFeature;
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
    @LoaderSpecific(LoaderType.FORGE)
    public boolean petrolparkBadgeCheck = SMSNConfigDefault.petrolparkBadgeCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean obscureModsCheck = SMSNConfigDefault.obscureModsCheck;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean supplementariesCreditsCheck = SMSNConfigDefault.supplementariesCreditsCheck;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean botaniaContributorCheck = SMSNConfigDefault.botaniaContributorCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean bagusLibSupportersCheck = SMSNConfigDefault.bagusLibSupportersCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean enigmaticLegacyUpdateCheck = SMSNConfigDefault.enigmaticLegacyUpdateCheck;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean enigmaticLegacyFetchDevotedBelievers = SMSNConfigDefault.enigmaticLegacyFetchDevotedBelievers;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean placeboTrails = SMSNConfigDefault.placeboTrails;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean placeboWings = SMSNConfigDefault.placeboWings;
    @LoaderSpecific(LoaderType.FABRIC)
    public boolean irisUpdateCheck = SMSNConfigDefault.irisUpdateCheck;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean adAstraStation = SMSNConfigDefault.adAstraStation;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean exposureGoldenCameraSkin = SMSNConfigDefault.exposureGoldenCameraSkin;
    @LoaderSpecific(LoaderType.FORGE)
    public boolean titaniumReward = SMSNConfigDefault.titaniumReward;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean ribbitsSupporterHat = SMSNConfigDefault.ribbitsSupporterHat;
    @LoaderSpecific({LoaderType.FORGE, LoaderType.FABRIC})
    public boolean mruApi = SMSNConfigDefault.mruApi;

    @Category("qol")
    @LoaderSpecific(LoaderType.FORGE)
    public boolean citadelAprilFoolsContent = SMSNConfigDefault.citadelAprilFoolsContent;
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
