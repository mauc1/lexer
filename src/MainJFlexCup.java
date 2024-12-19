import jflex.exceptions.*;
import java.io.*;
import ParserLexer.Lexer;
import ParserLexer.sym;
import java_cup.*;
import java_cup.runtime.Symbol;

public class MainJFlexCup {

    //funcion para inicializar el lexer y parser
    public void initLexerParser(String fullPathLexer, String[] strArrParser) throws internal_error, Exception {
        GenerateLexer(fullPathLexer);
        Generateparser(strArrParser);
    }

    //funcion para generar el lexer
    public void GenerateLexer(String ruta) throws IOException, SilentExit {
        String[] strArr = { ruta };
        jflex.Main.generate(strArr);
    }

    //funcion para generar el parser
    public void Generateparser(String[] strArr) throws internal_error, IOException, Exception {
        java_cup.Main.main(strArr);
    }

    //funcion para probar el lexer (contar lexemas)
    public void pruebaLexer(String rutaScanear) throws Exception {
        Reader reader = new BufferedReader(new FileReader(rutaScanear));
        Lexer lex = new Lexer(reader);

        int i = 0;
        Symbol token;

        String outputPath = (System.getProperty("user.dir")) + "\\src\\texto\\resultado.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

        while (true) {
            token = lex.next_token();
            if (token.sym != 0) {
                String tokenInfo = "Código Token: " + token.sym +", Nombre Token: " + sym.terminalNames[token.sym] + ", Valor: " + (token.value == null ? lex.yytext() : token.value.toString()) + ", Linea: " + (token.left + 1) + ", Columna: " + (token.right + 1) + "\n";
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
            i++; //contador de lexemas
            }
        }
}
