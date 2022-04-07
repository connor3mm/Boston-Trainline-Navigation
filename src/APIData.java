import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class APIData {
    private List<Station> stations = new ArrayList<>();
    private List<Neighbour> neighbours = new ArrayList<>();

    //Getters
    public List<Station> getStations() {
        return this.stations;
    }

    public List<Neighbour> getNeighbours() {
        return this.neighbours;
    }

    /**
     * Read stations and neighbours from file and add them to station and neighbours lists.
     */
    public void readFromAFile(File myObj) {
        try {
            Scanner myReader = new Scanner(myObj);

            //split everything up
            //for each station store id and name in station class
            // for each neighbour(line color, prev, next) create new neighbour object and add to station list
            while (myReader.hasNextLine()) {
                //Separates the stations and edges then gets the station information
                String currentStation = myReader.nextLine().trim();
                String[] currentLine = currentStation.split("[\t] +");
                String[] stationInformation = currentLine[0].split(" ");

                //Create new station from current file info
                String[] edgeInformation = currentLine[1].split("\\s+");
                Station currentStation1 = new Station(stationInformation[0], stationInformation[1]);

                //Add edges(neighbours)
                int i = 0;
                while (i < edgeInformation.length) {
                    neighbours.add(new Neighbour(currentStation1, edgeInformation[i], edgeInformation[i + 1], edgeInformation[i + 2]));
                    i += 3;
                }

                stations.add(currentStation1);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
