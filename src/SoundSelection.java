import java.util.Random;

/**
 * Created by Blade on 4/7/2016.
 */
public class SoundSelection {
    int selector;
    String soundName;

    public String choose(){
        Random rand = new Random();
        int randomCount = rand.nextInt(6)+1;

        if (randomCount==1){
            soundName = "startup";
        }
        if (randomCount==2){
            soundName = "reset";
        }
        if (randomCount==3){
            soundName = "goodbye";
        }
        if (randomCount==4){
            soundName = "error";
        }
        if (randomCount==5){
            soundName = "loading";
        }
        if (randomCount==6){
            soundName = "sleep";
        }
//        System.out.println(soundName);
        return soundName;
    }
}
