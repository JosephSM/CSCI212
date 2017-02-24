import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Scanner;
import java.awt.FileDialog;

/**
 * Created by joey on 12/10/16.
 */
public class StudentFileIO {

    JFileChooser fileChooser;
    FileDialog fileDialog;
    JFrame origin;

    public StudentFileIO(JFrame origin) {
        this.origin = origin;
    }


    public void Import(DefaultTableModel model) {
        fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File selectedFile = fileChooser.getSelectedFile();
        getStudentDataFromFile(selectedFile, model);
    }


    public void Export(DefaultTableModel model) {
        fileDialog = new FileDialog(origin);
        fileDialog.setMode(1);
        fileDialog.setTitle("Export Data to File");
        fileDialog.setVisible(true);
        String file = fileDialog.getDirectory() + fileDialog.getFile();
        writeModelToFile(model, file);
    }


    private void writeModelToFile(DefaultTableModel model, String file) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(file));
            for(int row = 0; row < model.getRowCount();row++){
                for (int col = 1; col < model.getColumnCount(); col++) {
                    fw.write(model.getValueAt(row, col).toString().trim());
                    if(col != model.getColumnCount()-1){
                        fw.write(", ");
                    }
                }
                fw.write("\r\n");
                fw.flush();
            }

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void getStudentDataFromFile(File file, DefaultTableModel model) {
        boolean invalidRows = false;
        int counter = 1;
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while (sc.hasNextLine()) {
            Object[] rowData = new Object[6];
            String[] strData = sc.nextLine().split(",");
            if(strData.length != 5) {
                continue;
            }
            rowData[0] = counter;
            for(int i = 1; i <= 5; i++){
                rowData[i] = strData[i-1].trim();
            }
            if(StudentActionWindow.studentIsValid(strData[0],strData[1],strData[3],strData[2],strData[4],false)) {
                model.addRow(rowData);
                counter++;
            }
            else{
                invalidRows = true;
            }
        }
        if(invalidRows){
            JOptionPane.showMessageDialog(null, "To maintain a clean student record, invalid Data was not imported");
        }
    }

}

