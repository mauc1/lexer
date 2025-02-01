package ParserLexer.arbol;
import java.util.List;

public class SwitchNode extends StatementNode{
    public ExpressionNode condicion;
    public List<CaseNode> casos;
    public ExpressionNode bloqueDefault;

    public SwitchNode(ExpressionNode condicion, List<CaseNode> casos, ExpressionNode bloqueDefault) {
        this.condicion = condicion;
        this.casos = casos;
        this.bloqueDefault = bloqueDefault;
    }

    @Override  
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
