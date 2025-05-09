import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GraphVisualizerPanel extends JPanel {
    JLabel fileLabel;
    JButton submit = new JButton();
    File selectedFile;
    public GraphVisualizerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Graph Visualizer"));

        // Przycisk wyboru pliku
        JButton fileChooserButton = new JButton("Select Input File");
        fileChooserButton.setFocusable(false);
        fileLabel = new JLabel("No file selected");
        fileChooserButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileLabel.setText(selectedFile.getName());
                submit.setEnabled(true);
            }
        });

        submit = new JButton();
        submit.setText("Submit");
        submit.setFocusable(false);
        submit.setEnabled(false);
        submit.addActionListener(e -> printOptions());

        add(fileChooserButton);
        add(fileLabel);
        add(submit);
    }
    private void printOptions(){
        String chosenFile = selectedFile.getAbsolutePath();

        System.out.println("Wybrany plik do wizualizacji: " + chosenFile);
    }
}