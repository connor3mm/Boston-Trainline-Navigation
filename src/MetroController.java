import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MetroController implements Initializable {
    private MetroModel model;
    private MetroView view;

    public MetroController(MetroView view, MetroModel model) throws Exception {
        this.view = view;
        this.model = model;

        model.initializeGraph();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.view.populateDropDown(model.getStationNames()); // this never gets accessed for some reason
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




