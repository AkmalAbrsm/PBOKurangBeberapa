package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class LimasSegitiga extends Segitiga {
    private Double tinggi;
    private Double tinggiSisi; // slant height
    private Double volume, luasPermukaan;

    public LimasSegitiga(Double alas, Double tinggiAlas, Double tinggi, Double tinggiSisi) throws ArgumentException {
        super(alas, tinggiAlas, tinggi);
        if (tinggi <= 0 || tinggiSisi <= 0) {
            throw new ArgumentException("Tinggi dan tinggi sisi harus lebih besar dari nol");
        }
        this.tinggi = tinggi;
        this.tinggiSisi = tinggiSisi;
        this.nama = "Limas Segitiga";
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    public Double hitungVolume() {
        volume = (1.0 / 3.0) * super.luas * tinggi;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = super.luas + 0.5 * super.keliling * tinggiSisi;
        return luasPermukaan;
    }

    public Double hitungVolume(Double alasBaru, Double tinggiAlasBaru, Double tinggiBaru) throws ArgumentException {
        if (alasBaru <= 0 || tinggiAlasBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Alas, tinggi alas, dan tinggi harus lebih besar dari nol");
        }
        Double luasBaru = 0.5 * alasBaru * tinggiAlasBaru;
        volume = (1.0 / 3.0) * luasBaru * tinggiBaru;
        return volume;
    }

    public Double hitungLuasPermukaan(Double alasBaru, Double tinggiAlasBaru, Double kelilingBaru, Double tinggiSisiBaru) throws ArgumentException {
        if (alasBaru <= 0 || tinggiAlasBaru <= 0 || kelilingBaru <= 0 || tinggiSisiBaru <= 0) {
            throw new ArgumentException("Parameter harus lebih besar dari nol");
        }
        Double luasBaru = 0.5 * alasBaru * tinggiAlasBaru;
        luasPermukaan = luasBaru + 0.5 * kelilingBaru * tinggiSisiBaru;
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}