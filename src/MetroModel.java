import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetroModel {
    public static GraphImplementation graph;


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

        //Test for dfs search TO BE REMOVED
        System.out.println("===================================================");
        List<String> endPath = graph.findRoute("30", "0");  // ("0", "30") doesn't work cause 0 is starting destination
        System.out.println("RESULT: " + endPath);
        int lineSwitches = graph.calculateLineSwitching(endPath);
        System.out.println("Line Switches " + lineSwitches);
        System.out.println("****************************************************");
        //System.out.println(getStationNamesFromID(endPath));
        System.out.println(convertToString(getStationNamesFromID(endPath)));
    }



    public List<String> getStationNames() {
        List<Station> stationList = graph.getStations();
        List<String> stationNames = new ArrayList<>();

        stationList.forEach(station -> {
            stationNames.add(station.getStationName());
        });

        return stationNames;
    }

    public List<String> getStationNamesFromID(List<String> path){
        List<String> stationNames = new ArrayList<>();
        List<Station> stationList = graph.getStations();

        for (int i=0; i< path.size();i++){
            stationNames.add(stationList.get(Integer.parseInt(path.get(i))).getStationName());//getting the name of the stations
        }

        return stationNames;
    }

    public String convertToString(List<String> names){
        String string = names.get(0).trim();
        string = string + " > ";

        for (int i=1; i<names.size();i++){
            string = string + names.get(i).trim();

            if(i != names.size()-1) {
                string += " > ";
            }
        }
        return string;
    }

    public String getIdOFStationFromList(String station){
        List<Station> stationList = graph.getStations();
        String id = "";

        for (int i=0; i< stationList.size();i++){
            if(stationList.get(i).getStationName() == station){
                id = stationList.get(i).getId();
            }
        }

        return id;
    }

    public List<String> findPath(String start, String end){
        List<Station> stationList = graph.getStations();

        start = getIdOFStationFromList(start);
        end = getIdOFStationFromList(end);

        List<String> endPath = graph.findRoute(start, end);

        return endPath;
    }


    public int numOfLineSwitches(List<String> endPath){
        int lineSwitching = graph.calculateLineSwitching(endPath);
        return lineSwitching;
    }

}