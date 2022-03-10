import java.util.ArrayList;

public interface GraphADT<N,E> {

    public void addStation(Station station);

    public void addEdge(Neighbour edge);

    public ArrayList<N> getNeighbouringNodes();
}
