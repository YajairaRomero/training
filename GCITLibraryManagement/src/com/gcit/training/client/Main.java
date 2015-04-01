package com.gcit.training.client;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

			Scanner input = new Scanner(System.in);
			boolean cont = true;   
			
			while(cont){ //loop back to main menu
				
				System.out.println("Welcome to the GCIT Library Management System. "
						+ "Which category of user are you?\n1)Librarian \n2)Administrator \n3)Borrower");
				
				int ch = input.nextInt();  //user's choice
				boolean cont2 = true;
				
				switch (ch) {
				
				case 1:
					//Librarian
					while(cont2){
					LibrarianClient lib = new LibrarianClient();
					System.out.println("LIBRARIAN\n1) Enter branch you manage \n2) Quit to previous");
					int x = input.nextInt();
					
					if(x == 1){
						try {
							lib.begin(input);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
					else
						cont2 = false;
					}
					break;
					
				case 2:
					AdministratorClient admin = new AdministratorClient();
					try {
						admin.begin(input);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
					
				case 3:
					//borrower
					BorrowerClient borr = new BorrowerClient();
					try {
						borr.begin(input);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				default:
					break;
									
				}
				
			}//loop back to main menu
		
	}

}
