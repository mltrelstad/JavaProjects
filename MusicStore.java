import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MusicStore {

	static Logger logger = Logger.getLogger(MusicStore.class.getName());
	
	public static void main(String[] args) {
		
        logger.setLevel(Level.INFO);
        
		try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("/Users/mltrelstad/eclipse-workspace/MusicStore/src/logging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.addHandler(new ConsoleHandler());

        try {
            FileHandler fileHandler = new FileHandler("/Users/mltrelstad/eclipse-workspace/MusicStore/Logs/logfile.log", 2000, 5);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		Scanner scanner = new Scanner (System.in);
		
		boolean isAuthenticated = false;
		
		do {
			System.out.println("Enter username:");
	        String username = scanner.nextLine();
	
	        System.out.println("Enter password:");
	        String password = scanner.nextLine();
	        
	        if(authenticate(username, password)) {
	        	System.out.println("Login successful!");
	        	isAuthenticated = true;
	        	break;
	        }else {
	        	System.out.println("Login failed. Try again.");
	        }
		}while(!isAuthenticated);
		

		System.out.println("___________________");
		System.out.println("HOME STATE RECORDS");
		System.out.println("Welcome to the store manager!\n");
		
		String input = "";
		char sel = 'q';
		
		do {			
			System.out.println("\nEnter 1 to View Products");
			System.out.println("Enter 2 to Search Products");
			System.out.println("Enter 3 to Add Product(s)");
			System.out.println("Enter 4 to Update Product(s)");
			System.out.println("Enter 5 to Delete Product(s)");
			System.out.println("Enter 6 to View Sales Report");
			System.out.println("Enter q to Exit\n");
			System.out.println("What would you like to do: ");
			
			input = scanner.next();
			
			if (input.length()>1) {
				System.out.println("Input not known, please try again.");
			}else {
				sel = input.charAt(0);
		
/*View*/			if (sel == '1'){			
					Products p = new Products();
					p.getAllProducts();
					
/*Search*/			}else if (sel == '2') {
					System.out.println("Enter a keyword to search: ");
					String keyword = scanner.next();
					Products p = new Products();
					p.searchProducts(keyword);
					
/*Add*/				}else if (sel == '3') {		//Add products
					System.out.println("Enter the product name:");
				    String productName = scanner.next();
				    
				    System.out.println("Enter the product cost:");
				    while (!scanner.hasNextDouble()) {
				        System.out.println("Invalid input. Try again:");
				        scanner.next();
				    }
				    double productCost = scanner.nextDouble();
				    
				    Products p = new Products();
				    p.addProduct(productName, productCost);
				    
/*Update*/			}else if (sel == '4') {
					scanner.nextLine();
				    System.out.println("Enter the product name you want to update:");
				    String productNameToUpdate = scanner.nextLine().trim();

				    System.out.println("Enter the new name (or press Enter to keep the existing name):");
				    String newName = scanner.nextLine().trim();

				    System.out.println("Enter the new cost (or press Enter to keep the existing cost):");
				    String costInput = scanner.nextLine().trim();
				    
				    double newCost = costInput.isEmpty() ? -1.0 : Double.parseDouble(costInput);

				    Products p = new Products();
				    p.updateProduct(productNameToUpdate, newName, newCost);
				    
/*Delete*/			}else if (sel == '5') {
					scanner.nextLine();
				    System.out.println("Enter the product name you want to delete:");
				    String productNameToDelete = scanner.nextLine().trim();

				    Products p = new Products();
				    p.deleteProduct(productNameToDelete);
				    
/*ViewReport*/		}else if (sel == '6') {
					Products p = new Products();
				    double totalSales = p.getSales();
				    
/*Quit*/			}else if (sel == 'q') {
					System.out.println("\n\n*************************");
					System.out.println("Exiting program, goodbye.");
					System.out.println("*************************");
					break;
				}
				else {
					System.out.println("Input not known, please try again.");
				}
			}
			
			logger.info("User action: " + input + ", Timestamp: " + System.currentTimeMillis());
			
			
		}while(sel != 'q');
		scanner.close();
	}

	private static boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
