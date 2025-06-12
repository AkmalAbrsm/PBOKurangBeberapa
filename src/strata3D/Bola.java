package strata3D;

import strata2D.*;

public class Bola extends Lingkaran {
    private Double volume;
    private Double luasPermukaan;

    public Bola(Double jariJari) {
        super(jariJari);
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.nama = "Bola";
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    public Double hitungVolume() {
        volume = (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = 4 * Math.PI * Math.pow(jariJari, 2);
        return luasPermukaan;
    }

    public Double hitungVolume(Double jariBaru) {
        volume = (4.0 / 3.0) * Math.PI * Math.pow(jariBaru, 3);
        return volume;
    }

    public Double hitungLuasPermukaan(Double jariBaru) {
        luasPermukaan = 4 * Math.PI * Math.pow(jariBaru, 2);
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
