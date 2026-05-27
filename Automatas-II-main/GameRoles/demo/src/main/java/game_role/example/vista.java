package game_role.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
    import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

public class vista extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new vista().setVisible(true));
    }

    private JTextPane inputArea; 
    private JTextArea linesArea; 
    private JTextArea outputArea;
    private JScrollPane inputScroll;
    private JScrollPane outputScroll;
    private JComboBox<String> languageBox;
    private JPanel topBar;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JSplitPane splitPane;
    private JButton themeBtn;
    private JButton helpBtn;
    private JLabel lblLang;
    
    private JPanel helpPanel;
    private JTextArea helpText;

    private List<JButton> standardButtons = new ArrayList<>();

    private static final Color XBOX_GREEN = new Color(16, 124, 16); 
    private static final Color XBOX_GREEN_HOVER = new Color(57, 255, 20); 
    private static final Color LINE_NUM_COLOR = new Color(130, 130, 130);

    private Color bgCurrent;
    private Color panelCurrent;
    private Color textCurrent;
    private boolean isLightTheme = false;
    private boolean isHighlighting = false;

    public vista() {
        setTitle("GameRole IDE");
        setSize(1250, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        bgCurrent = new Color(18, 18, 18); 
        panelCurrent = new Color(35, 35, 35);
        textCurrent = new Color(240, 240, 240);

        topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftPanel.setOpaque(false);
        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        JButton importBtn = createStandardButton("Importar");
        JButton saveBtn = createStandardButton("Guardar");
        JButton translateBtn = createStandardButton("Traducir");
        JButton exportTradBtn = createStandardButton("Exportar");

        languageBox = new JComboBox<>(new String[]{"Kotlin", "Python", "C", "Go", "C#"});
        styleCombo(languageBox);
        lblLang = new JLabel("Destino:");
        lblLang.setFont(new Font("Segoe UI", Font.BOLD, 12));

        leftPanel.add(importBtn);
        leftPanel.add(saveBtn);
        leftPanel.add(new JLabel("  |  ")); 
        leftPanel.add(translateBtn);
        leftPanel.add(exportTradBtn);
        leftPanel.add(lblLang);
        leftPanel.add(languageBox);

        helpBtn = createStandardButton("Guía");
        themeBtn = createStandardButton("Tema: Blanco");
        
        JButton runBtn = new JButton("RUN");
        styleRunButton(runBtn);

        rightPanel.add(helpBtn);
        rightPanel.add(themeBtn);
        rightPanel.add(runBtn);

        topBar.add(leftPanel, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);

        inputArea = new JTextPane() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                java.awt.Component parent = getParent();
                if (parent instanceof javax.swing.JViewport) {
                    return getUI().getPreferredSize(this).width <= parent.getSize().width;
                }
                return false;
            }
        };
        inputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        inputArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        linesArea = new JTextArea(" 1 \n");
        linesArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        linesArea.setForeground(LINE_NUM_COLOR);
        linesArea.setEditable(false);
        linesArea.setFocusable(false);
        linesArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        inputScroll = new JScrollPane(inputArea);
        inputScroll.setRowHeaderView(linesArea);
        inputScroll.setBorder(null);

        inputArea.getDocument().addDocumentListener(new DocumentListener() {
            private void updateEditor() {
                SwingUtilities.invokeLater(() -> {
                    int lines = inputArea.getDocument().getDefaultRootElement().getElementCount();
                    StringBuilder lineNumbers = new StringBuilder();
                    for (int i = 1; i <= lines; i++) {
                        lineNumbers.append(" ").append(i).append(" \n");
                    }
                    linesArea.setText(lineNumbers.toString());
                    
                    applySyntaxHighlighting();
                });
            }
            @Override public void changedUpdate(DocumentEvent e) { }
            @Override public void insertUpdate(DocumentEvent e) { updateEditor(); }
            @Override public void removeUpdate(DocumentEvent e) { updateEditor(); }
        });

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, XBOX_GREEN));

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScroll, outputScroll);
        splitPane.setDividerLocation(450);
        splitPane.setDividerSize(5);
        splitPane.setBorder(null);

        helpPanel = new JPanel(new BorderLayout());
        helpPanel.setPreferredSize(new Dimension(300, 0));
        helpPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, XBOX_GREEN));
        
        JLabel helpTitle = new JLabel("DICCIONARIO GAMEROLE", SwingConstants.CENTER);
        helpTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        helpTitle.setForeground(XBOX_GREEN);
        helpTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        helpPanel.add(helpTitle, BorderLayout.NORTH);

        helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setFocusable(false);
        helpText.setFont(new Font("Consolas", Font.PLAIN, 13));
        helpText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        helpText.setText(getGrammarHelpText());
        
        JScrollPane helpScroll = new JScrollPane(helpText);
        helpScroll.setBorder(null);
        helpPanel.add(helpScroll, BorderLayout.CENTER);
        
        helpPanel.setVisible(false);

        add(topBar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(helpPanel, BorderLayout.EAST);
        
        applyTheme();

        setHelloWorldCode();

        importBtn.addActionListener(e -> importFile());
        saveBtn.addActionListener(e -> saveFile());
        runBtn.addActionListener(e -> runCode());
        translateBtn.addActionListener(e -> translateCode());
        exportTradBtn.addActionListener(e -> exportTranslation());
        themeBtn.addActionListener(e -> toggleTheme());
        helpBtn.addActionListener(e -> helpPanel.setVisible(!helpPanel.isVisible())); // Alternar visibilidad
    }

    private void setHelloWorldCode() {
        String welcomeCode = 
            "// ¡Bienvenido a GameRole IDE!\n" +
            "// Ejecuta (RUN) este código para probar el compilador.\n\n" +
            "Juego HolaMundo Mundo_abierto\n\n" +
            "    Simulacion nivel = 1\n" +
            "    Simulacion_vida jugador = \"Heroe\"\n\n" +
            "    FPS(\"¡Hola, Mundo desde GameRole!\")\n" +
            "    FPS(\"Bienvenido \" Lucha jugador Lucha \" al Nivel \" Lucha nivel)\n\n" +
            "Sandbox\n";
        inputArea.setText(welcomeCode);
    }

    private String getGrammarHelpText() {
        return 
            "--- ESTRUCTURAS BÁSICAS ---\n" +
            "Juego [ID] Mundo_abierto \n" +
            "  -> Inicia el programa (main)\n" +
            "Sandbox \n" +
            "  -> Cierre de bloque ( '}' )\n\n" +

            "--- TIPOS DE DATOS ---\n" +
            "Simulacion              -> int\n" +
            "Simulacion_vida         -> string\n" +
            "Simulacion_construccion -> bool\n" +
            "Simulacion_vuelo        -> float\n\n" +

            "--- IMPRESIÓN ---\n" +
            "FPS(\"...\") / TPS(\"...\") -> print()\n\n" +

            "--- CONTROL DE FLUJO ---\n" +
            "Accion(...)             -> if\n" +
            "Accion_aventura(...)    -> else if\n" +
            "Aventura(...)           -> for\n" +
            "Supervivencia(...)      -> while\n\n" +

            "--- OPERADORES MATEMÁTICOS ---\n" +
            "Lucha          -> Suma (+)\n" +
            "Beat_em_up     -> Resta (-)\n" +
            "Hack_and_slash -> Mult (*)\n" +
            "Soulslike      -> Div (/)\n" +
            "Roguelike      -> Modulo (%)\n\n" +

            "--- COMPARADORES LÓGICOS ---\n" +
            "Estrategia     -> Mayor que (>)\n" +
            "RTS            -> Menor que (<)\n" +
            "TBS            -> Igual a (==)\n" +
            "CuatroX        -> Mayor o igual (>=)\n" +
            "MOBA           -> Menor o igual (<=)\n";
    }

    private void updateStyles() {
        Style defaultStyle = inputArea.getStyle("Default");
        if (defaultStyle == null) defaultStyle = inputArea.addStyle("Default", null);
        StyleConstants.setForeground(defaultStyle, textCurrent);
        StyleConstants.setFontFamily(defaultStyle, "Consolas");
        StyleConstants.setFontSize(defaultStyle, 15);

        Style kwStyle = inputArea.getStyle("Keyword");
        if (kwStyle == null) kwStyle = inputArea.addStyle("Keyword", null);
        StyleConstants.setForeground(kwStyle, isLightTheme ? new Color(0, 0, 255) : new Color(86, 156, 214));
        StyleConstants.setBold(kwStyle, true);

        Style typeStyle = inputArea.getStyle("Type");
        if (typeStyle == null) typeStyle = inputArea.addStyle("Type", null);
        StyleConstants.setForeground(typeStyle, isLightTheme ? new Color(38, 127, 153) : new Color(78, 201, 176));
        StyleConstants.setBold(typeStyle, false);

        Style opStyle = inputArea.getStyle("Operator");
        if (opStyle == null) opStyle = inputArea.addStyle("Operator", null);
        StyleConstants.setForeground(opStyle, isLightTheme ? new Color(175, 0, 219) : new Color(197, 134, 192));
        StyleConstants.setBold(opStyle, false);

        Style funcStyle = inputArea.getStyle("Function");
        if (funcStyle == null) funcStyle = inputArea.addStyle("Function", null);
        StyleConstants.setForeground(funcStyle, isLightTheme ? new Color(121, 94, 38) : new Color(220, 220, 170));
        StyleConstants.setBold(funcStyle, false);

        Style strStyle = inputArea.getStyle("String");
        if (strStyle == null) strStyle = inputArea.addStyle("String", null);
        StyleConstants.setForeground(strStyle, isLightTheme ? new Color(163, 21, 21) : new Color(206, 145, 120));
        StyleConstants.setBold(strStyle, false);

        Style numStyle = inputArea.getStyle("Number");
        if (numStyle == null) numStyle = inputArea.addStyle("Number", null);
        StyleConstants.setForeground(numStyle, isLightTheme ? new Color(9, 134, 88) : new Color(181, 206, 168));
        StyleConstants.setBold(numStyle, false);

        Style commentStyle = inputArea.getStyle("Comment");
        if (commentStyle == null) commentStyle = inputArea.addStyle("Comment", null);
        StyleConstants.setForeground(commentStyle, new Color(106, 153, 85));
        StyleConstants.setBold(commentStyle, false);
    }

    private void applySyntaxHighlighting() {
        if (isHighlighting) return;
        
        SwingUtilities.invokeLater(() -> {
            isHighlighting = true;
            try {
                StyledDocument doc = inputArea.getStyledDocument();
                String text = doc.getText(0, doc.getLength());
                
                doc.setCharacterAttributes(0, text.length(), inputArea.getStyle("Default"), true);

                String regexKW = "\\b(Juego|Mundo_abierto|Sandbox|Accion_aventura|Accion|Aventura|Supervivencia)\\b";
                String regexType = "\\b(Simulacion_vida|Simulacion_construccion|Simulacion_vuelo|Simulacion)\\b";
                String regexOp = "\\b(Estrategia|RTS|TBS|CuatroX|MOBA|Lucha|Beat_em_up|Hack_and_slash|Soulslike|Roguelike)\\b";
                String regexFunc = "\\b(FPS|TPS)\\b";

                Matcher funcMatcher = Pattern.compile(regexFunc).matcher(text);
                while (funcMatcher.find()) doc.setCharacterAttributes(funcMatcher.start(), funcMatcher.end() - funcMatcher.start(), inputArea.getStyle("Function"), false);

                Matcher typeMatcher = Pattern.compile(regexType).matcher(text);
                while (typeMatcher.find()) doc.setCharacterAttributes(typeMatcher.start(), typeMatcher.end() - typeMatcher.start(), inputArea.getStyle("Type"), false);

                Matcher opMatcher = Pattern.compile(regexOp).matcher(text);
                while (opMatcher.find()) doc.setCharacterAttributes(opMatcher.start(), opMatcher.end() - opMatcher.start(), inputArea.getStyle("Operator"), false);

                Matcher kwMatcher = Pattern.compile(regexKW).matcher(text);
                while (kwMatcher.find()) doc.setCharacterAttributes(kwMatcher.start(), kwMatcher.end() - kwMatcher.start(), inputArea.getStyle("Keyword"), false);

                Matcher numMatcher = Pattern.compile("\\b\\d+(\\.\\d+)?\\b").matcher(text);
                while (numMatcher.find()) doc.setCharacterAttributes(numMatcher.start(), numMatcher.end() - numMatcher.start(), inputArea.getStyle("Number"), false);

                Matcher strMatcher = Pattern.compile("\"[^\"]*\"").matcher(text);
                while (strMatcher.find()) doc.setCharacterAttributes(strMatcher.start(), strMatcher.end() - strMatcher.start(), inputArea.getStyle("String"), false);

                Matcher commentMatcher = Pattern.compile("//.*").matcher(text);
                while (commentMatcher.find()) doc.setCharacterAttributes(commentMatcher.start(), commentMatcher.end() - commentMatcher.start(), inputArea.getStyle("Comment"), false);

            } catch (Exception ex) {
            } finally {
                isHighlighting = false;
            }
        });
    }

    private void toggleTheme() {
        isLightTheme = !isLightTheme;
        if (isLightTheme) {
            bgCurrent = Color.WHITE;
            panelCurrent = new Color(240, 240, 240);
            textCurrent = Color.BLACK;
            themeBtn.setText("Tema: Negro");
        } else {
            bgCurrent = new Color(18, 18, 18); 
            panelCurrent = new Color(35, 35, 35); 
            textCurrent = new Color(240, 240, 240); 
            themeBtn.setText("Tema: Blanco");
        }
        applyTheme();
    }

    private void applyTheme() {
        getContentPane().setBackground(bgCurrent);
        topBar.setBackground(panelCurrent);
        inputArea.setBackground(bgCurrent);
        outputArea.setBackground(bgCurrent);
        splitPane.setBackground(bgCurrent);
        linesArea.setBackground(panelCurrent);
        languageBox.setBackground(bgCurrent);
        languageBox.setForeground(textCurrent);
        lblLang.setForeground(textCurrent);

        helpPanel.setBackground(panelCurrent);
        helpText.setBackground(panelCurrent);
        helpText.setForeground(textCurrent);
        
        inputScroll.getViewport().setBackground(bgCurrent);
        outputScroll.getViewport().setBackground(bgCurrent);
        
        inputArea.setCaretColor(XBOX_GREEN_HOVER); 
        outputArea.setForeground(textCurrent);

        for (JButton btn : standardButtons) {
            btn.setBackground(panelCurrent);
            btn.setForeground(textCurrent);
        }
        
        updateStyles(); 
        applySyntaxHighlighting(); 
    }

    private JButton createStandardButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) { 
                btn.setBackground(XBOX_GREEN_HOVER); 
                btn.setForeground(Color.BLACK); 
            }
            @Override
            public void mouseExited(MouseEvent evt) { 
                btn.setBackground(panelCurrent); 
                btn.setForeground(textCurrent);
            }
        });
        standardButtons.add(btn);
        return btn;
    }

    private void styleRunButton(JButton btn) {
        btn.setBackground(XBOX_GREEN);
        btn.setForeground(Color.BLACK); 
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) { btn.setBackground(XBOX_GREEN_HOVER); }
            @Override
            public void mouseExited(MouseEvent evt) { btn.setBackground(XBOX_GREEN); }
        });
    }

    private void styleCombo(JComboBox<?> combo) {
        combo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            reporte.append(salida.isEmpty() ? "(No hubo salidas)\n" : salida);

            reporte.append("\n--- Reporte de Errores ---\n");
            reporte.append("• Errores Léxicos: 0\n• Errores Sintácticos: 0\n• Errores Semánticos: 0\n");
            reporte.append("\n[✓] Ejecución finalizada con éxito.");

        } catch (Exception ex) {
            System.out.flush();
            String salida = baos.toString();

            reporte.append("--- Mensajes de salida del programa (Hasta el error) ---\n");
            reporte.append(salida.isEmpty() ? "(Ninguna salida previa al error)\n" : salida);

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
            String codigoTraducido = switch (lang) {
                case "Kotlin" -> new KotlinTraductor().visit(tree);
                case "Python" -> new PythonTraductor().visit(tree);
                case "C" -> new CTraductor().visit(tree);
                case "Go" -> new GoTraductor().visit(tree);
                case "C#" -> new CSharpTraductor().visit(tree);
                default -> "// Lenguaje no soportado.";
            };
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
            String descFiltro = "";

            switch (lang) {
                case "Kotlin": codigoTraducido = new KotlinTraductor().visit(tree); extension = "kt"; descFiltro = "Archivos Kotlin (*.kt)"; break;
                case "Python": codigoTraducido = new PythonTraductor().visit(tree); extension = "py"; descFiltro = "Archivos Python (*.py)"; break;
                case "C":      codigoTraducido = new CTraductor().visit(tree); extension = "c"; descFiltro = "Archivos C (*.c)"; break;
                case "Go":     codigoTraducido = new GoTraductor().visit(tree); extension = "go"; descFiltro = "Archivos Go (*.go)"; break;
                case "C#":     codigoTraducido = new CSharpTraductor().visit(tree); extension = "cs"; descFiltro = "Archivos C# (*.cs)"; break;
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Exportar Código a " + lang);
            chooser.setFileFilter(new FileNameExtensionFilter(descFiltro, extension));
            chooser.setSelectedFile(new File("traduccion." + extension));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivoDestino = chooser.getSelectedFile();
                if (!archivoDestino.getAbsolutePath().toLowerCase().endsWith("." + extension)) {
                    archivoDestino = new File(archivoDestino.getAbsolutePath() + "." + extension);
                }
                Files.writeString(archivoDestino.toPath(), codigoTraducido, StandardCharsets.UTF_8);
                JOptionPane.showMessageDialog(this, "Exportación a " + lang + " Exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            outputArea.setText("Error al exportar:\n" + ex.getMessage());
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
                throw new RuntimeException("ERROR SINTÁCTICO: [Línea " + line + ":" + charPositionInLine + "] -> " + msg);
            }
        });

        return parser.programa();
    }
}