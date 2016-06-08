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
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import regras.estruturas.ItemsetMed;

/**
 *
 * @author ragero
 */
public class LiftNorm {

    public LiftNorm(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, boolean repetir, Float minLift, int rank){
        
        ArrayList<String> itemsets;
        HashMap<String,Float> hashSup;
        HashMap<String,Float> hashConf;
        HashMap<String,Float> hashLift;
        HashMap<String,Double> hashLift2;
        double rangeMax = 0, rangeMin = 0, range = 0, limDep = 0;
        supMin = 3000000f;
        
        for(int i=0;i<filesIn.size();i++){
            File fileIn = filesIn.get(i);
            File fileOut = filesOut.get(i);
            System.out.println("File In:  " + fileIn.toString());
            System.out.println("File Out: " + fileOut.toString());
            itemsets = new ArrayList<String>();
            hashSup = new HashMap<String,Float>();
            hashConf = new HashMap<String,Float>();
            hashLift = new HashMap<String,Float>();
            hashLift2 = new HashMap<String,Double>();
            
            
            
            //System.out.println("Lendo o Arquivo de Regras...");
            String line = "";
            try{
                RandomAccessFile fileRules = new RandomAccessFile(fileIn, "r");
                line = "";
                while((line = fileRules.readLine())!=null){
                    //System.out.print(".");
                    String[] data = null;
                    String cabeca = line.substring(0, line.indexOf(" <-"));
                    cabeca = cabeca.trim();
                    String corpo = line.substring(line.indexOf("<- ") + 3, line.indexOf("("));
                    corpo = corpo.trim();
                    
                    String linha = corpo;
                    corpo = "";
                    linha = linha.trim();
                    data = linha.split(" ");

                    Arrays.sort(data);
                    linha = new String();
                    for(int j=0;j<data.length;j++){
                        corpo = corpo + data[j] + " ";
                    }
                    corpo = corpo.trim();
                    
                    linha = cabeca + " " + corpo;
                    data = linha.split(" ");
                    
                  
                    Arrays.sort(data);
                    linha = new String();
                    for(int j=0;j<data.length;j++){
                        linha = linha + data[j] + " ";
                    }

                    String sSuporte = line.substring(line.indexOf("(") + 1, line.indexOf(","));
                    String sConfianca = line.substring(line.indexOf(", ") + 2, line.indexOf(")"));

                    Float suporte = (Float.parseFloat(sSuporte) / 100);
                    Float confianca = (Float.parseFloat(sConfianca) / 100);
                    
                    if(suporte < supMin){
                        supMin = suporte;
                    }
                    
                   //Limites da medida lift
                    linha = linha.trim();
                    if(suporte >= supMin){
                        if(!itemsets.contains(linha)){
                            itemsets.add(linha);
                            hashSup.put(linha, suporte);
                            
                            hashConf.put(linha, confianca);
                            float lift = 0.0f;
                            hashLift.put(linha, lift);
                        }
                    }    
                }
                
                fileRules.close();
                
                
                //Cálculo do lift normalizado
                                
                rangeMin = (4*supMin)/(Math.pow((1 + supMin), 2));
                rangeMax = (1/supMin);
                range = rangeMax - rangeMin;
                limDep = (1-rangeMin)/(range);
                System.out.println("Valor Mínimo: " + rangeMin);
                System.out.println("Valor Máximo: " + rangeMax);
                System.out.println("Range: " + range);
                System.out.println("Limiar de Dependência: " + limDep);
                
                Set<String> chaves = hashLift.keySet();
                Iterator it = chaves.iterator();
                while(it.hasNext()){
                    String chave = (String)it.next();
                    float lift = 0.0f;
                    //Loop para calcular todos os lifts dos itens de uma regra
                    try{
                        String chaveCabeca = "";
                        String chaveCorpo = "";
                        String[] items = chave.split(" ");
                        if(items.length==1){
                            continue;
                        }
                        
                        float minLiftMin = 3000000f;
                        
                        for(int j=0;j<items.length;j++){
                            chaveCabeca = items[j];
                            int ind = chave.indexOf(chaveCabeca);
                            chaveCorpo = chave.substring(0,ind);
                            chaveCorpo = chaveCorpo + chave.substring(ind + chaveCabeca.length(), chave.length());
                            chaveCorpo = chaveCorpo.trim();
                            chaveCorpo = chaveCorpo.replace("  ", " ");
                            lift = hashSup.get(chave) / (hashSup.get(chaveCabeca) * hashSup.get(chaveCorpo));
                            lift = (float)(lift - limDep)/(float)(range);
                            if(lift < minLiftMin){
                                minLiftMin = lift;
                            }
                        }
                        hashLift2.put(chave, (double)lift);
                    }catch(Exception e){

                    }
                }
                
                
                
                //Gravando os itemsets frequentes
                FileWriter arqOut = new FileWriter(fileOut);
                
                if(rank > 0){
                    ArrayList<ItemsetMed> sortRules =  new ArrayList<ItemsetMed>();
                    Object[] items = hashLift2.keySet().toArray();
                    for(int k=0; k<items.length; k++){
                        String itemset = (String)items[k];
                        if(!itemset.contains(" ")){
                            ItemsetMed rule = new ItemsetMed(itemset, 0.0);
                            sortRules.add(rule);
                        }else{
                            ItemsetMed rule = new ItemsetMed(itemset, hashLift2.get(itemset));
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


                    //Gravando os n-gramas
                    //FileWriter arqOut = new FileWriter(fileOut);

                    if(repetir == true){
                        for(int j=0;j<itemsets.size();j++){
                            String linha = itemsets.get(j);
                            Double valor = hashLift2.get(linha);
                            Float valRep = hashSup.get(linha);
                            valRep = valRep * 100;
                            if(linha.contains(" ")){
                                if((valor > 0)&&(valor >= minLift)){
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
                            Double valor = hashLift2.get(linha);
                            if(valor == null){
                                continue;
                            }
                            String[] numItens = linha.split(" ");
                            //Limitar número de itens
                            if(numItens.length != 2 ){
                                continue;
                            }
                            if(linha.contains(" ")){
                                if((valor > 0)&(valor >= minLift)){
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
                System.out.println("Linha: " + line);
                System.exit(1);
            }
            System.out.println();
  
            
        }
    }
    
}
