package strata2D;

public class Trapesium extends Bangun2D {
    private Double a, b, tinggi, sisi1, sisi2;
    private String nama;
    protected Double luas, keliling;

    public Trapesium(Double a, Double b, Double tinggi, Double sisi1, Double sisi2) {
        if (a <= 0 || b <= 0 || tinggi <= 0 || sisi1 <= 0 || sisi2 <= 0)
            throw new IllegalArgumentException("Semua nilai harus > 0");
        this.a = a;
        this.b = b;
        this.tinggi = tinggi;
        this.sisi1 = sisi1;
        this.sisi2 = sisi2;
        this.nama = "Trapesium";
    }

    @Override
    public Double hitungLuas() {
        luas = ((a + b) * tinggi) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = a + b + sisi1 + sisi2;
        return keliling;
    }

    public Double hitungLuas(Double aBaru, Double bBaru, Double tinggiBaru) {
        luas = ((aBaru + bBaru) * tinggiBaru) / 2;
        return luas;
    }

    public Double hitungKeliling(Double aBaru, Double bBaru, Double sisi1Baru, Double sisi2Baru) {
        keliling = aBaru + bBaru + sisi1Baru + sisi2Baru;
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
