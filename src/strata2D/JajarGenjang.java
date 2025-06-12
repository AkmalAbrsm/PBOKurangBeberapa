package strata2D;

public class JajarGenjang extends Bangun2D {
    private String nama;
    private Double alas, tinggi, sisiMiring;

    public JajarGenjang(Double alas, Double tinggi, Double sisiMiring) {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0)
            throw new IllegalArgumentException("Semua nilai harus > 0");
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
        this.nama = "strata2D.JajarGenjang";
    }

    @Override
    public Double hitungLuas() {
        return alas * tinggi;
    }

    @Override
    public Double hitungKeliling() {
        return 2 * (alas + sisiMiring);
    }

    @Override
    public String getNama() {
        return nama;
    }
}
