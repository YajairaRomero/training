package com.gcit.training.librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Main {

	public static void main(String[] args) {

		try {

			Scanner input = new Scanner(System.in);

			Common comm = new Common();

			int ch = 0;  //user's choice

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/library", "root", "");

			Statement stmt = conn.createStatement();

			PreparedStatement ps = null;

			boolean cont = true;   //while the program is still continuing

			while(cont){ //loop back to main menu

				System.out.println("Welcome to the GCIT Library Management System. "
						+ "Which category of user are you?\n1)Librarian \n2)Administrator \n3)Borrower");
				ch = input.nextInt();  //user's choice


				switch (ch) {

				//Librarian
				case 1:

					Librarian lib = new Librarian();

					boolean cont2 = true;

					while(cont2){ //loop back to menu LIB1

						System.out.println("\nLIBRARIAN \n1) Enter a Branch You Manage \n"
								+ "2) Quit to Previous");
						int x = input.nextInt();

						if(x == 1){

							boolean cont3 = true;

							while(cont3){ //loop back to menu LIB2

								lib.returnLocations(stmt, comm); 

								x = input.nextInt();    //the library the user chose 

								lib.chooseLibrary(x, stmt);	//get library name		

								if(x < lib.max){

									boolean cont4 = true;

									while(cont4){ //loop back to menu LIB3

										System.out.println("1) Update the details of the library\n"
												+ "2) Add copies of book to branch\n"
												+ "3) Quit to previous");
										x = input.nextInt();

										if(x == 1){
											//update library details

											int result = lib.update(stmt, input);

											if(result == 1 )
												System.out.println("Update complete");

											else {
												System.out.println("No update");
												continue;
											}
										}

										else if(x == 2){
											//add books

											lib.printBooks(stmt, comm);	

											x = input.nextInt();

											if(x < lib.max)												
												lib.addBooks(stmt, x, input);												

											else
												continue;
										}

										else
											break;
									} //loop back to menu LIB3

								}
								else
									cont3 = false;

							} //loop back to menu LIB2
						}

						//Quit to main page
						if(x == 2)
							break;

					} //loop back to menu LIB1

					break;



					//Administrator
				case 2:
					System.out.println("\nADMINISTRATOR\n1) Manage book and author\n"
							+ "2) Manage publishers\n"
							+ "3) Manage library branches\n"
							+ "4) Manage borrowers\n"
							+ "5) Over-ride due date for a book");

					int y = input.nextInt();

					switch (y) {
					case 1:
						//manage book and author
						System.out.println("Manage: \n1) Books and Authors \n2) Publishers \n3)Library Publishers \n"
								+ "4) Over-ride Due Date for a Book");
						y = input.nextInt();

						if(y == 1){

							//add book or author
							//String table = ""
							System.out.println("1");

						}

						else if (y ==2){


						}

						else if(y == 3){

						}

						break;

					case 2:
						//manage publishers
						break;

					case 3:
						//manage library branches
						break;

					case 4:
						//manage borrowers
						
						System.out.println("1) Add a borrower \n2) Update a borrower \n 3)Delete a borrower \n"
								+ "4)Quit to previous");
						y = input.nextInt();
						
						if(y == 1){
							//add a borrower
							System.out.print("You're about to enter information for a new borrower. Enter 'quit' at any"
									+ "prompt to cancel. \nEnter new borrower name: ");
							String name = input.nextLine();
							
							if(name.equals("quit")){
								
								//quit
							}
							
							System.out.print("\nEnter new borrower address: ");
							String addr = input.nextLine();
							
							if(addr.equals("quit")){
								//quit
							}
							
							System.out.print("\nEnter new borrower phone number: ");
							String phone = input.nextLine();
							
							if(phone.equals("quit")){
								//quit
							}
							
							String query = "insert into tbl_borrower values(" + name + ", " + addr + ", " + ", phone)";
							
						}
						
						else if(y==2){
							//update a borrower
						}
						else if (y == 3){
							//delete a borrower
						}
						
						else{
							//go back
						}
							
						break;

					case 5:
						//over-ride due date 
						break;

					default:
						break;
					}

					break;



				//Borrower
				case 3:
					Borrower borr = new Borrower();
					int x = 0;
					boolean check = false, bcont = true;

					while(bcont){ //loop back to menu BORR1
						System.out.println("\nBORROWER");

						while(check == false){
							System.out.print("Enter your card number: ");
							x = input.nextInt();

							//check that the user gives a valid card number
							check = borr.checkUser(stmt, x);

							if(check == false)
								System.out.println("Invalid card number.");
						}

						System.out.println("1) Check out book \n2) Return a book \n3) Quit to previous");

						x = input.nextInt();

						if(x == 1){
							//Check out a book
							boolean bcont2 = true;

							while(bcont2){ //loop back to menu BORR2 
								borr.returnLocations(stmt, comm);
								x = input.nextInt();

								borr.chooseLibrary(x, stmt);

								if(x < borr.max){
									boolean bcont3 = true;

									while(bcont3){ //loop back to menu BORR3
										//choose book you want to check out
										System.out.println("Pick the book you want to check out");
										borr.chooseBook(stmt, comm, input);
										x = input.nextInt();  //book user chose

										if(x < borr.max && x>0){

											borr.getBookId(x, stmt);
											//check out the book
											String q = borr.returnQuery();

											ps= (PreparedStatement) conn.prepareStatement(q);

											//set current date
											Date d = new Date();
											java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());

											//get next week's date
											Calendar c = new GregorianCalendar();
											Date ct = c.getTime();
											c.setTime(ct);
											c.add(Calendar.DAY_OF_YEAR, 7);
											Date duedate = c.getTime();
											java.sql.Timestamp ts2 = new java.sql.Timestamp(duedate.getTime());


											ps.setTimestamp(1, ts); //set dateOut value for query
											ps.setTimestamp(2, ts2); //set dueDate value for query
											ps.executeUpdate();

											System.out.println("Book checked out successfully\n");
										}

										else 
											bcont3=false;

									}//loop back to menu BORR3

								}								

								else
									bcont2=false;
							}//loop back to menu BORR2
						} 

						else if(x == 2){

							//return a book
							
						}

						else 
							bcont = false;

					}//loop back to menu BORR1
					break;

				default:
					break;
				}


			} //loop back to main menu

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
