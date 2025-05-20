import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.Map;

public class GraphPanel extends JPanel {
    private static final int VERTEX_RADIUS = 20;
    private static final int EDGE_THICKNESS = 2;
    private final Map<Integer, Vertices> wierzcholki;
    private double scale = 1.0;  // Początkowe skalowanie (1.0 = 100%)
    private double translateX = 0;
    private double translateY = 0;
    private int lastMouseX, lastMouseY;


    public GraphPanel(Map<Integer, Vertices> wierzcholki) {
        this.wierzcholki = wierzcholki;
        setBackground(Color.WHITE);

        addMouseWheelListener(e -> {
            double zoomFactor = e.getWheelRotation() < 0 ? 1.1 : 0.9;  // 1.1 = przybliż, 0.9 = oddal
            scale = Math.max(0.5, Math.min(1.5, scale * zoomFactor));
            repaint();
        });

        // Nasłuchiwanie przeciągania myszą (panning)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseX = e.getX();
                lastMouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - lastMouseX;
                int dy = e.getY() - lastMouseY;
                translateX += dx;
                translateY += dy;
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform transform = new AffineTransform();
        transform.translate(translateX, translateY);
        transform.scale(scale, scale);
        g2d.setTransform(transform);

        // Rysuj krawędzie
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(EDGE_THICKNESS));
        for (Vertices w : wierzcholki.values()) {
            Point p1 = new Point(w.getX() * 50, w.getY() * 50);
            for (Vertices sasiad : w.getNeighbors()) {
                Point p2 = new Point(sasiad.getX() * 50, sasiad.getY() * 50);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        // Rysuj wierzchołki
        for (Vertices w : wierzcholki.values()) {
            // Kolor wierzchołka
            g2d.setColor(new Color(70, 130, 180));  // SteelBlue
            g2d.fillOval(w.getX() * 50 - VERTEX_RADIUS, w.getY() * 50 - VERTEX_RADIUS,
                    2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS);



            // Etykieta (ID wierzchołka)
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String label = String.valueOf(w.getId());
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();
            g2d.drawString(label,
                    w.getX() * 50 - labelWidth / 2,
                    w.getY() * 50 + labelHeight / 4);
        }
    }
}