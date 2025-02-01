package ParserLexer.arbol;

public class PrintNode extends StatementNode{
    public ExpressionNode expresion;

    public PrintNode(ExpressionNode expresion) {
        this.expresion = expresion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
