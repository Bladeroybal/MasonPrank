import java.io.*;
import java.util.Random;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import sun.audio.*;


/**
 * A devious system to screw with Mason. Sorry Mason
 */
public class JavaAudioPlaySoundExample {
    public static void main(String[] args)
            throws Exception {

        //Initiate
        long mouseReverse = 0;

        //Master Time Control
        Timer master = new Timer();
        master.startTime();

        //Timer to control how often voice happens
        Timer voiceClock = new Timer();
        voiceClock.startTime();

        //Timer to control how often mouse control happens
        Timer mouseClock = new Timer();
        mouseClock.startTime();

        //Timer to control when mouse button switches L/R
        Timer clickClock = new Timer();
        clickClock.startTime();

        //random time generator for Voice
        Random randTime = new Random();
        int randomCount = randTime.nextInt(25)+15;
        System.out.println("Next sound in "+randomCount+" minutes");

        //random time generator for Mouse
        int mouseMove = randTime.nextInt(25)+15;
        System.out.println("Next mouse move in "+mouseMove+" minutes");

        //random time generator for MouseClick Reverse
        int clickReverse = randTime.nextInt(15)+15;

        while (true) {
            //VOICES
            if (voiceClock.returnSeconds() == randomCount) {
                //Select the file to open as a sound
                SoundSelection soundSelection = new SoundSelection();

                // open the sound file as a Java input stream
                String gongFile = "c:/Program Files/Rainmeter/Defaults/Debug/"+soundSelection.choose()+".wav";
                InputStream in = new FileInputStream(gongFile);

                // create an audiostream from the inputstream
                AudioStream audioStream = new AudioStream(in);

                // play the audio clip with the audioplayer class
                AudioPlayer.player.start(audioStream);

                //reset clock
                voiceClock.startTime();
                randomCount = randTime.nextInt(25)+15;
                System.out.println("Next sound in "+randomCount+" minutes");
            }
            //MOUSE LOCATION
            if (mouseClock.returnSeconds() == mouseMove && master.returnSeconds() > 15){
                Robot robot = new Robot();

                //inverse MOUSE X Y POSITION
                robot.mouseMove(0, 0);
                TimeUnit.SECONDS.sleep(4);
                robot.mouseMove(0, 0);
                TimeUnit.SECONDS.sleep(10);
                robot.mouseMove(0, 0);
                mouseClock.startTime();



                //reset mouse clock
                mouseClock.startTime();
                mouseMove = randTime.nextInt(25)+15;
                System.out.println("Next mouse move in "+mouseMove+" minutes");
            }
            //MOUSE CLICK
            if (clickClock.returnSeconds() == clickReverse && master.returnSeconds() > 40){
                //Let me know
                System.out.println("Click reversed");
                //startup Windows SystemParameters
                Windows windows = new Windows();
                //Turn right click to left click
                windows.mouseLR(1);

                //let him be freaked out for a bit
                TimeUnit.SECONDS.sleep(8);
                //Flip Back to normal
                windows.mouseLR(0);
                System.out.println("Click Normal");

                //reset count
                clickClock.startTime();
                clickReverse = randTime.nextInt(15)+15;
            }
        }
    }
}