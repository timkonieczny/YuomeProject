import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class YuomeMainFrame extends JFrame {

	public YuomeMainFrame(){
		super("Yuome");
		JPanel left_panel = new JPanel();
		JPanel upper_left_panel = new JPanel();
		JPanel lower_left_panel = new JPanel();
		JPanel right_panel = new JPanel();
		JPanel upper_right_panel = new JPanel();
		JPanel lower_right_panel = new JPanel();
		JPanel bottom_right_panel = new JPanel();
		JList list = new JList();
		JList depts_list = new JList();
		JTextField user_name = new JTextField(11);
		JButton add_user = new JButton("hinzufügen");
		JTextField value = new JTextField(10);
		JButton add_value = new JButton("hinzufügen");
		JTextField repay_value = new JTextField(8);
		JButton add_repay = new JButton("zahlen");
		JScrollPane pane = new JScrollPane(list);
		JScrollPane depts_pane = new JScrollPane(depts_list);
		JLabel add_user_label = new JLabel("      Neuer Benutzer:");
		JLabel add_value_label = new JLabel("Einkauf hinzufügen:");
		JLabel repay_label = new JLabel("Rückzahlung:");
		JLabel repay_to = new JLabel(" an ");
		JLabel euro = new JLabel("€");
		JLabel euro2 = new JLabel("€");
		JLabel balance_label = new JLabel("Kontostand");
		JLabel depts_label = new JLabel("            Schulden");
		JComboBox pay_user = new JComboBox();
		JComboBox payed_user = new JComboBox();
		YuomeController yuome = new YuomeController();
		
		AddUserListener add_user_listener = new AddUserListener(yuome, list, user_name, pay_user, payed_user);
		AddValueListener add_value_listener = new AddValueListener(yuome, list, depts_list, value);
		AddRepayListener add_repay_listener = new AddRepayListener(yuome, list, depts_list, repay_value, pay_user, payed_user);
		
		HashMap<String,Double> users = yuome.getUsers();
		Object[] users_arr = users.entrySet().toArray();
		Object[] usernames_arr = users.keySet().toArray();
		list.setListData(users_arr);
		
		add(left_panel, BorderLayout.WEST);
		add(right_panel, BorderLayout.EAST);
		
		for(Object user : usernames_arr){
			pay_user.addItem(user.toString());
		}
		pane.setPreferredSize(new Dimension(100,200));
		depts_pane.setPreferredSize(new Dimension(150,200));
		left_panel.setLayout(new BorderLayout());
		left_panel.add(upper_left_panel, BorderLayout.NORTH);
		upper_left_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upper_left_panel.add(balance_label);
		upper_left_panel.add(depts_label);
		left_panel.add(lower_left_panel, BorderLayout.CENTER);
		lower_left_panel.add(pane);
		lower_left_panel.add(depts_pane);
		right_panel.setLayout(new BorderLayout());
		right_panel.add(upper_right_panel, BorderLayout.NORTH);
		right_panel.add(lower_right_panel, BorderLayout.CENTER);
		right_panel.add(bottom_right_panel, BorderLayout.SOUTH);
		upper_right_panel.add(add_user_label);
		upper_right_panel.add(user_name);
		upper_right_panel.add(add_user);
		lower_right_panel.add(add_value_label);
		lower_right_panel.add(value);
		lower_right_panel.add(euro);
		lower_right_panel.add(add_value);
		bottom_right_panel.add(repay_label);
		pay_user.setPreferredSize(new Dimension(100,23));
		payed_user.setPreferredSize(new Dimension(100,23));
		bottom_right_panel.add(pay_user);
		bottom_right_panel.add(repay_to);
		bottom_right_panel.add(payed_user);
		bottom_right_panel.add(repay_value);
		bottom_right_panel.add(euro2);
		bottom_right_panel.add(add_repay);
		
		add_user.addActionListener(add_user_listener);
		add_value.addActionListener(add_value_listener);
		add_repay.addActionListener(add_repay_listener);	
	}
}
