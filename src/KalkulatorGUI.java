import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// Import semua kelas bentuk dan exception (asumsi kelas-kelas ini ada dalam struktur proyek Anda)
import strata2D.*;
import strata3D.*;
import Exception.ArgumentException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Custom Border untuk sudut membulat.
 * Memberikan tampilan yang lebih lembut pada elemen UI.
 */
class RoundedBorder implements Border {
    private int radius;
    private Color color;

    /**
     * Konstruktor untuk RoundedBorder.
     * @param radius Radius pembulatan sudut.
     * @param color Warna garis border.
     */
    RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    /**
     * Mendapatkan insets (padding) dari border.
     * @param c Komponen yang menggunakan border ini.
     * @return Objek Insets yang mendefinisikan padding.
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 2, radius);
    }

    /**
     * Memeriksa apakah border bersifat opak.
     * @return True jika border opak, false sebaliknya.
     */
    public boolean isBorderOpaque() {
        return true; // Border itu sendiri buram
    }

    /**
     * Menggambar border pada komponen.
     * Menggunakan Anti-aliasing untuk pembulatan yang halus.
     * @param c Komponen yang akan digambar bordernya.
     * @param g Objek Graphics.
     * @param x Koordinat X awal.
     * @param y Koordinat Y awal.
     * @param width Lebar area gambar.
     * @param height Tinggi area gambar.
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
        g2.dispose();
    }
}

/**
 * Custom JButton yang menggambar latar belakang gradien dan memiliki sudut membulat.
 * Memberikan efek visual yang modern dan menarik pada tombol.
 */
class GradientButton extends JButton {
    private Color startColor;
    private Color endColor;
    private Color originalStartColor;
    private Color originalEndColor;
    private int cornerRadius;

    /**
     * Konstruktor untuk GradientButton.
     * @param text Teks yang akan ditampilkan pada tombol.
     * @param start Warna awal untuk gradien.
     * @param end Warna akhir untuk gradien.
     * @param radius Radius pembulatan sudut tombol.
     */
    public GradientButton(String text, Color start, Color end, int radius) {
        super(text);
        this.startColor = start;
        this.endColor = end;
        this.originalStartColor = start; // Menyimpan warna asli untuk efek hover
        this.originalEndColor = end;
        this.cornerRadius = radius;
        setContentAreaFilled(false); // Penting: Jangan gambar latar belakang tombol default
        setFocusPainted(false);      // Hapus persegi panjang fokus
        setBorderPainted(false);     // Kami akan menggambar latar belakang/border kami sendiri
        setForeground(Color.WHITE);  // Warna teks default

        // Tambahkan efek hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Sedikit menggelapkan warna saat hover
                startColor = new Color(Math.max(0, originalStartColor.getRed() - 20),
                        Math.max(0, originalStartColor.getGreen() - 20),
                        Math.max(0, originalStartColor.getBlue() - 20));
                endColor = new Color(Math.max(0, originalEndColor.getRed() - 20),
                        Math.max(0, originalEndColor.getGreen() - 20),
                        Math.max(0, originalEndColor.getBlue() - 20));
                setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ganti kursor saat hover
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Mengembalikan warna asli
                startColor = originalStartColor;
                endColor = originalEndColor;
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kembalikan kursor default
                repaint();
            }
        });
    }

    /**
     * Menggambar komponen tombol.
     * Menggambar latar belakang gradien berbentuk persegi panjang membulat.
     * @param g Objek Graphics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Buat cat gradien
        GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
        g2.setPaint(gp);

        // Isi persegi panjang membulat
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2.dispose();
        super.paintComponent(g); // Gambar teks dan ikon setelah latar belakang
    }

    /**
     * Menggambar border tombol.
     * Kami menangani border sebagai bagian dari paintComponent dengan mengisi area.
     * Tidak perlu menggambar border terpisah di sini.
     * @param g Objek Graphics.
     */
    @Override
    protected void paintBorder(Graphics g) {
        // Kami menangani border painting di paintComponent atau bergantung pada fill,
        // atau menambahkan border tipis di sini jika diinginkan, mis. menggunakan RoundedBorder.
        // Untuk kesederhanaan, mari kita hilangkan border terpisah untuk saat ini, mengandalkan fill gradien.
    }
}


/**
 * Aplikasi GUI untuk menghitung properti berbagai bentuk geometri.
 * Kelas ini membuat jendela dengan bilah menu untuk memilih bentuk.
 * Berdasarkan pilihan, secara dinamis menghasilkan bidang input untuk dimensi bentuk.
 * Tombol "Hitung" melakukan perhitungan dan menampilkan hasilnya.
 */
public class KalkulatorGUI extends JFrame {

    private JTextArea outputArea;
    private JPanel inputPanel;
    private JLabel titleLabel;
    private JButton calculateButton;

    private String currentShape;
    private java.util.List<JTextField> inputFields = new ArrayList<>();
    private java.util.List<JLabel> inputLabels = new ArrayList<>();
    private final Map<String, String[]> shapeFields = new LinkedHashMap<>();

    // Tambahkan ExecutorService untuk mengelola thread pool
    private ExecutorService executorService; // Menggunakan ExecutorService untuk mengelola thread

    /**
     * Konstruktor KalkulatorGUI.
     * Menginisialisasi peta bidang bentuk dan menyiapkan UI utama.
     */
    public KalkulatorGUI() {
        // Inisialisasi peta dengan bidang yang diperlukan untuk setiap bentuk
        initializeShapeFields();
        // Inisialisasi ExecutorService dengan fixed thread pool (misalnya, 2 thread)
        executorService = Executors.newFixedThreadPool(2); // Menggunakan thread pooling
        // Siapkan jendela utama
        initUI();
    }


    /**
     * Menginisialisasi frame utama dan komponen-komponennya.
     * Metode ini bertanggung jawab untuk mengatur estetika GUI.
     */
    private void initUI() {
        setTitle("Kalkulator Geometri Tingkat Lanjut"); // Judul yang lebih menarik
        setSize(850, 650); // Ukuran jendela sedikit lebih besar
        setLocationRelativeTo(null); // Pusat jendela di layar
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Keluar dari aplikasi saat menutup jendela

        // Atur bilah menu
        setJMenuBar(createMenuBar());

        // Panel kontainer utama dengan border layout
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20)); // Jeda yang diperbesar antar komponen
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25)); // Padding yang lebih besar di sekitar konten
        mainPanel.setBackground(new Color(240, 244, 248)); // Warna latar belakang abu-abu terang (Light Slate Gray)

        // Panel untuk judul dan input dinamis
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(240, 244, 248)); // Samakan dengan latar belakang utama

        titleLabel = new JLabel("Pilih bentuk geometri dari menu di atas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Font lebih besar, tebal, dan modern
        titleLabel.setForeground(new Color(35, 47, 52)); // Warna teks judul sangat gelap
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Pusatkan judul

        inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Tata letak grid dinamis, jeda yang diperbesar
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 20, 20, 20), // Padding internal untuk panel input
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true) // Border garis tipis dengan sudut membulat
        ));
        inputPanel.setBackground(new Color(255, 255, 255)); // Latar belakang putih untuk bidang input agar jelas

        topPanel.add(titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Ruang lebih banyak di bawah judul
        topPanel.add(inputPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Tombol Hitung
        // Menggunakan GradientButton kustom untuk tampilan yang lebih menarik
        calculateButton = new GradientButton("Hitung", new Color(26, 188, 156), new Color(22, 160, 133), 15); // Gradien Turquoise ke Green Sea, radius 15px
        calculateButton.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Font lebih besar dan tebal untuk tombol
        calculateButton.setPreferredSize(new Dimension(200, 50)); // Atur ukuran yang disukai untuk tombol yang lebih menonjol
        calculateButton.addActionListener(new CalculateButtonListener()); // Tambahkan listener aksi

        // Buat panel baru khusus untuk tombol agar dapat dipusatkan
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Pusatkan tombol
        buttonPanel.setBackground(new Color(240, 244, 248)); // Samakan dengan latar belakang utama
        buttonPanel.add(calculateButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Area Output
        outputArea = new JTextArea(12, 50); // Ukuran yang sedikit lebih besar
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14)); // Font monospaced yang baik untuk tampilan hasil
        outputArea.setEditable(false); // Area teks tidak dapat diedit
        outputArea.setBackground(new Color(232, 246, 243)); // Latar belakang hijau kebiruan sangat terang
        outputArea.setForeground(new Color(44, 62, 80)); // Warna teks output gelap (Wet Asphalt)
        outputArea.setMargin(new Insets(15, 15, 15, 15)); // Padding internal untuk teks
        outputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), // Border tipis
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding di dalam border
        ));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Hapus border default scroll pane
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Membuat dan mengembalikan bilah menu utama untuk aplikasi.
     * Bilah menu dirancang dengan skema warna yang kohesif.
     * @return JMenuBar yang telah dikonfigurasi.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(74, 101, 114)); // Latar belakang abu-abu biru gelap
        menuBar.setBorder(BorderFactory.createEmptyBorder()); // Hapus border default menu bar

        // Definisikan struktur menu
        String[] menuTitles = {"Lingkaran", "Persegi", "Persegi Panjang", "Segitiga", "Trapesium", "Jajar Genjang", "Belah Ketupat", "Layang-Layang"};

        for(String title : menuTitles) {
            JMenu menu = new JMenu(title);
            menu.setForeground(Color.WHITE); // Teks menu putih
            menu.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font menu lebih tebal

            // Dapatkan semua bentuk yang termasuk dalam kategori bentuk dasar ini dari peta
            for (String shapeName : shapeFields.keySet()) {
                if(getBaseShape(shapeName).equals(title)) {
                    JMenuItem menuItem = new JMenuItem(shapeName);
                    menuItem.setBackground(new Color(74, 101, 114)); // Samakan dengan latar belakang menu
                    menuItem.setForeground(Color.WHITE); // Teks item menu putih
                    menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13)); // Font item menu reguler
                    menuItem.setOpaque(true); // Diperlukan agar warna latar belakang ditampilkan

                    // Tambahkan efek hover untuk item menu
                    menuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            menuItem.setBackground(new Color(52, 73, 85)); // Warna lebih gelap saat hover
                            menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ganti kursor saat hover
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            menuItem.setBackground(new Color(74, 101, 114)); // Kembalikan warna asli
                            menuItem.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kembalikan kursor default
                        }
                    });

                    menuItem.addActionListener(new ShapeMenuItemListener(shapeName));
                    menu.add(menuItem);
                }
            }
            menuBar.add(menu);
        }

        return menuBar;
    }

    /**
     * Memperbarui panel input dengan bidang yang diperlukan untuk bentuk yang dipilih.
     * @param shapeName Nama bentuk untuk membuat formulir input.
     */
    private void updateInputPanel(String shapeName) {
        currentShape = shapeName;
        titleLabel.setText("Input untuk " + shapeName); // Perbarui judul

        // Hapus komponen sebelumnya
        inputPanel.removeAll();
        inputFields.clear();
        inputLabels.clear();

        // Dapatkan bidang yang diperlukan untuk bentuk yang dipilih
        String[] fields = shapeFields.get(shapeName);
        if (fields != null) {
            for (String fieldName : fields) {
                JLabel label = new JLabel(fieldName + ":", SwingConstants.RIGHT);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Ukuran font yang konsisten
                label.setForeground(new Color(52, 73, 85)); // Warna teks yang lebih gelap

                JTextField textField = new JTextField(15); // Bidang teks sedikit lebih lebar
                textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                textField.setBackground(new Color(236, 240, 241)); // Latar belakang abu-abu kebiruan terang
                textField.setBorder(BorderFactory.createCompoundBorder(
                        new RoundedBorder(8, new Color(189, 195, 199)), // Border membulat untuk bidang teks
                        new EmptyBorder(6, 10, 6, 10) // Padding internal
                ));

                inputLabels.add(label);
                inputFields.add(textField);

                inputPanel.add(label);
                inputPanel.add(textField);
            }
        }

        // Segarkan panel
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    /**
     * Listener untuk item menu. Memperbarui panel input saat bentuk dipilih.
     */
    private class ShapeMenuItemListener implements ActionListener {
        private String shapeName;

        public ShapeMenuItemListener(String shapeName) {
            this.shapeName = shapeName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updateInputPanel(shapeName);
            outputArea.setText(""); // Bersihkan area output saat memilih bentuk baru
        }
    }

    /**
     * Listener untuk tombol "Hitung". Melakukan perhitungan berdasarkan bentuk dan input saat ini.
     */
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentShape == null || currentShape.isEmpty()) {
                outputArea.setText("Error: Silakan pilih bentuk terlebih dahulu dari menu di atas.");
                return;
            }

            // Dapatkan nilai dari bidang teks
            double[] values = new double[inputFields.size()];
            try {
                for (int i = 0; i < inputFields.size(); i++) {
                    values[i] = Double.parseDouble(inputFields.get(i).getText());
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Input tidak valid. Harap masukkan angka yang benar.");
                return;
            }

            // Buat objek CalculationTask dan kirimkan ke ExecutorService
            CalculationTask task = new CalculationTask(currentShape, values, outputArea);
            executorService.execute(task); // Eksekusi tugas di thread terpisah

            // Opsional: tampilkan pesan bahwa perhitungan sedang berlangsung
            outputArea.setText("Perhitungan untuk " + currentShape + " sedang berlangsung...");
        }
    }


    /**
     * Membuat instance bentuk yang benar, menghitung propertinya, dan menampilkan hasilnya.
     * @param v Sebuah array nilai double dari bidang input.
     * @throws ArgumentException jika nilai input tidak valid untuk bentuk tersebut.
     */
    private void calculateAndDisplay(double... v) throws ArgumentException {
        StringBuilder result = new StringBuilder();
        result.append("--- Hasil untuk ").append(currentShape).append(" ---\n\n");

        // Bentuk 2D
        if (currentShape.equals("Lingkaran")) {
            Lingkaran obj = new Lingkaran(v[0]);
            result.append(String.format("Jari-Jari: %.2f\n", v[0]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Persegi")) {
            Persegi obj = new Persegi(v[0]);
            result.append(String.format("Sisi: %.2f\n", v[0]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Persegi Panjang")) {
            // Bagian ini menangani perhitungan untuk Persegi Panjang (bangun datar)
            PersegiPanjang obj = new PersegiPanjang(v[0], v[1]);
            result.append(String.format("Panjang: %.2f, Lebar: %.2f\n", v[0], v[1]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Segitiga")) {
            Segitiga obj = new Segitiga(v[0], v[1], v[2]);
            result.append(String.format("Alas: %.2f, Tinggi: %.2f, Sisi Miring: %.2f\n", v[0], v[1], v[2]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Belah Ketupat")) {
            BelahKetupat obj = new BelahKetupat(v[0], v[1], v[2]);
            result.append(String.format("Diagonal 1: %.2f, Diagonal 2: %.2f, Sisi: %.2f\n", v[0], v[1], v[2]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Jajar Genjang")) {
            JajarGenjang obj = new JajarGenjang(v[0], v[1], v[2]);
            result.append(String.format("Alas: %.2f, Tinggi: %.2f, Sisi Miring: %.2f\n", v[0], v[1], v[2]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Layang-Layang")) {
            LayangLayang obj = new LayangLayang(v[0], v[1], v[2], v[3]);
            result.append(String.format("D1: %.2f, D2: %.2f, Sisi Pendek: %.2f, Sisi Panjang: %.2f\n", v[0], v[1], v[2], v[3]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Trapesium")) {
            Trapesium obj = new Trapesium(v[0], v[1], v[2], v[3], v[4]);
            result.append(String.format("Sisi Sejajar a: %.2f, Sisi Sejajar b: %.2f, Tinggi: %.2f, Sisi 1: %.2f, Sisi 2: %.2f\n", v[0], v[1], v[2], v[3], v[4]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Juring Lingkaran")) {
            JuringLingkaran obj = new JuringLingkaran(v[0], v[1]);
            result.append(String.format("Jari-Jari: %.2f, Sudut: %.2f\n", v[0], v[1]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        } else if (currentShape.equals("Tembereng Lingkaran")) {
            TemberengLingkaran obj = new TemberengLingkaran(v[0], v[1]);
            result.append(String.format("Jari-Jari: %.2f, Sudut: %.2f\n", v[0], v[1]));
            result.append(String.format("Luas: %.2f\n", obj.hitungLuas()));
            result.append(String.format("Keliling: %.2f\n", obj.hitungKeliling()));
        }

        // Bentuk 3D
        else if (currentShape.equals("Kubus")) {
            Kubus obj = new Kubus(v[0]);
            result.append(String.format("Sisi: %.2f\n", v[0]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            result.append(String.format("Panjang Rusuk: %.2f\n", obj.hitungPanjangRusuk()));
        } else if (currentShape.equals("Balok")) {
            // Bagian ini menangani perhitungan untuk Balok (bangun ruang)
            Balok obj = new Balok(v[0], v[1], v[2]);
            result.append(String.format("Panjang: %.2f, Lebar: %.2f, Tinggi: %.2f\n", v[0], v[1], v[2]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Tabung")) {
            Tabung obj = new Tabung(v[0], v[1]);
            result.append(String.format("Jari-Jari: %.2f, Tinggi: %.2f\n", v[0], v[1]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Bola")) {
            Bola obj = new Bola(v[0]);
            result.append(String.format("Jari-Jari: %.2f\n", v[0]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Kerucut")) {
            Kerucut obj = new Kerucut(v[0], v[1]);
            result.append(String.format("Jari-Jari: %.2f, Tinggi: %.2f, Garis Pelukis: %.2f\n", v[0], v[1], v[2]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Kerucut Terpancung")) {
            KerucutTerpancung obj = new KerucutTerpancung(v[0], v[1], v[2]);
            result.append(String.format("Jari-Jari Bawah: %.2f, Jari-Jari Atas: %.2f, Tinggi: %.2f\n", v[0], v[1], v[2]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Prisma Segitiga")) {
            PrismaSegitiga obj = new PrismaSegitiga(v[0], v[1], v[2], v[3]);
            result.append(String.format("Alas Segitiga: %.2f, Tinggi Segitiga: %.2f, Sisi Miring: %.2f, Tinggi Prisma: %.2f\n", v[0], v[1], v[2], v[3]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Limas Segitiga")) {
            LimasSegitiga obj = new LimasSegitiga(v[0], v[1], v[2], v[3]);
            result.append(String.format("Alas Segitiga: %.2f, Tinggi Alas: %.2f, Tinggi Limas: %.2f, Tinggi Sisi Tegak: %.2f\n", v[0], v[1], v[2], v[3]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Prisma Belah Ketupat")) {
            PrismaBelahKetupat obj = new PrismaBelahKetupat(v[0], v[1], v[2], v[3]);
            result.append(String.format("D1: %.2f, D2: %.2f, Sisi: %.2f, Tinggi Prisma: %.2f\n", v[0], v[1], v[2], v[3]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Limas Belah Ketupat")) {
            LimasBelahKetupat obj = new LimasBelahKetupat(v[0], v[1], v[2], v[3]);
            result.append(String.format("D1: %.2f, D2: %.2f, Sisi: %.2f, Tinggi Limas: %.2f\n", v[0], v[1], v[2], v[3]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Limas Persegi")) {
            LimasPersegi obj = new LimasPersegi(v[0], v[1]);
            result.append(String.format("Sisi Alas: %.2f, Tinggi Limas: %.2f\n", v[0], v[1]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Tembereng Bola")) {
            TemberengBola obj = new TemberengBola(v[0], v[1]);
            result.append(String.format("Jari-Jari Bola: %.2f, Tinggi Tembereng: %.2f\n", v[0], v[1]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Juring Bola")) {
            JuringBola obj = new JuringBola(v[0], v[1]);
            result.append(String.format("Jari-Jari Bola: %.2f, Sudut: %.2f\n", v[0], v[1]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        } else if (currentShape.equals("Cincin Bola")) {
            CincinBola obj = new CincinBola(v[0], v[1]);
            result.append(String.format("Jari-Jari Bola: %.2f, Tinggi Cincin: %.2f\n", v[0], v[1]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
        }
        // ... tambahkan bentuk lain di sini sesuai kebutuhan fungsionalitas Anda ...
        else {
            result.append("Kalkulator untuk '").append(currentShape).append("' belum diimplementasikan.");
        }

        result.append("\n---------------------------------\n");
        outputArea.setText(result.toString());
    }

    /**
     * Menginisialisasi peta yang mendefinisikan bidang input yang diperlukan untuk setiap bentuk.
     * Kunci adalah nama bentuk, dan nilai adalah array string yang mewakili label input.
     * Bagian ini tetap sama karena ini adalah fungsionalitas inti.
     */
    private void initializeShapeFields() {
        // Lingkaran dan turunannya
        shapeFields.put("Lingkaran", new String[]{"Jari-Jari"});
        shapeFields.put("Juring Lingkaran", new String[]{"Jari-Jari", "Sudut (derajat)"});
        shapeFields.put("Tembereng Lingkaran", new String[]{"Jari-Jari", "Sudut (derajat)"});
        shapeFields.put("Tabung", new String[]{"Jari-Jari", "Tinggi"});
        shapeFields.put("Kerucut", new String[]{"Jari-Jari", "Tinggi"});
        shapeFields.put("Kerucut Terpancung", new String[]{"Jari-Jari Bawah", "Jari-Jari Atas", "Tinggi"});
        shapeFields.put("Bola", new String[]{"Jari-Jari"});
        shapeFields.put("Tembereng Bola", new String[]{"Jari-Jari Bola", "Tinggi Tembereng"});
        shapeFields.put("Juring Bola", new String[]{"Jari-Jari Bola", "Sudut (derajat)"});
        shapeFields.put("Cincin Bola", new String[]{"Jari-Jari Bola", "Tinggi Cincin"});

        // Persegi dan turunannya
        shapeFields.put("Persegi", new String[]{"Sisi"});
        shapeFields.put("Kubus", new String[]{"Sisi"});
        shapeFields.put("Limas Persegi", new String[]{"Sisi Alas", "Tinggi Limas"});

        // Persegi Panjang dan turunannya
        shapeFields.put("Persegi Panjang", new String[]{"Panjang", "Lebar"}); // Ini adalah Persegi Panjang 2D
        shapeFields.put("Balok", new String[]{"Panjang", "Lebar", "Tinggi"}); // Ini adalah Balok 3D
        shapeFields.put("Limas Persegi Panjang", new String[]{"Panjang", "Lebar", "Tinggi Limas", "Tinggi Sisi Tegak"});

        // Segitiga dan turunannya
        shapeFields.put("Segitiga", new String[]{"Alas", "Tinggi", "Sisi Miring"});
        shapeFields.put("Prisma Segitiga", new String[]{"Alas Segitiga", "Tinggi Segitiga", "Sisi Miring", "Tinggi Prisma"});
        shapeFields.put("Limas Segitiga", new String[]{"Alas Segitiga", "Tinggi Alas", "Tinggi Limas", "Tinggi Sisi Tegak"});

        // Trapesium dan turunannya
        shapeFields.put("Trapesium", new String[]{"Sisi Sejajar a", "Sisi Sejajar b", "Tinggi", "Sisi Miring 1", "Sisi Miring 2"});
        shapeFields.put("Prisma Trapesium", new String[]{"Sisi a", "Sisi b", "Tinggi Alas", "Sisi 1", "Sisi 2", "Tinggi Prisma"});
        shapeFields.put("Limas Trapesium", new String[]{"Sisi a", "Sisi b", "Tinggi Alas", "Sisi 1", "Sisi 2", "Tinggi Limas"});

        // Jajar Genjang dan turunannya
        shapeFields.put("Jajar Genjang", new String[]{"Alas", "Tinggi", "Sisi Miring"});
        shapeFields.put("Prisma Jajar Genjang", new String[]{"Alas", "Tinggi Alas", "Sisi Miring", "Tinggi Prisma"});
        shapeFields.put("Limas Jajar Genjang", new String[]{"Alas", "Tinggi Alas", "Sisi Miring", "Tinggi Limas"});

        // Belah Ketupat dan turunannya
        shapeFields.put("Belah Ketupat", new String[]{"Diagonal 1", "Diagonal 2", "Sisi"});
        shapeFields.put("Prisma Belah Ketupat", new String[]{"Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Prisma"});
        shapeFields.put("Limas Belah Ketupat", new String[]{"Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Limas"});

        // Layang-Layang dan turunannya
        shapeFields.put("Layang-Layang", new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang"});
        shapeFields.put("Prisma Layang-Layang", new String[]{"D1", "D2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma"});
        shapeFields.put("Limas Layang-Layang", new String[]{"D1", "D2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas"});
    }

    /**
     * Metode pembantu untuk menentukan kategori bentuk dasar untuk nama bentuk yang diberikan.
     * @param shapeName Nama spesifik dari bentuk.
     * @return Nama kategori bentuk dasar (misalnya, "Lingkaran", "Persegi").
     * Bagian ini tetap sama karena ini adalah fungsionalitas inti.
     */
    private String getBaseShape(String shapeName) {
        if (shapeName.contains("Lingkaran") || shapeName.contains("Tabung") || shapeName.contains("Kerucut") || shapeName.contains("Bola")) return "Lingkaran";
        if (shapeName.contains("Persegi") || shapeName.contains("Kubus")) return "Persegi";
        if (shapeName.contains("Persegi Panjang") || shapeName.contains("Balok")) return "Persegi Panjang";
        if (shapeName.contains("Segitiga")) return "Segitiga";
        if (shapeName.contains("Trapesium")) return "Trapesium";
        if (shapeName.contains("Jajar Genjang")) return "Jajar Genjang";
        if (shapeName.contains("Belah Ketupat")) return "Belah Ketupat";
        if (shapeName.contains("Layang-Layang")) return "Layang-Layang";
        return "";
    }


    /**
     * Metode utama untuk menjalankan aplikasi GUI.
     */
    public static void main(String[] args) {
        // Gunakan EventQueue.invokeLater untuk keamanan thread dalam aplikasi Swing
        EventQueue.invokeLater(() -> {
            try {
                KalkulatorGUI ex = new KalkulatorGUI();
                ex.setVisible(true);

                // Tambahkan shutdown hook untuk menutup ExecutorService saat aplikasi keluar
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    ex.executorService.shutdown(); // Menutup ExecutorService saat aplikasi ditutup
                    System.out.println("ExecutorService dimatikan.");
                }));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}