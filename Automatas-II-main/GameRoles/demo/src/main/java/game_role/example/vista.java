package game_role.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

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
import org.antlr.v4.runtime.Recognizer;
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

        languageBox = new JComboBox<>(new String[]{"Kotlin", "Python", "C", "Go", "C#"});
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
                String code = Files.readString(chooser.getSelectedFile().toPath(), StandardCharsets.UTF_8);
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
                Files.writeString(chooser.getSelectedFile().toPath(), inputArea.getText(), StandardCharsets.UTF_8);
                JOptionPane.showMessageDialog(this, "Archivo guardado con éxito.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream interceptorStream = new PrintStream(baos);

        System.setOut(interceptorStream);
        System.setErr(interceptorStream);

        StringBuilder reporte = new StringBuilder();
        reporte.append("========== RESULTADO DE EJECUCIÓN ==========\n\n");

        try {
            ParseTree tree = buildTree();

            LanguageCustomVisitor visitor = new LanguageCustomVisitor();
            visitor.visit(tree);

            System.out.flush();
            String salida = baos.toString();

            reporte.append("--- Mensajes de salida del programa ---\n");
            if (salida.isEmpty()) {
                reporte.append("(No hubo salidas / prints)\n");
            } else {
                reporte.append(salida);
            }

            reporte.append("\n--- Reporte de Errores ---\n");
            reporte.append("• Errores Léxicos: 0\n");
            reporte.append("• Errores Sintácticos: 0\n");
            reporte.append("• Errores Semánticos: 0\n");
            reporte.append("\n[✓] Ejecución finalizada con éxito.");

        } catch (Exception ex) {
            System.out.flush();
            String salida = baos.toString();

            reporte.append("--- Mensajes de salida del programa (Hasta el error) ---\n");
            if (salida.isEmpty()) {
                reporte.append("(Ninguna salida previa al error)\n");
            } else {
                reporte.append(salida);
            }

            reporte.append("\n--- Reporte de Errores ---\n");
            
            String msg = ex.getMessage();
            if (msg == null) msg = ex.toString();

            if (msg.contains("ERROR LÉXICO")) {
                reporte.append("• Errores Léxicos: 1\n  └ Detalle: ").append(msg.replace("ERROR LÉXICO: ", "")).append("\n");
                reporte.append("• Errores Sintácticos: 0\n• Errores Semánticos: 0\n");
            } else if (msg.contains("ERROR SINTÁCTICO")) {
                reporte.append("• Errores Léxicos: 0\n");
                reporte.append("• Errores Sintácticos: 1\n  └ Detalle: ").append(msg.replace("ERROR SINTÁCTICO: ", "")).append("\n");
                reporte.append("• Errores Semánticos: 0\n");
            } else if (msg.contains("[RS")) { 
                reporte.append("• Errores Léxicos: 0\n• Errores Sintácticos: 0\n");
                reporte.append("• Errores Semánticos: 1\n  └ Detalle: ").append(msg).append("\n");
            } else {
                reporte.append("• Error General / Interno de Java:\n  └ ").append(msg).append("\n");
            }

            reporte.append("\n[X] Ejecución detenida debido a errores.");
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
            outputArea.setText(reporte.toString());
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
            String codigoTraducido = "";

            switch (lang) {
                case "Kotlin": codigoTraducido = new KotlinTraductor().visit(tree); break;
                case "Python": codigoTraducido = new PythonTraductor().visit(tree); break;
                case "C":      codigoTraducido = new CTraductor().visit(tree); break;
                case "Go":     codigoTraducido = new GoTraductor().visit(tree); break;
                case "C#":     codigoTraducido = new CSharpTraductor().visit(tree); break;
                default:       codigoTraducido = "// Lenguaje no soportado."; break;
            }

            outputArea.setText(codigoTraducido);

        } catch (Exception ex) {
            outputArea.setText("No se puede traducir el código porque contiene errores:\n" + ex.getMessage());
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

            switch (lang) {
                case "Kotlin":
                    codigoTraducido = new KotlinTraductor().visit(tree);
                    extension = "kt"; descripcionFiltro = "Archivos de Kotlin (*.kt)"; break;
                case "Python":
                    codigoTraducido = new PythonTraductor().visit(tree);
                    extension = "py"; descripcionFiltro = "Archivos de Python (*.py)"; break;
                case "C":
                    codigoTraducido = new CTraductor().visit(tree);
                    extension = "c"; descripcionFiltro = "Archivos de C (*.c)"; break;
                case "Go":
                    codigoTraducido = new GoTraductor().visit(tree);
                    extension = "go"; descripcionFiltro = "Archivos de Go (*.go)"; break;
                case "C#":
                    codigoTraducido = new CSharpTraductor().visit(tree);
                    extension = "cs"; descripcionFiltro = "Archivos de C# (*.cs)"; break;
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

                Files.writeString(archivoDestino.toPath(), codigoTraducido, StandardCharsets.UTF_8);
                JOptionPane.showMessageDialog(this, "Código en " + lang + " exportado con éxito.", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            outputArea.setText("Error al exportar la traducción (hay un error en tu código):\n" + ex.getMessage());
        }
    }

    private ParseTree buildTree() {
        CharStream input = CharStreams.fromString(inputArea.getText());
        GameRoleLexer lexer = new GameRoleLexer(input);
        
        lexer.removeErrorListeners();
        lexer.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                throw new RuntimeException("ERROR LÉXICO: [Línea " + line + ":" + charPositionInLine + "] -> " + msg);
            }
        });

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GameRoleParser parser = new GameRoleParser(tokens);
        
        parser.removeErrorListeners();
        parser.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                throw new RuntimeException("ERROR SINTÁCTICO: [Línea " + line + ":" + charPositionInLine + "] -> Se esperaba otra estructura, pero se encontró un error: " + msg);
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
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(ACCENT); }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(PANEL_DARK); }
        });
        return btn;
    }

    private void styleCombo(JComboBox<?> combo) {
        combo.setBackground(PANEL_DARK);
        combo.setForeground(TEXT_DARK);
    }
}