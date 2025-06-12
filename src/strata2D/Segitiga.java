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
        this.nama = "strata2D.Segitiga";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        return (alas * tinggi) / 2;
    }

    @Override
    public Double hitungKeliling() {
        return alas + tinggi + sisiMiring;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
