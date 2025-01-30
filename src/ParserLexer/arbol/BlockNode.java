package ParserLexer.arbol;

import java.util.List;

public class BlockNode extends ASTNode {
    public List<StatementNode> statement;

    public BlockNode(List<StatementNode> statement) {
        this.statement = statement;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
