import java.util.Vector;

public class Group {
	private Vector<Piece> pieces = new Vector<Piece>();
	
	public void addPiece(Piece p) {
		pieces.add(p);
	}
	
	public Piece getPiece(int i) {
		return pieces.get(i);
	}

	public void removePiece() throws Exception {
		try {
			pieces.removeElementAt(0);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			throw new Exception();
		}
	}
	
	public void removePieces(int n) throws Exception {
		if (n > pieces.size() || n == 0) {
			throw new Exception();
		}
		else {
			for (int i = 0; i < n; i++) {
				removePiece();
			}
		}
	}
	
	public int size() {
		return pieces.size();
	}
}
