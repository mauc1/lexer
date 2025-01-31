package ParserLexer.arbol;

public class ExpresionRelaNode extends ExpressionNode {
    public String operador;
    public ExpressionNode izquierda;
    public ExpressionNode derecha;

    public ExpresionRelaNode(String operador, ExpressionNode izquierda, ExpressionNode derecha) {
        this.operador = operador;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
