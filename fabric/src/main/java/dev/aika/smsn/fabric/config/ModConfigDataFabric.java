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
    public boolean ipnUpdateCheckAndUserTracking = ModConfigDefaults.IPN_UPDATE_CHECK_AND_USER_TRACKING;

    @ConfigEntry.Gui.Tooltip()
    public boolean supplementariesCreditsCheck = ModConfigDefaults.SUPPLEMENTARIES_CREDITS_CHECK;

    @Override
    public boolean aetherMoaSkinsFeature() {
        return ModConfigDefaults.AETHER_MOA_SKINS_FEATURE;
    }

    @Override
    public boolean ipnUpdateCheckAndUserTracking() {
        return ipnUpdateCheckAndUserTracking;
    }

    @Override
    public boolean kubejsUpdateCheck() {
        return ModConfigDefaults.KUBEJS_UPDATE_CHECK;
    }

    @Override
    public boolean petrolparkBadgeCheck() {
        return ModConfigDefaults.PETROLPARK_BADGE_CHECK;
    }

    @Override
    public boolean supplementariesCreditsCheck() {
        return supplementariesCreditsCheck;
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
