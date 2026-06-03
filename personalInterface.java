package admin;

import temp.Driver;
import temp.People;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class personalInterface extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField cnic;
	private JButton login;
	private JPanel loginPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					personalInterface frame = new personalInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public personalInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		setContentPane(contentPane);
		firstName = new JTextField(10);
		firstName.setText("First name");
		cnic = new JTextField(10);
		cnic.setText("cnic");
		login = new JButton("Login");
		loginPanel = new JPanel(new FlowLayout());
		loginPanel.add(firstName);
		loginPanel.add(cnic);
		loginPanel.add(login);
		contentPane.add(new JLabel("Login"));
		contentPane.add(loginPanel);

		login.addActionListener(e->{
			String name = firstName.getText();
			String cnicText = cnic.getText();
			ArrayList<People> peopleList = new ArrayList<>();
			try {
				Driver.read(peopleList);
				boolean found = false;
				People user = null;
				for (People people:peopleList){
					if (people.getfname().equals(name) & people.getCNIC().equals(cnicText)){
						found = true;
						user = people;
						break;
					}
				}
				if (found){
					contentPane.removeAll();
					contentPane.updateUI();
					contentPane.add(new JLabel("Home"));
					contentPane.add(new Home(contentPane,user,peopleList));
					pack();
				}else{
					System.out.println("not found");
				}
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			}
		});
	}

}
