import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.List;


public class MetroView {
    @FXML public ComboBox departureOption = new ComboBox();
    @FXML public ComboBox destinationOption = new ComboBox();
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
        System.out.println("AHAHAHAHAHAHAHHh");
        this.departureOption.getItems().addAll(stationNames);
        this.destinationOption.getItems().addAll(stationNames);
        System.out.println(departureOption);


        this.departureOption.setPromptText("e.g Arlington");
        this.destinationOption.setPromptText("e.g AllstonStreet");

        this.departureOption.setOnAction(evt -> { System.out.println(departureOption.getValue());});
        this.destinationOption.setOnAction(evt -> { System.out.println(destinationOption.getValue());});
    }






//    FindRouteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);




}




