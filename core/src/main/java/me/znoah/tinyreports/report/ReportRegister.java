package me.znoah.tinyreports.report;

import me.znoah.tinyreports.user.User;
import me.znoah.tinyreports.user.staff.StaffRegistry;

public class ReportRegister {
    private final StaffRegistry staffRegistry;

    public ReportRegister(StaffRegistry staffRegistry){
        this.staffRegistry = staffRegistry;
    }

    public void add(String reportedName, String reason, User reporter) {
        for (User staff : staffRegistry.getStaffList()){
            staff.sendMessage("&8");
            staff.sendMessage("&f" + reporter.getName() + "&e has reported &f" + reportedName);
            staff.sendMessage("&eReason: &f" + reason);
            staff.sendMessage("&8");
        }
    }
}
