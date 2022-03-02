import java.util.ArrayList;

public interface GraphInterface<N,E> {

    public void addVertex();

    public void addEdge();

    //add list for neighbours
    public ArrayList<N> getNeighbouringNodes();
}
