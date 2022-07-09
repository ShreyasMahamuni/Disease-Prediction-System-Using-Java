import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// Dr Login Module
class dr_Login_module implements ActionListener
{
	JFrame f;						JLabel user,pass;
	JTextField user_tf;				JPasswordField pass_tf;
	JButton login_submit,bth;
	
	dr_Login_module()
	{
		f = new JFrame();
		f.setTitle("Login as Doctor");
		Color darkGreen = new Color	(34,139,34);

		user = new JLabel("Username");				user.setBounds(200,150,200,25);
		user_tf = new JTextField(25);				user_tf.setBounds(200,180,200,25);
		pass = new JLabel("Password");				pass.setBounds(200,220,200,25);
		pass_tf = new JPasswordField(16);			pass_tf.setBounds(200,250,200,25);
		login_submit = new JButton("Login");		login_submit.setBounds(200,320,200,25);
		login_submit.setBackground(darkGreen);		login_submit.setForeground(Color.WHITE);
		
		bth = new JButton("Back To Home");			bth.setBounds(200,500,200,25);
		bth.setBackground(Color.LIGHT_GRAY);

		f.add(user);	f.add(user_tf);				f.add(pass);	f.add(pass_tf);
		f.add(bth);		f.add(login_submit);

		login_submit.addActionListener(this);		bth.addActionListener(this);

		f.setLayout(null);
		f.setSize(600,600);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String arg = e.getActionCommand();
		if (e.getSource() == login_submit) {
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
		String str1 = user_tf.getText();
        char[] p = pass_tf.getPassword();
        String str2 = new String(p);

		try{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("--------------------");
			System.out.println("\nDrivers established\n");
			System.out.println("--------------------");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			System.out.println("--------------------");
			System.out.println("\nconnection established\n");
			System.out.println("--------------------");
			
			PreparedStatement ps = con.prepareStatement("select * from drlogin where DB_USER=? and DB_MOBNO=?");

            ps.setString(1, str1);
            ps.setString(2, str2);

            ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				// String username = setText();
				profile p_data = new profile(rs.getString("DB_USER"));	
				f.setVisible(true);
				f.dispose();
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
