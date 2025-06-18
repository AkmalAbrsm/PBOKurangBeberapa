package strata2D;

import Exception.ArgumentException;

public class Segitiga extends Bangun2D {
    public String nama;
    public Double alas, tinggi;
    public Double luas, keliling;

    public Segitiga(Double alas, Double tinggi) throws ArgumentException {
        if (alas <= 0 || tinggi <= 0) {
            throw new ArgumentException("Nilai tidak boleh kurang dari atau sama dengan 0");
        }
        this.alas = alas;
        this.tinggi = tinggi;
        this.nama = "Segitiga";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luas = (this.alas * this.tinggi) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = this.alas + Math.sqrt(((this.alas / 2) * (this.alas / 2)) + this.tinggi * this.tinggi) * 2;
        return keliling;
    }

    public Double hitungLuas(Double alasBaru, Double tinggiBaru) {
        luas = (alasBaru * tinggiBaru) / 2;
        return luas;
    }

    public Double hitungKeliling(Double alasBaru, Double tinggiBaru) {
        keliling = alasBaru + tinggiBaru + Math.sqrt(Math.pow(alasBaru, 2) + Math.pow(tinggiBaru, 2));
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
