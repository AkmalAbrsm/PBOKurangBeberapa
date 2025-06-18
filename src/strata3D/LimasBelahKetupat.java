package strata3D;
import Exception.ArgumentException;

import strata2D.*;

public class LimasBelahKetupat extends BelahKetupat {
    private Double tinggi;
    protected Double volume, luasPermukaan;

    public LimasBelahKetupat(Double d1, Double d2, Double sisi, Double tinggi) throws ArgumentException {
        super(d1, d2, sisi);
        if (tinggi <= 0) {
            throw new ArgumentException("Tinggi harus > 0");
        }
        this.tinggi = tinggi;
        this.nama = "Limas Belah Ketupat";
    }

    public Double hitungVolume() {
        volume = (super.hitungLuas() * tinggi) / 3;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = super.hitungLuas() + (4 * ((sisi * tinggi) / 2));
        return luasPermukaan;
    }

    public Double hitungLuasPermukaan(Double d1baru, Double d2baru, Double sisibaru, Double tinggibaru) {
        if (d1baru <= 0 || d2baru <= 0 || sisibaru <= 0 || tinggibaru <= 0) {
            throw new IllegalArgumentException("Diagonal, sisi, dan tinggi harus lebih besar dari nol");
        }
        luasPermukaan = (d1baru * d2baru / 2) + (4 * ((sisibaru * tinggibaru) / 2));
        return luasPermukaan;
    }

    public Double hitungVolume(Double d1baru, Double d2baru, Double sisibaru, Double tinggibaru) {
        if (d1baru <= 0 || d2baru <= 0 || sisibaru <= 0 || tinggibaru <= 0) {
            throw new IllegalArgumentException("Diagonal, sisi, dan tinggi harus lebih besar dari nol");
        }
        volume = (d1baru * d2baru / 2 * tinggibaru) / 3;
        return volume;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
