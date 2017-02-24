import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by joey on 12/8/16.
 */
public class StudentActionWindow implements ActionListener, KeyListener {

    State state = State.ADD;
    Object[] rowData;
    DefaultTableModel model;
    JTable table;


    JFrame frame = new JFrame();
        JPanel panel = new JPanel();
            JLabel firstNamelbl = new JLabel("First Name: ");
            JTextField fnField = new JTextField(15);
            JLabel lastNamelbl = new JLabel("Last Name : ");
            JTextField lnField = new JTextField(15);
            JLabel cunyIDlbl = new JLabel("Cuny ID : ");
            JTextField cunyField = new JTextField(15);
            JLabel gpalbl = new JLabel("GPA : ");
            JTextField gpaField = new JTextField(15);
            JLabel venuslbl = new JLabel("Venus Login : ");
            JTextField venusField = new JTextField(15);

            JButton goOption = new JButton("Modify Existing Student");
            JButton cancel = new JButton("Cancel");

    public StudentActionWindow(Object[] rowData, JTable table, State state){

        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        fnField.addKeyListener(this);
        lnField.addKeyListener(this);
        cunyField.addKeyListener(this);
        panel.add(firstNamelbl);
        panel.add(fnField);
        panel.add(lastNamelbl);
        panel.add(lnField);
        panel.add(cunyIDlbl);
        panel.add(cunyField);
        panel.add(gpalbl);
        panel.add(gpaField);
        panel.add(venuslbl);
        venusField.setEditable(false);
        panel.add(venusField);


        goOption.addActionListener(this);
        cancel.addActionListener(this);
        panel.add(goOption);
        panel.add(cancel);

        frame.add(panel);
        frame.setContentPane(panel);
        frame.setVisible(true);

        this.state = state;
        this.table = table;
        this.model = (DefaultTableModel) table.getModel();
        this.rowData = rowData;
        setState();

    }




    public void setFieldsFromRowData(Object[] rowData){
        fnField.setText(rowData[1].toString());
        lnField.setText(rowData[2].toString());
        cunyField.setText(rowData[3].toString());
        gpaField.setText(rowData[4].toString());
        venusField.setText(rowData[5].toString());
    }

    void setState() {
        switch (this.state) {
            case ADD:
                frame.setTitle("Create A Student Record");
                goOption.setText("Add New Student");
                break;
            case DELETE:
                setFieldsFromRowData(rowData);
                frame.setTitle("Delete A Student Record");
                goOption.setText("Purge Student Record");

                break;
            case MODIFY:
                setFieldsFromRowData(rowData);
                frame.setTitle("Modify Current Student Record");
                goOption.setText("Update Student Record");
                break;
            default:
        }
    }

    public static boolean studentIsValid(String $fn, String $ln, String $gpa, String $id, String $venus, boolean showAlerts){
        String fn, ln, gpa, id, venus;
        fn = $fn.trim();
        ln = $ln.trim();
        gpa = $gpa.trim();
        id = $id.trim();
        venus = $venus.trim();
        boolean valid = true;
        String message = new String("Accepted!");
        if (!isValidName(fn)){
            message = "Please input valid First Name";
            valid = false;
        }
        else if (!isValidName(ln)) {
            message = "Please input valid Last Name";
            valid = false;
        }
        else if (!isGPAValid(gpa)){
            message = "Please input valid GPA";
            valid = false;
        }
        else if (!isValidID(id)){
            message = "Please input valid ID";
            valid = false;
        }
        else if (!isValidVenusLogin(venus, ln, fn, id)){
            message = "Please input valid Venus Login";
            valid = false;
        }
        if(showAlerts && !valid) {
            JOptionPane.showMessageDialog(null, message);
        }
        return valid;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(goOption)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Are you sure you want to ");
            sb.append(this.state);
            sb.append(" this record?");
            int confirm = JOptionPane.showConfirmDialog(null, sb.toString(), " Confirm", JOptionPane.OK_CANCEL_OPTION);
            if(studentIsValid(fnField.getText(),
                            lnField.getText(),
                            gpaField.getText(),
                            cunyField.getText(),
                            venusField.getText(), true)) {

                if (confirm == JOptionPane.OK_OPTION) {
                    System.out.print("hello!");
                    switch (this.state) {
                        case MODIFY:
                            updateRow(rowData, model);
                            break;
                        case ADD:
                            addNewRow(model);
                            break;
                        case DELETE:
                            deleteRow(table);
                            break;
                    }
                    frame.setVisible(false);
                }
            }
        }
        frame.setVisible(false);
    }

    private void deleteRow(JTable table) {
        model.removeRow(table.getSelectedRow());
    }

    private void addNewRow(DefaultTableModel m) {
        DefaultTableModel model = m;
        Object[] newStudent = new Object[6];
        newStudent[0] = model.getRowCount();
        newStudent[1] = fnField.getText();
        newStudent[2] = lnField.getText();
        newStudent[3] = cunyField.getText();
        newStudent[4] = gpaField.getText();
        newStudent[5] = venusField.getText();
        model.addRow(newStudent);
    }

    private void updateRow(Object[] rowData, DefaultTableModel model) {
        int row = (int)rowData[0] - 1;
        model.setValueAt(fnField.getText(), row, 1);
        model.setValueAt(lnField.getText(), row, 2);
        model.setValueAt(cunyField.getText(), row, 3);
        model.setValueAt(gpaField.getText(), row, 4);
        model.setValueAt(venusField.getText(), row, 5);
    }

    public static String createVenusLogin(String fname, String lname, String cunyID){
        try {
            fname = fname.trim();
            lname = lname.trim();
            cunyID = cunyID.trim();
            String last = lname.substring(0, 2).toLowerCase();
            String first = fname.substring(0, 2).toLowerCase();
            String id = cunyID.substring(4, 8);
            return last + first + id;
        }
        catch(Exception ex){
            return "";
        }
    }

    public static boolean isGPAValid(String GPA){
        try {
            return Double.parseDouble(GPA) <= 4 && Double.parseDouble(GPA) >= 0 ? true : false;
        }
        catch(NumberFormatException e){
            return false;
        }

    }

    public static boolean isValidName(String name){
        return name.length() >= 2? true: false;
    }

    public static boolean isValidID(String id){
        return id.length() == 8? true: false;
    }

    public static boolean isValidVenusLogin(String venusLogin, String lname, String fname, String id) {
        try {
            if (!venusLogin.substring(0, 2).toLowerCase().equals(lname.substring(0, 2).toLowerCase())) {
                return false;
            }
            if (!venusLogin.substring(2, 4).toLowerCase().equals(fname.substring(0, 2).toLowerCase())) {
                return false;
            }
            return venusLogin.substring(4, 8).toLowerCase().equals(id.substring(4, 8));
        }
        catch(Exception e){
            return false;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String fn, ln, id;
        fn = fnField.getText();
        ln = lnField.getText();
        id = cunyField.getText();
        venusField.setText(createVenusLogin(fn, ln, id));
    }
}
