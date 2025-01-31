package ParserLexer.arbol;

public class ExpresionUnariaNode extends ExpressionNode {
    public String identificador;
    public String operador;

    public ExpresionUnariaNode(String identificador, String operador) {
        this.identificador = identificador;
        this.operador = operador;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
