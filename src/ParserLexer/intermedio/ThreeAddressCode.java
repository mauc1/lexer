package ParserLexer.intermedio;

public class ThreeAddressCode {
    public String op;
    public String arg1;
    public String arg2;
    public String result;

    public ThreeAddressCode(String op, String arg1, String arg2, String result) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    @Override
    public String toString() {
        if (arg2 == null) {
            if (op != "=") return result + " = " + op + " " + arg1;
            else return result + " " + op + " " + arg1;
        } else {
            return result + " = " + arg1 + " " + op + " " + arg2;
        }
    }
}
