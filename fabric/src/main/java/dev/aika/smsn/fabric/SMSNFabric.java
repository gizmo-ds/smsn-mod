package dev.aika.smsn.fabric;

import net.fabricmc.api.ModInitializer;

import dev.aika.smsn.SMSN;

public final class SMSNFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SMSN.init();
    }
}
