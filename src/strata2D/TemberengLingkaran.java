package strata2D;
import Exception.ArgumentException;

public class TemberengLingkaran extends Lingkaran {
    public Double sudutDerajat;
    public Double luasTembereng;
    public Double kelilingTembereng;

    public TemberengLingkaran(Double jariJari, Double sudutDerajat) throws ArgumentException {
        super(jariJari);
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new ArgumentException("Sudut harus antara 0 dan 360 derajat");
        }
        this.sudutDerajat = sudutDerajat;
        this.nama = "Tembereng Lingkaran";
        this.luasTembereng = hitungLuas();
        this.kelilingTembereng = hitungKeliling();
    }

    @Override
    public Double hitungLuas() {
        double luasJuring = super.luas * (this.sudutDerajat / 360);
        double luasSegitiga = 0.5 * this.jariJari * this.jariJari * Math.sin(Math.toRadians(this.sudutDerajat));
        luasTembereng = luasJuring - luasSegitiga;
        return luasTembereng;
    }

    @Override
    public Double hitungKeliling() {
        double panjangBusur = super.keliling * (this.sudutDerajat / 360);
        double chord = 2 * this.jariJari * Math.sin(Math.toRadians(this.sudutDerajat / 2));
        kelilingTembereng = chord + panjangBusur;
        return kelilingTembereng;
    }

    public Double hitungLuas(Double jariJariBaru, Double sudutDerajatBaru) {
        double luasJuring = super.hitungLuas(jariJariBaru) * (sudutDerajatBaru / 360);
        double luasSegitiga = 0.5 * jariJariBaru * jariJariBaru * Math.sin(Math.toRadians(sudutDerajatBaru));
        return luasJuring - luasSegitiga;
    }

    public Double hitungKeliling(Double jariJariBaru, Double sudutDerajatBaru) {
        double panjangBusur = super.hitungKeliling(jariJariBaru) * (sudutDerajatBaru / 360);
        double chord = 2 * jariJariBaru * Math.sin(Math.toRadians(sudutDerajatBaru / 2));
        return chord + panjangBusur;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
