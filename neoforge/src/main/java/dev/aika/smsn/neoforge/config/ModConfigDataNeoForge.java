package dev.aika.smsn.neoforge.config;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.config.ModConfig;
import dev.aika.smsn.config.ModConfigDefaults;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = SMSN.MOD_ID)
public class ModConfigDataNeoForge implements ConfigData, ModConfig {
    @ConfigEntry.Gui.Tooltip()
    public boolean aetherMoaSkinsFeature = ModConfigDefaults.AETHER_MOA_SKINS_FEATURE;

    @ConfigEntry.Gui.Tooltip()
    public boolean ipnUpdateCheckAndUserTracking = ModConfigDefaults.IPN_UPDATE_CHECK_AND_USER_TRACKING;

    @ConfigEntry.Gui.Tooltip()
    public boolean kubejsUpdateCheck = ModConfigDefaults.KUBEJS_UPDATE_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean petrolparkBadgeCheck = ModConfigDefaults.PETROLPARK_BADGE_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean supplementariesCreditsCheck = ModConfigDefaults.SUPPLEMENTARIES_CREDITS_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean bagusLibSupportersCheck = ModConfigDefaults.BAGUS_LIB_SUPPORTERS_CHECK;

    @ConfigEntry.Gui.Tooltip()
    public boolean immersiveEngineeringSpecialRevolvers = ModConfigDefaults.IMMERSIVE_ENGINEERING_SPECIAL_REVOLVERS;

    @Override
    public boolean aetherMoaSkinsFeature() {
        return aetherMoaSkinsFeature;
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
    public boolean petrolparkBadgeCheck() {
        return petrolparkBadgeCheck;
    }

    @Override
    public boolean supplementariesCreditsCheck() {
        return supplementariesCreditsCheck;
    }

    @Override
    public boolean bagusLibSupportersCheck() {
        return bagusLibSupportersCheck;
    }

    @Override
    public boolean immersiveEngineeringSpecialRevolvers() {
        return immersiveEngineeringSpecialRevolvers;
    }
}
