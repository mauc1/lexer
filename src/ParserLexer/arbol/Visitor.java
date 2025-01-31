package ParserLexer.arbol;

public interface Visitor {
    void visit(ProgramNode node);
    void visit(FunctionNode node);
    void visit(BlockNode node);
    void visit(StatementNode node);
    void visit(CreacionNode node);
    void visit(AsignacionNode node);
    void visit(ExpressionNode node);
    void visit(CreacionAsignacionNode node);
    void visit(LiteralNode node);
    void visit(ExpresionAritNode node);
    void visit(ExpresionLogicaNode node);
    void visit(ExpresionRelaNode node);
    void visit(ExpresionUnariaNode node);
}
