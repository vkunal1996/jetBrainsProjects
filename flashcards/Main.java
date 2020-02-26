package flashcards;
import java.util.*;
public class Main {
    public static <K,V> K getKey(Map<K,V>map,V value){
        for(Map.Entry<K,V> entry:map.entrySet()){
            if(value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
    public static void main(String[] args) {
       Scanner input=new Scanner(System.in);
       int number;
       
       Set <String> cardset=new HashSet<>();
       Set<String> definitionset=new HashSet<>();
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
        }
    }
}
