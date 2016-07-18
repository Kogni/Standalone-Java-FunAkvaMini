
public class DatabaseMaker {
	
	public Object_Akvarier [] Akvarier = new Object_Akvarier[99];
	public Object_Pumper [] Pumper = new Object_Pumper[99];
	public Object_Lys [] Lys = new Object_Lys[99];
	public Object_Varme [] Varme = new Object_Varme[99];

	public void LagAkvarier(){
		int Levelet;
		int Stoerrelsen;
		int Prisen;
		for ( int x = 1 ; x <= 10 ; x++ ){
			Levelet = x;
			Stoerrelsen = x*20;
			Prisen = Stoerrelsen*2;
			Akvarier[x] = new Object_Akvarier(Levelet, Stoerrelsen, Prisen);
		}
	}
	
	public void LagPumper(){
		int Levelet;
		int Styrken;
		int Prisen;
		for ( int x = 1 ; x <= 20 ; x++ ){
			Levelet = x;
			Styrken = x*30;
			Prisen = Styrken*2;
			Pumper[x] = new Object_Pumper(Levelet, Styrken, Prisen);
		}
	}
	
	public void LagLys(){
		int Levelet;
		int Styrken;
		int Prisen;
		for ( int x = 1 ; x <= 20 ; x++ ){
			Levelet = x;
			Styrken = x*30;
			Prisen = Styrken*3;
			Lys[x] = new Object_Lys(Levelet, Styrken, Prisen);
		}
	}
	
	public void LagVarme(){
		int Levelet;
		int Styrken;
		int Prisen;
		for ( int x = 1 ; x <= 20 ; x++ ){
			Levelet = x;
			Styrken = x*30;
			Prisen = Styrken*3;
			Varme[x] = new Object_Varme(Levelet, Styrken, Prisen);
		}
	}

}
