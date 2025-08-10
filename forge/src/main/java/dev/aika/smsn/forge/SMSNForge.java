package dev.aika.smsn.forge;

import dev.aika.smsn.forge.compat.ClothConfigCompat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import dev.aika.smsn.SMSN;
import net.minecraftforge.network.NetworkConstants;

@Mod(SMSN.MOD_ID)
public final class SMSNForge {
    public SMSNForge() {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> SMSNForge::clientInit);
        SMSN.init();
    }

    public static void clientInit() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        DistExecutor.safeRunWhenOn(Dist.CLIENT,
                () -> () -> ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
                        () -> new ConfigGuiHandler.ConfigGuiFactory(ClothConfigCompat::setup)));
    }
}
