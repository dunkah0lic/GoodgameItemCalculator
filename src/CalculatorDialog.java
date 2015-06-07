import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class CalculatorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox par1CB;
    private JComboBox par3CB;
    private JComboBox par2CB;
    private JTextField value1TF;
    private JTextField value2TF;
    private JTextField value3TF;
    private JTextArea resultTA;
    private JTextField value4TF;
    private JTextField value5TF;
    private JTextField value6TF;
    private JTextField value7TF;
    private JTextField value8TF;
    private JTextField value9TF;
    private JTextField value10TF;
    private JTextField value11TF;
    private JTextField value12TF;
    private JTextField value14TF;
    private JTextField value13TF;
    private JComboBox equippedCB;
    private ArrayList<JTextField> textFields;

    public CalculatorDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Подобрать");
        setBounds(100, 100, 1300, 400);

        equippedCB.addItem("Одето");
        equippedCB.addItem("На складе");
        equippedCB.addItem("Одето + на складе");



        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        textFields = new ArrayList<JTextField>();
        textFields.add(value1TF);
        textFields.add(value2TF);
        textFields.add(value3TF);
        textFields.add(value4TF);
        textFields.add(value5TF);
        textFields.add(value6TF);
        textFields.add(value7TF);
        textFields.add(value8TF);
        textFields.add(value9TF);
        textFields.add(value10TF);
        textFields.add(value11TF);
        textFields.add(value12TF);
        textFields.add(value13TF);
        textFields.add(value14TF);
        String xml=null;
        try{
            xml = new String(Files.readAllBytes(Paths.get("items.xml")));
        } catch (IOException e){

        }
        Random random = new Random();
        XStream xStream = new XStream();
        xStream.alias("items", ItemList.class);
        xStream.alias("item", Item.class);
        xStream.alias("parameter", Parameter.class);
        xStream.addImplicitCollection(ItemList.class, "list");

        ArrayList<Item> itemList = (ArrayList<Item>)xStream.fromXML(xml);
        LinkedHashMap<Integer, Float> limits = new LinkedHashMap<Integer, Float>();

        for (int i=0;i<textFields.size();i++){
            if (!textFields.get(i).getText().trim().isEmpty()){
                limits.put(i, Float.valueOf(textFields.get(i).getText()));
            }
        }
        ArrayList<ArrayList<Item>> results = null;
        switch (equippedCB.getSelectedIndex()){
            case 0: results = Calculator.select(itemList, limits, true);
                break;
            case 1: results = Calculator.select(itemList, limits, false);
                break;
            case 2: results = Calculator.select(itemList, limits);
                break;
            default:
                break;
        }
        //ArrayList<ArrayList<Item>> results = Calculator.select(itemList, limits);


        String resultString = "";
        for (ArrayList<Item> list: results) {
            for (Item item: list){
                int maxNameLength = 50;
                String place = "";
                switch (item.getPlacement()) {
                    case 0: place = "Шапка ";
                        break;
                    case 1: place = "Артефакт ";
                        break;
                    case 2: place = "Оружие ";
                        break;
                    case 3: place = "Доспех ";
                        break;
                }
                String equipped = "";
                if (item.isEquipped()){
                    equipped = " Одето";
                }else {
                    equipped = " На складе";
                }
                String tempString = place + item.getName() + equipped;
                tempString = tempString + generateString(random, " ", maxNameLength-tempString.length());
                for (Map.Entry<Integer,Float> entry: limits.entrySet()){
                    tempString = tempString +  " " + String.format("%.1f", item.getPars()[entry.getKey()]);
                }
                resultString = resultString + tempString + "\n";//resultString + String.format("%-30s",tempString) + " " + String.format("%.1f", item.getPars()[par1]) + " " + String.format("%.1f", item.getPars()[par2]) + " " + String.format("%.1f", item.getPars()[par3]) + "\n";
            }
            resultString = resultString + "\n";
        }

        resultTA.setText(resultString);
        //dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private JComboBox addKinds(JComboBox jComboBox){
        JComboBox result = jComboBox;
        result.addItem("Сила ББ");
        result.addItem("Сила СС");
        result.addItem("Стена");
        result.addItem("Ров");
        result.addItem("Ворота");
        result.addItem("Скорость");
        result.addItem("Обнаружение");
        result.addItem("Мародер");
        result.addItem("Огонь");
        result.addItem("Честь");
        result.addItem("Слава");
        return result;
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
}
