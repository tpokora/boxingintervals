package projects.tpokora.com.boxingintervals;

import java.util.Formatter;

/**
 * Created by pokor on 24.09.2016.
 */
public class Timer {

    public static final int SECOND = 1000;

    private int buffor;
    private int intervals;
    private int intervalMinute;
    private int intervalSeconds;

    private int breakDuration;

    public Timer(int buffor, int intervals, int intervalMinute, int intervalSeconds, int breakDuration) {
        this.buffor = buffor;
        this.intervals = intervals;
        this.intervalMinute = intervalMinute;
        this.intervalSeconds = intervalSeconds;
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

    public int getIntervalMinute() {
        return intervalMinute;
    }

    public void setIntervalMinute(int intervalMinute) {
        this.intervalMinute = intervalMinute;
    }

    public int getIntervalSeconds() {
        return intervalSeconds;
    }

    public void setIntervalSeconds(int intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    public String getTimerTime() {
        return String.format("%02d", intervalMinute) + ":" + String.format("%02d", intervalSeconds);
    }

    public int getIntervalDuration() {
        return (intervalMinute * 60) + intervalSeconds;
    }

    public String getBreakDurationString() {
        return Integer.toString(this.breakDuration);
    }
}
