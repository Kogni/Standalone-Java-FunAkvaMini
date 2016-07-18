import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Akvariet {
	
	public Object_Akvarier Utstyr_Object_Akvarier;
	public int AkvarieLevel;
	public double AkvarieStoerrelse;
	public double AkvarieVann;
	public double AkvarieEffekt;
	public double AkvariePotensial;
	public double AkvarieFaktor;
	
	public Object_Pumper Utstyr_Object_Pumper;
	public int PumpeLevel;
	public double PumpeKapasitet;
	public double PumpeNaa;
	public double PumpeEffekt;
	public double PumpePotensial;
	public double PumpeFaktor;
	public double SkittMengde;
	public double Vannkvalitet;
	
	public Object_Lys Utstyr_Object_Lys;
	public int LysLevel;
	public double LysStyrke;
	public double LysNaa;
	public double LysEffekt;
	public double LysPotensial;
	public double LysFaktor;
	
	public Object_Varme Utstyr_Object_Varme;
	public int VarmeLevel;
	public double VarmekolbeW;
	public double VarmeNaa;
	public double VarmeEffekt;
	public double VarmePotensial;
	public double VarmeFaktor;
	public double Temperatur;
	public double TargetTemperatur;
	
	public double Alder; //dager
	public double Level = 1;
	private double Temp;
	
	public double LifeBar;
	public double Levelighet;
	
	void Validiate(GUI_Forside Class_GUI_Forside){
		
		//System.out.println("0.0 SkittMengde=" + SkittMengde);
		
		/*
		
		if ( AkvarieStoerrelse < 0){
			AkvarieStoerrelse = 0;
		}
		
		if ( PumpeKapasitet < 0){
			PumpeKapasitet = 0;
		}
		
		if ( LysStyrke < 0){
			LysStyrke = 0;
		}
		
		if ( VarmekolbeW < 0){
			VarmekolbeW = 0;
		}
		*/
		
		//-----------------------
		
		Temp = AkvarieVann;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		AkvarieVann = Temp;
		if ( AkvarieVann < 0){
			AkvarieVann = 0;
			JOptionPane.showMessageDialog(Class_GUI_Forside, "Akvariet ditt tørket helt ut!\n Du er en sadistisk fiskemishandler, alt utstyret ditt er konfiskert!");
			System.exit(0);
		}
		
		Temp = PumpeNaa;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		PumpeNaa = Temp;
		if ( PumpeNaa < 0){
			PumpeNaa = 0;
		}
		
		Temp = LysNaa;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		LysNaa = Temp;
		if ( LysNaa < 0){
			LysNaa = 0;
		}
		
		Temp = VarmeNaa;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		VarmeNaa = Temp;
		if ( VarmeNaa < 0){
			VarmeNaa = 0;
		}
		
		TargetTemperatur = (((VarmeNaa/AkvarieVann)/0.9)*25);
		Temperatur = Temperatur + ((TargetTemperatur-Temperatur)/10);
		
		Temp = Temperatur;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		Temperatur = Temp;
		if ( Temperatur < 0 ){
			Temperatur = 0;
		} else if ( Temperatur > 25 ) {
			Temperatur = 25;
		}
		
		//System.out.println("1 SkittMengde=" + SkittMengde);
		SkittMengde = SkittMengde - PumpeNaa;
		if ( SkittMengde < 0){
			SkittMengde = 0;
		}
		//System.out.println("2 SkittMengde=" + SkittMengde);
		Temp = SkittMengde;
		Temp = ((int)(Math.round(Temp*100))/100);
		SkittMengde = Temp;
		
		//System.out.println("3 SkittMengde=" + SkittMengde);
		
		//---------
		
		
		Temp = AkvarieVann /AkvarieStoerrelse;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		AkvarieEffekt = Temp;
		
		Temp = PumpeNaa /AkvarieVann;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		PumpeEffekt = Temp;
		
		Temp = LysNaa / AkvarieVann;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		LysEffekt = Temp;
		
		Temp = VarmeNaa / AkvarieVann;
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		Temp = Temp / 100;
		VarmeEffekt = Temp;
		
		//-----------------
		
		Temp = (AkvarieVann/AkvarieStoerrelse);
		Temp = Temp*100;
		Temp = (int)(Math.round(Temp));
		//Temp = Temp / 100;
		AkvariePotensial = Temp;
		//System.out.println("AkvarieVann="+AkvarieVann+" AkvarieStoerrelse="+AkvarieStoerrelse+" Temp="+Temp);
		
		Temp = (PumpeNaa/PumpeKapasitet);
		Temp = ((int)(Math.round(Temp*100))/1);
		PumpePotensial = Temp;
		//System.out.println(Temp);
		
		Temp = (LysNaa/LysStyrke);
		Temp = ((int)(Math.round(Temp*100))/1);
		LysPotensial = Temp;
		//System.out.println(Temp);
		
		Temp = (VarmeNaa/VarmekolbeW);
		Temp = ((int)(Math.round(Temp*100))/1);
		VarmePotensial = Temp;
		//System.out.println(Temp);
		
		//-----------------
		
		/*
		Temp = (AkvarieVann/AkvarieStoerrelse);
		Temp = ((int)(Math.round(Temp*100))/1);
		AkvarieFaktor = Temp;
		*/
		AkvarieFaktor = AkvariePotensial;
		
		Temp = 100 - SkittMengde;
		PumpeFaktor = Temp;
		
		Temp = LysEffekt / 0.5;
		Temp = ((int)(Math.round(Temp*100))/1);
		LysFaktor = Temp;
		if ( LysFaktor > 100 ){
			LysFaktor = 100;
		}
		
		Temp = Temperatur / 25;
		Temp = ((int)(Math.round(Temp*100))/1);
		VarmeFaktor = Temp;
		
		Levelighet = 0;
		Levelighet = Levelighet + AkvarieFaktor;
		//System.out.println("A " + AkvarieFaktor);
		Levelighet = Levelighet + PumpeFaktor;
		//System.out.println("P " + PumpeFaktor);
		Levelighet = Levelighet + LysFaktor;
		//System.out.println("L " + LysFaktor);
		Levelighet = Levelighet + VarmeFaktor;
		//System.out.println("V " + VarmeFaktor);
		Levelighet = Levelighet / 4;
		//System.out.println("Levelighet="+Levelighet);
		
		LifeBar = Levelighet-50;
		
	}
	
	public void OppgraderAkvarie(){
		
	}

}
