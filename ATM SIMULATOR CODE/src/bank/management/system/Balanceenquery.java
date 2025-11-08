package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Balanceenquery extends JFrame implements ActionListener {
    JButton back;
    String pinno;
    Balanceenquery(String pinno)
    {
        this.pinno=pinno;
        System.out.println(pinno);
        setLayout(null);
        ImageIcon atmimage=new ImageIcon(ClassLoader.getSystemResource("icons/atmscreen.jpg"));
        Image i2=atmimage.getImage().getScaledInstance(700,650,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,700,650);
        add(image);

        back=new JButton("BACK");
        back.setFont(new Font("Arial",Font.BOLD,15));
        back.setBounds(270,300,180,30);
        back.addActionListener(this);
        image.add(back);

        //create connection
        int balanceexists=0;
        try {
            Connection con = atmconnection.getdataconnection();
            String query = "select * from account where pin='" + pinno + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balanceexists += Integer.parseInt(rs.getString("amount"));
                } else {
                    balanceexists -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        JLabel currentbalance=new JLabel("BALANCE IN YOUR ACCOUNT:  " + balanceexists);
        currentbalance.setBounds(180,180,400,20);
        currentbalance.setFont(new Font("Arial",Font.BOLD,15));
        currentbalance.setForeground(Color.white);
        image.add(currentbalance);

        getContentPane().setBackground(Color.white);
        setSize(700,680);
        setVisible(true);
        setLocation(300,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String pin=pinno;
        setVisible(false);
        new transaction(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new Balanceenquery("");
    }
}
