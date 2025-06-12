package strata2D;

public class Segitiga extends Bangun2D {
    private String nama;
    private Double alas, tinggi, sisiMiring;
    private Double luas, keliling;

    public Segitiga(Double alas, Double tinggi, Double sisiMiring) {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new IllegalArgumentException("Nilai tidak boleh kurang dari atau sama dengan 0");
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
        luas = (alas * tinggi) / 2;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = alas + tinggi + sisiMiring;
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
