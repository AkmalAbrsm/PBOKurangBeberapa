// src/CalculationTask.java (buat file baru)

import Exception.ArgumentException;
import strata2D.*;
import strata3D.*;
import javax.swing.JTextArea; // Untuk mengupdate outputArea

public class CalculationTask implements Runnable {
    private String shapeName;
    private double[] values;
    private JTextArea outputArea;

    public CalculationTask(String shapeName, double[] values, JTextArea outputArea) {
        this.shapeName = shapeName;
        this.values = values;
        this.outputArea = outputArea;
    }

    @Override
    public void run() {
        // Logika perhitungan akan dipindahkan dari calculateAndDisplay
        StringBuilder result = new StringBuilder();
        result.append("--- Hasil untuk ").append(shapeName).append(" ---\n\n");

        try {
            // Bentuk 2D
            if (shapeName.equals("Lingkaran")) {
                Lingkaran obj = new Lingkaran(values[0]);
                result.append(String.format("Jari-Jari: %.2f\n", values[0]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Persegi")) {
                Persegi obj = new Persegi(values[0]);
                result.append(String.format("Sisi: %.2f\n", values[0]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Persegi Panjang")) {
                PersegiPanjang obj = new PersegiPanjang(values[0], values[1]);
                result.append(String.format("Panjang: %.2f, Lebar: %.2f\n", values[0], values[1]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Segitiga")) {
                Segitiga obj = new Segitiga(values[0], values[1]);
                result.append(String.format("Alas: %.2f, Tinggi: %.2f\n", values[0], values[1]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Belah Ketupat")) {
                BelahKetupat obj = new BelahKetupat(values[0], values[1], values[2]);
                result.append(String.format("Diagonal 1: %.2f, Diagonal 2: %.2f, Sisi: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Jajar Genjang")) {
                JajarGenjang obj = new JajarGenjang(values[0], values[1], values[2]);
                result.append(String.format("Alas: %.2f, Tinggi: %.2f, Sisi Miring: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Layang-Layang")) {
                LayangLayang obj = new LayangLayang(values[0], values[1], values[2], values[3]);
                result.append(String.format("D1: %.2f, D2: %.2f, Sisi Pendek: %.2f, Sisi Panjang: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Trapesium")) {
                Trapesium obj = new Trapesium(values[0], values[1], values[2]);
                result.append(String.format("Sisi Sejajar a: %.2f, Sisi Sejajar b: %.2f, Tinggi: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Juring Lingkaran")) {
                JuringLingkaran obj = new JuringLingkaran(values[0], values[1]);
                result.append(String.format("Jari-Jari: %.2f, Sudut: %.2f\n", values[0], values[1]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            } else if (shapeName.equals("Tembereng Lingkaran")) {
                TemberengLingkaran obj = new TemberengLingkaran(values[0], values[1]);
                result.append(String.format("Jari-Jari: %.2f, Sudut: %.2f\n", values[0], values[1]));
                result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
                result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
            }
            // Bentuk 3D
            else if (shapeName.equals("Kubus")) {
                Kubus obj = new Kubus(values[0]);
                result.append(String.format("Sisi: %.2f\n", values[0]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
                result.append(String.format("Panjang Rusuk: %.2f\n", obj.hitungPanjangRusuk()));
            } else if (shapeName.equals("Balok")) {
                Balok obj = new Balok(values[0], values[1], values[2]);
                result.append(String.format("Panjang: %.2f, Lebar: %.2f, Tinggi: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            } else if (shapeName.equals("Tabung")) {
                Tabung obj = new Tabung(values[0], values[1]);
                result.append(String.format("Jari-Jari: %.2f, Tinggi: %.2f\n", values[0], values[1]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Bola")) {
                Bola obj = new Bola(values[0]);
                result.append(String.format("Jari-Jari: %.2f\n", values[0]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Kerucut")) {
                Kerucut obj = new Kerucut(values[0], values[1]);
                result.append(String.format("Jari-Jari: %.2f, Tinggi: %.2f\n", values[0], values[1]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Kerucut Terpancung")) {
                KerucutTerpancung obj = new KerucutTerpancung(values[0], values[1], values[2]);
                result.append(String.format("Jari-Jari Bawah: %.2f, Jari-Jari Atas: %.2f, Tinggi: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Prisma Segitiga")) {
                PrismaSegitiga obj = new PrismaSegitiga(values[0], values[1], values[2]);
                result.append(String.format("Alas Segitiga: %.2f, Tinggi Segitiga: %.2f, Tinggi Prisma: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Segitiga")) {
                LimasSegitiga obj = new LimasSegitiga(values[0], values[1], values[2]);
                result.append(String.format("Tinggi Alas: %.2f, Tinggi Limas: %.2f, Tinggi Sisi Tegak: %.2f\n", values[0], values[1], values[2]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Prisma Belah Ketupat")) {
                PrismaBelahKetupat obj = new PrismaBelahKetupat(values[0], values[1], values[2], values[3]);
                result.append(String.format("D1: %.2f, D2: %.2f, Sisi: %.2f, Tinggi Prisma: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Belah Ketupat")) {
                LimasBelahKetupat obj = new LimasBelahKetupat(values[0], values[1], values[2], values[3]);
                result.append(String.format("D1: %.2f, D2: %.2f, Sisi: %.2f, Tinggi Limas: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Persegi")) {
                LimasPersegi obj = new LimasPersegi(values[0], values[1]);
                result.append(String.format("Sisi Alas: %.2f, Tinggi Limas: %.2f\n", values[0], values[1]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Tembereng Bola")) {
                TemberengBola obj = new TemberengBola(values[0], values[1]);
                result.append(String.format("Jari-Jari Bola: %.2f, Tinggi Tembereng: %.2f\n", values[0], values[1]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Juring Bola")) {
                JuringBola obj = new JuringBola(values[0], values[1]);
                result.append(String.format("Jari-Jari Bola: %.2f, Sudut: %.2f\n", values[0], values[1]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Cincin Bola")) {
                CincinBola obj = new CincinBola(values[0], values[1]);
                result.append(String.format("Jari-Jari Bola: %.2f, Tinggi Cincin: %.2f\n", values[0], values[1]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Persegi Panjang")) {
                LimasPersegiPanjang obj = new LimasPersegiPanjang(values[0], values[1], values[2], values[3]);
                result.append(String.format("Panjang: %.2f, Lebar: %.2f, Tinggi Limas: %.2f, Tinggi Sisi Tegak: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Prisma Jajar Genjang")) {
                PrismaJajarGenjang obj = new PrismaJajarGenjang(values[0], values[1], values[2], values[3]);
                result.append(String.format("Alas: %.2f, Tinggi Alas: %.2f, Sisi Miring: %.2f, Tinggi Prisma: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Jajar Genjang")) {
                LimasJajarGenjang obj = new LimasJajarGenjang(values[0], values[1], values[2], values[3]);
                result.append(String.format("Alas: %.2f, Tinggi Alas: %.2f, Sisi Miring: %.2f, Tinggi Limas: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Prisma Layang-Layang")) {
                PrismaLayangLayang obj = new PrismaLayangLayang(values[0], values[1], values[2], values[3], values[4]);
                result.append(String.format("D1: %.2f, D2: %.2f, Sisi Pendek: %.2f, Sisi Panjang: %.2f, Tinggi Prisma: %.2f\n", values[0], values[1], values[2], values[3], values[4]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Layang-Layang")) {
                LimasLayangLayang obj = new LimasLayangLayang(values[0], values[1], values[2], values[3], values[4]);
                result.append(String.format("D1: %.2f, D2: %.2f, Sisi Pendek: %.2f, Sisi Panjang: %.2f, Tinggi Limas: %.2f\n", values[0], values[1], values[2], values[3], values[4]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Prisma Trapesium")) {
                PrismaTrapesium obj = new PrismaTrapesium(values[0], values[1], values[2], values[3]);
                result.append(String.format("Sisi a: %.2f, Sisi b: %.2f, Tinggi Alas: %.2f, Tinggi Prisma: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            } else if (shapeName.equals("Limas Trapesium")) {
                LimasTrapesium obj = new LimasTrapesium(values[0], values[1], values[2], values[3]);
                result.append(String.format("Sisi a: %.2f, Sisi b: %.2f, Tinggi Alas: %.2f, Tinggi Limas: %.2f\n", values[0], values[1], values[2], values[3]));
                result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
                result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            }
            // ... tambahkan bentuk lain di sini sesuai kebutuhan fungsionalitas Anda ...
            else {
                result.append("Kalkulator untuk '").append(shapeName).append("' belum diimplementasikan.");
            }
        } catch (ArgumentException ex) {
            result.append("Error: ").append(ex.getMessage());
        } catch (Exception ex) {
            result.append("Terjadi error tak terduga: ").append(ex.getMessage());
            ex.printStackTrace(); // Cetak stack trace untuk debugging
        }

        result.append("\n---------------------------------\n");

        // Penting: Update GUI dari Event Dispatch Thread (EDT)
        // SwingUtilities.invokeLater memastikan bahwa pembaruan UI aman dari thread lain.
        javax.swing.SwingUtilities.invokeLater(() -> outputArea.setText(result.toString()));
    }
}
