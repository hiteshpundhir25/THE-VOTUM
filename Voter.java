import java.util.Scanner;

public class Voter {
    private final int id;
    private final int pin;
    private boolean voted;

    Voter(int id, int pin) {
        this.id = id;
        this.pin = pin;
        this.voted = false;
    }

    boolean login(int enteredId, int enteredPin) {
        return id == enteredId && pin == enteredPin;
    }

    boolean hasVoted() {
        return voted;
    }

    void markVoted() {
        voted = true;
    }

    void castVote(Scanner input, Admin admin) {
        System.out.println("These are the candidates:");
        for (int c = 0; c < admin.getCandidateCount(); c++) {
            System.out.println((c + 1) + ") " + admin.getCandidateName(c));
        }
        System.out.print("Enter the number of your candidate (1 to 3): ");
        int choice = input.nextInt();

        if (choice < 1 || choice > admin.getCandidateCount()) {
            System.out.println("Wrong number, please enter 1, 2 or 3. Your vote is not counted.");
            return;
        }

        admin.addVote(choice);
        markVoted();
        System.out.println("Thank you! You voted for " + admin.getCandidateName(choice - 1) + ".");
    }
}