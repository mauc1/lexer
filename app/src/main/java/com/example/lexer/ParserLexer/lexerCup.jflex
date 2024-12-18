/* https://www.jflex.de/manual.html */
/* JFlex example: partial Java language lexer specification */
package ParserLexer;
import java.io.StringReader;
import java_cup.runtime.*;

/*Declaraciones y opciones*/
%%

%class Lexer
%public 
%unicode
%cup
%line
%column

%{
    //string para imprimir los contenidos de los tokens encontrados
    StringBuffer string = new StringBuffer();
    private int errorCount = 0;

    //symbol predefinidos por el ejemplo proporcionado por jflex
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

    //manejar errores y reportar
    private void handleError(String message) {
        System.err.println("ERROR: " + message + ", línea " + yyline + ", columna " + yycolumn);
        errorCount++;
    }
%}

//expresiones basicas
LineTerminator = \r|\n|\r\n
WHITESPACE     = {LineTerminator} | [ \t\f]
LETRA = [a-zA-Z]
DIGITO = [0-9]
IDENTIFICADOR = ("_" {LETRA} "_")
Float = -? (0 | {digitoNoCero} {digito}*) ("." {digito}+)? (("e" | "E") -? {digito}+)?

/* Se definen las expresiones para commentarios */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment   = "/\_" [^\_] ~"\_/" | "/\_" "\_"+ "/"
TraditionalCommentError = "/_" [^\_]* 
EndOfLineComment     = "@" {InputCharacter}* {LineTerminator}?
CommentContent       =  ([^\_] | \_+ [^/\_])*


/* Reglas y estados lexicos */
%state STRING
%state ERROR
%state DECREMENT

%%

//palabras reservadas
<YYINITIAL> "rodolfo"         { return symbol(sym.INTEGER); }
<YYINITIAL> "bromista"        { return symbol(sym.FLOAT); }
<YYINITIAL> "trueno"          { return symbol(sym.BOOL); }
<YYINITIAL> "cupido"          { return symbol(sym.CHAR); }
<YYINITIAL> "cometa"          { return symbol(sym.STRING); }
<YYINITIAL> "false"           { return symbol(sym.FALSE); }
<YYINITIAL> "true"            { return symbol(sym.TRUE); }
<YYINITIAL> "abrecuento"      { return symbol(sym.ABRECUENTO); }
<YYINITIAL> "cierracuento"    { return symbol(sym.CIERRACUENTO); }
<YYINITIAL> "abreempaque"     { return symbol(sym.ABREEMPAQUE); }
<YYINITIAL> "cierraempaque"   { return symbol(sym.CIERRAEMPAQUE); }
<YYINITIAL> "abreregalo"      { return symbol(sym.ABREREGALO); }
<YYINITIAL> "cierraregalo"    { return symbol(sym.CIERRAREGALO); }
<YYINITIAL> "finregalo"       { return symbol(sym.FINREGALO); }
<YYINITIAL> "navidad"         { return symbol(sym.SUMA); }
<YYINITIAL> "intercambio"     { return symbol(sym.RESTA); }
<YYINITIAL> "reyes"           { return symbol(sym.DIVISION); }
<YYINITIAL> "nochebuena"      { return symbol(sym.MULTIPLICACION); }
<YYINITIAL> "magos"           { return symbol(sym.MODULO); }
<YYINITIAL> "adviento"        { return symbol(sym.POTENCIA); }
<YYINITIAL> "quien"           { return symbol(sym.INCREMENTO); }
<YYINITIAL> "grinch"          { return symbol(sym.DECREMENTO); }
<YYINITIAL> "snowball"        { return symbol(sym.MENOR); }
<YYINITIAL> "evergreen"       { return symbol(sym.MENORIGUAL); }
<YYINITIAL> "minstix"         { return symbol(sym.MAYOR); }
<YYINITIAL> "upatree"         { return symbol(sym.MAYORIGUAL); }
<YYINITIAL> "mary"            { return symbol(sym.IGUALDAD); }
<YYINITIAL> "openslae"        { return symbol(sym.DIFERENTE); }
<YYINITIAL> "melchor"         { return symbol(sym.CONJUNCION); }
<YYINITIAL> "gaspar"          { return symbol(sym.DISYUNCION); }
<YYINITIAL> "baltazar"        { return symbol(sym.NEGACION); }
<YYINITIAL> "elfo"            { return symbol(sym.IF); }
<YYINITIAL> "hada"            { return symbol(sym.ELSE); }
<YYINITIAL> "envuelve"        { return symbol(sym.WHILE); }
<YYINITIAL> "duende"          { return symbol(sym.FOR); }
<YYINITIAL> "varios"          { return symbol(sym.SWITCH); }
<YYINITIAL> "historia"        { return symbol(sym.CASE); }
<YYINITIAL> "último"          { return symbol(sym.DEFAULT); }
<YYINITIAL> "corta"           { return symbol(sym.BREAK); }
<YYINITIAL> "envia"           { return symbol(sym.RETURN); }
<YYINITIAL> "sigue"           { return symbol(sym.DOSPUNTOS); }
<YYINITIAL> "narra"           { return symbol(sym.PRINT); }
<YYINITIAL> "escucha"         { return symbol(sym.READ); }
<YYINITIAL> "_verano_"        { return symbol(sym.MAIN); }
//comentarios
<YYINITIAL> "#"               { /* comentarios de una línea */ }
<YYINITIAL> "\\_ .* _/"       { /* comentarios multilínea */ }
//comentarios
<YYINITIAL> "//".*          { /*  comentarios de una línea */ }
<YYINITIAL> "/\\*"([^*]|\\*+[^*/])*"\\*/" { /*  comentarios multilínea */ }
//ids
<YYINITIAL>{IDENTIFICADOR}   { return symbol(sym.IDENTIFICADOR); }
// Ignorar espacios en blanco
{WHITESPACE}    { /* Ignorar */ }



<STRING> {
    \"                             { yybegin(YYINITIAL); 
                                    return symbol(sym.t_string_nicolas, 
                                    ("\"" + string.toString() + "\"")); }

    [^\n\r\"\\]                   { string.append( yytext() ); }
    \\t                            { string.append('\t'); }
    \\n                            { string.append('\n'); }

    \\r                            { string.append('\r'); }
    \\\"                           { string.append('\"'); }
    \\                             { string.append('\\'); }  
    
    .                              { handleError("Carácter no reconocido en la cadena"); }
}



//REGLAS---------------------------
//reglas de recuperación en modo panico
<YYINITIAL,ERROR> {
    //caracteres no validos, se intenta avanzar al siguiente caracter reconocible
    [^] { 
        yycolumn++;
        return symbol(sym.ERROR);
    }
}


<YYINITIAL,ERROR> "_/" {
    //fin de comentario no encontrado, se intenta recuperar
    yybegin(YYINITIAL);
    yycolumn += 2; //avanzar 2 caracteres para evitar un bucle infinito
    return symbol(sym.ERROR);
}


<YYINITIAL,ERROR> [^ \t\n\r\"\-\)\(\+\!\{\}\[\]\|\,\#]* {
    //ignorar cualquier otro carácter durante la recuperación
    yycolumn += yylength();
    return symbol(sym.ERROR);
}


// fallback en caso de encontrar un caracter no reconocido
[^]                              { 
                                    yycolumn++;
                                    return symbol(sym.ERROR);
                                }
