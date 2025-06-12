package strata2D;

public class PersegiPanjang extends Bangun2D {
    protected Double panjang, lebar;
    protected String nama;

    public PersegiPanjang(Double panjang, Double lebar) {
        if (panjang <= 0 || lebar <= 0)
            throw new IllegalArgumentException("Panjang dan lebar harus > 0");
        this.panjang = panjang;
        this.lebar = lebar;
        this.nama = "strata2D.PersegiPanjang";
    }

    @Override
    public Double hitungLuas() {
        return panjang * lebar;
    }

    @Override
    public Double hitungKeliling() {
        return 2 * (panjang + lebar);
    }

    @Override
    public String getNama() {
        return nama;
    }
}
