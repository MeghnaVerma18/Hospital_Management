package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_update_details extends JFrame {
    Patient_update_details(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("Tahoma",Font.BOLD,20));
        label1.setForeground(Color.white);
        panel.add(label1);


        JLabel label3 = new JLabel("Name :");
        label3.setBounds(25,88,100,14);
        label3.setFont(new Font("Tahoma",Font.PLAIN,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        Choice choice = new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label4 = new JLabel("Room Number");
        label4.setBounds(25,129,100,14);
        label4.setFont(new Font("Tahoma",Font.PLAIN,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textField = new JTextField();
        textField.setBounds(248,129,140,20);
        panel.add(textField);

        JLabel INTime = new JLabel("IN-Time");
        INTime.setBounds(25,174,100,14);
        INTime.setFont(new Font("Tahoma",Font.BOLD,14));
        INTime.setForeground(Color.white);
        panel.add(INTime);

        JTextField textField1 = new JTextField();
        textField1.setBounds(248,174,140,20);
        panel.add(textField1);

        JLabel label5 = new JLabel("Amount Paid (Rs) : ");
        label5.setBounds(25,216,100,14);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textField2 = new JTextField();
        textField2.setBounds(248,216,140,20);
        panel.add(textField2);

        JLabel label6 = new JLabel("Pending Amount (Rs) :");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248,261,150,20);
        panel.add(textField3);

        JButton button = new JButton("Check");
        button.setBounds(281,378,89,23);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from patient_info where Name = '"+id+"'";
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    while (resultSet.next()){
                        textField.setText(resultSet.getString("Room_Number"));
                        textField1.setText(resultSet.getString("Time"));
                        textField2.setText(resultSet.getString("Deposite"));
                    }
                    ResultSet resultSet1 = c.statement.executeQuery("select * from room where room_no = '"+textField.getText()+"'");
                    while (resultSet1.next()){
                        String price = resultSet1.getNString("Price");
                        int amount_paid = Integer.parseInt(price)-Integer.parseInt(textField2.getText());
                        textField3.setText(""+amount_paid);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        JButton button1 = new JButton("Update");
        button1.setBounds(56,378,89,23);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String q = choice.getSelectedItem();
                    String room = textField.getText();
                    String time = textField1.getText();
                    String amount = textField2.getText();
                    c.statement.executeUpdate("update patient_info set Room_Number = '"+room+"',Time ='"+time+"',Deposite = '"+amount+"' where Name = '"+q+"'");
                    //c.statement.executeQuery("update patient_info set Room_Number = '"+room+"',Time ='"+time+"',Deposite = '"+amount+"' where Name = '"+q+"'");
                   JOptionPane.showMessageDialog(null,"Update Successfully");
                   setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton button2 = new JButton("Back");
        button2.setBounds(168,378,89,23);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.white);
        panel.add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setUndecorated(true);
        setSize(950,500);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_update_details();
    }
}
