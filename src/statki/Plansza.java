package statki;
import java.awt.GridLayout;
import javax.swing.*;

public class Plansza extends JPanel { //klasa tworz�ca plansz� do gry
	private Pola[] tablica=new Pola[100];
	Plansza(){
		super();
		GridLayout uklad=new GridLayout(10,10); //planasza to siatka 10x10
		setLayout(uklad);
		for(int i=0;i<100;i++){ //p�tla umieszczaj�ca 100 p�l na naszej planszy
			tablica[i]=new Pola(i);
			add(tablica[i]);
			}
		setSize(500, 500);
		setVisible(true);
		}
}