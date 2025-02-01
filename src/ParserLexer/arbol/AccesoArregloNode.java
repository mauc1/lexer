package ParserLexer.arbol;

public class AccesoArregloNode extends ExpressionNode {
    public String identificador;
    public ExpressionNode index;

    public AccesoArregloNode(String identificador, ExpressionNode index) {
        this.identificador = identificador;
        this.index = index;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
