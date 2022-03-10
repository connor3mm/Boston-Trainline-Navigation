import java.util.List;

public class MetroModel {
    private GraphImplementation graph;
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
