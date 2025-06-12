package strata3D;

import strata2D.*;

public class LimasBelahKetupat extends BelahKetupat {
    private Double tinggi;

    public LimasBelahKetupat(Double d1, Double d2, Double sisi, Double tinggi) {
        super(d1, d2, sisi);
        if (tinggi <= 0) throw new IllegalArgumentException("Tinggi harus > 0");
        this.tinggi = tinggi;
        this.nama = "strata3D.LimasBelahKetupat";
    }

    public Double hitungVolume() {
        return (super.hitungLuas() * tinggi) / 3;
    }

    public Double hitungLuasPermukaan() {
        return super.hitungLuas() + (4 * ((sisi * tinggi) / 2));
    }
}
