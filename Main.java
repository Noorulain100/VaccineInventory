package admin;

import temp.personnelHome;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame {
    private JButton personal;
    private JButton admin;

    public Main(){
        personal = new JButton("Personal");
        admin = new JButton("Admin");
        setLayout(new FlowLayout());
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(personal);
        add(admin);
        setVisible(true);
        personal.addActionListener(e->{
            new personalInterface().setVisible(true);
        });
        admin.addActionListener(e->{
            new personnelHome().setVisible(true);
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
