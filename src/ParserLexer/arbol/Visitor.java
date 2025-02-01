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
    void visit(IfNode node);
    void visit(ForNode node);
    void visit(WhileNode node);
    void visit(CaseNode node);
    void visit(SwitchNode node);
    void visit(BreakNode node);
    void visit(ReturnNode node);
    void visit(ReadNode node);
    void visit(PrintNode node);
    void visit(LlamadaFuncionNode node);
    void visit(CreacionArregloNode node);
    void visit(ModificarArregloNode node);
    void visit(TipoVariableNode node);
    void visit(VariableNode node);
    void visit(AccesoArregloNode node);
}
