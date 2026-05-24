// Generated from c:/Users/rodic/OneDrive/Documentos/GitHub/Automatas-II/GameRoles/demo/src/main/antlr4/game_role/example/GameRole.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GameRoleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4296002816L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 245760L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67645734920L) != 0)) {
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
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67645734920L) != 0)) {
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1040187392L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32505856L) != 0)) ) {
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
		"\u0004\u0001$\u0090\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001%\b\u0001"+
		"\u000b\u0001\f\u0001&\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002/\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006K\b\u0006\n\u0006\f\u0006N\t\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0003\tg\b\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\tm\b\t\u0001\t\u0003\tp\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0083"+
		"\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u0089\b\f\n\f\f\f\u008c\t"+
		"\f\u0001\r\u0001\r\u0001\r\u0000\u0001\u0018\u000e\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0003\u0001\u0000"+
		"\u000e\u0011\u0001\u0000\u0019\u001d\u0001\u0000\u0014\u0018\u0092\u0000"+
		"\u001c\u0001\u0000\u0000\u0000\u0002$\u0001\u0000\u0000\u0000\u0004.\u0001"+
		"\u0000\u0000\u0000\u00060\u0001\u0000\u0000\u0000\b5\u0001\u0000\u0000"+
		"\u0000\n7\u0001\u0000\u0000\u0000\f;\u0001\u0000\u0000\u0000\u000eO\u0001"+
		"\u0000\u0000\u0000\u0010W\u0001\u0000\u0000\u0000\u0012o\u0001\u0000\u0000"+
		"\u0000\u0014q\u0001\u0000\u0000\u0000\u0016u\u0001\u0000\u0000\u0000\u0018"+
		"\u0082\u0001\u0000\u0000\u0000\u001a\u008d\u0001\u0000\u0000\u0000\u001c"+
		"\u001d\u0005\u0001\u0000\u0000\u001d\u001e\u0005 \u0000\u0000\u001e\u001f"+
		"\u0005\f\u0000\u0000\u001f \u0003\u0002\u0001\u0000 !\u0005\r\u0000\u0000"+
		"!\"\u0005\u0000\u0000\u0001\"\u0001\u0001\u0000\u0000\u0000#%\u0003\u0004"+
		"\u0002\u0000$#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001"+
		"\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'\u0003\u0001\u0000\u0000"+
		"\u0000(/\u0003\u0006\u0003\u0000)/\u0003\n\u0005\u0000*/\u0003\f\u0006"+
		"\u0000+/\u0003\u000e\u0007\u0000,/\u0003\u0010\b\u0000-/\u0003\u0012\t"+
		"\u0000.(\u0001\u0000\u0000\u0000.)\u0001\u0000\u0000\u0000.*\u0001\u0000"+
		"\u0000\u0000.+\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000.-\u0001"+
		"\u0000\u0000\u0000/\u0005\u0001\u0000\u0000\u000001\u0003\b\u0004\u0000"+
		"12\u0005 \u0000\u000023\u0005\u0002\u0000\u000034\u0003\u0018\f\u0000"+
		"4\u0007\u0001\u0000\u0000\u000056\u0007\u0000\u0000\u00006\t\u0001\u0000"+
		"\u0000\u000078\u0005 \u0000\u000089\u0005\u0002\u0000\u00009:\u0003\u0018"+
		"\f\u0000:\u000b\u0001\u0000\u0000\u0000;<\u0005\b\u0000\u0000<=\u0005"+
		"\u0003\u0000\u0000=>\u0003\u0014\n\u0000>?\u0005\u0004\u0000\u0000?@\u0005"+
		"\f\u0000\u0000@A\u0003\u0002\u0001\u0000AL\u0005\r\u0000\u0000BC\u0005"+
		"\t\u0000\u0000CD\u0005\u0003\u0000\u0000DE\u0003\u0014\n\u0000EF\u0005"+
		"\u0004\u0000\u0000FG\u0005\f\u0000\u0000GH\u0003\u0002\u0001\u0000HI\u0005"+
		"\r\u0000\u0000IK\u0001\u0000\u0000\u0000JB\u0001\u0000\u0000\u0000KN\u0001"+
		"\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000"+
		"M\r\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OP\u0005\u000b\u0000"+
		"\u0000PQ\u0005\u0003\u0000\u0000QR\u0003\u0014\n\u0000RS\u0005\u0004\u0000"+
		"\u0000ST\u0005\f\u0000\u0000TU\u0003\u0002\u0001\u0000UV\u0005\r\u0000"+
		"\u0000V\u000f\u0001\u0000\u0000\u0000WX\u0005\n\u0000\u0000XY\u0005\u0003"+
		"\u0000\u0000YZ\u0003\n\u0005\u0000Z[\u0005\u0005\u0000\u0000[\\\u0003"+
		"\u0014\n\u0000\\]\u0005\u0005\u0000\u0000]^\u0003\n\u0005\u0000^_\u0005"+
		"\u0004\u0000\u0000_`\u0005\f\u0000\u0000`a\u0003\u0002\u0001\u0000ab\u0005"+
		"\r\u0000\u0000b\u0011\u0001\u0000\u0000\u0000cd\u0005\u0012\u0000\u0000"+
		"df\u0005\u0003\u0000\u0000eg\u0003\u0018\f\u0000fe\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hp\u0005\u0004\u0000"+
		"\u0000ij\u0005\u0013\u0000\u0000jl\u0005\u0003\u0000\u0000km\u0003\u0018"+
		"\f\u0000lk\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000np\u0005\u0004\u0000\u0000oc\u0001\u0000\u0000\u0000oi\u0001"+
		"\u0000\u0000\u0000p\u0013\u0001\u0000\u0000\u0000qr\u0003\u0018\f\u0000"+
		"rs\u0003\u0016\u000b\u0000st\u0003\u0018\f\u0000t\u0015\u0001\u0000\u0000"+
		"\u0000uv\u0007\u0001\u0000\u0000v\u0017\u0001\u0000\u0000\u0000wx\u0006"+
		"\f\uffff\uffff\u0000x\u0083\u0005 \u0000\u0000y\u0083\u0005\"\u0000\u0000"+
		"z\u0083\u0005!\u0000\u0000{\u0083\u0005#\u0000\u0000|\u0083\u0005\u001e"+
		"\u0000\u0000}\u0083\u0005\u001f\u0000\u0000~\u007f\u0005\u0003\u0000\u0000"+
		"\u007f\u0080\u0003\u0018\f\u0000\u0080\u0081\u0005\u0004\u0000\u0000\u0081"+
		"\u0083\u0001\u0000\u0000\u0000\u0082w\u0001\u0000\u0000\u0000\u0082y\u0001"+
		"\u0000\u0000\u0000\u0082z\u0001\u0000\u0000\u0000\u0082{\u0001\u0000\u0000"+
		"\u0000\u0082|\u0001\u0000\u0000\u0000\u0082}\u0001\u0000\u0000\u0000\u0082"+
		"~\u0001\u0000\u0000\u0000\u0083\u008a\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\n\b\u0000\u0000\u0085\u0086\u0003\u001a\r\u0000\u0086\u0087\u0003\u0018"+
		"\f\t\u0087\u0089\u0001\u0000\u0000\u0000\u0088\u0084\u0001\u0000\u0000"+
		"\u0000\u0089\u008c\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000"+
		"\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u0019\u0001\u0000\u0000"+
		"\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u008e\u0007\u0002\u0000"+
		"\u0000\u008e\u001b\u0001\u0000\u0000\u0000\b&.Lflo\u0082\u008a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}