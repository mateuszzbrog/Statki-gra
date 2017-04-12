package statki;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;


class OknoWyniki extends JDialog {
	
	private JTextArea pole;
	private String tekst = "";
	private JButton ok;
	
	public OknoWyniki() {
		setTitle("Wyniki");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(280, 260);
		setResizable(false);
		setModal(true);
		setLocation(new Point(310, 200));
		
		pole = new JTextArea();
		pole.setBounds(70, 15, 160, 170);
		pole.setEditable(false);
		pole.setBackground(new Color(238,238,238));
		pole.setFont(new Font("Verdana", Font.BOLD, 12));
		
		ok = new JButton("OK");
		ok.setBounds(115, 190, 50, 30);
		ok.setFocusable(false);
		ok.setMargin(new Insets(1,1,1,1));
		ok.addActionListener(new Close());
		
		wyswietlWyniki();
		
		add(pole);
		add(ok);
		
		setVisible(true);
	}
	
	public void wyswietlWyniki() {
		try {
			
			FileReader fr = new FileReader("rysunki/plik.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String pom = "";
			int i = 1;
			while ((pom = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(pom);
				tekst += String.valueOf(i) + ". ";
				
				while (st.hasMoreTokens()) {
					String temp = st.nextToken();
					if (temp.equals(";"))
						tekst += " -  " + st.nextToken() + "\n";
					else
						tekst += temp + " ";
				}
				++i;
			}
			
			br.close();
		}	
		catch(FileNotFoundException ex) {
			pole.setText("Nie znaleziono pliku !!!");
		}
		catch (IOException ex) {
			pole.setText("Wystapil blad odczytu pliku");
		}
		pole.setText(tekst);
	}
	
	private class Close implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}