package dev.aika.smsn.config;

import dev.aika.smsn.SMSN;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@SuppressWarnings("CanBeFinal")
@Config(name = SMSN.MOD_ID)
public class ModConfigData implements ConfigData, ModConfig {
    @ConfigEntry.Gui.Tooltip()
    public boolean aetherMoaSkinsFeature = ModConfigDefaults.AETHER_MOA_SKINS_FEATURE;

    @ConfigEntry.Gui.Tooltip()
    public boolean quarkContributorCheck = ModConfigDefaults.QUARK_CONTRIBUTOR_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean ipnUpdateCheckAndUserTracking = ModConfigDefaults.IPN_UPDATE_CHECK_AND_USER_TRACKING;

    @ConfigEntry.Gui.Tooltip()
    public boolean kubejsUpdateCheck = ModConfigDefaults.KUBEJS_UPDATE_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean xaeroMapPatreonCheck = ModConfigDefaults.XAERO_MAP_PATREON_CHECK;
    @ConfigEntry.Gui.Tooltip()
    public boolean xaeroMapVersionCheck = ModConfigDefaults.XAERO_MAP_VERSION_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean citadelAprilFoolsContent = ModConfigDefaults.CITADEL_APRIL_FOOLS_CONTENT;
    @ConfigEntry.Gui.Tooltip()
    public boolean alexModsContributorCheck = ModConfigDefaults.ALEX_MODS_CONTRIBUTOR_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean obscureModsCheck = ModConfigDefaults.OBSCURE_MODS_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean immersiveEngineeringSpecialRevolvers = ModConfigDefaults.IMMERSIVE_ENGINEERING_SPECIAL_REVOLVERS;

    @Override
    public boolean aetherMoaSkinsFeature() {
        return aetherMoaSkinsFeature;
    }

    @Override
    public boolean quarkContributorCheck() {
        return quarkContributorCheck;
    }

    @Override
    public boolean ipnUpdateCheckAndUserTracking() {
        return ipnUpdateCheckAndUserTracking;
    }

    @Override
    public boolean kubejsUpdateCheck() {
        return kubejsUpdateCheck;
    }

    @Override
    public boolean xaeroMapPatreonCheck() {
        return xaeroMapPatreonCheck;
    }

    @Override
    public boolean xaeroMapVersionCheck() {
        return xaeroMapVersionCheck;
    }

    @Override
    public boolean citadelAprilFoolsContent() {
        return citadelAprilFoolsContent;
    }

    @Override
    public boolean alexModsContributorCheck() {
        return alexModsContributorCheck;
    }

    @Override
    public boolean obscureModsCheck() {
        return obscureModsCheck;
    }

    @Override
    public boolean immersiveEngineeringSpecialRevolvers() {
        return immersiveEngineeringSpecialRevolvers;
    }
}
