package ParserLexer;
public class Simbolo {
    public String id;
    public String ambito;
    public String tipo;
    
    public Simbolo(String pAmbito, String pTipo, String pId){
       this.id = pId;
       this.ambito = pAmbito; 
       this.tipo = pTipo;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public String getAmbito(){
        return this.ambito;
    }

    public String getId(){
        return this.id;
    }
}
