package ParserLexer.arbol;

public class IfNode extends StatementNode{
    public ExpressionNode condicion;
    public BlockNode bloqueIf;
    public BlockNode bloqueElse;

    public IfNode(ExpressionNode condicion, BlockNode bloqueIf, BlockNode bloqueElse) {
        this.condicion = condicion;
        this.bloqueIf = bloqueIf;
        this.bloqueElse = bloqueElse;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
