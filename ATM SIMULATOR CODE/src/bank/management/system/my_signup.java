package bank.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
public class my_signup extends JFrame implements ActionListener {
    long random;
    JTextField nametextfeild,fathertextfeild,emailtextfeild,addresstextfeild,citytextfeild,statetextfeild,pincodetextfeild;
    JButton next;
    JRadioButton male,female;
    JDateChooser dobdate;
    my_signup()
    {
        setLayout(null);
        Random ran=new Random();
        random=Math.abs((ran.nextLong()%9000L) + 1000L);

        JLabel formno=new JLabel("APPLICATION FORM NO : " + random);
        formno.setFont(new Font("Raleway",Font.BOLD,22));
        formno.setBounds(180,0,500,30);
        add(formno);

        JLabel personaldetails=new JLabel("PAGE 1 : PERSONAL DETAILS");
        personaldetails.setFont(new Font("Raleway",Font.BOLD,18));
        personaldetails.setBounds(200,50,300,20);
        add(personaldetails);

        JLabel name=new JLabel("NAME :");
        name.setFont(new Font("Arial",Font.BOLD,15));
        name.setBounds(140,120,100,20);
        add(name);

        nametextfeild=new JTextField();
        nametextfeild.setFont(new Font("Arial",Font.BOLD,12));
        nametextfeild.setBounds(320,120,250,20);
        add(nametextfeild);

        JLabel fathername=new JLabel("FATHER'S NAME :");
        fathername.setFont(new Font("Arial",Font.BOLD,15));
        fathername.setBounds(140,160,150,20);
        add(fathername);

        fathertextfeild=new JTextField();
        fathertextfeild.setFont(new Font("Arial",Font.BOLD,12));
        fathertextfeild.setBounds(320,160,250,20);
        add(fathertextfeild);

        JLabel dob=new JLabel("DATE OF BIRTH :");
        dob.setFont(new Font("Arial",Font.BOLD,15));
        dob.setBounds(140,200,150,20);
        add(dob);

        dobdate=new JDateChooser();
        dobdate.setBounds(320,200,200,20);
        add(dobdate);

        JLabel gender=new JLabel("GENDER :");
        gender.setFont(new Font("Arial",Font.BOLD,15));
        gender.setBounds(140,240,150,20);
        add(gender);

        male=new JRadioButton("MALE ");
        male.setBounds(320,240,60,20);
        male.setBackground(Color.white);
        add(male);

        female=new JRadioButton("FEMALE ");
        female.setBounds(420,240,80,20);
        female.setBackground(Color.white);
        add(female);

        ButtonGroup bg=new ButtonGroup();
        bg.add(male);
        bg.add(female);

        JLabel email=new JLabel("E-MAIL ADDRESS :");
        email.setFont(new Font("Arial",Font.BOLD,15));
        email.setBounds(140,280,150,20);
        add(email);

        emailtextfeild=new JTextField();
        emailtextfeild.setFont(new Font("Arial",Font.BOLD,12));
        emailtextfeild.setBounds(320,280,250,20);
        add(emailtextfeild);

        JLabel address=new JLabel(" ADDRESS :");
        address.setFont(new Font("Arial",Font.BOLD,15));
        address.setBounds(140,320,150,20);
        add(address);

        addresstextfeild=new JTextField();
        addresstextfeild.setFont(new Font("Arial",Font.BOLD,12));
        addresstextfeild.setBounds(320,320,250,20);
        add(addresstextfeild);

        JLabel city=new JLabel("CITY :");
        city.setFont(new Font("Arial",Font.BOLD,15));
        city.setBounds(140,360,150,20);
        add(city);

        citytextfeild=new JTextField();
        citytextfeild.setFont(new Font("Arial",Font.BOLD,12));
        citytextfeild.setBounds(320,360,250,20);
        add(citytextfeild);

        JLabel state=new JLabel("STATE :");
        state.setFont(new Font("Arial",Font.BOLD,15));
        state.setBounds(140,400,150,20);
        add(state);

        statetextfeild=new JTextField();
        statetextfeild.setFont(new Font("Arial",Font.BOLD,12));
        statetextfeild.setBounds(320,400,250,20);
        add(statetextfeild);

        JLabel pincode=new JLabel("PIN CODE :");
        pincode.setFont(new Font("Arial",Font.BOLD,15));
        pincode.setBounds(140,440,150,20);
        add(pincode);

        pincodetextfeild=new JTextField();
        pincodetextfeild.setFont(new Font("Arial",Font.BOLD,12));
        pincodetextfeild.setBounds(320,440,150,20);
        add(pincodetextfeild);

        next=new JButton("NEXT >>");
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.setFont(new Font("Airal",Font.BOLD,12));
        next.setBounds(470,540,100,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.white);
        setSize(700,680);
        setVisible(true);
        setLocation(300,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String formno="" + random;
        String name=nametextfeild.getText();
        String fname=fathertextfeild.getText();
        String dob=((JTextField)dobdate.getDateEditor().getUiComponent()).getText();
        String gender=null;
        if (male.isSelected())
        {
            gender="male";
        } else if (female.isSelected()) {
            gender="female";
        }
        String email=emailtextfeild.getText();
        String address=addresstextfeild.getText();
        String city=citytextfeild.getText();
        String state=statetextfeild.getText();
        String pincode=pincodetextfeild.getText();
        try {
            if (name.equals(""))
            {
                JOptionPane.showMessageDialog(null,"NAME CANNOT BE NULL");
            }
            else {
                Connection con=atmconnection.getdataconnection();
                String query="insert into signup " +
                        "values" +
                        "('"+formno+"','"+name+"','"+fname+"','"+dob+"'," +
                        "'"+gender+"','"+email+"','"+address+"','"+city+"'," +
                        "'"+state+"','"+pincode+"')";
                PreparedStatement pre=con.prepareStatement(query);
                pre.executeUpdate();
                setVisible(false);
                new two_signup(formno).setVisible(true);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        new my_signup();
    }
}

