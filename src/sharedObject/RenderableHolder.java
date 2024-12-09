package sharedObject;

import javafx.scene.canvas.Canvas;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {

    private static final RenderableHolder instance = new RenderableHolder();

    private List<IRenderable> entities;
    private Comparator<IRenderable> comparator;

    public static Image mainmenuBackground,croppedMenuBackground,playButton,playButtonHover,mapButton1,mapButton2,mapButton3,pauseMenu,mapselectionMenu,mapBackButton;

    public static Image[] state;


    public static AudioClip pickItemSound;
    public static MediaPlayer gameBackgroundMusic,menuBackgroundMusic;
    public static AudioClip walkSound,chopSound,grillSound,doneCookingSound,sold,clockSound,endSound;
    static {
        
        //loadAnimation();
        loadMenuResource();
        loadPauseResource();
        loadMapselectionResource();
           
        //loadSound();
        //loadEndResource();
    }

    public RenderableHolder() {
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getZ() > o2.getZ()) {
                return 1;
            }
            return -1;
        };
    }

    public static RenderableHolder getInstance() {
        return instance;
    }


    public static void loadMenuResource(){
        playButton = new Image(ClassLoader.getSystemResource("playButton.png").toString());
        playButtonHover = new Image(ClassLoader.getSystemResource("playButtonHover.png").toString());
        mainmenuBackground = new Image(ClassLoader.getSystemResource("mainmenuBackground.png").toString());
        croppedMenuBackground = new WritableImage(mainmenuBackground.getPixelReader(),0,0,360,400);
    }
    public static void loadMapResource(){
        mapButton1 = new Image(ClassLoader.getSystemResource("mapButton1.png").toString());
        mapButton2 = new Image(ClassLoader.getSystemResource("mapButton2.png").toString());
        mapButton3 = new Image(ClassLoader.getSystemResource("mapButton3.png").toString());
    }
    public static void loadPauseResource(){
        pauseMenu = new Image(ClassLoader.getSystemResource("game-background.jpg").toString());
        
    }
    public static void loadMapselectionResource(){
    	mapselectionMenu = new Image(ClassLoader.getSystemResource("mapselectedMenu.png").toString());
        mapBackButton = new Image(ClassLoader.getSystemResource("backButton.png").toString());
    }

//    public static void loadAnimation(){
//        slimeWalkAnimation = new WritableImage[8];
//        for (int i = 0 ; i < slimeWalkAnimation.length; i++){
//            slimeWalkAnimation[i] = new WritableImage(slimeWalkSprite.getPixelReader(),35+(i*128),90,53,38);
//        }
//
//        slimeIdleAnimation = new WritableImage[8];
//        for (int i = 0 ; i < slimeIdleAnimation.length; i++){
//            slimeIdleAnimation[i] = new WritableImage(slimeIdleSprite.getPixelReader(),35+(i*128),90,53,38);
//        }
//    }

//    public static void loadSound(){
//        pickItemSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/pickItemSound.mp3").toString());
//        Media sound = new Media(ClassLoader.getSystemResource("Sound_Resource/gameBackgroundMusic.wav").toString());
//        gameBackgroundMusic = new MediaPlayer(sound);
//        sound = new Media(ClassLoader.getSystemResource("Sound_Resource/menuBackgroundMusic.wav").toString());
//        menuBackgroundMusic = new MediaPlayer(sound);
//        gameBackgroundMusic.setVolume(0.2);
//        gameBackgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
//        menuBackgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
//        menuBackgroundMusic.setVolume(0.2);
//        walkSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/walk.wav").toString());
//        chopSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/chop.wav").toString());
//        grillSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/grill.wav").toString());
//        doneCookingSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/doneCooking.wav").toString());
//        sold = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/sold.wav").toString());
//        clockSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/clockRunOut.wav").toString());
//        endSound = new AudioClip(ClassLoader.getSystemResource("Sound_Resource/endSound.wav").toString());
//    }
    public void add(IRenderable entity) {
        entities.add(entity);
        Collections.sort(entities, comparator);
    }

    public void update() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }

    public List<IRenderable> getEntities() {
        return entities;
    }
}
