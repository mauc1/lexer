package ParserLexer.arbol;

public interface Visitor {
    void visit(ProgramNode node);
    void visit(FunctionNode node);
    void visit(BlockNode node);
    void visit(StatementNode node);
}
