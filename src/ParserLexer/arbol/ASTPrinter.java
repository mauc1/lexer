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

    @Override
    public void visit(IfNode node) {
        printIndent();
        System.out.println("IfNode");
        indent++;
        printIndent();
        System.out.println("Condicion:");
        node.condicion.accept(this);
        printIndent();
        System.out.println("Bloque If:");
        node.bloqueIf.accept(this);
        if (node.bloqueElse != null) {
            printIndent();
            System.out.println("Bloque Else:");
            node.bloqueElse.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(ForNode node) {
        printIndent();
        System.out.println("ForNode");
        indent++;
        printIndent();
        System.out.println("Creacion:");
        node.creacion.accept(this);
        printIndent();
        System.out.println("Condicion:");
        node.condicion.accept(this);
        printIndent();
        System.out.println("Operacion:");
        node.operacion.accept(this);
        printIndent();
        System.out.println("Bloque:");
        node.bloque.accept(this);
        indent--;
    }

    @Override
    public void visit(WhileNode node) {
        printIndent();
        System.out.println("WhileNode");
        indent++;
        printIndent();
        System.out.println("Condicion:");
        node.condicion.accept(this);
        printIndent();
        System.out.println("Bloque:");
        node.bloque.accept(this);
        indent--;
    }

    @Override
    public void visit(SwitchNode node) {
        printIndent();
        System.out.println("SwitchNode");
        indent++;
        printIndent();
        System.out.println("Expresion:");
        node.condicion.accept(this);
        for (CaseNode caso : node.casos) {
            printIndent();
            System.out.println("Caso:");
            caso.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(ReturnNode node) {
        printIndent();
        System.out.println("ReturnNode");
        indent++;
        node.expresion.accept(this);
        indent--;
    }

    @Override
    public void visit(BreakNode node) {
        printIndent();
        System.out.println("BreakNode");
    }

    @Override
    public void visit(ReadNode node) {
        printIndent();
        System.out.println("ReadNode: identificador=" + node.id);
    }

    @Override
    public void visit(PrintNode node) {
        printIndent();
        System.out.println("PrintNode");
        indent++;
        node.expresion.accept(this);
        indent--;
    }

    @Override
    public void visit(LlamadaFuncionNode node) {
        printIndent();
        System.out.println("LlamadaFuncion: nombre=" + node.nombre);
        indent++;
        for (ExpressionNode argumento : node.argumentos) {
            argumento.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(CreacionArregloNode node) {
        printIndent();
        System.out.println("CreacionArregloNode: tipo=" + node.tipo + ", identificador=" + node.identificador);
        indent++;
        //for (ExpressionNode valores : node.valores) {
        //    valores.accept(this);
        //}
        indent--;
    }

    @Override
    public void visit(ModificarArregloNode node) {
        printIndent();
        System.out.println("ModificarArregloNode: identificador=" + node.identificador);
        indent++;
        printIndent();
        System.out.println("Posicion:");
        node.posicion.accept(this);
        printIndent();
        System.out.println("Valor:");
        node.valor.accept(this);
        indent--;
    }

    @Override
    public void visit(TipoVariableNode node) {
        printIndent();
        System.out.println("TipoVariableNode: tipo=" + node.tipo + ", identificador=" + node.identificador);
    }

    @Override
    public void visit(VariableNode node) {
        printIndent();
        System.out.println("VariableNode: identificador=" + node.identificador);
    }

    @Override
    public void visit(AccesoArregloNode node) {
        printIndent();
        System.out.println("AccesoArregloNode: identificador=" + node.identificador);
        indent++;
        printIndent();
        System.out.println("Posicion:");
        node.index.accept(this);
        indent--;
    }

    @Override
    public void visit(CaseNode node) {
    }


}