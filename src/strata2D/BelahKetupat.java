package strata2D;

public class BelahKetupat extends Bangun2D {
    protected String nama;
    protected Double d1, d2;
    protected Double sisi;
    protected Double luas, keliling;

    public BelahKetupat(Double d1, Double d2, Double sisi) {
        if (d1 <= 0 || d2 <= 0 || sisi <= 0) {
            throw new IllegalArgumentException("Semua nilai harus > 0");
        }
        this.d1 = d1;
        this.d2 = d2;
        this.sisi = sisi;
        this.nama = "Belah Ketupat";
    }

    @Override
    public Double hitungLuas() {
        luas = (d1 * d2) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 4 * sisi;
        return keliling;
    }

    public Double hitungLuas(Double d1Baru, Double d2Baru) {
        if (d1Baru <= 0 || d2Baru <= 0) {
            throw new IllegalArgumentException("Diagonal harus > 0");
        }
        luas = (d1Baru * d2Baru) / 2;
        return luas;
    }

    public Double hitungKeliling(Double sisiBaru) {
        if (sisiBaru <= 0) {
            throw new IllegalArgumentException("Sisi harus > 0");
        }
        keliling = 4 * sisiBaru;
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
