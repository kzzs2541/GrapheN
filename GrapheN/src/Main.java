import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });

        Graph graf = LoadCSRRG.loadGraph("/home/kzzs/Desktop/Graf/git@github.com:kzzs2541/GrafC_JIMP2.git/graphs/graf.csrrg");

        // Przykład: Wyświetl wierzchołki i ich sąsiadów
        for (Vertices w : graf.getWierzcholki().values()) {
            System.out.println(w + " -> Sąsiedzi: " + w.getNeighbors());
        }
    }
}