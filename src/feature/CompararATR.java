/*
 * CompararATR.java
 *
 * Created on January 15, 2010, 12:35 PM
 */

package feature;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author  ragero
 */
public class CompararATR extends javax.swing.JFrame {
    
    /** Creates new form CompararATR */
    public CompararATR() {
        initComponents();
        this.setVisible(true);
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
        tArq1 = new javax.swing.JTextField();
        tArq2 = new javax.swing.JTextField();
        bProcIn = new javax.swing.JButton();
        bProcOut = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bAnalisar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lNumAtr1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lNumAtr2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lNumSem = new javax.swing.JLabel();
        lNumDif = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lSem = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lDif = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compare Generated Features");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Discover.names"));

        jLabel1.setText("File 1:");

        jLabel2.setText("File 2:");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tArq1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(tArq2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bProcIn)
                    .addComponent(bProcOut))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tArq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProcIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tArq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProcOut))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bAnalisar.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        bAnalisar.setText("Analyse");
        bAnalisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnalisarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Results"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Statistics"));

        jLabel3.setText("Number of Features from File 1:");

        lNumAtr1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lNumAtr1.setText("  ");
        lNumAtr1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel5.setText("Number of Features from File 2:");

        lNumAtr2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lNumAtr2.setText("  ");
        lNumAtr2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel7.setText("Number of equal features:");

        jLabel8.setText("Number of different features:");

        lNumSem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lNumSem.setText("  ");
        lNumSem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lNumDif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lNumDif.setText("  ");
        lNumDif.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lNumAtr1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lNumAtr2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNumDif, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNumSem, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lNumAtr1)
                    .addComponent(jLabel7)
                    .addComponent(lNumSem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lNumAtr2)
                    .addComponent(jLabel8)
                    .addComponent(lNumDif))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Equal Features"));

        jScrollPane1.setViewportView(lSem);

        jButton2.setText("Save");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(203, 203, 203))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Different Features"));

        jScrollPane2.setViewportView(lDif);

        jButton3.setText("Save");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, 0, 444, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(1013, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(bAnalisar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(bAnalisar)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bProcInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcInActionPerformed
        JFileChooser open = new JFileChooser();
        open.setFileSelectionMode(open.FILES_ONLY);
        open.setDialogTitle("Selecione 1º Arquivo");
        open.setDialogType(open.OPEN_DIALOG);
        open.showOpenDialog(null);
        if(open.getSelectedFile()==null){
            tArq1.setText("");
        }else{
            tArq1.setText(open.getSelectedFile().toString());
        }
    }//GEN-LAST:event_bProcInActionPerformed

    private void bProcOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcOutActionPerformed
        JFileChooser open = new JFileChooser();
        open.setFileSelectionMode(open.FILES_ONLY);
        open.setDialogTitle("Selecione 2º Arquivo");
        open.setDialogType(open.OPEN_DIALOG);
        open.showOpenDialog(null);
        if(open.getSelectedFile()==null){
            tArq2.setText("");
        }else{
            tArq2.setText(open.getSelectedFile().toString());
        }
    }//GEN-LAST:event_bProcOutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bAnalisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnalisarActionPerformed
        
        //Validando arquivos de entrada
        File file1 = new File(tArq1.getText());
        File file2 = new File(tArq2.getText());
        if(!file1.exists()){
            JOptionPane.showMessageDialog(null, "O 1º arquivo de entrada é inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!file2.exists()){
            JOptionPane.showMessageDialog(null, "O 2º arquivo de entrada é inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        HashMap<String,Boolean> hash = new HashMap<String,Boolean>();
        int contAtr1=0, contAtr2=0, contSem=0, contDif=0;
        ArrayList<String> semelhantes = new ArrayList<String>();
        ArrayList<String> diferentes = new ArrayList<String>();
        DefaultListModel modelSem = new DefaultListModel();
        DefaultListModel modelDif = new DefaultListModel();
        lSem.setModel(modelSem);
        lDif.setModel(modelDif);
        
        //Lendo o 1º Arquivo
        System.out.println("Lendo o primeiro arquivo...");
        try{
            RandomAccessFile arq1 = new RandomAccessFile(tArq1.getText(), "r");
            String linha = "";
            while((linha = arq1.readLine())!=null){
                String chave = new String(linha);
                Boolean var = false;
                hash.put(chave, var);
                contAtr1++;
            }
            arq1.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler o 1º arquivo.\nO aplicativo será finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
        
        //Lendo o 2º Arquivo
        System.out.println("Lendo o segundo arquivo arquivo...");
        try{
            RandomAccessFile arq2 = new RandomAccessFile(tArq2.getText(), "r");
            String linha = "";
            while((linha = arq2.readLine())!=null){
                String chave = new String(linha);
                if(hash.containsKey(chave)){
                    Boolean var = false;
                    hash.put(chave, var);
                    contSem++;
                    semelhantes.add(chave);
                }else{
                    contDif++;
                    diferentes.add(chave);
                }
                contAtr2++;
                System.out.print(".");
            }
            arq2.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler o 2º arquivo.\nO aplicativo será finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
        
        //Ordenando as listas de elementos semelhantes e diferentes
        System.out.println("\nOrdenando os elementos das Listas");
        Object[] arraySem = semelhantes.toArray();
        Arrays.sort(arraySem);
        Object[] arrayDif = diferentes.toArray();
        Arrays.sort(arrayDif);
        
        //Gerando a lista de semelhantes
        for(int i=0;i<arraySem.length;i++){
            String ngram = new String(arraySem[i].toString());
            modelSem.addElement(ngram);
        }
        lSem.setModel(modelSem);
        //Gerando a lista de diferentes
        for(int i=0;i<arrayDif.length;i++){
            String ngram = new String(arrayDif[i].toString());
            modelDif.addElement(ngram);
        }
        lDif.setModel(modelDif);
        
        //Exibindo as contagens
        lNumAtr1.setText("\"" + contAtr1 + "\"");
        lNumAtr2.setText("\"" + contAtr2 + "\"");
        lNumSem.setText("\"" + contSem + "\"");
        lNumDif.setText("\"" + contDif + "\"");
        
    }//GEN-LAST:event_bAnalisarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompararATR().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnalisar;
    private javax.swing.JButton bProcIn;
    private javax.swing.JButton bProcOut;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lDif;
    private javax.swing.JLabel lNumAtr1;
    private javax.swing.JLabel lNumAtr2;
    private javax.swing.JLabel lNumDif;
    private javax.swing.JLabel lNumSem;
    private javax.swing.JList lSem;
    private javax.swing.JTextField tArq1;
    private javax.swing.JTextField tArq2;
    // End of variables declaration//GEN-END:variables
    
}
