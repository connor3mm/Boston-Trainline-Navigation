import java.util.ArrayList;

public class Station {
    private String id;
    private String stationName;
//    private ArrayList<Neighbour> neighbouringStations;

    public Station(String id, String stationName) {
        this.id = id;
        this.stationName = stationName;
//        this.neighbouringStations = neighbouringStations;
    }

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

//    public ArrayList<Neighbour> getNeighbouringStations() {
//        return neighbouringStations;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

//    public void setNeighbouringStations(ArrayList<Neighbour> neighbouringStations) {
//        this.neighbouringStations = neighbouringStations;
//    }
}
