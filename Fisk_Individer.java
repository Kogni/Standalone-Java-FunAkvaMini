import javax.swing.JOptionPane;
import java.awt.Color;

public class Fisk_Individer {

	public double Helse = 100;
	public double TargetHelse = 100;
	public double Sult = 0;
	public double Alder = 0;
	private Akvariet Class_Akvariet;
	
	//Grafikk
	private int AkvarieVenstre = 0;
	private int AkvarieHoeyre = 0;
	private int AkvarieTopp = 0;
	private int AkvarieBunn = 0;
	private int moveX = 10;
	private int moveY = 10;
	public int Location_x = 0;
	public int Location_y = 0;  
	public int FiskLengde = 1;
	public int FiskHoeyde = 1;
	
	public Color Fiskefarge;
	public int VoksenLengde;
	public int VoksenHoeyde;
	public int BabyLengde;
	public int BabyHoeyde;
	public int VoksenAlder;
	
	public Fisk_Individer(Akvariet A, int x, int y){
		Class_Akvariet = A;
		this.Location_x = x;
		this.Location_y = y;
		VoksenLengde = 30;
		BabyLengde =3;
		VoksenHoeyde = 10;
		BabyHoeyde = 1;
		VoksenAlder = 40;
	}
	
	public void Update(GUI_Forside Class_GUI_Forside){
		
		TargetHelse = Class_Akvariet.Levelighet;
		TargetHelse = TargetHelse*0.8  ;
		//System.out.println("TargetHelse="+TargetHelse+ " ((100-Sult)/5)="+((100-Sult)/5));
		TargetHelse = TargetHelse + ((100-Sult)/5);
		//System.out.println("TargetHelse="+TargetHelse);
		if ( TargetHelse > 100 ){
			TargetHelse = 100;
		}
		
		if ( Alder >= VoksenAlder){
			Class_Akvariet.SkittMengde = Class_Akvariet.SkittMengde + 8;
		} else {
			Class_Akvariet.SkittMengde = Class_Akvariet.SkittMengde + Alder * 0.2;
			TargetHelse = (TargetHelse-(40-Alder))*(100/(100-(40-Alder)));
		}
		
		if ( TargetHelse > 0 ){
			Helse = Helse + ((TargetHelse-Helse)/24);
		} else { //forholdene er så dårlige at det blir instagib
			Helse = TargetHelse;
		}
		
		if ( Helse > 100 ){
			Helse = 100;
		}
		
		Sult ++;
		
		if ( Helse < 0){
			Helse = 0;
		}
		
		SettFarge();
		
		//VoksenLengde = 30;
		//VoksenHoeyde = 10;
		
		if ( Alder < VoksenAlder ){
			//FiskLengde = (int)(VoksenLengde * (Alder/VoksenAlder));
			//FiskHoeyde = (int)(VoksenHoeyde * (Alder/VoksenAlder));
			FiskLengde = (((VoksenLengde-BabyLengde)/(int)VoksenAlder)*(int)Alder)+BabyLengde;
			FiskHoeyde = (((VoksenHoeyde-BabyHoeyde)/(int)VoksenAlder)*(int)Alder)+BabyHoeyde;
			//System.out.println("Setter størrelse til "+FiskLengde+"-"+FiskHoeyde);
		}

	}
	
	public void Flytt(){
		//System.out.println("Akvarie - SettBevegelsesOmraade="+AkvarieHoeyre+"x"+AkvarieBunn);
		
		//bestem flytt eller ei
		int Move = (int)Math.floor(Math.random()*10+(-1));
		if ( Move < 0 ) { //flytt x
			moveX = (int)Math.floor(Math.random()*3+(-1));
			Location_x = Location_x + moveX;
		} else {
			Location_x = Location_x + moveX;
		}
		
		Move = (int)Math.floor(Math.random()*10+(-1));
		if ( Move < 0 ) { //flytt y
			moveY = (int)Math.floor(Math.random()*3+(-1));
			Location_y = Location_y + moveY;
		} else {
			Location_y = Location_y + moveY;
		}
		
		//Ikke svøm ut av akvarieveggen
		if ( (Location_x+FiskLengde) > AkvarieHoeyre ) { //utenfor høyre side
			Location_x = (AkvarieHoeyre-FiskLengde);
		} else if ( Location_x < AkvarieVenstre ) { //utenfor høyre side
			Location_x = AkvarieVenstre;
		}

		if ( (Location_y+FiskHoeyde) > AkvarieBunn ) { //utenfor høyre side
			Location_y = (AkvarieBunn-FiskHoeyde);
		} else if ( Location_y < AkvarieTopp ) { //utenfor høyre side
			Location_y = AkvarieTopp;
		}	
	}
	
	public void SettFarge(){
		
		if ( Helse >= 90 ){
			Fiskefarge = Color.green;
		} else if ( Helse >= 50 ){
			Fiskefarge = Color.yellow;
		} else if ( Helse >= 25 ){
			Fiskefarge = Color.orange;
		} else if ( Helse >= 0 ){
			Fiskefarge = Color.red;
		}
		
		
	}
	
	public void SettBevegelsesOmraade(int Left, int Right, int Top, int Bottom){
		AkvarieVenstre = Left;
		AkvarieHoeyre = Right;
		AkvarieTopp  = Top;
		AkvarieBunn = Bottom;
		//System.out.println("Akvarie startup - SettBevegelsesOmraade="+AkvarieHoeyre+"x"+AkvarieBunn);
	}
	
	public void SettStoerrelse(int width, int height){
		this.FiskLengde = width;
		this.FiskHoeyde = height;
	}
	
}
