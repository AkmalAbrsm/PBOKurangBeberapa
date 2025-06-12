package strata2D;

public class PersegiPanjang extends Bangun2D {
    protected Double panjang, lebar;
    protected String nama;
    protected Double luas, keliling;

    public PersegiPanjang(Double panjang, Double lebar) {
        if (panjang <= 0 || lebar <= 0)
            throw new IllegalArgumentException("Panjang dan lebar harus > 0");
        this.panjang = panjang;
        this.lebar = lebar;
        this.nama = ".Persegi Panjang";
    }

    @Override
    public Double hitungLuas() {
        luas = panjang * lebar;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 2 * (panjang + lebar);
        return keliling;
    }

    public Double hitungLuas(Double panjangBaru, Double lebarBaru) {
        luas = panjangBaru * lebarBaru;
        return luas;
    }

    public Double hitungKeliling(Double panjangBaru, Double lebarBaru) {
        keliling = 2 * (panjangBaru + lebarBaru);
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
