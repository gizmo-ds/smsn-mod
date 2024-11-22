package dev.aika.smsn.forge;

import dev.aika.smsn.forge.config.ClothConfigImpl;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import dev.aika.smsn.SMSN;

@Mod(SMSN.MOD_ID)
public final class SMSNForge {
    public SMSNForge() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> SMSNForge::clientInit);
        SMSN.init();
    }

    public static void clientInit() {
        ClothConfigImpl.loadConfig();

        ClothConfigImpl.registerConfigGui();
    }
}
