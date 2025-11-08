package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class three_signup extends JFrame  implements ActionListener {
    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton submit,cancel;
    String formno;
    three_signup(String formno)
    {
        this.formno=formno;
        setLayout(null);
        JLabel accountdetails=new JLabel("PAGE 3 : ACCOUNT DETAILS");
        accountdetails.setFont(new Font("Raleway",Font.BOLD,18));
        accountdetails.setBounds(200,30,300,20);
        add(accountdetails);

        JLabel accounttype=new JLabel("ACCOUNT TYPE: ");
        accounttype.setFont(new Font("Raleway",Font.BOLD,15));
        accounttype.setBounds(100,100,200,20);
        add(accounttype);

        r1=new JRadioButton("RECURRING DEPOSIT ACCOUNT");
        r1.setFont(new Font("Raleway",Font.BOLD,12));
        r1.setBackground(Color.white);
        r1.setBounds(150,140,250,20);
        add(r1);

        r2=new JRadioButton("CURRENT ACCOUNT");
        r2.setFont(new Font("Raleway",Font.BOLD,12));
        r2.setBackground(Color.white);
        r2.setBounds(400,140,200,20);
        add(r2);

        r3=new JRadioButton("SAVING ACCOUNT");
        r3.setFont(new Font("Raleway",Font.BOLD,12));
        r3.setBackground(Color.white);
        r3.setBounds(150,180,250,20);
        add(r3);

        r4=new JRadioButton("FIXED DEPOSIT ACCOUNT");
        r4.setFont(new Font("Raleway",Font.BOLD,12));
        r4.setBackground(Color.white);
        r4.setBounds(400,180,250,20);
        add(r4);

        ButtonGroup accountgroup=new ButtonGroup();
        accountgroup.add(r1);
        accountgroup.add(r2);
        accountgroup.add(r3);
        accountgroup.add(r4);

        JLabel cardno=new JLabel("CARD NUMBER: ");
        cardno.setFont(new Font("Raleway",Font.BOLD,15));
        cardno.setBounds(150,240,150,20);
        add(cardno);

        JLabel cardcount=new JLabel("your 17 digit card number ");
        cardcount.setFont(new Font("Raleway",Font.BOLD,10));
        cardcount.setBounds(145,260,150,15);
        add(cardcount);

        JLabel cardvalue=new JLabel("XXXX XXXX XXXX 4452 ");
        cardvalue.setFont(new Font("Raleway",Font.BOLD,15));
        cardvalue.setBounds(350,240,200,20);
        add(cardvalue);

        JLabel pinno=new JLabel("PIN NUMBER: ");
        pinno.setFont(new Font("Raleway",Font.BOLD,15));
        pinno.setBounds(150,300,150,20);
        add(pinno);

        JLabel pincount=new JLabel("your 4 digit card number ");
        pincount.setFont(new Font("Raleway",Font.BOLD,10));
        pincount.setBounds(145,320,150,15);
        add(pincount);

        JLabel pinvalue=new JLabel("XXXX ");
        pinvalue.setFont(new Font("Raleway",Font.BOLD,15));
        pinvalue.setBounds(350,300,200,20);
        add(pinvalue);

        JLabel services=new JLabel("SERVICES REQUIRED: ");
        services.setFont(new Font("Raleway",Font.BOLD,15));
        services.setBounds(100,380,200,20);
        add(services);

        c1=new JCheckBox("DEBIT CARD");
        c1.setFont(new Font("Raleway",Font.BOLD,12));
        c1.setBackground(Color.white);
        c1.setBounds(150,420,100,20);
        add(c1);

        c2=new JCheckBox("INTERNET BANKING");
        c2.setFont(new Font("Raleway",Font.BOLD,12));
        c2.setBackground(Color.white);
        c2.setBounds(400,420,150,20);
        add(c2);

        c3=new JCheckBox("E-MAIL & SMS ALERT");
        c3.setFont(new Font("Raleway",Font.BOLD,12));
        c3.setBackground(Color.white);
        c3.setBounds(150,450,200,20);
        add(c3);

        c4=new JCheckBox("MOBILE BANKING");
        c4.setFont(new Font("Raleway",Font.BOLD,12));
        c4.setBackground(Color.white);
        c4.setBounds(400,450,150,20);
        add(c4);

        c5=new JCheckBox("CHEQUE BOOK");
        c5.setFont(new Font("Raleway",Font.BOLD,12));
        c5.setBackground(Color.white);
        c5.setBounds(150,480,150,20);
        add(c5);

        c6=new JCheckBox("E-STATEMENT");
        c6.setFont(new Font("Raleway",Font.BOLD,12));
        c6.setBackground(Color.white);
        c6.setBounds(400,480,150,20);
        add(c6);

        c7=new JCheckBox("I HEREBY DECLARES THAT THE ABOVE ENTERED DETAILS ARE CORRECT TO THE BEST OF MY KNOWLEDGE");
        c7.setFont(new Font("Raleway",Font.BOLD,10));
        c7.setBackground(Color.white);
        c7.setBounds(50,520,600,15);
        add(c7);

        submit=new JButton("SUBMIT");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setFont(new Font("Airal",Font.BOLD,12));
        submit.setBounds(400,570,100,30);
        submit.addActionListener(this);
        add(submit);

        cancel=new JButton("CANCEL");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Airal",Font.BOLD,12));
        cancel.setBounds(150,570,100,30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.white);
        setSize(700,680);
        setVisible(true);
        setLocation(300,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
            String accounttype=null;
            if(r1.isSelected())
            {
                accounttype="RECURRING DEPOSIT ACCOUNT";
            } else if (r2.isSelected()) {
                accounttype="CURRENT ACCOUNT";
            } else if (r3.isSelected()) {
                accounttype="SAVING ACCOUNT";
            } else if (r4.isSelected()) {
                accounttype="FIXED DEPOSIT ACCOUNT";
            }
            Random random=new Random();
            String cardnum="" + Math.abs((random.nextLong() % 90000000L) + 44502428700000000L);
            String pinnum="" + Math.abs((random.nextLong() % 9000L) + 1000L);
            String facility="";
            if(c1.isSelected())
            {
                facility=facility + " DEBIT CARD ";
            }
            if (c2.isSelected()) {
                facility=facility + " INTERNET BANKING ";
            }
            if (c3.isSelected()) {
                facility=facility + " E-MAIL AND SMS ALERT ";
            }
            if (c4.isSelected()) {
                facility=facility + " MOBILE BANKING ";
            }
            if (c5.isSelected()) {
                facility=facility + " CHEQUE-BOOK ";
            }
            if (c6.isSelected()) {
                facility=facility + " E-STATEMENT ";
            }

            try {
                if(accounttype =="")
                {
                    JOptionPane.showMessageDialog(null,"ACCOUNT TYPE CANNOT BE BLANK");
                }
                else {
                    Connection con=atmconnection.getdataconnection();
                    String query="insert into signupthree " +
                            "values" +
                            "('"+formno+"','"+accounttype+"','"+cardnum+"','"+pinnum+"'," +
                            "'"+facility+"')";
                    String queryl="insert into login " +
                            "values" +
                            "('"+formno+"','"+cardnum+"','"+pinnum+"')";
                    PreparedStatement pre=con.prepareStatement(query);
                    PreparedStatement pr=con.prepareStatement(queryl);
                    pre.executeUpdate();
                    pr.executeUpdate();
                    JOptionPane.showMessageDialog(null,"SIGN UP COMPLETED ");
                    JOptionPane.showMessageDialog(null,"CARD NO: " + cardnum + "\n PIN: " + pinnum);
                    setVisible(false);
                    new Login().setVisible(true);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        } else if (ae.getSource()==cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new three_signup("");
    }
}

