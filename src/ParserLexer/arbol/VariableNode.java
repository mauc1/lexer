package ParserLexer.arbol;

public class VariableNode extends ExpressionNode {
    public String identificador;

    public VariableNode(String identificador) {
        this.identificador = identificador;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
