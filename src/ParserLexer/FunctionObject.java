package ParserLexer;


public class FunctionObject {
    private String idFuncion; //Nombre de la funcion
    private TipoDato tipoReturn;
    private Boolean hayRetorno;
    private TipoDato[] tParams;
    
    public FunctionObject(String pIdFuncion, TipoDato pTipoReturn, TipoDato[] pTParams, Boolean pHayRetorno){
        this.idFuncion = pIdFuncion;
        this.tipoReturn = pTipoReturn;
        this.tParams = pTParams;
        this.hayRetorno = pHayRetorno;
    }
    
    
    public void setIdFuncion(String pId){
        this.idFuncion = pId;
    }
    
    public void setTipoReturn(TipoDato pTipo){
        this.tipoReturn = pTipo;
    }
    
    public void setTParams(TipoDato[] pTParams){
        this.tParams = pTParams;
    }
    
    public void setTHayRetorno(Boolean pHayRetorno){
        this.hayRetorno = pHayRetorno;
    }

    public String getIdFuncion() {
        return this.idFuncion;
    }

    public TipoDato getTipoReturn() {
        return this.tipoReturn;
    }

    public TipoDato[] getTParams() {
        return this.tParams;
    }

    public Boolean getTHayRetorno() {
        return this.hayRetorno;
    }
    
    public void insertarTipoParametro(TipoDato tipo) {
        //Crear un nuevo arreglo de los nuevos parametros
        TipoDato[] tempParams = new TipoDato[tParams.length + 1];

        //Se copian elementos existentes
        int i = 0;
        for (TipoDato tipoExistente: tParams) {
            tempParams[i] = tipoExistente;
            i++;
        }
        //Se inserta el nuevo tipo
        tempParams[i] = tipo;
        this.tParams = tempParams;
    }


}

