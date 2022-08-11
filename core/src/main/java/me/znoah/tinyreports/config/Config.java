package me.znoah.tinyreports.config;

import java.util.List;

public abstract class Config {
    protected final String PLUGIN_FOLDER = "plugins/TinyReports/";

    public abstract void load() throws Exception;
    public abstract void reload() throws Exception;
    public abstract Object get(String path);
    public abstract List<String> getStringList(String path);
    public abstract String getInsidePath();
    public abstract String getFileName();
}
