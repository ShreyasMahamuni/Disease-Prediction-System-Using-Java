import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Profile Module

class profile implements ActionListener
{
	JFrame f;
	JLabel dr_user,dr_name,dr_spec,dr_mob;
	JButton bth;

	profile(String username)
	{
		f = new JFrame();
		f.setTitle("Login as Doctor");

		dr_user = new JLabel();		dr_user.setBounds(200,100,200,25);
		dr_name = new JLabel();		dr_name.setBounds(200,130,200,25);
		dr_spec = new JLabel();		dr_spec.setBounds(200,160,200,25);
		dr_mob = new JLabel();		dr_mob.setBounds(200,190,200,25);
		
		bth = new JButton("Back To Home");			bth.setBounds(200,300,200,25);
		bth.setBackground(Color.LIGHT_GRAY);		bth.addActionListener(this);

		f.add(dr_user);		f.add(dr_name);
		f.add(dr_spec);		f.add(dr_mob);		f.add(bth);

	/* Logical Part of the Program Start */

		try{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("--------------------");
			System.out.println("\nDrivers established\n");
			System.out.println("--------------------");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			System.out.println("--------------------");
			System.out.println("\nconnection established\n");
			System.out.println("--------------------");
			
			PreparedStatement ps = con.prepareStatement("select * from drlogin where DB_USER=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				dr_user.setText(rs.getString("DB_USER"));
			 	dr_name.setText(rs.getString("DB_FNAME")+" "+rs.getString("DB_LNAME"));
				dr_spec.setText(rs.getString("DB_SPEC"));
				dr_mob.setText(rs.getString("DB_MOBNO"));
			}
			con.close();		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		f.setLayout(null);
		f.setSize(600,600);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e)
	{
		String arg = e.getActionCommand();
		if (arg.equals("Back To Home")) {
			home_pg hp = new home_pg();	
			f.setVisible(true);
			f.dispose();
		}
	}

	/* Logical Part of the Program End */
}