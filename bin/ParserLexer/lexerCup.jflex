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
delim = \r|\n|\r\n
ESPACIO = {delim} | [ \t\f]
LETRA = [a-zA-Z]
DIGITO = [0-9]
ESCAPE = "\\"
DOBLECOMILLA = "\""
DIGITONOCERO = [1-9]
ID = "_"({LETRA}|{DIGITO})*"_"
Float = -? (0 | {DIGITONOCERO} {DIGITO}*) ("." {DIGITO}+)? (("e" | "E") -? {DIGITO}+)?
STRCOMPLETO = {DOBLECOMILLA}({LETRA}|{DIGITO})({LETRA}|{DIGITO})*{DOBLECOMILLA}
STRPALABRA = {LETRA}({LETRA}|{DIGITO})*

//comentario de una linea
COMENTARIOSIMPLE = "#" [^\n]* {delim}?
//comentario multilinea
MULTICOMENTARIO = "\\_" ([^\\_] | "\\_")* "_/"
//var de cualquier tipo de comentario para registrarla
COMENTARIO = {COMENTARIOSIMPLE} | {MULTICOMENTARIO}

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
<YYINITIAL> "abrecuento"      { return symbol(sym.BRACEIZQ); }
<YYINITIAL> "cierracuento"    { return symbol(sym.BRACEDER); }
<YYINITIAL> "abreempaque"     { return symbol(sym.CORCHETEIZQ); }
<YYINITIAL> "cierraempaque"   { return symbol(sym.CORCHETEDER); }
<YYINITIAL> "abreregalo"      { return symbol(sym.PARENIZQ); }
<YYINITIAL> "cierraregalo"    { return symbol(sym.PARENDER); }
<YYINITIAL> "finregalo"       { return symbol(sym.ENDEXPR); }
<YYINITIAL> "entrega"         { return symbol(sym.ASIGNACION); }
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
<YYINITIAL> ","               { return symbol(sym.COMMA); }
<YYINITIAL> "'"               { return symbol(sym.COMILLA); }
<YYINITIAL> "\""              { return symbol(sym.COMILLADOBLE); } 
<YYINITIAL> "!"               { return symbol(sym.CHAR); } //LO DEJAMOS COMO CHAR POR UN CODIGO DE EJEMPLO DEL PROFESOR
<YYINITIAL> {STRCOMPLETO}     { return symbol(sym.STRING); }
<YYINITIAL> {STRPALABRA}      { return symbol(sym.STRING); }
//comentarios
<YYINITIAL> {COMENTARIO} { }
<YYINITIAL> "//".*            { }
<YYINITIAL> "/\\*"([^*]|\\*+[^*/])*"\\*/" { }
//ids
<YYINITIAL> {ID}   { return symbol(sym.IDENTIFICADOR); }
// Ignorar espacios en blanco
<YYINITIAL> {ESPACIO}    { }


//MANEJO DE ERRORES
<YYINITIAL,ERROR> {
    [^] { 
        yycolumn++;
        return symbol(sym.ERROR);
    }
}

//manejo de errores en caso de no reconocer el caracter
<YYINITIAL,ERROR> [^ \t\n\r\"\-\)\(\+\!\{\}\[\]\|\,\#]* {
    yycolumn += yylength();
    return symbol(sym.ERROR);
}

[^]                              { 
                                    yycolumn++;
                                    return symbol(sym.ERROR);
                                }

//fuentes:
//para esta parte usamos un ejemplo de stackoverflow y una web con informacion 
// https://www.angelfire.com/mac/michelo0/Tema6.html
// https://stackoverflow.com/questions/21837308/jflex-error-unexpected-character-yyinitial
