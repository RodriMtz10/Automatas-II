// Generated from game_role\example\GameRole.g4 by ANTLR 4.9.2
package game_role.example;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GameRoleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GameRoleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(GameRoleParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(GameRoleParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(GameRoleParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(GameRoleParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(GameRoleParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(GameRoleParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#decision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecision(GameRoleParser.DecisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#repeticionWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeticionWhile(GameRoleParser.RepeticionWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#repeticionFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeticionFor(GameRoleParser.RepeticionForContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#imprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImprimir(GameRoleParser.ImprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion(GameRoleParser.CondicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#comparador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparador(GameRoleParser.ComparadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(GameRoleParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameRoleParser#operadorAritmetico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperadorAritmetico(GameRoleParser.OperadorAritmeticoContext ctx);
}