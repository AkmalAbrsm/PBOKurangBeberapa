package strata3D;

import strata2D.*;

public class PrismaJajarGenjang extends JajarGenjang {
    private Double tinggiPrisma;

    public PrismaJajarGenjang(Double alas, Double tinggi, Double sisiMiring, Double tinggiPrisma) {
        super(alas, tinggi, sisiMiring);
        if (tinggiPrisma <= 0) throw new IllegalArgumentException("Tinggi prisma harus > 0");
        this.tinggiPrisma = tinggiPrisma;
    }

    public Double hitungVolume() {
        return super.hitungLuas() * tinggiPrisma;
    }

    public Double hitungLuasPermukaan() {
        return (2 * super.hitungLuas()) + (super.hitungKeliling() * tinggiPrisma);
    }
}

