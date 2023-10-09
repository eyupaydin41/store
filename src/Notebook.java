import java.util.ArrayList;
import java.util.Comparator;

public class Notebook extends Product{

    private static int nextID = 20001;
    private int id;


    public Notebook(Brand brand, String name, double price, int discountRate, int amountOfStock, int memory, double screenSize, double batteryPower, int RAM, String color) {
        super(brand, name, price, discountRate, amountOfStock, memory, screenSize, batteryPower, RAM, color);
        this.id = nextID;
        nextID++;
    }

    public Notebook(Database database) {
        this.database = database;
    }



    @Override
    public void list(ArrayList<? extends Product> arrayList) {

        System.out.println(" ");

        System.out.println("-".repeat(126));
        System.out.format("| %-5s | %-20s | %-10s | %-10s | %-13s | %-11s | %-9s | %-10s | %-10s |%n", "ID", "Ürün Adı", "Fiyat (TL)", "Marka", "Depolama (GB)", "Ekran (inç)", "Pil (mAh)", "RAM (GB)", "Renk");
        System.out.println("-".repeat(126));

        arrayList.forEach(notebook -> System.out.format("| %-5d | %-20s | %-10.2f | %-10s | %-13d | %-11.1f | %-9.1f | %-10d | %-10s |%n",
                notebook.getId(), notebook.getName(), notebook.getPrice() - notebook.getDiscountRate() * notebook.getPrice() / 100, notebook.getBrand().getName(), notebook.getMemory(),
                notebook.getScreenSize(), notebook.getBatteryPower(), notebook.getRAM(), notebook.getColor()));
        System.out.println("-".repeat(126));
        choiceTable();
    }
    @Override
    public void remove() {

        System.out.println("\nİptal etmek için <0> tuşlayınız.");

        int input;
        boolean isValid = true;
        boolean isFound = false;
        Notebook removeNotebook = null;

        while (isValid) {
            try {
                System.out.print("\nSilmek istediğiniz ürünün ID'sini giriniz: ");
                input = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Geçersiz bir değer girdiniz.");
                scanner.nextLine();
                continue;
            }

            if (input == 0) {
                return;
            }

            for (Notebook notebook:database.getNotebooks()) {
                if (notebook.getId() == input) {
                    isFound = true;
                    break;
                }
            }


            if (isFound) {

                for (Notebook notebook : database.getNotebooks()) {
                    if (notebook.getId() == input) {
                        removeNotebook = notebook;
                        System.out.println("\nÜrün silindi.");
                        isValid = false;
                    }
                }
                database.getNotebooks().remove(removeNotebook);
            } else {
                System.out.println("Bu ID'e sahip bir ürün bulunamadı.");
            }
        }


    }
    @Override
    public void change() {


        System.out.println("\nİptal etmek için <0> tuşlayınız.");

        int input;
        boolean isValid = true;
        boolean isFound = false;

        while (isValid) {
            try {
                System.out.print("\nBilgilerini değiştirmek istediğiniz ürünün ID'sini giriniz: ");
                input = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Geçersiz bir değer girdiniz.");
                scanner.nextLine();
                continue;
            }

            if (input == 0) {
                return;
            }

            for (Notebook notebook:database.getNotebooks()) {
                if (notebook.getId() == input) {
                    isFound = true;
                    break;
                }
            }


            if (isFound) {

                for (Notebook notebook : database.getNotebooks()) {
                    if (notebook.getId() == input) {

                        boolean isValidInfo = true;
                        char changeInput = 0;

                        while(isValidInfo) {

                            System.out.println("\nÜrünün hangi bilgisini değiştirmek istiyorsunuz?");
                            System.out.println("<M> Marka <I> İsim <F> Fiyat <O> İndirim Oranı <S> Stok Sayısı <H> Hafıza\n<E> Ekran Boyutu <P> Pil Gücü <R> RAM <C> Renk\n<0> Geri Dön");
                            System.out.print("Tercihiniz: ");

                            try {
                                changeInput = scanner.nextLine().charAt(0);
                            } catch (Exception e) {
                                System.out.println("Geçersiz bir değer girdiniz.");
                                scanner.nextLine();
                            }

                            switch (changeInput) {
                                case 'S', 's' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setAmountOfStock(chooseAmountOfStock());
                                    System.out.println("Ürünün stok sayısı değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'O', 'o' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setDiscountRate(chooseDiscountRate());
                                    System.out.println("Ürünün indirim oranı değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'M', 'm' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setBrand(chooseBrand());
                                    System.out.println("Ürünün markası değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'I', 'ı', 'i' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setName(chooseName());
                                    System.out.println("Ürünün ismi değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'F', 'f' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setPrice(choosePrice());
                                    System.out.println("Ürünün fiyatı değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'H', 'h' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setMemory(chooseMemory());
                                    System.out.println("Ürünün hafızası değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'E', 'e' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setScreenSize(chooseScreenSize());
                                    System.out.println("Ürünün ekran boyutu değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'P', 'p' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setBatteryPower(chooseBatteryPower());
                                    System.out.println("Ürünün pil gücü değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'R', 'r' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setRAM(chooseRAM());
                                    System.out.println("Ürünün RAM miktarı değiştirildi.");
                                    isValidInfo = false;
                                }
                                case 'C', 'c' -> {
                                    System.out.println("\nİptal etmek için <Q> tuşlayınız.");
                                    notebook.setColor(chooseColor());
                                    System.out.println("Ürünün rengi değiştirildi.");
                                    isValidInfo = false;
                                }
                                case '0' -> isValidInfo = false;
                                default ->  System.out.println("Geçersiz bir değer girdiniz.");

                            }
                        }
                        isValid = false;
                    }
                }
            } else {
                System.out.println("Bu ID'e sahip bir ürün bulunamadı.");
            }
        }

    }
    @Override
    public void add() {
        Brand choosenBrand;
        String name;
        double price;
        int memory;
        double screenSize;
        double batteryPower;
        int RAM;
        String color;
        int discountRate;
        int amountOfStock;


        System.out.println("\nİptal etmek için <Q> tuşlayınız.");

        choosenBrand = chooseBrand();
        if (choosenBrand.getName().equals("quit")) {
            return;
        }
        scanner.nextLine();
        name = chooseName();
        if (name.equals("quit")) {
            return;
        }
        price = choosePrice();
        if (price == -1) {
            return;
        }
        memory = chooseMemory();
        if (memory == -1) {
            return;
        }
        screenSize = chooseScreenSize();
        if (screenSize == -1) {
            return;
        }
        batteryPower = chooseBatteryPower();
        if (batteryPower == -1) {
            return;
        }
        RAM = chooseRAM();
        if (RAM == -1) {
            return;
        }
        color = chooseColor();
        if (color.equals("quit")) {
            return;
        }
        discountRate = chooseDiscountRate();
        if (discountRate == -1) {
            return;
        }
        amountOfStock = chooseAmountOfStock();
        if (amountOfStock == -1) {
            return;
        }


        database.getNotebooks().add(new Notebook(choosenBrand, name, price, discountRate, amountOfStock, memory, screenSize, batteryPower, RAM, color));

        System.out.println("\nÜrün eklendi.");

    }
    public void choiceTable() {
        System.out.println("<F> Filtrele <S> Sırala <Q> Geri Dön");

        boolean isValid = true;

        while(isValid) {
            System.out.print("Tercihiniz: ");
            char input = scanner.next().charAt(0);

            switch (input) {
                case 'F', 'f' -> {
                    isValid = false;
                    filterNotebook();
                }
                case 'S', 's' -> {
                    isValid = false;
                    listNotebook();
                }
                case 'Q', 'q' -> isValid = false;
                default -> System.out.println("Geçersiz bir değer girdiniz.");
            }
        }
    }
    public void filterNotebook() {

        System.out.println("""

                Hangi kategoriye göre filtrelemek istersiniz?
                <M> Marka <F> Fiyat <Q> Geri Dön""");

        boolean isValid = true;

        while(isValid) {
            System.out.print("Tercihiniz: ");
            char input = scanner.next().charAt(0);
            switch (input) {
                case 'M', 'm' -> {
                    isValid = false;
                    System.out.println("Geri dönmek için <Q> tuşlayınız.");

                    database.getFilteredNotebooks().clear();

                    boolean isValidBrand = true;

                    scanner.nextLine();
                    while(isValidBrand) {
                        System.out.print("\nHangi marka/markaların ürünlerini görmek istiyorsunuz: ");
                        String inputBrand = scanner.nextLine();

                        if (inputBrand.equalsIgnoreCase("q")) {
                            return;
                        }

                        int numberofValidBrand = 0;
                        String[] words = inputBrand.split(" ");
                        for (String word : words) {

                            for (Brand brand : database.getBrands()) {

                                if (brand.getName().equalsIgnoreCase(word.trim())) {
                                    numberofValidBrand++;
                                }

                            }

                        }
                        if (numberofValidBrand == words.length) {

                            for (String word : words) {
                                for (Brand brand : database.getBrands()) {
                                    if (brand.getName().equalsIgnoreCase(word)) {
                                        for (Notebook notebook : database.getNotebooks()) {
                                            if (notebook.getBrand() == brand) {
                                                database.getFilteredNotebooks().add(notebook);
                                            }
                                        }
                                    }
                                }
                            }
                            isValidBrand = false;
                            list(database.getFilteredNotebooks());

                        } else {
                            System.out.println("\nSeçtiğiniz markanın mağazamızda satışı yoktur.\nLütfen farklı bir marka ismi giriniz.");
                        }
                    }
                }
                case 'F', 'f' -> {
                    database.getFilteredNotebooks().clear();
                    double minPrice = 0;
                    double maxPrice = 0;
                    boolean isValidPrice = true;
                    while (isValidPrice) {
                        try {
                            System.out.print("\nListelenmesini istediğiniz ürünlerin MİNİMUM fiyatını yazınız (veya 'Q' girerek çıkış yapın):");
                            String input2 = scanner.next();
                            if (input2.equalsIgnoreCase("Q")) {
                                return;
                            }
                            minPrice = Double.parseDouble(input2);
                            isValidPrice = false;
                        } catch (Exception e) {
                            System.out.println("Geçersiz bir değer girdiniz.");
                            scanner.nextLine();
                        }
                    }

                    isValidPrice = true;
                    while (isValidPrice) {
                        try {
                            System.out.print("\nListelenmesini istediğiniz ürünlerin MAKSİMUM fiyatını yazınız (veya 'Q' girerek çıkış yapın):");
                            String input2 = scanner.next();
                            if (input2.equalsIgnoreCase("Q")) {
                                return;
                            }
                            maxPrice = Double.parseDouble(input2);
                            isValidPrice = false;
                        } catch (Exception e) {
                            System.out.println("Geçersiz bir değer girdiniz.");
                            scanner.nextLine();
                        }
                    }

                    if (minPrice<=maxPrice) {
                        for (Notebook notebook : database.getNotebooks()) {
                            if (notebook.getPrice() >= minPrice && notebook.getPrice() <= maxPrice) {
                                database.getFilteredNotebooks().add(notebook);
                            }
                        }
                        list(database.getFilteredNotebooks());
                        isValid = false;
                    } else {
                        System.out.println("\nMinimum fiyat, maksimum fiyattan fazla olamaz.");
                        System.out.println("""

                        Hangi kategoriye göre filtrelemek istersiniz?
                        <M> Marka <F> Fiyat <Q> Geri Dön""");
                    }


                }

                case 'Q', 'q' -> isValid = false;
                default -> System.out.println("Geçersiz bir harf girdiniz.");
            }
        }

    }
    public void listNotebook() {

        System.out.println("""

                Hangi kategoriye göre sıralamak istiyorsunuz?
                <A> İsime göre (A-Z) <Z> İsime göre (Z-A) <F> Fiyata göre (Artan) <G> Fiyata göre (Azalan) <Q> Geri Dön""");

        boolean isValid = true;

        while (isValid) {
            System.out.print("Tercihiniz: ");
            char input = scanner.next().charAt(0);
            switch (input) {
                case 'A', 'a' -> {
                    if (database.getFilteredNotebooks().isEmpty()) {
                        database.getNotebooks().sort(Comparator.comparing(Notebook::getName));
                        list(database.getNotebooks());
                    } else {
                        database.getFilteredNotebooks().sort(Comparator.comparing(Notebook::getName));
                        list(database.getFilteredNotebooks());
                    }
                    isValid = false;
                }
                case 'Z', 'z' -> {
                    if (database.getFilteredNotebooks().isEmpty()) {
                        database.getNotebooks().sort(Comparator.comparing(Notebook::getName).reversed());
                        list(database.getNotebooks());
                    } else {
                        database.getFilteredNotebooks().sort(Comparator.comparing(Notebook::getName).reversed());
                        list(database.getFilteredNotebooks());
                    }
                    isValid = false;
                }
                case 'F', 'f' -> {
                    if (database.getFilteredNotebooks().isEmpty()) {
                        database.getNotebooks().sort(Comparator.comparing(Notebook::getPrice));
                        list(database.getNotebooks());
                    } else {
                        database.getFilteredNotebooks().sort(Comparator.comparing(Notebook::getPrice));
                        list(database.getFilteredNotebooks());
                    }
                    isValid = false;
                }
                case 'G', 'g' -> {
                    if (database.getFilteredNotebooks().isEmpty()) {
                        database.getNotebooks().sort(Comparator.comparing(Notebook::getPrice).reversed());
                        list(database.getNotebooks());
                    } else {
                        database.getFilteredNotebooks().sort(Comparator.comparing(Notebook::getPrice).reversed());
                        list(database.getFilteredNotebooks());
                    }
                    isValid = false;
                }
                case 'Q', 'q' -> isValid = false;
                default -> System.out.println("Geçersiz bir değer girdiniz.");
            }
        }

    }
    public Brand chooseBrand() {
        String input;
        Brand choosenBrand = null;
        boolean isValidBrand = true;

        while(isValidBrand) {
            System.out.print("\nNotebook'un markasını giriniz: ");
            input = scanner.next().trim();

            if (input.equalsIgnoreCase("q")) {
                return new Brand("quit");
            }

            for (Brand brand : database.getBrands()) {
                if (brand.getName().equalsIgnoreCase(input)) {
                    isValidBrand = false;
                    choosenBrand = brand;
                }
            }

            if (choosenBrand == null) {
                System.out.println("\nSeçtiğiniz markanın mağazamızda satışı yoktur.\nLütfen farklı bir marka ismi giriniz.");
            }

        }
        return choosenBrand;
    }
    public String chooseName() {
        String name;

        while(true) {
            System.out.print("\nNotebook'un ismini giriniz: ");
            StringBuilder updatedName = new StringBuilder();
            name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("q")) {
                return "quit";
            }

            if (!name.isEmpty()) {
                String[] words = name.split(" ");

                for (String word : words) {
                    name = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                    updatedName.append(name).append(" ");
                }

                name = updatedName.toString().trim();
                break;
            }
            else {
                System.out.println("Notebook ismi boş veya geçersiz.");
            }
        }
        return name;

    }
    public double choosePrice() {
        double price;

        while(true) {
            System.out.print("\nNotebook'un fiyatını giriniz: ");
            String input = scanner.nextLine();
            input = input.replace(',', '.');

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                price = Double.parseDouble(input);
                if (price < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir fiyat giriniz.");
            }

        }
        return price;
    }
    public int chooseMemory() {
        int memory;

        while(true) {
            System.out.print("\nNotebook'un hafızasını (GB) giriniz: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                memory = Integer.parseInt(input);
                if (memory < 0) {
                    throw new Exception();
                }
                break;

            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir rakam giriniz.");
            }
        }
        return memory;
    }
    public double chooseScreenSize() {
        double screenSize;

        while(true) {
            System.out.print("\nNotebook'un ekran boyutunu (inç) giriniz: ");
            String input = scanner.nextLine();
            input = input.replace(',', '.');

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                screenSize = Double.parseDouble(input);
                if (screenSize < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir değer giriniz.");
            }

        }
        return screenSize;
    }
    public double chooseBatteryPower() {
        double batteryPower;

        while(true) {
            System.out.print("\nNotebook'un pil gücünü (mAh) giriniz: ");
            String input = scanner.nextLine();
            input = input.replace(',', '.');

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                batteryPower = Double.parseDouble(input);
                if (batteryPower < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir değer giriniz.");
            }

        }
        return batteryPower;
    }
    public int chooseRAM() {
        int RAM ;

        while(true) {
            System.out.print("\nNotebook'un RAM miktarını (GB) giriniz: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                RAM = Integer.parseInt(input);
                if (RAM < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir değer giriniz.");
            }
        }
        return RAM;
    }
    public String chooseColor() {
        String color = null;

        while(true) {

            System.out.print("\nNotebook'un rengini giriniz: ");
            StringBuilder updatedColor = new StringBuilder();

            try {
                color = scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println("Lütfen geçerli bir renk giriniz.");
            }

            if (color != null && color.equalsIgnoreCase("q")) {
                return "quit";
            }

            if (color != null && !color.trim().isEmpty() && color.matches("^[a-zA-ZçÇğĞıİIiöÖşŞüÜ\\s]+$")) {
                String[] words = color.split(" ");

                for (String word : words) {
                    color = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                    updatedColor.append(color).append(" ");
                }

                color = updatedColor.toString().trim();
                break;
            }
            else {
                System.out.println("Lütfen geçerli bir renk giriniz.");
            }

        }
        return color;
    }
    public int chooseDiscountRate() {
        int discountRate;

        while (true) {
            System.out.print("\nNotebook'un indirim oranını (%) giriniz: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                discountRate = Integer.parseInt(input);
                if (discountRate < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir değer giriniz.");
            }
        }
        return discountRate;
    }
    public int chooseAmountOfStock() {
        int amountOfStock;

        while (true) {
            System.out.print("\nNotebook'un stoktaki miktarını giriniz: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                amountOfStock = Integer.parseInt(input);
                if (amountOfStock < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Lütfen geçerli bir değer giriniz.");
            }
        }
        return amountOfStock;
    }


    @Override
    public int getProductID() {
        return 2;
    }
    @Override
    public String getProductName() {
        return "Notebook";
    }
    @Override
    public int getId() {
        return id;
    }

}
