#!/usr/bin/env sh
mkdir -p neoforge/mods
wget https://api.modrinth.com/maven/maven/modrinth/supplementaries/8lr6B9o3/supplementaries-8lr6B9o3.jar -O neoforge/mods/supplementaries-3.1.1-neoforge.jar
zip -d neoforge/mods/supplementaries-3.1.1-neoforge.jar architectury.common.json
