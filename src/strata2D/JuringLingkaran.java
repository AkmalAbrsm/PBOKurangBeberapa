package strata2D;
import Exception.ArgumentException;

public class JuringLingkaran extends Lingkaran {
    private Double sudutDerajat;
    private Double luasJuring;
    private Double kelilingJuring;

    public JuringLingkaran(Double jariJari, Double sudutDerajat) throws ArgumentException {
        super(jariJari);
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new ArgumentException("Sudut harus lebih dari 0 dan tidak lebih dari 360 derajat");
        }
        this.sudutDerajat = sudutDerajat;
        this.nama = "Juring Lingkaran";
        this.luasJuring = hitungLuas();
        this.kelilingJuring = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luasJuring = super.luas * (sudutDerajat / 360);
        return luasJuring;
    }

    @Override
    public Double hitungKeliling() {
        kelilingJuring = 2 * jariJari + (super.keliling * (sudutDerajat / 360));
        return kelilingJuring;
    }

    public Double hitungLuas(Double jariJariBaru, Double sudutDerajatBaru) {
        luasJuring = super.hitungLuas(jariJariBaru) * (sudutDerajatBaru / 360);
        return luasJuring;
    }

    public Double hitungKeliling(Double jariJariBaru, Double sudutDerajatBaru) {
        kelilingJuring = 2 * jariJariBaru + (super.hitungKeliling(jariJariBaru) * (sudutDerajatBaru / 360));
        return  kelilingJuring;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
