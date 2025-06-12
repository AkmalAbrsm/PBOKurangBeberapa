package strata3D;

import strata2D.*;

public class Kubus extends Persegi {

    private Double Volume; // Volume kubus
    private Double LuasPermukaan;
    private Double PanjangRusuk;

    public Kubus(Double sisi) {
        super(sisi); // Memanggil konstruktor strata2D.Persegi
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi tidak boleh kosong dan harus lebih besar dari nol");
        }
        this.nama = "Kubus";
        this.Volume = hitungVolume();
        this.LuasPermukaan = hitungLuasPermukaan();
        this.PanjangRusuk = hitungPanjangRusuk();
    }

    public Double hitungLuasPermukaan() {
        // Menghitung luas permukaan kubus
        LuasPermukaan = super.luas * 6; // Luas satu sisi
        return LuasPermukaan;
    }

    public Double hitungPanjangRusuk() {
        // Menghitung keliling kubus
        PanjangRusuk = super.sisi * 12;
        return PanjangRusuk;
    }

    public Double hitungVolume() {
        // Menghitung volume kubus
        Volume = super.luas * super.sisi; // sisi^3
        return Volume;
    }

    public Double hitungLuasPermukaan(Double sisibaru) {
        LuasPermukaan = (sisibaru * sisibaru) * 6; // Luas permukaan kubus dengan sisi baru
        return LuasPermukaan;
    }

    public Double hitungPanjangRusuk(Double sisibaru) {
        PanjangRusuk = sisibaru * 12; // Panjang rusuk kubus dengan sisi baru
        return PanjangRusuk;
    }

    public Double hitungVolume(Double sisibaru) {
        Volume = sisibaru * sisibaru * sisibaru; // Volume kubus dengan sisi baru
        return Volume;
    }

    @Override
    public String getNama() {
        // Mengembalikan nama kubus
        return nama;
    }

}
