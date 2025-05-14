# Java Projects for CodeAlpha Internship Program

Welcome to my Java projects for the CodeAlpha internship program, Summer 2025. This repository contains several applications that I worked on to improve my Java skills.

## Projects
  
### 1) Student Grade Tracker

#### Main Class: `StudentGradeTracker`

**Attributes:**
- `Scanner sc`: A Scanner object for user input.
- `double[] gradeArray`: An array to store the grades of students.
- `int size`: The number of students.

**Methods:**
- `public static void main(String[] args)`: The main method that executes the program.
  - Prompts the user to enter the number of students.
  - Collects grades for each student and stores them in `gradeArray`.
  - Calculates and displays the average, highest, and lowest grades.
    
### 2) Stock Trading Platform

#### Class: `User`

**Attributes:**
- `private String username`: The username of the user.
- `private double balance`: The current balance of the user.
- `private Map<String, Integer> portfolio`: A map to store the number of shares owned for each stock (Ticker Symbol, No of Shares owned).

**Methods:**
- `public User(String username, double balance)`: Constructor to initialize the user with a username and balance.
- `public String getUsername()`: Returns the username of the user.
- `public double getBalance()`: Returns the current balance of the user.
- `public Map<String, Integer> getPortfolio()`: Returns the user's portfolio.
- `public void deposit(double amount)`: Deposits a specified amount into the user's balance.
- `public boolean withdraw(double amount)`: Withdraws a specified amount from the user's balance if sufficient funds are available.
- `public void buyStock(String tickerSymbol, int quantity)`: Buys a specified quantity of shares for a given stock.
- `public void sellStock(String tickerSymbol, int quantity)`: Sells a specified quantity of shares for a given stock.

#### Class: `Stock`

**Attributes:**
- `private String tickerSymbol`: The ticker symbol of the stock.
- `private String companyName`: The name of the company.
- `private double price`: The current price of the stock.

**Methods:**
- `public Stock(String tickerSymbol, String companyName, double price)`: Constructor to initialize the stock with its ticker symbol, company name, and price.
- `public String getTickerSymbol()`: Returns the ticker symbol of the stock.
- `public String getCompanyName()`: Returns the name of the company.
- `public double getPrice()`: Returns the current price of the stock.

#### Main Class: `StockTradingPlatform`

**Attributes:**
- `public static List<Stock> availableStocks`: A list of available stocks in the market.
- `public static List<User> users`: A list of registered users.
- `public static User currentUser `: The currently logged-in user.
- `public static Scanner sc`: A Scanner object for user input.

**Methods:**
- `public static void displayDashboard()`: Displays the main dashboard for the logged-in user.
- `public static Stock findStock(String tickerSymbol)`: Finds and returns a stock by its ticker symbol.
- `public static void viewPortfolio()`: Displays the user's portfolio.
- `public static void viewMarketData()`: Displays the available stocks and their prices.
- `public static void buyShares()`: Allows the user to buy shares of a selected stock.
- `public static void sellShares()`: Allows the user to sell shares of a selected stock.
- `public static void main(String[] args)`: The main method that executes the program and handles user interactions.

### 3) Hotel Reservation System

#### Class: `Room`

**Attributes:**
- `String roomNumber`: The unique identifier for the room.
- `String roomType`: The type of the room (e.g., Standard, Deluxe, Suite).
- `double pricePerNight`: The price per night for the room.
- `List<Reservation> bookings`: A list of reservations for the room.

**Methods:**
- `public Room(String roomNumber, String roomType, double pricePerNight)`: Constructor to initialize the room.
- `public String getRoomNumber()`: Returns the room number.
- `public String getRoomType()`: Returns the room type.
- `public double getPricePerNight()`: Returns the price per night.
- `public boolean isAvailable(LocalDate checkInDate, LocalDate checkOutDate)`: Checks if the room is available for the given dates.
- `public void addBooking(Reservation reservation)`: Adds a reservation to the room's bookings.

#### Class: `User`

**Attributes:**
- `String username`: The username of the user.
- `String name`: The full name of the user.

**Methods:**
- `public User(String username, String name)`: Constructor to initialize the user.
- `public String getUser Name()`: Returns the username.
- `public String getName()`: Returns the user's full name.

#### Class: `Reservation`

**Attributes:**
- `String reservationId`: The unique identifier for the reservation.
- `String paymentMethod`: The payment method used for the reservation.
- `LocalDate checkInDate`: The check-in date for the reservation.
- `LocalDate checkOutDate`: The check-out date for the reservation.
- `Room room`: The room associated with the reservation.
- `User  user`: The user who made the reservation.

**Methods:**
- `public Reservation(String reservationId, String paymentMethod, LocalDate checkInDate, LocalDate checkOutDate, Room room, User user)`: Constructor to initialize the reservation.
- `public String getReservationId()`: Returns the reservation ID.
- `public String getPaymentMethod()`: Returns the payment method.
- `public LocalDate getCheckInDate()`: Returns the check-in date.
- `public LocalDate getCheckOutDate()`: Returns the check-out date.
- `public Room getRoom()`: Returns the room associated with the reservation.
- `public User getUser ()`: Returns the user who made the reservation.

#### Class: `Hotel`

**Attributes:**
- `List<Room> rooms`: A list of rooms in the hotel.
- `List<Reservation> reservations`: A list of all reservations made in the hotel.

**Methods:**
- `public void addRoom(Room room)`: Adds a room to the hotel.
- `public List<Room> getAllRooms()`: Returns all rooms in the hotel.
- `public List<Room> findRoomsByType(String roomType)`: Finds and returns rooms by type.
- `public List<Room> searchAvailableRoomsByType(String roomType, LocalDate checkInDate, LocalDate checkOutDate)`: Searches for available rooms by type and dates.
- `public String makeReservation(User user, Room room, LocalDate checkInDate, LocalDate checkOutDate, String paymentMethod)`: Makes a reservation for a user.
- `public Reservation findReservation(String reservationId)`: Finds a reservation by its ID.
- `public double getTotalCost(String reservationId)`: Calculates the total cost of a reservation.

#### Class: `HotelReservationSystem`

**Attributes:**
- `static Hotel hotel`: The hotel instance.
- `static Scanner sc`: A Scanner object for user input.
- `static User currentUser `: The currently logged-in user.

**Methods:**
- `static void initializeHotel()`: Initializes the hotel with rooms.
- `static void viewAvailableRooms()`: Displays available rooms based on user input.
- `static void makeReservation()`: Allows the user to make a reservation.
- `public static void main(String[] args)`: The main method that executes the program and handles user interactions.
