package strata2D;

public class Persegi extends Bangun2D {
    protected String nama; // Nama persegi
    protected Double sisi; // Sisi persegi
    protected Double luas; // Luas persegi
    private Double keliling; // Keliling persegi

    public Persegi(Double sisi) {
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.sisi = sisi;
        this.nama = "Persegi";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        // Menghitung luas persegi
        luas = sisi * sisi;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        // Menghitung keliling persegi
        keliling = 4 * sisi;
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