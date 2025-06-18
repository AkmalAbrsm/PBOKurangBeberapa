package strata2D;

import Exception.ArgumentException;

public class Segitiga extends Bangun2D {
    public String nama;
    public Double alas, tinggi, sisiMiring;
    public Double luas, keliling;

    public Segitiga(Double alas, Double tinggi, Double sisiMiring) throws ArgumentException {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new ArgumentException("Nilai tidak boleh kurang dari atau sama dengan 0");
        }
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
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
        keliling = this.alas + this.tinggi + this.sisiMiring;
        return keliling;
    }

    public Double hitungLuas(Double alasBaru, Double tinggiBaru) {
        luas = (alasBaru * tinggiBaru) / 2;
        return luas;
    }

    public Double hitungKeliling(Double alasBaru, Double tinggiBaru, Double sisiMiringBaru) {
        keliling = alasBaru + tinggiBaru + sisiMiringBaru;
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
