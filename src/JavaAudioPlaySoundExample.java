import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import sun.audio.*;

import javax.swing.*;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;


/**
 * A devious system to screw with Mason. Sorry Mason
 */
public class JavaAudioPlaySoundExample {
    public static void main(String[] args)
            throws Exception {

        //Initiate
        int mouseX = 0;
        int mouseY = 0;
        Robot robot = new Robot();
        int masterCount=0;

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
        int randomCount = randTime.nextInt(25)+7;
        System.out.println("First Voice in "+randomCount);

        //random time generator for Mouse
        int mouseMove = randTime.nextInt(25)+7;
        System.out.println("First MOVE in "+mouseMove);

        //random time generator for MouseClick Reverse
        int clickReverse = randTime.nextInt(25)+10;
        System.out.println("First CLICK in "+clickReverse);


        while (masterCount < 2) {
            //VOICES
            if (voiceClock.returnMinutes() == randomCount) {
                System.out.println("STARTING VOICES");
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
                randomCount = randTime.nextInt(25)+7;
                System.out.println("Next sound in "+randomCount+" minutes");
            }
            //MOUSE LOCATION
            if (mouseClock.returnMinutes() == mouseMove){
                System.out.println("STARTING MOUSE MOVER");

                //inverse MOUSE X Y POSITION
                robot.mouseMove(0, 0);
                TimeUnit.SECONDS.sleep(4);
                robot.mouseMove(0, 0);
                TimeUnit.SECONDS.sleep(10);
                robot.mouseMove(0, 0);
                mouseClock.startTime();



                //reset mouse clock
                mouseClock.startTime();
                mouseMove = randTime.nextInt(25)+7;
                System.out.println("Next mouse move in "+mouseMove+" minutes");
            }
            //MOUSE CLICK
            //Not working in JAR!!! Leaving off until fixed
//            if (clickClock.returnSeconds()== clickReverse){
//                //Let me know
//                System.out.println("Click reversed");
//                //startup Windows SystemParameters
//                Windows windows = new Windows();
//                //Turn right click to left click
//                windows.mouseLR(1);
//
//                //let him be freaked out for a bit
//                TimeUnit.SECONDS.sleep(8);
//                //Flip Back to normal
//                windows.mouseLR(0);
//                System.out.println("Click Normal");
//
//                //reset count
//                clickClock.startTime();
//                clickReverse = randTime.nextInt(25)+10;
//                System.out.println("Next mouse click in "+clickReverse+" minutes");
//            }
            //FINAL ACT
            if (master.returnMinutes() >= 120){
                System.out.println("FINAL STAGE");
                mouseClock.startTime();
                if (mouseClock.returnSeconds() < 1 ){

                    //JOOHHHHNNN CEENNNNAAAA
                    String gongFile = "c:/Program Files/Rainmeter/Defaults/Debug/cena.wav";
                    InputStream in = new FileInputStream(gongFile);

                    // create an audiostream from the inputstream
                    AudioStream audioStream = new AudioStream(in);

                    // play the audio clip with the audioplayer class
                    AudioPlayer.player.start(audioStream);

                    for (int i=0; i<26; i++){
                        robot.mouseMove(mouseX, mouseY);
                        mouseX = 1 + mouseX*2;
                        mouseY = 1 + mouseY*mouseY;
                        TimeUnit.MILLISECONDS.sleep(500);

                    }
                    masterCount++;

                }
            }
        }
        JFrame jf = new JFrame();
        jf.setSize(1920,1080);
        //TEXT
        JLabel label1 = new JLabel("MASON! :D", SwingConstants.CENTER);
        label1.setFont(new Font("Serif", Font.BOLD, 54));
        jf.add(label1);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setVisible(true);
        TimeUnit.SECONDS.sleep(10);
        jf.setVisible(false);
        dispose();
        System.exit(0);

    }
}