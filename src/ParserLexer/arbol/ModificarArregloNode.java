package ParserLexer.arbol;

public class ModificarArregloNode extends StatementNode {
    public String identificador;
    public ExpressionNode posicion;
    public ExpressionNode valor;

    public ModificarArregloNode(String identificador, ExpressionNode posicion, ExpressionNode valor) {
        this.identificador = identificador;
        this.posicion = posicion;
        this.valor = valor;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }   
    
}
