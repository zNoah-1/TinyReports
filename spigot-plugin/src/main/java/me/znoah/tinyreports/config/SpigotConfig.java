/*
    TinyReports (spigot-plugin) - The spigot implementation of TinyReports (core)
    Copyright (C) 2022 zNoah-1

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package me.znoah.tinyreports.config;

import me.znoah.tinyreports.util.chat.FileUtil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SpigotConfig extends Config {
    private final File configFile;
    private final String insidePath;

    private FileConfiguration config;

    public SpigotConfig(String insidePath){
        this.insidePath = insidePath;
        this.configFile = new File(PLUGIN_FOLDER + insidePath);
    }

    public void load() throws Exception {
        this.config = new YamlConfiguration();

        if (!configFile.exists()) copyFile(getFileName());
        config.load(configFile);
    }

    private void copyFile(String fileLoc) throws IOException {
        FileUtil fileUtil = new FileUtil();
        fileUtil.copy(fileLoc, configFile.getAbsolutePath());
    }

    @Override
    public void reload() throws Exception {
        load();
    }

    @Override
    public Object get(String path) {
        return config.get(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    @Override
    public String getInsidePath() {
        return insidePath;
    }

    @Override
    public String getFileName(){
        return configFile.getName();
    }
}
