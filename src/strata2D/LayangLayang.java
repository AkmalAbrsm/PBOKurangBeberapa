package strata2D;

public class LayangLayang extends Bangun2D {
    private String nama;
    public Double d1, d2, sisiPendek, sisiPanjang;
    protected Double luas, keliling;

    public LayangLayang(Double d1, Double d2, Double sisiPendek, Double sisiPanjang) {
        if (d1 <= 0 || d2 <= 0 || sisiPendek <= 0 || sisiPanjang <= 0)
            throw new IllegalArgumentException("Semua nilai harus > 0");
        this.d1 = d1;
        this.d2 = d2;
        this.sisiPendek = sisiPendek;
        this.sisiPanjang = sisiPanjang;
        this.nama = "Layang-Layang";
    }

    @Override
    public Double hitungLuas() {
        luas = (d1 * d2) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 2 * (sisiPendek + sisiPanjang);
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
