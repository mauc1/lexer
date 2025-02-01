package ParserLexer.arbol;
import java.util.List;

public class LlamadaFuncionNode extends ExpressionNode {
    public String nombre;
    public List<ExpressionNode> argumentos;

    public LlamadaFuncionNode(String nombre, List<ExpressionNode> argumentos) {
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
