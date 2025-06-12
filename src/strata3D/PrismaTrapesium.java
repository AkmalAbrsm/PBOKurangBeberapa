package strata3D;

import strata2D.*;

public class PrismaTrapesium extends Trapesium {
    private Double tinggiPrisma;

    public PrismaTrapesium(Double a, Double b, Double tinggi, Double sisi1, Double sisi2, Double tinggiPrisma) {
        super(a, b, tinggi, sisi1, sisi2);
        if (tinggiPrisma <= 0)
            throw new IllegalArgumentException("Tinggi prisma harus > 0");
        this.tinggiPrisma = tinggiPrisma;
    }

    public Double hitungVolume() {
        return super.hitungLuas() * tinggiPrisma;
    }

    public Double hitungLuasPermukaan() {
        return (2 * super.hitungLuas()) + (super.hitungKeliling() * tinggiPrisma);
    }
}
