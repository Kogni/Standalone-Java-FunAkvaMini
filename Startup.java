import javax.swing.JOptionPane;


public class Startup {
	
	public static void main(String[] args) {
		
		DatabaseMaker Class_DatabaseMaker = new DatabaseMaker();
		Class_DatabaseMaker.LagAkvarier();
		Class_DatabaseMaker.LagPumper();
		Class_DatabaseMaker.LagLys();
		Class_DatabaseMaker.LagVarme();
		
		//System.out.println("hmz1");
		Akvariet Class_Akvariet = new Akvariet();
		
		/*
		Class_Akvariet.Utstyr_Object_Akvarier = Class_DatabaseMaker.Akvarier[1];
		Class_Akvariet.AkvarieStoerrelse = Class_Akvariet.Utstyr_Object_Akvarier.Stoerrelse;
		Class_Akvariet.AkvarieVann = Class_Akvariet.AkvarieStoerrelse;
		*/
		Class_Akvariet.AkvarieLevel = 1;
		Class_Akvariet.Utstyr_Object_Akvarier = Class_DatabaseMaker.Akvarier[Class_Akvariet.AkvarieLevel];
		Class_Akvariet.AkvarieStoerrelse = Class_DatabaseMaker.Akvarier[Class_Akvariet.AkvarieLevel].Stoerrelse;
		Class_Akvariet.AkvarieVann = Class_Akvariet.AkvarieStoerrelse;
		
		Class_Akvariet.PumpeLevel = 1;
		Class_Akvariet.Utstyr_Object_Pumper = Class_DatabaseMaker.Pumper[Class_Akvariet.PumpeLevel];
		Class_Akvariet.PumpeKapasitet = Class_DatabaseMaker.Pumper[Class_Akvariet.PumpeLevel].Styrke;
		Class_Akvariet.PumpeNaa = Class_Akvariet.PumpeKapasitet;
		
		Class_Akvariet.LysLevel = 1;
		//System.out.println("LysLevel="+Class_Akvariet.LysLevel);
		Class_Akvariet.Utstyr_Object_Lys = Class_DatabaseMaker.Lys[Class_Akvariet.LysLevel];
		Class_Akvariet.LysStyrke = Class_DatabaseMaker.Lys[Class_Akvariet.LysLevel].Styrke;
		Class_Akvariet.LysNaa = Class_Akvariet.LysStyrke;
		
		Class_Akvariet.VarmeLevel = 1;
		Class_Akvariet.Utstyr_Object_Varme = Class_DatabaseMaker.Varme[Class_Akvariet.VarmeLevel];
		Class_Akvariet.VarmekolbeW = Class_DatabaseMaker.Varme[Class_Akvariet.VarmeLevel].Styrke;
		Class_Akvariet.VarmeNaa = Class_Akvariet.VarmekolbeW;
		
		Class_Akvariet.Temperatur = 25;
		Class_Akvariet.Levelighet = 100;
		Class_Akvariet.Alder = 0;
		
		//System.out.println(Class_Akvariet.AkvarieStoerrelse);
		Brain_Forside Class_Brain_Forside = new Brain_Forside(Class_Akvariet, Class_DatabaseMaker);
		
	}

}
