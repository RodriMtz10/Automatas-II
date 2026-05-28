package game_role.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

public class vista extends JFrame {

    private enum Lenguaje {
        KOTLIN("Kotlin", "kt", "Archivos Kotlin (*.kt)", tree -> new KotlinTraductor().visit(tree)),
        PYTHON("Python", "py", "Archivos Python (*.py)", tree -> new PythonTraductor().visit(tree)),
        C("C", "c", "Archivos C (*.c)", tree -> new CTraductor().visit(tree)),
        GO("Go", "go", "Archivos Go (*.go)", tree -> new GoTraductor().visit(tree)),
        CSHARP("C#", "cs", "Archivos C# (*.cs)", tree -> new CSharpTraductor().visit(tree));

        final String nombre, extension, descripcion;
        final java.util.function.Function<ParseTree, String> traductor;

        Lenguaje(String nombre, String extension, String descripcion, java.util.function.Function<ParseTree, String> traductor) {
            this.nombre = nombre; this.extension = extension; this.descripcion = descripcion; this.traductor = traductor;
        }
        @Override public String toString() { return nombre; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new vista().setVisible(true));
    }

    private JTextPane inputArea; 
    private JTextArea linesArea, outputArea, helpText;
    private JScrollPane inputScroll, outputScroll;
    private JComboBox<Lenguaje> languageBox; 
    private JPanel topBar, helpPanel;
    private JButton themeBtn, helpBtn;
    private JLabel lblLang;
    private List<JButton> standardButtons = new ArrayList<>();

    private JPopupMenu autoCompMenu;
    private JList<String> autoCompList;
    private javax.swing.Timer linterTimer;
    private final String[] palabrasClave = {
        "Juego", "Mundo_abierto", "Sandbox", "Simulacion", "Simulacion_vida", 
        "Simulacion_construccion", "Simulacion_vuelo", "Accion", "Accion_aventura", 
        "Aventura", "Supervivencia", "FPS", "TPS", "Estrategia", "RTS", "TBS", 
        "CuatroX", "MOBA", "Lucha", "Beat_em_up", "Hack_and_slash", "Soulslike", "Roguelike"
    };

    private static final Color XBOX_GREEN = new Color(16, 124, 16); 
    private static final Color XBOX_GREEN_HOVER = new Color(57, 255, 20); 
    private static final Color LINE_NUM_COLOR = new Color(130, 130, 130);
    private static final Color LINTER_ERROR_BG = new Color(70, 30, 30); 

    private Color bgCurrent = new Color(18, 18, 18), panelCurrent = new Color(35, 35, 35), textCurrent = new Color(240, 240, 240);
    private boolean isLightTheme = false;
    private volatile boolean isHighlighting = false;

    public vista() {
        setTitle("GameRole IDE con IntelliSense & Linter");
        setSize(1250, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); leftPanel.setOpaque(false);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); rightPanel.setOpaque(false);

        JButton importBtn = createStandardButton("Importar"), saveBtn = createStandardButton("Guardar");
        JButton translateBtn = createStandardButton("Traducir"), exportTradBtn = createStandardButton("Exportar");

        languageBox = new JComboBox<>(Lenguaje.values()); 
        languageBox.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLang = new JLabel("Destino:"); lblLang.setFont(new Font("Segoe UI", Font.BOLD, 12));

        leftPanel.add(importBtn); leftPanel.add(saveBtn); leftPanel.add(new JLabel("  |  "));
        leftPanel.add(translateBtn); leftPanel.add(exportTradBtn); leftPanel.add(lblLang); leftPanel.add(languageBox);

        helpBtn = createStandardButton("Guía"); themeBtn = createStandardButton("Tema: Blanco");
        JButton runBtn = new JButton("RUN"); styleRunButton(runBtn);
        rightPanel.add(helpBtn); rightPanel.add(themeBtn); rightPanel.add(runBtn);

        topBar.add(leftPanel, BorderLayout.WEST); topBar.add(rightPanel, BorderLayout.EAST);

        inputArea = new JTextPane() {
            @Override public boolean getScrollableTracksViewportWidth() {
                Component parent = getParent();
                return !(parent instanceof JViewport) || getUI().getPreferredSize(this).width <= parent.getSize().width;
            }
        };
        inputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        inputArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        linesArea = new JTextArea(" 1 \n"); linesArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        linesArea.setForeground(LINE_NUM_COLOR); linesArea.setEditable(false); linesArea.setFocusable(false);

        inputScroll = new JScrollPane(inputArea);
        inputScroll.setRowHeaderView(linesArea);
        inputScroll.setBorder(null);

        inputScroll.getVerticalScrollBar().addAdjustmentListener(e -> {
            int scrollValue = inputScroll.getVerticalScrollBar().getValue();
            inputScroll.getRowHeader().setViewPosition(new Point(0, scrollValue));
        });

        inputArea.getDocument().addDocumentListener(new DocumentListener() {
            private void updateEditor() {
                if (isHighlighting) return;
                SwingUtilities.invokeLater(() -> {
                    int lines = inputArea.getStyledDocument().getDefaultRootElement().getElementCount();
                    StringBuilder lineNumbers = new StringBuilder();
                    for (int i = 1; i <= lines; i++) lineNumbers.append(" ").append(i).append(" \n");
                    linesArea.setText(lineNumbers.toString());
                    applySyntaxHighlighting();
                });
                
                if (linterTimer != null) {
                    linterTimer.restart();
                }
            }
            @Override public void changedUpdate(DocumentEvent e) {}
            @Override public void insertUpdate(DocumentEvent e) { updateEditor(); }
            @Override public void removeUpdate(DocumentEvent e) { updateEditor(); }
        });

        outputArea = new JTextArea(); 
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, XBOX_GREEN));

        outputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScroll, outputScroll);
        splitPane.setDividerLocation(450); splitPane.setDividerSize(5); splitPane.setBorder(null);

        helpPanel = new JPanel(new BorderLayout()); helpPanel.setPreferredSize(new Dimension(300, 0));
        helpPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, XBOX_GREEN));
        JLabel helpTitle = new JLabel("DICCIONARIO GAMEROLE", SwingConstants.CENTER);
        helpTitle.setFont(new Font("Segoe UI", Font.BOLD, 14)); helpTitle.setForeground(XBOX_GREEN);
        helpTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        helpPanel.add(helpTitle, BorderLayout.NORTH);

        helpText = new JTextArea(getGrammarHelpText()); helpText.setEditable(false); helpText.setFocusable(false);
        helpText.setFont(new Font("Consolas", Font.PLAIN, 13)); helpText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        helpPanel.add(new JScrollPane(helpText), BorderLayout.CENTER); helpPanel.setVisible(false);

        add(topBar, BorderLayout.NORTH); add(splitPane, BorderLayout.CENTER); add(helpPanel, BorderLayout.EAST);
        
        initIntelliSense();
        initLinter();

        applyTheme(); setHelloWorldCode();

        importBtn.addActionListener(e -> gestionarArchivo(true, null));
        saveBtn.addActionListener(e -> gestionarArchivo(false, "Archivo guardado con éxito."));
        runBtn.addActionListener(e -> runCode());
        translateBtn.addActionListener(e -> translateCode());
        exportTradBtn.addActionListener(e -> exportTranslation());
        themeBtn.addActionListener(e -> toggleTheme());
        helpBtn.addActionListener(e -> helpPanel.setVisible(!helpPanel.isVisible()));
    }

    private void initIntelliSense() {
        autoCompMenu = new JPopupMenu();
        autoCompList = new JList<>();
        autoCompList.setFont(new Font("Consolas", Font.PLAIN, 14));
        autoCompList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        autoCompList.setBackground(panelCurrent);
        autoCompList.setForeground(textCurrent);
        
        autoCompMenu.add(new JScrollPane(autoCompList));
        autoCompMenu.setFocusable(false);

        autoCompList.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { insertarPalabraSeleccionada(); }
            }
        });

        inputArea.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    mostrarMenuAutoCompletado("");
                    e.consume();
                }
                if (autoCompMenu.isVisible()) {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        int index = autoCompList.getSelectedIndex() + 1;
                        if (index < autoCompList.getModel().getSize()) autoCompList.setSelectedIndex(index);
                        e.consume();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        int index = autoCompList.getSelectedIndex() - 1;
                        if (index >= 0) autoCompList.setSelectedIndex(index);
                        e.consume();
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        insertarPalabraSeleccionada();
                        e.consume();
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        autoCompMenu.setVisible(false);
                        e.consume();
                    }
                }
            }

            @Override public void keyReleased(KeyEvent e) {
                if (autoCompMenu.isVisible() && !e.isControlDown() && e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_ENTER) {
                    String prefijo = obtenerPrefijoActual();
                    if (prefijo.isEmpty()) {
                        autoCompMenu.setVisible(false);
                    } else {
                        filtrarOpciones(prefijo);
                    }
                }
            }
        });
    }

    private String obtenerPrefijoActual() {
        try {
            int pos = inputArea.getCaretPosition();
            String texto = inputArea.getText(0, pos);
            int i = pos - 1;
            while (i >= 0 && Character.isUnicodeIdentifierPart(texto.charAt(i))) { i--; }
            return texto.substring(i + 1, pos);
        } catch (Exception ex) { return ""; }
    }

    private void mostrarMenuAutoCompletado(String prefijo) {
        filtrarOpciones(prefijo);
        try {
            Rectangle rect = inputArea.modelToView2D(inputArea.getCaretPosition()).getBounds();
            autoCompMenu.show(inputArea, rect.x, rect.y + rect.height);
            inputArea.requestFocusInWindow();
        } catch (Exception ignored) {}
    }

    private void filtrarOpciones(String prefijo) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String palabra : palabrasClave) {
            if (palabra.toLowerCase().startsWith(prefijo.toLowerCase())) {
                model.addElement(palabra);
            }
        }
        autoCompList.setModel(model);
        if (model.getSize() > 0) {
            autoCompList.setSelectedIndex(0);
            autoCompMenu.pack();
        } else {
            autoCompMenu.setVisible(false);
        }
    }

    private void insertarPalabraSeleccionada() {
        String seleccion = autoCompList.getSelectedValue();
        if (seleccion != null) {
            try {
                int pos = inputArea.getCaretPosition();
                String prefijo = obtenerPrefijoActual();
                int start = pos - prefijo.length();
                Document doc = inputArea.getDocument();
                doc.remove(start, prefijo.length());
                doc.insertString(start, seleccion + " ", null);
            } catch (Exception ignored) {}
        }
        autoCompMenu.setVisible(false);
    }

    private void initLinter() {
        linterTimer = new javax.swing.Timer(1500, e -> ejecutarLinterSilencioso());
        linterTimer.setRepeats(false);
    }

    private void ejecutarLinterSilencioso() {
        if (inputArea.getText().trim().isEmpty()) {
            limpiarEstadoLinter();
            return;
        }
        try {
            buildTree();
            limpiarEstadoLinter();
        } catch (RuntimeException ex) {
            linesArea.setBackground(LINTER_ERROR_BG);
            String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
            outputArea.setText("⚠️ [Linter en Tiempo Real]\n" + msg);
        }
    }

    private void limpiarEstadoLinter() {
        linesArea.setBackground(panelCurrent);
        if (outputArea.getText().startsWith("⚠️ [Linter")) {
            outputArea.setText("");
        }
    }

    private void gestionarArchivo(boolean esImportar, String msgExito) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos GameRole (*.gr)", "gr"));
        int res = esImportar ? chooser.showOpenDialog(this) : chooser.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                if (esImportar) {
                    inputArea.setText(Files.readString(chooser.getSelectedFile().toPath(), StandardCharsets.UTF_8));
                } else {
                    Files.writeString(chooser.getSelectedFile().toPath(), inputArea.getText(), StandardCharsets.UTF_8);
                    JOptionPane.showMessageDialog(this, msgExito, "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                outputArea.setText("Error en operación de archivo: " + ex.getMessage());
            }
        }
    }

    private void applySyntaxHighlighting() {
        isHighlighting = true;
        try {
            StyledDocument doc = inputArea.getStyledDocument();
            String text = doc.getText(0, doc.getLength());
            doc.setCharacterAttributes(0, text.length(), inputArea.getStyle("Default"), false);

            colorearRegex(doc, text, "\\b(Juego|Mundo_abierto|Sandbox|Accion_aventura|Accion|Aventura|Supervivencia)\\b", "Keyword");
            colorearRegex(doc, text, "\\b(Simulacion_vida|Simulacion_construccion|Simulacion_vuelo|Simulacion)\\b", "Type");
            colorearRegex(doc, text, "\\b(Estrategia|RTS|TBS|CuatroX|MOBA|Lucha|Beat_em_up|Hack_and_slash|Soulslike|Roguelike)\\b", "Operator");
            colorearRegex(doc, text, "\\b(FPS|TPS)\\b", "Function");
            colorearRegex(doc, text, "\\b\\d+(\\.\\d+)?\\b", "Number");
            colorearRegex(doc, text, "\"[^\"]*\"", "String");
            colorearRegex(doc, text, "//.*", "Comment");
        } catch (Exception ignored) {
        } finally {
            isHighlighting = false;
        }
    }

    private void colorearRegex(StyledDocument doc, String textoCompleto, String regex, String nombreEstilo) {
        Matcher matcher = Pattern.compile(regex).matcher(textoCompleto);
        while (matcher.find()) {
            doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), inputArea.getStyle(nombreEstilo), false);
        }
    }

    private void translateCode() {
        outputArea.setText(""); 
        if (inputArea.getText().trim().isEmpty()) { outputArea.setText("El editor está vacío."); return; }
        try {
            Lenguaje lang = (Lenguaje) languageBox.getSelectedItem();
            if (lang != null) outputArea.setText(lang.traductor.apply(buildTree()));
        } catch (Exception ex) {
            outputArea.setText("Error en traducción:\n" + ex.getMessage());
        }
    }

    private void exportTranslation() {
        outputArea.setText(""); 
        if (inputArea.getText().trim().isEmpty()) { outputArea.setText("El editor está vacío."); return; }
        try {
            ParseTree tree = buildTree();
            Lenguaje lang = (Lenguaje) languageBox.getSelectedItem();
            if (lang == null) return;
            String codigoTraducido = lang.traductor.apply(tree);

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Exportar Código a " + lang.nombre);
            chooser.setFileFilter(new FileNameExtensionFilter(lang.descripcion, lang.extension));
            chooser.setSelectedFile(new File("traduccion." + lang.extension));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivoDestino = chooser.getSelectedFile();
                if (!archivoDestino.getAbsolutePath().toLowerCase().endsWith("." + lang.extension)) {
                    archivoDestino = new File(archivoDestino.getAbsolutePath() + "." + lang.extension);
                }
                Files.writeString(archivoDestino.toPath(), codigoTraducido, StandardCharsets.UTF_8);
                JOptionPane.showMessageDialog(this, "Exportación exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            outputArea.setText("Error al exportar:\n" + ex.getMessage());
        }
    }

    private void runCode() {
        outputArea.setText(""); 
        if (inputArea.getText().trim().isEmpty()) { outputArea.setText("El editor está vacío."); return; }

        PrintStream originalOut = System.out, originalErr = System.err;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream interceptorStream = new PrintStream(baos);
        System.setOut(interceptorStream); System.setErr(interceptorStream);

        StringBuilder reporte = new StringBuilder("========== RESULTADO DE EJECUCIÓN ==========\n\n");
        try {
            new LanguageCustomVisitor().visit(buildTree());
            System.out.flush();
            String salida = baos.toString();
            reporte.append("--- Mensajes de salida del programa ---\n").append(salida.isEmpty() ? "(No hubo salidas)\n" : salida)
                   .append("\n--- Reporte de Errores ---\n• Errores Léxicos: 0\n• Errores Sintácticos: 0\n• Errores Semánticos: 0\n\nEjecución finalizada con éxito.");
            limpiarEstadoLinter();
        } catch (Exception ex) {
            System.out.flush();
            String salida = baos.toString();
            reporte.append("--- Mensajes de salida (Hasta el error) ---\n").append(salida.isEmpty() ? "(Ninguna salida)\n" : salida)
                   .append("\n--- Reporte de Errores ---\n");
            
            String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
            if (msg.contains("ERROR LÉXICO")) {
                reporte.append("• Errores Léxicos: 1\n  └ Detalle: ").append(msg.replace("ERROR LÉXICO: ", "")).append("\n• Errores Sintácticos: 0\n• Errores Semánticos: 0\n");
            } else if (msg.contains("ERROR SINTÁCTICO")) {
                reporte.append("• Errores Sintácticos: 1\n  └ Detalle: ").append(msg.replace("ERROR SINTÁCTICO: ", "")).append("\n• Errores Semánticos: 0\n");
            } else if (msg.contains("[RS")) {
                reporte.append("• Errores Léxicos: 0\n• Errores Sintácticos: 0\n• Errores Semánticos: 1\n  └ Detalle: ").append(msg).append("\n");
            } else {
                reporte.append("• Error General Java:\n  └ ").append(msg).append("\n");
            }
            reporte.append("\n[X] Ejecución detenida.");
        } finally {
            System.setOut(originalOut); System.setErr(originalErr);
            outputArea.setText(reporte.toString());
        }
    }

    private ParseTree buildTree() {
        CharStream input = CharStreams.fromString(inputArea.getText());
        GameRoleLexer lexer = new GameRoleLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?,?> r, Object o, int l, int p, String m, RecognitionException e) {
                throw new RuntimeException("ERROR LÉXICO: [Línea " + l + ":" + p + "] -> " + m);
            }
        });

        GameRoleParser parser = new GameRoleParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?,?> r, Object o, int l, int p, String m, RecognitionException e) {
                throw new RuntimeException("ERROR SINTÁCTICO: [Línea " + l + ":" + p + "] -> " + m);
            }
        });
        return parser.programa();
    }

    private void toggleTheme() {
        isLightTheme = !isLightTheme;
        bgCurrent = isLightTheme ? Color.WHITE : new Color(18, 18, 18);
        panelCurrent = isLightTheme ? new Color(240, 240, 240) : new Color(35, 35, 35);
        textCurrent = isLightTheme ? Color.BLACK : new Color(240, 240, 240);
        themeBtn.setText(isLightTheme ? "Tema: Negro" : "Tema: Blanco");
        applyTheme();
    }

    private void applyTheme() {
        getContentPane().setBackground(bgCurrent); topBar.setBackground(panelCurrent);
        inputArea.setBackground(bgCurrent); outputArea.setBackground(bgCurrent);
        linesArea.setBackground(panelCurrent); languageBox.setBackground(bgCurrent);
        languageBox.setForeground(textCurrent); lblLang.setForeground(textCurrent);
        helpPanel.setBackground(panelCurrent); helpText.setBackground(panelCurrent); helpText.setForeground(textCurrent);
        inputScroll.getViewport().setBackground(bgCurrent); outputScroll.getViewport().setBackground(bgCurrent);
        inputArea.setCaretColor(XBOX_GREEN_HOVER); outputArea.setForeground(textCurrent);

        if (autoCompList != null) {
            autoCompList.setBackground(panelCurrent);
            autoCompList.setForeground(textCurrent);
        }

        for (JButton btn : standardButtons) { btn.setBackground(panelCurrent); btn.setForeground(textCurrent); }
        updateStyles(); applySyntaxHighlighting();
    }

    private JButton createStandardButton(String text) {
        JButton btn = new JButton(text); btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12)); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent evt) { btn.setBackground(XBOX_GREEN_HOVER); btn.setForeground(Color.BLACK); }
            @Override public void mouseExited(MouseEvent evt) { btn.setBackground(panelCurrent); btn.setForeground(textCurrent); }
        });
        standardButtons.add(btn); return btn;
    }

    private void styleRunButton(JButton btn) {
        btn.setBackground(XBOX_GREEN); btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15)); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent evt) { btn.setBackground(XBOX_GREEN_HOVER); }
            @Override public void mouseExited(MouseEvent evt) { btn.setBackground(XBOX_GREEN); }
        });
    }

    private void setHelloWorldCode() {
        inputArea.setText("// ¡Bienvenido a GameRole IDE!\n// Ejecuta (RUN) este código para probar.\n\nJuego HolaMundo Mundo_abierto\n\n    Simulacion nivel = 1\n    Simulacion_vida jugador = \"Heroe\"\n\n    FPS(\"¡Hola, Mundo desde GameRole!\")\n    FPS(\"Bienvenido \" Lucha jugador Lucha \" al Nivel \" Lucha nivel)\n\nSandbox\n");
    }

    private String getGrammarHelpText() {
        return "--- ESTRUCTURAS BÁSICAS ---\nJuego [ID] Mundo_abierto \n  -> Inicia el programa (main)\nSandbox \n  -> Cierre de bloque ( '}' )\n\n--- TIPOS DE DATOS ---\nSimulacion              -> int\nSimulacion_vida         -> string\nSimulacion_construccion -> bool\nSimulacion_vuelo        -> float\n\n--- IMPRESIÓN ---\nFPS(\"...\") / TPS(\"...\") -> print()\n\n--- CONTROL DE FLUJO ---\nAccion(...)             -> if\nAccion_aventura(...)    -> else if\nAventura(...)           -> for\nSupervivencia(...)      -> while\n\n--- OPERADORES ---\nLucha          -> Suma (+)\nBeat_em_up     -> Resta (-)\nHack_and_slash -> Mult (*)\nSoulslike      -> Div (/)\nRoguelike      -> Modulo (%)\n\n--- COMPARADORES ---\nEstrategia     -> Mayor que (>)\nRTS            -> Menor que (<)\nTBS            -> Igual a (==)\nCuatroX        -> Mayor o igual (>=)\nMOBA           -> Menor o igual (<=)\n";
    }

    private void updateStyles() {
        String[] nombres = {"Default", "Keyword", "Type", "Operator", "Function", "String", "Number", "Comment"};
        Color[] oscuros = {textCurrent, new Color(86, 156, 214), new Color(78, 201, 176), new Color(197, 134, 192), new Color(220, 220, 170), new Color(206, 145, 120), new Color(181, 206, 168), new Color(106, 153, 85)};
        Color[] claros = {textCurrent, Color.BLUE, new Color(38, 127, 153), new Color(175, 0, 219), new Color(121, 94, 38), new Color(163, 21, 21), new Color(9, 134, 88), new Color(106, 153, 85)};
        
        for (int i = 0; i < nombres.length; i++) {
            Style s = inputArea.getStyle(nombres[i]);
            if (s == null) s = inputArea.addStyle(nombres[i], null);
            StyleConstants.setForeground(s, isLightTheme ? claros[i] : oscuros[i]);
            StyleConstants.setFontFamily(s, "Consolas");
            StyleConstants.setFontSize(s, 15);
            StyleConstants.setBold(s, nombres[i].equals("Keyword"));
        }
    }
}