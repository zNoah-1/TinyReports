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

package me.znoah.tinyreports.report.alert.discord;

import me.znoah.tinyreports.config.Config;
import me.znoah.tinyreports.report.alert.Alert;
import me.znoah.tinyreports.report.alert.discord.webhook.WebhookRunnable;
import me.znoah.tinyreports.report.alert.discord.webhook.WebhookThread;
import me.znoah.tinyreports.util.http.discord.DiscordEmbedBuilder;

import java.util.Map;

public class DiscordAlert extends Alert {
    public static final WebhookThread WEBHOOK_THREAD = new WebhookThread();
    private final Config config;
    private final String rootPath;

    public DiscordAlert(Config config, String rootPath) {
        this.config = config;
        this.rootPath = rootPath;
    }

    @Override
    public void run() {
        DiscordEmbedBuilder embedBuilder = new DiscordEmbedBuilder();
        embedBuilder.setTitle(replacePlaceholders(config.get(rootPath + ".message.embed.title").toString()));
        embedBuilder.setDescription(replacePlaceholders(config.get(rootPath + ".message.embed.description").toString()));
        embedBuilder.setColor(config.get(rootPath + ".message.embed.color").toString());

        for (String key : config.getKeyList(rootPath + ".message.embed.fields")){
            embedBuilder.addField(
                    replacePlaceholders(config.get(rootPath + ".message.embed.fields." + key + ".title").toString()),
                    replacePlaceholders(config.get(rootPath + ".message.embed.fields." + key + ".description").toString()),
                    (Boolean) config.get(rootPath + ".message.embed.fields." + key + ".inline"));
        }

        Runnable runnable = new WebhookRunnable(config.get(rootPath + ".url").toString(), embedBuilder.getText());
        WEBHOOK_THREAD.add(runnable);
    }

    private String replacePlaceholders(String text){
        for (Map.Entry<String, String> entry : placeholdersToReplace.entrySet()){
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }
}
