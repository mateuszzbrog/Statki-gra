package statki;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Pola extends JButton implements ActionListener { //klasa definiuj�ca pola w kt�rcyh przechowywane b�d� informacje czy w danym polu kryje si� statek
	private Image pole=new ImageIcon("rysunki/pole.jpg").getImage();
	private Image pudlo=new ImageIcon("rysunki/pudlo.jpg").getImage();
	private Image trafiony=new ImageIcon("rysunki/trafiony.jpg").getImage();
	private Integer pozycja; //musi by� obiektem, �eby mo�na by�o usun�� z listaStatkow bez podawania indeksu
	private Losowanko maszynaLosujaca=Losowanko.pobierzMaszyneLosujaca();
	private ArrayList<Integer> listaStatkow=maszynaLosujaca.zwrocPozycjeStatkow(); //przekazujemy pozycje statk�w do tablicy
	private static int licznikRuchow=0; //zmienna statyczna, bo jeden licznik dla ca�ej klasy
	
	Pola(Integer pozycja){
		super();
		setIcon(new ImageIcon(pole)); //pole po stworzeniu ma by� niewiadom� - dopiero po klikni�ciu gracz dowiaduje si� czy trafi� w statek
		this.pozycja=pozycja; //zmienna przechowuje numer pola, potrzebny do por�wnania z list� pozycji do kt�rych komputer wylosowa� statki
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(listaStatkow.isEmpty()){ //po trafieniu, pozycja jest usuwana z listy - je�eli lista jest pusta, to gracz ustrzeli� wszystkie statki
		koniecGry();
		} else{
			licznikRuchow++; //po ka�dym klikni�ciu zwi�kszamy licznik ruch�w
			Statki.liczbaWykonanychRuchow.setText("Liczba wykonanych ruch�w: "+zwrocLicznikRuchow());
			if(listaStatkow.contains(pozycja)){ 
				setIcon(new ImageIcon(trafiony)); //je�li klikni�ta pozycja jest na li�cie wylosowanych, to zmiana grafiki na "trafiony"
				listaStatkow.remove(pozycja); //po trafieniu usuwamy statek z listy statk�w
				removeActionListener(this); //wy��czamy aktywno��, �eby licznik nie nabija� kolejnych klikni��
				} else {
					setIcon(new ImageIcon(pudlo));	//je�li klikni�tej pozycji nie ma na li�cie statk�w, to zmiana grafiki na "pud�o"
					removeActionListener(this); //wy��czamy aktywno��, �eby licznik nie nabija� kolejnych klikni��
				}
			} 
		koniecGry(); // po wszystkim sprawdzamy czy gra jest ju� sko�czona
	}
	public static int zwrocLicznikRuchow(){ //metoda zwraca licznik ruch�w
		return licznikRuchow;
	}
	public static void zerujLicznikRuchow(){ //metoda zeruje licznik(wywo�ywana przy rozpoczynaniu nowej gry)
		licznikRuchow=0;
	}
	public void koniecGry(){
		if(listaStatkow.isEmpty()){ //sprawdzamy czy lista statk�w jest pusta
			Statki.liczbaWykonanychRuchow.setText("Wygra�e� w : "+zwrocLicznikRuchow()+" ruchach!"); //informujemy gracza o zwyci�stwie oraz wyniku
			removeActionListener(this); 
			KoniecGry koniecGry=new KoniecGry();
		}
	}
}
