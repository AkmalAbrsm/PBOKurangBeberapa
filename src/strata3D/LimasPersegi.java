package strata3D;

import strata2D.*;
import Exception.ArgumentException;


public class LimasPersegi extends Persegi {

    // Properties specific to the pyramid
    public Double tinggiLimas;
    public Double volume;
    public Double luasPermukaan;


    public LimasPersegi(Double sisi, Double tinggiLimas) throws ArgumentException {
        // Initialize the square base by calling the parent constructor.
        // This also calculates the base's area (luas) and perimeter (keliling).
        super(sisi);

        // Validate the pyramid's height
        if (tinggiLimas <= 0) {
            throw new ArgumentException("Tinggi limas tidak boleh kosong dan harus lebih besar dari nol");
        }

        // Initialize the pyramid's specific properties
        this.tinggiLimas = tinggiLimas;
        this.nama = "Limas Persegi"; // Override the name from the parent class

        // Calculate volume and surface area upon creation, following the parent's pattern
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }


    public Double hitungVolume() {
        // super.luas holds the area of the Persegi base, calculated in the parent constructor
        this.volume = (1.0 / 3.0) * super.luas * this.tinggiLimas;
        return this.volume;
    }


    public Double hitungLuasPermukaan() {
        // Calculate the slant height of the triangular faces using the Pythagorean theorem
        double setengahSisi = super.sisi / 2.0;
        double tinggiSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(setengahSisi, 2));

        // Calculate the area of the four triangular faces
        double luasSisiTegak = 4 * (0.5 * super.sisi * tinggiSisiTegak);

        // Surface Area = Base Area + Area of all triangular faces
        this.luasPermukaan = super.luas + luasSisiTegak;
        return this.luasPermukaan;
    }


    public Double hitungVolume(Double tinggiLimasBaru) throws ArgumentException {
        if (tinggiLimasBaru <= 0) {
            throw new ArgumentException("Tinggi limas baru harus > 0");
        }
        return (1.0 / 3.0) * super.luas * tinggiLimasBaru;
    }


    public Double hitungLuasPermukaan(Double sisiBaru, Double tinggiLimasBaru) throws ArgumentException {
        if (sisiBaru <= 0 || tinggiLimasBaru <= 0) {
            throw new ArgumentException("Sisi dan tinggi limas baru harus > 0");
        }
        double luasAlasBaru = sisiBaru * sisiBaru;
        double setengahSisiBaru = sisiBaru / 2.0;
        double tinggiSisiTegakBaru = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(setengahSisiBaru, 2));
        double luasSisiTegakBaru = 4 * (0.5 * sisiBaru * tinggiSisiTegakBaru);
        return luasAlasBaru + luasSisiTegakBaru;
    }



    @Override
    public String getNama() {
        return this.nama;
    }
}