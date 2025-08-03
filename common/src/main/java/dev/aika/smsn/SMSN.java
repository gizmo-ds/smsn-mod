package dev.aika.smsn;

import dev.aika.smsn.config.SMSNConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SMSN {
    public static final String MOD_ID = "smsn";
    public static final String MOD_NAME = "SaveMyShitNetwork";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static SMSNConfig CONFIG = new SMSNConfig();

    public static void init() {
        CONFIG = SMSNConfig.load();
    }
}
