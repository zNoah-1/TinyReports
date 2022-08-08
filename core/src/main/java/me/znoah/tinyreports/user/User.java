package me.znoah.tinyreports.user;

import java.util.UUID;

public interface User {
    String getName();
    UUID getPlayerUUID();
    void sendMessage(String message);
}
