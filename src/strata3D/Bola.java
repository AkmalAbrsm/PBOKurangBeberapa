package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class Bola extends Lingkaran {
    private Double volume;
    private Double luasPermukaan;

    public Bola(Double jariJari) throws ArgumentException {
        super(jariJari);
        if (jariJari <= 0) {
            throw new ArgumentException("Jari-jari tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.nama = "Bola";

    }

    public Double hitungVolume() {
        volume = (4.0 / 3.0) * jariJari * super.luas;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = 4 * super.luas;
        return luasPermukaan;
    }

    public Double hitungVolume(Double jariBaru) {
        volume = (4.0 / 3.0) * jariBaru * super.hitungLuas(jariBaru);
        return volume;
    }

    public Double hitungLuasPermukaan(Double jariBaru) {
        luasPermukaan = 4 * super.hitungLuas(jariBaru);
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
