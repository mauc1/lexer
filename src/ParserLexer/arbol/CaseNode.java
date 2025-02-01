package ParserLexer.arbol;

public class CaseNode extends ASTNode {
    public String etiqueta;
    public ExpressionNode expresion;

    public CaseNode(String etiqueta, ExpressionNode expresion) {
        this.etiqueta = etiqueta;
        this.expresion = expresion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
