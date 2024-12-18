import java.nio.file.*;

public class App {
    //main
    public static void main(String[] args) throws Exception {
        //descomentar según lo que se requiera
        //ya sea generar o probar
        
       // GenerarLexerParser();
        
        GenerarPrueba(); //en resultado.txt estarán los resultados
    }
    //funcion para generar parser lexer
    public static void GenerarLexerParser() throws Exception {
        //variables basicas que tendran las rutas de los archivos
        String basePath, fullPathLexer, fullPathParser, jlexer, jparser;

        //instancia de la clase MainJFlexCup para construir el lexer
        MainJFlexCup mfjc;
        
        //basepath es la ruta del directorio donde se ubica el proyecto
        basePath = System.getProperty("user.dir");

        //archivos .java del parser y el lexer
        jparser = "parser.java";
        jlexer = "Lexer.java";

        mfjc = new MainJFlexCup();

        //eliminar el sym.java por si existe
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\sym.java"));

        //rutas para los archivos lexer y parser
        fullPathLexer = basePath + "\\src\\ParserLexer\\lexerCup.jflex";
        fullPathParser = basePath + "\\src\\ParserLexer\\parser.cup";

        //se borran los archivos si ya existen
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath + "\\src\\ParserLexer\\" + jlexer));

        // Se genera el lexer y parser
        String[] strArrParser = { fullPathParser };
        mfjc.initLexerParser(fullPathLexer, strArrParser);

        // Se generan el sym.java el parser.java y el lexer.java en la carpeta
        // ParserLexer
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