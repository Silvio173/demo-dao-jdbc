package Aplication;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj = new Department(1, "Livros"); 
		System.out.println(obj);
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 7600.20, obj);
		
		System.out.println(seller);

	}

}
