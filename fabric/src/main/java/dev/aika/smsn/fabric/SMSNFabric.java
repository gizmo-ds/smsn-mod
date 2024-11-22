package dev.aika.smsn.fabric;

import dev.aika.smsn.fabric.config.ClothConfigImpl;
import net.fabricmc.api.ModInitializer;

import dev.aika.smsn.SMSN;

public final class SMSNFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ClothConfigImpl.loadConfig();
        SMSN.init();
    }
}
