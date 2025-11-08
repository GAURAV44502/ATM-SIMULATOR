package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class transaction extends JFrame implements ActionListener {
    JButton deposit,withdrawl,fastcash,ministatement,pinchange,balanceenquiry,exit;
    String pinno;
    transaction( String pinno)
    {
        this.pinno=pinno;
        setLayout(null);
        ImageIcon atmimage=new ImageIcon(ClassLoader.getSystemResource("icons/atmscreen.jpg"));
        Image i2=atmimage.getImage().getScaledInstance(700,650,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,700,650);
        add(image);

        JLabel text=new JLabel("PLEASE SELECT YOUR TRANSACTION");
        text.setBounds(220,130,400,20);
        text.setFont(new Font("Arial",Font.BOLD,15));
        text.setForeground(Color.white);
        image.add(text);

        deposit=new JButton("DEPOSIT");
        deposit.setFont(new Font("Arial",Font.BOLD,15));
        deposit.setBounds(150,170,180,30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl=new JButton("CASH WITHDRAWL");
        withdrawl.setFont(new Font("Arial",Font.BOLD,15));
        withdrawl.setBounds(380,170,180,30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash=new JButton("FASTCASH");
        fastcash.setFont(new Font("Arial",Font.BOLD,15));
        fastcash.setBounds(150,210,180,30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        pinchange=new JButton("PIN CHANGE");
        pinchange.setFont(new Font("Arial",Font.BOLD,15));
        pinchange.setBounds(150,250,180,30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry=new JButton("BALANCE ENQUIRY");
        balanceenquiry.setFont(new Font("Arial",Font.BOLD,15));
        balanceenquiry.setBounds(380,210,180,30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit=new JButton("EXIT");
        exit.setFont(new Font("Arial",Font.BOLD,15));
        exit.setBounds(380,250,180,30);
        exit.addActionListener(this);
        image.add(exit);

        getContentPane().setBackground(Color.white);
        setSize(700,680);
        setVisible(true);
        setLocation(300,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String pin=pinno;
        if (ae.getSource()==exit)
        {
            System.exit(0);
        } else if (ae.getSource()==deposit) {
            setVisible(false);
           new Deposit(pin).setVisible(true);
        } else if (ae.getSource()==withdrawl) {
            setVisible(false);
           new Withdraw(pin).setVisible(true);
        } else if (ae.getSource()==fastcash) {
            setVisible(false);
           new Fastcash(pinno).setVisible(true);
        } else if (ae.getSource()==pinchange) {
            setVisible(false);
           new Changepin(pinno).setVisible(true);
        } else if (ae.getSource()==balanceenquiry) {
            setVisible(false);
           new Balanceenquery(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new transaction("");
    }
}

