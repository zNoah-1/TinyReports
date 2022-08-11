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
