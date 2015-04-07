package com.gcit.training.client;

import java.util.List;
import java.util.Scanner;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.Publisher;
import com.gcit.training.library.service.AdministratorService;
import com.gcit.training.library.Borrower;

public class AdministratorClient {
	
	private List<Author> aList = null;
	private List<Books> bList = null;
	private List<Publisher> pList = null;
	private List<Borrower> borrList = null;
	private List<LibraryBranch> lbList = null;
	private int max = 0;
	private String name = "", address = "", phone = "";

	public void begin(Scanner in) throws Exception{

		AdministratorService service = new AdministratorService();
		int choice = 0;
		boolean temp = true;

		while(temp){
			System.out.println("1) Manage Books \n2) Manage Authors \n3) Manage Publishers \n4) Manage Borrowers \n"
					+ "5) Over-ride Due Date for a Book Loan \n6) Quit to previous");
			choice = in.nextInt();
			boolean temp2 = true;

			switch (choice) {
			case 1:
				while(temp2){
					//Manage books
					System.out.println("BOOKS \n1) Add new book \n2) Update or delete existing book \n3) Quit to previous");
					choice = in.nextInt();

					switch (choice) {
					case 1:
						//Add 
						Books book = new Books();
						System.out.print("You're about to enter information for a new book. Enter 'quit' at any prompt to exit.\n"
								+ "Enter a new title: ");
						String info = in.nextLine();
						info = in.nextLine();
						if(!info.equals("quit")){
							book.setTitle(info);
						}
						else 
							break;

						break;

					case 2:
						//update or delete existing book
						boolean temp3 = true;
						while(temp3){
							System.out.println("Choose a book");
							Books book2 = new Books();
							bList = (List<Books>) service.displayInventroy(book2);
							max = bList.size() + 1;
							choice = in.nextInt();
							
							if(choice < max){
								book2 = bList.get(choice -1);
								
								System.out.println("1) Update book \n2) Delete book \n3) Quit to previous");
								choice = in.nextInt();
								
								if(choice == 1){
									//update
								}
								else if(choice == 2){
									//delete
								}
								else{
									//quit
								}
								
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
				}
				break;

			case 2:
				//Manage authors
				while(temp2){					
					System.out.println("AUTHORS \n1) Add new author \n2) Update or delete existing author \n3) Quit to previous");
					choice = in.nextInt();

					switch (choice) {
					case 1:
						//Add 
						Author author = new Author();

						System.out.print("You're about to enter information for a new author. Enter 'quit' at prompt to exit.\n"
								+ "Enter a new author name: ");
						String info = in.nextLine();
						info = in.nextLine();
						
						if(!info.equals("quit")){
							author.setName(info);
						}
						else 
							break;
						
						service.createInventory(author);
						System.out.println("Author added successfully \n");

						break;

					case 2:
						//update or delete existing author
						System.out.println("Choose an author");
						Author author2 = new Author();
						aList = (List<Author>) service.displayInventroy(author2);
						choice = in.nextInt();
						max = aList.size() + 1;
						
						if(choice < max){
							author2 = aList.get(choice - 1);
							
							System.out.println("1) Update author \n2) Delete author \n3) Quit to previous");
							choice = in.nextInt();
							
							if(choice == 1){
								//update
								System.out.print("You're about to update an author with id: " + author2.getAuthorid() 
										+ " and name " + author2.getName() + ". Enter 'quit' at prompt to cancel. \n"
												+ "Enter new author name: ");
								String aname = in.nextLine();
								aname = in.nextLine();
								
								if(!aname.equals("quit")){
									author2.setName(aname);
									service.UpdateInventory(author2);
									
									System.out.println("Author updated successsfully \n");
								
								}
							}
							else if(choice == 2){
								service.deleteInventory(author2);
								System.out.println("Author deleted successfully \n");
							}
							else{
								//quit
							}								
						}
						
						break;

					case 3:
						//quit
						temp2 = false;
						break;

					default:
						break;
					}
				}
				break;

			case 3:
				//Manage publishers
				while(temp2){
					//Manage books
					System.out.println("PUBLISHERS \n1) Add new publisher \n2) Update or delete existing publisher \n3) Quit to previous");
					choice = in.nextInt();

					switch (choice) {
					case 1:
						//Add 
						Publisher publisher = new Publisher();
						int result = newInfoInput("publisher", in);

						if(result == 1){
							publisher.setPname(name);
							publisher.setPaddr(address);
							publisher.setPphone(phone);

							service.createInventory(publisher);
							System.out.println("Publisher added successfully \n");

						}

						break;

					case 2:
						//update or delete existing publisher

						boolean temp3 = true;
						while(temp3){
							System.out.println("Choose a publisher");
							Publisher pub = new Publisher();
							pList = (List<Publisher>) service.displayInventroy(pub);							 
							choice = in.nextInt();
							max = pList.size() + 1;

							if(choice < max){
								pub = pList.get(choice - 1);

								System.out.println("1) Update publisher \n2) Delete publisher \n3) Quit to previous");
								choice = in.nextInt();

								if(choice == 1){
									//update
									name = pub.getPname();
									address = pub.getPaddr();
									phone = pub.getPphone();

									int result2 = updateInfoInput("publisher", in, pub.getPid());
									if(result2 == 1){
										pub.setPname(name);
										pub.setPaddr(address);
										pub.setPphone(phone);

										service.UpdateInventory(pub);
										System.out.println("Publisher updated successfully \n");
									}
								}
								else if(choice == 2){
									//delete
									service.deleteInventory(pub);
									System.out.println("Publisher successfully deleted");
								}
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
				}
				break;

			case 4:
				//Manage borrowers
				while(temp2){
					//Manage publishers
					System.out.println("BORROWERS \n1) Add new borrower \n2) Update or delete existing borrower \n3) Quit to previous");
					choice = in.nextInt();

					switch (choice) {
					case 1:
						//Add 
						Borrower borrower = new Borrower();
						int result = newInfoInput("borrower", in);
						if(result == 1){
							borrower.setName(name);
							borrower.setAddress(address);
							borrower.setPhone(phone);

							service.createInventory(borrower);
							System.out.println("Borrower successfully added\n");
						}

						break;

					case 2:
						//update or delete existing borrower
						boolean temp3 = true;
						while(temp3){
							System.out.println("Choose a borrower");
							Borrower borr = null;
							borrList = (List<Borrower>) service.displayInventroy(borr);
							choice = in.nextInt();
							max = borrList.size() + 1;

							if(choice < max){
								//borr = (Borrower) service.chooseInventroy("borrower", choice);
								borr = borrList.get(choice - 1);
								System.out.println("1) Update borrower \n2) Delete borrower \n3) Quit to previous");
								choice = in.nextInt();

								if(choice == 1){
									//update
									name = borr.getName();
									address = borr.getAddress();
									phone = borr.getPhone();

									int result2 = updateInfoInput("borrower", in, borr.getCardno());
									if(result2 == 1){
										borr.setName(name);
										borr.setAddress(address);
										borr.setPhone(phone);

										service.UpdateInventory(borr);
										System.out.println("Borrower updated successfully \n");
									}

								}
								else if(choice == 2){
									//delete
									service.deleteInventory(borr);
									System.out.println("Borrower successfully deleted");
								}
							}

							else 
								temp3 = false;
						}//loop back to menu

						break;

					case 3:
						//quit
						temp2 = false;
						break;

					default:
						break;
					}
				}
				break;

			case 5:
				//over-ride due date
				break;

			case 6:
				temp = false;
				break;


			default:
				temp = false;
				break;
			}

		}//loop back to menu
	}

	private int newInfoInput(String type, Scanner in){

		System.out.print("You're about to enter information for a new " + type + ". Enter 'quit' at any prompt to exit.\n"
				+ "Enter a new " + type + " name: ");
		String info = in.nextLine();
		info = in.nextLine();
		if(!info.equals("quit")){
			name = info;
		}
		else 
			return 0;
		System.out.print("Enter a new " + type + " address: ");
		info = in.nextLine();
		if(!info.equals("quit")){
			address = info;
		}
		else 
			return 0;

		System.out.print("Enter a new " + type + " phone number: ");
		info = in.nextLine();
		if(!info.equals("quit")){
			phone = info;
		}
		else 
			return 0;

		return 1;
	}

	private int updateInfoInput(String type, Scanner in, int id){
		System.out.print("You're about to update information for "+ type + " with id: " + id +
				" and name: " + name +". Enter 'quit' at any prompt to cancel operation.\n"
				+ "Enter a new name or 'N/A' for no change: ");
		String info = in.nextLine();
		info = in.nextLine();

		if(info.equals("quit")){
			return 0;
		}
		else if(!info.equals("N/A")){
			name = info;
		}

		System.out.print("Enter a new address or 'N/A' for no change: ");
		info = in.nextLine();
		if(info.equals("quit")){
			return 0;
		}
		else if(!info.equals("N/A")){
			address = info;
		}

		System.out.print("Enter a new phone number or 'N/A' for no change: ");
		info = in.nextLine();
		if(info.equals("quit")){
			return 0;
		}
		else if(!info.equals("N/A")){
			phone =info;
		}
		return 1;
	}

}
