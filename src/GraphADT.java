import java.util.List;

public interface GraphADT {

    void addStation(Station station);

    void addEdge(Neighbour neighbour);

    List<Neighbour> getNeighbouringNodes(Station station);
}
