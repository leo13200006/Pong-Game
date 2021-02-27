package helper;

public class Time {
    public double timeStarted;

    public Time() {
        this.timeStarted = System.nanoTime();
    }

    public double getTime() {
        return ((System.nanoTime() - this.timeStarted) * 1E-9);
    }
}
