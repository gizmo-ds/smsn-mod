package dev.aika.smsn.api;

import dev.architectury.injectables.targets.ArchitecturyTarget;

@SuppressWarnings("unused")
public enum LoaderType {
    NEOFORGE, FORGE, FABRIC, QUILT;

    public static LoaderType getCurrentLoader() {
        String currentTarget = ArchitecturyTarget.getCurrentTarget();
        return switch (currentTarget) {
            case "fabric" -> FABRIC;
            case "forge" -> FORGE;
            case "neoforge" -> NEOFORGE;
            case "quilt" -> QUILT;
            default -> null;
        };
    }
}
