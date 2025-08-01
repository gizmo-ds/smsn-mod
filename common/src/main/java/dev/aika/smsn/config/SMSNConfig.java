package dev.aika.smsn.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.aika.smsn.annotation.Category;
import dev.aika.smsn.annotation.Components;
import dev.aika.smsn.annotation.LoaderSpecific;
import dev.aika.smsn.api.LoaderType;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;

@Getter
@SuppressWarnings("unused")
public class SMSNConfig extends ModConfig {
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean ipnUpdateCheckAndUserTracking = SMSNConfigDefault.ipnUpdateCheckAndUserTracking;
    @LoaderSpecific(LoaderType.FABRIC)
    public boolean irisUpdateCheck = SMSNConfigDefault.irisUpdateCheck;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean xaeroMapVersionCheck = SMSNConfigDefault.xaeroMapVersionCheck;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean xaeroMapPatreonCheck = SMSNConfigDefault.xaeroMapPatreonCheck;
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    public boolean mruApi = SMSNConfigDefault.mruApi;

    @Category("qol")
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    @Components.Switch(checked = "enable", unchecked = "disabled")
    public boolean hideFireOverlayWhenFireResistance = SMSNConfigDefault.hideFireOverlayWhenFireResistance;
    @Category("qol")
    @LoaderSpecific({LoaderType.NEOFORGE, LoaderType.FABRIC})
    @Components.Switch(checked = "enable", unchecked = "disabled")
    public boolean hideRealmsButton = SMSNConfigDefault.hideRealmsButton;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    public static SMSNConfig load() {
        if (!CONFIG_FILE.exists() && OLD_CONFIG_FILE.exists())
            Files.move(OLD_CONFIG_FILE.toPath(), CONFIG_FILE.toPath());
        if (CONFIG_FILE.exists()) return SMSNConfig.load(SMSNConfig.class);
        final var defaultConfig = new SMSNConfig();
        defaultConfig.save();
        return defaultConfig;
    }
}
