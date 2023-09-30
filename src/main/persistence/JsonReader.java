package persistence;


import model.Jog;
import model.JogList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads JogList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads JogList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public JogList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJogList(jsonObject);
    }

    // EFFECTS: parses JogList from JSON object and returns it
    private JogList parseJogList(JSONObject jsonObject) {
        JogList jogList = new JogList();
        addItems(jogList, jsonObject);
        return jogList;
    }

    // MODIFIES: JogList
    // EFFECTS: parses jogList from JSON object and adds them to JogList
    private void addItems(JogList jogList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("joglist");
        for (Object json : jsonArray) {
            JSONObject nextJog = (JSONObject) json;
            addItem(jogList, nextJog);
        }
    }

    // MODIFIES: JogList
    // EFFECTS: parses Jog from JSON object and adds it to JogList
    private void addItem(JogList jogList, JSONObject nextJog) {
        int jogId = nextJog.getInt("jog id");
        int distanceRan = nextJog.getInt("distance ran");
        int timeTaken = nextJog.getInt("time taken");
        double avgSpeed = nextJog.getDouble("avg speed");
        int avgHeartRate = nextJog.getInt("avg heart rate");
        String dateOfJog = nextJog.getString("date of jog");
        Jog jog = new Jog(jogId, distanceRan, timeTaken, avgSpeed, avgHeartRate, dateOfJog);
        jogList.addJog(jog);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

}