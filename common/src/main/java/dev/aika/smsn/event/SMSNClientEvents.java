package dev.aika.smsn.event;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.SMSNPlatform;
import dev.aika.smsn.client.gui.screen.VersionCheckWarningScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public final class SMSNClientEvents {
    private static boolean firstTitleScreen = false;

    public static void versionCheckWarn(final Screen screen) {
        //noinspection ConstantValue
        if (!firstTitleScreen && SMSN.CONFIG.isVersionCheckWarn() && SMSNPlatform.isModVersionCheckEnabled()) {
            Minecraft.getInstance().setScreen(new VersionCheckWarningScreen(screen));
            firstTitleScreen = true;
        }
    }
}
