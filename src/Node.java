import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class Node extends JButton{
    public static int BUTTON_SIZE = 30;
    private boolean chosen;
    //public boolean isChosenToPath;
    //public boolean isChosenToShortestPath;
    private static Node clickedButton;
    private Point location;
    private int id;

    public Node(){}

    public Node(Point location, int id) {
        this.id = id;
        this.location = location;
        repairLocation();
        setContentAreaFilled(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                addEdge();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateLocation(e.getLocationOnScreen());
                GraphWindow.getPanel().setFocusable(false);
                clickedButton = null;
            }
            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    public boolean isChosen(){
        return chosen;
    }

    public void setChosen(boolean c){
        chosen = c;
    }

    private  void addEdge(){
        if (clickedButton != null) {
            int _id = clickedButton.id;
            int weight;
            try{
                weight = Integer.parseInt(JOptionPane.showInputDialog("Enter Capacity"));
            }
            catch(NumberFormatException ex) {
                System.out.println("Nie podales kosztu");
                clickedButton = null;
                return;
            }
            PathsWeb.neighboursTab.get(id).add(_id);
            PathsWeb.neighboursTab.get(_id).add(id);
            PathsWeb.tabForEtiquetteAlgorithm[id][_id] = PathsWeb.tabForEtiquetteAlgorithm[_id][id] = weight;

            Edge edge = new Edge(weight, this, clickedButton);
            GraphWindow.addEdge(edge);
            PathsWeb.edgesTab.get(id).set(clickedButton.id, edge);
            PathsWeb.edgesTab.get(clickedButton.id).set(id, edge);

            EtiquetteAlgorithm ea = new EtiquetteAlgorithm();
            String[] path = ea.findPathAToB(1, 2).split("");

            GraphWindow.getEdges().stream().filter(e -> e != null).forEach(e -> e.ChooseToShortest(false));
            for(int i = 0; i < path.length - 1; i++){
                PathsWeb.edgesTab.get(Integer.parseInt(path[i])).get(Integer.parseInt(path[i + 1])).ChooseToShortest(true);
            }
            clickedButton = null;
        } else {
            clickedButton = this;
        }
    }

    private void updateLocation(Point xy) {
        this.location = xy;
        repairLocation();
        repaint();
    }

    private void repairLocation(){
        this.location.x -= GraphWindow.getCal().getX();
        this.location.y -= GraphWindow.getCal().getY();
    }

    public int getXPos(){
        return this.location.x;
    }

    public int getYPos(){
        return this.location.y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(5));
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        if (id == GraphWindow.getNodesQuantity())
            GraphWindow.getEdges().stream().filter(e -> e != null).forEach(e -> e.paintComponent(g));
        g.setColor(Color.BLACK);
        if (getModel().isRollover()) {
            g.setColor(Color.LIGHT_GRAY);
        }
        if(getModel().isArmed()) {
            g.setColor(Color.RED);
        }
        g.fillOval(location.x - BUTTON_SIZE / 2, location.y - BUTTON_SIZE / 2, BUTTON_SIZE, BUTTON_SIZE);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(id), location.x - 5, location.y + 5);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new Ellipse2D.Float(location.x - BUTTON_SIZE / 2, location.y - BUTTON_SIZE / 2, BUTTON_SIZE, BUTTON_SIZE);
        return shape.contains(x, y);
    }

    @Override
    public void paintBorder(Graphics g) {}
}
