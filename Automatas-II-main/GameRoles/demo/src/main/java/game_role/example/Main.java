package game_role.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    private static final String EXTENSION = "gr";
    private static final String DIRBASE = "GameRoles/demo/src/test/resources/";

    public static void main(String[] args) throws IOException {

        String files[] = args.length == 0
                ? new String[] { "prueba." + EXTENSION }
                : args;

        System.out.println("Dirbase: " + DIRBASE);

        for (String file : files) {

            System.out.println("START: " + file);
            
            CharStream input = CharStreams.fromFileName(DIRBASE + file);
            GameRoleLexer lexer = new GameRoleLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GameRoleParser parser = new GameRoleParser(tokens);
            ParseTree tree = parser.programa();

            System.out.println("\n===== EJECUCIÓN =====\n");
            LanguageCustomVisitor visitor = new LanguageCustomVisitor();
            visitor.visit(tree);

            // Kotlin Traductor
            KotlinTraductor traductor = new KotlinTraductor();
            String codigoKotlin = traductor.visit(tree);

            String nombreSalida = file.replace("." + EXTENSION, ".kt");

            try (FileWriter writer = new FileWriter(DIRBASE + nombreSalida)) {
                writer.write(codigoKotlin);
                System.out.println("Archivo Kotlin generado: " + nombreSalida);
            }

            JFrame frame = new JFrame("Árbol Sintáctico");

            TreeViewer viewer = new TreeViewer(
                    Arrays.asList(parser.getRuleNames()),
                    tree);

            viewer.setScale(1.0);

            JScrollPane scrollPane = new JScrollPane(viewer);

            frame.add(scrollPane);
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}