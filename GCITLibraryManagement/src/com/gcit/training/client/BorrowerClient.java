package com.gcit.training.client;

import java.util.List;
import java.util.Scanner;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.BookLoan;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.service.BorrowerService;

public class BorrowerClient {

	private int max = 0;
	private List<LibraryBranch> lbList = null;
	private LibraryBranch branch = null;
	private List<BookCopies> copiesList = null;
	private List<BookLoan> loanList = null;
	private BookCopies copy = null;
	private Borrower borr = null;
	private BookLoan loan = null;

	public void begin(Scanner in) throws Exception{

		BorrowerService service = new BorrowerService();
		int choice = 0;
		boolean temp = false;


		while(temp == false){
			System.out.print("Enter your card number: ");
			choice = in.nextInt();
			borr = service.checkCardNo(choice);

			if(borr == null){
				System.out.println("Invalid card number");
				temp = false;
			}
			else
				temp = true;
		}

		while(temp){
			System.out.println("1) Check out a book \n2) Return a book \n3) Quit to previous");
			choice = in.nextInt();
			boolean temp2 = true;

			switch (choice) {
			case 1:
				//check out a book

				while(temp2){
					System.out.println("Pick the branch you want to check out from");
					lbList = service.displayLibraryBranch();
					max = lbList.size() + 1;
					choice = in.nextInt();

					if(choice < max){
						branch = lbList.get(choice - 1);
						
						boolean temp3 = true;

						while(temp3){
							System.out.println("Pick the book you want to check out");
							copiesList = service.displayBranchCopies(branch);
							max = copiesList.size() + 1;
							choice = in.nextInt();

							if(choice < max){
								copy = copiesList.get(choice - 1); 
								service.chooseBook(copy, borr, branch);
								System.out.println("Book checked out successfully \n");
							}
							else 
								temp3 = false;
						}
					}

					else
						temp2 = false;

				}//loop back to menu
				break;

			case 2:
				//return a book
				while(temp2){
					System.out.println("Pick the book you want to return");
					loanList = service.displayBooksCheckedOut(borr);
					max = loanList.size()+1;
					choice = in.nextInt();

					if(choice < max){	
						loan = loanList.get(choice - 1);
						service.returnBook(loan);
						System.out.println("Book returned successfully\n");
					}
					else
						temp2 = false;
				}
				break;

			case 3:
				temp = false;
				break;

			default:
				break;
			}

		}
	}

}
