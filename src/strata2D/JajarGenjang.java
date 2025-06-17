package strata2D;
import Exception.ArgumentException;

public class JajarGenjang extends Bangun2D {
    private String nama;
    private Double alas, tinggi, sisiMiring;
    protected Double keliling, luas;

    public JajarGenjang(Double alas, Double tinggi, Double sisiMiring) throws ArgumentException {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0)
            throw new ArgumentException("Semua nilai harus > 0");
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
        this.nama = "Jajar Genjang";
    }

    @Override
    public Double hitungLuas() {
        luas = alas * this.tinggi;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 2 * (this.alas + this.sisiMiring);
        return keliling;
    }

    public Double hitungLuas(Double alasBaru, Double tinggiBaru) throws ArgumentException {
        if (alasBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Alas dan tinggi baru harus > 0");
        }
        luas = alasBaru * tinggiBaru;
        return luas;
    }

    public Double hitungKeliling(Double alasBaru, Double sisiMiringBaru) throws ArgumentException {
        if (alasBaru <= 0 || sisiMiringBaru <= 0) {
            throw new ArgumentException("Alas dan sisi miring baru harus > 0");
        }
        keliling = 2 * (alasBaru + sisiMiringBaru);
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
