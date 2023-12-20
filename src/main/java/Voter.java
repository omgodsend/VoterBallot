public class Voter {
    int voterId;
    String voterName;
    String party;
    String candidate;
    String dob;
    int age;

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = Integer.parseInt(voterId);
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "voterId='" + voterId + '\'' +
                ", voterName='" + voterName + '\'' +
                ", party='" + party + '\'' +
                ", candidate='" + candidate + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }

    public Voter(int voterId, String voterName, String party, String candidate, String dob, int age) {
            this.voterId = voterId;
            this.voterName = voterName;
            this.party = party;
            this.candidate = candidate;
            this.dob = dob;
            this.age = age;
    }

    public Voter(String voterName, String party, String candidate, String dob, int age) {
        this.voterName = voterName;
        this.party = party;
        this.candidate = candidate;
        this.dob = dob;
        this.age = age;
    }
}
