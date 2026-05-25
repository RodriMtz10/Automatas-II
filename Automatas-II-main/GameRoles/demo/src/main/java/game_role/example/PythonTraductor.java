package game_role.example;

public class PythonTraductor extends GameRoleBaseVisitor<String> {
    private int indentLevel = 0;

    private String getIndent() {
        return "    ".repeat(indentLevel);
    }

    @Override
    public String visitPrograma(GameRoleParser.ProgramaContext ctx) {
        String nombrePrograma = ctx.ID().getText();
        StringBuilder sb = new StringBuilder();
        
        sb.append("# Programa traducido de GameRole: ").append(nombrePrograma).append("\n\n");
        
        sb.append("def __safe_add(a, b):\n");
        sb.append("    if isinstance(a, str) or isinstance(b, str):\n");
        sb.append("        return str(a) + str(b)\n");
        sb.append("    return a + b\n\n");
        
        sb.append("if __name__ == \"__main__\":\n");
        
        indentLevel++;
        sb.append(visit(ctx.bloque()));
        indentLevel--;
        
        return sb.toString();
    }

    @Override
    public String visitBloque(GameRoleParser.BloqueContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.instruccion().isEmpty()) {
            sb.append(getIndent()).append("pass\n");
            return sb.toString();
        }
        for (var instr : ctx.instruccion()) {
            String trad = visit(instr);
            if (trad != null && !trad.isEmpty()) {
                sb.append(trad);
            }
        }
        return sb.toString();
    }

    @Override
    public String visitInstruccion(GameRoleParser.InstruccionContext ctx) {
        if (ctx.declaracion() != null) return visit(ctx.declaracion());
        if (ctx.asignacion() != null) return visit(ctx.asignacion());
        if (ctx.decision() != null) return visit(ctx.decision());
        if (ctx.repeticionWhile() != null) return visit(ctx.repeticionWhile());
        if (ctx.repeticionFor() != null) return visit(ctx.repeticionFor());
        if (ctx.imprimir() != null) return visit(ctx.imprimir());
        return "";
    }

    @Override
    public String visitDeclaracion(GameRoleParser.DeclaracionContext ctx) {
        String id = ctx.ID().getText();
        String expr = visit(ctx.expresion());
        return getIndent() + id + " = " + expr + "\n";
    }

    @Override
    public String visitAsignacion(GameRoleParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        String expr = visit(ctx.expresion());
        return getIndent() + id + " = " + expr + "\n";
    }

    @Override
    public String visitDecision(GameRoleParser.DecisionContext ctx) {
        StringBuilder sb = new StringBuilder();
        
        String cond0 = visit(ctx.condicion(0));
        sb.append(getIndent()).append("if ").append(cond0).append(":\n");
        
        indentLevel++;
        sb.append(visit(ctx.bloque(0)));
        indentLevel--;
        
        for (int i = 1; i < ctx.condicion().size(); i++) {
            String condI = visit(ctx.condicion(i));
            sb.append(getIndent()).append("elif ").append(condI).append(":\n");
            indentLevel++;
            sb.append(visit(ctx.bloque(i)));
            indentLevel--;
        }
        
        return sb.toString();
    }

    @Override
    public String visitRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx) {
        StringBuilder sb = new StringBuilder();
        String cond = visit(ctx.condicion());
        
        sb.append(getIndent()).append("while ").append(cond).append(":\n");
        indentLevel++;
        sb.append(visit(ctx.bloque()));
        indentLevel--;
        
        return sb.toString();
    }

    @Override
    public String visitRepeticionFor(GameRoleParser.RepeticionForContext ctx) {
        StringBuilder sb = new StringBuilder();
       
        String asig0 = visit(ctx.asignacion(0)).trim();
        String cond = visit(ctx.condicion()).trim();
        String asig1 = visit(ctx.asignacion(1)).trim();
        
        sb.append(getIndent()).append(asig0).append("\n");
        sb.append(getIndent()).append("while ").append(cond).append(":\n");
        
        indentLevel++;
        sb.append(visit(ctx.bloque()));
        sb.append(getIndent()).append(asig1).append("\n");
        indentLevel--;
        
        return sb.toString();
    }

    @Override
    public String visitImprimir(GameRoleParser.ImprimirContext ctx) {
        String expr = (ctx.expresion() != null) ? visit(ctx.expresion()) : "\"\"";
        return getIndent() + "print(" + expr + ")\n";
    }

    @Override
    public String visitCondicion(GameRoleParser.CondicionContext ctx) {
        String izq = visit(ctx.expresion(0));
        String der = visit(ctx.expresion(1));
        String opOriginal = ctx.comparador().getText();
        
        String opTraducido = switch (opOriginal) {
            case "Estrategia" -> ">";
            case "RTS" -> "<";
            case "TBS" -> "==";
            case "CuatroX" -> ">=";
            case "MOBA" -> "<=";
            default -> "==";
        };
        return izq + " " + opTraducido + " " + der;
    }

    @Override
    public String visitExpresion(GameRoleParser.ExpresionContext ctx) {
        if (ctx.NUMBER() != null) return ctx.NUMBER().getText();
        if (ctx.FLOAT() != null) return ctx.FLOAT().getText();
        if (ctx.STRING() != null) return ctx.STRING().getText();
        if (ctx.ID() != null) return ctx.ID().getText();
        if (ctx.TRUE() != null) return "True"; 
        if (ctx.FALSE() != null) return "False";
        
        if (ctx.expresion().size() == 2) {
            String izq = visit(ctx.expresion(0));
            String der = visit(ctx.expresion(1));
            String opOriginal = ctx.operadorAritmetico().getText();
            
            if (opOriginal.equals("Lucha")) {
                return "__safe_add(" + izq + ", " + der + ")";
            }
            
            String opTraducido = switch (opOriginal) {
                case "Beat_em_up" -> "-";
                case "Hack_and_slash" -> "*";
                case "Soulslike" -> "/";
                case "Roguelike" -> "%";
                default -> "+";
            };
            return "(" + izq + " " + opTraducido + " " + der + ")";
        }
        
        if (ctx.expresion().size() == 1) {
            return "(" + visit(ctx.expresion(0)) + ")";
        }
        return "";
    }
}