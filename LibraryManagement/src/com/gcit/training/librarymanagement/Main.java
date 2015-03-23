package com.gcit.training.librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

								System.out.println("Choose your branch");
								ps = (PreparedStatement) conn.prepareStatement("select * from tbl_library_branch");

								ResultSet rs = ps.executeQuery();
								lib.max = comm.returnLocations(rs);

								x = input.nextInt();    //the library the user chose 

								ps = (PreparedStatement) conn.prepareStatement("select * from tbl_library_branch");
								rs = ps.executeQuery();
								lib.chooseLibrary(x, rs);	//get library name		

								if(x < lib.max){

									boolean cont4 = true;

									while(cont4){ //loop back to menu LIB3

										System.out.println("1) Update the details of the library\n"
												+ "2) Add copies of book to branch\n"
												+ "3) Quit to previous");
										x = input.nextInt();

										if(x == 1){
											//update library details

											int result = lib.update(input);

											if(result == 1 ){
												String query = "update tbl_library_branch set branchName = ?, branchAddress = ? where branchId =? " ;
												ps = (PreparedStatement) conn.prepareStatement(query);
												ps.setString(1, lib.getBranchName());
												ps.setString(2, lib.getAddress());
												ps.setInt(3, lib.getId());

												ps.executeUpdate();

												System.out.println("Update complete");
											}
											else {
												System.out.println("No update");
												continue;
											}
										}

										else if(x == 2){
											//add books
											System.out.println("Pick the book you want to add copies of to your library");

											String q = "select title from tbl_book_copies "
													+ "join tbl_book on tbl_book.bookId= tbl_book_copies.bookId "
													+ "where tbl_book_copies.branchiD = ?" ;

											ps = (PreparedStatement) conn.prepareStatement(q);
											ps.setInt(1, lib.getId());

											rs = ps.executeQuery();

											lib.max = comm.printBooks(rs);

											x = input.nextInt();

											if(x < lib.max)	{		
												q = "select tbl_book.bookId, noOfCopies from tbl_book_copies "
														+ "join tbl_book on tbl_book.bookId= tbl_book_copies.bookId where tbl_book_copies.branchiD = " + lib.getId();
												ps = (PreparedStatement) conn.prepareStatement(q);
												rs = ps.executeQuery();

												lib.addBooks(rs, x, input);	

												System.out.println("Enter new number of copies: ");
												x = input.nextInt();

												q = "update tbl_book_copies set noOfCopies = ? where branchId = ? and bookId = ?";
												ps = (PreparedStatement) conn.prepareStatement(q);
												ps.setInt(1, x);
												ps.setInt(2, lib.getId());
												ps.setInt(3, lib.getBookId());

												int res = ps.executeUpdate();

												if(res == 1){

													System.out.println("Update successful\n");
												}

											}
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
					Administrator admin = new Administrator();

					boolean acont = true;
					while(acont){
						System.out.println("\nADMINISTRATOR\n1) Manage book and author\n"
								+ "2) Manage publishers\n"
								+ "3) Manage library branches\n"
								+ "4) Manage borrowers\n"
								+ "5) Over-ride due date for a book\n"
								+ "6) Quit to previous");

						int y = input.nextInt();

						boolean acont2 = true;

						switch (y) {
						case 1:
							//manage book and author


							while(acont2){ //loop back to menu ADMIN2ab
								System.out.println("1) Manage books \n2) Manage authors \n"
										+ "3) Quit to previous");
								y = input.nextInt();

								boolean acont3 = true;

								switch (y) {
								case 1:
									//manage books

									while(acont3){ //loop back to menu ADMIN3ab
										System.out.println("1) Add book \n 2) Select book \n3) Quit to previous");
										//add an author

										y = input.nextInt();

										if(y ==1){
											//add book
											int res = admin.add3(input);

											if(res == 1){
												String query = "insert into tbl_author (authorName) values(?)";

												ps = (PreparedStatement) conn.prepareStatement(query);
												ps.setString(1, admin.getName());

												ps.executeUpdate();

												System.out.println("Author added");
											}
										}
										else if(y == 2){
											//select book
										}
										else
											acont3 = false;
									} // loop back to menu ADMIN3ab
									break;

								case 2:
									//manage authors

									while(acont3){ //loop back to menu ADMIN3ab
										System.out.println("1) Add author \n 2) Select author \n3) Quit to previous");
										//add an author

										y = input.nextInt();

										if(y == 1){
											//add author
											int res = admin.add3(input);

											if(res == 1){
												String query = "insert into tbl_author (authorName) values(?)";

												ps = (PreparedStatement) conn.prepareStatement(query);
												ps.setString(1, admin.getName());

												ps.executeUpdate();

												System.out.println("Author added");
											}
										}

										else if(y == 2){
											//select an author

											boolean acont4 = true;

											while(acont4){ //loop back to menu ADMIN3
												System.out.println("Select an author");
												String query = "select authorName from tbl_author";
												ps = (PreparedStatement) conn.prepareStatement(query);

												ResultSet rs = ps.executeQuery();
												int i =1; 
												
												// print author names
												while(rs.next()){
													System.out.println(i +") " + rs.getString("authorName") );
													i++;
												}
												System.out.println(i + ") Quit to previous");

												int y2 = input.nextInt();

												query = "select * from tbl_author";
												ps = (PreparedStatement) conn.prepareStatement(query);
												rs = ps.executeQuery();

												admin.chooseAuthor(rs, y2);


												if(y2 < i){
													boolean acont5 = true;

													while(acont5){ //loop back to menu ADMIN4
														System.out.println("1) Update publisher \n2) Delete publisher \n3) Quit to previous");
														y = input.nextInt();

														if(y==1){
															//update publisher
															int res = admin.update("publisher", input);

															if(res == 1){
																query = "update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? "
																		+ "where publisherId =? ";
																ps = (PreparedStatement) conn.prepareStatement(query);
																ps.setString(1, admin.getName());
																ps.setString(2, admin.getAddr());
																ps.setString(3, admin.getPhone());
																ps.setInt(4, admin.getId());

																res = ps.executeUpdate();

																if(res == 1)
																	System.out.println("Update successful");
															}

														}

														else if(y == 2){
															//delete publisher
															query = "delete from tbl_publisher where publisherId = ?";
															ps = (PreparedStatement) conn.prepareStatement(query);
															ps.setInt(1, admin.getId());

															int res = ps.executeUpdate();

															if(res == 1){
																System.out.println("Publisher deleted successfully \n");
																acont4 = false;
															}

														}

														else
															acont5 = false;
													} //loop back to menu ADMIN4
												}

												else
													acont4 = false;  //quit
											} //loop back to menu ADMIN4ab
										}

										else
											acont3 = false;
									} // loop back to menu ADMIN3ab
									break;

								case 3:
									//quit
									acont2 = false;
									break;

								default:
									break;
								}
							}//loop back to menu ADMIN2ab
							break;

						case 2:
							//manage publishers
							//acont2 = true;

							while(acont2){ //loop back to menu ADMIN3
								System.out.println("1) Add a publisher \n2) Select a publisher \n"
										+ "3) Quit to previous");
								y = input.nextInt();


								if(y == 1){
									//add a publisher						
									int res = admin.add("publisher", input);

									if(res == 1){
										String query = "insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) "
												+ " values(?, ?, ?)";

										ps = (PreparedStatement) conn.prepareStatement(query);
										ps.setString(1, admin.getName());
										ps.setString(2, admin.getAddr());
										ps.setString(3, admin.getPhone());

										ps.executeUpdate();

										System.out.println("Publisher added");
									}
								}

								else if(y == 2){
									//get the publisher's existing data
									boolean acont3 = true;

									while(acont3){ //loop back to menu ADMIN3
										System.out.println("Select a publisher");
										String query = "select publisherName, publisherAddress, publisherPhone from tbl_publisher";
										ps = (PreparedStatement) conn.prepareStatement(query);

										ResultSet rs = ps.executeQuery();
										int i =1; 
										while(rs.next()){
											System.out.println(i +") " + rs.getString("publisherName") + ", " + rs.getString("publisherAddress") + ", " + rs.getString("publisherPhone"));
											i++;
										}
										System.out.println(i + ") Quit to previous");

										int y2 = input.nextInt();

										query = "select * from tbl_publisher";

										ps = (PreparedStatement) conn.prepareStatement(query);

										rs = ps.executeQuery();

										admin.choosePublisher(rs, y2);


										if(y2 < i){
											boolean acont4 = true;

											while(acont4){ //loop back to menu ADMIN4
												System.out.println("1) Update publisher \n2) Delete publisher \n3) Quit to previous");
												y = input.nextInt();

												if(y==1){
													//update publisher
													int res = admin.update("publisher", input);

													if(res == 1){
														query = "update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? "
																+ "where publisherId =? ";
														ps = (PreparedStatement) conn.prepareStatement(query);
														ps.setString(1, admin.getName());
														ps.setString(2, admin.getAddr());
														ps.setString(3, admin.getPhone());
														ps.setInt(4, admin.getId());

														res = ps.executeUpdate();

														if(res == 1)
															System.out.println("Update successful");
													}

												}

												else if(y == 2){
													//delete publisher
													query = "delete from tbl_publisher where publisherId = ?";
													ps = (PreparedStatement) conn.prepareStatement(query);
													ps.setInt(1, admin.getId());

													int res = ps.executeUpdate();

													if(res == 1){
														System.out.println("Publisher deleted successfully \n");
														acont4 = false;
													}

												}

												else
													acont4 = false;
											} //loop back to menu ADMIN4
										}
										else
											acont3 = false;
									}


								}
								else
									acont2 = false;
							} //loop back to menu ADMIN3
							break;


						case 3:
							//manage library branches
							while(acont2){ //loop back to menu ADMIN2lb

								System.out.println("1) Add a library branch \n2) Select a library branch \n"
										+ "3) Quit to previous");
								y = input.nextInt();

								if(y ==1){
									//add a library branch
									int res = admin.add2(input);

									if(res == 1){
										String query = "insert into tbl_library_branch (branchName, branchAddress) "
												+ " values(?, ?)";


										ps = (PreparedStatement) conn.prepareStatement(query);

										ps.setString(1, admin.getName());
										ps.setString(2, admin.getAddr());

										ps.executeUpdate();

										System.out.println("Library branch successfully added\n");
									}
								}
								else if(y == 2){
									//select a branch
									boolean acont3 = true;

									while(acont3){ //loop back to menu ADMIN3lb
										System.out.println("Select a library branch");
										String query = "select branchName, branchAddress from tbl_library_branch";
										ps = (PreparedStatement) conn.prepareStatement(query);

										ResultSet rs = ps.executeQuery();

										int i = comm.returnLocations(rs);

										int y2 = input.nextInt();

										query = "select * from tbl_library_branch";
										ps = (PreparedStatement) conn.prepareStatement(query);
										rs = ps.executeQuery();

										admin.chooseLibrary(rs, y2);

										if(y2 < i){
											boolean acont4 = true;

											while(acont4){ //loop back to menu ADMIN4lb
												System.out.println("1) Update library branch \n2) Delete library branch \n3) Quit to previous");
												y = input.nextInt();

												if(y==1){
													//update library branch
													int res = admin.updateLB(input);

													if(res == 1){
														query = "update tbl_library_branch set branchName = ?, branchAddress = ?"
																+ "where branchId = ? ";
														ps = (PreparedStatement) conn.prepareStatement(query);
														ps.setString(1, admin.getName());
														ps.setString(2, admin.getAddr());
														ps.setInt(3, admin.getId());

														res = ps.executeUpdate();

														if(res == 1)
															System.out.println("Update successful");
													}									

												}
												else if(y == 2){
													//delete library branch
													query = "delete from tbl_library_branch where branchId = ?";
													ps = (PreparedStatement) conn.prepareStatement(query);
													ps.setInt(1, admin.getId());

													int res = ps.executeUpdate();

													if(res == 1){
														System.out.println("Library branch deleted successfully \n");
														acont4 = false;
													}

												}
												else
													acont4 = false;
											} //loop back to menu ADMIN4b
										}
										else
											acont3 = false;
									} //loop back to ADMIN3b



								}

								else
									acont2 = false;
							} //loop back to menu ADMIN2lb
							break;


						case 4:
							//manage borrowers

							while(acont2){ //loop back to menu ADMIN2b

								System.out.println("1) Add a borrower \n2) Select a borrower \n"
										+ "3) Quit to previous");
								y = input.nextInt();

								if(y == 1){
									//add a borrower													
									int res = admin.add("borrower", input);

									if(res == 1){
										String query = "insert into tbl_borrower (name, address, phone) "
												+ " values(?, ?, ?)";


										ps = (PreparedStatement) conn.prepareStatement(query);

										ps.setString(1, admin.getName());
										ps.setString(2, admin.getAddr());
										ps.setString(3, admin.getPhone());

										ps.executeUpdate();

										System.out.println("Update complete");
									}
								}

								else if(y == 2){
									//get the borrower's existing data
									boolean acont3 = true;

									while(acont3){ //loop back to menu ADMIN3b

										System.out.println("Select a borrower");
										String query = "select name, address, phone from tbl_borrower";
										ps = (PreparedStatement) conn.prepareStatement(query);

										ResultSet rs = ps.executeQuery();
										int i =1; 

										//print out list of borrowers
										while(rs.next()){
											System.out.println(i +") " + rs.getString("name") + ", " + rs.getString("address") + ", " + rs.getString("phone"));
											i++;
										}
										System.out.println(i + ") Quit to previous");

										int y2 = input.nextInt();

										query = "select * from tbl_borrower";
										ps = (PreparedStatement) conn.prepareStatement(query);
										rs = ps.executeQuery();

										admin.chooseBorrower(rs, y2);


										if(y2 < i){
											boolean acont4 = true;

											while(acont4){ //loop back to menu ADMIN4b
												System.out.println("1) Update borrower \n2) Delete borrower \n3) Quit to previous");
												y = input.nextInt();

												if(y==1){
													//update borrower
													int res = admin.update("borrower", input);

													if(res == 1){
														query = "update tbl_borrower set name = ?, address = ?, phone = ? "
																+ "where cardNo =? ";
														ps = (PreparedStatement) conn.prepareStatement(query);
														ps.setString(1, admin.getName());
														ps.setString(2, admin.getAddr());
														ps.setString(3, admin.getPhone());
														ps.setInt(4, admin.getId());

														res = ps.executeUpdate();

														if(res == 1)
															System.out.println("Update successful");
													}

												}

												else if(y == 2){
													//delete publisher
													query = "delete from tbl_borrower where cardNo = ?";
													ps = (PreparedStatement) conn.prepareStatement(query);
													ps.setInt(1, admin.getId());

													int res = ps.executeUpdate();

													if(res == 1){
														System.out.println("Borrower deleted successfully \n");
														acont4 = false;
													}

												}

												else
													acont4 = false;
											} //loop back to menu ADMIN4b
										}
										else
											acont3 = false;
									} //loop back to ADMIN3b
								}
								else
									acont2 = false;
							} // loop back to menu ADMIN2b
							break;


						case 5:
							//over-ride due date 
							break;


						case 6:
							acont = false;
							break;

						default:
							break;
						}

					}// loop back to menu ADMIN1
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

							String q = "select cardNo from tbl_borrower";

							ps = (PreparedStatement) conn.prepareStatement(q);

							ResultSet rs = ps.executeQuery();

							//check that the user gives a valid card number
							check = borr.checkUser(rs, x);

							if(check == false)
								System.out.println("Invalid card number.");
						}

						System.out.println("1) Check out book \n2) Return a book \n3) Quit to previous");

						x = input.nextInt();
						boolean bcont2 = true;

						if(x == 1){
							//Check out a book

							while(bcont2){ //loop back to menu BORR2a 

								System.out.println("Pick the branch you want to check out from");
								ps = (PreparedStatement) conn.prepareStatement("select * from tbl_library_branch");

								ResultSet rs = ps.executeQuery();
								borr.max = comm.returnLocations(rs);
								x = input.nextInt();

								//choose a library

								String q = "select branchId from tbl_library_branch";
								ps = (PreparedStatement) conn.prepareStatement(q);

								rs = ps.executeQuery();

								borr.chooseLibrary(x, rs);

								if(x < borr.max){
									boolean bcont3 = true;

									while(bcont3){ //loop back to menu BORR3
										//choose book you want to check out
										System.out.println("Pick the book you want to check out");

										q = "select title from tbl_book_copies "
												+ "join tbl_book on tbl_book.bookId= tbl_book_copies.bookId "
												+ "where tbl_book_copies.branchiD = ?" ;

										ps = (PreparedStatement) conn.prepareStatement(q);
										ps.setInt(1, borr.getId());

										rs = ps.executeQuery();

										borr.max = comm.printBooks(rs);
										x = input.nextInt();  //book user chose

										if(x < borr.max && x>0){

											String query = "select bookid from tbl_book_copies where branchId = ? and noOfCopies>0";
											ps = (PreparedStatement) conn.prepareStatement(query);
											ps.setInt(1, borr.getId());
											rs = ps.executeQuery();

											borr.getBookId(x, rs); 		//get the bookid


											//check out the book
											String q2 = borr.returnQuery();

											ps= (PreparedStatement) conn.prepareStatement(q2);

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
							}//loop back to menu BORR2a
						} 

						else if(x == 2){							
							//return a book
							while(bcont2){ //loop back to menu BORR2b

								System.out.println("Pick a book you want to return \nLibrary branch name, book title, date checked out, due date");

								String q = "select tbl_library_branch.branchName, tbl_book.title, tbl_book_loans.dateOut, tbl_book_loans.dueDate "+
										"from tbl_book_loans " +
										"join tbl_library_branch on tbl_book_loans.branchid = tbl_library_branch.branchid " +
										"join tbl_book on tbl_book.bookId = tbl_book_loans.bookId " +
										"where tbl_book_loans.cardNo = ?";

								ps = (PreparedStatement) conn.prepareStatement(q);
								ps.setInt(1, borr.getCardNo());

								ResultSet rs = ps.executeQuery();
								int i = 1;
								//print out the books the user has
								while(rs.next()){

									System.out.println(i +") " + rs.getString("branchName") + ", " + rs.getString("title") +
											", " + rs.getString("dateOut") + ", " + rs.getString("dueDate"));
									i++;

								}

								System.out.println(i + ") Quit to previous");	

								x = input.nextInt();

								if(x < i){
									//delete the book from tbl_book_loans to return
									q = "select branchId, bookId from tbl_book_loans " +
											"where tbl_book_loans.cardNo = ?";

									ps = (PreparedStatement) conn.prepareStatement(q);
									ps.setInt(1, borr.getCardNo());

									rs = ps.executeQuery();

									borr.selectBook(rs, x);

									q = "delete from tbl_book_loans where branchId = ? and bookId = ? and cardNo = ?";

									ps = (PreparedStatement) conn.prepareStatement(q);
									ps.setInt(1, borr.getId());
									ps.setInt(2, borr.getbookId());
									ps.setInt(3, borr.getCardNo());

									int res = ps.executeUpdate();

									if(res == 1)
										System.out.println("Book returned successfully");
									continue;
								}

								else				
									bcont2 = false;

							} //loop back to menu BORR2B

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
