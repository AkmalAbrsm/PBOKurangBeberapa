package strata3D;

import strata2D.*;
import Exception.ArgumentException;


public class PrismaLayangLayang extends LayangLayang {

    // Properties specific to the prism
    public Double tinggiPrisma;
    public Double volume;
    public Double luasPermukaan;


    public PrismaLayangLayang(Double d1, Double d2, Double sisiPendek, Double sisiPanjang, Double tinggiPrisma) throws ArgumentException {
        super(d1, d2, sisiPendek, sisiPanjang);

        // Validate the prism's own dimension
        if (tinggiPrisma <= 0) {
            throw new ArgumentException("Tinggi prisma harus lebih besar dari nol");
        }

        // Initialize the prism's properties
        this.tinggiPrisma = tinggiPrisma;
        this.nama = "Prisma Layang-Layang"; // Override the name from the parent

        // Calculate volume and surface area upon object creation, following the pattern
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }


    public Double hitungVolume() {
        // super.luasLayangLayang holds the area of the kite base
        this.volume = super.luasLayangLayang * this.tinggiPrisma;
        return this.volume;
    }


    public Double hitungLuasPermukaan() {
        double luasAlasGanda = 2 * super.luasLayangLayang;
        double luasSelubung = super.kelilingLayangLayang * this.tinggiPrisma;
        this.luasPermukaan = luasAlasGanda + luasSelubung;
        return this.luasPermukaan;
    }


    public Double hitungVolume(Double tinggiPrismaBaru) throws ArgumentException {
        if (tinggiPrismaBaru <= 0) {
            throw new ArgumentException("Tinggi prisma baru harus > 0");
        }
        return super.luasLayangLayang * tinggiPrismaBaru;
    }


    public Double hitungLuasPermukaan(Double d1Baru, Double d2Baru, Double sisiPendekBaru, Double sisiPanjangBaru, Double tinggiPrismaBaru) throws ArgumentException {
        if (d1Baru <= 0 || d2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new ArgumentException("Semua dimensi baru harus > 0");
        }
        double luasAlasBaru = (d1Baru * d2Baru) / 2;
        double kelilingAlasBaru = 2 * (sisiPendekBaru + sisiPanjangBaru);
        return (2 * luasAlasBaru) + (kelilingAlasBaru * tinggiPrismaBaru);
    }


    @Override
    public String getNama() {
        return this.nama;
    }
}