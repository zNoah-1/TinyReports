package me.znoah.tinyreports.user.staff;

import me.znoah.tinyreports.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffRegistry {
    private final List<User> staffList = new ArrayList<>();

    public void add(User user){
        staffList.add(user);
    }

    public void remove(UUID playerUUID){
        for (User staff : staffList){
            if (staff.getPlayerUUID().equals(playerUUID)){
                staffList.remove(staff);
                return;
            }
        }
    }

    public List<User> getStaffList() {
        return staffList;
    }
}
