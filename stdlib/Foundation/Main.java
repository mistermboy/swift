package Foundation;

import java.util.ArrayList;

import Entities.Nota;
import Entities.Person;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(new Person("Paco", "SL8R", 18));
		objs.add(new Nota("Comprar pan", "Comprar pan porque si no no hay"));

		ArrayList<Person> obj = new ArrayList<Person>();
		obj.add(new Person("Paco", "SL8R", 18));
		obj.add(new Person("Pepe", "SL8R", 18));
		obj.add(new Person("M", "SL8R", 18));
		obj.add(new Person("Rosendo", "SL8R", 18));
		obj.add(new Person("Kelly", "SL8R", 18));
		obj.add(new Person("Albus", "SL8R", 18));
		obj.add(new Person("Bulfric", "SL8R", 18));
		obj.add(new Person("Percibal", "SL8R", 18));

		XMLWriter._write(obj, "Personas", "XMLPersonas");
		XMLWriter._write(obj, "Objetos", "XMLObjetos");
		
		 
		System.out.println("Exito en la operacion");
	}

}
