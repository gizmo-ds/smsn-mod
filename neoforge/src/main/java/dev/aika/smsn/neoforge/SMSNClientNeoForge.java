package dev.aika.smsn.neoforge;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.config.ClothConfigCommonImpl;
import dev.aika.smsn.neoforge.config.ClothConfigImpl;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = SMSN.MOD_ID, dist = Dist.CLIENT)
public class SMSNClientNeoForge {
    private static boolean clothConfigIsInstalled() {
        return SMSNPlatformImpl.isModLoaded("cloth_config");
    }

    public SMSNClientNeoForge(IEventBus ignoredModEventBus, ModContainer modContainer) {
        if (clothConfigIsInstalled()) ClothConfigCommonImpl.loadConfig();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ClothConfigImpl::setup);
    }
}
