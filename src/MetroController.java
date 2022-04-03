import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.List;


public class MetroController {
    @FXML public ComboBox departureOption;
    @FXML public ComboBox destinationOption;
    @FXML public Button FindRouteButton;
    @FXML public TextArea routeResult;
    @FXML public Label BostonTitle;

    // model class
    MetroModel model = new MetroModel();

    public void initialize() {
        model.initializeGraph();

        // add station names to dropdown menu
        List<String> stationNames = model.getStationNames();
        java.util.Collections.sort(stationNames);
        departureOption.getItems().addAll(stationNames);
        destinationOption.getItems().addAll(stationNames);

        departureOption.setPromptText("e.g Arlington");
        destinationOption.setPromptText("e.g AllstonStreet");

        departureOption.setOnAction(evt -> { System.out.println(departureOption.getValue());});
        destinationOption.setOnAction(evt -> { System.out.println(destinationOption.getValue());});

    }






//    FindRouteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);




}

