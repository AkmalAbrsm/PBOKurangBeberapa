package strata2D;

public class TemberengLingkaran extends Lingkaran {
    private Double sudutDerajat;
    private Double luasTembereng;
    private Double kelilingTembereng;

    public TemberengLingkaran(Double jariJari, Double sudutDerajat) {
        super(jariJari);
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut harus antara 0 dan 360 derajat");
        }
        this.sudutDerajat = sudutDerajat;
        this.nama = "Tembereng Lingkaran";
        this.luasTembereng = hitungLuas();
        this.kelilingTembereng = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        double luasJuring = Math.PI * jariJari * jariJari * (sudutDerajat / 360);
        double luasSegitiga = 0.5 * jariJari * jariJari * Math.sin(Math.toRadians(sudutDerajat));
        luasTembereng = luasJuring - luasSegitiga;
        return luasTembereng;
    }

    @Override
    public Double hitungKeliling() {
        double panjangBusur = 2 * Math.PI * jariJari * (sudutDerajat / 360);
        double chord = 2 * jariJari * Math.sin(Math.toRadians(sudutDerajat / 2));
        kelilingTembereng = chord + panjangBusur;
        return kelilingTembereng;
    }

    public Double hitungLuas(Double jariJariBaru, Double sudutDerajatBaru) {
        double luasJuring = Math.PI * jariJariBaru * jariJariBaru * (sudutDerajatBaru / 360);
        double luasSegitiga = 0.5 * jariJariBaru * jariJariBaru * Math.sin(Math.toRadians(sudutDerajatBaru));
        return luasJuring - luasSegitiga;
    }

    public Double hitungKeliling(Double jariJariBaru, Double sudutDerajatBaru) {
        double panjangBusur = 2 * Math.PI * jariJariBaru * (sudutDerajatBaru / 360);
        double chord = 2 * jariJariBaru * Math.sin(Math.toRadians(sudutDerajatBaru / 2));
        return chord + panjangBusur;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
