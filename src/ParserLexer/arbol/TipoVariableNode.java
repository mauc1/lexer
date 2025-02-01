package ParserLexer.arbol;

public class TipoVariableNode extends ExpressionNode {
    public String tipo;
    public String identificador;

    public TipoVariableNode(String tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
