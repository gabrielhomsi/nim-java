import java.util.Scanner;

public class Helper {
	public static void pause() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Aperte enter para continuar.");
		sc.nextLine();
	}
	
	public static void exit() {
		pause();
		System.exit(-1);
	}
	
	public static void clear() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
