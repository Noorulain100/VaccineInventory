package admin;

import temp.Driver;
import temp.People;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Home extends JPanel {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField dob;
    private JTextField cnic;
    private JTextField city;
    private JTextField age;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup buttonGroup;
    private JLabel appointed;
    private JLabel vaccinated;
    private JButton modify,makeAppointment,cancel,view;
    private JButton vaccination;
    private JPanel contentPane;
    private JPanel buttons1,buttons2;
    public Home(JPanel contentPane, People user, ArrayList<People> peopleList){
        this.contentPane = contentPane;
        firstName = new JTextField(10);
        lastName = new JTextField(10);
        dob = new JTextField(10);
        cnic = new JTextField(10);
        city = new JTextField(10);
        age = new JTextField(10);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);
        appointed = new JLabel("");
        appointed.setVisible(false);
        vaccinated = new JLabel("");
        modify = new JButton("Modify");
        makeAppointment = new JButton("Make appointment");
        vaccination = new JButton("View Vaccinations");
        cancel = new JButton("Cancel Appointment");
        view = new JButton("View Appointment");
        buttons1 = new JPanel(new FlowLayout());
        buttons2 = new JPanel(new FlowLayout());
        buttons1.add(modify);
        buttons1.add(view);
        buttons1.add(makeAppointment);
        buttons1.add(cancel);
        buttons2.add(vaccination);
        setLayout(new GridLayout(12,2,10,10));
        add(new JLabel("First Name"));
        add(new JLabel("Last Name"));
        add(firstName);
        add(lastName);
        add(new JLabel("Date of birth"));
        add(new JLabel("CNIC"));
        add(dob);
        add(cnic);
        add(new JLabel("City"));
        add(new JLabel("Age"));
        add(city);
        add(age);

        add(male);
        add(female);

        add(new JLabel("Has Appointment"));
        add(new JLabel("Vaccinated?"));
        add(appointed);
        add(vaccinated);
        update(user);
        add(buttons1);
        add(buttons2);

        modify.addActionListener(e->{
            String dobText = dob.getText();
            if (dobText.split("/").length != 3){
                JOptionPane.showMessageDialog(contentPane,"Invalid DOB format.");
                return;
            }
            user.setfname(firstName.getText());
            user.setlname(lastName.getText());
            user.setdateOfBirth(dobText);
            user.setCNIC(cnic.getText());
            user.setCity(city.getText());
            user.setAge(age.getText());
            user.setGender(male.isSelected() ? "male":"female");
            try {
                Driver.insert(peopleList);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showMessageDialog(contentPane,"Modification Done.");
        });

        makeAppointment.addActionListener(e->{
            if (user.isAppointmentSet()){
                JOptionPane.showMessageDialog(contentPane,"Appointment Already there.");
                return;
            }
            user.setAppointmentSet(true);
            try {
                Driver.insert(peopleList);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            update(user);
            JOptionPane.showMessageDialog(contentPane,"Appointment Made.");
        });

        cancel.addActionListener(e -> {
            if (!user.isAppointmentSet()){
                JOptionPane.showMessageDialog(contentPane,"No Appointments to Cancel.");
                return;
            }
            user.setAppointmentSet(false);
            try {
                Driver.insert(peopleList);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            update(user);
            JOptionPane.showMessageDialog(contentPane,"Appointment Canceled.");
        });

        view.addActionListener(e-> appointed.setVisible(true));
        vaccination.addActionListener(e->{
            new Vaccination().setVisible(true);
        });
    }

    private void update(People user){
        firstName.setText(user.getfname());
        lastName.setText(user.getlname());
        dob.setText(user.getdateOfBirth());
        cnic.setText(user.getCNIC());
        city.setText(user.getCity());
        age.setText(user.getAge());
        if (user.getGender().equals("male")) male.setSelected(true);
        else female.setSelected(true);
        appointed.setText(user.isAppointmentSet() ? "YES":"NO");
        vaccinated.setText(user.isVaccinated() ? "YES":"NO");
    }
}
