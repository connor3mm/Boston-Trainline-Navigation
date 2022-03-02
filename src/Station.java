import java.util.ArrayList;

public class Station {
    private int id;
    private String stationName;
    private ArrayList<Neighbour> neighbouringStations;

    public Station(int id, String stationName, ArrayList<Neighbour> neighbouringStations) {
        this.id = id;
        this.stationName = stationName;
        this.neighbouringStations = neighbouringStations;
    }

    public int getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public ArrayList<Neighbour> getNeighbouringStations() {
        return neighbouringStations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setNeighbouringStations(ArrayList<Neighbour> neighbouringStations) {
        this.neighbouringStations = neighbouringStations;
    }
}
