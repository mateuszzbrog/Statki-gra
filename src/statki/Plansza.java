package statki;
import java.awt.GridLayout;
import javax.swing.*;

public class Plansza extends JPanel { //klasa tworz¹ca planszê do gry
	private Pola[] tablica=new Pola[100];
	Plansza(){
		super();
		GridLayout uklad=new GridLayout(10,10); //planasza to siatka 10x10
		setLayout(uklad);
		for(int i=0;i<100;i++){ //pêtla umieszczaj¹ca 100 pól na naszej planszy
			tablica[i]=new Pola(i);
			add(tablica[i]);
			}
		setSize(500, 500);
		setVisible(true);
		}
}