package statki;
import java.util.*;

public class Losowanko {
	private static Losowanko maszynaLosujaca;
	private ArrayList<Integer> listaStatkow=new ArrayList<Integer>(); //tablica b�dzie przechowywa� numery p�l na kt�rych znajduj� si� statki
	private ArrayList<Integer> listaZajetych=new ArrayList<Integer>(); //tablica b�dzie przechowywa� numery p�l, kt�re s�siaduj� ze statkami(potrzebne, aby statki nie styka�y si� ze sob�
	
	private Losowanko(){} 
	public static Losowanko pobierzMaszyneLosujaca(){ //jedna instancja obiektu(jedna maszyna losuj�ca)
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
	
	public int losujPierwszePole(){ //metoda losuj�ca pierwsze pole w danym statku
		int poz1;
		do{
			poz1=(int)(Math.random()*100);
		} while((listaStatkow.contains(poz1))||(listaZajetych.contains(poz1))); //sprawdzamy czy ju� nie zosta�o wylosowane
		listaStatkow.add(poz1);
		return poz1;
	}
	
	public int losujKolejnePola(int poz1){ //metoda losuj�ca kolejne pole w danym statku jako parametr pierwsze pole statku
		int poz2=-1;
		do{
			if(poz1%10==9){ //je�eli pole jest przy prawej kraw�dzi
				if(losujKierunek()<50){
					poz2=poz1-10; //jedno pole w g�r�
					if(poz2<0) poz2=poz1-1; //wyj�tek gdy pole jest w prawym g�rnym rogu
				} else poz2=poz1-1; //jedno pole w lewo
			}
			if(poz1%10==0){ //je�eli pole jest przy lewej kraw�dzi
				if(losujKierunek()<50){ 
					poz2=poz1-10; //jedno pole w g�r�
					if(poz2<0) poz1=poz2+1; //wyj�tek gdy pole jest w lewym g�rnym rogu
				} else poz2=poz1+1; //jedno pole w prawo
			}
			if(poz1<10){ //je�eli pole jest przy g�rnej kraw�dzi
				if(losujKierunek()<50){
						poz2=poz1+1; // jedno pole w prawo
						if(poz2>9) poz2=poz1-1; //wyj�tek prawy g�rny r�g
				} else poz2=poz1+10; //jedno pole w d�
			}
			if(poz1>89){ //je�eli pole jest przy dolnej kraw�dzi
				if(losujKierunek()<50){
						poz2=poz1+1; // jedno pole w prawo
						if(poz2>99) poz2=poz1-1; //wyj�tek prawy dolny r�g
				} else poz2=poz1-10; //jedno pole w g�r�
			}
			if(!((poz1%10==9)||(poz1%10==0)||(poz1<10)||(poz1>89))){ //�adna z kraw�dzi
				if(losujKierunek()<50){ //pion czy poziom
					if(losujKierunek()<50){
						poz2=poz1+1;
					} else poz2=poz1-1;
				} else if(losujKierunek()<50){
					poz2=poz1+10;
				} else poz2=poz1-10;
			}
		} while((listaStatkow.contains(poz2))||(listaZajetych.contains(poz2))); //sprawdzamy czu ju� nie zosta�o wylosowane
		listaStatkow.add(poz2);
		return poz2;
	}
	
	public int losujKierunek(){
		int kierunek=(int)(Math.random()*100);
		return kierunek;
	}
	
	public void wypelnijZajete(int poz){ //metoda wype�niaj�ca pola dooko�a statku
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
	public void wyczyscListy(){ //metoda czyszcz�ca listy(wywo�ywana przed nowym losowaniem)
		listaStatkow.clear();
		listaZajetych.clear();
	}

}
