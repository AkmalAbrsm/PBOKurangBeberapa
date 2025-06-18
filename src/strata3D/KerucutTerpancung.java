package strata3D;

import Exception.ArgumentException;

public class KerucutTerpancung extends Kerucut {

    public Double jariJariAtas;
    public Double tinggiKerucutTerpancung;
    public Double garisPelukisKerucutTerpancung;

    public KerucutTerpancung(Double jariJariBawah, Double jariJariAtas, Double tinggi) throws ArgumentException {
        // Call to super() remains the same
        super(
                jariJariBawah,
                (tinggi * jariJariBawah) / (jariJariBawah - jariJariAtas),
                (Math.sqrt(Math.pow(tinggi, 2) + Math.pow(jariJariBawah - jariJariAtas, 2)) * jariJariBawah) / (jariJariBawah - jariJariAtas)
        );

        if (tinggi <= 0 || jariJariAtas <= 0 || jariJariBawah <= jariJariAtas) {
            throw new ArgumentException("Dimensi tidak valid. Pastikan tinggi > 0, jari-jari > 0, dan jariJariBawah > jariJariAtas.");
        }

        // Initialize the fields for this subclass
        this.jariJariAtas = jariJariAtas;
        this.tinggiKerucutTerpancung = tinggi;
        this.garisPelukisKerucutTerpancung = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(jariJariBawah - jariJariAtas, 2));
        this.nama = "Kerucut Terpancung";

        // --- FIX: CALCULATE THE VOLUME & AREA HERE ---
        // Now it's safe to call the methods because all fields are initialized.
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // The rest of the KerucutTerpancung class remains the same
    @Override
    public Double hitungVolume() {
        Double r = this.jariJariAtas;
        Double R = super.jariJari;
        Double t = this.tinggiKerucutTerpancung;

        volume = (1.0 / 3.0) * Math.PI * t * (Math.pow(R, 2) + R * r + Math.pow(r, 2));
        return volume;
    }

    @Override
    public Double hitungLuasPermukaan() {
        Double r = this.jariJariAtas;
        Double R = super.jariJari;
        Double s = this.garisPelukisKerucutTerpancung;

        Double luasAlasAtas = Math.PI * r * r;
        Double luasAlasBawah = super.luas;
        Double luasSelimut = Math.PI * (R + r) * s;

        luasPermukaan = luasAlasAtas + luasAlasBawah + luasSelimut;
        return luasPermukaan;
    }

}