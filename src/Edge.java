import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Edge extends JButton{
    private int cost;
    
    private boolean isChosenToShortestPath;
    private Node[] edgeNodes = new Node[2];
    /* For simulated annealing (Future functionality)
    private int modules;
    public double flow;
    public double capacity;
    public boolean isChosen;
    public boolean isChosenToPath;
     
    public void Add(double cost)
    {
        if (modules == 0) modules++;
        this.flow += cost;
        while (this.flow > this.modules*this.capacity)
        {
            modules++;
        }
    }
     */

    public Edge(int cost, Node start, Node end)
    {
        this.cost = cost;
        edgeNodes[0] = start;
        edgeNodes[1] = end;
    }

    void ChooseToShortest(boolean ctsp){
        isChosenToShortestPath = ctsp;
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(cost), (edgeNodes[0].getXPos()+edgeNodes[1].getXPos())/2,
                                             (edgeNodes[0].getYPos()+edgeNodes[1].getYPos())/2
        );
        if(isChosenToShortestPath) {
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.black);
        }
        Graphics2D g2D = (Graphics2D) g;
        g2D.draw(new Line2D.Float(edgeNodes[0].getXPos(), edgeNodes[0].getYPos(), edgeNodes[1].getXPos(), edgeNodes[1].getYPos()));
    }
}
