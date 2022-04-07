import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MetroModelTest {



    /**
     * The test checks whether the “getStationNames()” function returns a list with all of the stations
     * @throws FileNotFoundException
     */
    @org.junit.jupiter.api.Test
    void getStationNames() throws FileNotFoundException {
        MetroModel model = new MetroModel();
        model.initializeGraph();
        List<String> stationNames = model.getStationNames();
        int i=0;

        File myObj = new File("src/StationFile/allStations.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()){
            String currentStation = myReader.nextLine();
            Assertions.assertTrue(currentStation.trim().equals(stationNames.get(i).trim()));
            i++;
        }
    }

    //for this test a List of station names is created and then using the “convertToString()” function,
    //that list is converted to a formatted string, in which between each station the “ > ” symbol is added
    @org.junit.jupiter.api.Test
    void convertToString() {
        MetroModel model = new MetroModel();
        model.initializeGraph();

        List<String> stations = new ArrayList<>();
        stations.add("Riverway");
        stations.add("GreenStreet");
        stations.add("SutherlandRoad");
        stations.add("BostonUniversityEast");
        stations.add("Alewife");

        String testString = "Riverway > GreenStreet > SutherlandRoad > BostonUniversityEast > Alewife";
        String testStringFromFunction = model.convertToString(stations);

        Assertions.assertTrue(testStringFromFunction.equals(testString));
    }



    /**
     *  The test checks, whether the function “getIdOFStationFromList()” takes the correct ID of the station.
     *  For this purpose, station “Andrew” was used as a testing station, whose id is “94”
     */
    @org.junit.jupiter.api.Test
    void getIdOFStationFromList() {
        MetroModel model = new MetroModel();
        model.initializeGraph();

        String station = "Andrew\t\t";
        String id = model.getIdOFStationFromList(station);

        Assertions.assertTrue(Objects.equals(id, "94"));
    }



    /**
     * The test checks whether the function has found all paths. This function compares the ID of stations and not the station names.
     *  For this test, the test calculates the paths from “Andrew” station to “Central” station
     */
    @org.junit.jupiter.api.Test
    void findPath() {
        MetroModel model = new MetroModel();
        model.initializeGraph();

        String start = "Andrew\t\t";
        String end = "Central\t\t";

        List<List<String>> findPathValue = model.findPath(start, end);
        List<List<String>> answerLists = new ArrayList<>();
        List<String> answerList = new ArrayList<>();


        answerList.add("94");
        answerList.add("60");
        answerList.add("33");
        answerList.add("30");
        answerList.add("29");
        answerList.add("25");
        answerList.add("23");
        answerList.add("21");

        answerLists.add(answerList);

        Assertions.assertTrue(findPathValue.equals(answerLists));
    }



    /**
     * checks whether the number of the switched lines is correct.
     * For this example, the route from “Andrew” station to “Central” station is used. The number of line switches should be 0
     */
    @org.junit.jupiter.api.Test
    void numOfLineSwitches() {
        MetroModel model = new MetroModel();
        model.initializeGraph();

        List<String> path = new ArrayList<>();//path from Andrew station to Central station

        path.add("94");
        path.add("60");
        path.add("33");
        path.add("30");
        path.add("29");
        path.add("25");
        path.add("23");
        path.add("21");

        int numberOfLinesSwitched = model.numOfLineSwitches(path);

        Assertions.assertTrue(numberOfLinesSwitched == 0);
    }



    /**
     * The test checks if the most optimal path is chosen from all of the found paths.
     * For this test, the routes from “OrientHeights” station to “HeathStreet” station are calculated.
     * The algorithm finds two paths but chooses the best one.
     */
    @org.junit.jupiter.api.Test
    void bestLinePath() {
        MetroModel model = new MetroModel();
        model.initializeGraph();

        String start = "OrientHeights\t";
        String end = "HeathStreet\t\t";

        List<List<String>> findPathValue = model.findPath(start, end);
        List<List<String>> bestRoutePath = model.bestLinePath(findPathValue);
        List<List<String>> answerLists = new ArrayList<>();
        List<String> answerList = new ArrayList<>();

        answerList.add("11");
        answerList.add("13");
        answerList.add("16");
        answerList.add("18");
        answerList.add("26");
        answerList.add("28");
        answerList.add("27");
        answerList.add("29");
        answerList.add("31");
        answerList.add("34");
        answerList.add("41");
        answerList.add("53");
        answerList.add("62");
        answerList.add("70");
        answerList.add("79");
        answerList.add("84");
        answerList.add("87");
        answerList.add("88");
        answerList.add("89");
        answerList.add("92");
        answerList.add("95");
        answerList.add("96");

        answerLists.add(answerList);

        Assertions.assertTrue(answerLists.equals(bestRoutePath));
    }


    /**
     * This test if the path line switching going forward, equals the path line switching going backwards
     */
    @org.junit.jupiter.api.Test
    void equalPathLineSwitching() {

        MetroModel model = new MetroModel();
        model.initializeGraph();

        String start = "Aquarium\t\t";
        String end = "Broadway\t\t";

        List<List<String>> findPathValue = model.findPath(start, end);
        List<List<String>> bestRoutePath = model.bestLinePath(findPathValue);

        List<List<String>> findPathValue2 = model.findPath(end, start);
        List<List<String>> bestRoutePath2 = model.bestLinePath(findPathValue2);


        int numberOfLinesSwitched = model.numOfLineSwitches(bestRoutePath.get(0));
        int numberOfLinesSwitched2 = model.numOfLineSwitches(bestRoutePath2.get(0));

        Assertions.assertEquals(numberOfLinesSwitched, numberOfLinesSwitched2);
    }


    /**
     * This test if the path going forward, equals the path going backwards
     */
    @org.junit.jupiter.api.Test
    void equalPathDirection() {

        MetroModel model = new MetroModel();
        model.initializeGraph();

        String start = "Aquarium\t\t";
        String end = "Broadway\t\t";

        List<List<String>> findPathValue = model.findPath(start, end);
        List<List<String>> bestRoutePath = model.bestLinePath(findPathValue);

        List<List<String>> findPathValue2 = model.findPath(end, start);
        List<List<String>> bestRoutePath2 = model.bestLinePath(findPathValue2);

        Collections.reverse(bestRoutePath2.get(0));

        Assertions.assertEquals(bestRoutePath, bestRoutePath2);
    }



}