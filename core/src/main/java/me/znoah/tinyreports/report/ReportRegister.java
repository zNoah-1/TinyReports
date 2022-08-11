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
import me.znoah.tinyreports.user.User;
import me.znoah.tinyreports.user.staff.StaffRegistry;

public class ReportRegister {
    private final StaffRegistry staffRegistry;
    private final Config msgConfig;

    public ReportRegister(StaffRegistry staffRegistry, Config msgConfig){
        this.staffRegistry = staffRegistry;
        this.msgConfig = msgConfig;
    }

    public void add(String reportedName, String reason, User reporter) {
        for (User staff : staffRegistry.getStaffList()){
            for (String line : msgConfig.getStringList("staff.notification.reported")){
                staff.sendMessage(
                        line.replace("{reporter}", reporter.getName())
                                .replace("{reported}", reportedName)
                                .replace("{reason}", reason)
                );
            }
        }
    }
}
