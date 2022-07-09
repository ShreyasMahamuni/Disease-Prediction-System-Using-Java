import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Dr Registration Module
class dr_reg_module implements ActionListener
{
	JFrame f;
	JLabel user,pass,fname,lname,spec,mob;
	JTextField user_tf,pass_tf,fname_tf,lname_tf,spec_tf,mob_tf;
	JButton reg_submit,bth;
	
	dr_reg_module()
	{
		f = new JFrame();
		f.setTitle("Register as Doctor");

		user = new JLabel("Username");			user.setBounds(200,50,200,25);
		user_tf = new JTextField(25);			user_tf.setBounds(200,80,200,25);
		fname = new JLabel("First Name");		fname.setBounds(200,120,200,25);
		fname_tf = new JTextField(25);			fname_tf.setBounds(200,150,200,25);
		lname = new JLabel("Last Name");		lname.setBounds(200,190,200,25);
		lname_tf = new JTextField(25);			lname_tf.setBounds(200,210,200,25);
		spec = new JLabel("Speciality");		spec.setBounds(200,250,200,25);
		spec_tf = new JTextField(25);			spec_tf.setBounds(200,280,200,25);
		mob = new JLabel("Mobile No : ");		mob.setBounds(200,320,200,25);
		mob_tf = new JTextField(25);			mob_tf.setBounds(200,350,200,25);
		reg_submit = new JButton("Register");	reg_submit.setBounds(250,420,100,25);

		bth = new JButton("Back To Home");		bth.setBounds(200,500,200,25);
		bth.setBackground(Color.LIGHT_GRAY);

		f.add(user);		f.add(user_tf);		f.add(fname);		f.add(fname_tf);
		f.add(lname);		f.add(lname_tf);	f.add(spec);		f.add(spec_tf);
		f.add(mob);			f.add(mob_tf);		f.add(reg_submit);	f.add(bth);
		
		reg_submit.addActionListener(this);		bth.addActionListener(this);

		f.setLayout(null);
		f.setSize(600,600);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String arg = e.getActionCommand();
		if (e.getSource() == reg_submit) {
			verifyData();
		}
		if (arg.equals("Back To Home")) {
			home_pg hp = new home_pg();	
			f.setVisible(true);
			f.dispose();
		}
	}

	public void verifyData()
	{
		String str1 = user_tf.getText();		String str2 = fname_tf.getText();
		String str3 = lname_tf.getText();		String str4 = spec_tf.getText();
        String str5 = mob_tf.getText();

		try{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("--------------------");
			System.out.println("\nDrivers established\n");
			System.out.println("--------------------");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			System.out.println("--------------------");
			System.out.println("\nconnection established\n");
			System.out.println("--------------------");
			
			PreparedStatement ps = con.prepareStatement("insert into drlogin values (?, ?, ?, ?, ?)");

            ps.setString(1, str1);		ps.setString(2, str2);
            ps.setString(3, str3);		ps.setString(4, str4);
            ps.setString(5, str5);

            ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				JOptionPane.showMessageDialog(f, "Sucessfully INSERT Data......");
			}
			else
			{
				System.out.println("Plz Enter Valid Credintials ");
			}
			con.close();		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
