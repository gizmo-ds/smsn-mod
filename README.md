# Save My Shaky Network (1.18.2)

[![AFDIAN](https://img.shields.io/badge/%E7%88%B1%E5%8F%91%E7%94%B5-Gizmo-%23946ce6)](https://afdian.com/a/gizmo)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/oXzIQwRj?logo=modrinth&label=Modrinth)](https://modrinth.com/mod/oXzIQwRj)
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

## Features

For Forge/NeoForge: Set `versionCheck = false` in `config/fml.toml` to disable the loader's update checker.  
For Fabric with ModMenu: Set `"update_checker": false` in `config/modmenu.json` to disable ModMenu's update checks.

**Forge**

- [Quark](https://www.curseforge.com/minecraft/mc-mods/quark "3.2-358") `夸克` Disables contributor list fetching on
  startup and player join;
- [Citadel](https://www.curseforge.com/minecraft/mc-mods/citadel "1.11.3") Use local assets instead of remote assets –
  prevents April Fools' crashes;
- [Bagus Lib](https://www.curseforge.com/minecraft/mc-mods/bagus-lib "1.18.2-3.6.1") Blocks supporters info requests;
- [Blueprint](https://www.curseforge.com/minecraft/mc-mods/blueprint "1.18.2-5.5.0") Respects
  `slabfishSettings.enabled=false` to block unnecessary requests;
- [Botania](https://www.curseforge.com/minecraft/mc-mods/botania "1.18.2-435") `植物魔法` Disables contributor list
  fetches;
- [Enigmatic Legacy](https://www.curseforge.com/minecraft/mc-mods/enigmatic-legacy "2.25.0") `神秘遗物` Blocks update
  checks and "DevotedBelievers" fetches;
- [Immersive Engineering](https://www.curseforge.com/minecraft/mc-mods/immersive-engineering "1.18.2-8.4.0-161") Blocks
  special revolver skin requests;
- [Inventory Profiles Next](https://www.curseforge.com/minecraft/mc-mods/inventory-profiles-next "forge-1.18.2-1.10.14")
  `一键背包整理Next` Disables update checks and telemetry requests;
- [Titanium](https://www.curseforge.com/minecraft/mc-mods/titanium "3.5.9") `钛` Blocks reward requests;
- [Xaero's Minimap](https://www.curseforge.com/minecraft/mc-mods/xaeros-minimap "25.2.6_Forge_1.18.2") `Xaero的小地图`
  Disables update checks & patreon checks fetches;
- [Xaero's World Map](https://www.curseforge.com/minecraft/mc-mods/xaeros-world-map "1.39.9_Forge_1.18.2") `Xaero的世界地图`
  Disables update checks & patreon checks fetches.

**Fabric**

- [Iris Shaders](https://www.curseforge.com/minecraft/mc-mods/irisshaders "1.6.11+1.18.2") Blocks update requests;
- [Botania](https://www.curseforge.com/minecraft/mc-mods/botania "1.18.2-435-FABRIC") `植物魔法` Disables contributor
  list fetches;
- [Inventory Profiles Next](https://www.curseforge.com/minecraft/mc-mods/inventory-profiles-next "fabric-1.18.2-1.10.15")
  `一键背包整理Next` Disables update checks and telemetry requests;
- [Xaero's Minimap](https://www.curseforge.com/minecraft/mc-mods/xaeros-minimap "25.2.6_Fabric_1.18.2") `Xaero的小地图`
  Disables update checks & patreon checks fetches;
- [Xaero's World Map](https://www.curseforge.com/minecraft/mc-mods/xaeros-world-map "1.39.9_Fabric_1.18.2") `Xaero的世界地图`
  Disables update checks & patreon checks fetches.

## F.A.Q

1. Will XX Minecraft version be supported?  
   The author can only guarantee maintenance for current mainstream versions and versions the author actively plays.
2. Can I include `Save My Shaky Network` in a modpack?  
   ✅ Yes! It's recommended to
   use [Modrinth .mrpack](https://support.modrinth.com/en/articles/8802351-modrinth-modpack-format-mrpack) or CurseForge
   modpack ZIP format.  
   ❌ Do not directly include the mod binary (`.jar`) in your modpack—prioritize user security.

## License

This mod is distributed under [MIT license](https://github.com/gizmo-ds/smsn-mod/blob/1.19.2/LICENSE)
