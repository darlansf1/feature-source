/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obterngramas;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import javax.swing.JOptionPane;
import regras.estruturas.ItemsetMed;

/**
 *
 * @author ragero
 */
public class ItemsetSimplesMedio {
    public ItemsetSimplesMedio(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, boolean repetir, int rank){
        ArrayList<String> itemsets;
        HashMap<String,Float> hash;
        for(int i=0;i<filesIn.size();i++){
            File fileIn = filesIn.get(i);
            File fileOut = filesOut.get(i);
            System.out.println("File In:  " + fileIn.toString());
            System.out.println("File Out: " + fileOut.toString());
            itemsets = new ArrayList<String>();
            hash = new HashMap<String,Float>();
            
            //System.out.println("Lendo o Arquivo de Regras...");
            try{
                RandomAccessFile fileRules = new RandomAccessFile(fileIn, "r");
                String line = "";
                while((line = fileRules.readLine())!=null){
                    //System.out.print(".");
                    String[] data = null;
                    String cabeca = line.substring(0, line.indexOf(" <-"));
                    String corpo = line.substring(line.indexOf("<- ") + 3, line.indexOf("("));
                    String linha = cabeca + " " + corpo;
                    data = linha.split(" ");

                    Arrays.sort(data);
                    linha = new String();
                    for(int j=0;j<data.length;j++){
                        linha = linha + data[j] + " ";
                    }

                    String sSuporte = line.substring(line.indexOf("(") + 1, line.indexOf(","));
                    String sConfianca = line.substring(line.indexOf(", ") + 2, line.indexOf(")"));

                    Float suporte = Float.parseFloat(sSuporte);
                    if(suporte>=supMin){
                        if(!itemsets.contains(linha)){
                            itemsets.add(linha);
                            hash.put(linha, suporte);
                        }
                    }

                    //System.out.print("Regra -- Cabeça: " + regra.cabeca + " Corpo: " + regra.corpo + " Suporte: " + suporte + " Confiaça: " + confianca + "\n");
                }
                
                fileRules.close();
                        
                //Gravando os itemsets no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                
                if(rank > 0){
                    ArrayList<ItemsetMed> sortRules =  new ArrayList<ItemsetMed>();
                    Object[] items = itemsets.toArray();
                    for(int k=0; k<items.length; k++){
                        String itemset = (String)items[k];
                        if(!itemset.contains(" ")){
                            ItemsetMed rule = new ItemsetMed(itemset, 0.0);
                            sortRules.add(rule);
                        }else{
                            ItemsetMed rule = new ItemsetMed(itemset, (double)hash.get(itemset));
                            sortRules.add(rule);
                        }
                    }
                    
                    Object[] sortedRules = sortRules.toArray();
                    Arrays.sort( sortedRules, new Comparator() {
                        public int compare( Object rule1, Object rule2 ) {
                                ItemsetMed obj1 = (ItemsetMed)rule1;
                                ItemsetMed obj2 = (ItemsetMed)rule2;
                                if(obj1.med>obj2.med){
                                    return -1;
                                }else{
                                    if(obj1.med<obj2.med){
                                        return 1;
                                    }else{
                                        return 0;
                                    }         
                                }
                        }
                    });
                    
                    if(repetir == true){
                        for(int j=0;j<rank;j++){
                            ItemsetMed rule = (ItemsetMed)sortedRules[j];
                            String linha = rule.itemset;
                            Float valor = hash.get(linha);
                            for(int k=0;k<valor;k++){
                                arqOut.write(linha.trim().replace(" ", "_") + "\n");    
                                //arqOut.write(linha.replace(" ", "_") + " (" + valor + ")\n");
                                //arqOut.write(linha + "\n");
                            }
                        }
                    }else{
                        for(int j=0;j<rank;j++){
                            ItemsetMed rule = (ItemsetMed)sortedRules[j];
                            String linha = rule.itemset;
                            arqOut.write(linha.trim().replace(" ", "_") + "\n");
                            //arqOut.write(linha + "\n");

                        }    
                    }    
                }else{
                    float supMedio = 0;
                    int kitemsets = 0;
                    for(int j=0;j<itemsets.size();j++){
                        String linha = itemsets.get(j);
                        if(linha.contains(" ")){
                            float supRegra = hash.get(linha);
                            supMedio = supMedio + supRegra;
                            kitemsets++;
                            //System.out.println("Itemset: " + linha + "  Confianca: " + confRegra);
                        }
                    }
                    
                    System.out.println("Total: " + supMedio);
                    supMedio = (float)supMedio/(float)kitemsets;            
                    System.out.println("Suporte Medio: " + supMedio + "--------------------------------------------------");
                    
                    if(repetir == true){
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Float repet = hash.get(linha);
                            if(repet<supMedio){
                                continue;
                            }
                            for(int k=0;k<repet;k++){
                                arqOut.write(linha.trim().replace(" ", "_") + "\n");
                            }
                        }
                    }else{
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Float valor = hash.get(linha);
                            if(valor<supMedio){
                                continue;
                            }
                            //arqOut.write(linha + "(" + valor + ")\n");
                            arqOut.write(linha.trim().replace(" ", "_") + "\n");
                        }    
                    }    
                }
                
                
                arqOut.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar os n-gramas.\nO programa será encerrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println();
            
            
            
            
        }
    }
}
