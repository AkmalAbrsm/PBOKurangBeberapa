package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class Kerucut extends Lingkaran {
    public Double tinggi;
    public Double garisPelukis;
    public Double volume; // Remove initialization
    public Double luasPermukaan; // Remove initialization
    public String nama;

    public Kerucut(Double jariJari, Double tinggi, Double garisPelukis) throws ArgumentException {
        super(jariJari);
        if (tinggi <= 0 || garisPelukis <= 0) {
            throw new ArgumentException("Tinggi dan garis pelukis harus lebih besar dari nol");
        }
        this.tinggi = tinggi;
        this.garisPelukis = garisPelukis;
        this.nama = "Kerucut";

        // --- FIX: REMOVE THESE LINES ---
        // this.volume = hitungVolume();
        // this.luasPermukaan = hitungLuasPermukaan();
    }

    public Double hitungVolume() {
        volume = (1.0 / 3.0) * super.luas * tinggi;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = super.luas + Math.PI * jariJari * garisPelukis;
        return luasPermukaan;
    }

    // ... other methods remain the same ...
    public Double hitungVolume(Double jariBaru, Double tinggiBaru) throws ArgumentException {
        if (jariBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Jari-jari dan tinggi harus lebih besar dari nol");
        }
        Double luasBaru = Math.PI * jariBaru * jariBaru;
        volume = (1.0 / 3.0) * luasBaru * tinggiBaru;
        return volume;
    }

    public Double hitungLuasPermukaan(Double jariBaru, Double garisPelukisBaru) throws ArgumentException {
        if (jariBaru <= 0 || garisPelukisBaru <= 0) {
            throw new ArgumentException("Jari-jari dan garis pelukis harus lebih besar dari nol");
        }
        Double luasBaru = Math.PI * jariBaru * jariBaru;
        luasPermukaan = luasBaru + Math.PI * jariBaru * garisPelukisBaru;
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}