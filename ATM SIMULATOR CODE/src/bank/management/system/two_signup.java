package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class two_signup extends JFrame implements ActionListener {
    JTextField mobiletextfeild,incometextfeild,occupationtextfeild,pantextfeild,aadhartextfeild;
    JButton next;
    JRadioButton yes,no,senioryes,seniorno;
    JComboBox categorycombo,religioncombo;
    String formno;
    two_signup(String formno)
    {
        this.formno=formno;
        setLayout(null);

        JLabel additionaldetails=new JLabel("PAGE 2 : ADDITIONAL DETAILS");
        additionaldetails.setFont(new Font("Raleway",Font.BOLD,18));
        additionaldetails.setBounds(200,50,300,20);
        add(additionaldetails);

        JLabel religion=new JLabel("RELIGION :");
        religion.setFont(new Font("Arial",Font.BOLD,15));
        religion.setBounds(140,120,100,20);
        add(religion);

        String religionarray[]={"HINDU","MUSLIM","SIKH","CHRISTION","OTHERS"};
        religioncombo=new JComboBox(religionarray);
        religioncombo.setFont(new Font("Arial",Font.BOLD,12));
        religioncombo.setBounds(320,120,250,20);
        religioncombo.setBackground(Color.white);
        add(religioncombo);

        JLabel category=new JLabel("CATEGORY :");
        category.setFont(new Font("Arial",Font.BOLD,15));
        category.setBounds(140,160,150,20);
        add(category);

        String categoryarray[]={"GENERAL","SC","BC-A","BC-B","OBC","ST"};
        categorycombo=new JComboBox(categoryarray);
        categorycombo.setFont(new Font("Arial",Font.BOLD,12));
        categorycombo.setBounds(320,160,250,20);
        categorycombo.setBackground(Color.white);
        add(categorycombo);

        JLabel income=new JLabel("INCOME :");
        income.setFont(new Font("Arial",Font.BOLD,15));
        income.setBounds(140,200,150,20);
        add(income);

        incometextfeild=new JTextField();
        incometextfeild.setFont(new Font("Arial",Font.BOLD,12));
        incometextfeild.setBounds(320,200,250,20);
        add(incometextfeild);

        JLabel existaccoumt=new JLabel("EXISTING ACCOUNT :");
        existaccoumt.setFont(new Font("Arial",Font.BOLD,15));
        existaccoumt.setBounds(140,240,200,20);
        add(existaccoumt);

        yes=new JRadioButton("YES ");
        yes.setBounds(320,240,60,20);
        yes.setBackground(Color.white);
        add(yes);

        no=new JRadioButton("NO ");
        no.setBounds(420,240,80,20);
        no.setBackground(Color.white);
        add(no);

        ButtonGroup bge=new ButtonGroup();
        bge.add(yes);
        bge.add(no);

        JLabel occupation=new JLabel("OCCUPATION :");
        occupation.setFont(new Font("Arial",Font.BOLD,15));
        occupation.setBounds(140,280,150,20);
        add(occupation);

        occupationtextfeild=new JTextField();
        occupationtextfeild.setFont(new Font("Arial",Font.BOLD,12));
        occupationtextfeild.setBounds(320,280,250,20);
        add(occupationtextfeild);

        JLabel panno=new JLabel("PAN NUMBER :");
        panno.setFont(new Font("Arial",Font.BOLD,15));
        panno.setBounds(140,320,150,20);
        add(panno);

        pantextfeild=new JTextField();
        pantextfeild.setFont(new Font("Arial",Font.BOLD,12));
        pantextfeild.setBounds(320,320,250,20);
        add(pantextfeild);

        JLabel aadhar=new JLabel("AADHAR NUMBER :");
        aadhar.setFont(new Font("Arial",Font.BOLD,15));
        aadhar.setBounds(140,360,150,20);
        add(aadhar);

        aadhartextfeild=new JTextField();
        aadhartextfeild.setFont(new Font("Arial",Font.BOLD,12));
        aadhartextfeild.setBounds(320,360,250,20);
        add(aadhartextfeild);

        JLabel mobile=new JLabel("MOBILE NUMBER :");
        mobile.setFont(new Font("Arial",Font.BOLD,15));
        mobile.setBounds(140,400,150,20);
        add(mobile);

        mobiletextfeild=new JTextField();
        mobiletextfeild.setFont(new Font("Arial",Font.BOLD,12));
        mobiletextfeild.setBounds(320,400,250,20);
        add(mobiletextfeild);

        JLabel seniorcity=new JLabel("SENIOR CITIZEN :") ;
        seniorcity.setFont(new Font("Arial",Font.BOLD,15));
        seniorcity.setBounds(140,440,150,20);
        add(seniorcity);

        senioryes=new JRadioButton("YES ");
        senioryes.setBounds(320,440,80,20);
        senioryes.setBackground(Color.white);
        add(senioryes);

        seniorno=new JRadioButton("NO ");
        seniorno.setBounds(420,440,80,20);
        seniorno.setBackground(Color.white);
        add(seniorno);

        ButtonGroup besenior=new ButtonGroup();
        besenior.add(senioryes);
        besenior.add(seniorno);

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
        String getrelegion= (String) religioncombo.getSelectedItem();
        String getcategory= (String) categorycombo.getSelectedItem();
        String getincome=incometextfeild.getText();
        String getexistaccount=null;
        if(yes.isSelected())
        {
            getexistaccount="yes";
        } else if (no.isSelected()) {
            getexistaccount="no";
        }
        String getoccupation=occupationtextfeild.getText();
        String getpan=pantextfeild.getText();
        String getaadhar=aadhartextfeild.getText();
        String getmob=mobiletextfeild.getText();
        String getsenior=null;
        if (senioryes.isSelected())
        {
            getsenior="yes";
        } else if (seniorno.isSelected()) {
            getsenior="no";
        }
        try {
            Connection con=atmconnection.getdataconnection();
            String query="insert into signuptwo " +
                    "values" +
                    "('"+formno+"','"+getrelegion+"','"+getcategory+"','"+getincome+"'," +
                    "'"+getexistaccount+"','"+getoccupation+"','"+getpan+"','"+getaadhar+"'," +
                    "'"+getmob+"','"+getsenior+"')";
            PreparedStatement pre=con.prepareStatement(query);
            pre.executeUpdate();
            setVisible(false);
            new three_signup(formno).setVisible(true);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        new two_signup("");
    }
}

