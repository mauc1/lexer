package ParserLexer.arbol;

public class LiteralNode extends ExpressionNode {
    public Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}