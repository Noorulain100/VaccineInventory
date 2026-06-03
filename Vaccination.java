package admin;

import temp.Driver;
import temp.Vaccine;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Vaccination extends JFrame {
    private JPanel controls;
    private JPanel table;
    private JTextField center;
    private JTextField type;
    private JButton searchCenter;
    private JButton searchType;
    private ArrayList<Vaccine> vaccines;
    public Vaccination(){
        controls = new JPanel(new FlowLayout());
        table = new JPanel(new BorderLayout());
        center = new JTextField(10);
        type = new JTextField(10);
        searchCenter = new JButton("Search By Center");
        searchType = new JButton("Search By Type");
        controls.add(new JLabel("Center: "));
        controls.add(center);
        controls.add(searchCenter);
        controls.add(new JLabel("Type: "));
        controls.add(type);
        controls.add(searchType);
        setLayout(new GridLayout(2,1));
        add(controls);
        add(table);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);


        searchType.addActionListener(e->{
            try {
                vaccines = Driver.readVaccines();
                table.removeAll();
                table.updateUI();
                String data = "";
                for (Vaccine v:vaccines){
                    if (v.getName().contains(type.getText())) {
                        data += v.display();
                    }
                }
                table.add(new JLabel(data),BorderLayout.PAGE_START);
            } catch (FileNotFoundException x) {
                x.printStackTrace();
            }
        });
        searchCenter.addActionListener(e->{
            try {
                vaccines = Driver.readVaccines();
                table.removeAll();
                table.updateUI();
                if (center.getText().equalsIgnoreCase("c1")){
                    table.add(new JLabel(vaccines.get(0).display()),BorderLayout.PAGE_START);
                }else if (center.getText().equalsIgnoreCase("c2")){
                    table.add(new JLabel(vaccines.get(1).display()),BorderLayout.PAGE_START);
                }else if (center.getText().equalsIgnoreCase("c3")){
                    table.add(new JLabel(vaccines.get(2).display()),BorderLayout.PAGE_START);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        });
    }
}
