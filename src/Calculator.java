import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class Calculator {


	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	

	
	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}

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
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
        String xml = null;
        try{
            xml = new String(Files.readAllBytes(Paths.get("items.xml")));
        } catch (IOException e){

        }
        XStream xStream = new XStream();
        xStream.alias("items", ItemList.class);
        xStream.alias("item", Item.class);
        xStream.alias("parameter", Parameter.class);
        xStream.addImplicitCollection(ItemList.class, "list");

        ArrayList<Item> items = (ArrayList<Item>)xStream.fromXML(xml);//new ArrayList<Item>();
        //ArrayList<Item> items = itemList.getList();
		/*Random rand = new Random();
		for(int i=0;i<160;i++){
			int place = randInt(0,3);
			Parameter par1 = generateParameter();
			Parameter par2 = generateParameter();
			Parameter par3 = generateParameter();
			String name = generateString(rand,"abcdefghijklmnopqrstuvwxyz", 10);
			Item temp = new Item(place, par1, par2, par3, name);
			items.add(temp);
		}
		ArrayList<ArrayList<Item>> result = find(items, 1, 90, 4, 90, 5, 80);
		for (int j=0;j<result.size();j++){
			ArrayList<Item> temp = result.get(j);
			for (int i=0;i<temp.size();i++){
				System.out.println("name " + temp.get(i).getName() + "       " + temp.get(i).getPars()[0] + "         " + temp.get(i).getPars()[1] + "       " + temp.get(i).getPars()[2] + "          " + temp.get(i).getPars()[3] + "         " + temp.get(i).getPars()[4] + "          " + temp.get(i).getPars()[5] + "          " + temp.get(i).getPars()[6] + "          " + temp.get(i).getPars()[7]); 
			}
			System.out.println("");
		}
		


        xml = xStream.toXML(items);

        try{
            java.io.FileWriter fw = new java.io.FileWriter("items.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e){

        }


	}*/

}
