package com.edsondantass.musicplayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaController implements Initializable {
   private String path;
   private Media media;

   @FXML
   private AnchorPane container;
   @FXML
   private ImageView btnPlay;
   @FXML
   private ImageView btnNext;
   @FXML
   private ImageView btnPrev;
   @FXML
   private ImageView btnPause;
   @FXML
   private Label musicName;
   @FXML
   private ImageView btnFinishDisabled;
   @FXML
   private ImageView btnFinish;
   @FXML
   private ImageView btnMute;
   @FXML
   private ImageView btnUnmute;
   @FXML
   private ImageView btnSearch;
   @FXML
   private Slider progress;
   @FXML
   private ProgressBar songProgressBar;


   @FXML
   private void play(MouseEvent event) {
      if(path != null) {
         AppMusic appMusic = AppMusic.getInstance(media);
         appMusic.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                   progress.setValue(newValue.toSeconds());
                }
             }
         );

         progress.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               appMusic.mediaPlayer.seek(Duration.seconds(progress.getValue()));
            }
         });
         progress.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               appMusic.mediaPlayer.seek(javafx.util.Duration.seconds(progress.getValue()));
            }
         });

         appMusic.mediaPlayer.play();
         btnPlay.setVisible(false);
         btnPause.setVisible(true);
         btnFinishDisabled.setVisible(false);
         btnFinish.setVisible(true);
         btnSearch.setVisible(false);
      }
   }


   @FXML
   protected void pause() {
      if(media != null) {
         AppMusic appMusic = AppMusic.getInstance(media);
         btnPlay.setVisible(true);
         btnPause.setVisible(false);
         btnFinishDisabled.setVisible(true);
         btnFinish.setVisible(false);
         btnSearch.setVisible(true);

         appMusic.mediaPlayer.pause();
      }
   }

   @FXML
   protected void mute() {
      if(media != null) {
         AppMusic appMusic = AppMusic.getInstance(media);
         appMusic.mediaPlayer.setMute(true);
         btnMute.setVisible(false);
         btnUnmute.setVisible(true);
      }
   }

   @FXML
   protected void unmute() {
      if(media != null) {
         AppMusic appMusic = AppMusic.getInstance(media);
         appMusic.mediaPlayer.setMute(false);
         btnMute.setVisible(true);
         btnUnmute.setVisible(false);
      }

   }

   @FXML
   protected void search() {
      FileChooser fileChooser = new FileChooser();
      File file = fileChooser.showOpenDialog(null);
      if(file != null) {
         path = file.toURI().toString();
         if(path != null) {
            media = new Media(path);
            AppMusic.destroy();
            AppMusic.getInstance(media);
            btnPlay.setDisable(false);
            musicName.setText(file.getName());
         }
      }
   }

   @FXML
   protected void finish() {
      AppMusic appMusic = AppMusic.getInstance(media);
      appMusic.mediaPlayer.stop();
      btnFinish.setVisible(false);
      btnFinishDisabled.setVisible(true);
      btnPlay.setVisible(true);
      btnPlay.setDisable(true);
      btnPause.setVisible(false);
      btnSearch.setVisible(true);
      musicName.setText("...");
   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }


}