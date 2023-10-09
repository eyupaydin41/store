import java.util.ArrayList;

public class Database {

    private ArrayList<Brand> brands = new ArrayList<>();
    private ArrayList<Phone> phones = new ArrayList<>();
    private ArrayList<Notebook> notebooks = new ArrayList<>();
    private ArrayList<Phone> filteredPhones = new ArrayList<>();
    private ArrayList<Notebook> filteredNotebooks = new ArrayList<>();


    public void defaultProducts() {
        this.getBrands().add(new Brand("Apple"));
        this.getBrands().add(new Brand("Samsung"));
        this.getBrands().add(new Brand("Xiaomi"));
        this.getBrands().add(new Brand("Huawei"));
        this.getBrands().add(new Brand("MSI"));
        this.getBrands().add(new Brand("Lenovo"));
        this.getBrands().add(new Brand("Monster"));

        this.getNotebooks().add(new Notebook(this.getBrands().get(4), "MSI Thin", 32033, 0, 30, 1024, 13.3, 4000, 8, "Pembe"));
        this.getNotebooks().add(new Notebook(this.getBrands().get(6), "Monster Tulpar T7", 45199, 0, 100, 2048, 15.3, 4500, 32, "Siyah"));
        this.getNotebooks().add(new Notebook(this.getBrands().get(5), "Lenovo Vivobook", 16999, 0, 250, 512, 14.2, 4000, 10, "Beyaz"));
        this.getNotebooks().add(new Notebook(this.getBrands().get(4), "MSI Titan", 40168, 0, 500, 1024, 13.3, 3800, 16, "Altın"));
        this.getNotebooks().add(new Notebook(this.getBrands().get(6), "Monster Abra A5", 24899, 0, 30, 1024, 15.3, 5000, 16, "Siyah"));
        this.getNotebooks().add(new Notebook(this.getBrands().get(5), "Lenovo Thinkpad", 23499, 0, 300, 248, 15.3, 4500, 12, "Gri"));
        this.getNotebooks().add(new Notebook(this.getBrands().get(4), "MSI Katana", 73219, 0, 700, 2048, 14.2, 3500, 32, "Uzay Grisi"));

        this.getPhones().add(new Phone(this.getBrands().get(1), "Samsung S22", 24999, 0, 250, 256, 5.3, 4000, 8, "Gri", 32));
        this.getPhones().add(new Phone(this.getBrands().get(0), "Iphone 15", 76999, 0, 30, 64, 5.3, 4000, 8, "Koyu Mavi", 48));
        this.getPhones().add(new Phone(this.getBrands().get(2), "Redmi Note 10 Pro", 10500, 0, 100, 128, 5.6, 4500, 6, "Pembe", 24));
        this.getPhones().add(new Phone(this.getBrands().get(0), "Iphone 14", 56999, 0, 500, 128, 6.1, 3800, 6, "Uzay Grisi", 36));
        this.getPhones().add(new Phone(this.getBrands().get(1), "Samsung S23 Pro", 35999, 0, 300, 128, 6.1, 4500, 8, "Siyah", 48));
        this.getPhones().add(new Phone(this.getBrands().get(2), "Redmi Note 8", 7500, 0, 30, 64, 6.5, 5000, 4, "Mavi", 8));
        this.getPhones().add(new Phone(this.getBrands().get(0), "Iphone 13", 35999, 0, 700, 256, 5.3, 3500, 6, "Siyah", 18));
    }

    public ArrayList<Brand> getBrands() {
        return brands;
    }
    public ArrayList<Phone> getPhones() {
        return phones;
    }
    public ArrayList<Notebook> getNotebooks() {
        return notebooks;
    }

    public ArrayList<Phone> getFilteredPhones() {
        return filteredPhones;
    }

    public ArrayList<Notebook> getFilteredNotebooks() {
        return filteredNotebooks;
    }
}
