package app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.Account;
import model.Person;

public class Program {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Person p1 = new Person("Ze Manel", "8264763254", 23);
		Person p2 = new Person("Laurinda", "2343725", 47);
		Account a1 = new Account(1000,1,"Ze Manel");
		Account a2 = new Account(2000,2,"Laurinda");
		
		
		Class cPerson = p1.getClass();
		Field fAge = cPerson.getField("age");
		assert (Integer) fAge.get(p1) == 23;
		assert (Integer) fAge.get(p2) == 47;
		
		Class cAccount = a1.getClass();
		Field fNr = cAccount.getField("nr");
		assert (Integer) fNr.get(a1) == 1000;
		assert (Integer) fNr.get(a2) == 2000;
		
		System.out.println("TEST succeeded!");
		
	}

	/**
	 * Retorna uma mapa com os pares nome - valor dos campos
	 * do objecto o recebido por parametro.
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Map<String, Object> getFields(Object o) throws IllegalArgumentException, IllegalAccessException{
		Map<String, Object> hashmap = new HashMap<String, Object>();
		Field[] FieldsArray = o.getClass().getFields();
		for(Field field:FieldsArray){
			hashmap.put(field.getName(), field.get(o));
		}
		return hashmap;
	}
}
