public class Admin {
    private final String[] candidates = { "Ashish Kuntal", "Parth", "Swayam"} ;
    private final int[] counts = new int[3];
    private final Voter[] voters = new Voter[3];

    Admin() {
        voters[0] = new Voter(1, 1234);
        voters[1] = new Voter(2, 5678);
        voters[2] = new Voter(3, 9012);
    }

    int findVoter(int id, int pin) {
        for (int i = 0; i < voters.length; i++) {
            if (voters[i].login(id, pin)) {
                return i;
            }
        }
        return -1;
    }

    Voter getVoter(int index) {
        return voters[index];
    }

    int getCandidateCount() {
        return candidates.length;
    }

    String getCandidateName(int index) {
        return candidates[index];
    }

    void addVote(int choice) {
        counts[choice - 1]++;
    }

    void showResults() {
        int total = 0;
        System.out.println("Here are the results:");
        for (int c = 0; c < candidates.length; c++) {
            System.out.println(candidates[c] + " got " + counts[c] + " votes");
            total = total + counts[c];
        }
        System.out.println("Total votes: " + total);
    }
}