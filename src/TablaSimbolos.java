import java.util.ArrayList;

public class TablaSimbolos {
    
    public ArrayList<Simbolos> tablaSimbolo;
    public int indice;
    public ArrayList<String> pila = new ArrayList<String>();
    
    
    public TablaSimbolos(){
        this.tablaSimbolo = new ArrayList<Simbolos>();
        this.indice = 0;
    }
    
    public ArrayList<Simbolos> getTablaSimbolos(){
        return this.tablaSimbolo;
    }
    
    public void setTablaSimbolos(ArrayList<Simbolos> pTablaSimbolo){
          this.tablaSimbolo = pTablaSimbolo;
    }
    
    public void incrementarIndice(int pIndiceSuma){
        this.indice += pIndiceSuma;
    }
}
