import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();
        int choice = 0;
        while (choice != 3) {
            System.out.println(" ");
            System.out.println("==================");
            System.out.println("    THE VOTUM     ");
            System.out.println("==================");
            System.out.println(" ");
            System.out.println("1) VOTE");
            System.out.println("2) RESULTS");
            System.out.println("3) EXIT");
            System.out.println(" ");
            System.out.print("Enter your option: ");
            choice = input.nextInt();
            if (choice == 1) {
                System.out.print("Enter your voter id (1 to 3): ");
                int id = input.nextInt();
                System.out.print("Enter your pin: ");
                int pin = input.nextInt();
                int i = admin.findVoter(id, pin);
                if (i == -1) {
                    System.out.println("Wrong id or pin, try again.");
                } else if (admin.getVoter(i).hasVoted()) {
                    System.out.println("You have already voted, you can't vote again.");
                } else {
                    admin.getVoter(i).castVote(input, admin);
                }
            } else if (choice == 2) {
                admin.showResults();
            } else if (choice == 3) {
                System.out.println("Bye bye!");
            } else {
                System.out.println("Wrong option, enter 1, 2 or 3.");
            }
            System.out.println();
        }
        input.close();
    }
}