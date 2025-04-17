package dev.aika.smsn.config;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import dev.aika.smsn.SMSN;
import dev.aika.smsn.SMSNPlatform;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final Logger log = SMSN.LOGGER;
    private static final Marker MARKER = MarkerFactory.getMarker("ModConfig");

    protected static final File CONFIG_FILE = new File(SMSNPlatform.getConfigDir().toFile(), SMSN.MOD_ID + ".json5");
    protected static Jankson JANKSON;

    public ModConfig(Jankson jankson) {
        JANKSON = jankson;
    }

    protected static <T> T load(Class<T> clazz, Jankson jankson) {
        final JsonObject configJson;
        try {
            configJson = jankson.load(CONFIG_FILE);
        } catch (IOException e) {
            log.error(MARKER, "Failed to load config file: {}", CONFIG_FILE, e);
            return null;
        } catch (SyntaxError e) {
            log.error(MARKER, "Failed to parse config file: {}", CONFIG_FILE, e);
            return null;
        }
        return jankson.fromJson(configJson, clazz);
    }

    public void save() {
        final var configStr = this.toJson().toJson(true, true);
        try (var writer = new FileWriter(CONFIG_FILE)) {
            writer.write(configStr);
        } catch (IOException e) {
            log.error(MARKER, "Failed to write config file {}", CONFIG_FILE, e);
        }
    }

    public JsonElement toJson() {
        return JANKSON.toJson(this);
    }
}
