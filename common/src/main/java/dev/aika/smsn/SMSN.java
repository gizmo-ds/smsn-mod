package dev.aika.smsn;

import dev.aika.smsn.config.ModConfig;
import dev.aika.smsn.config.ModConfigDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SMSN {
    public static final String MOD_ID = "smsn";
    public static final String MOD_NAME = "SMSN";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static ModConfig CONFIG = new ModConfigDefaults();

    public static void init() {
    }
}
