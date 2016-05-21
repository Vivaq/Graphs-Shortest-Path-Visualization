import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class GraphWindow extends JFrame {
    private static JPanel panel;
    private static Point calibration;
    private static int nodesQuantity;
    private static ArrayList<Node> nodes;
    private static ArrayList<Edge> edges;
    public GraphWindow() {
        super("Draw Nodes and Edges");

        nodes = new ArrayList<>();
        nodes.add(null);
        edges = new ArrayList<>();
        edges.add(null);

        PathsWeb.neighboursTab.add(new ArrayList<>());
        PathsWeb.edgesTab.add(new Vector<>());

        setLayout(new GridLayout());
        setPreferredSize(new Dimension(900, 600));

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                calibration = panel.getLocationOnScreen();
                JButton cButton = new Node(e.getLocationOnScreen(), nodesQuantity + 1);
                panel.add(cButton);
                nodesQuantity++;
                nodes.add(new Node());
                PathsWeb.neighboursTab.add(new ArrayList<>());
                for(int i = 0; i < nodesQuantity; i++)
                    PathsWeb.edgesTab.get(i).add(null);
                PathsWeb.edgesTab.add(new Vector<>(Collections.nCopies(nodesQuantity + 1, null)));
                validate();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

            }
        });
        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static int getNodesQuantity(){
        return nodesQuantity;
    }

    public static ArrayList<Node> getNodes(){
        return nodes;
    }
    public static ArrayList<Edge> getEdges(){
        return edges;
    }
    public static void addEdge(Edge edge){
        edges.add(edge);
        panel.add(edge);
    }
    public static Point getCal(){
        return calibration;
    }
    public static JPanel getPanel(){
        return panel;
    }
}

