import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Nikolay on 04.03.2015.
 */
public class UI extends JFrame implements TableModelListener {
    private JTable itemsTable;
    private JPanel panel1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton calculateButton;
    public ArrayList<Item> itemList;
    private ItemTableModel itemTableModel;
    private XStream xStream;
    private String xml;

    public UI() {

        setTitle("База экипировки");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1041, 741);
        JPanel contentPane = panel1;
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        xml = null;
        try{
            xml = new String(Files.readAllBytes(Paths.get("items.xml")));
        } catch (IOException e){

        }
        xStream = new XStream();
        xStream.alias("items", ItemList.class);
        xStream.alias("item", Item.class);
        xStream.alias("parameter", Parameter.class);
        xStream.addImplicitCollection(ItemList.class, "list");

        itemList = (ArrayList<Item>)xStream.fromXML(xml);
        ArrayList<String> itemColumns = new ArrayList<String>();
        itemColumns.add("Имя");
        itemColumns.add("Одето");
        itemColumns.add("Уровень");
        itemColumns.add("Макс. ур.");
        itemColumns.add("Параметр 1");
        itemColumns.add("Параметр 2");
        itemColumns.add("Параметр 3");
        itemColumns.add("Слот");
        itemTableModel = new ItemTableModel(itemList, itemColumns);

        itemsTable.setModel(itemTableModel);
        itemsTable.getModel().addTableModelListener(this);


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int itemNumber = itemsTable.getSelectedRow();
                deleteItem(itemNumber);
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddItemDialog dialog = new AddItemDialog();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setVisible(true);
                try{
                    xml = new String(Files.readAllBytes(Paths.get("items.xml")));
                } catch (IOException e1){

                }
                itemList = (ArrayList<Item>)xStream.fromXML(xml);
                updateItemList();
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditItemDialog dialog = new EditItemDialog(itemsTable.getSelectedRow(), itemList);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setVisible(true);
                try{
                    xml = new String(Files.readAllBytes(Paths.get("items.xml")));
                } catch (IOException e1){

                }
                itemList = (ArrayList<Item>)xStream.fromXML(xml);
                updateItemList();
            }
        });
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculatorDialog dialog = new CalculatorDialog();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setVisible(true);
                /*try{
                    xml = new String(Files.readAllBytes(Paths.get("items.xml")));
                } catch (IOException e1){

                }
                itemList = (ArrayList<Item>)xStream.fromXML(xml);
                updateItemList();*/
            }
        });

    }

    private void deleteItem(int itemNumber){
        itemList.remove(itemNumber);
        updateItemList();
    }

    public void updateItemList(){
        itemTableModel.updateModel(itemList);
        itemTableModel.fireTableDataChanged();
        String xml = xStream.toXML(itemList);

        try{
            java.io.FileWriter fw = new java.io.FileWriter("items.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e){

        }
    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        System.out.println(row + " " + column);
        TableModel model = (TableModel)e.getSource();
        //String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        if (column>-1) {

            Item old = itemTableModel.modelData.get(row);
            Item item = null;

            switch (column) {
                case 0:
                    item = new Item(old.getPlacement(), old.getPar1().getLevel(), old.getPar1(), old.getPar2(), old.getPar3(), data.toString(), old.isEquipped());
                    System.out.println("name " + item.getName() + "       " + item.getPars()[0] + "         " + item.getPars()[1] + "       " + item.getPars()[2] + "          " + item.getPars()[3]
                            + "         " + item.getPars()[4] + "          " + item.getPars()[5] + "          " + item.getPars()[6] + "          " + item.getPars()[7]);
                    break;
                case 1:
                    item = new Item(old.getPlacement(), old.getPar1().getLevel(), old.getPar1(), old.getPar2(), old.getPar3(), old.getName(), (Boolean) data);
                    System.out.println("name " + item.getName() + "       " + item.getPars()[0] + "         " + item.getPars()[1] + "       " + item.getPars()[2] + "          " + item.getPars()[3]
                            + "         " + item.getPars()[4] + "          " + item.getPars()[5] + "          " + item.getPars()[6] + "          " + item.getPars()[7]);
                    break;
                case 2:
                    item = new Item(old.getPlacement(), (Integer) data, old.getPar1(), old.getPar2(), old.getPar3(), old.getName(), old.isEquipped());
                    System.out.println("name " + item.getName() + "       " + item.getPars()[0] + "         " + item.getPars()[1] + "       " + item.getPars()[2] + "          " + item.getPars()[3]
                            + "         " + item.getPars()[4] + "          " + item.getPars()[5] + "          " + item.getPars()[6] + "          " + item.getPars()[7]);
                    break;
            }

            itemList.remove(row);
            itemList.add(row, item);
            itemTableModel.updateModel(itemList);
            itemTableModel.fireTableDataChanged();

            String xml1 = xStream.toXML(itemList);

            try {
                java.io.FileWriter fw = new java.io.FileWriter("items.xml");
                fw.write(xml1);
                fw.close();
            } catch (IOException ex) {

            }
        }

    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.setVisible(true);
    }

}
