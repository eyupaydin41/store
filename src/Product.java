import java.util.ArrayList;
import java.util.Scanner;

public abstract class Product {

    Scanner scanner = new Scanner(System.in);
    Database database = new Database();

    private double price;
    private int cameraResolution;
    private int discountRate;
    private int amountOfStock;
    private String name;
    private Brand brand;
    private int memory;
    private double screenSize;
    private double batteryPower;
    private int RAM;
    private String color;

    public Product(Brand brand, String name, double price, int discountRate, int amountOfStock, int memory, double screenSize, double batteryPower, int RAM, String color) {
        this.price = price;
        this.discountRate = discountRate;
        this.amountOfStock = amountOfStock;
        this.name = name;
        this.brand = brand;
        this.memory = memory;
        this.screenSize = screenSize;
        this.batteryPower = batteryPower;
        this.RAM = RAM;
        this.color = color;
    }

    public Product() {

    }

   public void transaction() {

       System.out.println("-".repeat(37));
       System.out.format("%1s %-33s%-1s%n","|", "AydınStore " + getProductName() + " İşlemleri", " |");
       System.out.println("-".repeat(37));

       System.out.format("%-35s %1s%n","| 1 - Ürünleri Listele", "|");
       System.out.format("%-35s %1s%n","| 2 - Ürün Ekle", "|");
       System.out.format("%-35s %1s%n","| 3 - Ürün Sil", "|");
       System.out.format("%-35s %1s%n","| 4 - Ürün Düzenle", "|");
       System.out.format("%-35s %1s%n","| 0 - Geri Dön", "|");

       System.out.println("-".repeat(37));

       boolean isValid = true;
       int choice;

       while(isValid) {
           System.out.print("Tercihiniz: ");

           try {
               choice = scanner.nextInt();
           } catch (Exception e) {
               System.out.println("Geçersiz bir değer girdiniz.");
               scanner.nextLine();
               continue;
           }

           switch (choice) {
               case 1 -> {

                   if (getProductID() == 1) {
                       list(database.getPhones());
                   } else if (getProductID() == 2) {
                       list(database.getNotebooks());
                   }

                   isValid = false;
               }
               case 2 -> {
                   add();
                   isValid = false;
               }
               case 3 -> {
                   remove();
                   isValid = false;
               }
               case 4 -> {
                   change();
                   isValid = false;
               }

               case 0 -> isValid = false;

               default -> System.out.println("Geçersiz bir sayı girdiniz.");
           }
       }
   }

    public abstract void list(ArrayList<? extends Product> arrayList);

    public abstract void remove();
    public abstract void change();
    public abstract void add();


    public abstract int getId();

    public abstract String getProductName();

    public abstract int getProductID();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getAmountOfStock() {
        return amountOfStock;
    }

    public void setAmountOfStock(int amountOfStock) {
        this.amountOfStock = amountOfStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getMemory() {
        return memory;
    }
    public int getCameraResolution() {
        return cameraResolution;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public double getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(double batteryPower) {
        this.batteryPower = batteryPower;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }

}
