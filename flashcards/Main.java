import java.util.*;
public class Main {

    public static String choice;
    public static int number;
    public static Set <String> cardset=new HashSet<>();
    public static Set<String> definitionset=new HashSet<>();
    public static Map <String,String> m=new LinkedHashMap<>();
    public static String card,definition;
    
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
           System.out.println("The pair (\""+card+" : "+definition+"\") has been added.");
        
    }

    public static void remove(){
        String answer=null;
        System.out.println("The Card:");
        answer=input.nextLine();
        if(m.containsKey(answer)==true){
            m.remove(answer);
            System.out.println("The card has been removed.");
        } 
        else{
            System.out.println("Can't remove \""+answer+"\": there is no such card.");
        }
    }

    public static void ask(){
        String answer=null;
      

        for (String key : m.keySet()){
            System.out.println("Print the definition of "+"\""+key+"\"");
            answer=input.nextLine();
            if(m.get(key).equals(answer)){
                System.out.println("Correct answer.");
            }
            else{
                System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
            }
        }
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
               break;
               case "export":
               break;
               case "ask":
               ask();
               break;
               case "exit":
               System.out.println("Bye bye!");
               break;
            }

       }while(!choice.equals("exit"));
       /**
       System.out.println("Input the number of cards:");
       number=input.nextInt();
       
       input.nextLine();
       boolean flag=false;
       String card,definition;
       Map <String,String> m=new LinkedHashMap<>();
       for(int i=0;i<number;i++){
           flag=true;
           System.out.println("The card #"+(i+1)+":");

           do{
            card=input.nextLine();
           if(cardset.contains(card)){
               flag=true;
               System.out.println("The card "+"\""+card+"\" already exists. Try again:");
           }
           else{
               cardset.add(card);
               flag=false;
               break;
           }
           }
           while(flag==true);
           flag=false;

           System.out.println("The definition of the card #"+(i+1)+":");

           do{
            definition=input.nextLine();
           if(definitionset.contains(definition)){
               flag=true;
                System.out.println("The definition "+"\""+definition+"\" already exists. Try again:");
           }
           else{
               definitionset.add(definition);
               flag=false;
               break;
           }
           }
           while(flag==true);
           m.put(card,definition);
       }
       String answer=null;
      

        for (String key : m.keySet()){
            System.out.println("Print the definition of "+"\""+key+"\"");
            answer=input.nextLine();
            if(m.get(key).equals(answer)){
                System.out.println("Correct answer.");
            }
            else{
                System.out.println("Wrong answer. The Correct one is "+"\""+m.get(key)+"\" , you've just written the definition of "+"\""+getKey(m,answer)+"\"");
            }
        } */
    }
}
