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
        System.out.println("StatementNode"); //esto nunca se imprime
    }
    
    @Override
    public void visit(CreacionNode node) {
        printIndent();
        System.out.println("StatementNode");
        printIndent(); 
        printIndent();
        System.out.println("CreacionNode: tipo=" + node.tipo + ", identificador=" + node.identificador);
    }

    @Override
    public void visit(AsignacionNode node) {
        printIndent();
        System.out.println("AsignacionNode: identificador=" + node.identificador);
        indent++;
        node.expresion.accept(this);
        indent--;
    }

    @Override
    public void visit(ExpressionNode node) {
        printIndent();
        System.out.println("ExpressionNode: " + node.result);
    }

    @Override
    public void visit(CreacionAsignacionNode node) {
        printIndent();
        System.out.println("CreacionAsignacionNode: tipo=" + node.tipo + ", identificador=" + node.identificador);
        indent++;
        node.expresion.accept(this);
        indent--;
    }

    @Override
    public void visit(LiteralNode node) {
    }

    @Override
    public void visit(ExpresionAritNode node) {
        printIndent();
        System.out.println("ExpresionAritNode: operador=" + node.operador);
        indent++;
        printIndent();
        System.out.println("izquierda: " + node.izquierda);
        printIndent();
        System.out.println("derecha: " + node.derecha);
        indent--;
    }

    @Override
    public void visit(ExpresionLogicaNode node) {
        printIndent();
        System.out.println("ExpresionLogicaNode: operador=" + node.operador);
        indent++;
        node.izquierda.accept(this);
        node.derecha.accept(this);
        indent--;
    }

    @Override
    public void visit(ExpresionRelaNode node) {
        printIndent();
        System.out.println("ExpresionRelaNode: operador=" + node.operador);
        indent++;
        printIndent();
        System.out.println("izquierda: " + node.izquierda);
        printIndent();
        System.out.println("derecha: " + node.derecha);
        indent--;
    }

    @Override
    public void visit(ExpresionUnariaNode node) {
        printIndent();
        System.out.println("ExpresionUnariaNode: identificador=" + node.identificador + ", operador=" + node.operador);
    }

}