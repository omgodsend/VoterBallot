import java.util.List;

public interface VoterDao {
  void addVoter(Voter voter);
  void updateVoter(Voter voter);
  void deleteVoter(int voterId);
  Voter getVoterById(int voterId);
  List<Voter> getAllVoters();
}
