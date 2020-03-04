import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
public class Main {
 
    public static String choice;
    public static int number;
    public static Set <String> cardset=new HashSet<>();
    public static Set<String> definitionset=new HashSet<>();
    public static Map <String,String> m=new LinkedHashMap<>();
    public static Map<String,Integer> errorCounter=new LinkedHashMap<>();
    public static String card,definition;
    public static int error;
    public static File file;
    public static ArrayList<String> log=new ArrayList<>();
    public static String filetoImport;
    public static String filetoExport;
    public static int tempSize;
    public static Scanner input=new Scanner(System.in);
 
    public static <K,V> K getKey(Map<K,V>map,V value){
        for(Map.Entry<K,V> entry:map.entrySet()){
            if(value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
    public static ArrayList<String> getMaxkey(Map<String,Integer>map,Integer value){
        ArrayList<String> al=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            if(value.equals(entry.getValue())){
                al.add(entry.getKey());
            }
        }
        return al;
    }
    /**Adding card to the Hashmap*/
 
    public static void addcard(){
        error=0;
        System.out.println("The card:");
        log.add("The card:");
 
        do{
            card=input.nextLine();
            log.add(card);
            if(cardset.contains(card)){
                System.out.println("The card "+"\""+card+"\" already exists. Try again:");
                log.add("The card "+"\""+card+"\" already exists. Try again:");
                return;
            }
            else{
                cardset.add(card);
                break;
            }
        }
        while(true);
 
        System.out.println("The definition of the card:");
        log.add("The definition of the card:");
 
        do{
            definition=input.nextLine();
            log.add(definition);
            if(definitionset.contains(definition)){
                System.out.println("The definition "+"\""+definition+"\" already exists. Try again:");
                log.add("The definition "+"\""+definition+"\" already exists. Try again:");
                return;
            }
            else{
                definitionset.add(definition);
                break;
            }
        }
        while(true);
 
        m.put(card,definition);
        errorCounter.put(card,error);
        System.out.println("The pair (\""+card+"\" : \""+definition+"\") has been added.");
        log.add("The pair (\""+card+"\" : \""+definition+"\") has been added.");
 
    }
 
    public static void remove(){
        String answer=null;
        System.out.println("The Card:");
        log.add("The Card:");
        answer=input.nextLine();
        log.add(answer);
        if(m.containsKey(answer)==true){
            cardset.remove(answer);
            definitionset.remove(m.get(answer));
            m.remove(answer);
            errorCounter.remove(card);
 
 
            System.out.println("The card has been removed.");
            log.add("The card has been removed.");
        }
        else{
            System.out.println("Can't remove \""+answer+"\": there is no such card.");
            log.add("Can't remove \""+answer+"\": there is no such card.");
        }
    }
 
    public static void importFile(){
        String filename=filetoImport;
        if(filename==null) {
            System.out.println("File name:");
            log.add("File name:");
            filename = input.nextLine();
        }
        log.add(filename);
        file =new File(filename);
        if(!file.exists()){
            System.out.println("File not found");
            log.add("File not found");
        }
        else{
            try(Scanner temp=new Scanner(file)){
                tempSize=0;
                while(temp.hasNext()){
                    tempSize++;
                    String card=temp.nextLine();
                    String definition=temp.nextLine();
                    int error=Integer.parseInt(temp.nextLine());
                    if(m.containsKey(card)){
                        m.replace(card, m.get(card), definition);
                        errorCounter.replace(card, errorCounter.get(card), error);
                    }
                    else{
                        m.put(card,definition);
                        errorCounter.put(card,error);
                    }
                }
 
                System.out.println(tempSize+" cards have been loaded");
                log.add(tempSize +" cards have been loaded");
 
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
 
        }
    }
    public static void exportFile(){
        String filename1=filetoExport;
        if(filename1==null) {
            System.out.println("File name:");
            log.add("File name:");
            filename1 = input.nextLine();
        }
        log.add(filename1);
        file =new File(filename1);
        try(PrintWriter writer=new PrintWriter(file)){
            for (String key : m.keySet()){
                writer.println(key);
                writer.println(m.get(key));
                writer.println(errorCounter.get(key));
            }
            writer.flush();
            System.out.println(m.size()+" cards have been saved");
            log.add(m.size() +" cards have been saved");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
 
    public static void ask(int numberOfask){
 
        String answer=null;
 
 
        for (String key : m.keySet()){
            if(numberOfask==0){
                return;
            }
            numberOfask--;
            System.out.println("Print the definition of "+"\""+key+"\"");
            log.add("Print the definition of "+"\""+key+"\"");
            answer=input.nextLine();
            if(m.get(key).equals(answer)){
                System.out.println("Correct answer.");
                log.add("Correct answer:");
            }
            else{
                String temp=getKey(m,answer);
                int tempError=0;
                tempError=errorCounter.get(key);
                tempError+=1;
                errorCounter.replace(key, errorCounter.get(key), tempError);
 
 
                try{
                    if(!temp.equals(null)){
 
                        System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
                        log.add("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
                    }
                }
                catch(Exception e){
 
                    System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\"");
                    log.add("Wrong answer. The Correct one is "+"\""+m.get(key)+"\"");
                }
            }
        }
 
        if(numberOfask>0){
            while(numberOfask>0){
                List<String> keysAsArray = new ArrayList<String>(m.keySet());
                Random r = new Random();
                String key=keysAsArray.get(r.nextInt(keysAsArray.size()));
                System.out.println("Print the definition of "+"\""+key+"\"");
                log.add("Print the definition of "+"\""+key+"\"");
                answer=input.nextLine();
                if(m.get(key).equals(answer)){
                    System.out.println("Correct answer.");
                    log.add("Correct answer.");
                }
                else{
                    String temp=getKey(m,answer);
                    int tempError=errorCounter.get(key);
                    tempError+=1;
                    errorCounter.replace(key, errorCounter.get(key), tempError);
                    try{
                        if(!temp.equals(null)){
                            System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
                            log.add("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\"");
                        log.add("Wrong answer. The Correct one is "+"\""+m.get(key)+"\"");
                    }
                }
                numberOfask--;
 
            }
 
        }
    }
    public static void findhardest(){
        if(m.size()==0){
            System.out.println("There are no cards with errors");
            log.add("There are no cards with errors");
        }
        else{
            Map.Entry<String, Integer> maxEntry = null;
 
            for (Map.Entry<String, Integer> entry : errorCounter.entrySet()){
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                    maxEntry = entry;
                }
            }
            if(maxEntry.getValue()==0){
                System.out.println("There are no cards with errors");
                log.add("There are no cards with errors");
                return;
            }
            ArrayList<String> al=new ArrayList<>();
            al=getMaxkey(errorCounter,maxEntry.getValue());
            if(al.size()==1){
                System.out.println("The hardest card is \""+al.get(0)+"\". You have "+maxEntry.getValue()+" errors answering it.");
                log.add("The hardest card is \""+al.get(0)+"\". You have "+maxEntry.getValue()+" errors answering it.");
            }
            else{
                System.out.print("The hardest cards are ");
                log.add("The hardest cards are ");
                String tempCards="";
                for(String cards:al){
                    tempCards=tempCards+"\""+cards+"\", ";
                }
                System.out.println(tempCards+" You have "+maxEntry.getValue()+" errors answering it.");
                log.add(tempCards+" You have "+maxEntry.getValue()+" errors answering it.");
            }
        }
 
    }
    public static void resetstats(){
        for (String key:errorCounter.keySet()){
            errorCounter.replace(key,errorCounter.get(key),0);
        }
        System.out.println("Cards statistics has been reset");
        log.add("Cards statistics has been reset");
    }
    public static void writeLog(){
        System.out.println("File name:");
        log.add("File name:");
        String filename=input.nextLine();
        log.add(filename);
        file =new File(filename);
        try(PrintWriter writer=new PrintWriter(file)){
            for (String l : log){
                writer.println(l);
 
            }
            writer.flush();
            System.out.println("The log has been saved");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        int numberOfArguments=args.length;
 
        if(numberOfArguments==2){
            if(args[0].equals("-import")){
                filetoImport=args[1];
            }
            else{
                filetoExport=args[1];
            }
        }
        if(numberOfArguments==4){
            if(args[0].equals("-import")){
                filetoImport=args[1];
                filetoExport=args[3];
            }
            else{
                filetoExport=args[1];
                filetoImport=args[3];
            }
        }
        if(filetoImport!=null){
            importFile();
        }
        do{
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            choice=input.nextLine();
            switch(choice){
                case "add":
                    log.add("add");
                    addcard();
                    break;
                case "remove":
                    log.add("remove");
                    remove();
                    break;
                case "import":
                    log.add("import");
                    importFile();
                    break;
                case "export":
                    log.add("export");
                    exportFile();
                    break;
                case "ask":
                    log.add("ask");
                    System.out.println("How many times to ask?");
                    log.add("How many times to ask?");
                    int numberOfask=input.nextInt();
                    input.nextLine();
                    log.add(Integer.toString(numberOfask));
                    ask(numberOfask);
                    break;
                case "log":
                    log.add("log");
                    writeLog();
                    break;
                case "hardest card":
                    log.add("hardest card");
                    findhardest();
                    break;
                case "reset stats":
                    log.add("reset stats");
                    resetstats();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    if(filetoExport!=null){
                        exportFile();
                    }
 
                    break;
            }
 
        }while(!choice.equals("exit"));
 
    }
}