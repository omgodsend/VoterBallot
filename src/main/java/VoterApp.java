import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class VoterApp {

  private static final VoterDao voterDao = new VoterDaoImpl(JDBConnection.getConnection());


  public static void main(String[] args) {/////////////START OF MAIN//////////////////

    Scanner input = new Scanner(System.in);

    int choice;

    do {
      System.out.println("""
              Welcome to Voter Ballot menu!
              Please select one of the following.
              1- Create a Voter.
              2- Update a Voter.
              3- Delete a Voter.
              4- Show a Voter.
              5- Display all Voters.
              6- Exit the App.
              """);

      choice = input.nextInt();
      input.nextLine();

      switch (choice) {
        case 1:
          addVoter(input);
          break;
        case 2:
          updateVoter(input);
          break;
        case 3:
          deleteVoter(input);
          break;
        case 4:
          readVoterById(input);
          break;
        case 5:
          displayAllVoters(input);
          break;
        case 6:
          System.out.println("Exiting application...");
          break;
        default:
          System.out.println("Invalid input");
      }

    }while (choice != 6);


  }///////////////////////////END OF MAIN///////////////////////////////////////////

  private static void displayAllVoters(Scanner input) {
    for (Voter voter : voterDao.getAllVoters()) {
      System.out.println(voter);
    }
  }

  private static void readVoterById(Scanner input) {
    System.out.println("Please enter a Voter ID you'd like to see.");
    int voterId = input.nextInt();
    input.nextLine();
    Voter voter = voterDao.getVoterById(voterId);
    if (voter == null) {
      System.out.println("Voter not found");
    } else {
      System.out.println(voter);
    }
  }

  private static void deleteVoter(Scanner input) {
    System.out.println("Please enter Voter ID to delete");
    int voterId = input.nextInt();
    input.nextLine();
    Voter voter = voterDao.getVoterById(voterId);
    if (voter == null) {
      System.out.println("Voter not found");
    } else {
      voterDao.deleteVoter(voterId);
      System.out.println("Voter deleted successfully");
    }
  }

  private static void updateVoter(Scanner input) {
    System.out.println("Please enter Voter ID:");
    int voterId = input.nextInt();
    input.nextLine();
    Voter voter = voterDao.getVoterById(voterId);
    if (voter == null){
      System.out.println("Voter not found.");
    } else {
      System.out.println("Enter voter's name (or press enter to skip):");
      String voterName = input.nextLine();
      System.out.println("Enter voter's party (or press enter to skip):");
      String party = input.nextLine();
      System.out.println("Enter candidate's name (or press enter to skip):");
      String candidate = input.nextLine();
      System.out.println("Enter voter's date of birth (or press enter to skip):");
      String dob = input.nextLine();
      System.out.println("Enter voter's age (or press enter to skip):");
      int age = input.nextInt();
      input.nextLine();

      voter.setVoterName(voterName.isEmpty() ? voter.getVoterName() : voterName);
      voter.setParty(party.isEmpty() ? voter.getParty() : party);
      voter.setCandidate(candidate.isEmpty() ? voter.getCandidate() : candidate);
      voter.setDob(dob.isEmpty() ? voter.getDob() : dob);
      voter.setAge(age == 0 ? voter.getAge() : age);

      voterDao.updateVoter(voter);
      System.out.println("Voter updated successfully!");
      voterDao.getVoterById(voterId);
    }
  }

  private static void addVoter(Scanner input) {
    System.out.println("Please enter a voter name.");
    String voterName = input.nextLine();
    System.out.println("Please enter the voter's party");
    String party = input.nextLine();
    System.out.println("Please enter the voter's candidate");
    String candidate = input.nextLine();
    System.out.println("Please enter the voter's Date of Birth YYYY-MM-DD");
    String dob = input.nextLine();
    System.out.println("Please enter the voter's age");
    int age = input.nextInt();

    Voter voter = new Voter(voterName, party, candidate, dob, age);

    voterDao.addVoter(voter);
    System.out.println("Voter added successfully");
  }


}