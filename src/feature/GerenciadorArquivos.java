/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package feature;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class GerenciadorArquivos {

    public static boolean ListaArquivos(File dirIn, File dirOut, ArrayList<File> filesIn, ArrayList<File> filesOut, File dirBase){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            //System.out.println("File: " + files[i]);
            if(files[i].isDirectory()){
                File dirNameOut = new File(dirOut.toString() + files[i].toString().substring(dirBase.toString().length(), files[i].toString().length()));
                if(!dirNameOut.exists()){
                    boolean criou = dirNameOut.mkdir();
                    if(criou == false){
                        return false;
                    }
                }
                ListaArquivos(files[i], dirOut, filesIn, filesOut, dirBase);
            }
            if(!files[i].getName().endsWith("txt")){
                continue;
            }
            String fileName = files[i].toString();
            String fileOut = dirOut.toString() + fileName.substring(dirBase.toString().length(), fileName.length());
            filesIn.add(new File(fileName));
            filesOut.add(new File(fileOut));
        }
        return true;
    }
    
    public static boolean ListaArquivosEspecial(File dirIn, File dirOut, ArrayList<File> filesIn, ArrayList<String> filesOut, File dirBase){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            //System.out.println("File: " + files[i]);
            if(files[i].isDirectory()){
                File dirNameOut = new File(dirOut.toString() + files[i].toString().substring(dirBase.toString().length(), files[i].toString().length()));
                if(!dirNameOut.exists()){
                    boolean criou = dirNameOut.mkdir();
                    if(criou == false){
                        return false;
                    }
                }
                ListaArquivosEspecial(files[i], dirOut, filesIn, filesOut, dirBase);
            }
            if(!files[i].getName().endsWith("txt")){
                continue;
            }
            String fileName = files[i].toString();
            String fileOut = dirOut.toString() + fileName.substring(dirBase.toString().length(), fileName.length());
            filesIn.add(new File(fileName));
            filesOut.add(fileOut);
        }
        return true;
    }
    
    public static boolean CriarDirsTT(File dirIn, File dirOut, ArrayList<File> filesIn, ArrayList<File> filesOut, File dirBase){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()){
                File dirNameOut = new File(dirOut.toString() + "/TEST/" +files[i].toString().substring(dirBase.toString().length(), files[i].toString().length()));
                if(!dirNameOut.exists()){
                    boolean criou = dirNameOut.mkdir();
                    if(criou == false){
                        return false;
                    }
                }
                dirNameOut = new File(dirOut.toString() + "/TRAIN/" +files[i].toString().substring(dirBase.toString().length(), files[i].toString().length()));
                if(!dirNameOut.exists()){
                    boolean criou = dirNameOut.mkdir();
                    if(criou == false){
                        return false;
                    }
                }
                CriarDirsTT(files[i], dirOut, filesIn, filesOut, dirBase);
            }
        }
        return true;
    }
    
    public static boolean ListaArquivosTreino(File dirIn, File dirOut, ArrayList<File> filesIn, ArrayList<File> filesOut, File dirBase){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            //System.out.println("File: " + files[i]);
            if(!files[i].isDirectory()){
                String fileName = files[i].toString();
                if(files[i].getName().toLowerCase().contains("train")){
                    String fileOut = dirOut.toString() + fileName.substring(dirBase.toString().length(), fileName.length());
                    filesIn.add(new File(fileName));
                    filesOut.add(new File(fileOut));
                }    
            }else{
                ListaArquivosTreino(files[i], dirOut, filesIn, filesOut, dirBase);
            }
            
            
        }
        return true;
    }
    
    public static boolean ListaArquivosTeste(File dirIn, File dirOut, ArrayList<File> filesIn, ArrayList<File> filesOut, File dirBase){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            //System.out.println("File: " + files[i]);
            if(!files[i].isDirectory()){
                String fileName = files[i].toString();
                if(files[i].getName().toLowerCase().contains("test")){
                    String fileOut = dirOut.toString() + fileName.substring(dirBase.toString().length(), fileName.length());
                    filesIn.add(new File(fileName));
                    filesOut.add(new File(fileOut));
                }
            }else{
                ListaArquivosTeste(files[i], dirOut, filesIn, filesOut, dirBase);
            }
            
        }
        return true;
    }
    
    
    public static void ListaClasses(File dirIn, ArrayList<String> classes){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()){
                String dir = files[i].toString();
                dir = dir.replace('\\', '/');
                if(dir.endsWith("/")){
                    dir = dir.substring(0,dir.length()-1);
                }
                int ind = dir.lastIndexOf("/");
                String classe = new String(dir.substring(ind + 1, dir.length()));
                classes.add(classe);
            }
        }
        
    }
    
    public static boolean ListaDirs(File dirIn, File dirOut, ArrayList<String> dirs, File dirBase ){
        File[] files = dirIn.listFiles();
        for(int i=0;i<files.length;i++){
            //System.out.println("File: " + files[i]);
            if(files[i].isDirectory()){
                //File dirNameOut = new File(dirOut.toString() + files[i].toString().substring(dirBase.toString().length(), files[i].toString().length()));
                //if(!dirNameOut.exists()){
                //    boolean criou = dirNameOut.mkdir();
                //    if(criou == false){
                //        return false;
                //   }
                //}
                ListaDirs(files[i], dirOut, dirs, dirBase);
            }
            if(files[i].isFile()){
                if(!dirs.contains(dirIn.toString())){
                    dirs.add(dirIn.toString());
                }
            }
            
        }
        return true;
    }
}
