import java.util.ArrayList;

/**
 * Created by Nikolay on 04.03.2015.
 */
public class ItemList {

    private ArrayList<Item> list;

    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }

    public ItemList(){
        list = new ArrayList<Item>();
    }

    public void add(Item p){
        list.add(p);
    }
}