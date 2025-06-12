package strata3D;

public class CincinBola extends Bola {
    private Double tinggi;
    private Double volumeCincin;
    private Double luasPermukaanCincin;

    public CincinBola(Double jariJari, Double tinggi) {
        super(jariJari);
        if (tinggi <= 0 || tinggi >= 2 * jariJari) {
            throw new IllegalArgumentException("Tinggi harus lebih dari 0 dan kurang dari diameter bola");
        }
        this.tinggi = tinggi;
        this.nama = "Cincin Bola";
        this.volumeCincin = hitungVolume();
        this.luasPermukaanCincin = hitungLuasPermukaan();
    }

    @Override
    public Double hitungVolume() {
        // Rumus zona bola (cincin bola) = π * h² * (R - h/3)
        volumeCincin = Math.PI * tinggi * tinggi * (jariJari - (tinggi / 3.0));
        return volumeCincin;
    }

    @Override
    public Double hitungLuasPermukaan() {
        // Rumus luas permukaan zona bola: 2 * π * R * h
        luasPermukaanCincin = 2 * Math.PI * jariJari * tinggi;
        return luasPermukaanCincin;
    }

    public Double hitungVolume(Double jariJariBaru, Double tinggiBaru) {
        volumeCincin = Math.PI * tinggiBaru * tinggiBaru * (jariJariBaru - (tinggiBaru / 3.0));
        return  volumeCincin;
    }

    public Double hitungLuasPermukaan(Double jariJariBaru, Double tinggiBaru) {
        luasPermukaanCincin = 2 * Math.PI * jariJariBaru * tinggiBaru;
        return luasPermukaanCincin;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
