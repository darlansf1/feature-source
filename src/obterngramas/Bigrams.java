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
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ragero
 */
public class Bigrams {
    public void Sup(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin){
        
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
                    
                    if(data.length != 2){
                        continue;
                    }
                    
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
                
                for(int j=0;j<itemsets.size();j++){
                    String linha = itemsets.get(j);
                    Float valor = hash.get(linha);
                    arqOut.write(linha + "(" + valor + ")\n");
                }
                arqOut.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar os n-gramas.\nO programa será encerrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println();
  
            
        }
    }
    
    public void Lift(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin){
        
        ArrayList<String> itemsets;
        HashMap<String,Float> hashSup;
        HashMap<String,Float> hashConf;
        HashMap<String,Float> hashLift;
        for(int i=0;i<filesIn.size();i++){
            File fileIn = filesIn.get(i);
            File fileOut = filesOut.get(i);
            System.out.println("File In:  " + fileIn.toString());
            System.out.println("File Out: " + fileOut.toString());
            itemsets = new ArrayList<String>();
            hashSup = new HashMap<String,Float>();
            hashConf = new HashMap<String,Float>();
            hashLift = new HashMap<String,Float>();
            
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
                    
                    String linha = cabeca + " " + corpo;
                    data = linha.split(" ");
                    
                    if(data.length > 2){
                        continue;
                    }
                    
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
                    
                        if(!itemsets.contains(linha)){
                            itemsets.add(linha);
                            hashSup.put(linha, suporte);
                            hashConf.put(linha, confianca);
                            Float lift = 0.0f;
                            try{
                                lift = hashSup.get(linha) / (hashSup.get(cabeca) * hashSup.get(corpo));
                            }catch(Exception e){
                                
                            }
                            hashLift.put(linha, lift);
                        }
                    


                    //System.out.print("Regra -- Cabeça: " + regra.cabeca + " Corpo: " + regra.corpo + " Suporte: " + suporte + " Confiaça: " + confianca + "\n");
                }
                
                fileRules.close();
                        
                //Gravando os itemsets no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                
                for(int j=0;j<itemsets.size();j++){
                    String linha = itemsets.get(j);
                    Float valor = hashLift.get(linha);
                    if(linha.contains(" ")){
                        if(valor >= 1){
                            arqOut.write(linha + " (" + valor + ")\n");
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
    
    
    public void ChiSquared(ArrayList<File> filesIn, ArrayList<File> filesOut, Float supMin, String dirTrans){
        
        ArrayList<String> itemsets;
        HashMap<String,Float> hashSup;
        HashMap<String,Float> hashConf;
        HashMap<String,Float> hashLift;
        HashMap<String,Float> hashChi;
        HashMap<String,Integer> hashNumTrans;
        for(int i=0;i<filesIn.size();i++){
            File fileIn = filesIn.get(i);
            File fileOut = filesOut.get(i);
            System.out.println("File In:  " + fileIn.toString());
            System.out.println("File Out: " + fileOut.toString());
            itemsets = new ArrayList<String>();
            hashSup = new HashMap<String,Float>();
            hashConf = new HashMap<String,Float>();
            hashLift = new HashMap<String,Float>();
            hashChi = new HashMap<String,Float>();
            hashNumTrans = new HashMap<String,Integer>();
            
            //System.out.println("Lendo o Arquivo de Regras...");
            try{
                //Obter número de transações
                int numTrans = obterNumTrans(fileIn, dirTrans);
                hashNumTrans.put(fileOut.toString(), numTrans);
                RandomAccessFile fileRules = new RandomAccessFile(fileIn, "r");
                String line = "";
                while((line = fileRules.readLine())!=null){
                    //System.out.print(".");
                    String[] data = null;
                    String cabeca = line.substring(0, line.indexOf(" <-"));
                    cabeca = cabeca.trim();
                    String corpo = line.substring(line.indexOf("<- ") + 3, line.indexOf("("));
                    corpo = corpo.trim();
                    
                    String linha = cabeca + " " + corpo;
                    data = linha.split(" ");
                    
                    if(data.length > 2){
                        continue;
                    }
                    
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
                    
                        if(!itemsets.contains(linha)){
                            itemsets.add(linha);
                            hashSup.put(linha, suporte);
                            hashConf.put(linha, confianca);
                            Float lift = 0.0f;
                            try{
                                lift = hashSup.get(linha) / (hashSup.get(cabeca) * hashSup.get(corpo));
                            }catch(Exception e){
                                
                            }
                            hashLift.put(linha, lift);
                            Float chi = 0.0f;
                            try{
                                chi = ((lift - 1)*(lift -1))*((hashSup.get(corpo)*hashSup.get(cabeca))/((1-hashSup.get(corpo))*(1-hashSup.get(cabeca))));
                            }catch(Exception e){
                                
                            }
                            hashChi.put(linha, chi);
                        }
                    

                    //System.out.print("Regra -- Cabeça: " + regra.cabeca + " Corpo: " + regra.corpo + " Suporte: " + suporte + " Confiaça: " + confianca + "\n");
                }
                
                fileRules.close();
                        
                //Gravando os itemsets no arquivo de saida
                FileWriter arqOut = new FileWriter(fileOut);
                
                for(int j=0;j<itemsets.size();j++){
                    String linha = itemsets.get(j);
                    Float valor = hashChi.get(linha) * hashNumTrans.get(fileOut.toString());
                    
                    if(linha.contains(" ")){
                        if(valor >= 6.635){
                            arqOut.write(linha + " (" + valor + ")\n");
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
    
    public int obterNumTrans(File arqIn, String dirTrans){
        
        String arqEnt = arqIn.toString().replace("\\", "/");
        dirTrans = dirTrans.replace("\\", "/");
        String caminho = "";
        File arquivo;
        boolean ok = false;
        while(ok == false){
            int ind = arqEnt.indexOf("/");
            if(ind < 0){
                break;
            }
            arqEnt = arqEnt.substring(ind +1, arqEnt.length());
            caminho = dirTrans + "/" + arqEnt;
            arquivo = new File(caminho);
            if(arquivo.isFile()){
                ok = true;
                break;
            }
        }
        
        if(ok == false){
            JOptionPane.showMessageDialog(null, "Não foi encontrados arquivos de transações correspondentes\n. O programa será finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }   
        
        int numTrans = 0;
        try{
            RandomAccessFile arqTrans = new RandomAccessFile(caminho,"r");
            String line = "";
            while((line = arqTrans.readLine())!=null){
                numTrans++;
            }
            arqTrans.close();
        }catch(Exception e){
            
        }
        
        return numTrans;
    }
}
