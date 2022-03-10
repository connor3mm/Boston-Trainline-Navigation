import java.util.List;

public class MetroModel {
    public static GraphImplementation graph;
    public static void main(String[] args) {
        APIData something = new APIData();
        //GraphImplementation graph = something.readFromAFile();

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
        List<Station> stations = data.readFromAFile();

        for(Station station: stations) {
            graph.addStation(station);
            graph.addEdge(station);
        }


    }

}
