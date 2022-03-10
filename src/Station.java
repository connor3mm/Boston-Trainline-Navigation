import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Station {
    private String id;
    private String stationName;
    private List<Neighbour> neighbouringStations;

    public Station(String id, String stationName, List<Neighbour> neighbouringStations) {
        this.id = id;
        this.stationName = stationName;
        this.neighbouringStations = neighbouringStations;
    }

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public List<Neighbour> getNeighbouringStations() {
        return neighbouringStations;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setNeighbouringStations(ArrayList<Neighbour> neighbouringStations) {
        this.neighbouringStations = neighbouringStations;
    }
}
