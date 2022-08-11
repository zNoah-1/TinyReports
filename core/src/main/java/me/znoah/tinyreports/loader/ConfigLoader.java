/*
    TinyReports (core) - The core of TinyReports

    Copyright © 2022 zNoah-1

    Permission is hereby granted, free of charge, to any person obtaining a copy of this
    software and associated documentation files (the "Software"), to deal in the Software
    without restriction, including without limitation the rights to use, copy, modify,
    merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
    permit persons to whom the Software is furnished to do so, subject to the following
    conditions:

    The above copyright notice and this permission notice shall be included in all copies
    or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
    OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
    SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

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
