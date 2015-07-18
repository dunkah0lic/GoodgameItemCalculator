import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Calculator {

    public static ArrayList<ArrayList<Item>> select(ArrayList<Item> items, LinkedHashMap<Integer, Float> limits, boolean equipped){
        ArrayList<ArrayList<Item>> result = new ArrayList<ArrayList<Item>>();
        ArrayList<Item> hats = new ArrayList<Item>();
        ArrayList<Item> boots = new ArrayList<Item>();
        ArrayList<Item> weapons = new ArrayList<Item>();
        ArrayList<Item> armors = new ArrayList<Item>();
        for(int i=0;i<items.size();i++){
            if (items.get(i).isEquipped()==equipped) {
                switch (items.get(i).getPlacement()) {
                    case 0:
                        hats.add(items.get(i));
                        break;
                    case 1:
                        boots.add(items.get(i));
                        break;
                    case 2:
                        weapons.add(items.get(i));
                        break;
                    case 3:
                        armors.add(items.get(i));
                        break;
                    default:
                        break;
                }
            }
        }
        int size = limits.size();
        for (int i=0;i<hats.size();i++){
            float sums[] = new float[size];

            for (int j=0;j<boots.size();j++){
                for (int k=0;k<weapons.size();k++){
                    for (int l=0;l<armors.size();l++){
                        boolean over = true;
                        for (Map.Entry<Integer, Float> entry: limits.entrySet()){
                            int kind = entry.getKey();
                            if (hats.get(i).getPars()[kind] + boots.get(j).getPars()[kind] + weapons.get(k).getPars()[kind] + armors.get(l).getPars()[kind]<entry.getValue()-0.005) {
                                over = false;
                            }
                        }
                        if (over){
                            ArrayList<Item> temp = new ArrayList<Item>();
                            temp.add(hats.get(i));
                            temp.add(boots.get(j));
                            temp.add(weapons.get(k));
                            temp.add(armors.get(l));
                            result.add(temp);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static ArrayList<ArrayList<Item>> select(ArrayList<Item> items, LinkedHashMap<Integer, Float> limits){
        ArrayList<ArrayList<Item>> result = new ArrayList<ArrayList<Item>>();
        ArrayList<Item> hats = new ArrayList<Item>();
        ArrayList<Item> boots = new ArrayList<Item>();
        ArrayList<Item> weapons = new ArrayList<Item>();
        ArrayList<Item> armors = new ArrayList<Item>();
        for(int i=0;i<items.size();i++){
            switch (items.get(i).getPlacement()){
                case 0: hats.add(items.get(i));
                    break;
                case 1: boots.add(items.get(i));
                    break;
                case 2: weapons.add(items.get(i));
                    break;
                case 3: armors.add(items.get(i));
                    break;
                default:
                    break;
            }
        }
        int size = limits.size();
        for (int i=0;i<hats.size();i++){
            float sums[] = new float[size];

            for (int j=0;j<boots.size();j++){
                for (int k=0;k<weapons.size();k++){
                    for (int l=0;l<armors.size();l++){
                        boolean over = true;
                        for (Map.Entry<Integer, Float> entry: limits.entrySet()){
                            int kind = entry.getKey();
                            if (hats.get(i).getPars()[kind] + boots.get(j).getPars()[kind] + weapons.get(k).getPars()[kind] + armors.get(l).getPars()[kind]<entry.getValue()-0.005) {
                                over = false;
                            }
                        }
                        if (over){
                            ArrayList<Item> temp = new ArrayList<Item>();
                            temp.add(hats.get(i));
                            temp.add(boots.get(j));
                            temp.add(weapons.get(k));
                            temp.add(armors.get(l));
                            result.add(temp);
                        }
                    }
                }
            }
        }

        return result;
    }
	

}
