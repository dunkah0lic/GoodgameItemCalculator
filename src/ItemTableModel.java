import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Nikolay on 06.03.2015.
 */
public class ItemTableModel extends AbstractTableModel {

    protected List<Item> modelData;
    protected List<String> columnNames;

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.size();
    }

    @Override
    public int getRowCount() {

        return modelData.size();
    }

    private String getReadableParameter(Parameter par){
        String result = "error";
        switch(par.getKind()){
            case 0: result = "Сила ББ +" + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 1: result = "Сила СС +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 2: result = "Стена +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 3: result = "Ров +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 4: result = "Ворота +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 5: result = "Скорость +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 6: result = "Обнаружение +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 7: result = "Мародер +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 8: result = "Огонь +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 9: result = "Честь +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 10: result = "Слава +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 11: result = "Уничтожение +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 12: result = "Снаряжение +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            case 13: result = "Эконом +"  + par.getInitValue()+ " (" + par.getValue() + ")";
                break;
            default: result = "error";
                break;

        }
        return result;
    }

    private String getReadablePlacement(int placement){
        String result = "error";
        switch(placement){
            case 0: result = "Шапка";
                break;
            case 1: result = "Артефакт";
                break;
            case 2: result = "Оружие";
                break;
            case 3: result = "Доспех";
                break;
            default: result = "error";
                break;

        }
        return result;
    }

    public boolean isCellEditable(int row, int col)
    { return true; }
    public void setValueAt(Object value, int row, int col) {
        switch (col){
            case 2: modelData.get(row).getPar1().setLevel(Integer.valueOf(value.toString()));
        }
        fireTableCellUpdated(row, col);

    }

    @Override
    public Object getValueAt(int row, int col) {
        Object result = null;
        switch (col) {
            case 0: result = modelData.get(row).getName();
                break;
            case 1: if (modelData.get(row).isEquipped()){
                result = "Одето";
            } else {
                result = "На складе";
            }
                break;
            case 2: result = modelData.get(row).getPar1().getLevel();
                break;
            case 3: result = modelData.get(row).getPar1().getMaxLevel();
                break;
            case 4: result = getReadableParameter(modelData.get(row).getPar1());
                break;
            case 5: result = getReadableParameter(modelData.get(row).getPar2());
                break;
            case 6: result = getReadableParameter(modelData.get(row).getPar3());
                break;
            case 7: result = getReadablePlacement(modelData.get(row).getPlacement());
                break;
        }
        return result;
    }

    @Override
    public String getColumnName(int col){
        return columnNames.get(col);//

    }

    public ItemTableModel(List<Item> data, List<String> col){
        modelData = data;
        columnNames = col;
    }

    public void updateModel(List<Item> data) {
        modelData = data;
    }

}