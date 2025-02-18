import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private boolean[] seats;
    private Lock lock;
    private PriorityQueue<BookingRequest> bookingQueue;

    public TicketBookingSystem(int numSeats) {
        seats = new boolean[numSeats];
        lock = new ReentrantLock();
        bookingQueue = new PriorityQueue<>(Comparator.comparingInt(b -> -b.priority));
        Arrays.fill(seats, true);
    }

    public void bookSeat() {
        while (!bookingQueue.isEmpty()) {
            BookingRequest request = bookingQueue.poll();
            int seatNum = request.seatNum;
            boolean isVIP = request.isVIP;

            try {
                Thread.sleep(1000);
                lock.lock();
                try {
                    if (seats[seatNum]) {
                        seats[seatNum] = false;
                        System.out.println("Seat " + (seatNum + 1) + " booked successfully by " + (isVIP ? "VIP" : "Regular") + " customer.");
                    } else {
                        System.out.println("Seat " + (seatNum + 1) + " is already booked. " + (isVIP ? "VIP" : "Regular") + " customer cannot book it.");
                    }
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void addBookingRequest(int seatNum, boolean isVIP) {
        int priority = isVIP ? 1 : 0;
        bookingQueue.add(new BookingRequest(seatNum, isVIP, priority));
    }

    private static class BookingRequest {
        int seatNum;
        boolean isVIP;
        int priority;

        BookingRequest(int seatNum, boolean isVIP, int priority) {
            this.seatNum = seatNum;
            this.isVIP = isVIP;
            this.priority = priority;
        }
    }

    public void startBookingThreads() {
        Thread bookingProcessor = new Thread(this::bookSeat);
        bookingProcessor.start();
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(5);

        bookingSystem.addBookingRequest(0, true);
        bookingSystem.addBookingRequest(1, false);
        bookingSystem.addBookingRequest(2, true);
        bookingSystem.addBookingRequest(3, false);
        bookingSystem.addBookingRequest(4, false);

        bookingSystem.startBookingThreads();
    }
}
