package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener {

    JButton onehundred,fivehundred,onethousand,twothousand,fivethousand,tenthousand,back;
    String pinno;
    Fastcash(String pinno)
    {
        this.pinno=pinno;
        setLayout(null);
        ImageIcon atmimage=new ImageIcon(ClassLoader.getSystemResource("icons/atmscreen.jpg"));
        Image i2=atmimage.getImage().getScaledInstance(700,650,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,700,650);
        add(image);

        JLabel text=new JLabel("PLEASE SELECT THE WITHDRAWL AMOUNT");
        text.setBounds(200,130,400,20);
        text.setFont(new Font("Arial",Font.BOLD,15));
        text.setForeground(Color.white);
        image.add(text);

        onehundred=new JButton("RS 100");
        onehundred.setFont(new Font("Arial",Font.BOLD,15));
        onehundred.setBounds(150,170,180,30);
        onehundred.addActionListener(this);
        image.add(onehundred);

        fivehundred=new JButton("RS 500");
        fivehundred.setFont(new Font("Arial",Font.BOLD,15));
        fivehundred.setBounds(380,170,180,30);
        fivehundred.addActionListener(this);
        image.add(fivehundred);

        onethousand=new JButton("RS 1000");
        onethousand.setFont(new Font("Arial",Font.BOLD,15));
        onethousand.setBounds(150,210,180,30);
        onethousand.addActionListener(this);
        image.add(onethousand);

        twothousand=new JButton("RS 2000");
        twothousand.setFont(new Font("Arial",Font.BOLD,15));
        twothousand.setBounds(380,210,180,30);
        twothousand.addActionListener(this);
        image.add(twothousand);

        fivethousand=new JButton("RS 5000");
        fivethousand.setFont(new Font("Arial",Font.BOLD,15));
        fivethousand.setBounds(150,250,180,30);
        fivethousand.addActionListener(this);
        image.add(fivethousand);

        tenthousand=new JButton("RS 10000");
        tenthousand.setFont(new Font("Arial",Font.BOLD,15));
        tenthousand.setBounds(380,250,180,30);
        tenthousand.addActionListener(this);
        image.add(tenthousand);

        back=new JButton("BACK");
        back.setFont(new Font("Arial",Font.BOLD,15));
        back.setBounds(270,300,180,30);
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
        if (ae.getSource()==back)
        {
            setVisible(false);
            new transaction(pinnum).setVisible(true);
        }
        else {
            String cash=((JButton) ae.getSource()).getText().substring(3);
            try {
                Connection con=atmconnection.getdataconnection();
                String query="select * from account where pin='"+pinnum+"'";
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
                if (balanceexists<Integer.parseInt(cash))
                {
                    JOptionPane.showMessageDialog(null,"INSUFFICIENT BALANCE");
                    setVisible(false);
                    new transaction(pinnum).setVisible(true);
                }
                else {
                    Date date = new Date();
                    String queryinsert = "insert into account values('" + pinnum + "','" + date + "','Withdraw','" + cash + "')";
                    PreparedStatement pre = con.prepareStatement(queryinsert);
                    pre.executeUpdate();
                    JOptionPane.showMessageDialog(null, "RS" + cash + " WITHDRAWLED SUCCESSFULLY");
                    setVisible(false);
                    new transaction(pinnum).setVisible(true);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Fastcash("");
    }
}
