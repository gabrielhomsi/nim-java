import java.util.Vector;

public class Field {
	private Vector<Group> groups = new Vector<Group>();
	
	public void addGroup(Group g) {
		groups.add(g);
	}
	
	private Group _getGroup(int n) {
		return groups.get(n);
	}

	public Group getGroup(int n) throws Exception {
		return groups.get(n);
	}
	
	public int size() {
		return groups.size();
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < size(); i++) {
			if (_getGroup(i).size() > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public String toString() {
		String result = "";
		
		for (int i = 0; i < size(); i++) {
			result += i + 1 + ": ";
			
			Group g = _getGroup(i);
			for (int j = 0; j < g.size(); j++) {
				result += g.getPiece(0) + " ";
			}
			result += "\n";
		}
		
		return result;
	}
}
