package strata3D;

import strata2D.*;
import Exception.ArgumentException;

public class LimasPersegiPanjang extends PersegiPanjang {
    private Double tinggi;
    private Double volume, luasPermukaan;

    public LimasPersegiPanjang(Double panjang, Double lebar, Double tinggi) throws ArgumentException {
        super(panjang, lebar);
        if (tinggi <= 0) {
            throw new ArgumentException("Tinggi dan tinggi sisi harus lebih besar dari nol");
        }
        this.tinggi = tinggi;
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
        double panjangSlantHeight = Math.sqrt((super.panjang / 2) * (super.panjang / 2) + tinggi * tinggi);
        double lebarSlantHeight = Math.sqrt((super.lebar / 2) * (super.lebar / 2) + tinggi * tinggi);
        double luasSisiPanjang = 2 * (0.5 * super.panjang * panjangSlantHeight);
        double luasSisiLebar = 2 * (0.5 * super.lebar * lebarSlantHeight);
        luasPermukaan = super.luas + luasSisiPanjang + luasSisiLebar;
        return luasPermukaan;
    }

    public Double hitungVolume(Double panjangBaru, Double lebarBaru, Double tinggiBaru) throws ArgumentException {
        if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Panjang, lebar, dan tinggi harus lebih besar dari nol");
        }
        volume = (1.0 / 3.0) * panjangBaru * lebarBaru * tinggiBaru;
        return volume;
    }

    public Double hitungLuasPermukaan(Double panjangBaru, Double lebarBaru, Double tinggiBaru) throws ArgumentException {
        if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiBaru <= 0) {
            throw new ArgumentException("Panjang, lebar, dan tinggi sisi harus lebih besar dari nol");
        }
        Double kelilingBaru = 2 * (panjangBaru + lebarBaru);
        Double luasBaru = panjangBaru * lebarBaru;
        double panjangSlantHeight = Math.sqrt((panjangBaru / 2) * (panjangBaru / 2) + tinggiBaru * tinggiBaru);
        double lebarSlantHeight = Math.sqrt((lebarBaru / 2) * (lebarBaru / 2) + tinggiBaru * tinggiBaru);
        double luasSisiPanjang = 2 * (0.5 * panjangBaru * panjangSlantHeight);
        double luasSisiLebar = 2 * (0.5 * lebarBaru * lebarSlantHeight);
        luasPermukaan = luasBaru + luasSisiPanjang + luasSisiLebar;
        return luasPermukaan;
    }

    @Override
    public String getNama() {
        return nama;
    }
}