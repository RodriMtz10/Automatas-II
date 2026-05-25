package game_role.example;

import java.util.HashMap;
import java.util.Map;

public class CTraductor extends GameRoleBaseVisitor<String> {
    private int indentLevel = 0;
    
    private Map<String, String> tablaTipos = new HashMap<>();

    private String getIndent() {
        return "    ".repeat(indentLevel);
    }

    @Override
    public String visitPrograma(GameRoleParser.ProgramaContext ctx) {
        tablaTipos.clear();
        String nombrePrograma = ctx.ID().getText();
        StringBuilder sb = new StringBuilder();
        
        sb.append("// Programa traducido de GameRole: ").append(nombrePrograma).append("\n");
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n");
        sb.append("#include <string.h>\n");
        sb.append("#include <stdbool.h>\n\n");
        
        sb.append("// Funciones para convertir tipos a string dinamicos\n");
        sb.append("char* int_to_str(int v) { char* b = malloc(32); sprintf(b, \"%d\", v); return b; }\n");
        sb.append("char* double_to_str(double v) { char* b = malloc(64); sprintf(b, \"%f\", v); return b; }\n");
        sb.append("char* str_to_str(char* v) { return v; }\n");
        sb.append("char* const_str_to_str(const char* v) { return (char*)v; }\n\n");
        
        sb.append("#define DECAY(x) (1 ? (x) : (x))\n\n");
        
        sb.append("#define to_str(X) _Generic(DECAY(X), \\\n");
        sb.append("    int: int_to_str, \\\n");
        sb.append("    double: double_to_str, \\\n");
        sb.append("    char*: str_to_str, \\\n");
        sb.append("    const char*: const_str_to_str \\\n");
        sb.append(")(X)\n\n");
        
        sb.append("char* __safe_add(char* s1, char* s2) {\n");
        sb.append("    char* res = malloc(strlen(s1) + strlen(s2) + 1);\n");
        sb.append("    strcpy(res, s1); strcat(res, s2);\n");
        sb.append("    return res;\n");
        sb.append("}\n");
        sb.append("#define safe_add(A, B) __safe_add(to_str(A), to_str(B))\n\n");
        
        sb.append("#define print_gr(X) _Generic(DECAY(X), \\\n");
        sb.append("    int: printf(\"%d\\n\", X), \\\n");
        sb.append("    double: printf(\"%f\\n\", X), \\\n");
        sb.append("    char*: printf(\"%s\\n\", X), \\\n");
        sb.append("    const char*: printf(\"%s\\n\", X) \\\n");
        sb.append(")\n\n");
        
        sb.append("int main() {\n");
        
        indentLevel++;
        sb.append(visit(ctx.bloque()));
        sb.append(getIndent()).append("return 0;\n");
        indentLevel--;
        
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public String visitBloque(GameRoleParser.BloqueContext ctx) {
        StringBuilder sb = new StringBuilder();
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
        String tipoOriginal = ctx.tipo().getText();
        
        tablaTipos.put(id, tipoOriginal);
        
        String expr = visit(ctx.expresion());
        
        String tipoC = switch (tipoOriginal) {
            case "Simulacion" -> "int";
            case "Simulacion_vida" -> "char*";
            case "Simulacion_construccion" -> "bool";
            case "Simulacion_vuelo" -> "double";
            default -> "void*";
        };
        
        return getIndent() + tipoC + " " + id + " = " + expr + ";\n";
    }

    @Override
    public String visitAsignacion(GameRoleParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        String expr = visit(ctx.expresion());
        return getIndent() + id + " = " + expr + ";\n";
    }

    @Override
    public String visitDecision(GameRoleParser.DecisionContext ctx) {
        StringBuilder sb = new StringBuilder();
        
        String cond0 = visit(ctx.condicion(0));
        sb.append(getIndent()).append("if (").append(cond0).append(") {\n");
        
        indentLevel++;
        sb.append(visit(ctx.bloque(0)));
        indentLevel--;
        sb.append(getIndent()).append("}");
        
        for (int i = 1; i < ctx.condicion().size(); i++) {
            String condI = visit(ctx.condicion(i));
            sb.append(" else if (").append(condI).append(") {\n");
            indentLevel++;
            sb.append(visit(ctx.bloque(i)));
            indentLevel--;
            sb.append(getIndent()).append("}");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String visitRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx) {
        StringBuilder sb = new StringBuilder();
        String cond = visit(ctx.condicion());
        
        sb.append(getIndent()).append("while (").append(cond).append(") {\n");
        indentLevel++;
        sb.append(visit(ctx.bloque()));
        indentLevel--;
        sb.append(getIndent()).append("}\n");
        
        return sb.toString();
    }

    @Override
    public String visitRepeticionFor(GameRoleParser.RepeticionForContext ctx) {
        StringBuilder sb = new StringBuilder();
       
        String asig0 = visit(ctx.asignacion(0)).trim();
        String cond = visit(ctx.condicion()).trim();
        String asig1 = visit(ctx.asignacion(1)).trim();
        
        sb.append(getIndent()).append(asig0).append("\n");
        sb.append(getIndent()).append("while (").append(cond).append(") {\n");
        
        indentLevel++;
        sb.append(visit(ctx.bloque()));
        sb.append(getIndent()).append(asig1).append("\n");
        indentLevel--;
        
        sb.append(getIndent()).append("}\n");
        return sb.toString();
    }

    @Override
    public String visitImprimir(GameRoleParser.ImprimirContext ctx) {
        String expr = (ctx.expresion() != null) ? visit(ctx.expresion()) : "\"\"";
        return getIndent() + "print_gr(" + expr + ");\n";
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
        if (ctx.TRUE() != null) return "true";
        if (ctx.FALSE() != null) return "false";
        
        if (ctx.expresion().size() == 2) {
            String izq = visit(ctx.expresion(0));
            String der = visit(ctx.expresion(1));
            String opOriginal = ctx.operadorAritmetico().getText();
            
            if (opOriginal.equals("Lucha")) {
                if (isString(ctx.expresion(0)) || isString(ctx.expresion(1))) {
                    return "safe_add(" + izq + ", " + der + ")";
                }
                return "(" + izq + " + " + der + ")";
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

    private boolean isString(GameRoleParser.ExpresionContext ctx) {
        if (ctx == null) return false;
        if (ctx.STRING() != null) return true;
        
        if (ctx.ID() != null) {
            String tipo = tablaTipos.get(ctx.ID().getText());
            return "Simulacion_vida".equals(tipo);
        }
        
        if (ctx.expresion().size() == 2 && ctx.operadorAritmetico() != null) {
            if (ctx.operadorAritmetico().getText().equals("Lucha")) {
                return isString(ctx.expresion(0)) || isString(ctx.expresion(1));
            }
        }
        
        if (ctx.expresion().size() == 1) {
            return isString(ctx.expresion(0));
        }
        return false;
    }
}