import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* 	Home Page 	*/ 
class home_pg implements ActionListener{

	JFrame fp_f;
	JButton next_btn,dp_sys;
	JComboBox s_list;
	JCheckBox covid19_sym1,covid19_sym2,d_sym1,d_sym2;

	home_pg()
	{
		fp_f = new JFrame();
		fp_f.setTitle("Home");
		fp_f.getContentPane().setBackground(Color.WHITE);

	/* Menu Bar Start */
		JMenuBar main_menu = new JMenuBar();
		fp_f.setJMenuBar(main_menu);
		main_menu.setBackground(Color.LIGHT_GRAY);
		JMenu admin_mn = new JMenu("Admin");
		main_menu.add(admin_mn);
		JMenuItem dr_reg_mn,dr_login_mn;
		admin_mn.add(dr_reg_mn = new JMenuItem("Register as Doctor"));
		admin_mn.add(dr_login_mn = new JMenuItem("Login as Doctor"));
	/* Menu Bar End */
		
		prediction();
		dr_reg_mn.addActionListener(this);
		dr_login_mn.addActionListener(this);

		fp_f.setLayout(null);
		fp_f.setSize(600,600);
		fp_f.setLocationRelativeTo(null);
		fp_f.setVisible(true);
	}
	
/* Prediction System UI Start */
	public void prediction()
	{
		Color darkGreen = new Color	(34,139,34);  
		String basic_sym[]={"Fever"};

		s_list = new JComboBox(basic_sym);
		s_list.setBounds(200,50,200,25);
		s_list.setBackground(Color.WHITE);

		next_btn = new JButton("Next");
		next_btn.setBounds(200,100,200,25);
		next_btn.setBackground(Color.LIGHT_GRAY);
		next_btn.setForeground(Color.WHITE);

		dp_sys = new JButton("Disease Prediction");
		dp_sys.setBounds(200,300,200,25);
		dp_sys.setBackground(darkGreen);
		dp_sys.setForeground(Color.WHITE);

		next_btn.addActionListener(this);
		dp_sys.addActionListener(this);

		fp_f.add(s_list);
		fp_f.add(next_btn);
		fp_f.add(dp_sys);
		dp_sys.setVisible(false);
	}
/* Prediction System UI End */

/* Disease Symptoms UI Start */
	public void covid19_fn()
	{
		covid19_sym1 = new JCheckBox("Cough");
		covid19_sym1.setBounds(120,180,200,25);
		covid19_sym2 = new JCheckBox("loss of taste or smell");
		covid19_sym2.setBounds(120,220,200,25);

		covid19_sym1.setBackground(Color.WHITE);
		covid19_sym2.setBackground(Color.WHITE);

		fp_f.add(covid19_sym1);
		fp_f.add(covid19_sym2);
	}

	public void Dengue_fn()
	{
		d_sym1 = new JCheckBox("Joint Pain");
		d_sym1.setBounds(350,180,200,25);		
		d_sym2 = new JCheckBox("Vomiting");
		d_sym2.setBounds(350,220,200,25);

		d_sym1.setBackground(Color.WHITE);
		d_sym2.setBackground(Color.WHITE);

		fp_f.add(d_sym1);
		fp_f.add(d_sym2);
	}
/* Disease Symptoms UI End */

/* Logical Part of the Program Start */
	public void actionPerformed(ActionEvent e)
	{
		String arg = (String)e.getActionCommand();

		if (arg.equals("Register as Doctor")) {
			dr_reg_module drm = new dr_reg_module();
			fp_f.setVisible(true);
			fp_f.dispose();
		}		
		if (arg.equals("Login as Doctor")) {	
			dr_Login_module dlm = new dr_Login_module();
			fp_f.setVisible(true);
			fp_f.dispose();
		}
		if (arg.equals("Next")) {
			if(s_list.getItemAt(s_list.getSelectedIndex()) == "Fever")
			{
				covid19_fn();
				Dengue_fn();
				dp_sys.setVisible(true);
			}
		}
		if (arg.equals("Disease Prediction")) {
			if (covid19_sym1.isSelected() && covid19_sym2.isSelected()) {
				dr_info dri = new dr_info("Covid");
				fp_f.setVisible(true);
				fp_f.dispose();
			}
			else if (d_sym1.isSelected() && d_sym2.isSelected()) {
				dr_info dri = new dr_info("Dengue");
				fp_f.setVisible(true);
				fp_f.dispose();
			}
			else{
				System.out.println("Select Correct Symptoms");
			}
		}
	}
}
/* Logical Part of the Program End */

/* Main Method */
class mainfile{

	public static void main(String[] args)  throws Exception {
		home_pg hp = new home_pg();	
	}
}