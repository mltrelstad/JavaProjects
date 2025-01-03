import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Products {

	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public void getAllProducts() {					//Get all function
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "all4Wolves!");
			st = conn.createStatement();
			rs = st.executeQuery("select * from product");
			
			System.out.println("________________");
			System.out.println("Viewing catalog:\n\nTitle:");
			
			while(rs.next()) {
				String name = rs.getString("name");
				double cost = rs.getDouble("cost");
				System.out.println(name + " - - $" + cost);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchProducts(String keyword) {	//Search function
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "all4Wolves!");
			st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM product WHERE name LIKE '%" + keyword + "%'");
            
            System.out.println("\nSearch results: \n");
            
            boolean resultFound = false;

            while (rs.next()) {
                String name = rs.getString("name");
                double cost = rs.getDouble("cost");
                System.out.println("Name: " + name + " - - $" + cost);
                resultFound = true;
            }
            if (!resultFound) {
                System.out.println("No matching products found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	try {
        		if(rs!= null) rs.close();
        		if(ps != null) ps.close();
        		if(conn != null) conn.close();
        	}catch(SQLException ex) {
        		ex.printStackTrace();
        	}
        }
    }
	
	public void addProduct(String name, double cost) {
	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "all4Wolves!");
	        ps = conn.prepareStatement("INSERT INTO product (name, cost) VALUES (?, ?)");
	        ps.setString(1, name);
	        ps.setDouble(2, cost);
	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Product added successfully.");
	            System.out.println("Name: " + name + " - - $" + cost);
	        } else {
	            System.out.println("Failed to add product.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public void updateProduct(String name, String newName, double newCost) {
	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "all4Wolves!");
	        ps = conn.prepareStatement("UPDATE product SET name = ?, cost = ? WHERE name = ?");
	        ps.setString(1, newName);
	        ps.setDouble(2, newCost);
	        ps.setString(3, name);
	        
	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Product updated successfully.");
	        } else {
	            System.out.println("Failed to update product. Product with name " + name + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public void deleteProduct(String name) {					//Delete function
		try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "all4Wolves!");
	        ps = conn.prepareStatement("DELETE FROM product WHERE name = ?");
	        ps.setString(1, name);

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Product deleted successfully.");
	        } else {
	            System.out.println("Failed to delete product. Product with name " + name + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public double getSales() {						//Get Sales function
		double totalSales = 0.0;
	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "all4Wolves!");
	        st = conn.createStatement();
	        rs = st.executeQuery("SELECT cost FROM product");

	        while (rs.next()) {
	            double cost = rs.getDouble("cost");
	            totalSales += cost;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    System.out.println("Total Sales: $" + totalSales);
        return totalSales;
	}

	

}
