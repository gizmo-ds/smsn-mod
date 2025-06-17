New-Item -Name "neoforge/mods" -ItemType "Directory"
Invoke-WebRequest -Uri "https://api.modrinth.com/maven/maven/modrinth/supplementaries/8lr6B9o3/supplementaries-8lr6B9o3.jar" -OutFile "neoforge/mods/supplementaries-3.1.1-neoforge.jar"
zip -d neoforge/mods/supplementaries-3.1.1-neoforge.jar architectury.common.json
