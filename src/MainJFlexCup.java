import jflex.exceptions.*;
import java.io.*;
import ParserLexer.Lexer;
import java_cup.*;
import java_cup.runtime.Symbol;
import ParserLexer.sym;

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
}
