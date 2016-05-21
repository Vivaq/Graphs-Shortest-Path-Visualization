import java.util.ArrayList;
import java.util.PriorityQueue;

public class EtiquetteAlgorithm{
    private ArrayList<Node> nodes;
    private PriorityQueue<Integer> edgesQueue = new PriorityQueue<>();
    private double[] shortestPaths;
    private int nodesQuantity;

    public EtiquetteAlgorithm(){
        this.shortestPaths = PathsWeb.shortestPaths;
        this.nodesQuantity = GraphWindow.getNodesQuantity();
        nodes = GraphWindow.getNodes();
    }

    public String findPathAToB(int start, int end)
    {
        shortestPaths = new double[nodesQuantity + 1];
        int[] ancestor = new int[nodesQuantity + 1];
        for (int i = 1; i <= nodesQuantity; i++)
        {
            shortestPaths[i] = Double.POSITIVE_INFINITY;
            ancestor[i] = 0;
            nodes.get(i).setChosen(false);
        }
        shortestPaths[start] = 0;

        for (int i = 0; i < nodesQuantity - 1; i++)
        {
            edgesQueue.add(start);
            nodes.get(start).setChosen(true);
            do
            {
                int chosen = edgesQueue.poll();
                for (int j : PathsWeb.neighboursTab.get(chosen))
                {
                    if (PathsWeb.tabForEtiquetteAlgorithm[chosen][j] == 0) continue;
                    if (shortestPaths[chosen] + PathsWeb.tabForEtiquetteAlgorithm[chosen][j] <
                    shortestPaths[j])
                    {
                        shortestPaths[j] = shortestPaths[chosen] +
                                PathsWeb.tabForEtiquetteAlgorithm[chosen][j];
                        ancestor[j] = chosen;
                    }
                    if (!nodes.get(j).isChosen())
                    {
                        edgesQueue.add(j);
                        nodes.get(j).setChosen(true);
                    }
                }
            } while (edgesQueue.size() != 0); //size == Count??
            for (int k = 1; k <= nodesQuantity; k++)
            {
                nodes.get(k).setChosen(false);
            }
        }

        String path = String.valueOf(end);
        int find = end;
        while (ancestor[find] != 0)
        {
            path += Integer.toString(ancestor[find]);
            find = ancestor[find];
        }
        return path;
    }
}