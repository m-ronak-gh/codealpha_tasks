import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private double balance;
    private Map<String, Integer> portfolio; //<Ticker Symbol, No of Shares owned>

    public User(String username, double balance){
        this.username = username;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }
    
    public String getUsername(){
        return username;
    }
    public double getBalance(){
        return balance;
    }
    public Map<String, Integer> getPortfolio(){
        return portfolio;
    }

    public void deposit(double amount){
        this.balance += amount;
    }
    public boolean withdraw(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
            return true;
        }
        else
            return false;
    }

    public void buyStock(String tickerSymbol, int quantity){
        portfolio.put(tickerSymbol, portfolio.getOrDefault(tickerSymbol,0) + quantity);
    }
    public void sellStock(String tickerSymbol, int quantity){
        if(portfolio.containsKey(tickerSymbol)){
            int currentShares = portfolio.get(tickerSymbol);
            if(currentShares >= quantity){
                portfolio.put(tickerSymbol, currentShares-quantity);
                if(portfolio.get(tickerSymbol) == 0){
                    portfolio.remove(tickerSymbol);
                }
            }
            else{
                System.out.println("You don't have enough "+tickerSymbol+" shares to sell :(");
            }
        }
        else{
            System.out.println("You don't own any "+tickerSymbol+" shares :(");
        }
    }
}

class Stock {
    private String tickerSymbol;
    private String companyName;
    private double price;

    public Stock(String tickerSymbol, String companyName, double price){
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String getTickerSymbol(){
        return tickerSymbol;
    }
    public String getCompanyName(){
        return companyName;
    }
    public double getPrice(){
        return price;
    }

}

public class StockTradingPlatform {
    public static List<Stock> availableStocks = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static User currentUser;
    public static Scanner sc = new Scanner(System.in);

    public static void displayDashboard() {
        System.out.println("\n--- Main Dashboard ---");
        System.out.println("Logged in as: " + currentUser.getUsername());
        System.out.println("Balance: Rs " + currentUser.getBalance());
        System.out.println("\nOptions:");
        System.out.println("1. View Portfolio");
        System.out.println("2. View Market Data");
        System.out.println("3. Buy Shares");
        System.out.println("4. Sell Shares");
        System.out.println("5. Exit");
    }

    public static Stock findStock(String tickerSymbol) {
        for (Stock stock : availableStocks) {
            if (stock.getTickerSymbol().equalsIgnoreCase(tickerSymbol)) {
                return stock;
            }
        }
        return null;
    }

    public static void viewPortfolio() {
        System.out.println("\n--- Your Portfolio ---");
        if (currentUser.getPortfolio().isEmpty()) {
            System.out.println("Your portfolio is empty.");
        } else {
            for (Map.Entry<String, Integer> entry : currentUser.getPortfolio().entrySet()) {
                String ticker = entry.getKey();
                int shares = entry.getValue();
                Stock stock = findStock(ticker);
                double value = shares * stock.getPrice();
                System.out.println(ticker + ": " + shares + " shares - Value: Rs " + value);
            }
        }
    }

    public static void viewMarketData() {
        System.out.println("\n--- Market Data ---");
        if (availableStocks.isEmpty()) {
            System.out.println("No stocks available in the market.");
        } else {
            for (Stock stock : availableStocks) {
                System.out.println(stock.getTickerSymbol() + " (" + stock.getCompanyName() + "): Rs " + stock.getPrice());
            }
        }
    }

    public static void buyShares() {
        System.out.println("\n--- Buy Shares ---");
        if (availableStocks.isEmpty()) {
            System.out.println("No stocks available to buy.");
            return;
        }

        System.out.println("Available Stocks:");
        for (int i = 0; i < availableStocks.size(); i++) {
            System.out.println((i + 1) + ". " + availableStocks.get(i).getTickerSymbol() + " (" + availableStocks.get(i).getCompanyName() + ") - Rs " + availableStocks.get(i).getPrice());
        }

        System.out.print("Enter the number of the stock you want to buy (or 0 to go back): ");
        int stockNumber = sc.nextInt();

        if (stockNumber > 0 && stockNumber <= availableStocks.size()) {
            Stock selectedStock = availableStocks.get(stockNumber - 1);
            System.out.print("Enter the number of shares to buy: ");
            int quantity = sc.nextInt();

            if (quantity > 0) {
                double totalCost = quantity * selectedStock.getPrice();
                if (currentUser.getBalance() >= totalCost) {
                    currentUser.withdraw(totalCost);
                    currentUser.buyStock(selectedStock.getTickerSymbol(), quantity);
                    System.out.println("Successfully bought " + quantity + " shares of " + selectedStock.getTickerSymbol() + " for Rs " +totalCost);
                } else {
                    System.out.println("Insufficient balance to complete the purchase.");
                }
            } else {
                System.out.println("Invalid quantity.");
            }
        } else if (stockNumber != 0) {
            System.out.println("Invalid stock number.");
        }
    }

    public static void sellShares() {
        System.out.println("\n--- Sell Shares ---");
        if (currentUser.getPortfolio().isEmpty()) {
            System.out.println("Your portfolio is empty. Nothing to sell.");
            return;
        }

        System.out.println("Your Portfolio:");
        for (Map.Entry<String, Integer> entry : currentUser.getPortfolio().entrySet()) {
            String ticker = entry.getKey();
            int shares = entry.getValue();
            System.out.println(ticker + ": " + shares + " shares");
        }

        System.out.print("Enter the ticker symbol of the stock you want to sell (or type 'back' to go back): ");
        String tickerToSell = sc.nextLine().toUpperCase();

        if (!tickerToSell.equalsIgnoreCase("back")) {
            if (currentUser.getPortfolio().containsKey(tickerToSell)) {
                System.out.print("Enter the number of shares to sell: ");
                int quantityToSell = sc.nextInt();
                int ownedShares = currentUser.getPortfolio().get(tickerToSell);
                if (quantityToSell > 0 && quantityToSell <= ownedShares) {
                    Stock stockToSell = findStock(tickerToSell);
                    double amount = quantityToSell * stockToSell.getPrice();
                    currentUser.deposit(amount);
                    currentUser.sellStock(tickerToSell, quantityToSell);
                    System.out.println("Successfully sold " +quantityToSell+ " shares of " +tickerToSell+ " for Rs " +amount+ ".");
                } else {
                    System.out.println("Invalid quantity.");
                }
            } else {
                System.out.println("You don't own any shares of "+tickerToSell+".");
            }
        }
    }

    public static void main(String[] args) {
        //sample stocks
        availableStocks.add(new Stock("AAPL", "Apple", 211.19));
        availableStocks.add(new Stock("GOOGL", "Google", 159.05));
        availableStocks.add(new Stock("MSFT", "Meta", 448.29));
        availableStocks.add(new Stock("AMZN", "Amazon", 213.86));

        //sample user
        User user1 = new User("Trader-abc", 10000.00);
        users.add(user1);
        currentUser = user1;

        System.out.println("Welcome to Stock Trading Platform!");
        boolean run = true;
        while(run){
            displayDashboard();
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    viewPortfolio();
                    break;
                case "2":
                    viewMarketData();
                    break;
                case "3":
                    buyShares();
                    break;
                case "4":
                    sellShares();
                    break;
                case "5":
                    System.out.println("Exiting...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        System.out.println("Thank you for using the Stock Trading Platform!");
        sc.close();
    }
}