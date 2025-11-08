package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    JButton signin,clear,signup;
    JTextField cardtextfeild;
    JPasswordField pintextfeild;
    Login()
    {
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2=i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label=new JLabel(i3);
        label.setBounds(50,20,100,100);
        add(label);

        JLabel text=new JLabel("WELCOME TO ATM");
        text.setBounds(200,45,300,50);
        text.setFont(new Font("osward",Font.BOLD,30));
        add(text);

        JLabel card=new JLabel("CARD NO:");
        card.setBounds(150,140,150,30);
        card.setFont(new Font("Raleway",Font.BOLD,20));
        add(card);

        cardtextfeild=new JTextField();
        cardtextfeild.setBounds(300,140,200,30);
        cardtextfeild.setFont(new Font("Arial",Font.BOLD,15));
        add(cardtextfeild);

        JLabel pin=new JLabel("PIN NO:");
        pin.setBounds(150,210,150,30);
        pin.setFont(new Font("Raleway",Font.BOLD,20));
        add(pin);

        pintextfeild=new JPasswordField();
        pintextfeild.setBounds(300,210,200,30);
        pintextfeild.setFont(new Font("Arial",Font.BOLD,15));
        add(pintextfeild);

        signin=new JButton("SIGN IN");
        signin.setBounds(300,280,80,30);
        signin.setBackground(Color.black);
        signin.setForeground(Color.white);
        signin.setFont(new Font("Airal",Font.BOLD,12));
        signin.addActionListener(this);
        add(signin);

        clear=new JButton("CLEAR");
        clear.setBounds(420,280,80,30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.setFont(new Font("Airal",Font.BOLD,12));
        clear.addActionListener(this);
        add(clear);

        signup=new JButton("SIGN UP");
        signup.setBounds(300,330,200,30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.setFont(new Font("Airal",Font.BOLD,12));
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.white);

        setSize(700,450);
        setVisible(true);
        setLocation(320,140);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource() == clear)
        {
            cardtextfeild.setText("");
            pintextfeild.setText("");
        }
        else if (a.getSource() == signin) {
            try {
                Connection con = atmconnection.getdataconnection();
                String cardno = cardtextfeild.getText();
                String pinno = pintextfeild.getText();
                String query = "select * from login where card_num='" + cardno + "' and pin_num='" + pinno + "'";
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(query);
                if(rs.next())
                {
                    setVisible(false);
                    new transaction(pinno).setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"INCORRECT CARDNUMBER OR PIN");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else if (a.getSource() == signup) {
            setVisible(false);
            new my_signup().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
