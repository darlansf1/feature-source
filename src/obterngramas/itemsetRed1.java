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
public class itemsetRed1 {
    public void itemsetRed1Geral(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, boolean repetir, int rank){
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
                   
                //Selecionar os itemsets que serão gravados
                ArrayList<String> newItemsets = new ArrayList<String>();
                boolean[] adds = new boolean[itemsets.size()];
                for(int j=0;j<itemsets.size();j++){
                    String linha1 = itemsets.get(j);
                    float valor1 = hash.get(linha1);
                    for(int k=j+1;k<itemsets.size();k++){
                        String[] itens = linha1.split(" ");
                        String linha2 = itemsets.get(k);
                        boolean contem = true;
                        for(int l=0;l<itens.length;l++){
                            if(!linha2.contains(itens[l])){
                                contem = false;
                            }
                        }
                        
                        if(contem == true){
                            float valor2 = hash.get(linha2);
                            if(valor1 <= valor2){
                                //adds[j] = false;
                                adds[k] = true;
                             
                            }    
                        }
                        
                    }
                    
                }
                
                for(int j=0;j<adds.length;j++){
                    if(adds[j]==false){
                        newItemsets.add(new String(itemsets.get(j)));
                    }
                }
                
                
                //Gravando os itemsets no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                if(rank > 0){
                    ArrayList<ItemsetMed> sortRules =  new ArrayList<ItemsetMed>();
                    Object[] items = newItemsets.toArray();
                    for(int k=0; k<items.length; k++){
                        String itemset = (String)items[k];
                        if(!itemset.contains(" ")){
                            String[] numItems = itemset.split(" ");
                            ItemsetMed rule = new ItemsetMed(itemset, (double)numItems.length);
                            sortRules.add(rule);
                        }else{
                            ItemsetMed rule = new ItemsetMed(itemset, 1.0);
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
                                    arqOut.write(linha.replace(" ", "_") + " (" + valor + ")\n");
                                    //arqOut.write(linha + "\n");
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
                    if(repetir == true){
                        for(int j=0;j<newItemsets.size();j++){
                            String linha = newItemsets.get(j);
                            Float repet = hash.get(linha);
                            int valor = Math.round(repet);
                            for(int k=0;k<valor;k++){
                                arqOut.write(linha + "\n");
                            }
                        }
                    }else{
                        for(int j=0;j<newItemsets.size();j++){
                            String linha = newItemsets.get(j);
                            arqOut.write(linha + "\n");
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
    
    public void itemsetRed1Espec(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, boolean repetir){
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
                        
                //Selecionar os itemsets que serão gravados
                ArrayList<String> newItemsets = new ArrayList<String>();
                boolean[] adds = new boolean[itemsets.size()];
                for(int j=0;j<itemsets.size();j++){
                    String linha1 = itemsets.get(j);
                    float valor1 = hash.get(linha1);
                    for(int k=j+1;k<itemsets.size();k++){
                        String[] itens = linha1.split(" ");
                        String linha2 = itemsets.get(k);
                        boolean contem = true;
                        for(int l=0;l<itens.length;l++){
                            if(!linha2.contains(itens[l])){
                                contem = false;
                            }
                        }
                        
                        if(contem == true){
                            float valor2 = hash.get(linha2);
                            if(valor1 <= valor2){
                                adds[j] = true;
                                //adds[k] = true;
                             
                            }    
                        }
                        
                    }
                    
                }
                
                for(int j=0;j<adds.length;j++){
                    if(adds[j]==false){
                        newItemsets.add(new String(itemsets.get(j)));
                    }
                }
                
                
                //Gravando os itemsets no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                if(repetir == true){
                    for(int j=0;j<newItemsets.size();j++){
                        String linha = newItemsets.get(j);
                        Float repet = hash.get(linha);
                        int valor = Math.round(repet);
                        for(int k=0;k<valor;k++){
                            arqOut.write(linha.replace(" ", "_") + "\n");
                        }
                    }
                }else{
                    for(int j=0;j<newItemsets.size();j++){
                        String linha = newItemsets.get(j);
                        arqOut.write(linha.replace(" ", "_") + "\n");
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
