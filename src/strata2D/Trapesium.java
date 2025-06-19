package strata2D;

import Exception.ArgumentException;

public class Trapesium extends Bangun2D {
    public Double a, b, tinggi;
    public String nama;
    public Double luas, keliling;

    public Trapesium(Double a, Double b, Double tinggi) throws ArgumentException {
        if (a <= 0 || b <= 0 || tinggi <= 0 || a >= b)
            throw new ArgumentException("Semua nilai harus > 0 dan sisi atas harus lebih kecil dari sisi bawah");
        this.a = a;
        this.b = b;
        this.tinggi = tinggi;
        this.nama = "Trapesium";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luas = ((this.a + this.b) * this.tinggi) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = this.a + this.b + Math.sqrt(Math.pow((this.b - this.a) / 2, 2) + Math.pow(this.tinggi, 2)) * 2;
        return keliling;
    }

    public Double hitungLuas(Double aBaru, Double bBaru, Double tinggiBaru) {
        luas = ((aBaru + bBaru) * tinggiBaru) / 2;
        return luas;
    }

    public Double hitungKeliling(Double aBaru, Double bBaru, Double tinggiBaru) {
        keliling = aBaru + bBaru + Math.sqrt(Math.pow((bBaru - aBaru) / 2, 2) + Math.pow(tinggiBaru, 2)) * 2;
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
