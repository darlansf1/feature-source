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
public class ConfiancaMedia {

    public ConfiancaMedia(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, boolean repetir, Float confMin, int rank){
        
        ArrayList<String> itemsets;
        HashMap<String,Float> hashSup;
        HashMap<String,Double> hashConf;
        
        double confMed;
        int kitemsets;
        
        for(int i=0;i<filesIn.size();i++){
            File fileIn = filesIn.get(i);
            File fileOut = filesOut.get(i);
            confMed = 0;
            kitemsets = 0;
            System.out.println("File In:  " + fileIn.toString());
            System.out.println("File Out: " + fileOut.toString());
            itemsets = new ArrayList<String>();
            hashSup = new HashMap<String,Float>();
            hashConf = new HashMap<String,Double>();

            //System.out.println("Ta aqui!!!!!!!!!!");
            //System.out.println("Lendo o Arquivo de Regras...");
            try{
                RandomAccessFile fileRules = new RandomAccessFile(fileIn, "r");
                String line = "";
                while((line = fileRules.readLine())!=null){
                    //System.out.print(".");
                    String[] data = null;
                    String cabeca = line.substring(0, line.indexOf(" <-"));
                    cabeca = cabeca.trim();
                    String corpo = line.substring(line.indexOf("<- ") + 3, line.indexOf("("));
                    corpo = corpo.trim();
                    
                    String linha = corpo;
                    corpo = "";
                    data = linha.split(" ");
                    
                    //if(data.length > 2){
                    //    continue;
                    //}
                    
                    Arrays.sort(data);
                    linha = new String();
                    for(int j=0;j<data.length;j++){
                        corpo = corpo + data[j] + " ";
                    }
                    corpo = corpo.trim();
                    
                    linha = cabeca + " " + corpo;
                    data = linha.split(" ");
                    
                    //if(data.length > 2){
                    //    continue;
                    //}
                    
                    Arrays.sort(data);
                    linha = new String();
                    for(int j=0;j<data.length;j++){
                        linha = linha + data[j] + " ";
                    }

                    String sSuporte = line.substring(line.indexOf("(") + 1, line.indexOf(","));
                    String sConfianca = line.substring(line.indexOf(", ") + 2, line.indexOf(")"));

                    Float suporte = (Float.parseFloat(sSuporte) / 100);
                    Double confianca = (Double.parseDouble(sConfianca) / 100);
                    
         
                    linha = linha.trim();
                    if(suporte >= supMin){
                        
                        if(!itemsets.contains(linha)){
                            itemsets.add(linha);
                            hashSup.put(linha, suporte);
                            hashConf.put(linha, confianca);

                        }else{
                            double valor = hashConf.get(linha);
                            if(confianca<valor){
                                hashConf.remove(linha);
                                hashConf.put(linha, confianca);
                            }
                        }
                    }
                    //System.out.println("Linha: " + linha + " Cabeça: " + cabeca + " Corpo: " + corpo + " SupLinha: " + hashSup.get(linha) + " SupCabeca: " + hashSup.get(cabeca) + " SupCorpo: " + hashSup.get(corpo) + " Lift: " + hashLift.get(linha));
                }
                
                fileRules.close();
                
              
                //Gravando os itemsets no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                
                if(rank > 0){
                ArrayList<ItemsetMed> sortRules =  new ArrayList<ItemsetMed>();
                    Object[] items = hashConf.keySet().toArray();
                    for(int k=0; k<items.length; k++){
                        String itemset = (String)items[k];
                        if(!itemset.contains(" ")){
                            ItemsetMed rule = new ItemsetMed(itemset, 0.0);
                            sortRules.add(rule);
                        }else{
                            ItemsetMed rule = new ItemsetMed(itemset, hashConf.get(itemset));
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
                            Float valor = hashSup.get(linha);
                            valor = valor * 100;
                            for(int k=0;k<valor;k++){
                                    //arqOut.write(linha.replace(" ", "_") + " (" + valor + ")\n");
                                    arqOut.write(linha.replace(" ", "_") + "\n");
                            }
                        }
                    }else{
                        for(int j=0;j<rank;j++){
                            ItemsetMed rule = (ItemsetMed)sortedRules[j];
                            String linha = rule.itemset;
                            arqOut.write(linha.replace(" ", "_") + "\n");
                            //arqOut.write(linha + "\n");
                        }    
                    }    
                }else{
                    //Gravando os Unigramas
                    if(repetir == true){
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            String[] num_items = linha.split(" ");
                            if(num_items.length == 1){
                                Float valor = hashSup.get(linha);
                                valor = valor * 100;
                                for(int k=0;k<valor;k++){
                                        //arqOut.write(linha + " (" + valor + ")\n");
                                        arqOut.write(linha + "\n");
                                }
                            }

                        }
                    }else{
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            String[] num_items = linha.split(" ");
                            if(num_items.length == 1){     
                                 arqOut.write(linha + "\n");
                            }

                        }    
                    }

                    for(int j=0;j<itemsets.size();j++){
                        String linha = itemsets.get(j);
                        if(linha.contains(" ")){
                            Double confRegra = hashConf.get(linha);
                            confMed = confMed + confRegra;
                            kitemsets++;
                            //System.out.println("Itemset: " + linha + "  Confianca: " + confRegra);
                        }
                    }
                    //Gravando os N-gramas
                    System.out.println("Total: " + confMed);
                    confMed = (float)confMed/(float)kitemsets;            
                    System.out.println("Confianca Media: " + confMed + "--------------------------------------------------");

                    if(repetir == true){
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Float valRep = hashSup.get(linha);
                            valRep = valRep * 100;
                            Double confRegra = hashConf.get(linha);
                            if(confRegra<confMed){
                                continue;
                            }    

                            if(linha.contains(" ")){

                                for(int k=0;k<valRep;k++){
                                    //arqOut.write(linha + " (" + valor + ")\n");
                                    arqOut.write(linha.replace(" ", "_") + "\n");
                                }

                            }
                        }
                    }else{
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Double confRegra = hashConf.get(linha);
                            if(confRegra<confMed){
                                continue;
                            }
                            if(linha.contains(" ")){
                                //arqOut.write(linha + " (" + valor + ")\n");
                                arqOut.write(linha.replace(" ", "_") + "\n");
                            }
                        }    
                    }    
                }
                
                
                
                arqOut.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar os n-gramas.\nO programa será encerrado", "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
            System.out.println();
  
            
        }
    }
    
}
