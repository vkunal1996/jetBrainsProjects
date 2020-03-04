package encryptdecrypt;
import java.util.*;
import java.io.*;
interface EncryptionAlgorithm{
    public String encryption(String myString, int key, String algorithm);
    public String shiftEncryption(String myString,int key);
    public String unicodeEncryption(String myString,int key);
}
interface DecryptionAlgorithm{
    public String decryption(String myString,int key,String algorithm);
    public String shiftDecryption(String myString,int key);
    public String unicodeDecryption(String myString,int key);
}
class Encryption implements EncryptionAlgorithm,DecryptionAlgorithm{
    String decryptedString;
    String encryptedString;
    Map<Character,Character> mapping=new HashMap<>();
    ArrayList<Character> al=new ArrayList<>();
    public String encryption(String myString,int key,String alg){
        if(alg.equals("shift")){
            encryptedString=shiftEncryption(myString,key);
        }
        else{
            encryptedString=unicodeEncryption(myString,key);
        }
        return encryptedString;
    }
    public String decryption(String myString,int key, String alg){
        if(alg.equals("shift")){
            decryptedString=shiftDecryption(myString,key);
        }
        else{
            decryptedString=unicodeDecryption(myString,key);
        }
        return decryptedString;
    }
    public String shiftEncryption(String myString, int key){
        if(mapping.size()>0){
            mapping=new HashMap<>();
        }
        /*Mapping lower case letter with cypher*/
        for (Character c='a'; c<='z';c++){
            Character temp=c;
            for(int i=0;i<key;i++){
                temp++;
                if(temp>'z'){
                    temp='a';
                }
            }
            mapping.put(c,temp);

        }
        /*Mapping upper case letter to cypher*/
        for (Character c='A'; c<='Z';c++){
            Character temp=c;
            for(int i=0;i<key;i++){
                temp++;
                if(temp>'Z'){
                    temp='A';
                }
            }
            mapping.put(c,temp);

        }

        for(int i=0;i<myString.length();i++){
            if(!mapping.containsKey(myString.charAt(i))){
                al.add(myString.charAt(i));
            }
            else{
                al.add(mapping.get(myString.charAt(i)));
            }
        }
        return al.toString().substring(1,3*al.size()-1).replace(", ","");
    }
    public String unicodeEncryption(String myString,int key){
        encryptedString="";
        for (int i=0;i<myString.length();i++){
            Character temp=myString.charAt(i);
            for(int j=0;j<key;j++){
                temp++;
            }
            encryptedString+=temp;
        }
        return encryptedString;

    }
    public String unicodeDecryption(String myString, int key){
        decryptedString="";
        for (int i=0;i<myString.length();i++){
            Character temp=myString.charAt(i);
            for(int j=0;j<key;j++){
                temp--;
            }
            decryptedString+=temp;
        }
        return decryptedString;
    }

    public String shiftDecryption(String myString,int key){
        if(mapping.size()>0){
            mapping=new HashMap<>();
        }
        /*Mapping lower case letter with cypher*/
        for (Character c='z'; c>='a';c--){
            Character temp=c;
            for(int i=0;i<key;i++){
                temp--;
                if(temp<'a'){
                    temp='z';
                }
            }
            mapping.put(c,temp);

        }
        /*Mapping upper case letter to cypher*/
        for (Character c='Z'; c>='A';c--){
            Character temp=c;
            for(int i=0;i<key;i++){
                temp--;
                if(temp<'A'){
                    temp='Z';
                }
            }
            mapping.put(c,temp);

        }

        for(int i=0;i<myString.length();i++){
            if(!mapping.containsKey(myString.charAt(i))){
                al.add(myString.charAt(i));
            }
            else{
                al.add(mapping.get(myString.charAt(i)));
            }
        }
        return al.toString().substring(1,3*al.size()-1).replace(", ","");
    }
}
public class Main {
    public static Scanner input=new Scanner(System.in);
    public static File file,file1;
    public static String algorithm="shift";
    public static String mode="enc";
    public static int key=0;
    public static String data="";
    public static void main(String[] args) {
        Encryption e = new Encryption();

        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            al.add(args[i]);
        }
        if(al.contains("-alg")){
            algorithm=al.get(al.indexOf("-alg")+1);
        }
        if(al.contains("-mode")){
            mode=al.get(al.indexOf("-mode")+1);
        }
        if(al.contains("-key")){
            key=Integer.parseInt(al.get(al.indexOf("-key")+1));
        }

        if(al.contains("-data") && !al.contains("-in")){
            data=al.get(al.indexOf("-data")+1);
        }
        else if(al.contains("-data")&& al.contains("-in")){
            data=al.get(al.indexOf("-data")+1);
        }
        else if (al.contains("-in")&& !al.contains("-data")){
            try{
                file=new File(al.get(al.indexOf("-in")+1));
                if(!file.exists()){
                    System.out.println("Error");
                }
                else{
                    try(Scanner temp=new Scanner(file)){
                        data=temp.nextLine();
                    }catch(Exception err){
                        System.out.println("Error");
                    }
                }
            }catch(Exception err){
                System.out.println("Error");
            }
        }
        if(mode.equals("enc")){
            if(al.contains("-out")){
                try{
                    file1=new File(al.get(al.indexOf("-out")+1));
                    try(PrintWriter writer =new PrintWriter(file1)){
                        writer.println(e.encryption(data,key,algorithm));
                    }catch(Exception err){
                        System.out.println("Error");
                    }
                }catch(Exception err){
                    System.out.println("Error");
                }
            }
            else{
                System.out.println(e.encryption(data,key,algorithm));
            }
        }
        else if(mode.equals("dec")){
            System.out.println("hello");
            if(al.contains("-out")){
                try{
                    file1=new File(al.get(al.indexOf("-out")+1));
                    try(PrintWriter writer =new PrintWriter(file1)){
                        writer.println(e.decryption(data,key,algorithm));
                    }catch(Exception err){
                        System.out.println("Error");
                    }
                }catch(Exception err){
                    System.out.println("Error");
                }
            }
            else{
                System.out.println(e.decryption(data,key,algorithm));
            }
        }

    }
}
