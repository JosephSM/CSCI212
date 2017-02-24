import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.TableRowSorter;



public class App implements ActionListener, MouseListener, KeyListener{

    //Project Structure
    JFrame frame = new JFrame();
      JMenuBar menu = new JMenuBar(); // File -> open, export, quit // help -> about
        JMenu file = new JMenu("File");
          JMenuItem open = new JMenuItem("Open", 79);
          JMenuItem export = new JMenuItem("Export", 69);
          JMenuItem quit = new JMenuItem("Quit", 81);
        JMenu help = new JMenu("Help");
          JMenuItem about = new JMenuItem("About", 65);

    JPanel panel = new JPanel(); // panel -> label:searchby -> combobox -> textfield -> addbtn -> deletebtn -> table -> export btn
       JLabel searchBy = new JLabel("Search By: ");
       String[] columnHeaders = { "Row ID", "First Name", "Last Name", "CUNY ID", "GPA", "Venus Login" };
       JComboBox comboBoxOfColumnHeaders = new JComboBox(columnHeaders);
       JTextField searchField = new JTextField(10);
       JButton addbtn = new JButton("Add");
       JButton dltbtn = new JButton("Delete");

    //Table vars

    JTable table = new JTable(); // tbc
    DefaultTableModel tableModel;


    //Final Export Button
    JButton exportbtn = new JButton("Export Data");

    public App(){
        //setting frame
        frame.setSize(550, 580);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.setTitle("My Final Project");
        //adding menu items to menus and menus to menubar
        file.add(open);
        file.add(export);
        file.addSeparator();
        file.add(quit);
        menu.add(file);
        help.add(about);
        menu.add(help);
        frame.setJMenuBar(menu);

        //setting shortcuts

        open.setAccelerator(KeyStroke.getKeyStroke(79, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        export.setAccelerator(KeyStroke.getKeyStroke(69, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        quit.setAccelerator(KeyStroke.getKeyStroke(81, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        about.setAccelerator(KeyStroke.getKeyStroke(65, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));


        //adding listeners to all buttons and menu items on the frame
        open.addActionListener(this);
        export.addActionListener(this);
        quit.addActionListener(this);
        about.addActionListener(this);
        exportbtn.addActionListener(this);
        addbtn.addActionListener(this);
        dltbtn.addActionListener(this);
        table.addMouseListener(this);
        searchField.addKeyListener(this);
        searchField.addActionListener(this);
        searchField.setActionCommand("searchField");
        comboBoxOfColumnHeaders.addActionListener(this);
        comboBoxOfColumnHeaders.setActionCommand("searchCombo");


        //adding contents to panel
        panel.add(searchBy);
        panel.add(comboBoxOfColumnHeaders);
        panel.add(searchField);
        panel.add(addbtn);
        panel.add(dltbtn);


        //adding panel to frame
        frame.setContentPane(panel);


        //create column headers for out model
            //Data will be added later
        table.setModel(new DefaultTableModel(){

            // cells not editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });


        tableModel = (DefaultTableModel)table.getModel();
        for(String col: columnHeaders)
            tableModel.addColumn(col);
        table.setAutoCreateRowSorter(true);


        frame.add(table);
        //necessary to for scrolling and places the column headers
        //at the top of the table
        frame.add(new JScrollPane(table));
        frame.add(exportbtn);

        frame.setVisible(true);
    }

    public Object[] getSelectedRowArray(){
        Object[] selectedData = new Object[6];
        int selectedRow = table.getSelectedRow();

        if(selectedRow != -1) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                selectedData[i] = table.getValueAt(selectedRow, i);
            }
            return selectedData;
        }
        return null;


    }

    private void FilterRowBasedOnSearch() {

        RowFilter<Object,Object> rowFilter = new RowFilter<Object,Object>() {
            @Override
            public boolean include(javax.swing.RowFilter.Entry<? extends Object, ? extends Object> entry) {
                boolean shouldInclude = false;

                int selectedBox = comboBoxOfColumnHeaders.getSelectedIndex();
                String searchText = searchField.getText().toLowerCase();
                switch(selectedBox) {
                    case 4:
                        try {
                            if (Double.parseDouble(entry.getStringValue(selectedBox)) >= Double.parseDouble(searchText)) {
                                shouldInclude = true;
                            }
                        }
                        catch (Exception e){
                            shouldInclude = true;
                        }
                        break;
                    case 0: //Search at column index 0
                    case 3:                                             //.equals(searchText) was
                        if(entry.getStringValue(selectedBox).toLowerCase().startsWith(searchText)) {
                            shouldInclude = true;
                        }
                        break;
                    case 1:
                    case 2: //search at column index 1
                    case 5:
                      if(entry.getStringValue(selectedBox).toLowerCase().contains(searchText)) {
                          shouldInclude = true;
                          System.out.println(entry.getStringValue(selectedBox).toLowerCase());
                          System.out.println(searchText);
                       }
                        break;
                }
                return shouldInclude;
            }
        };
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        sorter.setRowFilter(rowFilter);
        table.setRowSorter(sorter);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Open":
                new StudentFileIO(frame).Import((DefaultTableModel)table.getModel());
                break;
            case "Quit":
                System.exit(0);
            case "Export":
            case "Export Data":
                new StudentFileIO(frame).Export((DefaultTableModel)table.getModel());
                break;
            case "About":
                JOptionPane.showMessageDialog(null,"Hello! This is my project.  I hope you like it.");
                break;
            case "Add":
                new StudentActionWindow(getSelectedRowArray(), table, State.ADD);
                break;
            case "Delete":
                if(getSelectedRowArray() == null){
                    JOptionPane.showMessageDialog(null, "Nothing Selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new StudentActionWindow(getSelectedRowArray(), table, State.DELETE);
                }
                break;
            case "searchField":
            case "searchCombo":
                FilterRowBasedOnSearch();
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 && e.getSource() == table){
            new StudentActionWindow(getSelectedRowArray(), table, State.MODIFY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        FilterRowBasedOnSearch();
    }

}