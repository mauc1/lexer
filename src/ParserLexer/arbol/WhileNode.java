package ParserLexer.arbol;

public class WhileNode extends StatementNode {
    public ExpressionNode condicion;
    public BlockNode bloque;

    public WhileNode(ExpressionNode condicion, BlockNode bloque) {
        this.condicion = condicion;
        this.bloque = bloque;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
