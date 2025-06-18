import strata2D.*;
import strata3D.*;
import Exception.ArgumentException;
import Interface.BendaGeometri;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // This is the main entry point of the application.
        // You can create instances of geometric shapes here and perform operations on them.
        // Example usage;
        try {
            PrismaBelahKetupat kerucutTerpancung = new PrismaBelahKetupat(4.0, 3.0, 2.0, 5.0); // Create a new Kubus with side length 4.0
            System.out.println("Nama : " + kerucutTerpancung.getNama());
            System.out.println("Luas : " + kerucutTerpancung.hitungVolume());
            System.out.println("Volume : " + kerucutTerpancung.hitungLuasPermukaan());


        } catch (ArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}