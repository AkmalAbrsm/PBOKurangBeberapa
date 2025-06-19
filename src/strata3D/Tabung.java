package strata3D;

import strata2D.Lingkaran;
import Exception.ArgumentException;

public class Tabung extends Lingkaran {
    private Double tinggi;
    private Double volume;
    private Double luasPermukaan;

    public Tabung(Double jariJari, Double tinggi) throws ArgumentException {
        super(jariJari);
        if (tinggi <= 0) {
            throw new ArgumentException("Tinggi tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.tinggi = tinggi;
        this.nama = "Tabung";
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    public Double hitungVolume() {
        volume = super.hitungLuas() * tinggi;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        // luas permukaan tabung = 2πr² (alas & tutup) + 2πrt (selimut)
        luasPermukaan = 2 * super.luas + 2 * Math.PI * jariJari * tinggi;
        return luasPermukaan;
    }

    public Double hitungVolume(Double jariJariBaru, Double tinggiBaru) {
        volume = Math.PI * jariJariBaru * jariJariBaru * tinggiBaru;
        return volume;
    }

    public Double hitungLuasPermukaan(Double jariJariBaru, Double tinggiBaru) {
        volume =  2 * Math.PI * jariJariBaru * jariJariBaru + 2 * Math.PI * jariJariBaru * tinggiBaru;
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
