package ParserLexer.arbol;

import java.util.List;

public class CreacionArregloNode extends StatementNode {
    public String tipo;
    public String identificador;
    public ExpressionNode tamano;
    public List<ExpressionNode> valores;

    public CreacionArregloNode(String tipo, String identificador, ExpressionNode tamano, List<ExpressionNode> valores) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.tamano = tamano;
        this.valores = null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
