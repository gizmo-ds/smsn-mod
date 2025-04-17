package dev.aika.smsn.neoforge;

import dev.aika.smsn.SMSN;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(SMSN.MOD_ID)
public final class SMSNNeoForge {
    public SMSNNeoForge(IEventBus ignoredBus, ModContainer ignoredContainer) {
        SMSN.init();
    }
}
