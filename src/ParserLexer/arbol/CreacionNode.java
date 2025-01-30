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
        //System.out.println("Visitando CreacionNode: tipo=" + tipo + ", identificador=" + identificador);
        visitor.visit(this);
    }   
}
