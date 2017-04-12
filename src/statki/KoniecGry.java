package statki;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class KoniecGry extends JDialog {
	private JLabel imieTekst;
	private JTextField imie; //pole do wpisania nazwy gracz
	private JLabel wynikGracza;
	private JButton ok;
	private int wynik;
	
	public KoniecGry() {
		wynik=Pola.zwrocLicznikRuchow();
		setTitle("Zwyciêstwo!");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(270, 160);
		setModal(true);
		setLocation(new Point(310, 230));
		setResizable(false);
		
		imieTekst = new JLabel("Podaj imiê:");
		imieTekst.setBounds(45, 20, 65, 20);
		
		imie = new JTextField("");
		imie.setBounds(115, 20, 100, 20);
		
		wynikGracza = new JLabel("Twój wynik: " + Pola.zwrocLicznikRuchow());
		wynikGracza.setBounds(80, 50, 110, 20);
				
		ok = new JButton("OK");
		ok.setBounds(110, 85, 60, 40);
		ok.setFocusable(false);
		ok.addActionListener(new Koniec());
		
		add(ok);
		add(imieTekst);
		add(imie);
		add(wynikGracza);
		
		setVisible(true);
	}
	
	public void zapiszWynik() { //metoda do zapisywania wyniku w pliku
		ArrayList<String> gracz = new ArrayList<String>(); //dwie listy przechowuj¹ce imie gracza i wynik
		ArrayList<String> punkty = new ArrayList<String>();
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("rysunki/plik.txt")); //plik z wynikami
			String pom = "";
			
			while ((pom = br.readLine()) != null) { //je¿eli linia nie jest pusta
				StringTokenizer st = new StringTokenizer(pom); //szuka bia³cyh znaków
								
				while (st.hasMoreTokens()) {
					String pom2 = st.nextToken();
					if (pom2.equals(";")) { //je¿eli napotka na cudzys³ów, to kolejny ci¹g znaków dodaje do tabeli z punktami
						punkty.add(st.nextToken());
					}	
					else {
						gracz.add(pom2); //je¿eli nie, to kolejny ci¹g dodawany jest do tabeli z graczami	
					}	
				}
			}
			br.close();
		}
		catch (IOException ex) {
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("rysunki/plik.txt"));
			boolean bool = false;
			
			int i = 0;
			for (int j = 0; j < 10; j++) {
				if ((!bool) && (wynik < Integer.parseInt(punkty.get(i)))) { //je¿eli wynik gracza jest mniejszy(czyli lepszy) od aktualnie rozwa¿anego to wpisuje jego imie oraz wynik
					bw.write(imie.getText() + " ; " + wynik);
					bool = true; //potrzebne by wynik by³ wpisywany tylko raz
				}
				else {
					bw.write(gracz.get(i) + " ; " + punkty.get(i));
					++i;
				}
				bw.newLine();
			}
			
			bw.close();		
		}
		catch (IOException ex) {
		}
	}
	
	private class Koniec implements ActionListener { //
		public void actionPerformed(ActionEvent e) {
			if (!(imie.getText().equals(""))) {
				if (Character.isLetterOrDigit((imie.getText()).charAt(0))) { //je¿eli gracz wpisuje "dziwne" znaki, to imie zmienione na Anonim
					zapiszWynik();
					dispose();
				}
				else {
					imie.setText("Anonim");
				}
			}
		}
	}
}