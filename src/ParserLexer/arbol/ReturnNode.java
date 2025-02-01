package ParserLexer.arbol;

public class ReturnNode extends StatementNode{
    public ExpressionNode expresion;

    public ReturnNode(ExpressionNode expresion) {
        this.expresion = expresion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
