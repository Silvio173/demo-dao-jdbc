package Aplication;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao(); 
		
		System.out.println("========= FindById in Department================");
		Department department = departmentDao.findById(2);
		System.out.println(department);
		System.out.println();
		System.out.println("========= DeleteById in Department================");
		departmentDao.deleteById(12);
		System.out.println("Deleted");
	
		System.out.println("========= Insert in Department================");
		department.setName("Cozinha");
		departmentDao.insert(department);
		System.out.println("Sucess insert into Department");
		
		System.out.println("========= Update in Department================");
		Department dpto = departmentDao.findById(17);
		dpto.setName("Refeitorio");
		departmentDao.update(dpto);
		System.out.println("Atualizao com sucesso");
		System.out.println("========= List All  Department================");
		List<Department> listAll = departmentDao.findAll();
		for(Department dpto2 : listAll) {
			System.out.println(dpto2);
		}
	}
}
