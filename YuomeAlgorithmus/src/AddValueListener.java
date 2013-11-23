import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AddValueListener implements ActionListener {
	
	private YuomeController yuome;
	private JList list;
	private JList depts_list;
	private JTextField value;

	public AddValueListener(YuomeController yuome, JList list, JList depts_list, JTextField value) {
		// TODO Auto-generated constructor stub
		this.yuome = yuome;
		this.list = list;
		this.value = value;
		this.depts_list = depts_list;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (list.getSelectedValues().length != 0){
			Entry entry = (Entry) list.getSelectedValue();
			String name = (String) entry.getKey();
			Double pay_value = Double.parseDouble(value.getText());
			yuome.addBuy(pay_value, name);
			HashMap<String,Double> users = yuome.getUsers();
			Object[] users_arr = users.entrySet().toArray();
			list.setListData(users_arr);
			HashMap<HashMap<String,String>,Double> depts = yuome.calculateDepts(users);
			Object[] depts_arr = depts.entrySet().toArray();
			depts_list.setListData(depts_arr);
			value.setText("");
		}
		else {
			JOptionPane.showMessageDialog(null, "Kein Benutzer ausgewählt!");
		}
	}

	
}
