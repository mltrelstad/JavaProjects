public class OSInfo {
    public static void main(String[] args) {
    	
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));

        System.out.println("OS Name: " + System.getProperty("os.name"));

        System.out.println("OS Version: " + System.getProperty("os.version"));

        System.out.println("\nJava Version: " + System.getProperty("java.version"));

        long startTime = System.nanoTime();

        String aRepeating = "";
        for (int i = 0; i < 100000; i++) {
            aRepeating += "a";
        	}

        long endTime = System.nanoTime();

        long executionTime = (endTime - startTime) / 1000000;

        System.out.println("\nTime: " + executionTime + " milliseconds");
        
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}