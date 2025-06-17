package strata2D;
import Interface.BendaGeometri;

public abstract class Bangun2D implements BendaGeometri {
    public String nama; // Nama bangun 2D
    public Double luas; // Luas bangun 2D
    public Double keliling; // Keliling bangun 2D

    @Override
    public String getNama() {
        return nama;
    }

    public abstract Double hitungLuas();

    public abstract Double hitungKeliling();

}
