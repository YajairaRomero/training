package com.gcit.training.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

			Scanner input = new Scanner(System.in);

			boolean cont = true;   //while the program is still continuing
			
			while(cont){ //loop back to main menu
				
				System.out.println("Welcome to the GCIT Library Management System. "
						+ "Which category of user are you?\n1)Librarian \n2)Administrator \n3)Borrower");
				
				int ch = input.nextInt();  //user's choice

				boolean cont2 = true;
				
				switch (ch) {

				
				case 1:
					//Librarian
					while(cont2){
					Librarian lib = new Librarian();
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
					//Administrator
					
					break;
					
				case 3:
					//borrower
					
					break;
					
				default:
					break;
					
					
				}
				
			}//loop back to main menu
		
	}

}
