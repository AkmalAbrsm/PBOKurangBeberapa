package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class PrismaSegitiga extends Segitiga {
    private Double tinggiPrisma;
    protected Double volume, luasPermukaan;
    private String nama;

    public PrismaSegitiga(Double alas, Double tinggi, Double tinggiPrisma)  throws ArgumentException {
        super(alas, tinggi);
        if (tinggiPrisma <= 0) {
            throw new ArgumentException("Tinggi prisma harus lebih besar dari nol");
        }
        this.tinggiPrisma = tinggiPrisma;
        this.nama = "Prisma Segitiga";
    }

    public Double hitungVolume() {
        volume = super.hitungLuas() * tinggiPrisma;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = (2 * super.hitungLuas()) + (super.hitungKeliling() * tinggiPrisma);
        return luasPermukaan;
    }

    public Double hitungLuasPermukaan(Double alasBaru, Double tinggiBaru, Double sisiMiringBaru, Double tinggiPrismaBaru) {
        if (alasBaru <= 0 || tinggiBaru <= 0 || sisiMiringBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new IllegalArgumentException("Alas, tinggi, sisi miring, dan tinggi prisma harus lebih besar dari nol");
        }
        luasPermukaan = (2 * (alasBaru * tinggiBaru / 2)) + (super.hitungKeliling(alasBaru, tinggiBaru) * tinggiPrismaBaru);
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
