package me.znoah.tinyreports.loader;

import me.znoah.tinyreports.config.Config;

import java.util.HashMap;
import java.util.Map;

public class ConfigLoader implements Loader {
    protected final Map<String, Config> configMap = new HashMap<>();
    private boolean failed = false;

    public ConfigLoader(Config... configs){
        for (Config config : configs){
            configMap.put(config.getInsidePath(), config);
        }
    }

    @Override
    public void load(){
        try {
            for (Config config : configMap.values()){
                config.load();
            }
            this.failed = false;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.failed = true;
        }
    }

    @Override
    public void reload(){
        try {
            reloadAll();
            this.failed = false;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.failed = true;
        }
    }

    @Override
    public void unload() {
        configMap.clear();
    }

    private void reloadAll() throws Exception {
        for (Config config : configMap.values()){
            config.reload();
        }
    }

    public Config getConfig(String filename){
        return configMap.get(filename);
    }

    public boolean isFailed() {
        return failed;
    }
}
