package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class LimasPersegiPanjang extends PersegiPanjang {
    private Double tinggi;
    private Double tinggiSisi; // slant height
    private Double volume, luasPermukaan;

    public LimasPersegiPanjang(Double panjang, Double lebar, Double tinggi, Double tinggiSisi) throws ArgumentException {
        super(panjang, lebar);
        if (tinggi <= 0 || tinggiSisi <= 0) {
            throw new ArgumentException("Tinggi dan tinggi sisi harus lebih besar dari nol");
        }
        this.tinggi = tinggi;
        this.tinggiSisi = tinggiSisi;
        this.nama = "Limas Persegi Panjang";
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    public Double hitungVolume() {
        volume = (1.0 / 3.0) * super.luas * tinggi;
        return volume;
    }

    public Double hitungLuasPermukaan() {
        // Surface area = base area + (1/2 * base perimeter * slant height)
        luasPermukaan = super.luas + 0.5 * super.keliling * tinggiSisi;
        return luasPermukaan;
    }

    public Double hitungVolume(Double panjangBaru, Double lebarBaru, Double tinggiBaru) throws ArgumentException {
        if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Panjang, lebar, dan tinggi harus lebih besar dari nol");
        }
        volume = (1.0 / 3.0) * panjangBaru * lebarBaru * tinggiBaru;
        return volume;
    }

    public Double hitungLuasPermukaan(Double panjangBaru, Double lebarBaru, Double tinggiSisiBaru) throws ArgumentException {
        if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiSisiBaru <= 0) {
            throw new ArgumentException("Panjang, lebar, dan tinggi sisi harus lebih besar dari nol");
        }
        Double kelilingBaru = 2 * (panjangBaru + lebarBaru);
        Double luasBaru = panjangBaru * lebarBaru;
        luasPermukaan = luasBaru + 0.5 * kelilingBaru * tinggiSisiBaru;
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}