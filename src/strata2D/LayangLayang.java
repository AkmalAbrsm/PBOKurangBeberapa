package strata2D;

import Exception.ArgumentException; // Assuming this custom exception exists

public class LayangLayang extends Bangun2D {
    // All fields are private to follow the reference pattern
    protected String nama;
    protected Double d1, d2, sisiPendek, sisiPanjang;
    protected Double luasLayangLayang;
    protected Double kelilingLayangLayang;

    public LayangLayang(Double d1, Double d2, Double sisiPendek, Double sisiPanjang) throws ArgumentException {
        // Use the custom ArgumentException
        if (d1 <= 0 || d2 <= 0 || sisiPendek <= 0 || sisiPanjang <= 0)
            throw new ArgumentException("Semua nilai harus > 0");

        this.d1 = d1;
        this.d2 = d2;
        this.sisiPendek = sisiPendek;
        this.sisiPanjang = sisiPanjang;
        this.nama = "Layang-Layang";

        // Initialize area and perimeter upon object creation, as per the pattern
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