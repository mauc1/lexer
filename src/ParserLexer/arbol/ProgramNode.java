package ParserLexer.arbol;

import java.util.List;

public class ProgramNode extends ASTNode {
    public List<FunctionNode> functions;

    public ProgramNode(List<FunctionNode> functions) {
        this.functions = functions;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}