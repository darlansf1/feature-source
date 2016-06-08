package feature;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import feature.GerenciadorArquivos;

/*
 * ConverterDiscArffTT.java
 *
 * Created on August 16, 2010, 3:39 PM
 */



/**
 *
 * @author  ragero
 */
public class ConverterDiscArffTT extends javax.swing.JFrame {
    
    BufferedReader entradaMatriz, entradaNames;
    BufferedWriter saidaArff;
    String nomeRelacao;
    boolean temClasse;
    
    /** Creates new form ConverterDiscArffTT */
    public ConverterDiscArffTT() {
        initComponents();
        this.setVisible(true);
    }
    
    public void MontaArffDiscover(String relacao, String dirIn, String dirOut, boolean classe){

        try{

                nomeRelacao = relacao;
                temClasse = classe;
                
                //Gravando arff de Treino
                
                nomeRelacao = "Treino";
                entradaMatriz = new BufferedReader(new FileReader(dirIn + "/discoverTreino.data"));
                entradaNames  = new BufferedReader(new FileReader(dirIn + "/discoverTreino.names"));
                
                System.out.println("Convertendo arquivos do diretório " + dirIn);
                
                saidaArff = new BufferedWriter(new FileWriter(dirOut + "/" + relacao + "Treino.arff"));

                gravaCabecalho();
                gravaDados();
                
                //Gravando arff de Teste
                nomeRelacao = "Teste";
                entradaMatriz = new BufferedReader(new FileReader(dirIn + "/discoverTeste.data"));
                entradaNames  = new BufferedReader(new FileReader(dirIn + "/discoverTeste.names"));
                
                System.out.println("Convertendo arquivos do diretório " + dirIn);
                
                saidaArff = new BufferedWriter(new FileWriter(dirOut + "/" + relacao + "Teste.arff"));

                gravaCabecalho();
                gravaDados();

        }

        catch(IOException ex){
                ex.printStackTrace();
        }

    }
    
    
    private void gravaCabecalho() throws IOException{

        String linha = entradaNames.readLine(), // Despreza a primeira posicao (filename:string:ignore.)
                       nomeAtributo,
                       linhaSaida;
        String [] classes;

        //  Se tiver classe, despreza a linha "att_class." do arquivo de entrada
        if(temClasse)
                linha = entradaNames.readLine();

        //  Grava o cabeçalho
        saidaArff.write("@RELATION " + nomeRelacao);
        saidaArff.newLine();
        saidaArff.write("");
        saidaArff.newLine();

        linha = entradaNames.readLine();
        while(linha != null){

                //  Grava a classe
                if(linha.startsWith("att_class")){
                        classes = linha.split("\\(")[1].split("\\)")[0].split(",");
                        linhaSaida = "@ATTRIBUTE class {";

                        if(classes.length > 0){
                                linhaSaida += classes[0].replaceAll("\"", "");
                                for(int i = 1; i < classes.length; i++)
                                        linhaSaida += "," + classes[i].replaceAll("\"", "");
                        }
                        linhaSaida += "}";

                        saidaArff.write(linhaSaida);
                        saidaArff.newLine();
                }

                //  Grava os demais atributos
                else{
                        nomeAtributo = linha.split("\"")[1];
                        saidaArff.write("@ATTRIBUTE " + nomeAtributo + " REAL");
                        saidaArff.newLine();
                }

                linha = entradaNames.readLine();
        }

        saidaArff.flush();		
    }

    private void gravaDados() throws IOException{

        String linha    = entradaMatriz.readLine();
        StringBuffer linhaSaida;
        String [] linhaSplit;
        int contInicial = 1;
        boolean found   = false;



        saidaArff.write("");
        saidaArff.newLine();
        saidaArff.write("@DATA");
        saidaArff.newLine();

        while(linha != null){

                linhaSplit = linha.split(",");

                //  Despreza a primeira posicao (nome do arquivo)
                linhaSaida = new StringBuffer();
                linhaSaida.append(linhaSplit[1]);
                for(int i = 2; i < linhaSplit.length - 1; i++){
                        linhaSaida .append( "," + linhaSplit[i]);
                }

                linhaSaida .append("," + linhaSplit[linhaSplit.length - 1]);

                saidaArff.write(linhaSaida.toString());
                saidaArff.newLine();

                linha = entradaMatriz.readLine();
        }

        saidaArff.flush();
        saidaArff.close();

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tDirIn = new javax.swing.JTextField();
        tDirOut = new javax.swing.JTextField();
        bProcIn = new javax.swing.JButton();
        bProcOut = new javax.swing.JButton();
        cClasse = new javax.swing.JCheckBox();
        bConverter = new javax.swing.JButton();
        bSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        oPasta = new javax.swing.JRadioButton();
        oSimples = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        tNameArff = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Directories"));

        jLabel1.setText("Input");

        jLabel2.setText("Output");

        bProcIn.setText("Select...");
        bProcIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcInActionPerformed(evt);
            }
        });

        bProcOut.setText("Select...");
        bProcOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tDirIn, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addComponent(tDirOut, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bProcIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bProcOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tDirIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bProcIn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bProcOut)
                            .addComponent(jLabel2)
                            .addComponent(tDirOut, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cClasse.setSelected(true);
        cClasse.setText("There is class");

        bConverter.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        bConverter.setText("Convert");
        bConverter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConverterActionPerformed(evt);
            }
        });

        bSair.setText("Close");
        bSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSairActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome do arquivo Arff"));

        oPasta.setSelected(true);
        oPasta.setText("Use the directory name");

        oSimples.setText("Specify the file name");

        jLabel3.setText("File name");

        jLabel4.setText(".arff");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(oSimples))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(oPasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tNameArff, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jLabel4))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oPasta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(oSimples)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(tNameArff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cClasse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                                .addComponent(bConverter, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cClasse)
                    .addComponent(bSair)
                    .addComponent(bConverter))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bProcInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcInActionPerformed
        JFileChooser open = new JFileChooser();
        open.setFileSelectionMode(open.DIRECTORIES_ONLY);
        open.setDialogTitle("Selecione o Diretório");
        open.setDialogType(open.OPEN_DIALOG);
        open.showOpenDialog(null);
        if(open.getSelectedFile()==null){
            tDirIn.setText("");
        }else{
            tDirIn.setText(open.getSelectedFile().toString());
        }
    }//GEN-LAST:event_bProcInActionPerformed

    private void bProcOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcOutActionPerformed
        JFileChooser open = new JFileChooser();
        open.setFileSelectionMode(open.DIRECTORIES_ONLY);
        open.setDialogTitle("Selecione o Diretório");
        open.setDialogType(open.OPEN_DIALOG);
        open.showOpenDialog(null);
        if(open.getSelectedFile()==null){
            tDirOut.setText("");
        }else{
            tDirOut.setText(open.getSelectedFile().toString());
        }
    }//GEN-LAST:event_bProcOutActionPerformed
    
    private boolean ValidarDir(){
        File dirIn = new File(tDirIn.getText());
        if(!dirIn.isDirectory()){
            JOptionPane.showMessageDialog(null, "O diretório de entrada não é válido", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!dirIn.exists()){
            JOptionPane.showMessageDialog(null, "O diretório de entrada não existe", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        File dirOut = new File(tDirOut.getText());
        if(!dirOut.isDirectory()){
            JOptionPane.showMessageDialog(null, "O diretório de saída não é valido", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!dirOut.exists()){
            JOptionPane.showMessageDialog(null, "O diretório de saída não existe", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        File[] files = dirIn.listFiles();
        if(files.length==0){
            JOptionPane.showMessageDialog(null, "O diretório de entrada não possui arquivos", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(dirIn.toString().equals(dirOut.toString())){
            JOptionPane.showMessageDialog(null, "O diretório de entrada não pode ser igual ao diretório de saída", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
    private void bConverterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConverterActionPerformed
        boolean valida = ValidarDir();
        if(valida == false){
            return;
        }
        File dirIn = new File(tDirIn.getText());
        File dirOut = new File(tDirOut.getText());
        
        boolean classe = false;
        if(cClasse.isSelected()){
            classe = true;
        }
        
        if(oPasta.isSelected()){
            ArrayList<String> dirs = new ArrayList<String>();
            boolean criarDir = GerenciadorArquivos.ListaDirs(dirIn, dirOut, dirs, dirIn);
            int lengthDirIn = dirIn.toString().length();
            
            for(int i=0;i<dirs.size();i++){
                File[] files = new File(dirs.get(i)).listFiles();
                int cData = 0;
                int cNames = 0;
                for(int j=0;j<files.length;j++){
                    String nomeArq = files[j].toString();
                    if(nomeArq.endsWith(".data")){
                        cData++;
                    }
                    if(nomeArq.endsWith(".names")){
                        cNames++;
                    }
                }
                /*if((cData > 1)||(cNames > 1)){
                    JOptionPane.showMessageDialog(null, "O diretório " + dirs.get(i) + " possui mais que um arquivo .data ou .names.\n A operação de conversão não será realizada para os arquivos deste diretório.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                
                if((cData == 0)||(cNames == 0)){
                    JOptionPane.showMessageDialog(null, "O diretório " + dirs.get(i) + " não possui um arquivo .data ou .names.\n A operação de conversão não será realizada para os arquivos deste diretório.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }*/
                
                String dirsIn = dirs.get(i).toString();
                dirsIn.replace("\\", "/");
                String fileOut = dirsIn.substring(lengthDirIn,dirsIn.length());
                fileOut.replace("\\", "/");
                String nameOut = fileOut.replace("/", "_");
                if(nameOut.startsWith("_")){
                    nameOut = nameOut.substring(1, nameOut.length());
                }
                fileOut = dirOut.toString();
                
                MontaArffDiscover(nameOut,dirsIn,dirOut.toString(), classe);
                
            }
        }else{
            File[] files = dirIn.listFiles();
            int cData = 0;
            int cNames = 0;
            
            for(int j=0;j<files.length;j++){
                String nomeArq = files[j].toString();
                if(nomeArq.endsWith(".data")){
                    cData++;
                }
                if(nomeArq.endsWith(".names")){
                    cNames++;
                }
            }
            /*if((cData > 1)||(cNames > 1)){
                JOptionPane.showMessageDialog(null, "O diretório de entrada possui mais que um arquivo .data ou .names.\n A operação de conversão não será realizada para os arquivos deste diretório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if((cData == 0)||(cNames == 0)){
                JOptionPane.showMessageDialog(null, "O diretório de entrada não possui um arquivo .data ou .names.\n A operação de conversão não será realizada para os arquivos deste diretório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            */
            String nameOut = tNameArff.getText();
            if(nameOut.trim().length()==0){
                JOptionPane.showMessageDialog(null, "O nome da relação não é válida.\n A operação de conversão não será realizada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            MontaArffDiscover(nameOut,dirIn.toString(),dirOut.toString(), classe);
            
        }
        
        
        
        
        JOptionPane.showMessageDialog(null, "Operação concluída com sucesso", "OK", JOptionPane.PLAIN_MESSAGE);
        
        //MontaArffDiscover();
    }//GEN-LAST:event_bConverterActionPerformed

    private void bSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSairActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConverterDiscArffTT().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConverter;
    private javax.swing.JButton bProcIn;
    private javax.swing.JButton bProcOut;
    private javax.swing.JButton bSair;
    private javax.swing.JCheckBox cClasse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton oPasta;
    private javax.swing.JRadioButton oSimples;
    private javax.swing.JTextField tDirIn;
    private javax.swing.JTextField tDirOut;
    private javax.swing.JTextField tNameArff;
    // End of variables declaration//GEN-END:variables
    
}
