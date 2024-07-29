package com.convert.audioconverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private final FileChooser fileChooser = new FileChooser();
    private String outputFormat = ".mp3";


    @FXML
    private ToggleGroup Format;
    @FXML
    private RadioButton mp3Button, wavButton, m4aButton;
    @SuppressWarnings("exports")
    @FXML
    public TextArea status;


    @SuppressWarnings("exports")
    public void setOutputFormat(ActionEvent event){
        if (mp3Button.isSelected())
            outputFormat = "." + mp3Button.getText().toLowerCase();
        else if (wavButton.isSelected()) {
            outputFormat = "." + wavButton.getText().toLowerCase();
        }
        else
            outputFormat = "." + m4aButton.getText().toLowerCase();
    }

    @FXML
    void GetFile(MouseEvent event) {
        status.clear();
        File file = fileChooser.showOpenDialog(new Stage());
        Convertor convertor = new Convertor(file, outputFormat);
        String info = convertor.Convert();
        status.setText(info);
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String username = System.getProperty("user.name");
        fileChooser.setInitialDirectory(new File("c:\\Users\\" + username + "\\Downloads"));
    }

}
