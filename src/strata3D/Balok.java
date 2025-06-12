package strata3D;

import strata2D.*;

public class Balok extends PersegiPanjang {
    private Double tinggi;

    public Balok(Double panjang, Double lebar, Double tinggi) {
        super(panjang, lebar);
        if (tinggi <= 0) throw new IllegalArgumentException("Tinggi harus > 0");
        this.tinggi = tinggi;
        this.nama = "strata3D.Balok";
    }

    public Double hitungVolume() {
        return panjang * lebar * tinggi;
    }

    public Double hitungLuasPermukaan() {
        return 2 * ((panjang * lebar) + (panjang * tinggi) + (lebar * tinggi));
    }
}

