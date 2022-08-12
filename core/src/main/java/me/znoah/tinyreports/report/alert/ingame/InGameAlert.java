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

package me.znoah.tinyreports.report.alert.ingame;

import me.znoah.tinyreports.report.alert.Alert;
import me.znoah.tinyreports.user.User;
import me.znoah.tinyreports.user.staff.StaffRegistry;

import java.util.*;

public class InGameAlert extends Alert {
    private final StaffRegistry staffRegistry;
    private final List<String> messageList;

    public InGameAlert(StaffRegistry staffRegistry, List<String> messageList) {
        this.staffRegistry = staffRegistry;
        this.messageList = messageList;
    }

    @Override
    public void run() {
        replacePlaceholders();
        for (User staff : staffRegistry.getStaffList()){
            for (String line : messageList){
                staff.sendMessage(line);
            }
        }
    }

    private void replacePlaceholders(){
        for (int i = 0; i < messageList.size(); i++){
            for (Map.Entry<String, String> entry : placeholdersToReplace.entrySet()){
                messageList.set(i, messageList.get(i).replace(entry.getKey(), entry.getValue()));
            }
        }
    }
}
