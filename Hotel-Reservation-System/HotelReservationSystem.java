import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    String roomNumber;
    String roomType;
    double pricePerNight;
    List<Reservation> bookings = new ArrayList<>();

    public Room(String roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable(LocalDate checkInDate, LocalDate checkOutDate) {
        for (Reservation res : bookings) {
            if (!(checkOutDate.isBefore(res.getCheckInDate()) || checkInDate.isAfter(res.getCheckOutDate()))) {
                return false;
            }
        }
        return true;
    }

    public void addBooking(Reservation reservation) {
        bookings.add(reservation);
    }
}

class User {
    String username;
    String name;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    String reservationId;
    String paymentMethod;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Room room;
    User user;

    public Reservation(String reservationId, String paymentMethod, LocalDate checkInDate, LocalDate checkOutDate, Room room, User user) {
        this.reservationId = reservationId;
        this.paymentMethod = paymentMethod;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.user = user;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> findRoomsByType(String roomType) {
        List<Room> filtered = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType)) {
                filtered.add(room);
            }
        }
        return filtered;
    }

    public List<Room> searchAvailableRoomsByType(String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> available = new ArrayList<>();
        for (Room room : findRoomsByType(roomType)) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                available.add(room);
            }
        }
        return available;
    }

    public String makeReservation(User user, Room room, LocalDate checkInDate, LocalDate checkOutDate, String paymentMethod) {
        if (room.isAvailable(checkInDate, checkOutDate)) {
            String reservationId = "RID-" + user.getUserName() + "-" + room.getRoomNumber();
            Reservation reservation = new Reservation(reservationId, paymentMethod, checkInDate, checkOutDate, room, user);
            room.addBooking(reservation);
            reservations.add(reservation);
            return reservationId;
        }
        return null;
    }

    public Reservation findReservation(String reservationId){
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }

    public double getTotalCost(String reservationId) {
        Reservation res = findReservation(reservationId);
        Room room = res.getRoom();
        long days = ChronoUnit.DAYS.between(res.checkInDate, res.checkOutDate);
        return days * room.getPricePerNight();
    }
}

public class HotelReservationSystem {
    static Hotel hotel = new Hotel();
    static Scanner sc = new Scanner(System.in);
    static User currentUser;

    static void initializeHotel() {
        hotel.addRoom(new Room("101", "Standard", 1000));
        hotel.addRoom(new Room("102", "Standard", 1000));
        hotel.addRoom(new Room("103", "Deluxe", 1500));
        hotel.addRoom(new Room("104", "Deluxe", 1500));
        hotel.addRoom(new Room("105", "Suite", 3000));

        hotel.addRoom(new Room("201", "Standard", 1000));
        hotel.addRoom(new Room("202", "Standard", 1000));
        hotel.addRoom(new Room("203", "Deluxe", 1500));
        hotel.addRoom(new Room("204", "Deluxe", 1500));
        hotel.addRoom(new Room("205", "Suite", 3000));

        hotel.addRoom(new Room("301", "Standard", 1000));
        hotel.addRoom(new Room("302", "Standard", 1000));
        hotel.addRoom(new Room("303", "Deluxe", 1500));
        hotel.addRoom(new Room("304", "Deluxe", 1500));
        hotel.addRoom(new Room("305", "Suite", 3000));
    }

    static void viewAvailableRooms() {
        System.out.print("Enter room type (e.g., Deluxe/Standard/Suite): ");
        String type = sc.nextLine();
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkIn = sc.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkIn);
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOut = sc.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOut);

        List<Room> availableRooms = hotel.searchAvailableRoomsByType(type, checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the selected type and dates.");
        } else {
            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Price per Night: Rs. " + room.getPricePerNight());
            }
        }
    }

    static void makeReservation() {
        System.out.print("Enter room number to book: ");
        String roomNumber = sc.nextLine();
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(sc.nextLine());
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(sc.nextLine());
        System.out.print("Enter payment method (Card/UPI/Cash): ");
        String payment = sc.nextLine();

        Room selectedRoom = null;
        for (Room room : hotel.getAllRooms()) {
            if (room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        String reservationId = hotel.makeReservation(currentUser, selectedRoom, checkIn, checkOut, payment);
        if (reservationId != null) {
            System.out.println("Reservation successful!");
            System.out.println("Reservation ID: " + reservationId);
            System.out.println("Total Cost: " + hotel.getTotalCost(reservationId));
        } else {
            System.out.println("Room is not available for the selected dates.");
        }
    }

    public static void main(String[] args) {
        initializeHotel();

        System.out.print("Enter your Username: ");
        String username = sc.nextLine();
        System.out.print("Enter your Fullname: ");
        String name = sc.nextLine();
        currentUser = new User(username, name);
        try {
            boolean run = true;
            while (run) {
                System.out.println("\n--- Hotel Reservation System ---");
                System.out.println("1. View Available Rooms by Type");
                System.out.println("2. Make a Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                switch (choice) {
                    case 1:
                        viewAvailableRooms();
                        break;
                    case 2:
                        makeReservation();
                        break;
                    case 3:
                        run = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Invalid date format! Please enter date in YYYY-MM-DD format.");
            return;
        }
    }
}