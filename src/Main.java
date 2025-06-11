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
            Kubus kubus = new Kubus(9.0); // Create a new Kubus with side length 4.0
            System.out.println("Nama: " + kubus.getNama());
            System.out.println("Luas: " + kubus.hitungLuasPermukaan());
            System.out.println("Keliling : " + kubus.hitungPanjangRusuk());
            System.out.println("Volume : " + kubus.hitungVolume());
            System.out.println("Volume Baru : " + kubus.hitungVolume(10.0));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}