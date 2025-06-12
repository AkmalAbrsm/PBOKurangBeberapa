package strata2D;

public class Lingkaran extends Bangun2D {
    protected String nama;
    protected Double jariJari;
    protected Double luas;
    private Double keliling;

    public Lingkaran(Double jariJari) {
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.jariJari = jariJari;
        this.nama = "Lingkaran";
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luas = Math.PI * jariJari * jariJari;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 2 * Math.PI * jariJari;
        return keliling;
    }

    public Double hitungLuas(Double jariJariBaru) {
        luas = Math.PI * jariJariBaru * jariJariBaru;
        return luas;
    }

    public Double hitungKeliling(Double jariJariBaru) {
        keliling = 2 * Math.PI * jariJariBaru;
        return keliling;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
