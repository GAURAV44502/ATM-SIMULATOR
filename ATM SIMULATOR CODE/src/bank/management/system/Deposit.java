package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    JButton deposit,back;
    JTextField amounttextfeild;
    String pinnum;
    Deposit(String pinno)
    {
        pinnum=pinno;
        setLayout(null);
        ImageIcon atmimage=new ImageIcon(ClassLoader.getSystemResource("icons/atmscreen.jpg"));
        Image i2=atmimage.getImage().getScaledInstance(700,650,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,700,650);
        add(image);

        JLabel amount=new JLabel("ENTER THE AMOUNT YOU WANT TO DEPOSIT");
        amount.setBounds(190,140,400,20);
        amount.setFont(new Font("Arial",Font.BOLD,15));
        amount.setForeground(Color.white);
        image.add(amount);

        amounttextfeild=new JTextField();
        amounttextfeild.setBounds(200,180,300,30);
        amounttextfeild.setFont(new Font("Arial",Font.BOLD,15));
        image.add(amounttextfeild);

        deposit=new JButton("DEPOSIT");
        deposit.setFont(new Font("Arial",Font.BOLD,15));
        deposit.setBounds(380,260,180,30);
        deposit.addActionListener(this);
        image.add(deposit);

        back=new JButton("BACK");
        back.setFont(new Font("Arial",Font.BOLD,15));
        back.setBounds(150,260,180,30);
        back.addActionListener(this);
        image.add(back);

        getContentPane().setBackground(Color.white);
        setSize(700,680);
        setVisible(true);
        setLocation(300,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(pinno);
    }

    public void actionPerformed(ActionEvent ae)
    {

        if(ae.getSource()==deposit)
        {
            String pin=pinnum;
            String pase=amounttextfeild.getText();
            Date date=new Date();
            if (pase.equals(""))
            {
                JOptionPane.showMessageDialog(null,"PLEASE ENTER THE AMOUNT");
            }else{
                try {
                    Connection con=atmconnection.getdataconnection();
                    String query="insert into account values('"+pin+"','"+date+"','Deposit','"+pase+"')";
                    PreparedStatement pre=con.prepareStatement(query);
                    pre.executeUpdate();

                    JOptionPane.showMessageDialog(null,"RS" + pase + "DEPOSITED SUCCESSFULLY" );
                    setVisible(false);
                    new transaction(pinnum).setVisible(true);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        } else if (ae.getSource()==back) {
            setVisible(false);
            new transaction(pinnum).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}

