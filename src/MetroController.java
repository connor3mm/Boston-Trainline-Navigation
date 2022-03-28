import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


public class MetroController {

    @FXML public MenuButton departureOption;
    @FXML public MenuButton destinationOption;
    @FXML public Button FindRouteButton;
    @FXML public TextArea routeResult;
    @FXML public Label BostonTitle;

    // model class
    MetroModel model = new MetroModel();


    public void initialize() {
        FindRouteButton.setOnAction(event -> {
        });

        // add station names to menu
        model.getStationNames().forEach(stationName -> {
            departureOption.getItems().add(new MenuItem(stationName));
            destinationOption.getItems().add(new MenuItem(stationName));
        });

        for (int i = 0; i < 5; i++) {
            departureOption.getItems().add(new MenuItem(("hello")));
        }

    }






//    FindRouteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);




}




