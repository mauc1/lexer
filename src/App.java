import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.*;
import ParserLexer.Lexer;
import ParserLexer.parser;

public class App {
    //Correr este archivo para ejecutar el programa, 
    //se generaran los archivos y luego de 4 segundos se ejecutara la prueba, 
    //debe proporcionar la ruta del archivo a leer
    
    //main
    public static void main(String[] args) throws Exception {
        
        GenerarLexerParser(); //comienza a generar el lexer y parser    
        
        Thread.sleep(4000); //timeout para que pueda generar los archivos
        
       // GenerarPrueba(); //en resultado.txt estarán los resultados

        //segundo proyecto
        //tabla de simbolos
        GenerarTablaSimbolos();
    }

    //funcion para generar la tabla de simbolos
    public static void GenerarTablaSimbolos() throws Exception {
        MainJFlexCup mfjc;
        Reader reader;
        parser parser;
        String path, basePath, fullPathLexer, fullPathParser, jlexer, jparser;

        basePath = System.getProperty("user.dir"); //basepath es la ruta del directorio donde se ubica el proyecto

        //archivos .java del parser y el lexer
        jparser = "parser.java";
        jlexer = "Lexer.java";

        mfjc = new MainJFlexCup();

        //pedimos ruta del archivo a leer
        System.out.println("Ingrese la ruta del archivo a leer: ");
        path = System.console().readLine();

        //rutas para los archivos lexer y parser
        fullPathLexer = basePath + "\\src\\ParserLexer\\lexerCup.jflex";
        fullPathParser = basePath + "\\src\\ParserLexer\\parser.cup";

        //se borran los archivos si ya existen
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\" + jlexer));

        // genera el lexer y parser
        String[] strArrParser = { fullPathParser };
        mfjc.initLexerParser(fullPathLexer, strArrParser);

        //se lee el archivo
        FileInputStream fileInputStream = new FileInputStream(path);
        reader = new InputStreamReader(fileInputStream);
        parser = new parser(new ParserLexer.Lexer(reader));

        //se ejecuta el parser
        parser.parse();
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

    //ejecutar la prueba del codigo
    public static void GenerarPrueba() throws Exception {
        MainJFlexCup mfjc; 

        //pedimos ruta del archivo a leer
        System.out.println("Ingrese la ruta del archivo a leer: ");
        String path = System.console().readLine();

        mfjc = new MainJFlexCup();
        
        mfjc.pruebaLexer(path);
    }
}