package ParserLexer.intermedio;

import java.util.ArrayList;
import java.util.List;

import ParserLexer.arbol.BlockNode;
import ParserLexer.arbol.CreacionNode;
import ParserLexer.arbol.FunctionNode;
import ParserLexer.arbol.ProgramNode;
import ParserLexer.arbol.StatementNode;
import ParserLexer.arbol.Visitor;

public class TACGenerator implements Visitor {
    private List<ThreeAddressCode> code;
    private int tempCount;

    public TACGenerator() {
        this.code = new ArrayList<>();
        this.tempCount = 0;
    }

    public List<ThreeAddressCode> getCode() {
        return code;
    }

    private String newTemp() {
        return "t" + (tempCount++);
    }

    @Override
    public void visit(ProgramNode node) {
        for (FunctionNode function : node.functions) {
            function.accept(this);
        }
    }

    @Override
    public void visit(FunctionNode node) {
        node.block.accept(this);
    }

    @Override
    public void visit(BlockNode node) {
        for (StatementNode statement : node.statement) {
            statement.accept(this);
        }
    }

   // @Override
    //public void visit(CreacionNode node) {
        // No se necesita generar código para la creación de variables
    //}

    @Override
    public void visit(StatementNode node) {
    }
}