package po23s.App;

public class buscaNulaException extends Exception {//Cria uma excessão para quando o texto do usuário for nulo
    public buscaNulaException(String txt){
        super(txt);
    }
}
