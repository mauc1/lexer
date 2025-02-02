package ParserLexer.intermedio;

public class ThreeAddressCode {
    public String op;
    public String arg1;
    public String arg2;
    public String ident;

    public ThreeAddressCode(String op, String arg1, String arg2, String ident) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.ident = ident;
    }

    @Override
    public String toString() {
        if (arg2 == null) {
            //ThreeAddressCode("=", node.expresion.result, null, node.identificador)
                               // = , 1, null, _i_
            if (op != "=") return ident + " " + op + " " + arg1;
            else return ident + " " + op + " " + arg1;
        } else {
            return ident + " = " + arg1 + " " + op + " " + arg2;
        }
    }
}
