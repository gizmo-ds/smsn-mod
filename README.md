# Save My Shaky Network (1.20.1)

[![AFDIAN](https://img.shields.io/badge/%E7%88%B1%E5%8F%91%E7%94%B5-Gizmo-%23946ce6)](https://afdian.com/a/gizmo)
[![CurseForge Downloads](https://img.shields.io/curseforge/dt/1129397?logo=curseforge&label=CurseForge)](https://www.curseforge.com/minecraft/mc-mods/smsn)
![GitHub License](https://img.shields.io/github/license/gizmo-ds/smsn-mod?style=flat&label=License)

Block common mods from connecting to the internet without affecting the mods' functionality, preventing slow loading or
game crashes caused by network issues.

## Why This Mod Exists?

Because in certain regions with strict network censorship, accessing specific websites is impossible. For example, in
China, websites like Patreon and GitHub are blocked and cannot be accessed normally. However, some popular mods, such as
Quark, have included code that requests these websites, and when these requests fail, it can cause the game to stutter
or crash. These issues typically occur during server startup or when players join. This mod was created to solve this
problem by blocking such network requests, improving game stability for players in affected regions.

- [关于夸克模组一直尝试连接至Patreon的问题](https://www.bilibili.com/video/BV14E421u7Kt/)
- [解决Quark模组无法加载赞助者信息无限报错的问题](https://www.bilibili.com/read/cv13814407/)
- [Quark#3160](https://github.com/VazkiiMods/Quark/issues/3160)
- [Quark#3331](https://github.com/VazkiiMods/Quark/issues/3331)
- [Quark#3416](https://github.com/VazkiiMods/Quark/issues/3416)

A few months ago, I noticed that my Minecraft server was taking nearly 10 minutes to start each time. After
investigating, I discovered that Quark was causing this issue. Upon searching the issue tracker, I found that the author
of Quark refused to address this problem, which led me to develop this mod to resolve it.

## Modrinth?

Modrinth doesn't like this mod ¯\\\_(ツ)\_/¯

I don't think this mod violates the [Modrinth's Content Rules](https://modrinth.com/legal/rules).

![screenshot-modrinth.png](./assets/screenshot-modrinth.png)

## Features

For Forge/NeoForge: Set `versionCheck = false` in `config/fml.toml` to disable the loader's update checker.  
For Fabric with ModMenu: Set `"update_checker": false` in `config/modmenu.json` to disable ModMenu's update checks.

**Forge**

- [Quark](https://www.curseforge.com/minecraft/mc-mods/quark "4.0-462") `夸克` Disables contributor list fetching on
  startup and player join. fewer celebrations;
- [Zeta](https://www.curseforge.com/minecraft/mc-mods/zeta "1.0-31") Block spam logs;
- [Nitrogen](https://github.com/The-Aether-Team/Nitrogen "1.20.1-1.0.11-neoforge") `氮` Disables supporters content for
  The Aether Team mods (e.g., disables MOA skins in The Aether);
- [Citadel](https://www.curseforge.com/minecraft/mc-mods/citadel "2.5.4") Use local assets instead of remote assets –
  prevents April Fools' crashes;
- [Alex's Caves](https://www.curseforge.com/minecraft/mc-mods/alexs-caves "1.1.4") Use local assets instead of remote
  assets;
- [Ad Astra](https://www.curseforge.com/minecraft/mc-mods/ad-astra "1.15.18") Blocks stations requests;
- [Bagus Lib](https://www.curseforge.com/minecraft/mc-mods/bagus-lib "1.20.1-5.3.0") Blocks supporters info requests;
- [Blueprint](https://www.curseforge.com/minecraft/mc-mods/blueprint "1.20.1-7.1.0") Respects
  `slabfishSettings.enabled=false` to block unnecessary requests;
- [Botania](https://www.curseforge.com/minecraft/mc-mods/botania "1.20.1-446-FORGE") `植物魔法` Disables contributor
  list fetches;
- [Enigmatic Legacy](https://www.curseforge.com/minecraft/mc-mods/enigmatic-legacy "2.29.0") `神秘遗物` Blocks update
  checks and "DevotedBelievers" fetches;
- [Exposure](https://www.curseforge.com/minecraft/mc-mods/exposure "1.7.15") Blocks golden camera skin requests;
- [M.R.U](https://www.curseforge.com/minecraft/mc-mods/mru "1.0.0+1.20.1-forge") Blocks API requests;
- [Immersive Engineering](https://www.curseforge.com/minecraft/mc-mods/immersive-engineering "2.29.0") Blocks special
  revolver skin requests;
- [Immersive Caves](https://www.curseforge.com/minecraft/mc-mods/immersive-caves "1.4.1") Blocks special revolver skin
  requests;
- [Inventory Profiles Next](https://www.curseforge.com/minecraft/mc-mods/inventory-profiles-next "forge-1.20.1-1.10.11")
  `一键背包整理Next` Disables update checks and telemetry requests;
- [Obscure API](https://www.curseforge.com/minecraft/mc-mods/obscure-api "15") Blocks mod list requests;
- [Petrolpark Library](https://www.curseforge.com/minecraft/mc-mods/petrolpark-library "1.20.1-1.0.6") Blocks badge
  fetch requests;
- [Placebo](https://www.curseforge.com/minecraft/mc-mods/placebo "1.20.1-8.6.0") Blocks patron trail requests and patron
  wing requests;
- [Ribbits](https://www.curseforge.com/minecraft/mc-mods/ribbits "3.0.2") Blocks patron hat requests and No more Pride Month;
- [Supplementaries](https://www.curseforge.com/minecraft/mc-mods/supplementaries "1.20-3.1.10") `锦致装饰` Prevents
  supporters info requests;
- [Titanium](https://www.curseforge.com/minecraft/mc-mods/titanium "1.20.1-3.8.23") `钛` Blocks reward requests;
- [Xaero's Minimap](https://www.curseforge.com/minecraft/mc-mods/xaeros-minimap "24.2.0_Forge_1.20") `Xaero的小地图`
  Disables update checks & patreon checks fetches;
- [Xaero's World Map](https://www.curseforge.com/minecraft/mc-mods/xaeros-world-map "1.38.8_Forge_1.20") `Xaero的世界地图`
  Disables update checks & patreon checks fetches;
- [ProjectE](https://www.curseforge.com/minecraft/mc-mods/projecte "1.0.1") `等价交换重制版` Blocks UUID check request;
- [Hexerei](https://www.curseforge.com/minecraft/mc-mods/hexerei "0.4.2.1") `魔法巫师` Blocks supporter requests;
- [Voidscape](https://www.curseforge.com/minecraft/mc-mods/voidscape "1.5.348") `虚空工艺` Blocks donator requests;
- [Additional Structures](https://modrinth.com/mod/additional-structures "4.1.2") `失落废墟` Blocks supporter requests;
- [TenshiLib](https://www.curseforge.com/minecraft/mc-mods/tenshilib "1.7.2") Blocks patreon checks requests;
- [Create Tram Additions](https://github.com/neebooo/Create-Tram-Additions "1.6") Blocks TTS requests;
- [Ars Nouveau](https://www.curseforge.com/minecraft/mc-mods/ars-nouveau "4.9.0") `新生魔艺` Blocks reward requests;
- [Immersive Portals](https://www.curseforge.com/minecraft/mc-mods/immersive-portals-for-forge "3.0.7") `沉浸式传送门`
  Blocks mod info requests;
- [Majrusz Library](https://www.curseforge.com/minecraft/mc-mods/majrusz-library "7.0.8") Blocks version check;
- [Kiwi Library](https://modrinth.com/mod/kiwi "11.8.29") Blocks reward requests;
- [Create: Steam 'n' Rails](https://www.curseforge.com/minecraft/mc-mods/create-steam-n-rails "1.5.1") `机械动力：汽鸣铁道` Blocks DevCaps requests;
- [All The Compatibility](https://modrinth.com/mod/all-the-compatibility "2.3.1") Blocks supporter requests;
- [UnionLib](https://www.curseforge.com/minecraft/mc-mods/unionlib "1.20.1-12.0.18-Forge") Disables cosmetics feature
  and blocks supporter requests;
- [Illager Revolution](https://www.curseforge.com/minecraft/mc-mods/illager-revolution "1.2") Blocks patreon checks requests.

**Fabric**

- [Nitrogen](https://github.com/The-Aether-Team/Nitrogen "1.20.1-1.0.15-fabric") `氮` Disables supporters content for
  The Aether Team mods (e.g., disables MOA skins in The Aether);
- [Ad Astra](https://www.curseforge.com/minecraft/mc-mods/ad-astra "1.15.18") Blocks stations requests;
- [Botania](https://www.curseforge.com/minecraft/mc-mods/botania "1.20.1-446-FABRIC") `植物魔法` Disables contributor
  list fetches;
- [Exposure](https://www.curseforge.com/minecraft/mc-mods/exposure "1.7.15") Blocks golden camera skin requests;
- [M.R.U](https://www.curseforge.com/minecraft/mc-mods/mru "1.0.0+1.20.1-fabric") Blocks API requests;
- [Inventory Profiles Next](https://www.curseforge.com/minecraft/mc-mods/inventory-profiles-next "fabric-1.20.1-1.10.11")
  `一键背包整理Next` Disables update checks and telemetry requests;
- [Iris Shaders](https://www.curseforge.com/minecraft/mc-mods/irisshaders "1.6.4+1.20") Blocks update requests;
- [Ribbits](https://www.curseforge.com/minecraft/mc-mods/ribbits "3.0.2") Blocks patron hat requests and No more Pride Month;
- [Supplementaries](https://www.curseforge.com/minecraft/mc-mods/supplementaries "1.20-3.1.10") `锦致装饰` Prevents
  supporters info requests;
- [Xaero's Minimap](https://www.curseforge.com/minecraft/mc-mods/xaeros-minimap "24.2.0_Fabric_1.20") `Xaero的小地图`
  Disables update checks & patreon checks fetches;
- [Xaero's World Map](https://www.curseforge.com/minecraft/mc-mods/xaeros-world-map "1.38.8_Fabric_1.20") `Xaero的世界地图`
  Disables update checks & patreon checks fetches;
- [TenshiLib](https://www.curseforge.com/minecraft/mc-mods/tenshilib-fabric "1.7.6") Blocks patreon checks requests;
- [Create Tram Additions](https://github.com/neebooo/Create-Tram-Additions "1.6") Blocks TTS requests;
- [Immersive Portals](https://www.curseforge.com/minecraft/mc-mods/immersive-portals-mod "3.0.7") `沉浸式传送门` Blocks
  mod info requests;
- [Majrusz Library](https://www.curseforge.com/minecraft/mc-mods/majrusz-library "7.0.8") Blocks version check;
- [Kiwi Library](https://modrinth.com/mod/kiwi "11.8.29") Blocks reward requests;
- [Create: Steam 'n' Rails](https://www.curseforge.com/minecraft/mc-mods/create-steam-n-rails "1.5.1") `机械动力：汽鸣铁道` Blocks DevCaps requests;
- [UnionLib](https://www.curseforge.com/minecraft/mc-mods/unionlib "1.20.1-12.0.18-Fabric") Disables cosmetics feature
  and blocks supporter requests.

## F.A.Q

1. Will XX Minecraft version be supported?  
   The author can only guarantee maintenance for current mainstream versions and versions the author actively plays.
2. Can I include `Save My Shaky Network` in a modpack?  
   ✅ Yes! It's recommended to
   use [Modrinth .mrpack](https://support.modrinth.com/en/articles/8802351-modrinth-modpack-format-mrpack) or CurseForge
   modpack ZIP format.  
   ❌ Do not directly include the mod binary (`.jar`) in your modpack—prioritize user security.
3. Add support for [I18nUpdateMod](https://github.com/CFPAOrg/I18nUpdateMod3)?  
   Never, I can't get a mod that needs a network to work after losing it. Maybe God can. 😇

## License

This mod is distributed under [MIT license](https://github.com/gizmo-ds/smsn-mod/blob/1.20.1/LICENSE)
