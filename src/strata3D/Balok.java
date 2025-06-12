package strata3D;

import strata2D.*;

public class Balok extends PersegiPanjang {
    private Double tinggi;
    private Double volume, luasPermukaan;


    public Balok(Double panjang, Double lebar, Double tinggi) {
        super(panjang, lebar);
        if (tinggi <= 0) throw new IllegalArgumentException("Tinggi harus > 0");
        this.tinggi = tinggi;
        this.nama = "Balok";
    }

    public Double hitungVolume() {
        volume = panjang * lebar * tinggi;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = 2 * (panjang * lebar + panjang * tinggi + lebar * tinggi);
        return luasPermukaan;
    }

    public Double hitungLuasPermukaan(Double panjangbaru, Double lebarbaru, Double tinggibaru) {
        if (panjangbaru <= 0 || lebarbaru <= 0 || tinggibaru <= 0) {
            throw new IllegalArgumentException("Panjang, lebar, dan tinggi harus lebih besar dari nol");
        }
        luasPermukaan = 2 * (panjangbaru * lebarbaru + panjangbaru * tinggibaru + lebarbaru * tinggibaru);
        return  luasPermukaan;
    }

    public Double hitungVolume(Double panjangbaru, Double lebarbaru, Double tinggibaru) {
        if (panjangbaru <= 0 || lebarbaru <= 0 || tinggibaru <= 0) {
            throw new IllegalArgumentException("Panjang, lebar, dan tinggi harus lebih besar dari nol");
        }
        volume = panjangbaru * lebarbaru * tinggibaru;
        return volume;
    }
}

