# Save My Shaky Network

在不影响 mods 功能的前提下阻止常用 mod 的联网行为, 防止因网络原因导致的加载缓慢或游戏崩溃.

## 你能得到什么

- 你的服务器启动不再会花费数十秒尝试获取[Quark](https://modrinth.com/mod/qnQsVE2z)的赞助者列表, 每次玩家加入都不再会重新获取赞助者列表.
- [Citadel](https://modrinth.com/mod/jJfV67b1)不再会让你的游戏在愚人节崩溃.
- [Destroy](https://modrinth.com/mod/destroy) 或 [Petrol's Parts](https://modrinth.com/mod/petrols-parts)
  不会再在每次玩家加入时请求一次远程数据库.

## 功能

- [Alex's Caves](https://modrinth.com/mod/qnQsVE2z): 禁止了获取最新冲突mod列表的请求,
  现在会使用`assets/alexscaves/warning/mod_generation_conflicts.txt`作为冲突mod列表.
- [Citadel](https://modrinth.com/mod/jJfV67b1): 禁止了获取赞助者列表, 现在会使用`assets/citadel/patreon.txt`.
  愚人节彩蛋永远不会被触发.
- [Quark](https://modrinth.com/mod/qnQsVE2z): 禁止了获取贡献者和赞助者列表.
- [Xaero's Minimap](https://modrinth.com/mod/1bokaNcj): 禁止了更新检测和获取赞助者列表.
- [Xaero's World Map](https://modrinth.com/mod/NcUtCpym): 禁止了更新检测和获取赞助者列表.
- [Petrolpark's Library](https://modrinth.com/mod/petrolpark): 禁止了获取 Badges 的请求. EarlyBird? 这真的不是什么违反
  Mojang 的 EULA 的东西吗?
- [KubeJS](https://modrinth.com/mod/kubejs): 禁止了更新检测, 为什么不允许用户禁止这个行为?
- [Inventory Profiles Next](https://modrinth.com/mod/O7RBXm3n): 禁止了更新检测和追踪请求, 我不明白这个mod中的隐私设定有何意义.
- [Obscure API](https://modrinth.com/mod/fU7jbFHc): 禁止了获取作品展示信息的请求, 我不认为这些信息对于用户来说有什么意义.

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
