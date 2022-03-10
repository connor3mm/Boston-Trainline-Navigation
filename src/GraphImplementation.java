import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphImplementation implements GraphADT<Station,EdgeColourLine> {

    private List<Station> stations;
    private List<Neighbour> edges;
    private Map<Station, List<Neighbour>> adjacentStations;

    public GraphImplementation() {
        stations = new ArrayList<>();
        edges = new ArrayList<>();
        adjacentStations = new HashMap<>();
    }

    @Override
    public void addStation(Station station) {


    }

    @Override
    public void addEdge(Neighbour edge) {
        this.edges.add(edge);

    }

    @Override
    public ArrayList<Station> getNeighbouringNodes() {
        //specify a station
        //for the specified station we want to get the ones that are connected to it
        return null;
    }

    public List<Station> getStations() {
        return this.stations;
    }

    public List<Neighbour> getEdges() {
        return this.edges;
    }

}
