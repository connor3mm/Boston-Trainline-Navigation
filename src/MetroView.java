import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MetroView implements Initializable {
    @FXML public ComboBox departureOption;
    @FXML public ComboBox destinationOption;
    @FXML public Button FindRouteButton;
    @FXML public TextArea routeResult;
    @FXML public Label BostonTitle;




//    // model class
//    public void initialize() {
//
//        // add station names to dropdown menu
//        List<String> stationNames = model.getStationNames();
//        java.util.Collections.sort(stationNames);
//        departureOption.getItems().addAll(stationNames);
//        destinationOption.getItems().addAll(stationNames);
//
//        departureOption.setPromptText("e.g Arlington");
//        destinationOption.setPromptText("e.g AllstonStreet");
//
//        departureOption.setOnAction(evt -> { System.out.println(departureOption.getValue());});
//        destinationOption.setOnAction(evt -> { System.out.println(destinationOption.getValue());});
//
//        FindRouteButton.setOnAction(evt -> {
//
//        });
//    }


    public void populateDropDown(List<String> stationNames) {
        java.util.Collections.sort(stationNames);
        this.departureOption.getItems().addAll(stationNames);
        this.destinationOption.getItems().addAll(stationNames);
        System.out.println(departureOption.getItems());


        this.departureOption.setOnAction(evt -> { System.out.println(departureOption.getValue());});
        this.destinationOption.setOnAction(evt -> { System.out.println(destinationOption.getValue());});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //departureOption.getItems().addAll("hi");

        this.departureOption.setPromptText("e.g Arlington");
        this.destinationOption.setPromptText("e.g AllstonStreet");
        this.destinationOption.getItems().addAll("hi");
    }


//    FindRouteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);




}




