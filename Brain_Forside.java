//import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class Brain_Forside {
	
	public GUI_Forside Class_GUI_Forside;
	public Akvariet Class_Akvariet;
	public DatabaseMaker Class_DatabaseMaker;
	public Fisk_Individer [] Fiskene = new Fisk_Individer[99];
	public int Fisker, Fisker_Voksne, Fisker_Yngel, Penger = 900;
	double Temp;
	int UpdateTeller, DoegnTeller, YngleTeller, MoveTeller;
	//int TimerInterval = 100;
	public boolean RunTimer = true;
	
	public Brain_Forside(Akvariet A, DatabaseMaker B){
		Class_Akvariet = A;
		Class_DatabaseMaker = B;
		//Class_DatabaseMaker.SendArray();
		SettOppGUI();
		UpdateTeller = 0;
		Timeren();
	}
	
	public void SettOppGUI (){
		//System.out.println(Class_Akvariet.AkvarieStoerrelse);
		Class_GUI_Forside = new GUI_Forside(Class_Akvariet, this);
		Class_GUI_Forside.SettOpp();
		//Class_GUI_Forside.setControl(this);
		Class_GUI_Forside.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JOptionPane.showMessageDialog(Class_GUI_Forside, "Velkommen til ditt nye akvarie!\n Du skal nå vise at du klarer å holde et akvarie i live, ved å sørge for gode boforhold for fisk. \n Du må skifte vann, pumpe, kolbe og lysrør med jevne mellomrom for å klare det.");
	}
	
	public void Pause(){
		if ( RunTimer == true ){
			RunTimer = false;
		} else {
			RunTimer = true;
		}
		//System.out.println("1 RunTimer="+RunTimer);
	}
	
	public void Timeren(){
		int delay = 10; //milliseconds
		 
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//System.out.println("evt="+evt.getID());
				int Passed = 0;
				//if ( Passed >= TimerInterval) {
		    	  
				if ( RunTimer == true ){
		    	  
					UpdateTeller++;
					DoegnTeller++;
					YngleTeller++;
					MoveTeller++;
		    	  
					if ( YngleTeller >= 480 ){
						//System.out.println("moo 480");
						YngleTeller = 0;
						Yngling();
					}
		    	  
					if ( DoegnTeller >= 240){
						//System.out.println("moo 240");
						Class_Akvariet.Alder = Class_Akvariet.Alder+1;
						Class_GUI_Forside.Alder.setText(Class_Akvariet.Alder+"");
						DoegnTeller = 0;
		    		  
						for ( int x = 1 ; x <= Fisker ; x++ ){
							if ( Fiskene[x].Helse > 0) {
								Fiskene[x].Alder ++;
							}
						}
					}
					
					if ( UpdateTeller >= 10){
						//System.out.println("moo 10");
						UpdateTekst();
						if (Class_Akvariet.Level >= 2){
							UpdateFish();
						}
						UpdateTeller = 0;
					}

					if ( MoveTeller >= 2 ){
						Class_GUI_Forside.UpdateFish();
						MoveTeller = 0;
					}
					
					NyttLevel(1);
					
				} else {
				}
				//}
			}
		};

		new Timer(delay, taskPerformer).start();
	}
	
	public void NyttLevel(int x){
		if ( x == 0 ){ //force neste level
			
			if ( Class_Akvariet.Level == 1 ){
				Level2();
			} else if (Class_Akvariet.Level == 2){
				Level3();
			} else if (Class_Akvariet.Level == 3){
				Level4();
			}
			
		} else { //sjekk om man kan gå til neste level
			
			if ( Class_Akvariet.Level == 1 ){
				if ( Class_Akvariet.Levelighet >= 50 ){
					if ( Class_Akvariet.Alder >= 1 ){
						Level2();
					}
				} else {
					//System.out.println(Class_Akvariet.Levelighet);
					JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har ikke klart å opprettholde gode leveforhold.\n Prøv igjen om du vil bli akvarist");
					System.exit(0);
				}
			} else {
				if ( (Fisker_Voksne+Fisker_Yngel) >= 1){
					if (Class_Akvariet.Level == 2){
						if ( Class_Akvariet.Alder >= 2){
							Level3();
						}
					} else if (Class_Akvariet.Level == 3){
						if ( (Fisker_Voksne + Fisker_Yngel) >= 10){
							Level4();
						}
					}
				} else {
					JOptionPane.showMessageDialog(Class_GUI_Forside, "All fisk er død!\n Duger du egentlig som akvarist, hm?");
					System.exit(0);
				}
			}
			
		}
	}
	
	public void Level2(){
		JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har holdt gode boforhold i 2 uker!\n Gratulerer, du får nå lov å prøve deg på fiskehold! \n Pass på boforholdene, mat fisken og skift vann ofte. \n Husk at fisken bruker litt tid på å tilpasse seg omgivelsene.");
		Class_Akvariet.Level = 2; //mistenkt
		AddFish(); //mistenkt

		Class_GUI_Forside.Oppgave.setText("Hold fisken i live i 2 uker");
		Class_GUI_Forside.Brett.setText((int)Class_Akvariet.Level+"");
  
		Class_GUI_Forside.MatFisk.show(true);
		Class_GUI_Forside.pack();
	}
	
	public void Level3(){
		  JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har holdt fisken i live i 2 uker!\n Gratulerer, du får nå en ny fisk, og muligheten for yngel! \n Fôring og bytte av pumpe blir nå ekstra viktig.");
		  Class_GUI_Forside.Oppgave.setText("Hold 10 fisker i live samtidig");
		  Class_Akvariet.Level = 3;
		  Class_GUI_Forside.Brett.setText((int)Class_Akvariet.Level+"");
		  AddFish();
  
		  /*
		  Class_GUI_Forside.Fiskenavn[2].show(true);
		  Class_GUI_Forside.FiskeHelse[2].show(true);
		  Class_GUI_Forside.FiskeSult[2].show(true);
		  Class_GUI_Forside.FiskeAntall[2].show(true);
		  */
  
		  Class_GUI_Forside.pack();
	}
	
	public void Level4(){
		  JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har holdt fiskebestanden i live og mangedoblet den!\n Gratulerer, du får nå oppgradere utstyret ditt. \n Å oppgradere utstyr koster penger, og pengene må du skaffe ved å selge de voksnefiskene du dretter opp.");
		  Class_GUI_Forside.Oppgave.setText("Samle opp en kapital på 1'000 kr");
		  Class_Akvariet.Level = 4;
		  Class_GUI_Forside.Brett.setText((int)Class_Akvariet.Level+"");
  
		  Class_GUI_Forside.SelgFisk.show(true);
		  Class_GUI_Forside.NyttAkvarie.show(true);
		  Class_GUI_Forside.NyPumpe.show(true);
		  Class_GUI_Forside.NyttLys.show(true);
		  Class_GUI_Forside.NyKolbe.show(true);
		  Class_GUI_Forside.KapitalTekst.show(true);
		  Class_GUI_Forside.Kapital.show(true);
		  Class_GUI_Forside.NyttAkvarie.setText("Oppgrader akvarie (" + Class_DatabaseMaker.Akvarier[(Class_Akvariet.AkvarieLevel+1)].Pris+")");
		  Class_GUI_Forside.NyPumpe.setText("Oppgrader pumpe (" + Class_DatabaseMaker.Pumper[(Class_Akvariet.PumpeLevel+1)].Pris+")");
		  Class_GUI_Forside.NyttLys.setText("Oppgrader lysrør (" + Class_DatabaseMaker.Lys[(Class_Akvariet.LysLevel+1)].Pris+")");
		  Class_GUI_Forside.NyKolbe.setText("Oppgrader varmekolbe (" + Class_DatabaseMaker.Varme[(Class_Akvariet.VarmeLevel+1)].Pris+")");
		   Class_GUI_Forside.pack();
	}
	
	public void UpdateTekst(){
		  Class_GUI_Forside.Kapital.setText(Penger+"");
		  
		  Class_Akvariet.AkvarieVann = Class_Akvariet.AkvarieVann - 0.1;
		  Class_Akvariet.PumpeNaa = Class_Akvariet.PumpeNaa - 0.3;
		  Class_Akvariet.LysNaa = Class_Akvariet.LysNaa - 0.05;
		  Class_Akvariet.VarmeNaa = Class_Akvariet.VarmeNaa - 0.1;
		  
		  Class_Akvariet.Validiate(Class_GUI_Forside);
		  
		  Class_GUI_Forside.AkvarieVann.setText(Class_Akvariet.AkvarieVann+"");
		  Class_GUI_Forside.PumpeNaa.setText(Class_Akvariet.PumpeNaa+"");
		  Class_GUI_Forside.LysNaa.setText(Class_Akvariet.LysNaa+"");
		  Class_GUI_Forside.VarmeNaa.setText(Class_Akvariet.VarmeNaa+"");
		  
		  Class_GUI_Forside.AkvarieEffekt.setText(Class_Akvariet.AkvarieEffekt+ "");
		  Class_GUI_Forside.PumpeEffekt.setText(" " + Class_Akvariet.PumpeEffekt);
		  Class_GUI_Forside.VarmeEffekt.setText(" " + Class_Akvariet.VarmeEffekt);
		  Class_GUI_Forside.LysEffekt.setText(" " + Class_Akvariet.LysEffekt);
		  
		  
		  Class_GUI_Forside.AkvarieVann2.setText("" + Class_Akvariet.AkvarieVann);
		  Class_GUI_Forside.Vannkvalitet.setText("" + Class_Akvariet.SkittMengde);
		  Class_GUI_Forside.Temperatur.setText("" + Class_Akvariet.Temperatur);
		  Class_GUI_Forside.LysNaa2.setText("" + Class_Akvariet.LysEffekt);
		  
		  //Class_GUI_Forside.Levelighet.setText("" + Class_Akvariet.Levelighet);
		  Class_GUI_Forside.LifeBar.setValue((int)Class_Akvariet.Levelighet);
		  Class_GUI_Forside.LifeBar.setStringPainted(true);
		  Class_GUI_Forside.LifeBar.setString("Boforhold: " + Class_GUI_Forside.LifeBar.getValue()+ " %");
		  //Class_GUI_Forside.LifeBar.setValue((int)Class_Akvariet.LifeBar);
		  
		  Class_GUI_Forside.AkvariePotensial.setText("" + Class_Akvariet.AkvariePotensial);
		  Class_GUI_Forside.PumpePotensial.setText("" + Class_Akvariet.PumpePotensial);
		  Class_GUI_Forside.LysPotensial.setText("" + Class_Akvariet.LysPotensial);
		  Class_GUI_Forside.VarmePotensial.setText("" + Class_Akvariet.VarmePotensial);
	}
	
	public void SkiftKolbe(){
		//Class_Akvariet.VarmekolbeW = 20;
		Class_Akvariet.VarmeNaa = Class_Akvariet.VarmekolbeW;
		//System.out.println("Skiftet kolbe");
	}
	
	public void SkiftLys(){
		//Class_Akvariet.LysStyrke = 10;
		Class_Akvariet.LysNaa = Class_Akvariet.LysStyrke;
		//System.out.println("Skiftet kolbe");
	}
	
	public void FyllAkvarie(){
		//Class_Akvariet.AkvarieStoerrelse = 20;
		Class_Akvariet.AkvarieVann = Class_Akvariet.AkvarieStoerrelse;
	}
	
	public void RensPumpe(){
		//Class_Akvariet.PumpeKapasitet = 60;
		Class_Akvariet.PumpeNaa = Class_Akvariet.PumpeKapasitet;
	}
	
	public void FeedFish(){
		for ( int x = 1 ; x <= Fisker ; x++ ){
			if ( (int)Fiskene[x].Helse > 0) {
				Fiskene[x].Sult = 0;
				//Class_GUI_Forside.FiskeSult[x].setText(Fiskene[x].Sult+"");
			}
		}
	}
	
	public void AddFish(){
		Fisker ++;
		//System.out.println("Adding fish, fish="+Fisker);
		Fiskene[Fisker] = new Fisk_Individer(Class_Akvariet, 0, 0);
		if ( Fisker_Voksne < 2 ){
			Fiskene[Fisker].Alder = Fiskene[Fisker].VoksenAlder;
			Fisker_Voksne ++;
		}
		//System.out.println("Adding fish: " + FoersteFisk);
		Fiskene[Fisker].SettStoerrelse( 30, 10);
		
		Class_GUI_Forside.ShowFish(Fisker);
		
	}
	
	public void SelgFisk(){
		boolean SolgtEnFisk = false;
		//System.out.println("Fisk før salg: " + Fisker);
		//System.out.println("Voksne fisk før salg: " + Fisker_Voksne);
		//System.out.println("Yngel før salg: " + Fisker_Yngel);
		for ( int x = 1 ; x <= Fisker ; x++ ){
			//System.out.println("før salg: Fiskene["+x+"].Helse="+Fiskene[x].Helse+" Fiskene["+x+"].Alder="+Fiskene[x].Alder);
			if ( SolgtEnFisk == false ){
				if ( (int)Fiskene[x].Helse > 0) {
					if ( (int)Fiskene[x].Alder >= Fiskene[x].VoksenAlder ){
						
						SolgtEnFisk = true;
						Fiskene[x].Helse = 0;
						//Fisker_Voksne --;
						//Fisker --;
						Penger = Penger + 50;
						//System.out.println("Fish sold: #"+x);
						
					}
				}
			}
		}
		RyddArray();
		Class_GUI_Forside.Kapital.setText(Penger+"");
		
		int TempFisker = Fisker;
		//Fisker = 0;
		for ( int x = 1 ; x <= TempFisker ; x++ ){
			if ( (int)Fiskene[x].Helse > 0) {
				//System.out.println("Etter salg: Fiskene["+x+"].Helse="+Fiskene[x].Helse);
				if ( (int)Fiskene[x].Alder >= Fiskene[x].VoksenAlder ){ //voksne fisker
					//Fisker_Voksne ++;
					//Fisker ++;
					//System.out.println("Salg: Addet #"+x+" til voksne, alder="+Fiskene[x].Alder+" Helse="+Fiskene[x].Helse);
				} else if ( (int)Fiskene[x].Alder < Fiskene[x].VoksenAlder ){ //voksne fisker
					//Fisker_Yngel ++;
					//Fisker ++;
					//System.out.println("Salg: Addet #"+x+" til yngel, alder="+Fiskene[x].Alder+" Helse="+Fiskene[x].Helse);
				}
			} else {
				//System.out.println("Fiskene["+x+"].Helse="+Fiskene[x].Helse);
			}
		}
		
		UpdateFish();
		
		/*
		//Fisker_Voksne = 0;
		//Fisker_Yngel = 0;
		int TempFisker = Fisker;
		//Fisker = 0;
		//System.out.println("Fisker_Voksne="+Fisker_Voksne);
		for ( int x = 1 ; x <= TempFisker ; x++ ){
			if ( Fiskene[x].Helse > 0) {
				//System.out.println("Etter salg: Fiskene["+x+"].Helse="+Fiskene[x].Helse);
				if ( Fiskene[x].Alder >= Fiskene[x].VoksenAlder ){ //voksne fisker
					//Fisker_Voksne ++;
					//Fisker ++;
					System.out.println("Salg: Addet #"+x+" til voksne, alder="+Fiskene[x].Alder);
				} else if ( Fiskene[x].Alder < Fiskene[x].VoksenAlder ){ //voksne fisker
					//Fisker_Yngel ++;
					//Fisker ++;
					System.out.println("Salg: Addet #"+x+" til yngel, alder="+Fiskene[x].Alder);
				}
			} else {
				//System.out.println("Fiskene["+x+"].Helse="+Fiskene[x].Helse);
			}
		}
		*/
		//System.out.println("Fisk etter salg: " + Fisker);
		//System.out.println("Voksne fisk etter salg: " + Fisker_Voksne);
		//System.out.println("Yngel etter salg: " + Fisker_Yngel);
	}
	
	public void UpdateFish(){
		
		double AvgHelse_Voksen = 0;
		double AvgSult_Voksen = 0;
		double AvgHelse_Yngel = 0;
		double AvgSult_Yngel = 0;
		
		Fisker_Voksne = 0;
		Fisker_Yngel = 0;
		
		//System.out.println("Fisker_Voksne="+Fisker_Voksne);
		for ( int x = 1 ; x <= Fisker ; x++ ){
			if ( (int)Fiskene[x].Helse >= 1) {
				if ( Fiskene[x].Alder >= Fiskene[x].VoksenAlder ){ //voksne fisker
					Fiskene[x].Update(Class_GUI_Forside);
			
					AvgHelse_Voksen = AvgHelse_Voksen + Fiskene[x].Helse;
					AvgSult_Voksen = AvgSult_Voksen + Fiskene[x].Sult;
				
					Fisker_Voksne ++;
					//System.out.println("Update: Addet #"+x+" til voksne, alder="+Fiskene[x].Alder+" Helse="+Fiskene[x].Helse+" Voksen Alder="+Fiskene[x].VoksenAlder);
				} else if ( Fiskene[x].Alder < Fiskene[x].VoksenAlder ){ //yngel
					Fiskene[x].Update(Class_GUI_Forside);
			
					AvgHelse_Yngel = AvgHelse_Yngel + Fiskene[x].Helse;
					AvgSult_Yngel = AvgSult_Yngel + Fiskene[x].Sult;
				
					Fisker_Yngel ++;
					//System.out.println("Update: Addet #"+x+" til yngel, alder="+Fiskene[x].Alder+" Helse="+Fiskene[x].Helse+" Voksen Alder="+Fiskene[x].VoksenAlder);
				}
			}
		}
		//System.out.println("Fisker_Voksne="+Fisker_Voksne+" Fisker_Yngel="+Fisker_Yngel);
		
		Temp = AvgHelse_Voksen / Fisker_Voksne;
		Temp = ((int)(Math.round(Temp*100))/100);
		AvgHelse_Voksen = Temp;
		//System.out.println("AvgHelse_Voksen="+AvgHelse_Voksen);
		Temp = AvgSult_Voksen / Fisker_Voksne;
		Temp = ((int)(Math.round(Temp*100))/100);
		AvgSult_Voksen = Temp;
		
		Temp = AvgHelse_Yngel / Fisker_Yngel;
		Temp = ((int)(Math.round(Temp*100))/100);
		AvgHelse_Yngel = Temp;
		Temp = AvgSult_Yngel / Fisker_Yngel;
		Temp = ((int)(Math.round(Temp*100))/100);
		AvgSult_Yngel = Temp;
		
		Class_GUI_Forside.Fiskenavn[1].setText("Voksne fischk"); //godkjent
		Class_GUI_Forside.FiskeHelse[1].setText(AvgHelse_Voksen+""); //godkjent
		Class_GUI_Forside.FiskeSult[1].setText(AvgSult_Voksen+""); //godkjent
		Class_GUI_Forside.FiskeAntall[1].setText(Fisker_Voksne+""); //godkjent
		
		if (Class_Akvariet.Level >= 3){
			Class_GUI_Forside.Fiskenavn[2].setText("Småtting fischk"); //skyldig
			Class_GUI_Forside.FiskeHelse[2].setText(AvgHelse_Yngel+""); //skyldig
			Class_GUI_Forside.FiskeSult[2].setText(AvgSult_Yngel+""); //skyldig
			Class_GUI_Forside.FiskeAntall[2].setText(Fisker_Yngel+""); //skyldig
		}
		
		RyddArray();
		//Class_GUI_Forside.UpdateFish();
	}
	
	public void Yngling(){
		if ( Fisker_Voksne >= 2 ){ //nok fisk til yngling
			for ( int x = 1 ; x <= (Fisker_Voksne/2) ; x++ ){
				AddFish();
			}
		}
	}
	
	public void RyddArray(){
		int NyFisker = Fisker;
		for ( int x = 1 ; x <= Fisker ; x++ ){
			//System.out.println("RyddArray: Fiskene[x].Helse="+Fiskene[x].Helse+ "("+Fisker+")Alder="+Fiskene[x].Alder);
			if ( Fiskene[x].Helse <= 0) { //fisken er dau og skal fjernes fra array
				//System.out.println("Rydd array: A#"+x+", Alder="+Fiskene[x].Alder+" Helse="+Fiskene[x].Helse);
				//Fisker --;
				for ( int y = x ; y <= Fisker ; y++ ){
					if ( Fiskene[y] != null ){ //en fisk å flytte fremover
						Fiskene[x] = Fiskene[y];
						//System.out.println("Flytter fisk#"+x+" i array, Fisker="+Fisker);
					}
				}
			} else {
				NyFisker = x;
			}
		}
		Fisker = NyFisker;
		//System.out.println("Fisker="+Fisker);
	}
	
	public void NyttAkvarie(){
		int NyttLevel = Class_Akvariet.AkvarieLevel +1;
		int PrisNytt = Class_DatabaseMaker.Akvarier[NyttLevel].Pris;
		if ( Penger >= PrisNytt ){
			Penger = Penger - PrisNytt;
			Class_Akvariet.AkvarieStoerrelse = Class_DatabaseMaker.Akvarier[NyttLevel].Stoerrelse;
			Class_Akvariet.AkvarieVann = Class_Akvariet.AkvarieStoerrelse;
			Class_Akvariet.AkvarieLevel ++;
			
			Class_GUI_Forside.AkvarieStoerrelse.setText(Class_Akvariet.AkvarieStoerrelse+"");
			Class_GUI_Forside.AkvarieVann.setText(Class_Akvariet.AkvarieVann+"");
			Class_GUI_Forside.NyttAkvarie.setText("Oppgrader akvarie (" + Class_DatabaseMaker.Akvarier[(Class_Akvariet.AkvarieLevel+1)].Pris+")");
		} else {
			JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har ikke råd.\n Nytt akvarie koster " + PrisNytt + ", du har bare " + Penger);
		}
	}
	
	public void NyPumpe(){
		int NyttLevel = Class_Akvariet.PumpeLevel +1;
		int PrisNytt = Class_DatabaseMaker.Pumper[NyttLevel].Pris;
		if ( Penger >= PrisNytt ){
			Penger = Penger - PrisNytt;
			Class_Akvariet.PumpeKapasitet = Class_DatabaseMaker.Pumper[NyttLevel].Styrke;
			Class_Akvariet.PumpeNaa = Class_Akvariet.PumpeKapasitet;
			Class_Akvariet.PumpeLevel ++;
			
			Class_GUI_Forside.PumpeKapasitet.setText(Class_Akvariet.PumpeKapasitet+"");
			Class_GUI_Forside.PumpeNaa.setText(Class_Akvariet.PumpeNaa+"");
			Class_GUI_Forside.NyPumpe.setText("Oppgrader pumpe (" + Class_DatabaseMaker.Pumper[(Class_Akvariet.PumpeLevel+1)].Pris+")");
			//System.out.println("Prøver å oppgradere pumpe til " + Class_Akvariet.PumpeKapasitet);
		} else {
			JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har ikke råd.\n Ny pumpe koster " + PrisNytt + ", du har bare " + Penger);
		}
	}
	
	public void NyttLys(){
		int NyttLevel = Class_Akvariet.LysLevel +1;
		int PrisNytt = Class_DatabaseMaker.Lys[NyttLevel].Pris;
		if ( Penger >= PrisNytt ){
			Penger = Penger - PrisNytt;
			Class_Akvariet.LysStyrke = Class_DatabaseMaker.Pumper[NyttLevel].Styrke;
			Class_Akvariet.LysNaa = Class_Akvariet.PumpeKapasitet;
			Class_Akvariet.LysLevel ++;
			
			Class_GUI_Forside.LysStyrke.setText(Class_Akvariet.LysStyrke+"");
			Class_GUI_Forside.LysNaa.setText(Class_Akvariet.LysNaa+"");
			Class_GUI_Forside.NyttLys.setText("Oppgrader lysrør (" + Class_DatabaseMaker.Lys[(Class_Akvariet.LysLevel+1)].Pris+")");
			//System.out.println("Prøver å oppgradere pumpe til " + Class_Akvariet.PumpeKapasitet);
		} else {
			JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har ikke råd.\n Nytt lys koster " + PrisNytt + ", du har bare " + Penger);
		}
	}
	
	public void NyKolbe(){
		int NyttLevel = Class_Akvariet.VarmeLevel +1;
		int PrisNytt = Class_DatabaseMaker.Varme[NyttLevel].Pris;
		if ( Penger >= PrisNytt ){
			Penger = Penger - PrisNytt;
			Class_Akvariet.VarmekolbeW = Class_DatabaseMaker.Pumper[NyttLevel].Styrke;
			Class_Akvariet.VarmeNaa = Class_Akvariet.VarmekolbeW;
			Class_Akvariet.VarmeLevel ++;
			
			Class_GUI_Forside.VarmekolbeW.setText(Class_Akvariet.VarmekolbeW+"");
			Class_GUI_Forside.VarmeNaa.setText(Class_Akvariet.VarmeNaa+"");
			Class_GUI_Forside.NyKolbe.setText("Oppgrader varmekolbe (" + Class_DatabaseMaker.Varme[(Class_Akvariet.VarmeLevel+1)].Pris+")");
			//System.out.println("Prøver å oppgradere pumpe til " + Class_Akvariet.PumpeKapasitet);
		} else {
			JOptionPane.showMessageDialog(Class_GUI_Forside, "Du har ikke råd.\n Ny varmekolbe koster " + PrisNytt + ", du har bare " + Penger);
		}
	}
	
	public void Hastighet(int x){
		//TimerInterval = x;
		//System.out.println("Hastighet satt til "+ TimerInterval);
	}
	
	public void Hjelp(){
		String Tekst = "";
		Tekst = "Forvirret over hva alt betyr?";
		Tekst = Tekst + "\n- Under Utstyr ser du kvaliteten på utstyret da du kjøpte det.";
		Tekst = Tekst + "\n- Under Effekt ser du kvaliteten utstyret ditt har akkurat nå, etter at tidens tann har gjort sitt.";
		Tekst = Tekst + "\n- Under Utnytte ser du utstyrets kvalitet i forhold til akvariet. Det forteller deg om utstyret er kraftig nok.";
		Tekst = Tekst + "\n- Under Boforhold ser du den aller viktigste informasjonen. Hvor mange liter akvariet rommer, hvor varmt det er, hvor lyst det er og hvor god vannkvaliteten er.";
		Tekst = Tekst + "\n";
		Tekst = Tekst + "\n- Akvarie";
		Tekst = Tekst + "\n Til høyre for Akvarie ser du størrelsen(liter) på akvariet, og hvilken status akvariet er i.";
		Tekst = Tekst + "\n Akvariet bør generelt romme så mange liter som mulig. Fisken klager aldri på for stor plass, men for liten plass er skadelig.";

		Tekst = Tekst + "\n- Pumpe";
		Tekst = Tekst + "\n Til høyre for Pumpe ser du kvaliteten på pumpen, og hvilken status den er i. Pumpen bør kunne rense 3x så mange liter akvariet rommer!";
		Tekst = Tekst + "\n Pumpen bør klare å holde drittmengden lik 0. Med en gang mengden overstiger 0, er faren stor for at det hoper seg opp til det dreper all fisk.";

		Tekst = Tekst + "\n- Lysrør";
		Tekst = Tekst + "\n Til høyre for Lysrør ser du kvaliteten på lysrørene, og hvilken status de er i. Rørene bør kunne lyse opp akvariet med 0,5w per liter!";
		
		Tekst = Tekst + "\n- Varmekolbe";
		Tekst = Tekst + "\n Til høyre for Varmekolbe ser du kvaliteten på kolben, og hvilken status den er i. Kolben bør kunne varme akvariet med 1w per liter!";
		Tekst = Tekst + "\n Varmekolben bør klare å holde temperaturen på 25 grader. Den kan være noen grader over eller under, men 25 er det beste.";
		
		Tekst = Tekst + "\n";
		Tekst = Tekst + "\n- Oppgradering av utstyr";
		Tekst = Tekst + "\n Etterhvert i spillet får du mulighet til å oppgradere utstyret ditt. Da er det viktig å huske at kraften på utstyret må matche hverandre.";
		Tekst = Tekst + "\n Hvis du oppgraderer akvariet og ikke resten, så risikerer du at det andre utstyret blir for svakt og boforholdene dårligere.";
		Tekst = Tekst + "\n Som regel er det derfor bedre å oppgradre akvariet til sist hvis du ikke har råd til å oppgradere alt samtidig.";
		
		Tekst = Tekst + "\n";
		Tekst = Tekst + "\n- Fisker";
		Tekst = Tekst + "\n Etterhvert får du mulighet til å avle opp mange fisker samtidig, og da trenger du en oversikt over hvor godt de trives.";
		Tekst = Tekst + "\n Helse% forteller deg den gjennomsnittelige helsen til fiskene. Noen fisker kan mistrives mens andre trives, spesielt blandt yngel.";
		Tekst = Tekst + "\n Yngel er veldig sårbare, og trenger optimale boforhold for å overleve. Jo yngre de er, jo mer sårbare.";
		
		JOptionPane.showMessageDialog(Class_GUI_Forside, Tekst);
	}
	
}
