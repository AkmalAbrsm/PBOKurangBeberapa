package strata2D;

public class Trapesium extends Bangun2D {
    private Double a, b, tinggi, sisi1, sisi2;
    private String nama;

    public Trapesium(Double a, Double b, Double tinggi, Double sisi1, Double sisi2) {
        if (a <= 0 || b <= 0 || tinggi <= 0 || sisi1 <= 0 || sisi2 <= 0)
            throw new IllegalArgumentException("Semua nilai harus > 0");
        this.a = a;
        this.b = b;
        this.tinggi = tinggi;
        this.sisi1 = sisi1;
        this.sisi2 = sisi2;
        this.nama = "strata2D.Trapesium";
    }

    @Override
    public Double hitungLuas() {
        return ((a + b) * tinggi) / 2;
    }

    @Override
    public Double hitungKeliling() {
        return a + b + sisi1 + sisi2;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
