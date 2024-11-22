package dev.aika.smsn.config;

public class ModConfigDefaults implements ModConfig {
    public static final boolean AETHER_MOA_SKINS_FEATURE = true;

    public static final boolean QUARK_CONTRIBUTOR_CHECK = false;

    public static final boolean IPN_UPDATE_CHECK_AND_USER_TRACKING = false;

    public static final boolean KUBEJS_UPDATE_CHECK = false;

    public static final boolean XAERO_MAP_PATREON_CHECK = false;
    public static final boolean XAERO_MAP_VERSION_CHECK = false;

    public static final boolean CITADEL_APRIL_FOOLS_CONTENT = false;
    public static final boolean ALEX_MODS_CONTRIBUTOR_CHECK = false;

    public static final boolean OBSCURE_MODS_CHECK = false;

    public boolean aetherMoaSkinsFeature() {
        return AETHER_MOA_SKINS_FEATURE;
    }

    public boolean quarkContributorCheck() {
        return QUARK_CONTRIBUTOR_CHECK;
    }

    public boolean ipnUpdateCheckAndUserTracking() {
        return IPN_UPDATE_CHECK_AND_USER_TRACKING;
    }

    public boolean kubejsUpdateCheck() {
        return KUBEJS_UPDATE_CHECK;
    }

    public boolean xaeroMapPatreonCheck() {
        return XAERO_MAP_PATREON_CHECK;
    }

    public boolean xaeroMapVersionCheck() {
        return XAERO_MAP_VERSION_CHECK;
    }

    public boolean citadelAprilFoolsContent() {
        return CITADEL_APRIL_FOOLS_CONTENT;
    }

    public boolean alexModsContributorCheck() {
        return ALEX_MODS_CONTRIBUTOR_CHECK;
    }

    public boolean obscureModsCheck() {
        return OBSCURE_MODS_CHECK;
    }
}
