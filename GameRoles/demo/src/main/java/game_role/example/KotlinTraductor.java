package game_role.example;

public class KotlinTraductor extends GameRoleBaseVisitor<String> {

    StringBuilder codigo = new StringBuilder();
    int indent = 0;

    private String tab() {
        return "    ".repeat(indent);
    }

    @Override
    public String visitPrograma(GameRoleParser.ProgramaContext ctx) {
        codigo.append("fun main() {\n");
        indent++;
        visit(ctx.bloque());
        indent--;
        codigo.append("}\n");
        return codigo.toString();
    }

    @Override
    public String visitBloque(GameRoleParser.BloqueContext ctx) {
        for (var instr : ctx.instruccion()) {
            visit(instr);
        }
        return null;
    }

    @Override
    public String visitInstruccion(GameRoleParser.InstruccionContext ctx) {
        if (ctx.declaracion() != null) return visit(ctx.declaracion());
        if (ctx.asignacion() != null) return visit(ctx.asignacion());
        if (ctx.decision() != null) return visit(ctx.decision());
        if (ctx.repeticionWhile() != null) return visit(ctx.repeticionWhile());
        if (ctx.repeticionFor() != null) return visit(ctx.repeticionFor());
        if (ctx.imprimir() != null) return visit(ctx.imprimir());
        return null;
    }

    @Override
    public String visitDeclaracion(GameRoleParser.DeclaracionContext ctx) {
        String id = ctx.ID().getText();
        String val = visit(ctx.expresion());
        codigo.append(tab())
              .append("var ")
              .append(id)
              .append(" = ")
              .append(val)
              .append("\n");
        return null;
    }

    @Override
    public String visitAsignacion(GameRoleParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        String val = visit(ctx.expresion());
        codigo.append(tab())
              .append(id)
              .append(" = ")
              .append(val)
              .append("\n");
        return null;
    }

    @Override
    public String visitImprimir(GameRoleParser.ImprimirContext ctx) {
        String val = visit(ctx.expresion());
        codigo.append(tab())
              .append("println(")
              .append(val)
              .append(")\n");
        return null;
    }

    @Override
    public String visitDecision(GameRoleParser.DecisionContext ctx) {
        String cond = visit(ctx.condicion(0));
        codigo.append(tab())
              .append("if (")
              .append(cond)
              .append(") {\n");
        indent++;
        visit(ctx.bloque(0));
        indent--;
        codigo.append(tab()).append("}\n");
        return null;
    }

    @Override
    public String visitRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx) {
        String cond = visit(ctx.condicion());
        codigo.append(tab())
              .append("while (")
              .append(cond)
              .append(") {\n");
        indent++;
        visit(ctx.bloque());
        indent--;
        codigo.append(tab()).append("}\n");
        return null;
    }

    @Override
    public String visitRepeticionFor(GameRoleParser.RepeticionForContext ctx) {
        String init = visit(ctx.asignacion(0));
        String cond = visit(ctx.condicion());
        String update = visit(ctx.asignacion(1));

        // Kotlin no usa for clásico como nuestro lenguaje, así que lo simulamos
        codigo.append(tab()).append("{\n");
        indent++;
        visit(ctx.asignacion(0)); // inicialización
        codigo.append(tab())
              .append("while (")
              .append(cond)
              .append(") {\n");
        indent++;
        visit(ctx.bloque());
        visit(ctx.asignacion(1)); // update
        indent--;
        codigo.append(tab()).append("}\n");
        indent--;
        codigo.append(tab()).append("}\n");
        return null;
    }

    @Override
    public String visitCondicion(GameRoleParser.CondicionContext ctx) {
        String izq = visit(ctx.expresion(0));
        String der = visit(ctx.expresion(1));
        String op = switch (ctx.comparador().getText()) {
            case "Estrategia" -> ">";
            case "RTS" -> "<";
            case "TBS" -> "==";
            case "CuatroX" -> ">=";
            case "MOBA" -> "<=";
            default -> "==";
        };
        return izq + " " + op + " " + der;
    }

    @Override
    public String visitExpresion(GameRoleParser.ExpresionContext ctx) {
        if (ctx.NUMBER() != null) return ctx.NUMBER().getText();
        if (ctx.FLOAT() != null) return ctx.FLOAT().getText();
        if (ctx.STRING() != null) return ctx.STRING().getText();
        if (ctx.ID() != null) return ctx.ID().getText();
        if (ctx.expresion().size() == 2) {
            String izq = visit(ctx.expresion(0));
            String der = visit(ctx.expresion(1));
            String op = switch (ctx.operadorAritmetico().getText()) {
                case "Lucha" -> "+";
                case "Beat_em_up" -> "-";
                case "Hack_and_slash" -> "*";
                case "Soulslike" -> "/";
                case "Roguelike" -> "%";
                default -> "";
            };
            return izq + " " + op + " " + der;
        }
        return visit(ctx.expresion(0));
    }
}