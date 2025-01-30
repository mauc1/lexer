package ParserLexer.arbol;

public abstract class ExpressionNode extends ASTNode {
    public String result; // Para almacenar el resultado de la expresión en el código intermedio

    @Override
    public abstract void accept(Visitor visitor);
}
