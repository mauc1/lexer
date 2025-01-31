package ParserLexer.arbol;

public class NegacionNode extends ExpressionNode {
    public ExpressionNode expresion;

    public NegacionNode(ExpressionNode expresion) {
        this.expresion = expresion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
