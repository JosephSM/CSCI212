/**
 * Created by joey on 12/8/16.
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        App myapp = new App();

                    }
                });

    }
}
