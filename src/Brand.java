public class Brand {

    private static int nextID = 1;
    private int id;
    private String name;

    public Brand(String name) {
        this.id = nextID;
        this.name = name;
        nextID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }




}
