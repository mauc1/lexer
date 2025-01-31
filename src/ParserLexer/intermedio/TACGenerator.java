package ParserLexer.intermedio;

import java.util.ArrayList;
import java.util.List;

import ParserLexer.arbol.AsignacionNode;
import ParserLexer.arbol.BlockNode;
import ParserLexer.arbol.CreacionAsignacionNode;
import ParserLexer.arbol.CreacionNode;
import ParserLexer.arbol.ExpresionAritNode;
import ParserLexer.arbol.ExpressionNode;
import ParserLexer.arbol.FunctionNode;
import ParserLexer.arbol.LiteralNode;
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
       /*  for (StatementNode statement : node.statement) {
            statement.accept(this);
        }*/
    }

   // @Override
    //public void visit(CreacionNode node) {
        // No se necesita generar código para la creación de variables
    //}

    @Override
    public void visit(StatementNode node) {
    }

    @Override
    public void visit(CreacionNode node) {
        //no se necesita generar código para la creación de variables sin asignación
    }

    @Override
    public void visit(AsignacionNode node) {
        node.expresion.accept(this);
        code.add(new ThreeAddressCode("=", node.expresion.result, null, node.identificador));
    }

    @Override
    public void visit(ExpressionNode node) {
        // Implementar la generación de código para las expresiones
    }

    @Override
    public void visit(CreacionAsignacionNode node) {
        //tree address code for a variable creation with assignment
        node.expresion.accept(this);
        code.add(new ThreeAddressCode("=", node.expresion.result, null, node.identificador));
    }

    @Override
    public void visit(LiteralNode node) {
    }

       @Override
    public void visit(ExpresionAritNode node) {

    }
}