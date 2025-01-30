package ParserLexer.arbol;

public class ASTPrinter implements Visitor {
    private int indent = 0;

    private void printIndent() {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }

    @Override
    public void visit(ProgramNode node) {
        printIndent();
        System.out.println("ProgramNode");
        indent++;
        for (FunctionNode function : node.functions) {
            function.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(FunctionNode node) {
        printIndent();
        System.out.println("FunctionNode: " + node.name);
        indent++;
        node.block.accept(this);
        indent--;
    }

    @Override
    public void visit(BlockNode node) {
        printIndent();
        System.out.println("BlockNode");
        indent++;
        //if (node.statement != null) {
            for (StatementNode statement : node.statement) {
                if (statement != null) {
                    statement.accept(this);
                }
            }
        //}
        indent--;
    }

    @Override
    public void visit(StatementNode node) {
        printIndent();
        System.out.println("StatementNode");
    }
    
    @Override
    public void visit(CreacionNode node) {
        printIndent();
        System.out.println("CreacionNode: tipo=" + node.tipo + ", identificador=" + node.identificador);
    }

    @Override
    public void visit(AsignacionNode node) {
        printIndent();
        System.out.println("AsignacionNode: identificador=" + node.identificador);
    }

}