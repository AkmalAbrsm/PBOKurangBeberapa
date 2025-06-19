import strata2D.*;
import strata3D.*;
import Exception.ArgumentException;
import Interface.BendaGeometri;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Start the KalkulatorGUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            KalkulatorGUI gui = new KalkulatorGUI();
            gui.setVisible(true);
        });
    }
}