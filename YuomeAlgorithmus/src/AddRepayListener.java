import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;


public class AddRepayListener implements ActionListener {
	
	private YuomeController yuome;
	private JList list;
	private JList depts_list;
	private JTextField repay_value;
	private JComboBox pay_user;
	private JComboBox payed_user;

	public AddRepayListener(YuomeController yuome, JList list,	JList depts_list, JTextField repay_value, JComboBox pay_user, JComboBox payed_user) {
		
		this.yuome = yuome;
		this.list = list;
		this.depts_list = depts_list;
		this.repay_value = repay_value;
		this.pay_user = pay_user;
		this.payed_user = payed_user;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		double pay_user_value = Double.parseDouble(repay_value.getText());
		yuome.repay(pay_user.getSelectedItem().toString(), payed_user.getSelectedItem().toString(), pay_user_value);
		HashMap<String,Double> users = yuome.getUsers();
		Object[] users_arr = users.entrySet().toArray();
		list.setListData(users_arr);
		HashMap<HashMap<String,String>, Double> depts = yuome.getDepts();
		Object[] depts_arr = depts.entrySet().toArray();
		depts_list.setListData(depts_arr);
	}

}
