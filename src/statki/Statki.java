package statki;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Statki extends JFrame {
	private static JButton nowaGra; //przycisk rozpoczynaj�cy now� gr�
	private static JButton wyniki; //przycisk wy�wietlaj�cy ranking najlepszy graczy
	private Losowanko maszynaLosujaca=Losowanko.pobierzMaszyneLosujaca(); 
	private Plansza plansza;
	public static JLabel liczbaWykonanychRuchow; //wy�wietla liczb� wykonanych ruch�w przez gracza w danej grze
	
	public Statki(){
		super("Gra w statki z komputerem");
		setSize(700,530);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Insets insets=new Insets(1,1,1,1);
		setResizable(false);
		
		nowaGra=new JButton("Nowa gra");
		nowaGra.setMargin(insets);
		nowaGra.setFocusable(false);
		nowaGra.setBounds(557,90,80,30);
		nowaGra.addActionListener(new NowaGra());
		
		wyniki=new JButton("WYNIKI");
		wyniki.setMargin(insets);
		wyniki.setFocusable(false);
		wyniki.setBounds(557,130,80,30);
		wyniki.addActionListener(new Wyniki());
		
		liczbaWykonanychRuchow=new JLabel("Liczba wykonanych ruch�w: "+Pola.zwrocLicznikRuchow());
		liczbaWykonanychRuchow.setFocusable(false);
		liczbaWykonanychRuchow.setBounds(510,30,200,30);
		
		plansza=new Plansza();
		plansza.setBounds(0,0,500,500);
		noweLosowanie();
		add(plansza);
		add(nowaGra);
		add(wyniki);
		add(liczbaWykonanychRuchow);
		
		setVisible(true);
	}
	public void noweLosowanie(){ //metoda losuj�ca odpowiedni� liczb� statk�w
		maszynaLosujaca.wyczyscListy(); //czy�cimy list� statk�w oraz s�siaduj�cych z nimi p�l
		Pola.zerujLicznikRuchow(); //na pocz�tku ka�dej gry zerujemy licznik
		liczbaWykonanychRuchow.setText("Liczba wykonanych ruch�w: "+Pola.zwrocLicznikRuchow()); //wy�wietlamy wyzerowany licznik
		maszynaLosujaca.czteromasztowiec();
		maszynaLosujaca.trzymasztowiec();
		maszynaLosujaca.trzymasztowiec();
		maszynaLosujaca.dwumasztowiec();
		maszynaLosujaca.dwumasztowiec();
		maszynaLosujaca.dwumasztowiec();
		maszynaLosujaca.jednomasztowiec();
		maszynaLosujaca.jednomasztowiec();
		maszynaLosujaca.jednomasztowiec();
		maszynaLosujaca.jednomasztowiec();
	}
	
	public class NowaGra implements ActionListener{ //po naci�ni�ciu przycisku "nowa gra"
		public void actionPerformed(ActionEvent e){
			plansza.hide();
			noweLosowanie();
			plansza=new Plansza();
			add(plansza);
			plansza.setVisible(true);;
		}
	}
	private class Wyniki implements ActionListener { //po naci�ni�ciu przycisku "wyniki"
		public void actionPerformed(ActionEvent e) {
			OknoWyniki oknoWyniki = new OknoWyniki();
		}
	}
	
	public static void main(String[] args){
		new Statki();
	}

}
