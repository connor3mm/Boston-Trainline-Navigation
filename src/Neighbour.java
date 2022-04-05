public class Neighbour {
    //Variables
    private Station currentStation;
    private String lineColour;
    private String previousStationID;
    private String nextStationId;

    //Constructor
    public Neighbour(Station currentStation, String lineColour, String previousStationID, String nextStationId) {
        this.currentStation = currentStation;
        this.lineColour = lineColour;
        this.previousStationID = previousStationID;
        this.nextStationId = nextStationId;
    }

    //Getters
    public String getLineColour() {
        return lineColour;
    }

    public String getPreviousStationID() {
        return previousStationID;
    }

    public String getNextStationId() {
        return nextStationId;
    }

    public Station getCurrentStation() {
        return currentStation;
    }
}