package Frames;

import po23s.App.utils;
import po23s.App.Book;
import po23s.App.buscaNulaException;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

public class buscaTitulos extends javax.swing.JFrame {
    DefaultListModel<Book> modelLstLivros = new DefaultListModel<>(); //Declara uma listModel para armazenar o resultado da busca por títulos
    private Book livro; //Cria uma instância de um livro para buscar por URL

    public buscaTitulos() {
        initComponents();
        lstLivros.setModel(modelLstLivros); 

        lstLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Permite a seleção de apenas um item por vez
        lstLivros.addListSelectionListener(new ListSelectionListener() { //Cria um evento de seleção
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { //Verifica se a mudança ocorreu
                    int idxLivroSelecionado = lstLivros.getSelectedIndex(); //Seleciona o índice do livro selecionado
                    if (idxLivroSelecionado != -1) { //Evita que o usuário ative o evento clicando em qualquer lugar da lista
                        Book livroSelecionado = modelLstLivros.getElementAt(idxLivroSelecionado);//Instância um livro a partir do livro selecionado
                        descricaoLivros descLivroSelecionado = new descricaoLivros(livroSelecionado); // Cria outra tela
                        descLivroSelecionado.setVisible(true); // Torna a tela visível

                    }
                }
            }
        });
        lstLivros.setCellRenderer(new BookListCellRenderer());//Permite exibir uma String com o título e o autor
    }

    @SuppressWarnings("")
    private void initComponents() {

        //Inicializa os elementos da tela
        lblBuscatit = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlFundo = new javax.swing.JScrollPane();
        lstLivros = new javax.swing.JList<>();
        jcbFiltro = new javax.swing.JComboBox<>();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblBuscatit.setText("Digite um título:"); //Define um texto para o label

        txtTitulo.addActionListener(new java.awt.event.ActionListener() { //Permite que o usuáario digite um texto
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar"); //Define um texto para o botão
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt); //Aciona o método em caso de clique no botão
            }
        });

        jcbFiltro.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "10", "7", "5", "3", "1" })); //Define as opções do ComboBox
        jcbFiltro.setToolTipText("Filtro");
        jcbFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroActionPerformed(evt); //Aciona o método quando o usuário seleciona um filtro, ou deixa o valor default: 10
            }
        });

        pnlFundo.setViewportView(lstLivros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscatit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addComponent(pnlFundo, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscatit)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFundo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String txtBusca = txtTitulo.getText().trim();//Seleciona o texto digitado pelo usuário e elimina os espaços antes do início e após o fim do texto
        try{//Trata uma excessão caso o texto seja nulo 
            if(txtBusca.isEmpty()){
                throw new buscaNulaException("Erro: Digite um texto válido"); //Cria uma excessão do tipo buscaNula (Foi criada uma classe para tal)
            }
        }catch(buscaNulaException e){ //Caso a excessão aconteça
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Campo vazio", JOptionPane.ERROR_MESSAGE);//Exibe uma mensagem de erro
        }
        
        String url = utils.encoder(txtBusca);//Não havendo erro de texto nulo, transforma o texto em um URL
        if (url.equals("")){//Se a codificação falhar, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(rootPane, "Por favor, remova caracteres inválidos do seu texto", "Caractere Inválido", JOptionPane.ERROR_MESSAGE);
        }

        livro = new Book(url); //Cria um novo livro
        ArrayList<Book> buscainfos = livro.getInfos(); //Armazena as informações em um ArrayList

        modelLstLivros.clear();//Limpa a lista por precaução
        int filtroSelecionado = Integer.parseInt((String) jcbFiltro.getSelectedItem()); /*Seleciona o filtro que o usuário criou (Disponível em jcbFiltro)
                                                                                        Transformando em inteiro*/
        int contador = 0; //Cria um contador
        for (Book info : buscainfos) { //Adiciona um livro por vez na lista
            if (filtroSelecionado > contador){ //Até que o contador seja igual ao filtro selecionado pelo usuário
                modelLstLivros.addElement(info);
                contador += 1;
            }
        }   
    }

    private void jcbFiltroActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new buscaTitulos().setVisible(true); //Exibe a tela principal
            }
        });
    }

  
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> jcbFiltro;
    private javax.swing.JScrollPane pnlFundo;
    private javax.swing.JLabel lblBuscatit;
    private javax.swing.JList<Book> lstLivros; 
    private javax.swing.JTextField txtTitulo;
  


    class BookListCellRenderer extends JLabel implements ListCellRenderer<Book> {
        public BookListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Book> list, Book value, int index,
                boolean isSelected, boolean cellHasFocus) {
            setText(value.getTitulo() + " - " + value.getAutores()); 
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
    }
}