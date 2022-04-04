import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MetroController {
    @FXML public ComboBox departureOption;
    @FXML public ComboBox destinationOption;
    @FXML public Button FindRouteButton;
    @FXML public TextArea routeResult;
    @FXML public Label BostonTitle;
    @FXML public Button ViewMapButton;
    @FXML public ImageView ViewMapIcon;

    // model class
    MetroModel model = new MetroModel();

    public void initialize() {
        model.initializeGraph();

        // add station names to dropdown menu
        List<String> stationNames = model.getStationNames();
        java.util.Collections.sort(stationNames);

        departureOption.getItems().addAll(stationNames);
        destinationOption.getItems().addAll(stationNames);
        //Remove station 0(departure) from GUI
        departureOption.getItems().remove(stationNames.size()-1);
        destinationOption.getItems().remove(stationNames.size()-1);

        departureOption.setPromptText("e.g Arlington");
        destinationOption.setPromptText("e.g AllstonStreet");

        departureOption.setOnAction(evt -> { System.out.println(departureOption.getValue());});
        destinationOption.setOnAction(evt -> { System.out.println(destinationOption.getValue());});

        FindRouteButton.setOnAction(actionEvent -> {
            if(departureOption.getValue()==null || destinationOption.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty inputs");
                alert.setContentText("Empty input!" + "\n" + "Please choose both a departure and destination option!");
                alert.showAndWait();
            } else if(departureOption.getValue() == destinationOption.getValue()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Identical destination and departure");
                alert.setContentText("Both destination and departure stations are identical." + "\n" + "Please choose different stations!");
                alert.showAndWait();
            } else {
                List<List<String>> findPathValue = model.findPath(departureOption.getValue().toString(),destinationOption.getValue().toString());
                List<String> stationNameFromID = new ArrayList<>();
                List<List<String>> bestRoutePath = model.bestLinePath(findPathValue);
                routeResult.setText("");
                for(List<String> path : bestRoutePath) {
                    stationNameFromID = model.getStationNamesFromID(path);
                    String resultToString = model.convertToString(stationNameFromID);

                    int numberOfLines = model.numOfLineSwitches(path);
                    int countStops = 0;

                    for(int i = 1; i < path.size()-1; i++) {
                        countStops++;
                    }

                    routeResult.appendText("From station " + departureOption.getValue().toString().trim() + " you have to travel " + countStops + " station to get to station " + destinationOption.getValue().toString().trim());
                    routeResult.appendText("\n");
                    routeResult.appendText("Take the route");
                    routeResult.appendText("\n");
                    routeResult.appendText(resultToString);
                    routeResult.appendText("\n");
                    routeResult.appendText("Number of line switches for the route: " + numberOfLines);
                    routeResult.appendText("\n\n");
                }
            }
        });

        ViewMapButton.setOnAction(evt -> { createNewStage(); });
        ViewMapIcon.setOnMouseClicked(evt -> { createNewStage(); });
    }

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
//        mapStage.setFullScreen(true);
        mapStage.showAndWait();
    };







//    FindRouteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);




}

