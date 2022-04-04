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

    /**
     * Return a list of neighbours for a given station
     * @param station
     * @return List of neighbours
     */
    @Override
    public List<Neighbour> getNeighbouringNodes(Station station) {
        return stationToNeighbourMap.get(station);
    }

    public List<Station> getStations() {
        return this.stations;
    }

    public List<Neighbour> getEdges() {
        return this.edges;
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
     *
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

    /**
     * Takes start and end station, finds the shortest path using Breadth first search
     *
     * @param startStation
     * @param endStation
     * @return Final array of the path from start station to end
     */
    public List<List<String>> findRoute(String startStation, String endStation) {
        List<String> visited = new ArrayList<>();
        Queue<List<String>> agenda = new LinkedList<>();
        ArrayList<String> stationsIDs = new ArrayList<>();

        List<List<String>> output = new ArrayList<>();

        List<String> currentNodePath;
        String currentNode;

        int max = 1000;


        stationsIDs.add(startStation);
        agenda.add(stationsIDs);

        while (agenda.size() != 0) {
            currentNodePath = agenda.poll();
            currentNode = currentNodePath.get(currentNodePath.size() - 1);

            if (visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);


            //Getting the last element of the current path and check if it is the end station then return the path

            if(currentNodePath.size() > max) {
                continue;
            }

            if (currentNodePath.contains(endStation)) {

                max = currentNodePath.size();
                output.add(currentNodePath);
                continue;
                //return currentNodePath;
            }


            //Get neighbours of current station
            ArrayList<String> agendaList = extendPath(currentNode);

            ArrayList<ArrayList<String>> finalOutput = new ArrayList<>();

            //For every neighbour of current node, create new array and append neighbour, if neigh is not in visited list
            for (String neigh : agendaList) {
                ArrayList<String> tempList = new ArrayList<>(currentNodePath);

                tempList.add(neigh);
                finalOutput.add(tempList);

            }
            agenda.addAll(finalOutput);
        }

        List<List<String>> output2 = new ArrayList<>();

        for(List<String> path : output){
            if(path.size() == max ){
                output2.add(path);
            }
        }


        return output2;
    }



    public Station getStationFromId(String id) {
        for (Station currentStation : this.stations) {
            if (currentStation.getId().equals(id)) {
                return currentStation;
            }
        }
        return null;
    }

    /**
     * finds the neighbours of current node.
     *
     * @param currentPathNode
     * @return Arraylist of neighbours
     */
    public ArrayList<String> extendPath(String currentPathNode) {
        ArrayList<String> neighbourIds = new ArrayList<>();
        List<Neighbour> neighbourList;

        Station currentStation = getStationFromId(currentPathNode);
        neighbourList = getNeighbouringNodes(currentStation);


        for (Neighbour currentNeighbour : neighbourList) {
            neighbourIds.add(currentNeighbour.getPreviousStationID());
            neighbourIds.add(currentNeighbour.getNextStationId());
        }

        return neighbourIds;
    }


    /**
     * Calculates how many line switches between start node and end node
     *
     * @param endPath
     * @return integer of line switches
     */
    public int calculateLineSwitching(List<String> endPath) {

        int lineSwitchTotal = 0;
        String currentColour = null;


        for (int i = 0; i < endPath.size() - 1; i++) {
            Station station = getStationFromId(endPath.get(i));
            List<Neighbour> temp = getNeighbouringNodes(station);

            for (Neighbour neigh : temp) {
                if (neigh.getPreviousStationID().equals(endPath.get(i + 1)) || neigh.getNextStationId().equals(endPath.get(i + 1))) {
                    String tempLineColour = neigh.getLineColour();

                    if ((currentColour != null) && (!currentColour.equals(tempLineColour))) {
                        lineSwitchTotal += 1;
                        currentColour = tempLineColour;
                        System.out.println(currentColour);
                        break;
                    }

                    currentColour = tempLineColour;
                }
            }
        }

        return lineSwitchTotal;
    }


}
