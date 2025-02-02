package ParserLexer.intermedio;

import java.util.ArrayList;
import java.util.List;

import ParserLexer.arbol.AsignacionNode;
import ParserLexer.arbol.BlockNode;
import ParserLexer.arbol.BreakNode;
import ParserLexer.arbol.CaseNode;
import ParserLexer.arbol.CreacionAsignacionNode;
import ParserLexer.arbol.CreacionNode;
import ParserLexer.arbol.ExpresionAritNode;
import ParserLexer.arbol.ExpresionLogicaNode;
import ParserLexer.arbol.ExpresionRelaNode;
import ParserLexer.arbol.ExpresionUnariaNode;
import ParserLexer.arbol.ExpressionNode;
import ParserLexer.arbol.FunctionNode;
import ParserLexer.arbol.LiteralNode;
import ParserLexer.arbol.LlamadaFuncionNode;
import ParserLexer.arbol.IfNode;
import ParserLexer.arbol.ForNode;
import ParserLexer.arbol.WhileNode;
import javafx.scene.control.Label;
import ParserLexer.arbol.SwitchNode;
import ParserLexer.arbol.TipoVariableNode;
import ParserLexer.arbol.VariableNode;
import ParserLexer.arbol.ModificarArregloNode;
import ParserLexer.arbol.PrintNode;
import ParserLexer.arbol.CreacionArregloNode;
import ParserLexer.arbol.AccesoArregloNode;
import ParserLexer.arbol.ProgramNode;
import ParserLexer.arbol.ReadNode;
import ParserLexer.arbol.ReturnNode;
import ParserLexer.arbol.StatementNode;
import ParserLexer.arbol.Visitor;

public class TACGenerator implements Visitor {
    private List<ThreeAddressCode> code;
    private int tempCount;
    private int labelCount;
    private int ifcount;

    public TACGenerator() {
        this.code = new ArrayList<>();
        this.tempCount = 0;
        this.labelCount = 0;
        this.ifcount = 0;
    }

    public List<ThreeAddressCode> getCode() {
        return code;
    }

    private String newTemp() {
        return "t" + (tempCount++);
    }

    private String newLabel() {
        return "L" + (labelCount++);
    }

    private String newIf() {
        return "if_" + (ifcount++);
    }

    @Override
    public void visit(ProgramNode node) {
        for (FunctionNode function : node.functions) {
            function.accept(this);
        }
    }

    @Override
    public void visit(FunctionNode node) {
        code.add(new ThreeAddressCode("label", null, null, node.name));
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
        String nombre = newTemp();
        code.add(new ThreeAddressCode("=", node.expresion.result, null, nombre));//node.identificador));
    }

    @Override
    public void visit(LiteralNode node) {
        // Implementar la generación de código para los literales
        node.result = node.value.toString();
    }

       @Override
    public void visit(ExpresionAritNode node) {
        node.izquierda.accept(this);
        node.derecha.accept(this);
        String temp = newTemp();
        //generar la instruccion
        code.add(new ThreeAddressCode(node.operador, node.izquierda.result, node.derecha.result, temp));
        node.result = temp;
    }

    @Override
    public void visit(ExpresionUnariaNode node) {
        String temp = newTemp();
        code.add(new ThreeAddressCode(node.operador, node.result, null, temp));
        node.result = temp;
    }

    @Override
    public void visit(IfNode node) {
        String IfLabel = newIf();
        String labelElse = newLabel();
        String labelEnd = newLabel();


        code.add(new ThreeAddressCode("label", " ", null, IfLabel));
        //codigo de la condicion
        node.condicion.accept(this);
// 2) ifFalse <cond> -> if_else (o vas ajustando la lógica a tu gusto)
code.add(new ThreeAddressCode("ifFalse", node.condicion.result, null, IfLabel + "_else"));

// 3) Generas el bloque 'if'
code.add(new ThreeAddressCode("goto", null, null, IfLabel + "_bloque"));

// 4) Etiqueta de bloque if
code.add(new ThreeAddressCode("label", null, null, IfLabel + "_bloque"));
node.bloqueIf.accept(this);

// 5) Cuando acaba el if, saltas al final
code.add(new ThreeAddressCode("goto", null, null, IfLabel + "_end"));

// 6) Etiqueta else
code.add(new ThreeAddressCode("label", null, null, IfLabel + "_else"));
if (node.bloqueElse != null) {
    node.bloqueElse.accept(this);
}

// 7) Fin del if
code.add(new ThreeAddressCode("label", null, null, IfLabel + "_end"));
    }

    @Override
    public void visit(ForNode node) {
        if (node.creacion != null) {
            node.creacion.accept(this);
        }
        String labelLoop = newLabel();
        String labelEnd = newLabel();

        // Etiqueta del inicio del bucle
        code.add(new ThreeAddressCode("Label", " ", null, labelLoop));

        // Condición del bucle
        if (node.condicion != null) {
            node.condicion.accept(this);
            code.add(new ThreeAddressCode("ifFalse", node.condicion.result, null, labelEnd));
        }

        // Cuerpo del bucle
        node.bloque.accept(this);
        //operacion
        if (node.operacion != null) {
            node.operacion.accept(this);
        }

        code.add(new ThreeAddressCode("gotp", " ", null, labelLoop));
        
        // Etiqueta del final del bucle
        code.add(new ThreeAddressCode("Label", " ", null, labelEnd));
    }

    @Override
    public void visit(WhileNode node) {
        String labelLoop = newLabel();
        String labelEnd = newLabel();

        // Etiqueta del inicio del bucle
        code.add(new ThreeAddressCode("Label", " ", null, labelLoop));

        // Condición del bucle
        node.condicion.accept(this);
        code.add(new ThreeAddressCode("ifFalse", node.condicion.result, null, labelEnd));

        // Cuerpo del bucle
        node.bloque.accept(this);

        code.add(new ThreeAddressCode("goto", " ", null, labelLoop));

        // Etiqueta del final del bucle
        code.add(new ThreeAddressCode("Label", " ", null, labelEnd));
    }

    @Override
    public void visit(SwitchNode node) {
    }

    @Override
    public void visit(LlamadaFuncionNode node) {
        // argumentos 
        List<String> args = new ArrayList<>();
        for (ExpressionNode arg : node.argumentos) {
            arg.accept(this);
            args.add(arg.result);
        }

        //generar la instruccion para pasar paramentros
        for (String arg : args) {
            code.add(new ThreeAddressCode("param", arg, null, null));
        }

        // temporal almacenar el resultado de la llamada
        String temp = newTemp();
        code.add(new ThreeAddressCode("call", node.nombre, String.valueOf(args.size()), temp));
        node.result = temp;
    }

    @Override
    public void visit(CreacionArregloNode node) {
    }

    @Override
    public void visit(ModificarArregloNode node) {
    }

    @Override
    public void visit(ExpresionLogicaNode node) {
<<<<<<< Updated upstream
=======
        node.izquierda.accept(this);
        node.derecha.accept(this);
        String temp = newTemp();

        //generar la instruccion
        code.add(new ThreeAddressCode(node.operador, node.izquierda.result, node.derecha.result, temp));
        node.result = temp;
>>>>>>> Stashed changes
    }

    @Override
    public void visit(ExpresionRelaNode node) {
<<<<<<< Updated upstream
=======
        node.izquierda.accept(this);
        node.derecha.accept(this);
        String temp = newTemp();

        //generar la instruccion
        code.add(new ThreeAddressCode(node.operador, node.izquierda.result, node.derecha.result, temp));
        node.result = temp;
>>>>>>> Stashed changes
    }

    @Override
    public void visit(CaseNode node) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    }

    @Override
    public void visit(BreakNode node) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    }

    @Override
    public void visit(ReturnNode node) {
<<<<<<< Updated upstream
=======
        if (node.expresion != null) {
            node.expresion.accept(this);
            code.add(new ThreeAddressCode("return", node.expresion.result, null, null));
        } else {
            code.add(new ThreeAddressCode("return", null, null, null));
        }
>>>>>>> Stashed changes
    }

    @Override
    public void visit(ReadNode node) {
<<<<<<< Updated upstream
=======
        code.add(new ThreeAddressCode("read", null, null, node.id));

>>>>>>> Stashed changes
    }

    @Override
    public void visit(PrintNode node) {
<<<<<<< Updated upstream
=======
        node.expresion.accept(this);
        code.add(new ThreeAddressCode("print", node.expresion.result, null, null));
>>>>>>> Stashed changes
    }

    @Override
    public void visit(TipoVariableNode node) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    }

    @Override
    public void visit(VariableNode node) {
<<<<<<< Updated upstream
=======
        node.result = node.identificador;

>>>>>>> Stashed changes
    }

    @Override
    public void visit(AccesoArregloNode node) {
<<<<<<< Updated upstream
    }

=======
        node.index.accept(this);
        String temp = newTemp();

        //generar la instruccion
        code.add(new ThreeAddressCode("array_acces", node.identificador, node.index.result, temp));
        node.result = temp;
    }
>>>>>>> Stashed changes
}