import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// Import all shape and exception classes
import strata2D.*;
import strata3D.*;
import Exception.ArgumentException;

/**
 * A GUI application for calculating properties of various geometric shapes.
 * This class creates a window with a menu bar to select a shape.
 * Based on the selection, it dynamically generates input fields for the shape's dimensions.
 * A "Hitung" button performs the calculations and displays the results.
 */
public class KalkulatorGUI extends JFrame {

    private JTextArea outputArea;
    private JPanel inputPanel;
    private JLabel titleLabel;
    private JButton calculateButton;

    private String currentShape;
    private java.util.List<JTextField> inputFields = new ArrayList<>();
    private java.util.List<JLabel> inputLabels = new ArrayList<>();
    // A map to store the labels for each shape's input fields.
    private final Map<String, String[]> shapeFields = new LinkedHashMap<>();

    public KalkulatorGUI() {
        // Initialize the map with required fields for each shape
        initializeShapeFields();
        // Setup the main window
        initUI();
    }

    /**
     * Initializes the main frame and its components.
     */
    private void initUI() {
        setTitle("Kalkulator Geometri Berbasis Bentuk Dasar");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set up the menu bar
        setJMenuBar(createMenuBar());

        // Main container panel with a border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel for title and dynamic inputs
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Pilih bentuk geometri dari menu di atas");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // Dynamic grid layout
        inputPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        topPanel.add(titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(inputPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Calculate button
        calculateButton = new JButton("Hitung");
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        calculateButton.addActionListener(new CalculateButtonListener());

// Create a new panel just for the button
        JPanel buttonPanel = new JPanel(); // Uses FlowLayout by default, which respects preferred sizes
        buttonPanel.add(calculateButton);

// Add the new panel to the center instead of the button itself
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Output area
        outputArea = new JTextArea(10, 50); // Suggest a size of 10 rows
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Creates and returns the main menu bar for the application.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Define menu structure
        String[] menuTitles = {"Lingkaran", "Persegi", "Persegi Panjang", "Segitiga", "Trapesium", "Jajar Genjang", "Belah Ketupat", "Layang-Layang"};

        for(String title : menuTitles) {
            JMenu menu = new JMenu(title);
            // Get all shapes that belong to this base shape category from the map
            for (String shapeName : shapeFields.keySet()) {
                if(getBaseShape(shapeName).equals(title)) {
                    JMenuItem menuItem = new JMenuItem(shapeName);
                    menuItem.addActionListener(new ShapeMenuItemListener(shapeName));
                    menu.add(menuItem);
                }
            }
            menuBar.add(menu);
        }

        return menuBar;
    }

    /**
     * Updates the input panel with fields required for the selected shape.
     * @param shapeName The name of the shape to create an input form for.
     */
    private void updateInputPanel(String shapeName) {
        currentShape = shapeName;
        titleLabel.setText("Input untuk " + shapeName);

        // Clear previous components
        inputPanel.removeAll();
        inputFields.clear();
        inputLabels.clear();

        // Get the required fields for the selected shape
        String[] fields = shapeFields.get(shapeName);
        if (fields != null) {
            for (String fieldName : fields) {
                JLabel label = new JLabel(fieldName + ":", SwingConstants.RIGHT);
                JTextField textField = new JTextField(10);

                inputLabels.add(label);
                inputFields.add(textField);

                inputPanel.add(label);
                inputPanel.add(textField);
            }
        }

        // Refresh the panel
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    /**
     * Listener for menu items. Updates the input panel when a shape is selected.
     */
    private class ShapeMenuItemListener implements ActionListener {
        private String shapeName;

        public ShapeMenuItemListener(String shapeName) {
            this.shapeName = shapeName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updateInputPanel(shapeName);
        }
    }

    /**
     * Listener for the "Hitung" button. Performs calculations based on the current shape and inputs.
     */
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentShape == null || currentShape.isEmpty()) {
                outputArea.setText("Error: Silakan pilih bentuk terlebih dahulu.");
                return;
            }

            // Get values from text fields
            double[] values = new double[inputFields.size()];
            try {
                for (int i = 0; i < inputFields.size(); i++) {
                    values[i] = Double.parseDouble(inputFields.get(i).getText());
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Input tidak valid. Harap masukkan angka yang benar.");
                return;
            }

            // Perform calculation based on the selected shape
            try {
                calculateAndDisplay(values);
            } catch (ArgumentException ex) {
                outputArea.setText("Error: " + ex.getMessage());
            } catch (Exception ex) {
                outputArea.setText("Terjadi error tak terduga: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * Instantiates the correct shape, calculates its properties, and displays the result.
     * @param v An array of double values from the input fields.
     * @throws ArgumentException if the input values are invalid for the shape.
     */
    private void calculateAndDisplay(double... v) throws ArgumentException {
        StringBuilder result = new StringBuilder();
        result.append("--- Hasil untuk ").append(currentShape).append(" ---\n");

        // 2D Shapes
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

        // 3D Shapes
        else if (currentShape.equals("Kubus")) {
            Kubus obj = new Kubus(v[0]);
            result.append(String.format("Sisi: %.2f\n", v[0]));
            result.append(String.format("Volume: %.2f\n", obj.hitungVolume()));
            result.append(String.format("Luas Permukaan: %.2f\n", obj.hitungLuasPermukaan()));
            result.append(String.format("Panjang Rusuk: %.2f\n", obj.hitungPanjangRusuk()));
        } else if (currentShape.equals("Balok")) {
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
            Kerucut obj = new Kerucut(v[0], v[1], v[2]);
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
        // ... add other shapes here ...
        else {
            result.append("Kalkulator untuk '").append(currentShape).append("' belum diimplementasikan.");
        }

        result.append("---------------------------------\n");
        outputArea.setText(result.toString());
    }

    /**
     * Initializes the map that defines the input fields required for each shape.
     * The key is the shape name, and the value is an array of strings representing the input labels.
     */
    private void initializeShapeFields() {
        // Lingkaran and its derivatives
        shapeFields.put("Lingkaran", new String[]{"Jari-Jari"});
        shapeFields.put("Juring Lingkaran", new String[]{"Jari-Jari", "Sudut (derajat)"});
        shapeFields.put("Tembereng Lingkaran", new String[]{"Jari-Jari", "Sudut (derajat)"});
        shapeFields.put("Tabung", new String[]{"Jari-Jari", "Tinggi"});
        shapeFields.put("Kerucut", new String[]{"Jari-Jari", "Tinggi", "Garis Pelukis"});
        shapeFields.put("Kerucut Terpancung", new String[]{"Jari-Jari Bawah", "Jari-Jari Atas", "Tinggi"});
        shapeFields.put("Bola", new String[]{"Jari-Jari"});
        shapeFields.put("Tembereng Bola", new String[]{"Jari-Jari Bola", "Tinggi Tembereng"});
        shapeFields.put("Juring Bola", new String[]{"Jari-Jari Bola", "Sudut (derajat)"});
        shapeFields.put("Cincin Bola", new String[]{"Jari-Jari Bola", "Tinggi Cincin"});

        // Persegi and its derivatives
        shapeFields.put("Persegi", new String[]{"Sisi"});
        shapeFields.put("Kubus", new String[]{"Sisi"});
        shapeFields.put("Limas Persegi", new String[]{"Sisi Alas", "Tinggi Limas"});

        // Persegi Panjang and its derivatives
        shapeFields.put("Persegi Panjang", new String[]{"Panjang", "Lebar"});
        shapeFields.put("Balok", new String[]{"Panjang", "Lebar", "Tinggi"});
        shapeFields.put("Limas Persegi Panjang", new String[]{"Panjang", "Lebar", "Tinggi Limas", "Tinggi Sisi Tegak"});

        // Segitiga and its derivatives
        shapeFields.put("Segitiga", new String[]{"Alas", "Tinggi", "Sisi Miring"});
        shapeFields.put("Prisma Segitiga", new String[]{"Alas Segitiga", "Tinggi Segitiga", "Sisi Miring", "Tinggi Prisma"});
        shapeFields.put("Limas Segitiga", new String[]{"Alas Segitiga", "Tinggi Alas", "Tinggi Limas", "Tinggi Sisi Tegak"});

        // Trapesium and its derivatives
        shapeFields.put("Trapesium", new String[]{"Sisi Sejajar a", "Sisi Sejajar b", "Tinggi", "Sisi Miring 1", "Sisi Miring 2"});
        shapeFields.put("Prisma Trapesium", new String[]{"Sisi a", "Sisi b", "Tinggi Alas", "Sisi 1", "Sisi 2", "Tinggi Prisma"});
        shapeFields.put("Limas Trapesium", new String[]{"Sisi a", "Sisi b", "Tinggi Alas", "Sisi 1", "Sisi 2", "Tinggi Limas"});

        // Jajar Genjang and its derivatives
        shapeFields.put("Jajar Genjang", new String[]{"Alas", "Tinggi", "Sisi Miring"});
        shapeFields.put("Prisma Jajar Genjang", new String[]{"Alas", "Tinggi Alas", "Sisi Miring", "Tinggi Prisma"});
        shapeFields.put("Limas Jajar Genjang", new String[]{"Alas", "Tinggi Alas", "Sisi Miring", "Tinggi Limas"});

        // Belah Ketupat and its derivatives
        shapeFields.put("Belah Ketupat", new String[]{"Diagonal 1", "Diagonal 2", "Sisi"});
        shapeFields.put("Prisma Belah Ketupat", new String[]{"Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Prisma"});
        shapeFields.put("Limas Belah Ketupat", new String[]{"Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Limas"});

        // Layang-Layang and its derivatives
        shapeFields.put("Layang-Layang", new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang"});
        shapeFields.put("Prisma Layang-Layang", new String[]{"D1", "D2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma"});
        shapeFields.put("Limas Layang-Layang", new String[]{"D1", "D2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas"});
    }

    /**
     * Helper method to determine the base shape category for any given shape name.
     * @param shapeName The specific name of the shape.
     * @return The name of the base shape category (e.g., "Lingkaran", "Persegi").
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
     * The main method to run the GUI application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KalkulatorGUI ex = new KalkulatorGUI();
            ex.setVisible(true);
        });
    }
}