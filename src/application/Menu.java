package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class Menu {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		int option;
		do {
			System.out.println("\n=== MAIN MENU ===");
			System.out.println("1 - Seller");
			System.out.println("2 - Department");
			System.out.println("0 - Sair");
			option = sc.nextInt();
			
			switch (option) {
				case 1:
					sellerMenu(sc);
					break;
				case 2:
					departmentMenu(sc);
					break;
			}
			
		} while(option != 0);
		
		sc.close();
	}
	
	
	private static void sellerMenu(Scanner sc) {
	    int option;

	    SellerDao sellerDao = DaoFactory.createSellerDao();

	    do {
	        System.out.println("\n--- SELLER MENU ---");
	        System.out.println("1 - Read by Id");
	        System.out.println("2 - Read by Department");
	        System.out.println("3 - Read All");
	        System.out.println("4 - Create");
	        System.out.println("5 - Update");
	        System.out.println("6 - Delete");
	        System.out.println("0 - Exit");

	        System.out.print("Choose an option: ");
	        option = sc.nextInt();

	        switch (option) {
	            case 1:
	                System.out.print("Enter seller ID: ");
	                int id = sc.nextInt();
	                Seller seller = sellerDao.findById(id);
	                System.out.println(seller);
	                break;

	            case 2:
	                System.out.print("Enter department ID: ");
	                int depId = sc.nextInt();
	                Department department = new Department(depId, null);

	                List<Seller> listByDep = sellerDao.findByDepartment(department);
	                for (Seller obj : listByDep) {
	                    System.out.println(obj);
	                }
	                break;

	            case 3:
	                List<Seller> listAll = sellerDao.findAll();
	                for (Seller obj : listAll) {
	                    System.out.println(obj);
	                }
	                break;

	            case 4:
	                sc.nextLine();

	                System.out.print("Enter name: ");
	                String name = sc.nextLine();

	                System.out.print("Enter email: ");
	                String email = sc.nextLine();

	                System.out.println("Enter de birth date: ");
	                String dateStr = sc.nextLine();
	                
	                Date birthDate = null;
	                try {
	                	birthDate = sdf.parse(dateStr);
	                	
	                }catch (Exception e) {
	                    System.out.println("Invalid date format!");
	                    break;
	                }
	                
	                System.out.print("Enter base salary: ");
	                double salary = sc.nextDouble();

	                System.out.print("Enter department ID: ");
	                int deptId = sc.nextInt();

	                Department dept = new Department(deptId, null);

	                Seller newSeller = new Seller(null, name, email, birthDate, salary, dept);
	                sellerDao.insert(newSeller);

	                System.out.println("Inserted! New id = " + newSeller.getId());
	                break;

	            case 5:
	                System.out.print("Enter ID to update: ");
	                int updateId = sc.nextInt();
	                sc.nextLine();

	                Seller sellerToUpdate = sellerDao.findById(updateId);

	                System.out.print("Enter new name: ");
	                String newName = sc.nextLine();

	                sellerToUpdate.setName(newName);
	                sellerDao.update(sellerToUpdate);

	                System.out.println("Update completed");
	                break;

	            case 6:
	                System.out.print("Enter ID to delete: ");
	                int deleteId = sc.nextInt();

	                sellerDao.deleteById(deleteId);
	                System.out.println("Delete completed");
	                break;

	            case 0:
	                System.out.println("Exiting...");
	                break;

	            default:
	                System.out.println("Invalid option!");
	        }

	    } while (option != 0);
	}
	
	private static void departmentMenu(Scanner sc) {
	    int option;

	    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	    do {
	        System.out.println("\n--- DEPARTMENT MENU ---");
	        System.out.println("1 - Read by Id");
	        System.out.println("2 - Read All");
	        System.out.println("3 - Create");
	        System.out.println("4 - Update");
	        System.out.println("5 - Delete");
	        System.out.println("0 - Exit");

	        System.out.print("Choose an option: ");
	        option = sc.nextInt();

	        switch (option) {
	            case 1:
	                System.out.print("Enter department ID: ");
	                int id = sc.nextInt();

	                Department dep = departmentDao.findById(id);
	                System.out.println(dep);
	                break;

	            case 2:
	                List<Department> list = departmentDao.findAll();
	                for (Department d : list) {
	                    System.out.println(d);
	                }
	                break;

	            case 3:
	                sc.nextLine();

	                System.out.print("Enter department name: ");
	                String name = sc.nextLine();

	                Department newDepartment = new Department(null, name);
	                departmentDao.insert(newDepartment);

	                System.out.println("Inserted! New id = " + newDepartment.getId());
	                break;

	            case 4:
	                System.out.print("Enter ID to update: ");
	                int updateId = sc.nextInt();
	                sc.nextLine();

	                Department depToUpdate = departmentDao.findById(updateId);

	                System.out.print("Enter new name: ");
	                String newName = sc.nextLine();

	                depToUpdate.setName(newName);
	                departmentDao.update(depToUpdate);

	                System.out.println("Update completed");
	                break;

	            case 5:
	                System.out.print("Enter ID to delete: ");
	                int deleteId = sc.nextInt();

	                departmentDao.deleteById(deleteId);
	                System.out.println("Delete completed");
	                break;

	            case 0:
	                System.out.println("Exiting...");
	                break;

	            default:
	                System.out.println("Invalid option!");
	        }

	    } while (option != 0);
	}
}
