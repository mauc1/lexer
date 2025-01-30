package ParserLexer.arbol;

public class AsignacionNode extends StatementNode {
    public String identificador;
    public StatementNode expresion;

    public AsignacionNode(String identificador, StatementNode expresion) {
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
