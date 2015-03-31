package com.gcit.training.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.service.LibrarianService;

public class Librarian {

	public void begin(Scanner in) throws Exception{
		System.out.println("Select a library branch");
		LibrarianService service = new LibrarianService();
		boolean temp = true;
		while(temp){
			service.displayLibraryBranches();
			
			int choice = in.nextInt();

			if(choice < service.max){
				
				service.chooseBranch(choice);
				
				boolean temp2 = true;
				
				while(temp2){
					System.out.println("1) Update the details of the library \n"
							+ "2) Add copies of book to branch \n"
							+ "3) Quit to previous");
					
					choice = in.nextInt();
					
					switch (choice) {
					case 1:
						//update library details
						LibraryBranch branch = service.getBranch();
						
						System.out.print("You have chosen to update the branch with Branch Id: " + branch.getBranchid() +
								" and Branch Name: " + branch.getBname()+". Enter 'quit' at any prompt to cancel operation. \n" +
								"Please enter a new branch name or enter 'N/A' for no change: ");
						String input = in.nextLine();
						input = in.nextLine();
						
						if(input.equals("quit")){
							break;							
						} else if(!input.equals("N/A"))
							branch.setBname(input);
						
						System.out.print("Please enter new branch address  or enter 'N/A' for no change: ");
						input = in.nextLine();
						
						if(input.equals("quit")){
							break;							
						} else if(!input.equals("N/A"))
							branch.setBaddr(input);
						
						service.updateBranch(branch);
						
						System.out.println("Branch updated successfully \n");
						
						break;
					case 2:
						//add book copies
						boolean temp3 = true;
						while(temp3){
							System.out.println("Pick the book you want to add copies of to your branch");
							service.displayBranchCopies();
							choice = in.nextInt();

							if(choice < service.max){
								System.out.print("Existing number of copies: " + service.numBranchCopies(choice)+
										"\nEnter new number of copies: ");
								int numCopies = in.nextInt();
								service.setBranchCopies(choice, numCopies);
								System.out.println("Number of copies updated successfully \n");
							}
							else
								temp3 = false;
						}
						break;

					case 3:
						//quit
						temp2 = false;
						break;
					default:
						break;
					}					
				}//loop back to previous menu	
			}
			else 
				temp = false;	
		}	
	}//loop back to previous menu
}
