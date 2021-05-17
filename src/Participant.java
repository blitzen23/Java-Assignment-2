import java.util.Comparator;

public class Participant {

	Integer shareNum;
	Integer shuffleNum;
	String username;
	public Participant(String username, Integer shareNum) {
		// TODO Auto-generated constructor stub
		this.shareNum = shareNum;
		this.username = username;
	}
	
	public static Comparator<Participant> usernameComparator = new Comparator<Participant>() {
		public int compare(Participant p1, Participant p2) {
			return p1.username.compareTo(p2.username);
	}};
}
