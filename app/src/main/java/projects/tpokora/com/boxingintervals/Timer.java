package projects.tpokora.com.boxingintervals;

import java.util.Formatter;

/**
 * Created by pokor on 24.09.2016.
 */
public class Timer {

    private int buffor;

    private int intervals;
    private int intervalDuration;

    private int breakDuration;

    public Timer(int buffor, int intervals, int intervalDuration, int breakDuration) {
        this.buffor = buffor;
        this.intervals = intervals;
        this.intervalDuration = intervalDuration;
        this.breakDuration = breakDuration;
    }

    public int getBuffor() {
        return buffor;
    }

    public void setBuffor(int buffor) {
        this.buffor = buffor;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public int getIntervalDuration() {
        return intervalDuration;
    }

    public void setIntervalDuration(int intervalDuration) {
        this.intervalDuration = intervalDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    public String getTimerTime() {
        float minutes = intervalDuration / 60;
        float seconds = 0;
        if (minutes % 60 > 0) {
            seconds = intervalDuration - (minutes * 60);
        }

        String hoursString = "00";
        String minutesString = minutes > 9 ? Float.toString(minutes) : "0" + minutes;
        String secondsString = seconds > 9 ? Float.toString(seconds) : "0" + seconds;;

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%.2s:%.2s:%.2s", hoursString, minutesString, secondsString);

        return sb.toString();
    }

    public String getBreakDurationString() {
        return Integer.toString(this.breakDuration);
    }
}
