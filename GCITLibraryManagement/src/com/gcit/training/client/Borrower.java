package com.gcit.training.client;

import java.util.Scanner;

import com.gcit.training.library.service.BorrowerService;

public class Borrower {

	public void begin(Scanner in) throws Exception{

		BorrowerService service = new BorrowerService();
		int choice = 0;
		boolean temp = false;

		while(temp == false){
			System.out.print("Enter your card number: ");
			choice = in.nextInt();
			temp = service.checkCardNo(choice);

			if(temp == false)
				System.out.println("Invalid card number");
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
					service.displayLibraryBranch();
					choice = in.nextInt();

					if(choice < service.max){
						service.chooseBranch(choice);
						boolean temp3 = true;

						while(temp3){
							System.out.println("Pick the book you want to check out");
							service.displayBranchCopies();
							choice = in.nextInt();

							if(choice < service.max){
								service.chooseBook(choice);
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
					service.displayBooksCheckedOut();
					choice = in.nextInt();

					if(choice < service.max){					
						service.returnBook(choice);
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
