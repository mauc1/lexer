package ParserLexer.arbol;

public class ExpresionAritNode extends ExpressionNode {
    public String operador;
    public ExpressionNode izquierda;
    public ExpressionNode derecha;

    public ExpresionAritNode(String operador, ExpressionNode izquierda, ExpressionNode derecha) {
        this.operador = operador;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String toString() {
        return "ExpresionAritNode(" + operador + ", " + izquierda + ", " + derecha + ")";
    }
}
