package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class Balok extends PersegiPanjang {
    private Double tinggi;
    private Double volume, luasPermukaan;


    public Balok(Double panjang, Double lebar, Double tinggi) throws ArgumentException {
        super(panjang, lebar);
        if (tinggi <= 0) {
            throw new ArgumentException("Tinggi harus > 0");
        }
        this.tinggi = tinggi;
        this.nama = "Balok";
    }

    public Double hitungVolume() {
        volume = super.luas * this.tinggi;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = 2 * (super.panjang * super.lebar + super.panjang * this.tinggi + super.lebar * this.tinggi);
        return luasPermukaan;
    }

    public Double hitungLuasPermukaan(Double panjangbaru, Double lebarbaru, Double tinggibaru) throws ArgumentException {
        if (panjangbaru <= 0 || lebarbaru <= 0 || tinggibaru <= 0) {
            throw new ArgumentException("Panjang, lebar, dan tinggi harus lebih besar dari nol");
        }
        luasPermukaan = 2 * (panjangbaru * lebarbaru + panjangbaru * tinggibaru + lebarbaru * tinggibaru);
        return  luasPermukaan;
    }

    public Double hitungVolume(Double panjangbaru, Double lebarbaru, Double tinggibaru) throws ArgumentException {
        if (panjangbaru <= 0 || lebarbaru <= 0 || tinggibaru <= 0) {
            throw new ArgumentException("Panjang, lebar, dan tinggi harus lebih besar dari nol");
        }
        volume = panjangbaru * lebarbaru * tinggibaru;
        return volume;
    }

    @Override
    public String getNama() {
        return nama;
    }
}

