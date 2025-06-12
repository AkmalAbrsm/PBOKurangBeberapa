package strata2D;

public class JuringLingkaran extends Lingkaran {
    private Double sudutDerajat;
    private Double luasJuring;
    private Double kelilingJuring;

    public JuringLingkaran(Double jariJari, Double sudutDerajat) {
        super(jariJari);
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut harus lebih dari 0 dan tidak lebih dari 360 derajat");
        }
        this.sudutDerajat = sudutDerajat;
        this.nama = "Juring Lingkaran";
        this.luasJuring = hitungLuas();
        this.kelilingJuring = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        luasJuring = Math.PI * jariJari * jariJari * (sudutDerajat / 360);
        return luasJuring;
    }

    @Override
    public Double hitungKeliling() {
        kelilingJuring = 2 * jariJari + (2 * Math.PI * jariJari * (sudutDerajat / 360));
        return kelilingJuring;
    }

    public Double hitungLuas(Double jariJariBaru, Double sudutDerajatBaru) {
        luasJuring = Math.PI * jariJariBaru * jariJariBaru * (sudutDerajatBaru / 360);
        return luasJuring;
    }

    public Double hitungKeliling(Double jariJariBaru, Double sudutDerajatBaru) {
        kelilingJuring = 2 * jariJariBaru + (2 * Math.PI * jariJariBaru * (sudutDerajatBaru / 360));
        return  kelilingJuring;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
