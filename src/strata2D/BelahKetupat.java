package strata2D;

public class BelahKetupat extends Bangun2D {
    protected String nama;
    protected Double d1, d2;
    protected Double sisi;

    public BelahKetupat(Double d1, Double d2, Double sisi) {
        if (d1 <= 0 || d2 <= 0 || sisi <= 0) {
            throw new IllegalArgumentException("Semua nilai harus > 0");
        }
        this.d1 = d1;
        this.d2 = d2;
        this.sisi = sisi;
        this.nama = "strata2D.BelahKetupat";
    }

    @Override
    public Double hitungLuas() {
        return (d1 * d2) / 2;
    }

    @Override
    public Double hitungKeliling() {
        return 4 * sisi;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
