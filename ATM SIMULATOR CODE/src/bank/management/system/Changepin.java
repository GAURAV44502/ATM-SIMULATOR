package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Changepin extends JFrame implements ActionListener {
    JButton changepin,back;
    JPasswordField newpintextfeild,renewpintextfeild;
    String pinno;
    Changepin(String pinno)
    {
        this.pinno=pinno;
        setLayout(null);
        ImageIcon atmimage=new ImageIcon(ClassLoader.getSystemResource("icons/atmscreen.jpg"));
        Image i2=atmimage.getImage().getScaledInstance(700,650,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,700,650);
        add(image);

        JLabel text=new JLabel("CHANGE DEBIT CARD PIN");
        text.setBounds(250,130,400,20);
        text.setFont(new Font("Arial",Font.BOLD,15));
        text.setForeground(Color.white);
        image.add(text);

        JLabel newpin=new JLabel("ENTER NEW PIN: ");
        newpin.setBounds(200,180,150,20);
        newpin.setFont(new Font("Arial",Font.BOLD,15));
        newpin.setForeground(Color.white);
        image.add(newpin);

        newpintextfeild=new JPasswordField();
        newpintextfeild.setBounds(380,180,100,20);
        newpintextfeild.setFont(new Font("Arial",Font.BOLD,15));
        image.add(newpintextfeild);

        JLabel renewpin=new JLabel("RE-ENTER NEW PIN: ");
        renewpin.setBounds(200,230,200,20);
        renewpin.setFont(new Font("Arial",Font.BOLD,15));
        renewpin.setForeground(Color.white);
        image.add(renewpin);

        renewpintextfeild=new JPasswordField();
        renewpintextfeild.setBounds(380,230,100,20);
        renewpintextfeild.setFont(new Font("Arial",Font.BOLD,15));
        image.add(renewpintextfeild);

        changepin=new JButton("CHANGE PIN");
        changepin.setFont(new Font("Arial",Font.BOLD,15));
        changepin.setBounds(380,290,180,30);
        changepin.addActionListener(this);
        image.add(changepin);

        back=new JButton("BACK");
        back.setFont(new Font("Arial",Font.BOLD,15));
        back.setBounds(150,290,180,30);
        back.addActionListener(this);
        image.add(back);

        getContentPane().setBackground(Color.white);
        setSize(700,680);
        setVisible(true);
        setLocation(300,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String pinnum=pinno;
        if (ae.getSource()==changepin) {
            try {
                String npin = newpintextfeild.getText();
                String rnpin = renewpintextfeild.getText();

                if (npin.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "PLEASE ENTER NEW PIN");
                }
                else if (npin.length()!=4)
                {
                    JOptionPane.showMessageDialog(null,"PLEASE ENTER 4 DIGIT PIN NUMBER");
                    return;
                }
                if (!npin.equals(rnpin)) {
                    JOptionPane.showMessageDialog(null, "PIN DOES NOT MATCH");
                    return;
                }
                Connection con=atmconnection.getdataconnection();
                String querya="update account set pin='"+rnpin+"' where pin='"+pinnum+"'";
                String queryl="update login set pin_num='"+rnpin+"' where pin_num='"+pinnum+"'";
                String querysth="update signupthree set pin_num='"+rnpin+"' where pin_num='"+pinnum+"'";

                PreparedStatement prea=con.prepareStatement(querya);
                prea.executeUpdate();

                PreparedStatement prel=con.prepareStatement(queryl);
                prel.executeUpdate();

                PreparedStatement presth=con.prepareStatement(querysth);
                presth.executeUpdate();
                JOptionPane.showMessageDialog(null,"PIN CHANGED SUCCESSFULLY");
                setVisible(false);
                new transaction(rnpin).setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource()==back) {
            setVisible(false);
            new transaction(pinnum).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Changepin("");
    }
}

