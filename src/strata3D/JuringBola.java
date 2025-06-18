package strata3D;

import Exception.ArgumentException;

public class JuringBola extends Bola {
    private Double sudutDerajat;
    private Double volumeJuring;
    private Double luasPermukaanJuring;

    public JuringBola(Double jariJari, Double sudutDerajat) throws ArgumentException {
        super(jariJari);
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new ArgumentException("Sudut harus antara 0 dan 360 derajat");
        }
        this.sudutDerajat = sudutDerajat;
        this.nama = "Juring Bola";
        this.volumeJuring = hitungVolume();
        this.luasPermukaanJuring = hitungLuasPermukaan();
    }

    @Override
    public Double hitungVolume() {
        volumeJuring = (sudutDerajat / 360) * super.hitungVolume();
        return volumeJuring;
    }

    @Override
    public Double hitungLuasPermukaan() {
        // luas juring bola = (sudut/360) * luas permukaan bola + luas penampang lingkaran (alas juring)
        luasPermukaanJuring = (sudutDerajat / 360) * super.hitungLuasPermukaan() + Math.PI * jariJari * jariJari;
        return luasPermukaanJuring;
    }

    public Double hitungVolume(Double jariJariBaru, Double sudutDerajatBaru) {
        volumeJuring =  (sudutDerajatBaru / 360) * ((4.0 / 3.0) * Math.PI * Math.pow(jariJariBaru, 3));
        return volumeJuring;
    }

    public Double hitungLuasPermukaan(Double jariJariBaru, Double sudutDerajatBaru) {
        luasPermukaanJuring = (sudutDerajatBaru / 360) * (4 * Math.PI * Math.pow(jariJariBaru, 2)) + Math.PI * jariJariBaru * jariJariBaru;
        return luasPermukaanJuring;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
