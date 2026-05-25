// Generated from game_role\example\GameRole.g4 by ANTLR 4.9.2
package game_role.example;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GameRoleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, LINE_COMMENT=6, BLOCK_COMMENT=7, 
		ACCION=8, ACCION_AVENTURA=9, AVENTURA=10, SUPERVIVENCIA=11, MUNDO_ABIERTO=12, 
		SANDBOX=13, SIMULACION=14, SIMULACION_VIDA=15, SIMULACION_CONSTRUCCION=16, 
		SIMULACION_VUELO=17, FPS=18, TPS=19, LUCHA=20, BEAT_EM_UP=21, HACK_AND_SLASH=22, 
		SOULSLIKE=23, ROGUELIKE=24, ESTRATEGIA=25, RTS=26, TBS=27, CUATRO_X=28, 
		MOBA=29, TRUE=30, FALSE=31, ID=32, NUMBER=33, FLOAT=34, STRING=35, WS=36;
	public static final int
		RULE_programa = 0, RULE_bloque = 1, RULE_instruccion = 2, RULE_declaracion = 3, 
		RULE_tipo = 4, RULE_asignacion = 5, RULE_decision = 6, RULE_repeticionWhile = 7, 
		RULE_repeticionFor = 8, RULE_imprimir = 9, RULE_condicion = 10, RULE_comparador = 11, 
		RULE_expresion = 12, RULE_operadorAritmetico = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "bloque", "instruccion", "declaracion", "tipo", "asignacion", 
			"decision", "repeticionWhile", "repeticionFor", "imprimir", "condicion", 
			"comparador", "expresion", "operadorAritmetico"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Juego'", "'='", "'('", "')'", "';'", null, null, "'Accion'", 
			"'Accion_aventura'", "'Aventura'", "'Supervivencia'", "'Mundo_abierto'", 
			"'Sandbox'", "'Simulacion'", "'Simulacion_vida'", "'Simulacion_construccion'", 
			"'Simulacion_vuelo'", "'FPS'", "'TPS'", "'Lucha'", "'Beat_em_up'", "'Hack_and_slash'", 
			"'Soulslike'", "'Roguelike'", "'Estrategia'", "'RTS'", "'TBS'", "'CuatroX'", 
			"'MOBA'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "LINE_COMMENT", "BLOCK_COMMENT", 
			"ACCION", "ACCION_AVENTURA", "AVENTURA", "SUPERVIVENCIA", "MUNDO_ABIERTO", 
			"SANDBOX", "SIMULACION", "SIMULACION_VIDA", "SIMULACION_CONSTRUCCION", 
			"SIMULACION_VUELO", "FPS", "TPS", "LUCHA", "BEAT_EM_UP", "HACK_AND_SLASH", 
			"SOULSLIKE", "ROGUELIKE", "ESTRATEGIA", "RTS", "TBS", "CUATRO_X", "MOBA", 
			"TRUE", "FALSE", "ID", "NUMBER", "FLOAT", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GameRole.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GameRoleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GameRoleParser.ID, 0); }
		public TerminalNode MUNDO_ABIERTO() { return getToken(GameRoleParser.MUNDO_ABIERTO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode SANDBOX() { return getToken(GameRoleParser.SANDBOX, 0); }
		public TerminalNode EOF() { return getToken(GameRoleParser.EOF, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			match(ID);
			setState(30);
			match(MUNDO_ABIERTO);
			setState(31);
			bloque();
			setState(32);
			match(SANDBOX);
			setState(33);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BloqueContext extends ParserRuleContext {
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public BloqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterBloque(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitBloque(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitBloque(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bloque);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				instruccion();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ACCION) | (1L << AVENTURA) | (1L << SUPERVIVENCIA) | (1L << SIMULACION) | (1L << SIMULACION_VIDA) | (1L << SIMULACION_CONSTRUCCION) | (1L << SIMULACION_VUELO) | (1L << FPS) | (1L << TPS) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstruccionContext extends ParserRuleContext {
		public DeclaracionContext declaracion() {
			return getRuleContext(DeclaracionContext.class,0);
		}
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public DecisionContext decision() {
			return getRuleContext(DecisionContext.class,0);
		}
		public RepeticionWhileContext repeticionWhile() {
			return getRuleContext(RepeticionWhileContext.class,0);
		}
		public RepeticionForContext repeticionFor() {
			return getRuleContext(RepeticionForContext.class,0);
		}
		public ImprimirContext imprimir() {
			return getRuleContext(ImprimirContext.class,0);
		}
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterInstruccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitInstruccion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitInstruccion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruccion);
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SIMULACION:
			case SIMULACION_VIDA:
			case SIMULACION_CONSTRUCCION:
			case SIMULACION_VUELO:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				declaracion();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				asignacion();
				}
				break;
			case ACCION:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				decision();
				}
				break;
			case SUPERVIVENCIA:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				repeticionWhile();
				}
				break;
			case AVENTURA:
				enterOuterAlt(_localctx, 5);
				{
				setState(44);
				repeticionFor();
				}
				break;
			case FPS:
			case TPS:
				enterOuterAlt(_localctx, 6);
				{
				setState(45);
				imprimir();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaracionContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode ID() { return getToken(GameRoleParser.ID, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public DeclaracionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitDeclaracion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitDeclaracion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionContext declaracion() throws RecognitionException {
		DeclaracionContext _localctx = new DeclaracionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaracion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			tipo();
			setState(49);
			match(ID);
			setState(50);
			match(T__1);
			setState(51);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TerminalNode SIMULACION() { return getToken(GameRoleParser.SIMULACION, 0); }
		public TerminalNode SIMULACION_VIDA() { return getToken(GameRoleParser.SIMULACION_VIDA, 0); }
		public TerminalNode SIMULACION_CONSTRUCCION() { return getToken(GameRoleParser.SIMULACION_CONSTRUCCION, 0); }
		public TerminalNode SIMULACION_VUELO() { return getToken(GameRoleParser.SIMULACION_VUELO, 0); }
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitTipo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitTipo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tipo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SIMULACION) | (1L << SIMULACION_VIDA) | (1L << SIMULACION_CONSTRUCCION) | (1L << SIMULACION_VUELO))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AsignacionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GameRoleParser.ID, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitAsignacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitAsignacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(ID);
			setState(56);
			match(T__1);
			setState(57);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecisionContext extends ParserRuleContext {
		public TerminalNode ACCION() { return getToken(GameRoleParser.ACCION, 0); }
		public List<CondicionContext> condicion() {
			return getRuleContexts(CondicionContext.class);
		}
		public CondicionContext condicion(int i) {
			return getRuleContext(CondicionContext.class,i);
		}
		public List<TerminalNode> MUNDO_ABIERTO() { return getTokens(GameRoleParser.MUNDO_ABIERTO); }
		public TerminalNode MUNDO_ABIERTO(int i) {
			return getToken(GameRoleParser.MUNDO_ABIERTO, i);
		}
		public List<BloqueContext> bloque() {
			return getRuleContexts(BloqueContext.class);
		}
		public BloqueContext bloque(int i) {
			return getRuleContext(BloqueContext.class,i);
		}
		public List<TerminalNode> SANDBOX() { return getTokens(GameRoleParser.SANDBOX); }
		public TerminalNode SANDBOX(int i) {
			return getToken(GameRoleParser.SANDBOX, i);
		}
		public List<TerminalNode> ACCION_AVENTURA() { return getTokens(GameRoleParser.ACCION_AVENTURA); }
		public TerminalNode ACCION_AVENTURA(int i) {
			return getToken(GameRoleParser.ACCION_AVENTURA, i);
		}
		public DecisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decision; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterDecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitDecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitDecision(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecisionContext decision() throws RecognitionException {
		DecisionContext _localctx = new DecisionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_decision);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(ACCION);
			setState(60);
			match(T__2);
			setState(61);
			condicion();
			setState(62);
			match(T__3);
			setState(63);
			match(MUNDO_ABIERTO);
			setState(64);
			bloque();
			setState(65);
			match(SANDBOX);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACCION_AVENTURA) {
				{
				{
				setState(66);
				match(ACCION_AVENTURA);
				setState(67);
				match(T__2);
				setState(68);
				condicion();
				setState(69);
				match(T__3);
				setState(70);
				match(MUNDO_ABIERTO);
				setState(71);
				bloque();
				setState(72);
				match(SANDBOX);
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeticionWhileContext extends ParserRuleContext {
		public TerminalNode SUPERVIVENCIA() { return getToken(GameRoleParser.SUPERVIVENCIA, 0); }
		public CondicionContext condicion() {
			return getRuleContext(CondicionContext.class,0);
		}
		public TerminalNode MUNDO_ABIERTO() { return getToken(GameRoleParser.MUNDO_ABIERTO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode SANDBOX() { return getToken(GameRoleParser.SANDBOX, 0); }
		public RepeticionWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeticionWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterRepeticionWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitRepeticionWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitRepeticionWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeticionWhileContext repeticionWhile() throws RecognitionException {
		RepeticionWhileContext _localctx = new RepeticionWhileContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_repeticionWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(SUPERVIVENCIA);
			setState(80);
			match(T__2);
			setState(81);
			condicion();
			setState(82);
			match(T__3);
			setState(83);
			match(MUNDO_ABIERTO);
			setState(84);
			bloque();
			setState(85);
			match(SANDBOX);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeticionForContext extends ParserRuleContext {
		public TerminalNode AVENTURA() { return getToken(GameRoleParser.AVENTURA, 0); }
		public List<AsignacionContext> asignacion() {
			return getRuleContexts(AsignacionContext.class);
		}
		public AsignacionContext asignacion(int i) {
			return getRuleContext(AsignacionContext.class,i);
		}
		public CondicionContext condicion() {
			return getRuleContext(CondicionContext.class,0);
		}
		public TerminalNode MUNDO_ABIERTO() { return getToken(GameRoleParser.MUNDO_ABIERTO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode SANDBOX() { return getToken(GameRoleParser.SANDBOX, 0); }
		public RepeticionForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeticionFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterRepeticionFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitRepeticionFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitRepeticionFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeticionForContext repeticionFor() throws RecognitionException {
		RepeticionForContext _localctx = new RepeticionForContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_repeticionFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(AVENTURA);
			setState(88);
			match(T__2);
			setState(89);
			asignacion();
			setState(90);
			match(T__4);
			setState(91);
			condicion();
			setState(92);
			match(T__4);
			setState(93);
			asignacion();
			setState(94);
			match(T__3);
			setState(95);
			match(MUNDO_ABIERTO);
			setState(96);
			bloque();
			setState(97);
			match(SANDBOX);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImprimirContext extends ParserRuleContext {
		public TerminalNode FPS() { return getToken(GameRoleParser.FPS, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode TPS() { return getToken(GameRoleParser.TPS, 0); }
		public ImprimirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imprimir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterImprimir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitImprimir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitImprimir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImprimirContext imprimir() throws RecognitionException {
		ImprimirContext _localctx = new ImprimirContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_imprimir);
		int _la;
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FPS:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(FPS);
				setState(100);
				match(T__2);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << TRUE) | (1L << FALSE) | (1L << ID) | (1L << NUMBER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
					{
					setState(101);
					expresion(0);
					}
				}

				setState(104);
				match(T__3);
				}
				break;
			case TPS:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(TPS);
				setState(106);
				match(T__2);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << TRUE) | (1L << FALSE) | (1L << ID) | (1L << NUMBER) | (1L << FLOAT) | (1L << STRING))) != 0)) {
					{
					setState(107);
					expresion(0);
					}
				}

				setState(110);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondicionContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public ComparadorContext comparador() {
			return getRuleContext(ComparadorContext.class,0);
		}
		public CondicionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterCondicion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitCondicion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitCondicion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicionContext condicion() throws RecognitionException {
		CondicionContext _localctx = new CondicionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_condicion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			expresion(0);
			setState(114);
			comparador();
			setState(115);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparadorContext extends ParserRuleContext {
		public TerminalNode ESTRATEGIA() { return getToken(GameRoleParser.ESTRATEGIA, 0); }
		public TerminalNode RTS() { return getToken(GameRoleParser.RTS, 0); }
		public TerminalNode TBS() { return getToken(GameRoleParser.TBS, 0); }
		public TerminalNode CUATRO_X() { return getToken(GameRoleParser.CUATRO_X, 0); }
		public TerminalNode MOBA() { return getToken(GameRoleParser.MOBA, 0); }
		public ComparadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterComparador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitComparador(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitComparador(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparadorContext comparador() throws RecognitionException {
		ComparadorContext _localctx = new ComparadorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comparador);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ESTRATEGIA) | (1L << RTS) | (1L << TBS) | (1L << CUATRO_X) | (1L << MOBA))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpresionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GameRoleParser.ID, 0); }
		public TerminalNode FLOAT() { return getToken(GameRoleParser.FLOAT, 0); }
		public TerminalNode NUMBER() { return getToken(GameRoleParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(GameRoleParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(GameRoleParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GameRoleParser.FALSE, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public OperadorAritmeticoContext operadorAritmetico() {
			return getRuleContext(OperadorAritmeticoContext.class,0);
		}
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitExpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitExpresion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expresion, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(120);
				match(ID);
				}
				break;
			case FLOAT:
				{
				setState(121);
				match(FLOAT);
				}
				break;
			case NUMBER:
				{
				setState(122);
				match(NUMBER);
				}
				break;
			case STRING:
				{
				setState(123);
				match(STRING);
				}
				break;
			case TRUE:
				{
				setState(124);
				match(TRUE);
				}
				break;
			case FALSE:
				{
				setState(125);
				match(FALSE);
				}
				break;
			case T__2:
				{
				setState(126);
				match(T__2);
				setState(127);
				expresion(0);
				setState(128);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpresionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expresion);
					setState(132);
					if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
					setState(133);
					operadorAritmetico();
					setState(134);
					expresion(9);
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OperadorAritmeticoContext extends ParserRuleContext {
		public TerminalNode LUCHA() { return getToken(GameRoleParser.LUCHA, 0); }
		public TerminalNode BEAT_EM_UP() { return getToken(GameRoleParser.BEAT_EM_UP, 0); }
		public TerminalNode HACK_AND_SLASH() { return getToken(GameRoleParser.HACK_AND_SLASH, 0); }
		public TerminalNode SOULSLIKE() { return getToken(GameRoleParser.SOULSLIKE, 0); }
		public TerminalNode ROGUELIKE() { return getToken(GameRoleParser.ROGUELIKE, 0); }
		public OperadorAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operadorAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).enterOperadorAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameRoleListener ) ((GameRoleListener)listener).exitOperadorAritmetico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameRoleVisitor ) return ((GameRoleVisitor<? extends T>)visitor).visitOperadorAritmetico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperadorAritmeticoContext operadorAritmetico() throws RecognitionException {
		OperadorAritmeticoContext _localctx = new OperadorAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_operadorAritmetico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LUCHA) | (1L << BEAT_EM_UP) | (1L << HACK_AND_SLASH) | (1L << SOULSLIKE) | (1L << ROGUELIKE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u0092\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\6\3\'\n\3\r\3\16\3(\3\4\3\4\3\4\3\4\3\4\3\4\5\4\61\n\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\7\bM\n\b\f\b\16\bP\13\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\5\13i\n\13\3\13\3\13\3\13\3\13\5\13o\n\13\3\13\5\13r\n\13\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5"+
		"\16\u0085\n\16\3\16\3\16\3\16\3\16\7\16\u008b\n\16\f\16\16\16\u008e\13"+
		"\16\3\17\3\17\3\17\2\3\32\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\5\3"+
		"\2\20\23\3\2\33\37\3\2\26\32\2\u0094\2\36\3\2\2\2\4&\3\2\2\2\6\60\3\2"+
		"\2\2\b\62\3\2\2\2\n\67\3\2\2\2\f9\3\2\2\2\16=\3\2\2\2\20Q\3\2\2\2\22Y"+
		"\3\2\2\2\24q\3\2\2\2\26s\3\2\2\2\30w\3\2\2\2\32\u0084\3\2\2\2\34\u008f"+
		"\3\2\2\2\36\37\7\3\2\2\37 \7\"\2\2 !\7\16\2\2!\"\5\4\3\2\"#\7\17\2\2#"+
		"$\7\2\2\3$\3\3\2\2\2%\'\5\6\4\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2"+
		"\2)\5\3\2\2\2*\61\5\b\5\2+\61\5\f\7\2,\61\5\16\b\2-\61\5\20\t\2.\61\5"+
		"\22\n\2/\61\5\24\13\2\60*\3\2\2\2\60+\3\2\2\2\60,\3\2\2\2\60-\3\2\2\2"+
		"\60.\3\2\2\2\60/\3\2\2\2\61\7\3\2\2\2\62\63\5\n\6\2\63\64\7\"\2\2\64\65"+
		"\7\4\2\2\65\66\5\32\16\2\66\t\3\2\2\2\678\t\2\2\28\13\3\2\2\29:\7\"\2"+
		"\2:;\7\4\2\2;<\5\32\16\2<\r\3\2\2\2=>\7\n\2\2>?\7\5\2\2?@\5\26\f\2@A\7"+
		"\6\2\2AB\7\16\2\2BC\5\4\3\2CN\7\17\2\2DE\7\13\2\2EF\7\5\2\2FG\5\26\f\2"+
		"GH\7\6\2\2HI\7\16\2\2IJ\5\4\3\2JK\7\17\2\2KM\3\2\2\2LD\3\2\2\2MP\3\2\2"+
		"\2NL\3\2\2\2NO\3\2\2\2O\17\3\2\2\2PN\3\2\2\2QR\7\r\2\2RS\7\5\2\2ST\5\26"+
		"\f\2TU\7\6\2\2UV\7\16\2\2VW\5\4\3\2WX\7\17\2\2X\21\3\2\2\2YZ\7\f\2\2Z"+
		"[\7\5\2\2[\\\5\f\7\2\\]\7\7\2\2]^\5\26\f\2^_\7\7\2\2_`\5\f\7\2`a\7\6\2"+
		"\2ab\7\16\2\2bc\5\4\3\2cd\7\17\2\2d\23\3\2\2\2ef\7\24\2\2fh\7\5\2\2gi"+
		"\5\32\16\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jr\7\6\2\2kl\7\25\2\2ln\7\5\2"+
		"\2mo\5\32\16\2nm\3\2\2\2no\3\2\2\2op\3\2\2\2pr\7\6\2\2qe\3\2\2\2qk\3\2"+
		"\2\2r\25\3\2\2\2st\5\32\16\2tu\5\30\r\2uv\5\32\16\2v\27\3\2\2\2wx\t\3"+
		"\2\2x\31\3\2\2\2yz\b\16\1\2z\u0085\7\"\2\2{\u0085\7$\2\2|\u0085\7#\2\2"+
		"}\u0085\7%\2\2~\u0085\7 \2\2\177\u0085\7!\2\2\u0080\u0081\7\5\2\2\u0081"+
		"\u0082\5\32\16\2\u0082\u0083\7\6\2\2\u0083\u0085\3\2\2\2\u0084y\3\2\2"+
		"\2\u0084{\3\2\2\2\u0084|\3\2\2\2\u0084}\3\2\2\2\u0084~\3\2\2\2\u0084\177"+
		"\3\2\2\2\u0084\u0080\3\2\2\2\u0085\u008c\3\2\2\2\u0086\u0087\f\n\2\2\u0087"+
		"\u0088\5\34\17\2\u0088\u0089\5\32\16\13\u0089\u008b\3\2\2\2\u008a\u0086"+
		"\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\33\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\t\4\2\2\u0090\35\3\2\2\2\n"+
		"(\60Nhnq\u0084\u008c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}