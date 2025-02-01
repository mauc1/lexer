package ParserLexer.arbol;

public class BreakNode extends StatementNode{
    public BreakNode() {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
