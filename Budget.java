package budget;


import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> loaded = new ArrayList<>();
    static ArrayList<String> listFood = new ArrayList<>();
    static ArrayList<String> listCloth = new ArrayList<>();
    static ArrayList<String> listOther = new ArrayList<>();
    static ArrayList<String> listEnt = new ArrayList<>();

    static double balance = 0;
    static double purchase = 0;
    static double purchaseFood = 0;
    static double purchaseOther = 0;
    static double purchaseCloth = 0;
    static double purchaseEnt = 0;


    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        boolean status = false;
        while(status == false){
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "5) Save\n" +
                    "6) Load\n" +
                    "7) Analyze\n" +
                    "0) Exit");
            System.out.println("");
            int response = scan.nextInt();
            switch (response) {
                case 1:
                    System.out.println("");
                    addIncome();
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("");
                    addPurchase();
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("");
                    listPurchase();
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("");
                    checkBalance();
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("");
                    save();
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("");
                    load();
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("");
                    analyze();
                    System.out.println("");
                    break;
                case 0:
                    status = true;
                    System.out.println("");
                    System.out.println("Bye!");
                    break;
            }
        }
    }

    private static void analyze() {
        System.out.println("How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
        int res = scan.nextInt();
        while(res != 4) {
            switch (res) {
                case 1 :
                    System.out.println("");
                    sortAll();
                    System.out.println("");
                    break;
                case 2 :
                    System.out.println("");
                    sortType();
                    System.out.println("");
                    break;
                case 3 :
                    System.out.println("");
                    sortCertain();
                    System.out.println("");
                    break;

            }
            System.out.println("How do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");
            res = scan.nextInt();
        }
    }

    private static void sortCertain() {
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other" );
        int res = scan.nextInt();
        switch (res) {
            case 1 :
                System.out.println("");
                sortIndividual(listFood, purchaseFood);
                System.out.println("");
                break;
            case 2 :
                System.out.println("");
                sortIndividual(listCloth, purchaseCloth);
                System.out.println("");
                break;
            case 3 :
                System.out.println("");
                sortIndividual(listEnt, purchaseEnt);
                System.out.println("");
                break;
            case 4 :
                System.out.println("");
                sortIndividual(listOther, purchaseOther);
                System.out.println("");
                break;
        }
    }

    public static void sortIndividual(ArrayList<String> list, double purch) {
        if(list.size() == 0) {
            System.out.println("Purchase list is empty!");
        }else {
            double[] array = new double[list.size()];
            int count = 0;
            for (String i : list) {
                array[count] = Double.parseDouble(i.substring(i.lastIndexOf("$")+1));
                count++;
            }
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] < array[j + 1]) {
                        double temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < list.size(); j++) {
                    if(Double.parseDouble(list.get(j).substring(list.get(j).lastIndexOf("$")+1)) == array[i]) {
                        System.out.println(list.get(j));
                    }
                }
            }
            System.out.println("Total: $" + String.format("%.2f", purch));
        }

    }
    private static void sortType() {
        ArrayList<String> all = putAllInList();
        if(all.size() == 0) {
            System.out.println("Types:\n" +
                    "Food - $0\n" +
                    "Entertainment - $0\n" +
                    "Clothes - $0\n" +
                    "Other - $0\n" +
                    "Total sum: $0\n");
        }else {
            HashMap<String, Double> maps = new HashMap<>();
            maps.put("Food -",  Double.parseDouble(String.format("%.2f", purchaseFood)));
            maps.put("Entertainment -", Double.parseDouble(String.format("%.2f", purchaseEnt)));
            maps.put("Clothes -", Double.parseDouble(String.format("%.2f", purchaseCloth)));
            maps.put("Other -", Double.parseDouble(String.format("%.2f", purchaseOther)));
            double[] array = {purchaseFood, purchaseCloth, purchaseEnt, purchaseOther};
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] < array[j + 1]) {
                        double temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
//            System.out.println("Types:");
            for (int i = 0; i < array.length; i++) {
                for (Map.Entry mp:
                        maps.entrySet()) {
                    if(mp.getValue().equals( Double.parseDouble(String.format("%.2f", array[i])))) {
                        System.out.println(mp.getKey() + " $" + mp.getValue());
                    }
                }
            }

//            System.out.println("Total sum: $" + (purchaseOther + purchaseEnt + purchaseCloth + purchaseFood));

        }

    }

    public static void sortAll() {
        ArrayList<String> list = putAllInList();
        if(list.size() == 0) {
            System.out.println("Purchase list is empty!");
        }else {
            double[] array = new double[list.size()];
            int count = 0;
            for (String i : list) {
                array[count] = Double.parseDouble(i.substring(i.lastIndexOf("$")+1));
                count++;
            }
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] < array[j + 1]) {
                        double temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
            ArrayList<String> as = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < list.size(); j++) {
                    if(Double.parseDouble(list.get(j).substring(list.get(j).lastIndexOf("$")+1)) == array[i] && !as.contains(list.get(j))) {
                        as.add(list.get(j));
                    }
                }
            }
            for (String i :
                    as) {
                System.out.println(i);
            }
        }
    }

    public static ArrayList<String> putAllInList() {
        ArrayList<String> all = new ArrayList<>();
        for (String i :
                listCloth) {
            all.add(i);
        }
        for (String i :
                listEnt) {
            all.add(i);
        }
        for (String i :
                listFood) {
            all.add(i);
        }
        for (String i :
                listOther) {
            all.add(i);
        }
        return all;
    }


    public static void checkBalance() {
        System.out.println("Balance: $" + (balance-purchase));
    }


    public static void list(ArrayList<String> list, double purchases) {
        if(list.size() == 0) {
            System.out.println("");
            System.out.println(":");
            System.out.println("Purchase list is empty");
        }else {
            System.out.println("");
            System.out.println(":");
            for (String i:
                    list) {
                System.out.println(i);
            }
            System.out.println("Total sum: $" + String.format("%.2f", purchases));
        }
    }
    public static void listPurchase() {
        if(listFood.size() == 0 && listOther.size()== 0  && listCloth.size() == 0 && listEnt.size() == 0) {
            System.out.println("Purchase list is empty!");
        }else {
            System.out.println("Choose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) ALl\n" +
                    "6) Back");
            int res = scan.nextInt();


            while(res != 6) {
                switch (res) {
                    case 1 :
                        list(listFood, purchaseFood);
                        System.out.println("");
                        break;
                    case 2 :
                        list(listCloth, purchaseCloth);
                        System.out.println("");
                        break;
                    case 3 :
                        list(listEnt, purchaseEnt);
                        System.out.println("");
                        break;
                    case 4 :
                        list(listOther, purchaseOther);
                        System.out.println("");
                        break;
                    case 5 :
                        getALL();
                        System.out.println("");
                        break;
                }
                System.out.println("Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) ALl\n" +
                        "6) Back");
                res = scan.nextInt();
            }


        }

    }

    private static void getALL() {
        ArrayList<String> all = new ArrayList<>();
        for (String i :
                listCloth) {
            all.add(i);
        }
        for (String i :
                listEnt) {
            all.add(i);
        }
        for (String i :
                listFood) {
            all.add(i);
        }
        for (String i :
                listOther) {
            all.add(i);
        }
        System.out.println("");
        System.out.println("All:");
        for (String i:
                all) {
            System.out.println(i);
        }
        double purchased = purchaseOther + purchaseEnt + purchaseCloth + purchaseFood;
        System.out.println("Total sum: $" + String.format("%.2f", purchased));

    }

    public static double addPurch(ArrayList<String> list) {
        System.out.println("");
        System.out.println("Enter purchase name:");
        scan.nextLine();
        String item = scan.nextLine();
        System.out.println("Enter its price:");
        double price = scan.nextDouble();
        purchase += Double.parseDouble(String.format("%.2f", price));
        list.add(item + " $" + String.format("%.2f", price));
        System.out.println("Purchase was added!");
        return price;
    }

    public static void addPurchase() {
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
        int res = scan.nextInt();
        while(res != 5) {
            switch (res) {
                case 1 :
                    purchaseFood += addPurch(listFood);
                    System.out.println("");
                    break;
                case 2 :
                    purchaseCloth += addPurch(listCloth);
                    System.out.println("");
                    break;
                case 3 :
                    purchaseEnt += addPurch(listEnt);
                    System.out.println("");
                    break;
                case 4 :
                    purchaseOther += addPurch(listOther);
                    System.out.println("");
                    break;
            }
            System.out.println("Choose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) Back");
            res = scan.nextInt();
        }
    }

    public static void addIncome() {
        System.out.println("Enter income: ");
        double income = scan.nextDouble();
        balance += income;
        System.out.println("Income was added!");
    }

    public static void save(){
        ArrayList<ArrayList<String>> all = new ArrayList<>();
        all.add(listFood);
        all.add(listCloth);
        all.add(listEnt);
        all.add(listOther);

        try {
            File file = new File("purchases.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            PrintWriter printWriter = new PrintWriter(fw);
            printWriter.println("Balance)" + (balance-purchase));
            for (int i = 0; i < all.size(); i++) {
                for (int j = 0; j < all.get(i).size(); j++) {
                    printWriter.println(i+1 + ")" +all.get(i).get(j));

                }

            }

            printWriter.close();

            System.out.println("Purchases were saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            File myObj = new File("purchases.txt");
            Scanner myReader = new Scanner(myObj);
            reset();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                loaded.add(data);
                if(data.charAt(0) == 'B') {
                    balance = Double.parseDouble(data.substring(data.indexOf(")") + 1));
                }
                if(data.charAt(0) == '1') {
                    listFood.add(data.substring(data.indexOf(")") + 1));
                    purchaseFood += Double.parseDouble(data.substring(data.lastIndexOf("$")+1));
                }
                if(data.charAt(0) == '2') {
                    listCloth.add(data.substring(data.indexOf(")") + 1));
                    purchaseCloth += Double.parseDouble(data.substring(data.lastIndexOf("$")+1));
                }
                if(data.charAt(0) == '3') {
                    listEnt.add(data.substring(data.indexOf(")") + 1));
                    purchaseEnt += Double.parseDouble(data.substring(data.lastIndexOf("$")+1));
                }
                if(data.charAt(0) == '4') {
                    listOther.add(data.substring(data.indexOf(")") + 1));
                    purchaseOther += Double.parseDouble(data.substring(data.lastIndexOf("$")+1));
                }
            }
            myReader.close();
            System.out.println("Purchases were loaded!");

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void reset() {
        loaded = new ArrayList<>();
        listFood = new ArrayList<>();
        listCloth = new ArrayList<>();
        listOther = new ArrayList<>();
        listEnt = new ArrayList<>();

        double balance = 0;
        double purchase = 0;
        double purchaseFood = 0;
        double purchaseOther = 0;
        double purchaseCloth = 0;
        double purchaseEnt = 0;
    }

}
