import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.*;
import ParserLexer.Lexer;
import ParserLexer.parser;
import java_cup.runtime.Symbol;
import ParserLexer.sym;

public class App {
    //Correr este archivo para ejecutar el programa, 
    //se generaran los archivos y luego de 4 segundos se ejecutara la prueba, 
    //debe proporcionar la ruta del archivo a leer
    
    //main
    public static void main(String[] args) throws Exception {
        
        //PASO 1: Generar el lexer y parser
       GenerarLexerParser(); //comienza a generar el lexer y parser    
        
        //PASO 2: Iniciar el lexer
        // String ruta = "C:\\Users\\mauri\\OneDrive\\Documentos\\GitHub\\lexer\\src\\texto\\codigo.txt";
        // String ruta = "C:\\Users\\julil\\OneDrive\\Documentos\\GitHub\\lexer\\src\\texto\\codigo.txt";
        String ruta = "";
        //espera 4 segundos
        Thread.sleep(5000);
        //PASO 3 : Ejecutar el parser
        ejecutarParser(ruta);

        //adicional: probar el lexer del proyecto 1
        //pruebaLexer(ruta); 
    }

    public static void ejecutarParser(String ruta) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(ruta);
        Reader reader = new InputStreamReader(fileInputStream);
        Lexer lexer = new Lexer(reader);
        parser parser = new parser(lexer);
        parser.parse();

        // Cerrar el flujo de entrada
        reader.close();
        fileInputStream.close();
    }

     //funcion para probar el lexer (contar lexemas)
    public static void pruebaLexer(String rutaScanear) throws Exception {
        Reader reader = new BufferedReader(new FileReader(rutaScanear));
        Lexer lex = new Lexer(reader);

        int i = 0;
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
            i++; //contador de lexemas
            }
    }


    //funcion para generar parser lexer
    public static void GenerarLexerParser() throws Exception {
        String basePath, fullPathLexer, fullPathParser, jlexer, jparser; //vars para las rutas de los archivos

        MainJFlexCup mfjc;
        
        basePath = System.getProperty("user.dir"); //basepath es la ruta del directorio donde se ubica el proyecto

        //archivos .java del parser y el lexer
        jparser = "parser.java";
        jlexer = "Lexer.java";

        mfjc = new MainJFlexCup();

        //eliminar el sym.java por si existe
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\sym.java"));

        //rutas para los archivos lexer y parser
        fullPathLexer = basePath + "\\src\\ParserLexer\\lexer.jflex";
        fullPathParser = basePath + "\\src\\ParserLexer\\parser.cup";

        //se borran los archivos si ya existen
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\" + jlexer));

        // genera el lexer y parser
        String[] strArrParser = { fullPathParser };
        mfjc.initLexerParser(fullPathLexer, strArrParser);

        //mover los archivos generados a la carpeta ParserLexer
        Files.move(Paths.get(basePath + "\\sym.java"), Paths.get(basePath +
                "\\src\\ParserLexer\\sym.java"));
        Files.move(Paths.get(basePath + "\\" + jparser), Paths.get(basePath +
                "\\src\\ParserLexer\\" + jparser));

    }
}