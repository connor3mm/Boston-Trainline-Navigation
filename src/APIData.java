import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class APIData {

    public static void main(String[] args) {
        APIData something = new APIData();
        //GraphImplementation graph = something.readFromAFile();

        MetroModel model = new MetroModel();
        model.initializeGraph();



    }

    public List<Station> readFromAFile() {
        //first two always same
        //color 2 numbers
        List<Station> stations = new ArrayList<>();

        try {
            File myObj = new File("src/StationFile/bostonmetro.txt");
            Scanner myReader = new Scanner(myObj);
            GraphImplementation graph = new GraphImplementation();

            while (myReader.hasNextLine()) {
                //split everything up
                //for each station store id and name in station class
                // for each neighbour(line color, prev, next) create new neighbour object and add to station list

                //Separates the stations and edges then gets the station information
                String currentStation = myReader.nextLine().trim();
                String[] currentLine = currentStation.split("[\t] +");
                String[] stationInformation = currentLine[0].split(" ");

                //Add current station to the graph
                //graph.addStation(new Station(stationInformation[0], stationInformation[1]));

                //Add edge (neighbours)
                String[] edgeInformation = currentLine[1].split("\\s+");
                List<Neighbour> neighbours = new ArrayList<>();
                int i = 0;
                while (i < edgeInformation.length) {
                    neighbours.add(new Neighbour(edgeInformation[i], edgeInformation[i+1], edgeInformation[i+2]));
                    //graph.addEdge(new Neighbour(edgeInformation[i], edgeInformation[i+1], edgeInformation[i+2]));
                    i += 3;
                }
                Station currentStation1 = new Station(stationInformation[0], stationInformation[1], neighbours);
                stations.add(currentStation1);
            }
            myReader.close();
            return stations;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
