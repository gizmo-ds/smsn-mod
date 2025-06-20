# Save My Shaky Network

[![爱发电](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fafdian.com%2Fapi%2Fuser%2Fget-profile%3Fuser_id%3D75e549844b5111ed8df552540025c377&query=%24.data.user.name&label=%E7%88%B1%E5%8F%91%E7%94%B5&color=%23946ce6)](https://afdian.com/a/gizmo)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/oXzIQwRj?logo=modrinth&label=Modrinth)](https://modrinth.com/mod/oXzIQwRj)
[![CurseForge Downloads](https://img.shields.io/curseforge/dt/1129397?logo=curseforge&label=CurseForge)](https://www.curseforge.com/minecraft/mc-mods/smsn)
![GitHub License](https://img.shields.io/github/license/gizmo-ds/smsn-mod?style=flat&label=License)

在不影响 mods 功能的前提下阻止常用 mod 的联网行为, 防止因网络原因导致的加载缓慢或游戏崩溃.

## 你能得到什么

- 你的服务器启动不再会花费数十秒尝试获取[Quark](https://modrinth.com/mod/qnQsVE2z)的赞助者列表, 每次玩家加入都不再会重新获取赞助者列表.
- [Citadel](https://modrinth.com/mod/jJfV67b1)不再会让你的游戏在愚人节崩溃.
- [Destroy](https://modrinth.com/mod/destroy) 或 [Petrol's Parts](https://modrinth.com/mod/petrols-parts)
  不会再在每次玩家加入时请求一次远程数据库.
- 更多可参考下面的列表

## 功能

如果你使用 Forge 或 NeoForge，请尝试修改 `config/fml.toml` 的 `versionCheck` 为 `false`，这能禁用掉加载器自带的版本更新检查。

```yaml
#file: noinspection SpellCheckingInspection
Neoforge:
  - Supplementaries: 禁止获取赞助者信息的请求
  - Petrolpark's Library: 禁止了获取 Badges 的请求
  - Nitrogen: 禁用了 The Aether Team 的模组（如：The Aether）的赞助者内容，这会让 The Aether 中的恐鸟的赞助者换肤功能失效，默认不启用
  - Inventory Profiles Next: 禁止了更新检测和追踪请求
  - Blueprint: 现在会尊重用户的 "slabfishSettings.enabled" 设置，在为 false 的情况下不会再发送请求
  - Bagus Lib: 禁止获取赞助者信息的请求
  - Immersive Engineering: 禁止获取特殊左轮皮肤的请求
  - Iris Shader: 禁止更新请求
  - Actually Additions: 禁止 Special People Stuff
  - Immersive Caves: 禁用 Discord 消息
  - Placebo: 禁止赞助者轨迹请求和赞助者翅膀请求
  - Exposure: 禁止黄金相机皮肤请求
  - Titanium: 禁止奖励请求
  - Ribbits: 禁止赞助者帽子请求

Fabric:
  - Supplementaries: 禁止获取赞助者信息的请求
  - Inventory Profiles Next: 禁止了更新检测和追踪请求
  - Iris Shader: 禁止更新请求
  - Exposure: 禁止黄金相机皮肤请求
  - Nitrogen: 禁用了 The Aether Team 的模组（如：The Aether）的赞助者内容，这会让 The Aether 中的恐鸟的赞助者换肤功能失效，默认不启用
  - Ribbits: 禁止赞助者帽子请求
```

## 为什么?

- [关于夸克模组一直尝试连接至Patreon的问题](https://www.bilibili.com/video/BV14E421u7Kt/)
- [解决Quark模组无法加载赞助者信息无限报错的问题](https://www.bilibili.com/read/cv13814407/)
- [Quark#3160](https://github.com/VazkiiMods/Quark/issues/3160)
- [Quark#3331](https://github.com/VazkiiMods/Quark/issues/3331)
- [Quark#3416](https://github.com/VazkiiMods/Quark/issues/3416)

坚持一款mod的开发并不容易, 在mod中对贡献者和赞助者表达感谢是常见的方法. 但我并不认为从远程服务器获取这些信息是个好主意,
因为并不是地球上所有的地方都拥有良好的网络环境.

在那些网络不好的地区使用这些mod往往会导致游戏加载时间过长, 有的mod甚至会导致游戏无法启动.

如果mod不会导致以上的问题, 又或者mod开发者愿意增加一个禁止这些行为的选项, 这个mod也许就不会诞生.

## 常见问题（Q&A）

1. 会支持 XX Minecraft 版本吗？  
   作者的精力只能确保当前主流版本和作者游玩的版本的维护。
2. 可以将 `救救我的答辩网络` 加入整合包吗？  
   可以，✅ 我推荐整合包使用 [Modrinth
   `.mrpack`](https://support.modrinth.com/en/articles/8802351-modrinth-modpack-format-mrpack) 或 CurseForge 整合包 ZIP
   格式。❌ 请不要直接将模组二进制文件(`.jar`)直接包含在整合包中，请为用户的安全负责。

## 赞助 ❤️

喜欢 `救救我的答辩网络` 吗？你可以在 [爱发电](https://afdian.com/a/gizmo) 对我进行赞助，助力模组持续更新！

[![金主爸爸](https://afdian-connect.deno.dev/sponsor.svg)](https://afdian.com/a/gizmo)