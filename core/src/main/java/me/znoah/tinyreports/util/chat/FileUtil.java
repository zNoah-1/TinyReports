package me.znoah.tinyreports.util.chat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtil {
    public void copy(String sourceFromJar, String destination) throws IOException {
        File destinationFile = new File(destination);
        destinationFile.getParentFile().mkdirs();

        InputStream link = getClass().getResourceAsStream("/res/" + sourceFromJar);
        Files.copy(link, destinationFile.getAbsoluteFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
