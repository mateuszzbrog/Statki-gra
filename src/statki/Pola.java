package statki;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Pola extends JButton implements ActionListener { //klasa definiuj¹ca pola w którcyh przechowywane bêd¹ informacje czy w danym polu kryje siê statek
	private Image pole=new ImageIcon("rysunki/pole.jpg").getImage();
	private Image pudlo=new ImageIcon("rysunki/pudlo.jpg").getImage();
	private Image trafiony=new ImageIcon("rysunki/trafiony.jpg").getImage();
	private Integer pozycja; //musi byæ obiektem, ¿eby mo¿na by³o usun¹æ z listaStatkow bez podawania indeksu
	private Losowanko maszynaLosujaca=Losowanko.pobierzMaszyneLosujaca();
	private ArrayList<Integer> listaStatkow=maszynaLosujaca.zwrocPozycjeStatkow(); //przekazujemy pozycje statków do tablicy
	private static int licznikRuchow=0; //zmienna statyczna, bo jeden licznik dla ca³ej klasy
	
	Pola(Integer pozycja){
		super();
		setIcon(new ImageIcon(pole)); //pole po stworzeniu ma byæ niewiadom¹ - dopiero po klikniêciu gracz dowiaduje siê czy trafi³ w statek
		this.pozycja=pozycja; //zmienna przechowuje numer pola, potrzebny do porównania z list¹ pozycji do których komputer wylosowa³ statki
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(listaStatkow.isEmpty()){ //po trafieniu, pozycja jest usuwana z listy - je¿eli lista jest pusta, to gracz ustrzeli³ wszystkie statki
		koniecGry();
		} else{
			licznikRuchow++; //po ka¿dym klikniêciu zwiêkszamy licznik ruchów
			Statki.liczbaWykonanychRuchow.setText("Liczba wykonanych ruchów: "+zwrocLicznikRuchow());
			if(listaStatkow.contains(pozycja)){ 
				setIcon(new ImageIcon(trafiony)); //jeœli klikniêta pozycja jest na liœcie wylosowanych, to zmiana grafiki na "trafiony"
				listaStatkow.remove(pozycja); //po trafieniu usuwamy statek z listy statków
				removeActionListener(this); //wy³¹czamy aktywnoœæ, ¿eby licznik nie nabija³ kolejnych klikniêæ
				} else {
					setIcon(new ImageIcon(pudlo));	//jeœli klikniêtej pozycji nie ma na liœcie statków, to zmiana grafiki na "pud³o"
					removeActionListener(this); //wy³¹czamy aktywnoœæ, ¿eby licznik nie nabija³ kolejnych klikniêæ
				}
			} 
		koniecGry(); // po wszystkim sprawdzamy czy gra jest ju¿ skoñczona
	}
	public static int zwrocLicznikRuchow(){ //metoda zwraca licznik ruchów
		return licznikRuchow;
	}
	public static void zerujLicznikRuchow(){ //metoda zeruje licznik(wywo³ywana przy rozpoczynaniu nowej gry)
		licznikRuchow=0;
	}
	public void koniecGry(){
		if(listaStatkow.isEmpty()){ //sprawdzamy czy lista statków jest pusta
			Statki.liczbaWykonanychRuchow.setText("Wygra³eœ w : "+zwrocLicznikRuchow()+" ruchach!"); //informujemy gracza o zwyciêstwie oraz wyniku
			removeActionListener(this); 
			KoniecGry koniecGry=new KoniecGry();
		}
	}
}
