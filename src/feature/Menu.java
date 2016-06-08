/*
 * Menu.java
 *
 * Created on 10 de Dezembro de 2009, 23:22
 */

package feature;

import extra.Clusterizador;
import extra.Classificadores;
import feature.ConverterDiscArffTT;
import javax.swing.SwingUtilities;

/**
 *
 * @author  Rafael
 */
public class Menu extends javax.swing.JFrame {

    /** Creates new form Menu */
    public Menu() {
        try {
            System.out.println("Sistema Operacional: " + System.getProperties().get("os.name"));
            try{
                javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e){
                if(System.getProperties().get("os.name").toString().contains("Windows")){
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
                }else{
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                }
            }
            
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
        } catch (Exception e) {  
            System.out.println("Ocorreu um erro ao carregar o Look and Feel.");
        }
        
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bConvertARNG = new javax.swing.JButton();
        bVisNumRegras = new javax.swing.JButton();
        bConvertNGMAV = new javax.swing.JButton();
        bCompAtr = new javax.swing.JButton();
        bConvDiscArff = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        bSepTT = new javax.swing.JButton();
        bSepDisc = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        bSepDiscBag = new javax.swing.JButton();
        bContarNGrams = new javax.swing.JButton();
        bFreqItems = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu - Gerador de N-Gramas");
        setLocationByPlatform(true);

        jButton1.setText("Converter Arquivo Texto em Arquivo de Transações");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Converter Arquivo de Transações em Arquivo de Regras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bConvertARNG.setText("Converter Arquivo de Regras em N-Gramas");
        bConvertARNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConvertARNGActionPerformed(evt);
            }
        });

        bVisNumRegras.setText("Visualizar Número de Regras Geradas");
        bVisNumRegras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVisNumRegrasActionPerformed(evt);
            }
        });

        bConvertNGMAV.setText("Converter Arquivos de N-Gramas em MAV");
        bConvertNGMAV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConvertNGMAVActionPerformed(evt);
            }
        });

        bCompAtr.setText("Comparar Atributos Gerados");
        bCompAtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompAtrActionPerformed(evt);
            }
        });

        bConvDiscArff.setText("Converter Discover em Arff");
        bConvDiscArff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConvDiscArffActionPerformed(evt);
            }
        });

        jButton3.setText("Classificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Agrupar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        bSepTT.setText("Separar Arquivos Treino e Teste");
        bSepTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSepTTActionPerformed(evt);
            }
        });

        bSepDisc.setText("Separar Discover Treino e Teste");
        bSepDisc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSepDiscActionPerformed(evt);
            }
        });

        jButton4.setText("Converter Discover-Arff Teste Treino");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        bSepDiscBag.setText("Separar Discover Bag-of-Words Treino e Teste");
        bSepDiscBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSepDiscBagActionPerformed(evt);
            }
        });

        bContarNGrams.setText("Contar N-Grams");
        bContarNGrams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContarNGramsActionPerformed(evt);
            }
        });

        bFreqItems.setText("Frequência dos Items");
        bFreqItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFreqItemsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(bConvertARNG, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bVisNumRegras, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bConvertNGMAV, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bConvDiscArff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bSepDisc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bSepTT, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bSepDiscBag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bContarNGrams, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bFreqItems, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(bCompAtr, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bVisNumRegras)
                .addGap(8, 8, 8)
                .addComponent(bConvertARNG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bConvertNGMAV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bConvDiscArff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(159, 159, 159)
                .addComponent(bCompAtr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bSepTT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bSepDisc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bSepDiscBag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bContarNGrams)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bFreqItems)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    new ConvertATAT();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    new ConvertATAR();
}//GEN-LAST:event_jButton2ActionPerformed

private void bVisNumRegrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVisNumRegrasActionPerformed
    new VisNumRegras();
}//GEN-LAST:event_bVisNumRegrasActionPerformed

private void bConvertARNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConvertARNGActionPerformed
    new ConvertARAA();
}//GEN-LAST:event_bConvertARNGActionPerformed

private void bConvertNGMAVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConvertNGMAVActionPerformed
    new ConvertAAMAV();
}//GEN-LAST:event_bConvertNGMAVActionPerformed

private void bCompAtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompAtrActionPerformed
    new CompararATR();
}//GEN-LAST:event_bCompAtrActionPerformed

private void bConvDiscArffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConvDiscArffActionPerformed
    new ConvertDiscArff();
}//GEN-LAST:event_bConvDiscArffActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    new Classificadores();
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    new Clusterizador();
}//GEN-LAST:event_jButton5ActionPerformed

private void bSepTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSepTTActionPerformed
    new SepararArquivosTT();
}//GEN-LAST:event_bSepTTActionPerformed

private void bSepDiscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSepDiscActionPerformed
    new SepararDiscoverTT();
}//GEN-LAST:event_bSepDiscActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    new ConverterDiscArffTT();
}//GEN-LAST:event_jButton4ActionPerformed

private void bSepDiscBagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSepDiscBagActionPerformed
    new SepararDiscoverBagTT();
}//GEN-LAST:event_bSepDiscBagActionPerformed

private void bContarNGramsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContarNGramsActionPerformed
    new ContarAtributos();
}//GEN-LAST:event_bContarNGramsActionPerformed

private void bFreqItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFreqItemsActionPerformed
    new ContarFrequenciaItems();
}//GEN-LAST:event_bFreqItemsActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCompAtr;
    private javax.swing.JButton bContarNGrams;
    private javax.swing.JButton bConvDiscArff;
    private javax.swing.JButton bConvertARNG;
    private javax.swing.JButton bConvertNGMAV;
    private javax.swing.JButton bFreqItems;
    private javax.swing.JButton bSepDisc;
    private javax.swing.JButton bSepDiscBag;
    private javax.swing.JButton bSepTT;
    private javax.swing.JButton bVisNumRegras;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    // End of variables declaration//GEN-END:variables

}