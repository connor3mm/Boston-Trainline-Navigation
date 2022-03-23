public class Neighbour {
    private Station currentStation;
    private String lineColour;
    private String previousStationID;
    private String nextStationId;

    public Neighbour(Station currentStation, String lineColour, String previousStationID, String nextStationId) {
        this.currentStation = currentStation;
        this.lineColour = lineColour;
        this.previousStationID = previousStationID;
        this.nextStationId = nextStationId;
    }

    public String getLineColour() {
        return lineColour;
    }

    public void setLineColour(String lineColour) {
        this.lineColour = lineColour;
    }

    public String getPreviousStationID() {
        return previousStationID;
    }

    public void setPreviousStationID(String previousStationID) {
        this.previousStationID = previousStationID;
    }

    public String getNextStationId() {
        return nextStationId;
    }

    public void setNextStationId(String nextStationId) {
        this.nextStationId = nextStationId;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }
}
