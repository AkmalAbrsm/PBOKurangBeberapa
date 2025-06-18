package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class Kerucut extends Lingkaran {
    public Double tinggi;
    public Double volume; // Remove initialization
    public Double luasPermukaan; // Remove initialization
    public String nama;

    public Kerucut(Double jariJari, Double tinggi) throws ArgumentException {
        super(jariJari);
        if (tinggi <= 0) {
            throw new ArgumentException("Tinggi harus lebih besar dari nol");
        }
        this.tinggi = tinggi;
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
        luasPermukaan = super.luas + Math.PI * jariJari * Math.sqrt((jariJari * jariJari) + (tinggi * tinggi));
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

    public Double hitungLuasPermukaan(Double jariBaru, Double tinggiBaru) throws ArgumentException {
        if (jariBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Jari-jari dan garis pelukis harus lebih besar dari nol");
        }
        Double luasBaru = Math.PI * jariBaru * jariBaru;
        luasPermukaan = luasBaru + Math.PI * jariBaru * Math.sqrt((jariBaru * jariBaru) + (tinggiBaru * tinggiBaru));
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}