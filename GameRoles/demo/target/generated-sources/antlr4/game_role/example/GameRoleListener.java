// Generated from game_role\example\GameRole.g4 by ANTLR 4.9.2
package game_role.example;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GameRoleParser}.
 */
public interface GameRoleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(GameRoleParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(GameRoleParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(GameRoleParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(GameRoleParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(GameRoleParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(GameRoleParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(GameRoleParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(GameRoleParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(GameRoleParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(GameRoleParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(GameRoleParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(GameRoleParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#decision}.
	 * @param ctx the parse tree
	 */
	void enterDecision(GameRoleParser.DecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#decision}.
	 * @param ctx the parse tree
	 */
	void exitDecision(GameRoleParser.DecisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#repeticionWhile}.
	 * @param ctx the parse tree
	 */
	void enterRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#repeticionWhile}.
	 * @param ctx the parse tree
	 */
	void exitRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#repeticionFor}.
	 * @param ctx the parse tree
	 */
	void enterRepeticionFor(GameRoleParser.RepeticionForContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#repeticionFor}.
	 * @param ctx the parse tree
	 */
	void exitRepeticionFor(GameRoleParser.RepeticionForContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#imprimir}.
	 * @param ctx the parse tree
	 */
	void enterImprimir(GameRoleParser.ImprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#imprimir}.
	 * @param ctx the parse tree
	 */
	void exitImprimir(GameRoleParser.ImprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion(GameRoleParser.CondicionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion(GameRoleParser.CondicionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#comparador}.
	 * @param ctx the parse tree
	 */
	void enterComparador(GameRoleParser.ComparadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#comparador}.
	 * @param ctx the parse tree
	 */
	void exitComparador(GameRoleParser.ComparadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(GameRoleParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(GameRoleParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameRoleParser#operadorAritmetico}.
	 * @param ctx the parse tree
	 */
	void enterOperadorAritmetico(GameRoleParser.OperadorAritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameRoleParser#operadorAritmetico}.
	 * @param ctx the parse tree
	 */
	void exitOperadorAritmetico(GameRoleParser.OperadorAritmeticoContext ctx);
}