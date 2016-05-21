import java.awt.*;


public class Main{
    private static GraphWindow graphWindow;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                graphWindow = new GraphWindow();
            }
        });
    }
}
