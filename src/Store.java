import java.util.Comparator;
import java.util.Scanner;

public class Store {

    Scanner scanner = new Scanner(System.in);
    Database database = new Database();
    Phone phone = new Phone(database);
    Notebook notebook = new Notebook(database);


    public void menu() {

        Product[] products = {phone, notebook};

        database.defaultProducts();

            while(true) {

                int processNumber = 0;


                database.getFilteredNotebooks().clear();
                database.getFilteredPhones().clear();
                database.getBrands().sort(Comparator.comparing(Brand::getId));
                database.getPhones().sort(Comparator.comparing(Phone::getId));
                database.getNotebooks().sort(Comparator.comparing(Notebook::getId));

                System.out.println(" ");

                System.out.println("-".repeat(37));
                System.out.format("%10s %12s %9s%n","| AydınStore","Ürün Yönetim","İşlemleri |");
                System.out.println("-".repeat(37));

                for (Product product : products) {
                    System.out.format("%-1s %-1d %-31s %1s%n","|", product.getProductID() , "- " +  product.getProductName() + " İşlemleri","|");
                    processNumber = product.getProductID();
                }


                int listBrandProcessNumber = processNumber + 1;
                System.out.format("%-1s %-1d %-31s %1s%n", "|", listBrandProcessNumber , "- Markaları Listele ","|");

                int addBrandProcessNumber = listBrandProcessNumber + 1;
                System.out.format("%-1s %-1d %-31s %1s%n", "|", addBrandProcessNumber , "- Marka Ekle ","|");

                int removeBrandProcessNumber = addBrandProcessNumber + 1;
                System.out.format("%-1s %-1d %-31s %1s%n", "|", removeBrandProcessNumber , "- Marka Sil ","|");

                System.out.println("-".repeat(37));

                boolean isValid = true;
                int choice;

                while (isValid) {
                    System.out.print("Tercihiniz: ");
                    try {
                        choice = scanner.nextInt();
                        System.out.println(" ");
                    } catch (Exception e) {
                        System.out.println("Geçersiz bir değer girdiniz.");
                        scanner.nextLine();
                        continue;
                    }
                    for (Product product : products) {
                        if (product.getProductID() == choice) {
                            product.transaction();
                            isValid = false;
                        }
                    }
                    if (listBrandProcessNumber == choice) {
                        listBrand();
                        isValid = false;
                    }
                    if (addBrandProcessNumber == choice) {
                        addBrand();
                        isValid = false;
                    }
                    if (removeBrandProcessNumber == choice) {
                        removeBrand();
                        isValid = false;
                    }

                    if (isValid) {
                        System.out.println("Geçersiz bir değer girdiniz.");
                    }
                }

            }

    }

    public void listBrand() {

        boolean isValid = true;
        char input = 0;

        for (Brand brand:database.getBrands()) {
            System.out.println("- " + brand.getName());
        }

        System.out.println("<A> Alfabetik sırala  <Z> Ters alfabetik sırala <Q> Geri Dön");

        while(isValid) {
            System.out.print("Tercihiniz: ");
            input = scanner.next().charAt(0);

            switch (input) {
                case 'A', 'a':
                    database.getBrands().sort(Comparator.comparing(Brand::getName));
                    isValid = false;
                    System.out.println(" ");
                    listBrand();
                    break;
                case 'Z', 'z':
                    database.getBrands().sort(Comparator.comparing(Brand::getName).reversed());
                    isValid = false;
                    System.out.println(" ");
                    listBrand();
                    break;
                case 'Q', 'q':
                    isValid = false;
                    break;
                default:
                    System.out.println("\nGeçersiz bir değer girdiniz.");
            }




        }




    }

    public void removeBrand() {

        boolean isContinue = true;
        boolean isValid = true;
        boolean hasBrand = false;
        Brand removenBrand = null;
        char choice = 0;

        System.out.println("Vazgeçmek için <Q> tuşlayınız.");


        scanner.nextLine();
        while (isValid) {
            System.out.print("Silmek istediğiniz markanın ismini yazınız: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return;
            }

            for (Brand brand : database.getBrands()) {
                if (brand.getName().equalsIgnoreCase(input)) {
                    hasBrand = true;
                    removenBrand = brand;
                }
            }

            if (!hasBrand) {
                System.out.println("\nBu marka mağazamızda bulunmuyor.\n");
            } else {
                System.out.println("\n'" + removenBrand.getName() + "' markasını ve bu markanın ürünlerini kaldırmak istediğinizden emin misiniz?\n" +
                        "<E> Evet <H> Hayır");

                while (isContinue) {

                System.out.print("\nTercihiniz: ");

                try {
                    choice = scanner.next().charAt(0);
                } catch (Exception e) {
                    System.out.println("Geçersiz bir değer girdiniz.");
                    scanner.nextLine();
                }

                    switch (choice) {
                        case 'E', 'e':

                            Brand finalRemovenBrand = removenBrand;
                            database.getNotebooks().removeIf(notebook -> notebook.getBrand().equals(finalRemovenBrand));
                            database.getPhones().removeIf(phone -> phone.getBrand().equals(finalRemovenBrand));
                            database.getBrands().remove(removenBrand);

                            System.out.println("\nMarka ve ürünleri silindi.");

                            isValid = false;
                            isContinue = false;
                            break;
                        case 'H', 'h':
                            isContinue = false;
                            isValid = false;
                            break;
                        default:
                            System.out.println("Geçersiz bir değer girdiniz.");

                    }
                }



            }



        }

    }

    public void addBrand() {

        System.out.println("Vazgeçmek için <Q> tuşlayınız.");

        boolean isValid = true;
        boolean alreadyHave = false;

        scanner.nextLine();
        while (isValid) {
            System.out.print("Eklemek istediğiniz markanın ismini yazınız: ");
            String brandName = scanner.nextLine();

            if (brandName.equalsIgnoreCase("q")) {
                return;
            }

            for (Brand brand : database.getBrands()) {
                if (brand.getName().equalsIgnoreCase(brandName.trim())) {
                    alreadyHave = true;
                    break;
                }
            }

            if (!alreadyHave) {
                database.getBrands().add(new Brand(brandName));
                System.out.println("\nMarka eklendi.");
                isValid = false;
            } else {
                System.out.println("\nBu marka zaten mağazamızda bulunuyor.\n");
            }
        }

    }

}
