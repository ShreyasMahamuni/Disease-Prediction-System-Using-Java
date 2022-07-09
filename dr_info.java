import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Details of Doctors with respect to Disease

class dr_info implements ActionListener
{
	JFrame f;
	JLabel dr_name,dr_spec,dr_mob,info,disease_predict ;
	JButton bth;
	
	dr_info(String nm)
	{
		f = new JFrame();
		f.setTitle("Doctor Information");

		disease_predict = new JLabel("Disease prediction : "+nm);	disease_predict.setBounds(200,50,300,25);
		info = new JLabel("Doctor Information : ");					info.setBounds(200,120,200,25);
		dr_name = new JLabel();										dr_name.setBounds(320,140,200,25);
		dr_mob = new JLabel();										dr_mob.setBounds(320,160,200,25);
		
		bth = new JButton("Back To Home");							bth.setBounds(200,300,200,25);
		bth.setBackground(Color.LIGHT_GRAY);
		bth.addActionListener(this);

		f.add(disease_predict);		f.add(info);
		f.add(dr_name);				f.add(dr_mob);		f.add(bth);

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
			
			PreparedStatement ps = con.prepareStatement("select * from drlogin where DB_SPEC=?");
            ps.setString(1, nm);
            ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
			 	dr_name.setText(rs.getString("DB_FNAME")+" "+rs.getString("DB_LNAME"));
				dr_mob.setText(rs.getString("DB_MOBNO"));

				System.out.println(rs.getString("DB_FNAME")+" "+rs.getString("DB_LNAME"));
				System.out.println(rs.getString("DB_MOBNO")+"\n");
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