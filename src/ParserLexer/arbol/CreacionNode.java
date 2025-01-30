package ParserLexer.arbol;

public class CreacionNode extends StatementNode {
    public String tipo;
    public String identificador;

    public CreacionNode(String tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }   
}
