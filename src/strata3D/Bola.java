package strata3D;

import strata2D.*;

public class Bola extends Lingkaran {
    private Double volume;
    private Double luasPermukaan;
    private Double diameter;

    public Bola(Double jariJari) {
        super(jariJari);
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.nama = "strata3D.Bola";
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
        this.diameter = hitungDiameter();
    }

    public Double hitungVolume() {
        volume = (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = 4 * Math.PI * Math.pow(jariJari, 2);
        return luasPermukaan;
    }

    public Double hitungDiameter() {
        diameter = 2 * jariJari;
        return diameter;
    }

    public Double hitungVolume(Double jariBaru) {
        return (4.0 / 3.0) * Math.PI * Math.pow(jariBaru, 3);
    }

    public Double hitungLuasPermukaan(Double jariBaru) {
        return 4 * Math.PI * Math.pow(jariBaru, 2);
    }

    public Double hitungDiameter(Double jariBaru) {
        return 2 * jariBaru;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
