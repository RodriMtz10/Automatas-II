package game_role.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class vista extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JComboBox<String> languageBox;

    // COLORES TEMA VS CODE
    private static final Color BG_DARK = new Color(30, 30, 30);
    private static final Color PANEL_DARK = new Color(45, 45, 45);
    private static final Color TEXT_DARK = new Color(220, 220, 220);
    private static final Color ACCENT = new Color(0, 122, 204);

    public vista() {

        setTitle("GameRole IDE");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // ======================
        // TOOLBAR (tipo VSCode)
        // ======================
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(PANEL_DARK);

        JButton importBtn = createButton("Importar");
        JButton saveBtn = createButton("Guardar");
        JButton runBtn = createButton("Ejecutar");
        JButton translateBtn = createButton("Traducir");

        languageBox = new JComboBox<>(new String[]{"Kotlin"});
        styleCombo(languageBox);

        topBar.add(importBtn);
        topBar.add(saveBtn);
        topBar.add(runBtn);
        topBar.add(translateBtn);
        topBar.add(new JLabel("  Lenguaje: "));
        topBar.add(languageBox);

        // ======================
        // EDITOR (INPUT)
        // ======================
        inputArea = new JTextArea();
        styleEditor(inputArea);

        JScrollPane inputScroll = new JScrollPane(inputArea);

        // ======================
        // CONSOLA (OUTPUT)
        // ======================
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

        // ======================
        // ADD COMPONENTS
        // ======================
        add(topBar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        getContentPane().setBackground(BG_DARK);

        // ======================
        // ACTIONS
        // ======================

        importBtn.addActionListener(e -> importFile());
        saveBtn.addActionListener(e -> saveFile());
        runBtn.addActionListener(e -> runCode());
        translateBtn.addActionListener(e -> translateCode());
    }

    // ======================
    // IMPORTAR
    // ======================
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

    // ======================
    // GUARDAR
    // ======================
    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Files.writeString(
                        chooser.getSelectedFile().toPath(),
                        inputArea.getText());
            } catch (Exception ex) {
                outputArea.setText("Error guardando: " + ex.getMessage());
            }
        }
    }

    // ======================
    // EJECUTAR
    // ======================
    private void runCode() {
        try {
            ParseTree tree = buildTree();

            LanguageCustomVisitor visitor = new LanguageCustomVisitor();
            Simbolo result = visitor.visit(tree);

            outputArea.setText(result.toString());

        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    // ======================
    // TRADUCIR
    // ======================
    private void translateCode() {
        try {
            ParseTree tree = buildTree();

            String lang = languageBox.getSelectedItem().toString();

            if (lang.equals("Kotlin")) {
                KotlinTraductor kt = new KotlinTraductor();
                outputArea.setText(kt.visit(tree));
            }

        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    // ======================
    // ANTLR BUILD TREE
    // ======================
    private ParseTree buildTree() {
        CharStream input = CharStreams.fromString(inputArea.getText());
        GameRoleLexer lexer = new GameRoleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GameRoleParser parser = new GameRoleParser(tokens);
        return parser.programa();
    }

    // ======================
    // ESTILO EDITOR VS CODE
    // ======================
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(ACCENT);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(PANEL_DARK);
            }
        });

        return btn;
    }

    private void styleCombo(JComboBox<?> combo) {
        combo.setBackground(PANEL_DARK);
        combo.setForeground(TEXT_DARK);
    }

    // ======================
    // MAIN
    // ======================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new vista().setVisible(true);
        });
    }
}