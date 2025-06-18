package strata3D;

import strata2D.*;
import Exception.ArgumentException;


public class PrismaBelahKetupat extends BelahKetupat {

    // Properties specific to the prism
    public Double tinggiPrisma;
    public Double volume;
    public Double luasPermukaan;


    public PrismaBelahKetupat(Double d1, Double d2, Double sisi, Double tinggiPrisma) throws ArgumentException {
        // Initialize the base shape by calling the parent constructor.
        // This also calculates the base's area (luas) and perimeter (keliling).
        super(d1, d2, sisi);

        // Validate the prism's own dimension
        if (tinggiPrisma <= 0) {
            throw new ArgumentException("Tinggi prisma harus lebih besar dari nol");
        }

        // Initialize the prism's properties
        this.tinggiPrisma = tinggiPrisma;
        this.nama = "Prisma Belah Ketupat"; // Override the name from the parent

        // Calculate volume and surface area upon object creation, following the pattern
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }


    public Double hitungVolume() {
        // super.luas holds the area of the BelahKetupat base
        this.volume = super.luas * this.tinggiPrisma;
        return this.volume;
    }


    public Double hitungLuasPermukaan() {
        double luasAlasGanda = 2 * super.luas;
        double luasSelubung = super.keliling * this.tinggiPrisma;
        this.luasPermukaan = luasAlasGanda + luasSelubung;
        return this.luasPermukaan;
    }


    public Double hitungVolume(Double tinggiPrismaBaru) throws ArgumentException {
        if (tinggiPrismaBaru <= 0) {
            throw new ArgumentException("Tinggi prisma baru harus > 0");
        }
        return super.luas * tinggiPrismaBaru;
    }


    public Double hitungLuasPermukaan(Double d1Baru, Double d2Baru, Double sisiBaru, Double tinggiPrismaBaru) throws ArgumentException {
        if (d1Baru <= 0 || d2Baru <= 0 || sisiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new ArgumentException("Semua dimensi baru harus > 0");
        }
        double luasAlasBaru = (d1Baru * d2Baru) / 2;
        double kelilingAlasBaru = 4 * sisiBaru;
        return (2 * luasAlasBaru) + (kelilingAlasBaru * tinggiPrismaBaru);
    }


    @Override
    public String getNama() {
        return this.nama;
    }
}