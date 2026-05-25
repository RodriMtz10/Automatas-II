package game_role.example;

import java.util.HashMap;
import java.util.Map;

public class LanguageCustomVisitor extends GameRoleBaseVisitor<Simbolo> {
    Map<String, Simbolo> tabla = new HashMap<>();

    private RuntimeException error(String codigo, String descripcion, int linea) {
        return new RuntimeException("[" + codigo + "] Línea: " + linea + " → " + descripcion);
    }

    @Override
    public Simbolo visitPrograma(GameRoleParser.ProgramaContext ctx) {
        visit(ctx.bloque());
        return null;
    }

    @Override
    public Simbolo visitBloque(GameRoleParser.BloqueContext ctx) {
        for (var instr : ctx.instruccion()) {
            visit(instr);
        }
        return null;
    }

    @Override
    public Simbolo visitInstruccion(GameRoleParser.InstruccionContext ctx) {
        if (ctx.declaracion() != null) return visit(ctx.declaracion());
        if (ctx.asignacion() != null) return visit(ctx.asignacion());
        if (ctx.decision() != null) return visit(ctx.decision());
        if (ctx.repeticionWhile() != null) return visit(ctx.repeticionWhile());
        if (ctx.repeticionFor() != null) return visit(ctx.repeticionFor());
        if (ctx.imprimir() != null) return visit(ctx.imprimir());
        return null;
    }

    @Override
    public Simbolo visitDeclaracion(GameRoleParser.DeclaracionContext ctx) {
        String id = ctx.ID().getText();
        String tipo = ctx.tipo().getText();
        if (tabla.containsKey(id)) {
            throw error("RS02", "Variable ya declarada: " + id, ctx.getStart().getLine());
        }
        Simbolo val = visit(ctx.expresion());
        validarTipo(tipo, val, ctx.getStart().getLine());
        tabla.put(id, new Simbolo(tipo, val.valor));
        return null;
    }

    @Override
    public Simbolo visitAsignacion(GameRoleParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        if (!tabla.containsKey(id)) {
            throw error("RS01", "Variable no declarada: " + id, ctx.getStart().getLine());
        }
        Simbolo val = visit(ctx.expresion());
        validarTipo(tabla.get(id).tipo, val, ctx.getStart().getLine());
        tabla.get(id).valor = val.valor;
        return null;
    }

    @Override
    public Simbolo visitDecision(GameRoleParser.DecisionContext ctx) {
        if (ctx.bloque().isEmpty()) {
            throw error("RS06", "Bloque condicional incompleto", ctx.getStart().getLine());
        }
        for (int i = 0; i < ctx.condicion().size(); i++) {
            boolean cond = (boolean) visit(ctx.condicion(i)).valor;
            if (cond) {
                visit(ctx.bloque(i));
                return null;
            }
        }
        return null;
    }

    @Override
    public Simbolo visitRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx) {
        while ((boolean) visit(ctx.condicion()).valor) {
            visit(ctx.bloque());
        }
        return null;
    }

    @Override
    public Simbolo visitRepeticionFor(GameRoleParser.RepeticionForContext ctx) {
        if (ctx.asignacion().size() < 2 || ctx.condicion() == null) {
            throw error("RS07", "Estructura FOR incompleta", ctx.getStart().getLine());
        }
        visit(ctx.asignacion(0));
        while ((boolean) visit(ctx.condicion()).valor) {
            visit(ctx.bloque());
            visit(ctx.asignacion(1));
        }
        return null;
    }

    @Override
    public Simbolo visitImprimir(GameRoleParser.ImprimirContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.expresion() == null) {
            throw error("RS09", "Impresión con elemento inválido.", linea);
        }
        Simbolo val = visit(ctx.expresion());
        System.out.println("[Línea " + linea + "] " + formatear(val.valor));
        return null;
    }

    @Override
    public Simbolo visitCondicion(GameRoleParser.CondicionContext ctx) {

        Simbolo izq = visit(ctx.expresion(0));
        Simbolo der = visit(ctx.expresion(1));

        if (!(izq.valor instanceof Number) || !(der.valor instanceof Number)) {
            throw error("RS08", "Las condiciones solo aceptan valores numéricos", ctx.getStart().getLine());
        }

        double a = ((Number) izq.valor).doubleValue();
        double b = ((Number) der.valor).doubleValue();

        String op = ctx.comparador().getText();

        boolean res = switch (op) {
            case "Estrategia" -> a > b;
            case "RTS" -> a < b;
            case "TBS" -> a == b;
            case "CuatroX" -> a >= b;
            case "MOBA" -> a <= b;
            default -> throw error("RS05", "Comparador inválido detectado", ctx.getStart().getLine());
        };

        return new Simbolo("bool", res);
    }

    @Override
    public Simbolo visitExpresion(GameRoleParser.ExpresionContext ctx) {
        if (ctx.NUMBER() != null)
            return new Simbolo("int", Integer.parseInt(ctx.NUMBER().getText()));
        if (ctx.STRING() != null)
            return new Simbolo("string", ctx.STRING().getText().replace("\"", ""));
        if (ctx.ID() != null) {
            String id = ctx.ID().getText();

            if (!tabla.containsKey(id)) {
                throw error("RS01", "Variable no declarada: " + id, ctx.getStart().getLine());
            }
            return tabla.get(id);
        }

        if (ctx.expresion().size() == 2) {
            Simbolo izq = visit(ctx.expresion(0));
            Simbolo der = visit(ctx.expresion(1));
            String op = ctx.operadorAritmetico().getText();

            if (op.equals("Lucha") &&
                (izq.valor instanceof String || der.valor instanceof String)) {
                return new Simbolo("string",
                        formatear(izq.valor) + formatear(der.valor));
            }

            if (!(izq.valor instanceof Number) || !(der.valor instanceof Number)) {
                throw error("RS03", "Tipos incompatibles de datos en operación", ctx.getStart().getLine());
            }

            double a = ((Number) izq.valor).doubleValue();
            double b = ((Number) der.valor).doubleValue();
            double res;

            switch (op) {
                case "Lucha": res = a + b; break;
                case "Beat_em_up": res = a - b; break;
                case "Hack_and_slash": res = a * b; break;
                case "Soulslike":
                    if (b == 0)
                        throw error("RS04", "No se puede hacer una división entre cero", ctx.getStart().getLine());
                    res = a / b;
                    break;
                case "Roguelike":
                    if (a != (int) a || b != (int) b) {
                        throw error("RS03", "El operador Roguelike solo acepta enteros", ctx.getStart().getLine());
                    }
                    int ia = (int) a;
                    int ib = (int) b;

                    if (ib == 0)
                        throw error("RS04", "No se puede dividir entre cero", ctx.getStart().getLine());
                    return new Simbolo("int", ia % ib);
                default:
                    throw error("RS08", "Operador no válido en el lenguaje", ctx.getStart().getLine());
            }
            
            if (res == (int) res) {
                return new Simbolo("int", (int) res);
            }
            return new Simbolo("float", res);
        }
        return visit(ctx.expresion(0));
    }

    private void validarTipo(String tipo, Simbolo val, int linea) {
        switch (tipo) {
            case "Simulacion":
                if (!(val.valor instanceof Integer || val.valor instanceof Double))
                    throw error("RS03", "Tipos incompatibles en asignación (se esperaba número)", linea);
                break;
            case "Simulacion_vida":
                if (!(val.valor instanceof String))
                    throw error("RS03", "Tipos incompatibles en asignación (se esperaba texto)", linea);
                break;
            case "Simulacion_construccion":
                if (!(val.valor instanceof Boolean))
                    throw error("RS03", "Tipos incompatibles en asignación (se esperaba booleano)", linea);
                break;
            case "Simulacion_vuelo":
                if (!(val.valor instanceof Double || val.valor instanceof Integer))
                    throw error("RS03", "Tipos incompatibles en asignación (se esperaba decimal)", linea);
                break;
        }
    }

    private String formatear(Object val) {
        if (val instanceof Double) {
            double d = (Double) val;
            if (d == (int) d) return String.valueOf((int) d);
            return String.format("%.2f", d);
        }
        return String.valueOf(val);
    }
}