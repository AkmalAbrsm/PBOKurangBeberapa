package strata2D;
import Exception.ArgumentException;

public class Lingkaran extends Bangun2D {
    protected String nama;
    protected Double jariJari;
    protected Double luas;
    protected Double keliling;

    public Lingkaran(Double jariJari) throws ArgumentException {
        if (jariJari <= 0) {
            throw new ArgumentException("Jari-jari tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.jariJari = jariJari;
        this.nama = "Lingkaran";
        this.luas = hitungLuas(jariJari); // Initialize luas here
        this.keliling = hitungKeliling(jariJari);
    }

    @Override
    public Double hitungLuas() {
        luas = Math.PI * this.jariJari * this.jariJari;
        return luas;
    }

    @Override
    public Double hitungKeliling() {
        keliling = 2 * Math.PI * this.jariJari;
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
