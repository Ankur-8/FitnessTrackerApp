package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a jog having an id, distance, time, date of jog and avg speed and heart rate
public class Jog implements Writable {

    private int jogId;
    private int distanceRan;
    private int timeTaken;
    private double avgSpeed;
    private int avgHeartRate;
    private String dateOfJog;

    //REQUIRES: jogId should be unique for every jog
    //EFFECTS: creates an object Jog with all int fields set to 0 and date to "To be assigned"
    public Jog(int jogId) {
        this.jogId = jogId;
        distanceRan = 0;
        timeTaken = 0;
        avgSpeed = 0;
        avgHeartRate = 0;
        dateOfJog = "To be assigned";
    }

    //REQUIRES: jogId should be unique for every jog
    //EFFECTS: creates an object Jog with all fields having their respective value as given in parameters
    public Jog(int jogId, int distanceRan, int timeTaken, double avgSpeed, int avgHeartRate, String dateOfJog) {
        this.jogId = jogId;
        this.distanceRan = distanceRan;
        this.timeTaken = timeTaken;
        this.avgSpeed = avgSpeed;
        this.avgHeartRate = avgHeartRate;
        this.dateOfJog = dateOfJog;
    }

    //REQUIRES: distanceRan > 0
    //MODIFIES: this
    //EFFECTS: updates the value of distanceRan to the input value
    public boolean setDistanceRan(int distanceRan) {
        this.distanceRan = distanceRan;
        return true;
    }

    //REQUIRES: timeTaken > 0
    //MODIFIES: this
    //EFFECTS: updates the value of TimeTaken to the input value
    public boolean setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
        return true;
    }

    //REQUIRES: avgSpeed > 0
    //MODIFIES: this
    //EFFECTS: updates the value of avgSpeed to the input value
    public boolean setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
        return true;
    }

    //REQUIRES: avgHeartRate > 0
    //MODIFIES: this
    //EFFECTS: updates the value of avgHeartRate to the input value
    public boolean setAvgHeartRate(int avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
        return true;
    }

    //MODIFIES: this
    //EFFECTS: updates the value of dateOfJog to the input value
    //         format for input: "month name:date"
    public boolean setDateOfJog(String dateOfJog) {
        this.dateOfJog = dateOfJog;
        return true;
    }

    //EFFECTS: returns the jogId of the jog
    public int getJogId() {
        return jogId;
    }

    //EFFECTS: returns the value of distanceRan
    public int getDistanceRan() {
        return distanceRan;
    }

    //EFFECTS: returns the value of timeTaken
    public int getTimeTaken() {
        return timeTaken;
    }

    //EFFECTS: returns the value of avgSpeed
    public double getAvgSpeed() {
        return avgSpeed;
    }

    //EFFECTS: returns the value of avgHeartRate
    public int getAvgHeartRate() {
        return avgHeartRate;
    }

    //EFFECTS: returns the value of dateOfJog
    public String getDateOfJog() {
        return dateOfJog;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("jog id", jogId);
        json.put("distance ran", distanceRan);
        json.put("time taken", timeTaken);
        json.put("avg speed", avgSpeed);
        json.put("avg heart rate", avgHeartRate);
        json.put("date of jog", dateOfJog);
        return json;
    }
}
