package dev.aika.smsn.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

@UtilityClass
public class ComponentUtils extends net.minecraft.network.chat.ComponentUtils {
    private static final Language language = Language.getInstance();

    public static MutableComponent createWithFallback(Object... keyOrComponent) {
        for (Object v : keyOrComponent) {
            if (v == null) continue;
            if (v instanceof String key) {
                if (!hasTranslation(key)) continue;
                return Component.translatable(key);
            } else if (v instanceof Component component) {
                if (component.getContents() instanceof TranslatableContents tc) {
                    if (tc.getKey().isEmpty()) continue;
                    if (tc.getFallback() != null && !tc.getFallback().isEmpty()) {
                        if (!hasTranslation(tc.getKey()) && !hasTranslation(tc.getFallback())) continue;
                    }
                }
                return component.copy();
            }
        }
        return null;
    }

    public static MutableComponent literal(String key) {
        if (key == null) return null;
        return Component.literal(key);
    }

    public static boolean hasTranslation(String key) {
        return language.has(key);
    }
}
