package game_role.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Element;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class vista extends JFrame {

        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new vista().setVisible(true));
    }

    private JTextArea inputArea;
    private JTextArea linesArea; 
    private JTextArea outputArea;
    private JComboBox<String> languageBox;

    private static final Color BG_DARK = new Color(30, 30, 30);
    private static final Color PANEL_DARK = new Color(45, 45, 45);
    private static final Color TEXT_DARK = new Color(220, 220, 220);
    private static final Color LINE_NUM_COLOR = new Color(133, 133, 133); 
    private static final Color ACCENT = new Color(0, 122, 204);

    public vista() {
        setTitle("GameRole IDE");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(PANEL_DARK);

        JButton importBtn = createButton("Importar");
        JButton saveBtn = createButton("Guardar");
        JButton runBtn = createButton("Ejecutar");
        JButton translateBtn = createButton("Traducir");
        JButton exportTradBtn = createButton("Exportar Traducción");

        languageBox = new JComboBox<>(new String[]{"Kotlin"});
        styleCombo(languageBox);

        topBar.add(importBtn);
        topBar.add(saveBtn);
        topBar.add(runBtn);
        topBar.add(translateBtn);
        topBar.add(exportTradBtn);
        
        JLabel lblLang = new JLabel("  Lenguaje: ");
        lblLang.setForeground(TEXT_DARK);
        topBar.add(lblLang);
        topBar.add(languageBox);

        inputArea = new JTextArea();
        styleEditor(inputArea);

        linesArea = new JTextArea(" 1 ");
        styleEditor(linesArea);
        linesArea.setBackground(new Color(40, 40, 40)); 
        linesArea.setForeground(LINE_NUM_COLOR);
        linesArea.setEditable(false);
        linesArea.setFocusable(false);

        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setRowHeaderView(linesArea);

        inputArea.getDocument().addDocumentListener(new DocumentListener() {
            private String getLineNumbersText() {
                int caretPosition = inputArea.getDocument().getLength();
                Element root = inputArea.getDocument().getDefaultRootElement();
                StringBuilder lineNumbers = new StringBuilder(" 1 \n");
                for (int i = 2; i <= root.getElementIndex(caretPosition) + 1; i++) {
                    lineNumbers.append(" ").append(i).append(" \n");
                }
                return lineNumbers.toString();
            }

            @Override public void changedUpdate(DocumentEvent e) { linesArea.setText(getLineNumbersText()); }
            @Override public void insertUpdate(DocumentEvent e) { linesArea.setText(getLineNumbersText()); }
            @Override public void removeUpdate(DocumentEvent e) { linesArea.setText(getLineNumbersText()); }
        });

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        styleEditor(outputArea);

        JScrollPane outputScroll = new JScrollPane(outputArea);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                inputScroll,
                outputScroll);

        splitPane.setDividerLocation(450);
        splitPane.setBackground(BG_DARK);

        add(topBar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        getContentPane().setBackground(BG_DARK);

        importBtn.addActionListener(e -> importFile());
        saveBtn.addActionListener(e -> saveFile());
        runBtn.addActionListener(e -> runCode());
        translateBtn.addActionListener(e -> translateCode());
        exportTradBtn.addActionListener(e -> exportTranslation());
    }

    private void importFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String code = Files.readString(chooser.getSelectedFile().toPath());
                inputArea.setText(code);
            } catch (Exception ex) {
                outputArea.setText("Error importando: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Files.writeString(chooser.getSelectedFile().toPath(), inputArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo original guardado con éxito.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                outputArea.setText("Error guardando: " + ex.getMessage());
            }
        }
    }

    private void runCode() {
        if (inputArea.getText().trim().isEmpty()) {
            outputArea.setText("El editor de código está vacío.");
            return;
        }

        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;

        try {
            ParseTree tree = buildTree();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream interceptorStream = new PrintStream(baos);
            System.setOut(interceptorStream);
            System.setErr(interceptorStream);

            LanguageCustomVisitor visitor = new LanguageCustomVisitor();
            visitor.visit(tree);

            System.out.flush();
            System.setOut(originalOut);
            System.setErr(originalErr);

            outputArea.setText(baos.toString());

        } catch (Exception ex) {
            System.setOut(originalOut);
            System.setErr(originalErr);
            outputArea.setText("Error de Ejecución:\n" + ex.getMessage());
        }
    }

    private void translateCode() {
        if (inputArea.getText().trim().isEmpty()) {
            outputArea.setText("El editor de código está vacío.");
            return;
        }
        try {
            ParseTree tree = buildTree();
            String lang = languageBox.getSelectedItem().toString();

            if (lang.equals("Kotlin")) {
                KotlinTraductor kt = new KotlinTraductor();
                outputArea.setText(kt.visit(tree));
            }

        } catch (Exception ex) {
            outputArea.setText("Error de Análisis/Traducción:\n" + ex.getMessage());
        }
    }

    private void exportTranslation() {
        if (inputArea.getText().trim().isEmpty()) {
            outputArea.setText("El editor de código está vacío. No hay nada que traducir.");
            return;
        }

        try {
            ParseTree tree = buildTree();
            String lang = languageBox.getSelectedItem().toString();
            String codigoTraducido = "";
            String extension = "";
            String descripcionFiltro = "";

            if (lang.equals("Kotlin")) {
                KotlinTraductor kt = new KotlinTraductor();
                codigoTraducido = kt.visit(tree);
                extension = "kt";
                descripcionFiltro = "Archivos de Kotlin (*.kt)";
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Exportar Código Traducido a " + lang);

            FileNameExtensionFilter filter = new FileNameExtensionFilter(descripcionFiltro, extension);
            chooser.setFileFilter(filter);

            chooser.setSelectedFile(new File("traduccion." + extension));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivoDestino = chooser.getSelectedFile();
                String path = archivoDestino.getAbsolutePath();
                
                if (!path.toLowerCase().endsWith("." + extension)) {
                    archivoDestino = new File(path + "." + extension);
                }

                Files.writeString(archivoDestino.toPath(), codigoTraducido);
                JOptionPane.showMessageDialog(this, "Código en " + lang + " exportado con éxito.", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            outputArea.setText("Error al exportar la traducción:\n" + ex.getMessage());
        }
    }

    private ParseTree buildTree() {
        CharStream input = CharStreams.fromString(inputArea.getText());
        GameRoleLexer lexer = new GameRoleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GameRoleParser parser = new GameRoleParser(tokens);
        
        parser.removeErrorListeners();
        parser.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
            public void syntaxError(Object recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                throw new RuntimeException("Error Sintáctico [Línea " + line + ":" + charPositionInLine + "] -> " + msg);
            }
        });

        return parser.programa();
    }

    private void styleEditor(JTextArea area) {
        area.setBackground(BG_DARK);
        area.setForeground(TEXT_DARK);
        area.setCaretColor(Color.WHITE);
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(PANEL_DARK);
        btn.setForeground(TEXT_DARK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(ACCENT); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(PANEL_DARK); }
        });
        return btn;
    }

    private void styleCombo(JComboBox<?> combo) {
        combo.setBackground(PANEL_DARK);
        combo.setForeground(TEXT_DARK);
    }
}