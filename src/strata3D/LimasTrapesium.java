package strata3D;

import strata2D.*;
import Exception.ArgumentException;


public class LimasTrapesium extends Trapesium {

    // Properties specific to the pyramid
    public Double tinggiLimas;
    public Double volume;
    public Double luasPermukaan;


    public LimasTrapesium(Double a, Double b, Double tinggiAlas, Double sisi1, Double sisi2, Double tinggiLimas) throws ArgumentException {
        // Initialize the base shape by calling the parent constructor.
        // This calculates the base's area (luas) and perimeter (keliling).
        super(a, b, tinggiAlas, sisi1, sisi2);

        // Validate the pyramid's own dimension
        if (tinggiLimas <= 0) {
            throw new ArgumentException("Tinggi limas harus lebih besar dari nol");
        }

        // Initialize the pyramid's properties
        this.tinggiLimas = tinggiLimas;
        this.nama = "Limas Trapesium"; // Override the name from the parent

        // Calculate volume and surface area upon creation, following the pattern
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }


    public Double hitungVolume() {
        // super.luas holds the area of the Trapesium base
        this.volume = (1.0 / 3.0) * super.luas * this.tinggiLimas;
        return this.volume;
    }


    public Double hitungLuasPermukaan() {
        // The lateral area consists of 4 triangles with bases a, b, sisi1, and sisi2.
        // Calculating their true heights (slant heights) is complex.
        // We will approximate by calculating an average slant height.
        double avgSlantHeight = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(super.tinggi / 2.0, 2));

        // Approximate the lateral area using the base perimeter and average slant height
        double lateralArea = (super.keliling * avgSlantHeight) / 2.0;

        // Total surface area is base area + lateral area
        this.luasPermukaan = super.luas + lateralArea;
        return this.luasPermukaan;
    }


    public Double hitungVolume(Double tinggiLimasBaru) throws ArgumentException {
        if (tinggiLimasBaru <= 0) {
            throw new ArgumentException("Tinggi limas baru harus > 0");
        }
        return (1.0 / 3.0) * super.luas * tinggiLimasBaru;
    }


    @Override
    public String getNama() {
        return this.nama;
    }
}