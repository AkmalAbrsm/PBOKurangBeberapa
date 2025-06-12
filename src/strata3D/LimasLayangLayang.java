package strata3D;

import strata2D.*;

public class LimasLayangLayang extends LayangLayang {
    private Double tinggi;

    public LimasLayangLayang(Double d1, Double d2, Double sisiPendek, Double sisiPanjang, Double tinggi) {
        super(d1, d2, sisiPendek, sisiPanjang);
        if (tinggi <= 0) throw new IllegalArgumentException("Tinggi harus > 0");
        this.tinggi = tinggi;
    }

    public Double hitungVolume() {
        return (super.hitungLuas() * tinggi) / 3;
    }

    public Double hitungLuasPermukaan() {
        return super.hitungLuas() + 2 * ((sisiPanjang * tinggi) / 2) + 2 * ((sisiPendek * tinggi) / 2);
    }
}
