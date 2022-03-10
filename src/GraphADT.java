import java.util.ArrayList;
import java.util.List;

public interface GraphADT<N,E> {

    public void addStation(Station station);

    public void addEdge(Station station);

    public List<Neighbour> getNeighbouringNodes(Station station);
}
