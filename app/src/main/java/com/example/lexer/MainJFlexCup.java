package com.example.lexer;
import jflex.exceptions.*;
import java.io.*;
import ParserLexer.Lexer;
import ParserLexer.sym;
import java_cup.*;
import java_cup.runtime.Symbol;

public class MainJFlexCup {

    public void initLexerParser(String fullPathLexer, String[] strArrParser) {
        GenerateLexer(fullPathLexer);
        Generateparser(strArrParser);
    }

    public void GenerateLexer(String ruta) throws IOException, SilentExit {
        String[] strArr = { ruta };
        jflex.Main.generate(strArr);
    }

    public void Generateparser(String[] strArr) throws internal_error, IOException, Exception {
        java_cup.Main.main(strArr);
    }

    public void pruebaLexer(String rutaScanear) throws Exception {
        Reader reader = new BufferedReader(new FileReader(rutaScanear));
        Lexer lex = new Lexer(reader);

        int i = 0;
        Symbol token;

        String outputPath = (System.getProperty("user.dir")) + "\\src\\Prueba\\resultado.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

    while (true) {
        token = lexer.next_token();
        if (token.sym != 0) {
            String tokenInfo = "Codigo Token: " + token.sym +", Nombre Token: " + sym.terminalNames[token.sym] + ", Valor: " + (token.value == null ? lex.yytext() : token.value.toString()) + ", Linea: " + (token.left + 1) + ", Columna: " + (token.right + 1) + "\n";
            System.out.println(tokenInfo);
            writer.write(tokenInfo);
            writer.write("\n");
        } else {
            String cantLexemas = "Cantidad de lexemas encontrados: " + i;
            System.out.println(cantLexemas);
            writer.write(cantLexemas);
            writer.newLine();
            writer.close();
            return;
        }
    }
    public static void main(String[] args) {
        MainJFlexCup main = new MainJFlexCup();

        try {
            // Rutas de los archivos
            String rutaJFlex = "src/ParserLexer/lexer.jflex";
            String[] rutaCUP = { "src/ParserLexer/parser.cup" };
            String rutaEntrada = "src/ParserLexer/archivoEntrada.txt";

            // Generar lexer y parser
            main.generateLexer(rutaJFlex);
            main.generateParser(rutaCUP);

            // Ejecutar prueba del lexer
            main.pruebaLexer(rutaEntrada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
