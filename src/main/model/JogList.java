package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a jog list which contains a list of Jogs
public class JogList implements Writable {

    private ArrayList<Jog> jogList;

    //EFFECTS: creates an object JogList with an empty arraylist initialized
    public JogList() {
        jogList = new ArrayList<>();
    }

    //EFFECTS: returns Jog at index n
    public Jog getJog(int n) {
        return jogList.get(n);
    }

    //MODIFIES: this
    //EFFECTS: adds the jog object into the arraylist
    public void addJog(Jog jog) {
        jogList.add(jog);
        EventLog.getInstance().logEvent(new Event("added new jog with jog id: " + jog.getJogId()));
    }

    //REQUIRES: jog of specified jogId should already be present
    //MODIFIES: this
    //EFFECTS: removes the jog of the specified jogId
    public void removeJog(int jogId) {
        Jog temp = null;
        for (Jog jog : jogList) {
            if (jogId == jog.getJogId()) {
                temp = jog;
            }
        }
        jogList.remove(temp);
        EventLog.getInstance().logEvent(new Event("removed jog with jog id: " + temp.getJogId()));
    }

    //REQUIRES: Jog of specified jogId should already be present
    //EFFECTS:  searches through the arraylist for jog of specified jogId and returns it
    public Jog searchJog(int jogId) {
        Jog temp = null;
        for (Jog jog : jogList) {
            if (jogId == jog.getJogId()) {
                temp = jog;
                break;
            }
        }
        return temp;
    }

    //EFFECTS: returns the size jogList
    public int jogListSize() {
        return jogList.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("joglist", jogListToJson());
        return json;
    }

    // EFFECTS: returns Jog in this JogList as a JSON array
    private JSONArray jogListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Jog jog : jogList) {
            jsonArray.put(jog.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns jogList;
    public ArrayList getJogList() {
        return jogList;
    }
}
