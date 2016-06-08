/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package processos;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ragero
 */
public class SupAutomatico {
    
    public static double obterSupAutSent(File arqIn){
        double sup = 0.0;
        Integer numTrans=0;
        Integer numItensTrans=0;
        RandomAccessFile arq = null;
        try{
            arq = new RandomAccessFile(arqIn, "r");
            String linha = new String("");
            HashMap<String, Integer> termFreq = new HashMap<String,Integer>();
            while((linha = arq.readLine())!=null){
                numTrans++;
                String[] itens = linha.split(" ");
                numItensTrans = numItensTrans + itens.length;
                for(int i=0;i<itens.length;i++){
                    String item = itens[i];
                    if(item.length()<=1){
                        continue;
                    }
                    if(termFreq.containsKey(item)){
                        Integer freq = termFreq.get(item);
                        termFreq.remove(item);
                        freq++;
                        termFreq.put(item, freq);
                    }else{
                        Integer freq = 1;
                        termFreq.put(item, freq);
                    }
                }
            }
            
            double numMedioItensTrans = (float)numItensTrans/(double)numTrans;
            double media = 0.0;
            double devpad = 0.0;
            Integer numTermos = termFreq.size();
            Integer freqGeral = 0;
                  
            Object[] chaves = termFreq.keySet().toArray();
            
            int[] values = new int[termFreq.keySet().size()];
    
            for(int i=0; i<chaves.length;i++){
                String chave = chaves[i].toString();
                Integer freq = termFreq.get(chave);
                values[i] = freq;
                freqGeral = freqGeral + freq;
            }
            
            Arrays.sort(values);
            //Gravando as Frequências em um arquivo
            /*try{
                FileWriter arqFreq = new FileWriter("/exp/ragero/experimentos_mest/testes/teste2/teste-" + arqIn.getName() + ".txt");
                
                arqFreq.write("\n\n\n" + arqIn.toString() + "\n");
                for(int j=0;j<values.length;j++){
                     arqFreq.write(j + ";" + values[j] + "\n");
                }
                arqFreq.close();
            }catch(Exception e){
                System.out.println("Problem!!!!!!!!");
                e.printStackTrace();
                
            }*/
            
            int indiceMediana = Math.round(chaves.length/2);
            float mediana = (float)values[indiceMediana] / (float)numTrans;
            
            media = (double)freqGeral/(double)numTermos;
            //Cálculo da Média Diferente 2
            double media2 = (double)media/(double)numTrans;
                        //Cálculo da Média Diferente 3
            double media3 = (double)freqGeral/(double)numTrans;
            
            int min = 100000, max = 0;
            for(int i=0; i<chaves.length;i++){
                String chave = chaves[i].toString();
                Integer freq = termFreq.get(chave);
                devpad = devpad + (Math.pow(devpad - media, 2)/(double)numTermos);
                if(freq<min){
                    min = freq;
                }else{
                    if(freq>max){
                        max = freq;
                    }
                }
            }
            
            //Verificando a Tendência dos valores de suporte
            int maior=0,menor=0;
            for(int i=0; i<chaves.length;i++){
                String chave = chaves[i].toString();
                Integer freq = termFreq.get(chave);
                if(freq>media){
                    maior++;
                }else{
                    menor++;
                }
            }
            
            
            double variancia = devpad/numTrans;
            devpad = Math.sqrt(variancia);
            
            
            System.out.println("Frequencia Geral: " + freqGeral);
            System.out.println("Numero de Termos: " + numTermos);
            System.out.println("Numero de Transacoes: " + numTrans);
            //System.out.println("Numero de Media: " + media);
            System.out.println("Suporte Medio: " + media2);
            //System.out.println("Numero de Media3: " + media3);
            //System.out.println("Numero de Desvio Padrao: " + devpad);
            //System.out.println("Mediana: " + mediana);
            //System.out.println("Maior que a Media: " + maior);
            //System.out.println("Menor que a Media: " + menor);
            //System.out.println("Maior ocorrência: " + max);
            //System.out.println("Menor ocorrência: " + min);
            
                        
            
            
            //Fórmula 1
            //sup = ((double)numTermos/(double)numTrans)*((double)media/(double)devpad);
            //Fórmula 2
            //sup = ((double)numTermos/(double)numTrans)* Math.log((double)media - (double)devpad);
            //Fórmula 3
            
            //sup = ((double)Math.sqrt(numMedioItensTrans)/(double)numTrans) * (media) * (1 - ((double)(menor - maior)/(double)numTermos));
            //sup = (double)(numMedioItensTrans) * (media) * (1 - ((double)(menor - maior)/(double)numTermos));
            //sup = sup;
            sup = media2*100;
            //sup = (media2 * 100) * (1 + ((double)numMedioItensTrans /(double) numTrans)) * (1 - Math.pow(((double)(menor - maior)/(double)numTermos),2));
            
            //sup = ((double)numMedioItensTrans/(double)numTermos) * ((double)media/(double)numTrans) * (1 - ((double)(menor - maior)/(double)numTermos));
            //sup = ((double)(numMedioItensTrans * media)/(double)(Math.log(numTrans) * numTermos));
            
            //Formula 4
            //sup = media * (1 - ((double)(menor - maior)/(double)numTermos)) * Math.log(numMedioItensTrans);
            //sup = (media - devpad)/(double)(numTrans*(1-(devpad/media)));
            //System.out.println("Sup1: " + sup);
            //sup = sup * (freqGeral/(double)numTrans);
            //System.out.println("Sup2: " + sup);
            //sup = sup * 25;
            //System.out.println("Suporte: " + sup);
            
            arq.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de regras\n" + arqIn.toString() + ".\nNão serão estraídas regras para este arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            try{
                arq.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }
            return 0.0;
        }
        
        return sup;
    }
    
    
    public static double obterSutAutPar(File arqIn){
        double sup = 0.0;
        Integer numTrans=0;
        try{
            RandomAccessFile arq = new RandomAccessFile(arqIn, "r");
            String linha = new String("");
            HashMap<String, Integer> termFreq = new HashMap<String,Integer>();
            while((linha = arq.readLine())!=null){
                numTrans++;
                String[] itens = linha.split(" ");
                ArrayList<String> contabilizados = new ArrayList<String>();
                for(int i=0;i<itens.length;i++){
                    String item = itens[i];
                    if(contabilizados.contains(item)){
                        continue;
                    }else{
                        contabilizados.add(item);
                    }
                    if(item.length()<=1){
                        continue;
                    }
                    if(termFreq.containsKey(item)){
                        Integer freq = termFreq.get(item);
                        termFreq.remove(item);
                        freq++;
                        termFreq.put(item, freq);
                    }else{
                        Integer freq = 1;
                        termFreq.put(item, freq);
                    }
                }
            }
            
            double media = 0.0;
            double devpad = 0.0;
            Integer numTermos = termFreq.size();
            Integer freqGeral = 0;
            
            Object[] chaves = termFreq.keySet().toArray();
            for(int i=0; i<chaves.length;i++){
                String chave = chaves[i].toString();
                Integer freq = termFreq.get(chave);
                freqGeral = freqGeral + freq;
            }
            
            media = (double)freqGeral/(double)numTermos;
            for(int i=0; i<chaves.length;i++){
                String chave = chaves[i].toString();
                Integer freq = termFreq.get(chave);
                devpad = devpad + (Math.pow(devpad - media, 2)/(double)numTermos);
            }
            
            devpad = Math.sqrt(devpad);
            
            
            System.out.println("Frequencia Geral: " + freqGeral);
            System.out.println("Numero de Termos: " + numTermos);
            System.out.println("Numero de Transacoes: " + numTrans);
            //System.out.println("Suporte Medio: " + media);
            //System.out.println("Numero de Desvio Padrao: " + devpad);
            
            //Fórmula 1:
            sup = ((double)numTermos/(double)numTrans) * (media/devpad);
            
            
            
            
            System.out.println("Suporte: " + sup);
            
            arq.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de regras\n" + arqIn.toString() + ".\nNão serão estraídas regras para este arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
        
        return sup;
    }
    
}
