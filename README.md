# Votum: Terminal Voting System in Java

Votum is a small digital ballot system that runs in the terminal (command prompt) on **one computer**. A voter can cast one vote, and an admin can see the results. There is no GUI, no internet, no database and no files. Everything is stored in memory while the program runs.

This project is made for the course **CSC2104 (Object Oriented Programming), Manipal University Jaipur**. It uses only topics from the course handout.

---

## Table of Contents

1. [Project at a Glance](#1-project-at-a-glance)
2. [Features](#2-features)
3. [Requirements](#3-requirements)
4. [Project Structure](#4-project-structure)
5. [How the Program Flows](#5-how-the-program-flows)
6. [How to Compile and Run](#6-how-to-compile-and-run)
7. [Demo Data](#7-demo-data)
8. [How to Use the Program](#8-how-to-use-the-program)
9. [Components in Detail](#9-components-in-detail)
10. [How the Three Files Connect](#10-how-the-three-files-connect)
11. [Handout Topics Used](#11-handout-topics-used)
12. [Team Work Division](#12-team-work-division)
13. [Testing Checklist](#13-testing-checklist)
14. [Known Limitations](#14-known-limitations)
15. [Troubleshooting](#15-troubleshooting)

---

## 1. Project at a Glance

| Item | Details |
|---|---|
| Project name | Votum |
| Language | Java |
| Type | Terminal (console) application |
| Course | CSC2104, Object Oriented Programming |
| University | Manipal University Jaipur |
| Number of files | 3 (`Main.java`, `Admin.java`, `Voter.java`) |
| Team size | 3 members, one file each |
| Data storage | In memory only (votes are lost when the program exits) |

---

## 2. Features

- A menu with three options: Voter, Admin, Exit.
- A voter logs in with a voter ID and a PIN.
- Each voter can vote **only once**.
- Wrong ID or PIN is rejected.
- A wrong candidate number is rejected, and the vote is not counted.
- Admin can see the vote count of every candidate and the total votes.
- The menu keeps coming back after every action until the user chooses Exit.

---

## 3. Requirements

- **JDK** (Java Development Kit) installed. Check with:

```text
java -version
javac -version
```

- A terminal (Command Prompt, PowerShell, or Linux/Mac Terminal).
- A text editor or IDE (VS Code, IntelliJ, Eclipse, Notepad++, or any).

No extra libraries are needed.

---

## 4. Project Structure

Keep all three files in **one folder**.

```text
votum/
├── Main.java     (class Main:  starts the program and shows the menu)
├── Admin.java    (class Admin: holds candidates, votes and voters, shows results)
├── Voter.java    (class Voter: one voter and the voting steps)
└── README.md     (this file)
```

File names must match the class names exactly, including capital letters.

---

## 5. How the Program Flows

```text
Main (main method, starts the program)
  |
  v
User menu (while loop)
  |
  |-- 1) Voter -> asks ID and PIN
  |                 |-- wrong ID or PIN         -> message, back to menu
  |                 |-- already voted           -> message, back to menu
  |                 |-- valid                   -> Voter.castVote()
  |                                                 -> shows candidates
  |                                                 -> reads choice (1 to 3)
  |                                                 -> Admin.addVote(choice)
  |                                                 -> voter marked as voted
  |
  |-- 2) Admin -> Admin.showResults() -> back to menu
  |
  |-- 3) Exit  -> prints goodbye and the loop ends
```

After option 1 or option 2, the program always returns to the menu. Only option 3 ends the program.

---

## 6. How to Compile and Run

Open the terminal inside the `votum` folder and run:

```text
javac *.java
java Main
```

- `javac *.java` compiles all three files together.
- `java Main` runs the program. Run it by the name `Main`, not `Main.java`.

If you change any file, compile again before running.

---

## 7. Demo Data

The data is fixed inside `Admin.java`.

**Candidates**

| Number | Name |
|---|---|
| 1 | Ashish Kuntal |
| 2 | Parth |
| 3 | Swayam |

**Voters**

| Voter ID | PIN |
|---|---|
| 1 | 1234 |
| 2 | 1234 |
| 3 | 1234 |

---

## 8. How to Use the Program

### 8.1 Main menu

When the program starts you will see:

```text
==================
    THE VOTUM     
==================
 
1) VOTE
2) RESULTS
3) EXIT
 
Enter your option: 
```

Type `1`, `2` or `3` and press Enter. Any other number shows `Wrong option, enter 1, 2 or 3.`

### 8.2 Voting (option 1)

1. Enter `1`.
2. Enter your voter ID (1 to 3).
3. Enter your PIN (`1234`).
4. Pick a candidate by typing its number.

Sample run:

```text
Enter your option: 1
Enter your voter id (1 to 3): 1
Enter your pin: 1234
These are the candidates:
1) Ashish Kuntal
2) Parth
3) Swayam
Enter the number of your candidate (1 to 3): 2
Thank you! You voted for Parth.
```

Possible messages:

| Situation | Message |
|---|---|
| Wrong ID or PIN | `Wrong id or pin, try again.` |
| Voter already voted | `You have already voted, you can't vote again.` |
| Candidate number not 1 to 3 | `Wrong number, please enter 1, 2 or 3. Your vote is not counted.` |
| Vote accepted | `Thank you! You voted for <name>.` |

If the candidate number is wrong, the vote is **not** counted and the voter is **not** marked as voted, so that voter can try again.

### 8.3 Seeing results (option 2)

Enter `2`. Sample output after the vote above:

```text
Here are the results:
Ashish Kuntal got 0 votes
Parth got 1 votes
Swayam got 0 votes
Total votes: 1
```

### 8.4 Exit (option 3)

Enter `3`. The program prints `Bye bye!` and stops. All votes are lost because they are stored only in memory.

---

## 9. Components in Detail

### 9.1 Voter.java (class `Voter`)

Represents one voter and the steps of casting a vote.

**Fields**

| Field | Type | Meaning |
|---|---|---|
| `id` | `private final int` | The voter ID. Cannot change after creation. |
| `pin` | `private final int` | The voter PIN. Cannot change after creation. |
| `voted` | `private boolean` | `false` at the start, becomes `true` after voting. |

**Constructor and methods**

| Member | What it does |
|---|---|
| `Voter(int id, int pin)` | Creates a voter and sets `voted` to `false`. |
| `boolean login(int enteredId, int enteredPin)` | Returns `true` only if both the ID and the PIN match. |
| `boolean hasVoted()` | Returns `true` if this voter has already voted. |
| `void markVoted()` | Sets `voted` to `true`. |
| `void castVote(Scanner input, Admin admin)` | Shows the candidates, reads the choice, checks it is 1 to 3, calls `admin.addVote(choice)`, then calls `markVoted()`. |

**Uses from Admin:** `getCandidateCount()`, `getCandidateName(int)`, `addVote(int)`.

### 9.2 Admin.java (class `Admin`)

Holds the election data and shows the results.

**Fields**

| Field | Type | Meaning |
|---|---|---|
| `candidates` | `private final String[]` | The names: Ashish Kuntal, Parth, Swayam. |
| `counts` | `private final int[]` | Vote count of each candidate (same position as the name). |
| `voters` | `private final Voter[]` | The three demo voters. |

**Constructor and methods**

| Member | What it does |
|---|---|
| `Admin()` | Creates the three demo `Voter` objects. |
| `int findVoter(int id, int pin)` | Loops over the voters and returns the position of the one that matches, or `-1` if none match. |
| `Voter getVoter(int index)` | Returns the voter at that position. |
| `int getCandidateCount()` | Returns how many candidates there are. |
| `String getCandidateName(int index)` | Returns the name of one candidate. |
| `void addVote(int choice)` | Adds one to the count of that candidate (`counts[choice - 1]++`). |
| `void showResults()` | Prints every candidate's votes and the total. |

**Uses from Voter:** `login(int, int)`.

### 9.3 Main.java (class `Main`)

The starting point of the program. It connects the other two classes.

| Part | What it does |
|---|---|
| `main` method | Creates one `Scanner` and one `Admin`. |
| Menu loop | A `while` loop that runs until the user enters 3. |
| Option 1 | Reads ID and PIN, calls `admin.findVoter(...)`, checks `hasVoted()`, then calls `castVote(...)`. |
| Option 2 | Calls `admin.showResults()`. |
| Option 3 | Prints the exit message and ends the loop. |
| Resource cleanup | Calls `input.close()` after the loop to cleanly release the scanner. |

**Uses from Admin:** `findVoter`, `getVoter`, `showResults`.
**Uses from Voter:** `hasVoted`, `castVote`.

---

## 10. How the Three Files Connect

```text
Main  --creates-->  Admin  --creates and holds-->  Voter objects (ID 1, 2, 3)
 |                    ^
 |                    | addVote(choice)
 |--option 1--> Voter.castVote(input, admin)
 |--option 2--> Admin.showResults()
```

- `Main` creates **one** `Admin` object, and `Admin` creates the three `Voter` objects.
- `Main` gives the same `Scanner` and the same `Admin` to `castVote`, so every vote goes into the same counts that the admin shows.

### Method contract between files

Each member must keep these method names and types **exactly the same**, otherwise the files will not compile together.

| Method | Written in | Called from |
|---|---|---|
| `int findVoter(int id, int pin)` | Admin | Main |
| `Voter getVoter(int index)` | Admin | Main |
| `void showResults()` | Admin | Main |
| `int getCandidateCount()` | Admin | Voter |
| `String getCandidateName(int index)` | Admin | Voter |
| `void addVote(int choice)` | Admin | Voter |
| `boolean login(int enteredId, int enteredPin)` | Voter | Admin |
| `boolean hasVoted()` | Voter | Main |
| `void markVoted()` | Voter | Voter (inside `castVote`) |
| `void castVote(Scanner input, Admin admin)` | Voter | Main |

---

## 11. Handout Topics Used

| Topic | Lecture | Where it is used |
|---|---|---|
| Datatypes, variables | 3 | all files |
| Operators | 4 | `&&`, `||`, `==`, `++`, `+` in all files |
| `if`, `else if` | 5 | Main, Voter |
| `for`, `while` | 6 | Main (`while`), Admin and Voter (`for`) |
| Arrays | 9 | Admin |
| Classes and objects | 10 | all files |
| Methods | 12 | all files |
| Constructors | 13 | Voter, Admin |
| Access control (`public`, `private`) | 18 | Voter, Admin |
| `final` | 19 | Voter, Admin |
| `Scanner` (console input) | 32 | Main, Voter |
| Console output | 33 | all files |

**Not clearly in the handout (check with your notes):**

- `public static void main(String[] args)`: required by Java, treat it as a fixed line.
- `input.nextInt()`: a method of `Scanner` that reads one whole number.
- `this.id = id;`: `this` refers to the field of the current object.
- Methods without `public` or `private` (default access).

---

## 12. Team Work Division

Three members, three parts. Every member owns **one Java file** and does an **equal set of extra tasks**, so the work is balanced.

### 12.1 Ownership at a glance

| | Member 1 | Member 2 | Member 3 |
|---|---|---|---|
| **Name** | ______________ | ______________ | ______________ |
| **Roll no.** | ______________ | ______________ | ______________ |
| **Java file** | `Voter.java` | `Admin.java` | `Main.java` |
| **Class** | `Voter` | `Admin` | `Main` |
| **Role in the program** | One voter and the voting steps | Data, votes and results | Menu and connecting everything |
| **Size of code** | 1 constructor, 4 methods | 1 constructor, 6 methods | 1 `main` method with menu loop and option handling |

### 12.2 Code each member writes

**Member 1: Voter.java**

- Private fields `id`, `pin`, `voted`.
- Constructor `Voter(int id, int pin)`.
- `login`, `hasVoted`, `markVoted`.
- `castVote`: shows candidates, reads and checks the choice, calls `addVote`, marks the voter as voted.

**Member 2: Admin.java**

- Arrays `candidates`, `counts`, `voters`.
- Constructor `Admin()` that creates the three demo voters.
- `findVoter`, `getVoter`.
- `getCandidateCount`, `getCandidateName`.
- `addVote`, `showResults`.

**Member 3: Main.java**

- Creating the `Scanner` and the `Admin` object.
- The `while` menu loop.
- Option 1: reading ID and PIN, calling `findVoter`, checking `hasVoted`, calling `castVote`.
- Option 2 and option 3 handling.
- Wrong-option message.

### 12.3 Shared tasks, divided equally

| Task | Member 1 | Member 2 | Member 3 |
|---|---|---|---|
| Write own code file | Voter.java | Admin.java | Main.java |
| Write README section for own file (Section 9) | 9.1 | 9.2 | 9.3 |
| Run the tests for own file (Section 13) | Tests 1 to 4 (voting) | Tests 5 to 8 (data and results) | Tests 9 to 12 (menu and flow) |
| Prepare to explain own code in viva | Voter | Admin | Main |
| Extra project task | Draw the flowchart (Section 5) | Prepare the demo data table and the handout topics table (Sections 7 and 11) | Final compile and full run check, record the sample outputs (Section 8) |

### 12.4 How to work together

1. **Agree on the method contract first** (Section 10). Do not change any method name, type or parameter without telling the other two.
2. Each member writes and saves their own file.
3. Put all three files in one folder and compile together with `javac *.java`.
4. Fix errors together. Most errors come from a mismatched method name or a missing capital letter in a file name.
5. Run the full testing checklist (Section 13) before submitting.

### 12.5 Who explains what in the viva

| Question | Answered by |
|---|---|
| Why are `id` and `pin` `private final`? | Member 1 |
| How does `castVote` decide a vote is not valid? | Member 1 |
| Why do we use arrays for candidates and counts? | Member 2 |
| What does `findVoter` return, and why `-1`? | Member 2 |
| How does the menu loop end? | Member 3 |
| How do the three classes talk to each other? | Member 3 |

---

## 13. Testing Checklist

Run these after compiling. The tests are written in order, so run them in one session starting from a fresh `java Main`.

| No. | Owner | What to do | Expected result |
|---|---|---|---|
| 1 | Member 1 | Option 1, ID 1, PIN 1234, pick 2 | `Thank you! You voted for Parth.` |
| 2 | Member 1 | Option 1, ID 3, PIN 1234, pick 7 | `Wrong number, please enter 1, 2 or 3. Your vote is not counted.` |
| 3 | Member 1 | After test 2, option 1 again with ID 3, PIN 1234, pick 1 | Vote is accepted for Ashish Kuntal (voter was not marked as voted) |
| 4 | Member 1 | Option 1 again with ID 1, PIN 1234 | `You have already voted, you can't vote again.` |
| 5 | Member 2 | Option 1, ID 2, PIN 0000 | `Wrong id or pin, try again.` |
| 6 | Member 2 | Option 1, ID 9, PIN 1234 | `Wrong id or pin, try again.` |
| 7 | Member 2 | Option 2 after tests 1 to 3 | Ashish Kuntal 1, Parth 1, Swayam 0, `Total votes: 2` |
| 8 | Member 2 | Option 2 at the very start of a new run | All counts 0, `Total votes: 0` |
| 9 | Member 3 | Enter `7` at the menu | `Wrong option, enter 1, 2 or 3.` |
| 10 | Member 3 | After any action, check that the menu appears again | Menu is shown again with THE VOTUM banner |
| 11 | Member 3 | Enter `3` | `Bye bye!` and the program stops |
| 12 | Member 3 | Run again after exit, option 2 | Counts are 0 again (votes are not saved) |

---

## 14. Known Limitations

- **Typing text instead of a number crashes the program.** `nextInt()` throws an `InputMismatchException`. It can be fixed with `try` / `catch` (Lectures 36 to 38).
- **Votes are not saved.** When the program exits, all votes are lost. Saving to a file would need `FileWriter` (Lecture 33).
- **Fixed demo data.** The candidates and voters are written inside `Admin.java`. There is no way to add new ones while the program runs.
- **No admin password.** Anyone who picks option 2 can see the results.
- **Runs on one computer only.** There is no network.

---

## 15. Troubleshooting

| Problem | Reason and fix |
|---|---|
| `'javac' is not recognized` | JDK is not installed, or not added to PATH. Install the JDK and open a new terminal. |
| `class Admin is public, should be declared in a file named Admin.java` | The file name does not match the class name. Check the capital letters. |
| `cannot find symbol` | A method name is different between files. Compare with the table in Section 10. |
| `Error: Could not find or load main class Main` | You are not in the project folder, or you ran `java Main.java` or forgot to compile. Run `javac *.java`, then `java Main`. |
| Program crashes with `InputMismatchException` | You typed text where a number was needed. Restart and type numbers only. |
| Old behaviour after editing code | You forgot to compile again. Run `javac *.java`. |
