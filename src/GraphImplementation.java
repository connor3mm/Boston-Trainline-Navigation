import java.util.*;

public class GraphImplementation implements GraphADT<Station, Neighbour> {

    private List<Station> stations;
    private List<Neighbour> edges;
    private Map<Station, List<Neighbour>> stationToNeighbourMap;

    public GraphImplementation() {
        stations = new ArrayList<>();
        edges = new ArrayList<>();
        stationToNeighbourMap = new HashMap<>();
    }

    @Override
    public void addStation(Station station) {
        stations.add(station);
        if (!stationToNeighbourMap.containsKey(station)) {
            stationToNeighbourMap.put(station, new ArrayList<>());
        }
    }

    /**
     * adding neighbour to edges list, then adding everything to stations to neighbour map
     * @param neighbour
     */
    @Override
    public void addEdge(Neighbour neighbour) {
        edges.add(neighbour);
        stationToNeighbourMap.get(neighbour.getCurrentStation()).add(neighbour);
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


    @Override
    public List<Neighbour> getNeighbouringNodes(Station station) {
        //return a list of neighbours for a given station
        return stationToNeighbourMap.get(station);
    }

    public List<Station> getStations() {
        return this.stations;
    }


    public void getStationsName() {
//        List<String> list = new List<>();
//
    //        for (int i = 0; i < this.getStations().size(); i++) {
//
//        }
//        return this.stations.forEach(station -> station.getStationName());
    }

//    public List<Station> getStation(Station S){
//
//    }

    public List<Neighbour> getEdges() {
        return this.edges;
    }

//
//    public ArrayList<Station> calculateRoute( Map<Station, List<Neighbour>> stationToNeighbourMap, Station startId, Station endId) {
//
////        int prev = solve(startId);
//
//
//        return reconstructPath(startId, endId, prev);
//    }
//
//
//    public int solve(Station startStation, Station endStation) {
//        Queue<Station> agenda = new LinkedList<>();
//        ArrayList<String> visited = new ArrayList<String>();
//
//        agenda.add(startStation);
//        visited.add(startStation.getId());
//
//        Station node = startStation;
//        List<Neighbour> neighbours;
//
//        while (!agenda.isEmpty()){
//            node = agenda.remove();
//            neighbours = getNeighbouringNodes(node);
//
//            for(Neighbour next : neighbours){
//
//              //  if (!visited[next]){
//
//                //}
//            }
//
//
//
//        }
//
//
//        return 1;  //prev
//    }
//
//
//    public ArrayList<Station> reconstructPath(Station startId, Station endId, int prev) {
//
//        return null;
//    }
//



}
