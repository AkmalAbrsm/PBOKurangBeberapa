package strata2D;
import Exception.ArgumentException;

public class BelahKetupat extends Bangun2D {
    public String nama;
    public Double d1, d2;
    public Double sisi;
    public Double luas, keliling;

    public BelahKetupat(Double d1, Double d2, Double sisi) throws ArgumentException {
        if (d1 <= 0 || d2 <= 0 || sisi <= 0) {
            throw new ArgumentException("Semua nilai harus > 0");
        }
        this.d1 = d1;
        this.d2 = d2;
        this.sisi = sisi;
        this.nama = "Belah Ketupat";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luas = (this.d1 * this.d2) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 4 * this.sisi;
        return keliling;
    }

    public Double hitungLuas(Double d1Baru, Double d2Baru) throws ArgumentException {
        if (d1Baru <= 0 || d2Baru <= 0) {
            throw new ArgumentException("Diagonal harus > 0");
        }
        luas = (d1Baru * d2Baru) / 2;
        return luas;
    }

    public Double hitungKeliling(Double sisiBaru) throws ArgumentException {
        if (sisiBaru <= 0) {
            throw new ArgumentException("Sisi harus > 0");
        }
        keliling = 4 * sisiBaru;
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
