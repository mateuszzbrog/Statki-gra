package statki;
import java.util.*;

public class Losowanko {
	private static Losowanko maszynaLosujaca;
	private ArrayList<Integer> listaStatkow=new ArrayList<Integer>(); //tablica bêdzie przechowywaæ numery pól na których znajduj¹ siê statki
	private ArrayList<Integer> listaZajetych=new ArrayList<Integer>(); //tablica bêdzie przechowywaæ numery pól, które s¹siaduj¹ ze statkami(potrzebne, aby statki nie styka³y siê ze sob¹
	
	private Losowanko(){} 
	public static Losowanko pobierzMaszyneLosujaca(){ //jedna instancja obiektu(jedna maszyna losuj¹ca)
		if(maszynaLosujaca==null){
			maszynaLosujaca=new Losowanko();
		}
		return maszynaLosujaca;
	}
	
	public void czteromasztowiec(){
		int poz1,poz2,poz3,poz4;
		poz1=losujPierwszePole();
		poz2=losujKolejnePola(poz1);
		poz3=losujKolejnePola(poz2);
		poz4=losujKolejnePola(poz3);
		wypelnijZajete(poz1);
		wypelnijZajete(poz2);
		wypelnijZajete(poz3);
		wypelnijZajete(poz4);
	}
	public void trzymasztowiec(){
		int poz1,poz2,poz3;
		poz1=losujPierwszePole();
		poz2=losujKolejnePola(poz1);
		poz3=losujKolejnePola(poz2);
		wypelnijZajete(poz1);
		wypelnijZajete(poz2);
		wypelnijZajete(poz3);
	}
	public void dwumasztowiec(){
		int poz1,poz2;
		poz1=losujPierwszePole();
		poz2=losujKolejnePola(poz1);
		wypelnijZajete(poz1);
		wypelnijZajete(poz2);
	}
	public void jednomasztowiec(){
		int poz1;
		poz1=losujPierwszePole();
		wypelnijZajete(poz1);
	}
	
	public int losujPierwszePole(){ //metoda losuj¹ca pierwsze pole w danym statku
		int poz1;
		do{
			poz1=(int)(Math.random()*100);
		} while((listaStatkow.contains(poz1))||(listaZajetych.contains(poz1))); //sprawdzamy czy ju¿ nie zosta³o wylosowane
		listaStatkow.add(poz1);
		return poz1;
	}
	
	public int losujKolejnePola(int poz1){ //metoda losuj¹ca kolejne pole w danym statku jako parametr pierwsze pole statku
		int poz2=-1;
		do{
			if(poz1%10==9){ //je¿eli pole jest przy prawej krawêdzi
				if(losujKierunek()<50){
					poz2=poz1-10; //jedno pole w górê
					if(poz2<0) poz2=poz1-1; //wyj¹tek gdy pole jest w prawym górnym rogu
				} else poz2=poz1-1; //jedno pole w lewo
			}
			if(poz1%10==0){ //je¿eli pole jest przy lewej krawêdzi
				if(losujKierunek()<50){ 
					poz2=poz1-10; //jedno pole w górê
					if(poz2<0) poz1=poz2+1; //wyj¹tek gdy pole jest w lewym górnym rogu
				} else poz2=poz1+1; //jedno pole w prawo
			}
			if(poz1<10){ //je¿eli pole jest przy górnej krawêdzi
				if(losujKierunek()<50){
						poz2=poz1+1; // jedno pole w prawo
						if(poz2>9) poz2=poz1-1; //wyj¹tek prawy górny róg
				} else poz2=poz1+10; //jedno pole w dó³
			}
			if(poz1>89){ //je¿eli pole jest przy dolnej krawêdzi
				if(losujKierunek()<50){
						poz2=poz1+1; // jedno pole w prawo
						if(poz2>99) poz2=poz1-1; //wyj¹tek prawy dolny róg
				} else poz2=poz1-10; //jedno pole w górê
			}
			if(!((poz1%10==9)||(poz1%10==0)||(poz1<10)||(poz1>89))){ //¿adna z krawêdzi
				if(losujKierunek()<50){ //pion czy poziom
					if(losujKierunek()<50){
						poz2=poz1+1;
					} else poz2=poz1-1;
				} else if(losujKierunek()<50){
					poz2=poz1+10;
				} else poz2=poz1-10;
			}
		} while((listaStatkow.contains(poz2))||(listaZajetych.contains(poz2))); //sprawdzamy czu ju¿ nie zosta³o wylosowane
		listaStatkow.add(poz2);
		return poz2;
	}
	
	public int losujKierunek(){
		int kierunek=(int)(Math.random()*100);
		return kierunek;
	}
	
	public void wypelnijZajete(int poz){ //metoda wype³niaj¹ca pola dooko³a statku
		listaZajetych.add(poz-11);
		listaZajetych.add(poz-10);
		listaZajetych.add(poz-9);
		listaZajetych.add(poz-1);
		listaZajetych.add(poz+1);
		listaZajetych.add(poz+9);
		listaZajetych.add(poz+10);
		listaZajetych.add(poz+11);
	}
	
	public int zwrocPozycjeStatkow(int a){
		return listaStatkow.get(a);
	}
	public ArrayList<Integer> zwrocPozycjeStatkow(){
		return listaStatkow;
	}
	public void wyczyscListy(){ //metoda czyszcz¹ca listy(wywo³ywana przed nowym losowaniem)
		listaStatkow.clear();
		listaZajetych.clear();
	}

}
