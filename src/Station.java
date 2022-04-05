import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Station {
    //Variables
    private String id;
    private String stationName;

    //Constructor
    public Station(String id, String stationName) {
        this.id = id;
        this.stationName = stationName;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }
}
