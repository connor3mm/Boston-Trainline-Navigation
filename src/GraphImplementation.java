import java.util.*;

public class GraphImplementation implements GraphADT {

    //Variables
    private List<Station> stations;
    private List<Neighbour> edges;
    private Map<Station, List<Neighbour>> stationToNeighbourMap;

    //Constructor
    public GraphImplementation() {
        stations = new ArrayList<>();
        edges = new ArrayList<>();
        stationToNeighbourMap = new HashMap<>();
    }


    //Getters
    @Override
    public List<Neighbour> getNeighbouringNodes(Station station) {
        return stationToNeighbourMap.get(station);
    }

    @Override
    public List<Station> getStations() {
        return this.stations;
    }


    /**
     * Add stations to map data structure
     *
     * @param station
     */
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


    /**
     * For Testing, displays map to terminal
     */
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
     * @return Final list of the path(s) from start station to end station
     */
    public List<List<String>> findRoute(String startStation, String endStation) {
        Queue<List<String>> agenda = new LinkedList<>();
        ArrayList<String> stationsIDs = new ArrayList<>();
        List<List<String>> visited = new ArrayList<>();
        List<List<String>> output = new ArrayList<>();

        List<String> currentNodePath;
        String currentNode;
        int max = 1000;

        stationsIDs.add(startStation);
        agenda.add(stationsIDs);

        while (agenda.size() != 0) {
            currentNodePath = agenda.poll();
            currentNode = currentNodePath.get(currentNodePath.size() - 1);

            if (visited.contains(currentNodePath)) {
                continue;
            }

            if (currentNodePath.size() > max) {
                continue;
            }

            //If end station is found, add to the output list and set new max with path length
            if (currentNodePath.contains(endStation)) {
                max = currentNodePath.size();
                output.add(currentNodePath);
                continue;
            }

            visited.add(currentNodePath);

            //Get neighbours of current station
            ArrayList<String> nextStates = extendPath(currentNode);

            ArrayList<ArrayList<String>> tempOutput = new ArrayList<>();

            //For every neighbour of current node, create new array and append neighbour, if neighbour is not in current path list
            for (String neighbour : nextStates) {
                ArrayList<String> tempList = new ArrayList<>(currentNodePath);

                if (!currentNodePath.contains(neighbour)) {
                    tempList.add(neighbour);
                    tempOutput.add(tempList);
                }
            }
            agenda.addAll(tempOutput);
        }

        //Finds the best path(s) that has the minimal stops
        List<List<String>> allPaths = getMinPath(output, max);

        return allPaths;
    }


    /**
     * Finds minimal path from given list of paths
     *
     * @param output
     * @param max
     * @return allPaths
     */
    private List<List<String>> getMinPath(List<List<String>> output, int max) {
        List<List<String>> allPaths = new ArrayList<>();

        for (List<String> path : output) {
            if (path.size() == max) {
                if (!allPaths.contains(path))
                    allPaths.add(path);
            }
        }
        return allPaths;
    }


    /**
     * Gets the station from its ID
     *
     * @param id
     * @return Station with passed in ID
     */
    public Station getStationFromId(String id) {
        for (Station currentStation : this.stations) {
            if (currentStation.getId().equals(id)) {
                return currentStation;
            }
        }
        return null;
    }


    /**
     * Finds the neighbours of current node
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
     * Calculates how many line switches between start station and end station
     *
     * @param endPath
     * @return The number of line switches
     */
    public int calculateLineSwitching(List<String> endPath) {
        int lineSwitchTotal = 0;
        List<String> list1 =  new ArrayList<>();

        int count = 0;


        if(endPath.size() == 2){
            return 0;
        }


        //get first station, get first stations neighbours
        Station station = getStationFromId(endPath.get(0));
        List<Neighbour> neighbours = getNeighbouringNodes(station);

        //for all neighbours, check if the next station id appears from endpath.
        for (Neighbour neigh : neighbours) {
            if (neigh.getPreviousStationID().equals(endPath.get(1)) || neigh.getNextStationId().equals(endPath.get(1))) {
                String tempLineColour = neigh.getLineColour();
                list1.add(tempLineColour);
            }
        }


        //for the rest of endpath, for each, check if the next station id appears from endpath.
        for (int i = 1; i < endPath.size() - 1; i++) {
            List<String> list2 =  new ArrayList<>();
            station = getStationFromId(endPath.get(i));
            neighbours = getNeighbouringNodes(station);


            for (Neighbour neigh : neighbours) {
                if (neigh.getPreviousStationID().equals(endPath.get(i + 1)) || neigh.getNextStationId().equals(endPath.get(i + 1))) {
                    String tempLineColour = neigh.getLineColour();
                    list2.add(tempLineColour);
                }
            }

            //Check if first list has a colour matching list 2
            for (String colour: list1){
                if(list2.contains(colour)){
                 count += 1;
                }
            }

            //if no colours match, line switching occurs
            if(count == 0){
                lineSwitchTotal +=1;
            }

            count = 0;
            list1 = list2;
        }

        return lineSwitchTotal;
    }
}