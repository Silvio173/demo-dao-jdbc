package Aplication;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
	
		System.out.println("====== Teste 1 = sellerById =========");
		Seller seller = sellerDao.findById(2);
		System.out.println(seller);
		
		System.out.println();
		System.out.println();
		
		System.out.println("====== Teste 2 = sellerByDepartment=========");
			Department department = new Department(2, null);
			List<Seller> list = sellerDao.findByDepartment(department);
			for(Seller obj: list) {
				System.out.println(obj);
			}
		}

}
