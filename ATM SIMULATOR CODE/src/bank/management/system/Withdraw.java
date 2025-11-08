package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    JButton withdraw,back;
    JTextField amounttextfeild;
    String pinno;
    Withdraw(String pinno)
    {
        this.pinno=pinno;
        setLayout(null);
        ImageIcon atmimage=new ImageIcon(ClassLoader.getSystemResource("icons/atmscreen.jpg"));
        Image i2=atmimage.getImage().getScaledInstance(700,650,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,700,650);
        add(image);

        JLabel amount=new JLabel("ENTER THE AMOUNT YOU WANT TO WITHDRAW");
        amount.setBounds(190,140,400,20);
        amount.setFont(new Font("Arial",Font.BOLD,15));
        amount.setForeground(Color.white);
        image.add(amount);

        amounttextfeild=new JTextField();
        amounttextfeild.setBounds(200,180,300,30);
        amounttextfeild.setFont(new Font("Arial",Font.BOLD,15));
        image.add(amounttextfeild);

        withdraw=new JButton("WITHDRAW");
        withdraw.setFont(new Font("Arial",Font.BOLD,15));
        withdraw.setBounds(380,260,180,30);
        withdraw.addActionListener(this);
        image.add(withdraw);

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
    }

    public void actionPerformed(ActionEvent ae)
    {

        if(ae.getSource()==withdraw) {
            String pin=pinno;
            String pase = amounttextfeild.getText();
            if (pase.equals("")) {
                JOptionPane.showMessageDialog(null, "PLEASE ENTER THE AMOUNT");
            }
            else {
                try {
                    Connection con=atmconnection.getdataconnection();
                    String query="select * from account where pin='"+pinno+"'";
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(query);
                    int balanceexists=0;
                    while (rs.next())
                    {
                        if (rs.getString("type").equals("Deposit"))
                        {
                            balanceexists+=Integer.parseInt(rs.getString("amount"));
                        }
                        else {
                            balanceexists-=Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if (balanceexists<Integer.parseInt(pase))
                    {
                        JOptionPane.showMessageDialog(null,"INSUFFICIENT BALANCE");
                        setVisible(false);
                        new transaction(pinno).setVisible(true);
                    }
                    else {
                        Date date = new Date();
                        String queryinsert = "insert into account values('" + pin + "','" + date + "','Withdraw','" + pase + "')";
                        PreparedStatement pre = con.prepareStatement(queryinsert);
                        pre.executeUpdate();
                        JOptionPane.showMessageDialog(null, "RS" + pase + " WITHDRAWLED SUCCESSFULLY");
                        setVisible(false);
                        new transaction(pinno).setVisible(true);
                    }

//                    String query="insert into account values('"+pinno+"','"+date+"','Withdraw','"+pase+"')";
//                    PreparedStatement pre=con.prepareStatement(query);
//                    pre.executeUpdate();
//                    JOptionPane.showMessageDialog(null,"RS" + pase + "WITHDRAWLED SUCCESSFULLY" );
//                    setVisible(false);
//                    new transaction(pinno).setVisible(true);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }else if (ae.getSource()==back) {
            setVisible(false);
            new transaction(pinno).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdraw("");
    }
}