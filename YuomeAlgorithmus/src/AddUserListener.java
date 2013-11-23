import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;


public class AddUserListener implements ActionListener {
	
	private YuomeController yuome;
	private JList list;
	private JTextField user_name;
	private JComboBox pay_user;
	private JComboBox payed_user;

	public AddUserListener(YuomeController yuome, JList list, JTextField user_name, JComboBox pay_user, JComboBox payed_user) {
		// TODO Auto-generated constructor stub
		this.yuome = yuome;
		this.list = list;
		this.user_name = user_name;
		this.pay_user = pay_user;
		this.payed_user = payed_user;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		yuome.addUser(user_name.getText());
		HashMap<String,Double> users = yuome.getUsers();
		Object[] users_arr = users.entrySet().toArray();
		Object[] usernames_arr = users.keySet().toArray();
		list.setListData(users_arr);
		user_name.setText("");
		pay_user.removeAllItems();
		payed_user.removeAllItems();
		for(Object user : usernames_arr){
			pay_user.addItem(user.toString());
			payed_user.addItem(user.toString());
		}
	}

}
