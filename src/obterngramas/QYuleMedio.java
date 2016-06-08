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
public class QYuleMedio {

    public QYuleMedio(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, boolean repetir, Float yuleMin, int rank){
        
        ArrayList<String> itemsets;
        HashMap<String,Float> hashSup;
        HashMap<String,Float> hashConf;
        HashMap<String,Float> hashQYule;
   
        
        for(int i=0;i<filesIn.size();i++){
            File fileIn = filesIn.get(i);
            File fileOut = filesOut.get(i);
            System.out.println("File In:  " + fileIn.toString());
            System.out.println("File Out: " + fileOut.toString());
            itemsets = new ArrayList<String>();
            hashSup = new HashMap<String,Float>();
            hashQYule = new HashMap<String,Float>();
            
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
                    Float confianca = (Float.parseFloat(sConfianca) / 100);
                            
                    linha = linha.trim();
                    if(suporte >= supMin){
                        Float qyule = 0.0f;
                        try{
                            float numerador = suporte*(1 - hashSup.get(corpo) - hashSup.get(cabeca) + suporte);
                            numerador = numerador - ((hashSup.get(corpo) - suporte)*(hashSup.get(cabeca)-suporte));
                            float denominador = suporte*(1 - hashSup.get(corpo) - hashSup.get(cabeca) + suporte);
                            denominador = denominador + ((hashSup.get(corpo) - suporte)*(hashSup.get(cabeca)-suporte));
                            qyule = (float)numerador/(float)denominador;
                        }catch(Exception e){

                        }
                        if(!itemsets.contains(linha)){
                            itemsets.add(linha);
                            hashSup.put(linha, suporte);
                            
                            hashQYule.put(linha, qyule);
                        }else{ 
                            float valor = hashQYule.get(linha);
                            if(qyule<valor){
                                hashQYule.remove(linha);
                                hashQYule.put(linha, qyule);
                            }
                        }
                    }    
                }
                
                fileRules.close();
                
                float qYuleMed =0;
                float kitemsets =0;
                for(int j=0;j<itemsets.size();j++){
                    String linha = itemsets.get(j);
                    if(linha.contains(" ")){
                        Float confRegra = hashQYule.get(linha);
                        qYuleMed = qYuleMed + confRegra;
                        kitemsets++;
                        //System.out.println("Itemset: " + linha + "  Confianca: " + confRegra);
                    }
                }
                
                System.out.println("Total: " + qYuleMed);
                qYuleMed = (float)qYuleMed/(float)kitemsets;            
                System.out.println("Yule's Q Medio: " + qYuleMed + "--------------------------------------------------");
                
                //Gravando os unigramas no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                
                if(rank > 0){
                    ArrayList<ItemsetMed> sortRules =  new ArrayList<ItemsetMed>();
                    Object[] items = hashQYule.keySet().toArray();
                    for(int k=0; k<items.length; k++){
                        String itemset = (String)items[k];
                        if(!itemset.contains(" ")){
                            ItemsetMed rule = new ItemsetMed(itemset, 0.0);
                            sortRules.add(rule);
                        }else{
                            ItemsetMed rule = new ItemsetMed(itemset, (double)hashQYule.get(itemset));
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
                                arqOut.write(linha.replace(" ", "_") + "\n");
                                    //arqOut.write(linha.replace(" ", "_") + " (" + valor + ")\n");
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


                    //Gravando os n-gramas no arquivo de saida


                    if(repetir == true){
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Float valor = hashQYule.get(linha);
                            Float valRep = hashSup.get(linha);
                            valRep = valRep * 100;
                            if(linha.contains(" ")){
                                if(valor > qYuleMed){
                                    for(int k=0;k<valRep;k++){
                                        //arqOut.write(linha + " (" + valor + ")\n");
                                        arqOut.write(linha.replace(" ", "_") + "\n");
                                    }
                                }
                            }
                        }
                    }else{
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Float valor = hashQYule.get(linha);
                            if(linha.contains(" ")){
                                if(valor > qYuleMed){
                                    //arqOut.write(linha + " (" + valor + ")\n");
                                    arqOut.write(linha.replace(" ", "_") + "\n");
                                }
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
