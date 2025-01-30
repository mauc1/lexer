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
        for (StatementNode stmt : statement) {
            if (stmt == null) {
                System.out.println("ADVERTENCIA: Hay un statement NULL en BlockNode.");
            } else {
                
                stmt.accept(visitor);
            }
            
        }
    }
    
}
