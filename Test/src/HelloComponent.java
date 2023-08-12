import javax.swing.*;
import java.awt.*;

public class HelloComponent extends JComponent {
    public void paintComponent (Graphics g) {
        g.drawString("Hello, Java!", 125, 95);
    }
}
