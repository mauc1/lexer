package ParserLexer.arbol;

public class ReadNode extends StatementNode{
    public String id;

    public ReadNode(String id) {
        this.id = id;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
