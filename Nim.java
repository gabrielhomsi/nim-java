import java.util.*;

public class Nim {
	Field f = new Field();
	Network n = new Network();
	boolean myTurn = false;
	
	Nim() {
		Group g1 = new Group();
		for (int i = 0; i < 3; i++) {
			g1.addPiece(new Piece());
		}
		f.addGroup(g1);
		
		Group g2 = new Group();
		for (int i = 0; i < 4; i++) {
			g2.addPiece(new Piece());
		}
		f.addGroup(g2);
		
		Group g3 = new Group();
		for (int i = 0; i < 5; i++) {
			g3.addPiece(new Piece());
		}
		f.addGroup(g3);
	}
	
	public void menu() {
		boolean invalidInput = false;
		int o;
		
		Scanner sc;
		
		while (true) { 
			Helper.clear();
			if (invalidInput) {
				System.out.println("Entrada invalida... tente novamente!");
				invalidInput = false;
			}
			System.out.println("1- Entrar em um jogo.");
			System.out.println("2- Criar um novo jogo.");
			System.out.println("3- Sair.");
			
			sc = new Scanner(System.in);
			try {
				o = Integer.parseInt(sc.nextLine());
			}
			catch (Exception e) {
				invalidInput = true;
				continue;
			}
			
			if (!(o >= 1 && o <= 3)) {
				invalidInput = true;
			}
			else {
				break;
			}
		}
		
		if (o == 1) {
			System.out.println("Entre com o endereco IP do criador do jogo: ");
			String addr = sc.nextLine();
			
			n.join(addr, 8080);
			myTurn = Boolean.parseBoolean(n.getInfo());
		}
		else if (o == 2) {
			Random r = new Random();
			int friendTurn = r.nextInt(2);
			myTurn = friendTurn == 0;
			n.host(8080);
			n.sendInfo(!myTurn + "");
		}
		else if (o == 3) {
			System.out.println("Bye bye!!");
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
			}
			System.exit(0);
		}
		
		start();
	}
	
	private void start() {
		while (true) {
			int groupNumber = 0;
			int numberOfPieces = 0;
			boolean invalidInput = false;
			
			if (myTurn) {
				while (true) {
					Helper.clear();
					if (invalidInput) {
						System.out.println("Entrada invalida... tente novamente!");
						invalidInput = false;
					}
					System.out.println(f);
					try {
						System.out.println("Eh o seu turno!");
						Scanner sc = new Scanner(System.in);
					
						System.out.println("Numero do grupo: ");
						groupNumber = Integer.parseInt(sc.nextLine()) - 1;
					
						System.out.println("Numero de pecas a ser removido: ");
						numberOfPieces = Integer.parseInt(sc.nextLine());
					}
					catch (Exception e) {
						invalidInput = true;
						continue;
					}
					
					try {
						f.getGroup(groupNumber).removePieces(numberOfPieces);
						n.sendInfo(groupNumber + "");
						n.sendInfo(numberOfPieces + "");
						break;
					}
					catch (Exception e) {
						invalidInput = true;
					}
				}
			}
			else {
				Helper.clear();
				System.out.println(f);
				System.out.println("Eh o turno do seu amigo... ZzZzZ");
				groupNumber = Integer.parseInt(n.getInfo());
				numberOfPieces = Integer.parseInt(n.getInfo());
				try {
					f.getGroup(groupNumber).removePieces(numberOfPieces);
				}
				catch (Exception e) {
				}
			}
			
			if (f.isEmpty()) {
				Helper.clear();
				System.out.println("O jogo acabou.");
				if (myTurn) {
					System.out.println("Vc perdeu...");
				}
				else {
					System.out.println("Vc venceu! =D");
				}
				Helper.pause();
				return;
			}
			
			myTurn = !myTurn;
		}
	}
	
	public static void main(String[] args) {
		Nim g = new Nim();
		while (true) {
			g.menu();
		}
	}
}
