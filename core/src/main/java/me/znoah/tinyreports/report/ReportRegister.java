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
