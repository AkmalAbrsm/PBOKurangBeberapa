package strata2D;

import Exception.ArgumentException;

public class PersegiPanjang extends Bangun2D {
    public Double panjang, lebar;
    public String nama;
    public Double luas, keliling;

    public PersegiPanjang(Double panjang, Double lebar) throws ArgumentException {
        if (panjang <= 0 || lebar <= 0)
            throw new ArgumentException("Panjang dan lebar harus > 0");
        this.panjang = panjang;
        this.lebar = lebar;
        this.nama = ".Persegi Panjang";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luas = this.panjang * this.lebar;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 2 * (this.panjang + this.lebar);
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
