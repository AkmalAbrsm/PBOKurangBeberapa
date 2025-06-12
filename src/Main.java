import strata2D.*;
import strata3D.*;
import Interface.BendaGeometri;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // This is the main entry point of the application.
        // You can create instances of geometric shapes here and perform operations on them.
        // Example usage;
        try {
            Bola bola = new Bola(21.0); // Create a new Kubus with side length 4.0
            System.out.println("Nama: " + bola.getNama());
            System.out.println("Luas Permukaan : " + bola.hitungLuasPermukaan());
            System.out.println("Volume : " + bola.hitungVolume());
            System.out.println("Volume Baru : " + bola.hitungVolume(10.0));

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}