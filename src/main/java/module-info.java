module com.edsondantass.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.media;


    opens com.edsondantass.musicplayer to javafx.fxml;
    exports com.edsondantass.musicplayer;
}