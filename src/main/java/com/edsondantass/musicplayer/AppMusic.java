package com.edsondantass.musicplayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AppMusic {
    private static AppMusic instance;
    public MediaPlayer mediaPlayer;


    private AppMusic(Media media) {
        try {
            this.mediaPlayer = new MediaPlayer(media);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AppMusic getInstance(Media media) {
        if (instance == null) {
            instance = new AppMusic(media);
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }
}
