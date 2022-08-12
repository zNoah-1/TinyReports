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

package me.znoah.tinyreports.report;

import me.znoah.tinyreports.config.Config;
import me.znoah.tinyreports.report.alert.Alert;
import me.znoah.tinyreports.report.alert.discord.DiscordAlert;
import me.znoah.tinyreports.report.alert.ingame.InGameAlert;
import me.znoah.tinyreports.user.User;
import me.znoah.tinyreports.user.staff.StaffRegistry;

public class ReportRegister {
    private final StaffRegistry staffRegistry;
    private final Config msgConfig;
    private final Config dcConfig;

    public ReportRegister(StaffRegistry staffRegistry, Config msgConfig, Config dcConfig){
        this.staffRegistry = staffRegistry;
        this.msgConfig = msgConfig;
        this.dcConfig = dcConfig;
    }

    //Note to self (zNoah-1): Consider the use of events, instead of this little alert mess
    public void add(String reportedName, String reason, User reporter) {
        alertOnlineStaff(reportedName, reason, reporter);
        alertDiscord(reportedName, reason, reporter);
    }

    private void alertOnlineStaff(String reportedName, String reason, User reporter) {
        InGameAlert alert = new InGameAlert(staffRegistry, msgConfig.getStringList("staff.notification.reported"));
        addPlaceholders(alert, reportedName, reason, reporter);
        alert.run();
    }

    private void alertDiscord(String reportedName, String reason, User reporter){
        if ((Boolean) dcConfig.get("webhook.enabled")){
            DiscordAlert alert = new DiscordAlert(dcConfig, "webhook");
            addPlaceholders(alert, reportedName, reason, reporter);
            alert.run();
        }
    }

    private void addPlaceholders(Alert alert, String reportedName, String reason, User reporter){
        alert.addPlaceholder("{reporter}", reporter.getName());
        alert.addPlaceholder("{reported}", reportedName);
        alert.addPlaceholder("{reason}", reason);
    }
}
