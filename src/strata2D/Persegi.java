package strata2D;
import Exception.ArgumentException;

public class Persegi extends Bangun2D {
    public String nama; // Nama persegi
    public Double sisi; // Sisi persegi
    public Double luas; // Luas persegi
    public Double keliling; // Keliling persegi

    public Persegi(Double sisi) throws ArgumentException {
        if (sisi <= 0) {
            throw new ArgumentException("Sisi tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.sisi = sisi;
        this.nama = "Persegi";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        // Menghitung luas persegi
        luas = this.sisi * this.sisi;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        // Menghitung keliling persegi
        keliling = 4 * this.sisi;
        return keliling;
    }

    @Override
    public String getNama() {
        // Mengembalikan nama persegi
        return nama;
    }

    public Double hitungLuas(double sisibaru){
        // Menghitung luas persegi dengan sisi baru
        luas = sisibaru * sisibaru;
        return luas;
    }

    public Double hitungKeliling(double sisibaru){
        // Menghitung keliling persegi dengan sisi baru
        keliling = 4 * sisibaru;
        return keliling;
    }
}