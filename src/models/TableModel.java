
package models;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel{

    private String[] col;
    private Object[][] rows;
    
    public TableModel(){
    }
    
    public TableModel(Object[][] data,String[] colName){
        
        rows=data;
        col=colName;
        
    }
    
    @Override
    public Class getColumnClass(int cols){
    
        if(cols == 11){
            return Icon.class;
        }
        else{
            return getValueAt(0,cols).getClass();
        }
        
    }
    
    @Override
    public int getRowCount() {
        
        return rows.length;
    
    }

    @Override
    public int getColumnCount() {
        
        return col.length;
    
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        
        return rows[rowIndex][colIndex];
    
    }
    
    @Override
    public String getColumnName(int cols){
    
        return col[cols];
        
    }
    
}
