package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class PrismaTrapesium extends Trapesium {
    private Double tinggiPrisma;
    protected Double volume, luasPermukaan;
    private String nama;

    public PrismaTrapesium(Double a, Double b, Double tinggi, Double sisi1, Double sisi2, Double tinggiPrisma) throws ArgumentException {
        super(a, b, tinggi, sisi1, sisi2);
        if (tinggiPrisma <= 0)
            throw new ArgumentException("Tinggi prisma harus > 0");
        this.tinggiPrisma = tinggiPrisma;
        this.nama = "Prisma Trapesium";
    }

    public Double hitungVolume() {
        volume = super.hitungLuas() * tinggiPrisma;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = (2 * super.hitungLuas()) + (super.hitungKeliling() * tinggiPrisma);
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
