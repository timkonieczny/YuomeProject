import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class YuomeController {

	private HashMap<String, Double> users;
	private HashMap<HashMap<String, String>, Double> depts;
	private Scanner scanner;
	
	public YuomeController(){
		scanner = new Scanner(System.in);
		depts = new HashMap<>();
		users = new HashMap<>();
	}

	public HashMap<String, Double> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, Double> users) {
		this.users = users;
	}

	public HashMap<HashMap<String, String>, Double> getDepts() {
		return depts;
	}

	public void setDepts(HashMap<HashMap<String, String>, Double> depts) {
		this.depts = depts;
	}
	public void addUser(String name){
		users.put(name, 0.0);
	}
	public void addBuy(Double betrag, String name){
		double schuldner_betrag = 0 - (betrag / users.size());
		double einzahler_betrag = betrag + schuldner_betrag;
		for (Entry<String, Double> entry : users.entrySet()){
			if(entry.getKey().equals(name)){
				double temp = entry.getValue();
				entry.setValue(temp + einzahler_betrag);
			}
			else{
				double temp = entry.getValue();
				entry.setValue(temp + schuldner_betrag);
			}
		}
	}
	public void repay(String pay_user, String payed_user, Double pay_user_value){
		double temp = 0.0;
		temp = users.get(pay_user);
		users.put(pay_user, temp + pay_user_value);
		temp = users.get(payed_user);
		users.put(payed_user, temp - pay_user_value);
		depts = calculateDepts(users);
	}
	public HashMap<HashMap<String, String>, Double> calculateDepts(HashMap<String, Double> users){
		HashMap<String, Double> userdepts = new HashMap<>();
		HashMap<HashMap<String, String>, Double> zuzahlen = new HashMap<>();
		HashMap<String, Double> temphash = new HashMap<>();
		double temp = 0.0;
		String pay_user = "";
		String payed_user = "";
		boolean go_on = true;
		
		for (Entry<String, Double> entry : users.entrySet()){
			userdepts.put(entry.getKey(), entry.getValue());
		}
		
		while(go_on){
			HashMap<String, Double> einzahler = new HashMap<>();
			HashMap<String, Double> schuldner = new HashMap<>();
			double pay_user_value = (Collections.max(userdepts.values()));;
			double pay_user_value_over = 0.0;
			temphash.clear();
			for (Entry<String, Double> entry : userdepts.entrySet()){
				if(entry.getValue() > 0){
					einzahler.put(entry.getKey(),entry.getValue());
				}	
			}
			for (Entry<String, Double> entry : userdepts.entrySet()){
				if(entry.getValue() < 0){
					schuldner.put(entry.getKey(),entry.getValue());
				}	
			}
			for (Entry<String, Double> einzahler_entry : einzahler.entrySet()){
				for (Entry<String, Double> schuldner_entry : schuldner.entrySet()){
					if((einzahler_entry.getValue() + schuldner_entry.getValue() < pay_user_value) && (einzahler_entry.getValue() - schuldner_entry.getValue() >= 0)){
						pay_user_value_over = einzahler_entry.getValue() + schuldner_entry.getValue();
						if(pay_user_value_over >= 0){
							pay_user_value = schuldner_entry.getValue() * -1;
						}
						else if(pay_user_value_over < 0){
							pay_user_value = (schuldner_entry.getValue() - pay_user_value_over) * -1;
						}
						pay_user = schuldner_entry.getKey();
						payed_user = einzahler_entry.getKey();
					}
				}
			}
			if(userdepts.get(pay_user) != null){
				temp = userdepts.get(pay_user);
				userdepts.put(pay_user, temp + pay_user_value);
				temp = userdepts.get(payed_user);
				userdepts.put(payed_user, temp - pay_user_value);
			}
			
			final String pay_user_final = pay_user;
			final String payed_user_final = payed_user;
			final double pay_user_value_final = Math.round(pay_user_value * 100) / 100.;
			if (pay_user_value_final >= 0.01){
			zuzahlen.put(new HashMap<String, String>(){{ put(pay_user_final, payed_user_final); }}, pay_user_value_final);
			}
			go_on = false;
			for (Entry<String, Double> entry : userdepts.entrySet()){
				if(entry.getValue() >= 0.01){
					go_on = true;
				}
			}
			
		}
		return zuzahlen;
	}
}
