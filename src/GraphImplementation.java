import java.lang.reflect.Array;
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

//    public List<Station> getStation(Station S){
//
//    }

    public List<Neighbour> getEdges() {
        return this.edges;
    }


//    public ArrayList<Station> calculateRoute( Map<Station, List<Neighbour>> stationToNeighbourMap, Station startId, Station endId) {
//
//        int prev = solve(startId);
//
//
//        return reconstructPath(startId, endId, prev);
//    }


    public ArrayList<String> findRoute(String startStation, String endStation) {
        Queue<ArrayList<String>> agenda = new LinkedList<>(); //this should probably be just a String
        ArrayList<ArrayList<String>> visited = new ArrayList<>(); //this needs to change lmao
        ArrayList<String> stationsIDs = new ArrayList<>();
        stationsIDs.add(startStation);
        agenda.add(stationsIDs);
        ArrayList<String> currentNodePath;
        List<Neighbour> neighbours;

        while (agenda.size() != 0){
            System.out.println("Agenda: " +agenda);
            currentNodePath = agenda.poll();

            if(visited.contains(currentNodePath)){
                continue;
            }

            visited.add(currentNodePath);

            //getting the last element of the current path and check if it is the end station then return the path
            if(currentNodePath.get(currentNodePath.size()-1).equals(endStation)) {
                return currentNodePath;
            }

//            ArrayList<String> nextStates;
            agenda.add(extendPath(currentNodePath));
//
//            for(String currentState: nextStates) {
//                String lastElement = nextStates.get(nextStates.size() - 1);
//                if(!visited.contains(lastElement)){
//                    visited.add(lastElement);
//                    //agenda.add(ne);
//                }
//            }


        }


        return null;  //prev
    }

    public Station getStationFromId(String id) {
        for(Station currentStation: this.stations) {
            if(currentStation.getId().equals(id)) {
                return currentStation;
            }
        }
        return null;
    }

    public ArrayList<String> extendPath(ArrayList<String> currentPathNode) {
        ArrayList<String> neighbourIds = new ArrayList<>();
        List<Neighbour> neighbourList = new ArrayList<>();
        for(String currentId : currentPathNode) {
            Station currentStation = getStationFromId(currentId);
            neighbourList = getNeighbouringNodes(currentStation);
        }

        for(Neighbour currentNeighbour: neighbourList) {
            neighbourIds.add(currentNeighbour.getPreviousStationID());
            neighbourIds.add(currentNeighbour.getNextStationId());
        }

        return neighbourIds;
    }


}
