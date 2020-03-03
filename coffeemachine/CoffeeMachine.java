package machine;
import java.util.*;
class Machine{
    protected int waterInMachine;
    protected int milkInMachine;
    protected int beansInMachine;
    protected int moneyInMachine;
    protected int numberOfCups;

    public Machine(int waterInMachine,int milkInMachine,int beansInMachine,int numberOfCups,int moneyInMachine){
        this.waterInMachine=waterInMachine;
        this.milkInMachine=milkInMachine;
        this.beansInMachine=beansInMachine;
        this.moneyInMachine=moneyInMachine;
        this.numberOfCups=numberOfCups;
    }
    public int getWaterInMachine(){
        return waterInMachine;
    }
    public int getBeansInMachine(){
        return beansInMachine;
    }
    public int getMilkInMachine(){
        return milkInMachine;
    }
    public int getMoneyInMachine(){
        return  moneyInMachine;
    }
    public int getNumberOfCups(){
        return numberOfCups;
    }
    public void setWaterInMachine(int waterInMachine){
        this.waterInMachine=waterInMachine;
    }
    public void setMilkInMachine(int milkInMachine){
        this.milkInMachine=milkInMachine;
    }
    public void setBeansInMachine(int beansInMachine){
        this.beansInMachine=beansInMachine;
    }
    public void setMoneyInMachine(int moneyInMachine){
        this.moneyInMachine=moneyInMachine;
    }
    public void setNumberOfCups(int numberOfCups){
        this.numberOfCups=numberOfCups;
    }

    public void stateOfMachine(){
        System.out.println();
        System.out.println("The coffee machine has");
        System.out.println(this.waterInMachine+" of water");
        System.out.println(this.milkInMachine+" of milk");
        System.out.println(this.beansInMachine+" of coffee beans");
        System.out.println(this.numberOfCups+" of disposable cups");
        System.out.println("$"+this.moneyInMachine+" of money");
        System.out.println();
    }
    public void makeEspresso(){
        if(waterInMachine>=250 && beansInMachine>=16) {
            waterInMachine -= 250;
            beansInMachine -= 16;
            moneyInMachine += 4;
            setNumberOfCups(numberOfCups - 1);
            System.out.println("I have enough resources, making you a coffee!");
        }
        else{
            if(waterInMachine<250){
                System.out.println("Sorry, not enough water!");
            }
            else{
                System.out.println("Sorry, not enough coffee beans!");
            }
        }
        //stateOfMachine();
    }
    public void makeLatte(){
        if(waterInMachine>=350 && milkInMachine>=75 && beansInMachine>=20) {
            waterInMachine -= 350;
            milkInMachine -= 75;
            beansInMachine -= 20;
            moneyInMachine += 7;
            setNumberOfCups(numberOfCups - 1);
            System.out.println("I have enough resources, making you a coffee!");
        }
        else{
            if(waterInMachine<350){
                System.out.println("Sorry, not enough water!");
            }
            else if (milkInMachine<75){
                System.out.println("Sorry, not enough milk!");
            }
            else{
                System.out.println("Sorry, not enough coffee beans!");
            }
        }
        //stateOfMachine();
    }
    public void makeCappucccino(){
        if(waterInMachine>=200 && beansInMachine>=12 && milkInMachine>=100) {
            waterInMachine -= 200;
            beansInMachine -= 12;
            milkInMachine -= 100;
            moneyInMachine += 6;
            setNumberOfCups(numberOfCups - 1);
            System.out.println("I have enough resources, making you a coffee!");
        }
        else{
            if(waterInMachine<200){
                System.out.println("Sorry, not enough water!");
            }
            else if (milkInMachine<100){
                System.out.println("Sorry, not enough milk!");
            }
            else{
                System.out.println("Sorry, not enough coffee beans!");
            }
        }
        //stateOfMachine();
    }
    public void fill(int requiredWater,int requiredMilk,int requiredBeans, int requriedCups){
        waterInMachine+=requiredWater;
        milkInMachine+=requiredMilk;
        beansInMachine+=requiredBeans;
        numberOfCups+=requriedCups;
    }
}
public class CoffeeMachine {
    public static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        Machine m=new Machine(400,540,120,9,550);
        String action;

        int requiredWater,requiredMilk,requiredcups,requiredBeans;
        do {
            System.out.println("Write action(buy, fill, take, remaining, exit):");
            action=input.nextLine();
            switch (action) {
                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    requiredWater = input.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    requiredMilk = input.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    requiredBeans = input.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    requiredcups = input.nextInt();
                    input.nextLine();

                    m.fill(requiredWater, requiredMilk, requiredBeans, requiredcups);

                    break;
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3-cappuccino:");
                    String choice = input.nextLine();
                    switch (choice) {
                        case "1":
                            m.makeEspresso();
                            break;
                        case "2":
                            m.makeLatte();
                            break;
                        case "3":
                            m.makeCappucccino();
                            break;
                    }

                    break;
                case "take":
                    System.out.println("\nI gave you $" + m.getMoneyInMachine());
                    m.setMoneyInMachine(0);
                    System.out.println();
                    break;
                case "remaining":
                    m.stateOfMachine();
                    break;
                case "exit":
                    break;
            }
        }while(!action.equals("exit"));
    }

}
