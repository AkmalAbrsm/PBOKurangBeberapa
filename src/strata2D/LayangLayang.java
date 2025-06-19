package strata2D;

import Exception.ArgumentException;

public class LayangLayang extends Bangun2D {

    public String nama;
    public Double d1, d2, sisiPendek, sisiPanjang;
    public Double luasLayangLayang;
    public Double kelilingLayangLayang;

    public LayangLayang(Double d1, Double d2, Double sisiPendek, Double sisiPanjang) throws ArgumentException {
        if (d1 <= 0 || d2 <= 0 || sisiPendek <= 0 || sisiPanjang <= 0 || sisiPendek >= sisiPanjang)
            throw new ArgumentException("Semua nilai harus > 0 dan Sisi Pendek harus lebih kecil dari Sisi Panjang");

        this.d1 = d1;
        this.d2 = d2;
        this.sisiPendek = sisiPendek;
        this.sisiPanjang = sisiPanjang;
        this.nama = "Layang-Layang";
        this.luasLayangLayang = hitungLuas();
        this.kelilingLayangLayang = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        // Update the specific instance field
        luasLayangLayang = (d1 * d2) / 2;
        return luasLayangLayang;
    }

    @Override
    public Double hitungKeliling() {
        // Update the specific instance field
        kelilingLayangLayang = 2 * (sisiPendek + sisiPanjang);
        return kelilingLayangLayang;
    }

    // Overloaded method for recalculating area, as per the pattern
    public Double hitungLuas(Double d1Baru, Double d2Baru) {
        luasLayangLayang = (d1Baru * d2Baru) / 2;
        return luasLayangLayang;
    }

    // Overloaded method for recalculating perimeter, as per the pattern
    public Double hitungKeliling(Double sisiPendekBaru, Double sisiPanjangBaru) {
        kelilingLayangLayang = 2 * (sisiPendekBaru + sisiPanjangBaru);
        return  kelilingLayangLayang;
    }

    @Override
    public String getNama() {
        return nama;
    }
}