import java.util.*;
import java.io.File;
import java.io.PrintWriter;
public class Main {

    public static String choice;
    public static int number;
    public static Set <String> cardset=new HashSet<>();
    public static Set<String> definitionset=new HashSet<>();
    public static Map <String,String> m=new LinkedHashMap<>();
    public static String card,definition;
    public static File file;
    
    public static Scanner input=new Scanner(System.in);

    public static <K,V> K getKey(Map<K,V>map,V value){
        for(Map.Entry<K,V> entry:map.entrySet()){
            if(value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
    /**Adding card to the Hashmap*/

    public static void addcard(){
        System.out.println("The card:");

           do{
            card=input.nextLine();
           if(cardset.contains(card)){
               System.out.println("The card "+"\""+card+"\" already exists. Try again:");
               return;
           }
           else{
               cardset.add(card);
               break;
           }
           }
           while(true);

           System.out.println("The definition of the card:");

           do{
            definition=input.nextLine();
           if(definitionset.contains(definition)){
                System.out.println("The definition "+"\""+definition+"\" already exists. Try again:");
                return;
           }
           else{
               definitionset.add(definition);
               break;
           }
           }
           while(true);
           m.put(card,definition);
           System.out.println("The pair (\""+card+"\" : \""+definition+"\") has been added.");
        
    }

    public static void remove(){
        String answer=null;
        System.out.println("The Card:");
        answer=input.nextLine();
        if(m.containsKey(answer)==true){
            cardset.remove(answer);
            definitionset.remove(m.get(answer));
            m.remove(answer);
            
    
            System.out.println("The card has been removed.");
        } 
        else{
            System.out.println("Can't remove \""+answer+"\": there is no such card.");
        }
    }

    public static void importFile(){
        System.out.println("File name:");
        String filename=input.nextLine();
        file =new File(filename);
        if(!file.exists()){
            System.out.println("File not found");
        }
        else{
            try(Scanner temp=new Scanner(file)){
                int tempSize=0;
                while(temp.hasNext()){
                    tempSize++;
                    String card=temp.nextLine();
                    
                    String definition=temp.nextLine();
                    if(m.containsKey(card)){
                        m.replace(card, m.get(card), definition);
                    }
                    else{
                     m.put(card,definition);
                    }
                }
                
            System.out.println(tempSize+" cards have been loaded");

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }
    public static void exportFile(){
        System.out.println("File name:");
        String filename=input.nextLine();
        file =new File(filename);
        try(PrintWriter writer=new PrintWriter(file)){
            for (String key : m.keySet()){
                writer.println(key);
                writer.println(m.get(key));
            }
            writer.flush();
            System.out.println(m.size()+" cards have been saved");
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
                answer=input.nextLine();
                if(m.get(key).equals(answer)){
                    System.out.println("Correct answer.");
                 }
                else{
                    String temp=getKey(m,answer);
                    try{
                        if(!temp.equals(null)){
                        System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\"");
                    }
                }
            }   
        
        if(numberOfask>0){
            while(numberOfask>0){
                List<String> keysAsArray = new ArrayList<String>(m.keySet());
                Random r = new Random();
                String key=keysAsArray.get(r.nextInt(keysAsArray.size()));
                System.out.println("Print the definition of "+"\""+key+"\"");
                answer=input.nextLine();
                if(m.get(key).equals(answer)){
                    System.out.println("Correct answer.");
                 }
                else{
                    String temp=getKey(m,answer);
                    try{
                        if(!temp.equals(null)){
                        System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\"");
                    }
                }
                numberOfask--;

            }

        }
    }
    public static void howmanytimes(){
        
    }
    public static void main(String[] args) {
       

       do{
           System.out.println("Input the action (add, remove, import, export, ask, exit):");
           choice=input.nextLine();
           switch(choice){
               case "add":
               addcard();
               break;
               case "remove":
               remove();
               break;
               case "import":
               importFile();
               break;
               case "export":
               exportFile();
               break;
               case "ask":
                System.out.println("How many times to ask?");
                int numberOfask=input.nextInt();
                input.nextLine();
                ask(numberOfask);
               break;
               case "exit":
               System.out.println("Bye bye!");
               break;
            }

       }while(!choice.equals("exit"));
       
    }
}


