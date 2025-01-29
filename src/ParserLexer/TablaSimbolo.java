package ParserLexer;
import java.util.ArrayList;

public class TablaSimbolo {

    public ArrayList<Simbolo> tablaSimbolo;
    public int indice;
    public ArrayList<String> pila = new ArrayList<String>();
    
    
    public TablaSimbolo(){
        this.tablaSimbolo = new ArrayList<Simbolo>();
        this.indice = 0;
    }
    
    public ArrayList<Simbolo> getTablaSimbolos(){
        return this.tablaSimbolo;
    }
    
    public void setTablaSimbolos(ArrayList<Simbolo> pTablaSimbolo){
          this.tablaSimbolo = pTablaSimbolo;
        
    }
    
    public void incrementarIndice(int pIndiceSuma){
        this.indice += pIndiceSuma;
        
    }
}
