package dev.aika.smsn.forge;

import net.minecraftforge.fml.common.Mod;

import dev.aika.smsn.SMSN;

@Mod(SMSN.MOD_ID)
public final class SMSNForge {
    public SMSNForge() {
        // Run our common setup.
        SMSN.init();
    }
}
