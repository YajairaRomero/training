package com.gcit.training.client;

import java.util.Scanner;

import com.gcit.training.library.service.AdministratorService;

public class Administrator {

	public void begin(Scanner in){
		
		AdministratorService service = new AdministratorService();
		int choice = 0;
		boolean temp = true;
		
		while(temp){
			System.out.println("1) Manage Books \n2) Manage Authors \n3) Manage Publishers \n4) Manage Borrowers \n"
					+ "5) Over-ride Due Date for a Book Loan \n6) Quit to previous");
			choice = in.nextInt();
			
			switch (choice) {
			case 1:
				//Manage books
				System.out.println("BOOKS \n1)Add \n2) Update \n3) Delete \n4) Quit to previous");
				choice = in.nextInt();
				
				switch (choice) {
				case 1:
					//Add 
					break;
					
				case 2:
					
					break;
					
				case 3:
					
					break;
					
				case 4:
					
					break;					
					
				default:
					break;
				}
				
				break;
				
			case 2:
				//Manage authors
				break;

			case 3:
				//Manage publishers
				break;

			case 4:
				//Manage borrowers
				break;

			case 5:
				//over-ride due date
				break;
				
			case 6:
				temp = false;
				break;


			default:
				break;
			}
			
		}//loop back to menu
	}
}
