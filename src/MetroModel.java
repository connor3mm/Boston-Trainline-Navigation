import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MetroModel {
    public static GraphImplementation graph;

    //main for testing
    public static void main(String[] args) {
        MetroModel model = new MetroModel();
        model.initializeGraph();
        List<Station> stations = graph.getStations();
        List<Neighbour> s = graph.getNeighbouringNodes(stations.get(6));
//        System.out.println(stations.get(6).getStationName());
//        System.out.println(s.get(0).getLineColour());
//        System.out.println(s.get(0).getPreviousStationID());
//        System.out.println(s.get(0).getNextStationId());
    }

    /**
     * initializer to build the model.
     */
    public void initializeGraph() {
        graph = new GraphImplementation();
        APIData data = new APIData();
        data.readFromAFile();

        List<Station> stations = data.getStations();
        List<Neighbour> neighbours = data.getNeighbours();

        graph.addStation(new Station("0", "departure"));

        for (Station station : stations) {
            graph.addStation(station);
        }

        for (Neighbour neighbour : neighbours) {
            graph.addEdge(neighbour);
        }

        graph.displayMap();

        //Test for bfs search TO BE REMOVED
//        System.out.println("===================================================");
//        List<List<String>> endPath = graph.findRoute("74", "34");
//        for (List<String> path : endPath) {
//            System.out.println("RESULT: " + path);
//        }
//
//        System.out.println("****************************************************");
//        System.out.println(bestLinePath(endPath));
//
//        //int lineSwitches = graph.calculateLineSwitching(endPath);
//        // System.out.println("Line Switches " + lineSwitches);
//        System.out.println("****************************************************");
//        //System.out.println(getStationNamesFromID(endPath));
//        //System.out.println(convertToString(getStationNamesFromID(endPath)));
    }


    /**
     * Gets all station names
     *
     * @return list of station names
     */
    public List<String> getStationNames() {
        List<Station> stationList = graph.getStations();
        List<String> stationNames = new ArrayList<>();

        stationList.forEach(station -> {
            stationNames.add(station.getStationName());
        });

        return stationNames;
    }


    /**
     * Gets station name from their ID
     *
     * @param path
     * @return List of station names
     */
    public List<String> getStationNamesFromID(List<String> path) {
        List<String> stationNames = new ArrayList<>();
        List<Station> stationList = graph.getStations();

        for (int i = 0; i < path.size(); i++) {
            stationNames.add(stationList.get(Integer.parseInt(path.get(i))).getStationName());
        }

        return stationNames;
    }


    /**
     * Converts list to a string of stations with > between them
     *
     * @param names
     * @return String of stations
     */
    public String convertToString(List<String> names) {
        StringBuilder string = new StringBuilder(names.get(0).trim());
        string.append(" > ");

        for (int i = 1; i < names.size(); i++) {
            string.append(names.get(i).trim());

            if (i != names.size() - 1) {
                string.append(" > ");
            }
        }
        return string.toString();
    }


    /**
     * Gets ID's from station names.
     *
     * @param station
     * @return ID of station
     */
    public String getIdOFStationFromList(String station) {
        List<Station> stationList = graph.getStations();
        String id = "";
        for (int i = 0; i < stationList.size(); i++) {
            if (Objects.equals(stationList.get(i).getStationName(), station)) {
                id = stationList.get(i).getId();
            }
        }

        return id;
    }


    /**
     * Finds the path from start station to end station.
     *
     * @param start
     * @param end
     * @return List of paths
     */
    public List<List<String>> findPath(String start, String end) {
        start = getIdOFStationFromList(start);
        end = getIdOFStationFromList(end);
        return graph.findRoute(start, end);
    }


    /**
     * Gets line switching number for single path
     *
     * @param endPath
     * @return Number of line switches
     */
    public int numOfLineSwitches(List<String> endPath) {
        return graph.calculateLineSwitching(endPath);
    }


    /**
     * Finds the best path in a list of paths.
     *
     * @param paths
     * @return List of best paths
     */
    public List<List<String>> bestLinePath(List<List<String>> paths) {
        List<List<String>> bestPaths = new ArrayList<>();
        int bestLine = 1000;

        for (List<String> path : paths) {
            int lines = 0;
            lines = graph.calculateLineSwitching(path);

            if (lines <= bestLine) {
                bestLine = lines;
                bestPaths.add(path);
            }
        }
        return bestPaths;
    }
}