package ParserLexer.arbol;

public class ForNode extends StatementNode{
    public StatementNode creacion;
    public ExpressionNode condicion;
    public ExpressionNode operacion;
    public BlockNode bloque;

    public ForNode(StatementNode creacion, ExpressionNode condicion, ExpressionNode operacion, BlockNode bloque) {
        this.creacion = creacion;
        this.condicion = condicion;
        this.operacion = operacion;
        this.bloque = bloque;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
