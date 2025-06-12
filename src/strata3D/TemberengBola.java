package strata3D;

public class TemberengBola extends Bola {
    private Double tinggi;
    private Double volumeTembereng;
    private Double luasPermukaanTembereng;

    public TemberengBola(Double jariJari, Double tinggi) {
        super(jariJari);
        if (tinggi <= 0 || tinggi > 2 * jariJari) {
            throw new IllegalArgumentException("Tinggi harus lebih dari 0 dan tidak boleh melebihi diameter bola");
        }
        this.tinggi = tinggi;
        this.nama = "Tembereng Bola";
        this.volumeTembereng = hitungVolume();
        this.luasPermukaanTembereng = hitungLuasPermukaan();
    }

    @Override
    public Double hitungVolume() {
        // Rumus volume tembereng bola (spherical cap): πh²(3R - h)/3
        volumeTembereng = (Math.PI * tinggi * tinggi * (3 * jariJari - tinggi)) / 3.0;
        return volumeTembereng;
    }

    @Override
    public Double hitungLuasPermukaan() {
        // Luas permukaan tembereng bola: 2πRh (tanpa alas)
        luasPermukaanTembereng = 2 * Math.PI * jariJari * tinggi;
        return luasPermukaanTembereng;
    }

    public Double hitungVolume(Double jariJariBaru, Double tinggiBaru) {
        return (Math.PI * tinggiBaru * tinggiBaru * (3 * jariJariBaru - tinggiBaru)) / 3.0;
    }

    public Double hitungLuasPermukaan(Double jariJariBaru, Double tinggiBaru) {
        return 2 * Math.PI * jariJariBaru * tinggiBaru;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
