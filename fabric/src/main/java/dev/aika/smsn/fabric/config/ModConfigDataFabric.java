package dev.aika.smsn.fabric.config;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.config.ModConfig;
import dev.aika.smsn.config.ModConfigDefaults;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = SMSN.MOD_ID)
public class ModConfigDataFabric implements ConfigData, ModConfig {
    @ConfigEntry.Gui.Tooltip()
    public boolean botaniaContributorCheck = ModConfigDefaults.BOTANIA_CONTRIBUTOR_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean ipnUpdateCheckAndUserTracking = ModConfigDefaults.IPN_UPDATE_CHECK_AND_USER_TRACKING;

    @ConfigEntry.Gui.Tooltip()
    public boolean xaeroMapPatreonCheck = ModConfigDefaults.XAERO_MAP_PATREON_CHECK;
    @ConfigEntry.Gui.Tooltip()
    public boolean xaeroMapVersionCheck = ModConfigDefaults.XAERO_MAP_VERSION_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean supplementariesCreditsCheck = ModConfigDefaults.SUPPLEMENTARIES_CREDITS_CHECK;

    @Override
    public boolean aetherMoaSkinsFeature() {
        return ModConfigDefaults.AETHER_MOA_SKINS_FEATURE;
    }

    @Override
    public boolean quarkContributorCheck() {
        return ModConfigDefaults.QUARK_CONTRIBUTOR_CHECK;
    }

    @Override
    public boolean ipnUpdateCheckAndUserTracking() {
        return ipnUpdateCheckAndUserTracking;
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
        return ModConfigDefaults.CITADEL_APRIL_FOOLS_CONTENT;
    }

    @Override
    public boolean alexModsContributorCheck() {
        return ModConfigDefaults.ALEX_MODS_CONTRIBUTOR_CHECK;
    }

    @Override
    public boolean petrolparkBadgeCheck() {
        return ModConfigDefaults.PETROLPARK_BADGE_CHECK;
    }

    @Override
    public boolean obscureModsCheck() {
        return ModConfigDefaults.OBSCURE_MODS_CHECK;
    }

    @Override
    public boolean supplementariesCreditsCheck() {
        return supplementariesCreditsCheck;
    }

    @Override
    public boolean botaniaContributorCheck() {
        return botaniaContributorCheck;
    }

    @Override
    public boolean bagusLibSupportersCheck() {
        return ModConfigDefaults.BAGUS_LIB_SUPPORTERS_CHECK;
    }

    @Override
    public boolean immersiveEngineeringSpecialRevolvers() {
        return ModConfigDefaults.IMMERSIVE_ENGINEERING_SPECIAL_REVOLVERS;
    }
}
