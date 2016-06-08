/*
 * ConvertATAT.java
 *
 * Created on 10 de Dezembro de 2009, 23:24
 */

package feature;

import estruturas.strFreq;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import preprocessamento.StopWords;

/**
 *
 * @author  Rafael
 */
public class ConvertATAT extends javax.swing.JFrame {
    
    static Integer tamJanela, tamPulo;
    HashMap<String, ArrayList<strFreq>> dicionario;
    HashMap<String, String> stemPal;
    static FileWriter arqTrans;
    /** Creates new form ConvertATAT */
    public ConvertATAT() {
        tamJanela = 5;
        tamPulo = 1;
        
        initComponents();
        
        File curDir = new File("");
        String camDic = curDir.getAbsolutePath();
        camDic = camDic.replace("\\", "/");
        if(camDic.endsWith("/")){
            camDic = camDic + "dictionary.txt";
        }else{
            camDic = camDic + "/dictionary.txt";
        }
        
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tDirIn = new javax.swing.JTextField();
        tDirOut = new javax.swing.JTextField();
        bProcIn = new javax.swing.JButton();
        bProcOut = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        oIng = new javax.swing.JRadioButton();
        oPort = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        cStopWords = new javax.swing.JCheckBox();
        cStemming = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        oSentenca = new javax.swing.JRadioButton();
        oParagrafo = new javax.swing.JRadioButton();
        oJanela = new javax.swing.JRadioButton();
        bOpJanela = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        oStem = new javax.swing.JRadioButton();
        oWord = new javax.swing.JRadioButton();
        bSair = new javax.swing.JButton();
        bConverter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mapping Text Files into Transaction Files");
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Directories"));

        jLabel1.setText("Input");

        jLabel2.setText("Output");

        bProcIn.setText("Select ...");
        bProcIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcInActionPerformed(evt);
            }
        });

        bProcOut.setText("Select ...");
        bProcOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tDirOut, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addComponent(tDirIn, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bProcOut)
                    .addComponent(bProcIn))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bProcIn)
                    .addComponent(tDirIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bProcOut)
                    .addComponent(tDirOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Language"));

        buttonGroup1.add(oIng);
        oIng.setSelected(true);
        oIng.setText("English");

        buttonGroup1.add(oPort);
        oPort.setText("Portuguese");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oPort)
                    .addComponent(oIng))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(oIng)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(oPort)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Text Preprocessing"));

        cStopWords.setSelected(true);
        cStopWords.setText("Remove stopwords");

        cStemming.setSelected(true);
        cStemming.setText("Stemming");
        cStemming.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cStemmingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cStemming)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cStopWords, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cStemming)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cStopWords)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Transactions"));

        buttonGroup2.add(oSentenca);
        oSentenca.setSelected(true);
        oSentenca.setText("Sentence");

        buttonGroup2.add(oParagrafo);
        oParagrafo.setText("Paragraph");

        buttonGroup2.add(oJanela);
        oJanela.setText("Window");
        oJanela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oJanelaActionPerformed(evt);
            }
        });

        bOpJanela.setText("...");
        bOpJanela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOpJanelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oSentenca)
                    .addComponent(oParagrafo)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(oJanela)
                        .addGap(18, 18, 18)
                        .addComponent(bOpJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(oSentenca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oParagrafo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bOpJanela)
                    .addComponent(oJanela))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaction Items"));

        buttonGroup3.add(oStem);
        oStem.setSelected(true);
        oStem.setText("Use stemmed items");

        buttonGroup3.add(oWord);
        oWord.setText("Use the word more frequent in the place of the stem");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oStem)
                    .addComponent(oWord))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(oStem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(oWord)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bSair.setText("Close");
        bSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSairActionPerformed(evt);
            }
        });

        bConverter.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        bConverter.setText("Convert");
        bConverter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConverterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bConverter, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bConverter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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

private void bSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSairActionPerformed
    this.dispose();
}//GEN-LAST:event_bSairActionPerformed

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
    ArrayList<File> filesIn = new ArrayList<File>();
    ArrayList<String> filesOut = new ArrayList<String>();
    boolean criarDir = GerenciadorArquivos.ListaArquivosEspecial(dirIn, dirOut, filesIn, filesOut, dirIn);
    if(criarDir == false){
        JOptionPane.showMessageDialog(null, "Não foi possível criar os diretórios necessários para a extração dos arquivos.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    String language = "";  
    if(oIng.isSelected()){
        language = "ingl";
    }else{
        language = "port";
    }
    
    StopWords sw = new StopWords(language); //Objeto para limpeza de documentos
    
    dicionario = new HashMap<String, ArrayList<strFreq>>();
    stemPal = new HashMap<String,String>();
    
    for(int i=0;i<filesIn.size();i++){
        File fileIn = filesIn.get(i);
        String fileOut = filesOut.get(i);
        System.out.println("File In:  " + fileIn.toString());
        System.out.println("File Out: " + fileOut.toString());
        if(!fileIn.getName().endsWith("txt")){
            continue;
        }
  
               
        String typeTrans = "";
        if(oSentenca.isSelected()){
            typeTrans = "sent";
        }else{
            if(oParagrafo.isSelected()){
                typeTrans = "par";
            }else{
                typeTrans = "jan";
            }
        }
        
        ArrayList<String> transactions = new ArrayList<String>();
        boolean remStopWords = false;
        if(cStopWords.isSelected()){
            remStopWords = true;
        }
        boolean radicalizar = false;
        if(cStemming.isSelected()){
            radicalizar = true;
        }
        transactions = TextToTransaction.convertTT(fileIn, dicionario, language, radicalizar, typeTrans, tamJanela, tamPulo, sw, remStopWords, stemPal);
               
        try{
            if(transactions.size()> 0){
                String filename = fileOut;
                arqTrans = new FileWriter(new File(filename));
                for(int j=0;j<transactions.size();j++){
                    String trans = transactions.get(j);
                    arqTrans.write(trans + "\n");
                }
                arqTrans.close();
                arqTrans = null;
                System.gc();    
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar o arquivo de transações. O aplicativo será finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
        
    }
    
    
    //Tem que colocar uma condição aqui!!!!!
    //-------------------------------------
    //-------------------------------------
    if(oWord.isSelected()){
        System.out.println("Reprocessando os arquivos de transação...");
        HashMap<String,String> dicionario2 = new HashMap<String,String>();
        Set<String> chaves = dicionario.keySet();
        Iterator it = chaves.iterator();
        while(it.hasNext()){
            String stem = (String)it.next();
            ArrayList<strFreq> sigs = dicionario.get(stem);
            int freq = 0;
            String word = "";
            for(int j=0;j<sigs.size();j++){
                strFreq sig = sigs.get(j);
                if(sig.frequencia > freq){
                    freq = sig.frequencia;
                    word = sig.palavra;
                }
                dicionario2.put(stem,word);
            }
        }
        for(int i=0;i<filesOut.size();i++){
            try{
                File fileOut = new File(filesOut.get(i));
                StringBuffer saida = new StringBuffer();
                BufferedReader arqIn = new BufferedReader(new FileReader(fileOut));
                String linha = "";
                while((linha = arqIn.readLine()) != null){
                    String[] palavras = linha.split(" ");
                    for(int j=0;j<palavras.length;j++){
                        saida.append(dicionario2.get(palavras[j]));
                        saida.append(" ");
                    }
                    saida.append("\n");
                }
                arqIn.close();
                FileWriter arqOut = new FileWriter(fileOut);
                arqOut.write(saida.toString());
                arqOut.close();
            }catch(Exception e){
                System.err.println("Houve um erro ao ler o arquivo de transações");
                e.printStackTrace();
            }
        }
    }
        
    System.out.println("Arquivos de transacao reprocessados");
    JOptionPane.showMessageDialog(null, "Operação concluída com sucesso", "Keyword", JOptionPane.PLAIN_MESSAGE); 

}//GEN-LAST:event_bConverterActionPerformed

private void oJanelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oJanelaActionPerformed
    
}//GEN-LAST:event_oJanelaActionPerformed

private void bOpJanelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpJanelaActionPerformed
    new opJanela(tamJanela, tamPulo);
}//GEN-LAST:event_bOpJanelaActionPerformed

private void cStemmingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cStemmingActionPerformed
    if(cStemming.isSelected()){
        oStem.setEnabled(true);
        oStem.setEnabled(true);
    }else{
        oStem.setEnabled(false);
        oStem.setEnabled(false);
    }
        
}//GEN-LAST:event_cStemmingActionPerformed

    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConverter;
    private javax.swing.JButton bOpJanela;
    private javax.swing.JButton bProcIn;
    private javax.swing.JButton bProcOut;
    private javax.swing.JButton bSair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox cStemming;
    private javax.swing.JCheckBox cStopWords;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton oIng;
    private javax.swing.JRadioButton oJanela;
    private javax.swing.JRadioButton oParagrafo;
    private javax.swing.JRadioButton oPort;
    private javax.swing.JRadioButton oSentenca;
    private javax.swing.JRadioButton oStem;
    private javax.swing.JRadioButton oWord;
    private javax.swing.JTextField tDirIn;
    private javax.swing.JTextField tDirOut;
    // End of variables declaration//GEN-END:variables

}
