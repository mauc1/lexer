package ParserLexer.arbol;

public class FunctionNode extends ASTNode {
    public String name;
    public BlockNode block;

    public FunctionNode(String name, BlockNode block) {
        this.name = name;
        this.block = block;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}