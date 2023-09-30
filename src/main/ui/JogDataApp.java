package ui;

import model.Event;
import model.EventLog;
import model.Jog;
import model.JogList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//Jog Data Application
public class JogDataApp extends JFrame implements ActionListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    private JogList jogList = new JogList();
    private String val = "[jog id, distance, time, avg speed, avg heart rate, date]";

    private static final String JSON_STORE = "./data/joglist.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    //frames
    private JFrame frame = new JFrame();
    private JFrame addFrame = new JFrame();
    private JFrame removeFrame = new JFrame();

    //labels
    private JLabel bgLabel = new JLabel();
    private JLabel txtLabel = new JLabel();


    //buttons
    private JButton addButton = new JButton();
    private JButton removeButton = new JButton();
    private JButton saveButton = new JButton();
    private JButton loadButton = new JButton();
    private JButton addSubmitButton;
    private JButton removeSubmitButton;



    //text fields
    private JTextField id = new JTextField("<jog id>");
    private JTextField distance = new JTextField("<distance ran (metres)>");
    private JTextField time = new JTextField("<time (mins)>");
    private JTextField speed = new JTextField("<avg speed (m/s)>");
    private JTextField heartRate = new JTextField("<avg heart rate (bpm)>");
    private JTextField jogDate = new JTextField("<jog date (month:date)>");
    private JTextField removeId;
    private JTextArea displayJog = new JTextArea("[jog id, distance, time, avg speed, avg heart rate, date]");






    //EFFECTS: runs the jog data app
    public JogDataApp() {
        super("Jog Data App");
        makeBackground();
        setButtons();
        makeTextFrame();
        ImageIcon image = new ImageIcon("JogImage.jpg");
        displayImage(image);
        makeAddFrame();
        makeRemoveFrame();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
                super.windowClosing(e);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                printLog();
                super.windowClosed(e);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: creates the text area in the top left
    private void makeTextFrame() {
        add(txtLabel);
        txtLabel.add(displayJog);
        displayJog.setBounds(0,0, WIDTH / 2, HEIGHT / 2);
        displayJog.setBackground(new Color(100, 177, 73));

    }

    //MODIFIES: this
    //EFFECTS: displays the image in the frame
    private void displayImage(ImageIcon image) {
        add(bgLabel);
        bgLabel.setBounds(WIDTH / 2, 0, WIDTH / 3, HEIGHT / 2);
        bgLabel.setIcon(image);
    }

    ////MODIFIES: this
    //EFFECTS: initializes the main 4 buttons
    private void setButtons() {
        addButton.setBounds(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 4);
        add(addButton);
        addButton.setText("add jog");
        addButton.addActionListener(this);
        addButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());


        removeButton.setBounds(0, (3 * HEIGHT) / 4, WIDTH / 2, HEIGHT / 4);
        add(removeButton);
        removeButton.setText("remove jog");
        removeButton.addActionListener(this);
        removeButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        saveButton.setBounds(WIDTH / 2, HEIGHT / 2,WIDTH / 2,HEIGHT / 4);
        add(saveButton);
        saveButton.setText("save data");
        saveButton.addActionListener(this);
        saveButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        loadButton.setBounds(WIDTH / 2, (3 * HEIGHT) / 4,WIDTH / 2,HEIGHT / 4);
        add(loadButton);
        loadButton.setText("load data");
        loadButton.addActionListener(this);
        loadButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    }


    // MODIFIES: this
    // EFFECTS: sets up the attributes of the main frame
    private void makeBackground() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        getContentPane().setBackground(new Color(100, 177, 73));
    }

    //MODIFIES: this
    //EFFECTS: creates a frame that is not visible until add button is pressed
    private void makeAddFrame() {
        addFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addFrame.setVisible(false);
        addFrame.setLayout(new GridLayout(4,2));
        addFrame.setSize(300, 250);
        addFrame.add(id);
        addFrame.add(distance);
        addFrame.add(time);
        addFrame.add(speed);
        addFrame.add(heartRate);
        addFrame.add(jogDate);
        addSubmitButton = new JButton("Submit");
        addSubmitButton.addActionListener(this);
        addFrame.add(addSubmitButton);

    }

    //MODIFIES: this
    //EFFECTS: creates a frame which is not visible until remove button is clicked
    private void makeRemoveFrame() {
        removeFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        removeFrame.setVisible(false);
        removeFrame.setLayout(new GridLayout(2,1));
        removeFrame.setSize(200, 125);
        removeId = new JTextField("<jog id>");
        removeFrame.add(removeId);
        removeSubmitButton = new JButton("Submit");
        removeSubmitButton.addActionListener(this);
        removeFrame.add(removeSubmitButton);

    }

    // MODIFIES: this
    // EFFECTS: calls the functions when certain buttons are pressed
    @Override
   public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {
            addFrame.setVisible(true);
        } else if (event.getSource() == removeButton) {
            removeFrame.setVisible(true);
        } else if (event.getSource() == saveButton) {
            saveJogList();
        } else if (event.getSource() == loadButton) {
            loadJogList();
        } else if (event.getSource() == addSubmitButton) {
            doAddJog();
        } else if (event.getSource() == removeSubmitButton) {
            doRemoveJog();
        }

    }

    //EFFECTS: saves the JogList to file
    private void saveJogList() {
        try {
            jsonWriter.open();
            jsonWriter.write(jogList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved to " + JSON_STORE,
                    "Jog Data App", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                    "Jog Data App", JOptionPane.WARNING_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads JogList from file
    private void loadJogList() {
        try {
            jogList = jsonReader.read();
            updateTextArea();
            JOptionPane.showMessageDialog(null, "Loaded from " + JSON_STORE,
                    "Jog Data App", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Jog Data App", JOptionPane.WARNING_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets the values of jog and then adds it to jogList
    private void doAddJog() {
        int jogId = Integer.parseInt(id.getText());
        int distanceRan = Integer.parseInt(distance.getText());
        int timeTaken = Integer.parseInt(time.getText());
        Double avgSpeed = Double.valueOf(speed.getText());
        int avgHeartRate = Integer.parseInt(heartRate.getText());
        String dateOfJog = jogDate.getText();

        Jog jog = new Jog(jogId);
        jog.setDistanceRan(distanceRan);
        jog.setTimeTaken(timeTaken);
        jog.setAvgSpeed(avgSpeed);
        jog.setAvgHeartRate(avgHeartRate);
        jog.setDateOfJog(dateOfJog);

        jogList.addJog(jog);

        updateAddToTextArea(jog);

        addFrame.setVisible(false);
        setOrignal();
        JOptionPane.showMessageDialog(null, "successfully added",
                "Jog Data App", JOptionPane.INFORMATION_MESSAGE);

    }

    //MODIFIES: this
    //EFFECTS: sets the text field values to their orignal values
    private void setOrignal() {
        id.setText("<jog id>");
        distance.setText("<distance ran>");
        time.setText("<time (mins)>");
        speed.setText("<avg speed (m/s)>");
        heartRate.setText("<avg heart rate (bpm)>");
        jogDate.setText("<jog date (month:date)>");
    }

    //MODIFIES: this
    //EFFECTS: updates the value displayed in text area
    private void updateAddToTextArea(Jog jog) {
        ArrayList valList = new ArrayList();
        valList.add(jog.getJogId());
        valList.add(jog.getDistanceRan());
        valList.add(jog.getTimeTaken());
        valList.add(jog.getAvgSpeed());
        valList.add(jog.getAvgHeartRate());
        valList.add(jog.getDateOfJog());

        String temp = displayJog.getText();
        displayJog.setText(temp + "\n" + valList);

    }

    //MODIFIES: this
    //EFFECTS: removes the jog of specified jogId
    private void doRemoveJog() {
        int jogId = Integer.parseInt(removeId.getText());

        jogList.removeJog(jogId);
        updateTextArea();


        removeFrame.setVisible(false);
        removeId.setText("<jog id>");
        JOptionPane.showMessageDialog(null, "successfully removed",
                "Jog Data App", JOptionPane.INFORMATION_MESSAGE);

    }

    //MODIFIES: this
    //EFFECTS: updates value displayed in text area
    private void updateTextArea() {
        String temp1 = val;

        ArrayList<Jog> temp2 = jogList.getJogList();
        for (Jog jog : temp2) {
            ArrayList temp3 = doSearchJog(jog);
            temp1 = temp1 + "\n" + temp3;
        }
        displayJog.setText(temp1);
    }

    //EFFECTS: searches for the Jog of specified JogId and returns its value as a list
    private ArrayList doSearchJog(Jog j) {
        int jogId = j.getJogId();

        Jog jog = jogList.searchJog(jogId);

        ArrayList valList = new ArrayList();
        valList.add(jog.getJogId());
        valList.add(jog.getDistanceRan());
        valList.add(jog.getTimeTaken());
        valList.add(jog.getAvgSpeed());
        valList.add(jog.getAvgHeartRate());
        valList.add(jog.getDateOfJog());

        return valList;
    }

    public void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }
}
