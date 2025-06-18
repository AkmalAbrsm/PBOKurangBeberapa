package strata3D;

import strata2D.*;
import Exception.ArgumentException;


public class LimasJajarGenjang extends JajarGenjang {

    // Properties specific to the pyramid
    public Double tinggiLimas;
    public Double volume;
    public Double luasPermukaan;

    public LimasJajarGenjang(Double alas, Double tinggiAlas, Double sisiMiring, Double tinggiLimas) throws ArgumentException {
        // Initialize the base shape by calling the parent constructor
        super(alas, tinggiAlas, sisiMiring);

        // Validate the pyramid's own dimension
        if (tinggiLimas <= 0) {
            throw new ArgumentException("Tinggi limas harus lebih besar dari nol");
        }

        // Initialize the pyramid's properties
        this.tinggiLimas = tinggiLimas;
        this.nama = "Limas Jajar Genjang"; // Override the name from the parent

        // Calculate volume and surface area upon object creation, following the pattern
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }


    public Double hitungVolume() {
        // super.luas holds the area of the JajarGenjang base, calculated in the parent constructor
        volume = (1.0 / 3.0) * super.luas * this.tinggiLimas;
        return volume;
    }


    public Double hitungLuasPermukaan() {
        // WARNING: This calculation assumes a right pyramid and requires calculating slant heights.
        // Slant height for faces with base 'alas':
        double slantHeight1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.tinggi / 2, 2));
        double luasSisiTegak1 = super.alas * slantHeight1; // Area for two opposing faces

        // To find the second slant height, we need the perpendicular distance to the 'sisiMiring' edge.
        // This is not directly available, but we can approximate it for this example.
        // This part remains complex for a general parallelogram.
        // For simplicity, we assume a rhombus-like structure for the second pair of slant heights.
        double someOtherWidth = super.luas / super.sisiMiring;
        double slantHeight2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(someOtherWidth / 2, 2));
        double luasSisiTegak2 = super.sisiMiring * slantHeight2; // Area for the other two faces

        // super.luas is the area of the JajarGenjang base
        luasPermukaan = super.luas + luasSisiTegak1 + luasSisiTegak2;
        return luasPermukaan;
    }


    public Double hitungVolume(Double tinggiLimasBaru) throws ArgumentException {
        if (tinggiLimasBaru <= 0) {
            throw new ArgumentException("Tinggi limas baru harus > 0");
        }
        volume = (1.0 / 3.0) * super.luas * tinggiLimasBaru;
        return volume;
    }


    @Override
    public String getNama() {
        return this.nama;
    }
}