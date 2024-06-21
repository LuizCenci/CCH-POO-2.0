
package Frames;
import javax.swing.DefaultListModel;

import po23s.App.Book;

public class descricaoLivros extends javax.swing.JFrame {

    DefaultListModel<String> descLivro = new DefaultListModel<>();

    String txtTitulo;
    String titulo;
    String autores;
    String editora;
    String disponivelPDF;
    String valor;


    public descricaoLivros(Book livroSelecionado) { //A partir de um livro inicializa um construtor
        //Armazena as principais informações abaixo
        this.txtTitulo = livroSelecionado.getTitulo();
        this.titulo = "Título: "+livroSelecionado.getTitulo();
        this.autores = "Autor: "+livroSelecionado.getAutores();
        this.editora = "Editora: "+livroSelecionado.getEditora();
        if (livroSelecionado.getDisponivelPDF() == true){ 
            this.disponivelPDF = "Disponível para PDF"; 
        }else{
            this.disponivelPDF = "Indisponível para PDF";
        }
        if (livroSelecionado.getValor().equals("0")){
            this.valor = "Indisponível para venda";
        }else{
            this.valor = "Valor: R$ "+ livroSelecionado.getValor();
        }
        initComponents();
        lstDesc.setModel(descLivro); //Define um modelo para a lista que exibirá as informações
    }

    @SuppressWarnings("")                        
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInfosLivro = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDesc = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtInfosLivro.setText("Informações sobre o livro: "+txtTitulo);
        txtInfosLivro.setWrapStyleWord(true); 
        txtInfosLivro.setLineWrap(true);
        txtInfosLivro.setEditable(false); //Não permite que o usuário escreva sobre o textArea
        jScrollPane1.setViewportView(txtInfosLivro);

        //Adiciona os itens instanciados no construtor na lista para exibi-los
        descLivro.addElement(titulo);
        descLivro.addElement(autores);
        descLivro.addElement(editora);
        descLivro.addElement(disponivelPDF);
        descLivro.addElement(valor);
        lstDesc.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstDesc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }                       

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(descricaoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(descricaoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(descricaoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(descricaoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

               }
        });
    }                   
    private javax.swing.JList<String> lstDesc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtInfosLivro;
                  
}
