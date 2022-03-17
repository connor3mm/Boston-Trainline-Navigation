import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphImplementation implements GraphADT<Station, Neighbour> {

    private List<Station> stations;
    private List<Neighbour> edges;
    private Map<Station, List<Neighbour>> stationToNeighbourMap;

    public GraphImplementation() {
        stations = new ArrayList<>();
        edges = new ArrayList<>();
        stationToNeighbourMap = new HashMap<>();
        stationToNeighbourMap.put(new Station("0", "idk lol"), new ArrayList<>());
    }

    @Override
    public void addStation(Station station) {
        stations.add(station);
        if (!stationToNeighbourMap.containsKey(station)) {
            stationToNeighbourMap.put(station, new ArrayList<>());
        }
    }

    @Override
    public void addEdge(Neighbour neighbour) {
        //we want to create our map
        //create the edge between two stations
        //Change to neighbour
        //helper function to get station with given id from neighbour (2 stations)

        //Station list of neighbours
        //Neighbour with two stations


        edges.add(neighbour);


        Station prevStation = getNeighbourStations(neighbour.getPreviousStationID());
        Station nextStation = getNeighbourStations(neighbour.getNextStationId());


        stationToNeighbourMap.get(prevStation).add(neighbour);
        stationToNeighbourMap.get(nextStation).add(neighbour);
    }

    public void displayMap() {
        for (Map.Entry<Station, List<Neighbour>> entry : stationToNeighbourMap.entrySet()) {
            System.out.println("-----------------------------------");
            System.out.println(entry.getKey().getStationName());

            for (Neighbour n : entry.getValue()) {
                System.out.println(n.getLineColour());
                System.out.println(n.getPreviousStationID());
                System.out.println(n.getNextStationId());

            }
            System.out.println("-----------------------------------");
        }
    }

    public Station getNeighbourStations(String id) {
        for (Station currentStation : this.stations) {
            if (currentStation.getId().equals(id)) {
                return currentStation;
            }
        }
        return null;
    }


    @Override
    public List<Neighbour> getNeighbouringNodes(Station station) {
        //return a list of neighbours for a given station
        return stationToNeighbourMap.get(station);
    }

    public List<Station> getStations() {
        return this.stations;
    }

    public List<Neighbour> getEdges() {
        return this.edges;
    }
}
