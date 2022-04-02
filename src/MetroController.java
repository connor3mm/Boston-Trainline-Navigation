import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.List;


public class MetroController {
    private MetroModel model;
    private MetroView view;

    public MetroController(MetroView view, MetroModel model) throws Exception {
        this.view = view;
        this.model = model;

        model.initializeGraph();
        view.populateDropDown(model.getStationNames());
    }
    // model class

//    public void initialize() {
//        model.initializeGraph();
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
//
//        });


    }




