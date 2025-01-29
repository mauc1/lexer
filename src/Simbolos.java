public class Simbolos {
    public String id;
    public String ambito;
    public String tipo;
    
    public Simbolos(String pAmbito, String pTipo, String pId){
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
