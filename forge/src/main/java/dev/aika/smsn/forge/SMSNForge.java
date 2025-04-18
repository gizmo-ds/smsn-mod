package dev.aika.smsn.forge;

import dev.aika.smsn.forge.compat.cloth.ClothConfigCompat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import dev.aika.smsn.SMSN;

@Mod(SMSN.MOD_ID)
public final class SMSNForge {
    public SMSNForge() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> SMSNForge::clientInit);
        SMSN.init();
    }

    public static void clientInit() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(ClothConfigCompat::setup));
    }
}
