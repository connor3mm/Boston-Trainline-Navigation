public class Neighbour {
    private String lineColour;
    private int previousStationID;
    private int nextStationId;

    public Neighbour(String lineColour, int previousStationID, int nextStationId) {
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

    public int getPreviousStationID() {
        return previousStationID;
    }

    public void setPreviousStationID(int previousStationID) {
        this.previousStationID = previousStationID;
    }

    public int getNextStationId() {
        return nextStationId;
    }

    public void setNextStationId(int nextStationId) {
        this.nextStationId = nextStationId;
    }
}
