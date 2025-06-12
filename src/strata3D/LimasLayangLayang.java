package strata3D;

import strata2D.*;

public class LimasLayangLayang extends LayangLayang {
    private Double tinggi;
    protected Double volume, luasPermukaan;
    private String nama;

    public LimasLayangLayang(Double d1, Double d2, Double sisiPendek, Double sisiPanjang, Double tinggi) {
        super(d1, d2, sisiPendek, sisiPanjang);
        if (tinggi <= 0) throw new IllegalArgumentException("Tinggi harus > 0");
        this.tinggi = tinggi;
        this.nama = "Limas Belah Ketupat";
    }

    public Double hitungVolume() {
        volume = (super.hitungLuas() * tinggi) / 3;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        luasPermukaan = super.hitungLuas() + 2 * ((sisiPanjang * tinggi) / 2) + 2 * ((sisiPendek * tinggi) / 2);
        return luasPermukaan;
    }

    public Double hitungLuasPermukaan(Double d1baru, Double d2baru, Double sisiPendekBaru, Double sisiPanjangBaru, Double tinggibaru) {
        if (d1baru <= 0 || d2baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggibaru <= 0) {
            throw new IllegalArgumentException("Diagonal, sisi pendek, sisi panjang, dan tinggi harus lebih besar dari nol");
        }
        luasPermukaan = (d1baru * d2baru / 2) + 2 * ((sisiPanjangBaru * tinggibaru) / 2) + 2 * ((sisiPendekBaru * tinggibaru) / 2);
        return luasPermukaan;
    }

    public Double hitungVolume(Double d1baru, Double d2baru, Double sisiPendekBaru, Double sisiPanjangBaru, Double tinggibaru) {
        if (d1baru <= 0 || d2baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggibaru <= 0) {
            throw new IllegalArgumentException("Diagonal, sisi pendek, sisi panjang, dan tinggi harus lebih besar dari nol");
        }
        volume = (d1baru * d2baru / 2 * tinggibaru) / 3;
        return volume;
    }

    @Override
    public String getNama() {
        return nama;
    }
}
