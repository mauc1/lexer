package ParserLexer.arbol;

public class CreacionAsignacionNode extends StatementNode {
    public String tipo;
    public String identificador;
    public ExpressionNode expresion;

    public CreacionAsignacionNode(String tipo, String identificador, ExpressionNode expresion) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
