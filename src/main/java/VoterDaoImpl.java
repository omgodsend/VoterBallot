import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoterDaoImpl implements VoterDao {
    private final Connection connection;

    public VoterDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addVoter(Voter voter) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO voters (voterName, age, party, date_of_birth, candidate) VALUES (?,?,?,?,?)");) {
            statement.setString(1, voter.getVoterName());
            statement.setInt(2, voter.getAge());
            statement.setString(3, voter.getParty());
            statement.setString(4, voter.getDob());
            statement.setString(5, voter.getCandidate());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateVoter(Voter voter) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE voters SET voterId = ?, voterName = ?, age = ?, party = ?, date_of_birth = ?, candidate = ? WHERE voterId = ?");) {
            statement.setInt(1, voter.getVoterId());
            statement.setString(2, voter.getVoterName());
            statement.setInt(3, voter.getAge());
            statement.setString(4, voter.getParty());
            statement.setString(5, voter.getDob());
            statement.setString(6, voter.getCandidate());
            statement.setInt(7, voter.getVoterId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteVoter(int voterId) {
        Voter voter = null;
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM voters WHERE voterId = ?");) {
            statement.setInt(1, voterId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Voter getVoterById(int voterId) {
        Voter voter = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM voters WHERE voterId = ?");) {
            statement.setInt(1, voterId);
            try (ResultSet rs = statement.executeQuery();) {
                if (rs.next()) {
                    String voterName = rs.getString("voterName");
                    int age = rs.getInt("age");
                    String party = rs.getString("party");
                    String dob = rs.getString("date_of_birth");
                    String candidate = rs.getString("candidate");

                    voter = new Voter(voterId, voterName, party, candidate, dob, age);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return voter;
    }
    @Override
    public List<Voter> getAllVoters() {
        List<Voter> voters = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM voters");) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int voterId = rs.getInt("voterId");
                    String voterName = rs.getString("voterName");
                    int age = rs.getInt("age");
                    String party = rs.getString("party");
                    String dob = rs.getString("date_of_birth");
                    String candidate = rs.getString("candidate");

                    Voter voter = new Voter(voterId, voterName, party, candidate, dob, age);
                    voters.add(voter);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return voters;
    }
}
