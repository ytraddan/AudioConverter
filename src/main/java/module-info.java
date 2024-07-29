module com.convert.audioconverter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.convert.audioconverter to javafx.fxml;
    exports com.convert.audioconverter;
}