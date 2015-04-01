package com.gcit.training.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.service.LibrarianService;

public class LibrarianClient {
	private LibraryBranch branch = null;
	private BookCopies copies = null; 
	private List<BookCopies> copiesList = null;
	private List<LibraryBranch> lbList = null;
	private int max = 0;

	public void begin(Scanner in) throws Exception{
		System.out.println("Select a library branch");
		LibrarianService service = new LibrarianService();
		boolean temp = true;
		while(temp){

			lbList = service.displayLibraryBranches();			
			int choice = in.nextInt();
			max = lbList.size()+1;

			if(choice < max){		
				branch = lbList.get(choice-1);

				boolean temp2 = true;

				while(temp2){
					System.out.println("1) Update the details of the library \n"
							+ "2) Add copies of book to branch \n"
							+ "3) Quit to previous");

					choice = in.nextInt();

					switch (choice) {
					case 1:
						//update library details
						//LibraryBranch branch = service.getBranch();
						boolean temp4 = true;
						while(temp4){
							System.out.print("You have chosen to update the branch with Branch Id: " + branch.getBranchid() +
									" and Branch Name: " + branch.getBname()+". Enter 'quit' at any prompt to cancel operation. \n" +
									"Please enter a new branch name or enter 'N/A' for no change: ");
							String input = in.nextLine();
							input = in.nextLine();

							if(input.equals("quit")){
								temp4 = false;
								break;							
							} else if(!input.equals("N/A"))
								branch.setBname(input);

							System.out.print("Please enter new branch address  or enter 'N/A' for no change: ");
							input = in.nextLine();

							if(input.equals("quit")){
								temp4 = false;
								break;							
							} else if(!input.equals("N/A"))
								branch.setBaddr(input);

							service.updateBranch(branch);

							System.out.println("Branch updated successfully \n");
							temp4 = false;
						}
						break;

					case 2:
						//add book copies
						boolean temp3 = true;
						while(temp3){
							System.out.println("Pick the book you want to add copies of to your branch");
							copiesList = service.displayBranchCopies(branch.getBranchid());
							max = copiesList.size() +1;
							choice = in.nextInt();

							if(choice < max){
								copies = copiesList.get(choice - 1);

								System.out.print("Existing number of copies: " + copies.getNoOfCopies()+
										"\nEnter new number of copies: ");
								int numCopies = in.nextInt();
								copies.setNoOfCopies(numCopies);
								service.setBranchCopies(copies);

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
