import jflex.exceptions.*;
import java.io.*;
import ParserLexer.Lexer;
import ParserLexer.sym;
import java_cup.*;
import java_cup.runtime.Symbol;

public class MainJFlexCup {

    /**
    Iniciar el analizador lexico
    * @param fullPathLexer ruta del archivo .jflex para generar el LEXER
    * @param strArrParser contiene los archivos necesarios para generar el parser
    * @throws internal_error si hay un error
    * @throws exception errores generales 
    */
    public void initLexerParser(String fullPathLexer, String[] strArrParser) throws internal_error, Exception {
        GenerateLexer(fullPathLexer);
        Generateparser(strArrParser);
    }

    /**
     * genera el analizador lexico
     * @param ruta tiene la ruta .jflex
     * @throws IOException error al acceder al archivo
     * @throws silentExit Si JFlex encuentra un problema grave y detiene la ejecución.
     */
    public void GenerateLexer(String ruta) throws IOException, SilentExit {
        String[] strArr = { ruta };
        jflex.Main.generate(strArr);
    }

    /**
     * Genera el analizador sintactico utilizando CUP
     * @param strArr arreglo de strings con lo necesario para CUP
     * @throws internal_error agarra errores internos
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws Exception para errores generales
     */
    public void Generateparser(String[] strArr) throws internal_error, IOException, Exception {
        java_cup.Main.main(strArr);
    }

    /**
     * bucle para reconocer los lexemas que hay en el archivo de entrada, se saca su informacion de , lineo, columna y valor
     * @param rutaScanner ruta de donde se analiza el archivo
     * @throws Exception si ocurre algun error en la ejecucion
     */
    public void pruebaLexer(String rutaScanner) throws Exception {
        Reader reader = new BufferedReader(new FileReader(rutaScanner));
        Lexer lex = new Lexer(reader);

        int i = 0;      //contador de tokens
        Symbol token;

        String outputPath = (System.getProperty("user.dir")) + "\\src\\texto\\resultado.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

        while (true) {
            token = lex.next_token();
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
            i++; //incremento del contador de Tokenss
            }
        }
}
