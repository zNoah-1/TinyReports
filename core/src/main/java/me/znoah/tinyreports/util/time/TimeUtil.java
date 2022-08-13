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

package me.znoah.tinyreports.util.time;

public class TimeUtil {
    public static String timeString(long millis){
        StringBuilder text = new StringBuilder();
        int hours = getSeconds(millis) / 3600;
        int minutes = (getSeconds(millis) / 60) - (60 * hours);
        int seconds = getSeconds(millis) - (3600 * hours) - (60 * minutes);

        if (hours > 0){
            text.append(hours).append("h");
        }
        if (minutes > 0){
            if (!text.toString().equals("")) text.append(" ");
            text.append(minutes).append("m");
        }
        if (seconds > 0){
            if (!text.toString().equals("")) text.append(" ");
            text.append(seconds).append("s");
        }

        return text.toString();
    }

    public static String timeStringPlaceholders(long millis){
        StringBuilder text = new StringBuilder();
        int hours = getSeconds(millis) / 3600;
        int minutes = (getSeconds(millis) / 60) - (60 * hours);
        int seconds = getSeconds(millis) - (3600 * hours) - (60 * minutes);

        if (hours > 0){
            if (hours == 1) text.append(hours).append("{hr}");
            else text.append(hours).append("{hrs}");
        }
        if (minutes > 0){
            if (!text.toString().equals("")) text.append(" ");
            if (minutes == 1) text.append(minutes).append("{min}");
            else text.append(minutes).append("{mins}");
        }
        if (seconds > 0){
            if (!text.toString().equals("")) text.append(" ");
            if (seconds == 1) text.append(seconds).append("{sec}");
            else text.append(seconds).append("{secs}");
        }

        return text.toString();
    }

    private static int getSeconds(long millis){
        return (int) Math.ceil(millis / 1000.0);
    }
}
