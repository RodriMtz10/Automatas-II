grammar GameRole;

programa
    : 'Juego' ID MUNDO_ABIERTO bloque SANDBOX EOF
    ;

bloque
    : instruccion+
    ;

instruccion
    : declaracion
    | asignacion
    | decision
    | repeticionWhile
    | repeticionFor
    | imprimir
    ;

declaracion
    : tipo ID '=' expresion
    ;

tipo
    : SIMULACION                  // int
    | SIMULACION_VIDA             // string
    | SIMULACION_CONSTRUCCION     // bool
    | SIMULACION_VUELO            // float
    ;

asignacion
    : ID '=' expresion
    ;

decision
    : ACCION '(' condicion ')' MUNDO_ABIERTO bloque SANDBOX
      (ACCION_AVENTURA '(' condicion ')' MUNDO_ABIERTO bloque SANDBOX)*
    ;

repeticionWhile
    : SUPERVIVENCIA '(' condicion ')' MUNDO_ABIERTO bloque SANDBOX
    ;

repeticionFor
    : AVENTURA '(' asignacion ';' condicion ';' asignacion ')' MUNDO_ABIERTO bloque SANDBOX
    ;

imprimir
    : FPS '(' expresion? ')'
    | TPS '(' expresion? ')'
    ;

condicion
    : expresion comparador expresion
    ;

comparador
    : ESTRATEGIA   // >
    | RTS          // <
    | TBS          // ==
    | CUATRO_X     // >=
    | MOBA         // <=
    ;

expresion
    : expresion operadorAritmetico expresion
    | ID
    | FLOAT
    | NUMBER
    | STRING
    | TRUE
    | FALSE
    | '(' expresion ')'
    ;

operadorAritmetico
    : LUCHA          // +
    | BEAT_EM_UP     // -
    | HACK_AND_SLASH // *
    | SOULSLIKE      // /
    | ROGUELIKE      // %
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;

ACCION : 'Accion';
ACCION_AVENTURA : 'Accion_aventura';
AVENTURA : 'Aventura';
SUPERVIVENCIA : 'Supervivencia';

MUNDO_ABIERTO : 'Mundo_abierto';
SANDBOX : 'Sandbox';

SIMULACION : 'Simulacion';
SIMULACION_VIDA : 'Simulacion_vida';
SIMULACION_CONSTRUCCION : 'Simulacion_construccion';
SIMULACION_VUELO: 'Simulacion_vuelo';

FPS : 'FPS';
TPS : 'TPS';

LUCHA : 'Lucha';
BEAT_EM_UP : 'Beat_em_up';
HACK_AND_SLASH : 'Hack_and_slash';
SOULSLIKE : 'Soulslike';
ROGUELIKE : 'Roguelike';

ESTRATEGIA : 'Estrategia';
RTS : 'RTS';
TBS : 'TBS';
CUATRO_X : 'CuatroX';
MOBA : 'MOBA';

TRUE : 'true';
FALSE : 'false';

ID : [a-zA-Z_][a-zA-Z0-9_]* ;
NUMBER : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]+ ;
STRING : '"' .*? '"' ;

WS : [ \t\r\n]+ -> skip ;