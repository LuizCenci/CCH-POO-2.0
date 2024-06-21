package po23s.App;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Book extends ClienteHttp {//Classe Book
    //Inicializa os atributos do livro
    private String url;
    private JSONArray itens;
    private String titulo;
    private String autores;
    private String editora;
    private boolean disponivelPDF;
    private String valor;

    public Book(String url) {//Cria uma instância de Book a partir de um URL
        this.url = url;
        inicializaDados();
    }

    private void inicializaDados() { //Inicializa os dados do URL
        try {
            String dados = buscaDados(url);//Utiliza o método de ClienteHttp para buscar os dados do URL
            if (dados != null) { //Se não for null, armazena os dados JSON em infos
                JSONObject infos = new JSONObject(dados);
                itens = infos.optJSONArray("items");//Seleciona o campo dos dados onde os itens do livro estão armazenados
            } else {
                System.out.println("Dados da API não foram obtidos.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao inicializar dados do livro: " + e.getMessage());
        }
    }   

    public ArrayList<Book> getInfos() {//Função getInfos
        ArrayList<Book> listaLivros = new ArrayList<>(); //Cria um arraylist do tipo Book para armazenar dados

        if (itens != null) { //Verifica se os itens existem
            for (int i = 0; i < itens.length(); i++) { //Adiciona a arraylist todos os livros encontrados a partir do URL fornecido 
                JSONObject item = itens.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo"); //Busca o campo volumeInfo pela key

                titulo = volumeInfo.optString("title", "Não informado"); //Seleciona o título pela key "title"

                JSONArray listaAutores = volumeInfo.optJSONArray("authors");// Como pode haver mais de um autor, inicializa uma array de autores
                if (listaAutores != null && listaAutores.length() > 0) {
                    StringBuilder autoresBuilder = new StringBuilder();//Cria uma string builder para gerar uma string personalizada
                    for (int j = 0; j < listaAutores.length(); j++) {
                        if (j > 0) {
                            autoresBuilder.append(", ");
                        }
                        autoresBuilder.append(listaAutores.getString(j));
                    }
                    autores = autoresBuilder.toString();
                } else {
                    autores = "Não informado";
                }

                editora = volumeInfo.optString("publisher", "Não informado");//Busca pela editora através da key "publisher"

                JSONObject tipoAcesso = item.optJSONObject("accessInfo");
                disponivelPDF = (tipoAcesso != null && tipoAcesso.has("pdf")); //Tenta encontrar a key "pdf" nos meios de acesso, sendo true caso encontrar

                JSONObject infoValor = item.optJSONObject("saleInfo"); //Cria um objeto para armazenar todas as informações de venda
                valor = getPreco(infoValor); //Chama o método que tratará essas informações

                Book book = new Book(url);
                book.setTitulo(titulo);
                book.setAutores(autores);
                book.setEditora(editora);
                book.setDisponivelPDF(disponivelPDF);
                book.setValor(valor);

                listaLivros.add(book);
            }
        }
        return listaLivros;
    }

    private String getPreco(JSONObject infos) {
        if (infos != null) {
            String disponivel = infos.optString("saleability");//Busca pelo campo "saleability" no objeto infos
            if (disponivel.equals("FOR_SALE")) {//Se estiver a venda procura pelo preço 
                JSONObject listaPreco = infos.getJSONObject("listPrice");
                String valor = listaPreco.optString("amount");
                return valor;//Retorna o valor encontrado
            } else {
                return "0";//Valor defaulta para quando não há preço
            }
        }
        return "0";//Valor default para quando não está a venda
    }

    //getters e setters padrões
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public boolean getDisponivelPDF() {
        return disponivelPDF;
    }

    public void setDisponivelPDF(boolean disponivelPDF) {
        this.disponivelPDF = disponivelPDF;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
