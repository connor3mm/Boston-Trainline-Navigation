import java.util.ArrayList;
import java.util.List;

public interface GraphADT<N,E> {

    public void addStation(Station station);

    public void addEdge(Neighbour neighbour);

    public List<Neighbour> getNeighbouringNodes(Station station);
}
