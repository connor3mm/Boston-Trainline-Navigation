import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class MetroController {
    @FXML
    public ComboBox departureOption;
    @FXML
    public ComboBox destinationOption;
    @FXML
    public Button FindRouteButton;
    @FXML
    public TextArea routeResult;
    @FXML
    public Label BostonTitle;
    @FXML
    public Button ViewMapButton;
    @FXML
    public ImageView ViewMapIcon;

    // Model object
    MetroModel model = new MetroModel();


    //Initialize the controller
    public void initialize() {
        model.initializeGraph();

        // add station names to dropdown menu
        List<String> stationNames = model.getStationNames();
        java.util.Collections.sort(stationNames);

        departureOption.getItems().addAll(stationNames);
        destinationOption.getItems().addAll(stationNames);

        //Remove station 0(departure) from GUI
        departureOption.getItems().remove(stationNames.size() - 1);
        destinationOption.getItems().remove(stationNames.size() - 1);

        departureOption.setPromptText("e.g Arlington");
        destinationOption.setPromptText("e.g AllstonStreet");

        //for testing the user input in the terminal
        departureOption.setOnAction(evt -> {
            System.out.println(departureOption.getValue());
        });
        destinationOption.setOnAction(evt -> {
            System.out.println(destinationOption.getValue());
        });

        //Checks for errors, if no errors, display text output of path.
        FindRouteButton.setOnAction(actionEvent -> {
            if (departureOption.getValue() == null || destinationOption.getValue() == null) {
                validationCheck(Alert.AlertType.ERROR, "Empty inputs", "Empty input!", "Please choose both a departure and destination option!");
            } else if (departureOption.getValue() == destinationOption.getValue()) {
                validationCheck(Alert.AlertType.WARNING, "Identical destination and departure", "Both destination and departure stations are identical.", "Please choose different stations!");
            } else {
                displayTextOutput();
            }
        });

        //Display map from GUI
        ViewMapButton.setOnAction(evt -> {
            createNewStage();
        });
        ViewMapIcon.setOnMouseClicked(evt -> {
            createNewStage();
        });
    }


    /**
     * Creates the path output for the text area box
     */
    private void displayTextOutput() {
        List<List<String>> findPathValue = model.findPath(departureOption.getValue().toString(), destinationOption.getValue().toString());
        List<List<String>> bestRoutePath = model.bestLinePath(findPathValue);
        List<String> stationNameFromID;

        routeResult.setText("");

        for (List<String> path : bestRoutePath) {
            stationNameFromID = model.getStationNamesFromID(path);
            String resultToString = model.convertToString(stationNameFromID);

            int numberOfLines = model.numOfLineSwitches(path);
            int countStops = path.size() - 1;

            routeResult.appendText("From station " + departureOption.getValue().toString().trim() + " you have to travel " + countStops + " stations to get to station " + destinationOption.getValue().toString().trim());
            routeResult.appendText("\n");
            routeResult.appendText("Take the route");
            routeResult.appendText("\n");
            routeResult.appendText(resultToString);
            routeResult.appendText("\n");
            routeResult.appendText("Number of line switches for the route: " + numberOfLines);
            routeResult.appendText("\n\n");
        }
    }


    /**
     * Outputs validation error message.
     *
     * @param error
     * @param Empty_inputs
     * @param x
     * @param x1
     */
    private void validationCheck(Alert.AlertType error, String Empty_inputs, String x, String x1) {
        Alert alert = new Alert(error);
        alert.setTitle(Empty_inputs);
        alert.setContentText(x + "\n" + x1);
        alert.showAndWait();
    }


    /**
     * Creates GUI map page.
     */
    public void createNewStage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            // TODO: handle error maybe????
            return;
        }

        Stage mapStage = new Stage();
        mapStage.setScene(newScene);
        mapStage.setTitle("Boston Metro Digital Map");
        mapStage.setResizable(false);
        mapStage.showAndWait();
    }
}