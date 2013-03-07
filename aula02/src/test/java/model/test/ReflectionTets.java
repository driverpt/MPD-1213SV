package model.test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import app.Program;

import model.Account;
import model.Person;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ReflectionTets extends TestCase{
	
	Person p1 = new Person("Ze Manel", "8264763254", 23);
	Person p2 = new Person("Laurinda", "2343725", 47);
	Account a1 = new Account(1000,1,"Ze Manel");
	Account a2 = new Account(2000,2,"Laurinda");

	public void test_person_age_field() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{		
		Class cPerson = p1.getClass();
		Field fAge = cPerson.getField("age");
		Assert.assertEquals(23, ((Integer) fAge.get(p1)).intValue());
		Assert.assertEquals(47, ((Integer) fAge.get(p2)).intValue());	
	}
	

	public void test_person_name_field() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{		
		Class cPerson = p1.getClass();
		Field fName = cPerson.getField("name");
		Assert.assertEquals("Ze Manel", fName.get(p1));
		Assert.assertEquals("Laurinda", fName.get(p2));	
	}

	public void test_person_fields_values() throws IllegalArgumentException, IllegalAccessException{
		Map<String, Object> fieldsVals1 = Program.getFields(p1);
		Map<String, Object> fieldsVals2 = Program.getFields(p2);
		
		Assert.assertEquals("Ze Manel", fieldsVals1.get("name"));
		Assert.assertEquals("8264763254", fieldsVals1.get("id"));
		Assert.assertEquals(23, fieldsVals1.get("age"));
		
		Assert.assertEquals("Laurinda", fieldsVals2.get("name"));
		Assert.assertEquals("2343725", fieldsVals2.get("id"));
		Assert.assertEquals(47, fieldsVals2.get("age"));
		
	}
	
	public void test_account_fields_values() throws IllegalArgumentException, IllegalAccessException{
		Map<String, Object> fieldsVals3 = Program.getFields(a1);
		Map<String, Object> fieldsVals4 = Program.getFields(a2);
		Long value1 = (long) 1000;
		Long value2 = (long) 2000;
		Assert.assertEquals(value1, fieldsVals3.get("balance"));
		Assert.assertEquals(1, fieldsVals3.get("nr"));
		Assert.assertEquals("Ze Manel", fieldsVals3.get("holder"));
		
		Assert.assertEquals(value2, fieldsVals4.get("balance"));
		Assert.assertEquals(2, fieldsVals4.get("nr"));
		Assert.assertEquals("Laurinda", fieldsVals4.get("holder"));
	}
	
}
