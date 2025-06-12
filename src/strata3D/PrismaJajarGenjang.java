package strata3D;

import strata2D.*;

public class PrismaJajarGenjang extends JajarGenjang {
    private Double tinggiPrisma;
    protected Double volume, luasPermukaan;
    private String nama;

    public PrismaJajarGenjang(Double alas, Double tinggi, Double sisiMiring, Double tinggiPrisma) {
        super(alas, tinggi, sisiMiring);
        if (tinggiPrisma <= 0) throw new IllegalArgumentException("Tinggi prisma harus > 0");
        this.tinggiPrisma = tinggiPrisma;
        this.nama = "Prisma Jajar Genjang";
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
        luasPermukaan = (2 * (alasBaru * tinggiBaru)) + (2 * sisiMiringBaru * tinggiPrismaBaru);
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}


