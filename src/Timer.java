/**
 * Created by Blade on 4/6/2016.
 */
public class Timer {
    private long tStart;
    private long tEnd;
    private long tDelta;
    private double elapsedSeconds;
    private double elapsedMinutes;


    public void startTime(){
        tStart = System.currentTimeMillis();
    }

    public double returnSeconds() {
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000;
        return elapsedSeconds;
    }

    public double returnMinutes() {
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedMinutes = tDelta/60000;
        return elapsedMinutes;
    }






}