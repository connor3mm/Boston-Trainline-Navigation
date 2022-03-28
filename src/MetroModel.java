import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetroModel {
    public static GraphImplementation graph = new GraphImplementation();

    public static void main(String[] args) {
        MetroModel model = new MetroModel();
        model.initializeGraph();
        List<Station> stations = graph.getStations();
        List<Neighbour> s = graph.getNeighbouringNodes(stations.get(6));
        System.out.println(stations.get(6).getStationName());
        System.out.println(s.get(0).getLineColour());
        System.out.println(s.get(0).getPreviousStationID());
        System.out.println(s.get(0).getNextStationId());
    }

    public void initializeGraph() {
        graph = new GraphImplementation();
        APIData data = new APIData();
        data.readFromAFile();
        List<Station> stations = data.getStations();
        List<Neighbour> neighbours = data.getNeighbours();

        graph.addStation(new Station("0", "departure"));
        for (Station station : stations) {
            graph.addStation(station);
        }


        for (Neighbour neighbour : neighbours) {
            graph.addEdge(neighbour);
        }


        graph.displayMap();
    }

    public List<String> getStationNames() {
        List<Station> stationList = graph.getStations();
        List<String> stationNames = new ArrayList<>();
        
        stationList.forEach(station -> {
            stationNames.add(station.getStationName());
        });
        
        return stationNames;
    }

}
