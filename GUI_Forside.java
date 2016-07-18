import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;

import javax.swing.*;
import java.awt.*;
import java.io.*; 
import java.util.Vector;
import java.awt.event.*;

import java.text.AttributedString;
import java.awt.font.TextAttribute;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.BoxLayout;

public class GUI_Forside extends JFrame implements ActionListener, MouseMotionListener {
	private Color[] layerColors = { Color.yellow, Color.magenta, Color.cyan,   Color.red, Color.green };
	JPanel pane = new JPanel();
	
	private Brain_Forside myControl;
	private JLabel Tekst1;
	
	public JLabel AkvarieStoerrelse;
	public JLabel AkvarieVann;
	public JLabel AkvarieVann2;
	public JLabel AkvarieEffekt;
	public JLabel AkvariePotensial;
	public JLabel PumpeKapasitet;
	public JLabel PumpeNaa;
	public JLabel PumpeEffekt;
	public JLabel PumpePotensial;
	public JLabel Vannkvalitet;
	public JLabel LysStyrke;
	public JLabel LysNaa;
	public JLabel LysNaa2;
	public JLabel LysEffekt;
	public JLabel LysPotensial;
	public JLabel VarmekolbeW;
	public JLabel VarmeNaa;
	public JLabel VarmeEffekt;
	public JLabel VarmePotensial;
	public JLabel Temperatur;
	public JLabel Levelighet;
	public JLabel Alder;
	public JLabel Brett;
	public JLabel Oppgave;
	public JLabel KapitalTekst;
	public JLabel Kapital;
	public JButton FyllAkvarie;
	public JButton NyttAkvarie;
	public JButton RensPumpe;
	public JButton NyPumpe;
	public JButton SkiftLys;
	public JButton NyttLys;
	public JButton SkiftKolbe;
	public JButton NyKolbe;
	public JButton MatFisk;
	public JButton SelgFisk;
	public JButton Hjelp;
	public JProgressBar LifeBar;
	public JLabel[] Fiskenavn = new JLabel[99];
	public JLabel[] FiskeHelse = new JLabel[99];
	public JLabel[] FiskeSult = new JLabel[99];
	public JLabel[] FiskeAntall = new JLabel[99];
	GridBagConstraints c = new GridBagConstraints();
	
	//---Menu
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	String[ ] MenuItems = new String[ ] { "Pause", "Exit" };
	String[ ] HoppLevler = new String[ ] { "1", "2", "3", "4", "5" };
	JMenuItem[] HoppKnapper = new JMenuItem[HoppLevler.length];
	JMenuItem NesteLevelKnapp;
	String[ ] Hastigheter = new String[ ] { "1000", "100", "10", "1" };
	JMenuItem[] HastighetsKnapper = new JMenuItem[Hastigheter.length];
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem, Pauseknapp;
	
	MenuListener listener;
	
	//---AKVARIE
	
	private BufferedImage TotalBilde = null;
	private Canvas Vann, Luft = null;
	public Color BallColor = Color.black;
	public Color Water = Color.cyan;
	//public Ball2[] Fisk = new Ball2[10];
	public int AkvarieLengde, AkvarieHoeyde;
	public int VannTeller = 0;
	public JPanel MainPanel = new JPanel();
	public Dimension TegningsStoerrelse = new Dimension(0,0);
	
	
	//----
	
	private Akvariet Class_Akvariet;
	double Moo;
	
	public GUI_Forside (Akvariet A, Brain_Forside Brain_Forside){
	    super( "AkvaMini" );
	    Class_Akvariet = A;
	    myControl = Brain_Forside;
	    
	    //---- MENU

	      JMenu fileMenu = new JMenu("File");
	      fileMenu.addMenuListener(listener);
	      fileMenu.add(new JMenuItem("Open"));
	      fileMenu.add(new JMenuItem("Close"));
	      fileMenu.add(new JMenuItem("Exit"));
	      JMenu helpMenu = new JMenu("Help");
	      helpMenu.addMenuListener(listener);
	      helpMenu.add(new JMenuItem("About MenuTest"));
	      helpMenu.add(new JMenuItem("Class Hierarchy"));
	      helpMenu.addSeparator();
	      helpMenu.add(new JCheckBoxMenuItem("Balloon Help"));
	      JMenu subMenu = new JMenu("Categories");
	      subMenu.addMenuListener(listener);
	      JRadioButtonMenuItem rb;
	      ButtonGroup group = new ButtonGroup();
	      subMenu.add(rb = new JRadioButtonMenuItem("A Little Help", true));
	      group.add(rb);
	      subMenu.add(rb = new JRadioButtonMenuItem("A Lot of Help"));
	      group.add(rb);
	      helpMenu.add(subMenu);
	      JMenuBar mb = new JMenuBar();
	      mb.add(fileMenu);
	      mb.add(helpMenu);
	      setJMenuBar(mb);
	      
	      //AKVARIE
	      
	      /*
	      if ( myControl == null) {
	    	  System.out.println("myControl = null");
	      } else  if ( myControl.Class_Akvariet == null ){
	    	  System.out.println("myControl.Class_Akvariet == null");
	      } else if ( myControl.Class_Akvariet.AkvarieStoerrelse == 0 ){
	    	  System.out.println("myControl.Class_Akvariet.AkvarieStoerrelse == null");
	      }
	      */
	      
	      //System.out.println("myControl.Class_Akvariet.AkvarieStoerrelse="+myControl.Class_Akvariet.AkvarieStoerrelse);
	      AkvarieLengde = (int)( myControl.Class_Akvariet.AkvarieStoerrelse*(1*3));
	      AkvarieHoeyde = (int)( myControl.Class_Akvariet.AkvarieStoerrelse*(2*3)/3 );
	      TegningsStoerrelse.setSize(AkvarieLengde, AkvarieHoeyde);
	      
	}
	
    private JPanel createControlPanel() {

        JPanel controls = new JPanel();
        return controls;
    }
	
	private JLabel createColoredLabel(String text, Color color, Point origin) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(origin.x, origin.y, 140, 140);
        return label;
    }
	
	public void setControl(Brain_Forside Brain_Forside){
		myControl = Brain_Forside;
	}
	
	public void SettOpp(){
		LagGUI();
		//System.out.println("Moo2");
	}
	
	public void LagGUI(){
		//System.out.println("Moo3");
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		Tekst1 = new JLabel("Temp");
		Font UnBold = Tekst1.getFont();
		UnBold = UnBold.deriveFont(UnBold.getStyle() ^ Font.BOLD);
		
		//---MAIN
		
		Tekst1 = new JLabel("Alder: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		//c.gridx = 1;
		//c.gridy = 2;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Alder = new JLabel("0");
		Alder.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(Alder, c);
		
		Tekst1 = new JLabel("Brett: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Brett = new JLabel("1");
		Brett.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(Brett, c);
		
		KapitalTekst = new JLabel("Kapital: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(KapitalTekst, c);
		KapitalTekst.show(false);
		
		Kapital = new JLabel("0");
		Kapital.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(Kapital, c);
		Kapital.show(false);
		
		Tekst1 = new JLabel("Utstyr: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel("Effekt: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel("Utnytte: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel("Boforhold: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel("Potensial: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		Tekst1.show(false);
		
		Tekst1 = new JLabel("Oppgave: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Oppgave = new JLabel("Oppretthold boforhold over 50% i 14 dager");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 10;
		c.gridwidth = 5;
		pane.add(Oppgave, c);
		
		Hjelp = new JButton("Hjelp");
		Hjelp.setFont(UnBold);
		Hjelp.setActionCommand("Hjelp");
		Hjelp.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(Hjelp, c);
		
		//---------------
		
		Tekst1 = new JLabel("Akvarie: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		AkvarieStoerrelse = new JLabel(""+Class_Akvariet.AkvarieStoerrelse);
		AkvarieStoerrelse.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(AkvarieStoerrelse, c);
		
		AkvarieVann = new JLabel(""+Class_Akvariet.AkvarieVann);
		AkvarieVann.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(AkvarieVann, c);
		
		AkvarieEffekt = new JLabel(" "+(Class_Akvariet.AkvarieVann/Class_Akvariet.AkvarieStoerrelse));
		AkvarieEffekt.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(AkvarieEffekt, c);
		
		AkvarieVann2 = new JLabel(""+Class_Akvariet.AkvarieVann);
		//AkvarieVann2.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(AkvarieVann2, c);
		
		Tekst1 = new JLabel(" Liter");
		//Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		AkvariePotensial = new JLabel("");
		AkvariePotensial.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(AkvariePotensial, c);
		AkvariePotensial.show(false);
		
		FyllAkvarie = new JButton("Fyll på vann");
		FyllAkvarie.setFont(UnBold);
		FyllAkvarie.setActionCommand("Fyll på vann");
		FyllAkvarie.addActionListener(this);
	    c.gridx = 8;
		c.gridy = 2;
		c.gridwidth = 1;
	    pane.add(FyllAkvarie, c);
	    
	    NyttAkvarie = new JButton("Oppgrader akvarie (" + ((Class_Akvariet.AkvarieStoerrelse+20)*10)+ ")");
	    NyttAkvarie.setFont(UnBold);
	    NyttAkvarie.setActionCommand("Oppgrader akvarie");
	    NyttAkvarie.addActionListener(this);
	    c.gridx = 9;
		c.gridy = 2;
		c.gridwidth = 1;
	    pane.add(NyttAkvarie, c);
	    NyttAkvarie.show(false);
		
		//---------------
		
		Tekst1 = new JLabel("Pumpe: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		PumpeKapasitet = new JLabel(""+Class_Akvariet.PumpeNaa);
		PumpeKapasitet.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(PumpeKapasitet, c);
		
		PumpeNaa = new JLabel(""+Class_Akvariet.PumpeKapasitet);
		PumpeNaa.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(PumpeNaa, c);
		
		PumpeEffekt = new JLabel(" " + (Class_Akvariet.PumpeNaa/Class_Akvariet.PumpeKapasitet));
		PumpeEffekt.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(PumpeEffekt, c);
		
		Vannkvalitet = new JLabel(" " + Class_Akvariet.Vannkvalitet);
		//Vannkvalitet.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(Vannkvalitet, c);
		
		Tekst1 = new JLabel("Dritt/L");
		//Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		PumpePotensial = new JLabel("");
		PumpePotensial.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(PumpePotensial, c);
		PumpePotensial.show(false);
		
		RensPumpe =  new JButton("Rens pumpe");
		RensPumpe.setFont(UnBold);
		RensPumpe.setActionCommand("Rens pumpe");
		RensPumpe.addActionListener(this);
	    c.gridx = 8;
		c.gridy = 3;
		c.gridwidth = 1;
	    pane.add(RensPumpe, c);
	    
	    NyPumpe = new JButton("Oppgrader pumpe");
	    NyPumpe.setFont(UnBold);
	    NyPumpe.setActionCommand("Oppgrader pumpe");
	    NyPumpe.addActionListener(this);
	    c.gridx = 9;
		c.gridy = 3;
		c.gridwidth = 1;
	    pane.add(NyPumpe, c);
	    NyPumpe.show(false);
		
		//---------------
		
		Tekst1 = new JLabel("Lysrør: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		LysStyrke = new JLabel(""+Class_Akvariet.LysStyrke);
		LysStyrke.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(LysStyrke, c);
		
		LysNaa = new JLabel(""+Class_Akvariet.LysNaa);
		LysNaa.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(LysNaa, c);
		
		LysEffekt = new JLabel(" " + (Class_Akvariet.LysStyrke/Class_Akvariet.LysNaa));
		LysEffekt.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(LysEffekt, c);
		
		LysNaa2 = new JLabel(" " + (Class_Akvariet.LysStyrke/Class_Akvariet.LysNaa));
		//LysNaa2.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(LysNaa2, c);
		
		Tekst1 = new JLabel("ng/l");
		//Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		LysPotensial = new JLabel("");
		LysPotensial.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(LysPotensial, c);
		LysPotensial.show(false);
		
		SkiftLys =  new JButton("Bytt lysrør");
		SkiftLys.setFont(UnBold);
		SkiftLys.setActionCommand("Bytt lysrør");
		SkiftLys.addActionListener(this);
	    c.gridx = 8;
		c.gridy = 4;
		c.gridwidth = 1;
	    pane.add(SkiftLys, c);
	    
	    NyttLys = new JButton("Oppgrader lysrør");
	    NyttLys.setFont(UnBold);
	    NyttLys.setActionCommand("Oppgrader lysrør");
	    NyttLys.addActionListener(this);
	    c.gridx = 9;
		c.gridy = 4;
		c.gridwidth = 1;
	    pane.add(NyttLys, c);
	    NyttLys.show(false);
		
		//---------------
		
		Tekst1 = new JLabel("Varmekolbe: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		VarmekolbeW = new JLabel(""+Class_Akvariet.VarmekolbeW);
		VarmekolbeW.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(VarmekolbeW, c);
		
		VarmeNaa = new JLabel(""+Class_Akvariet.VarmeNaa);
		VarmeNaa.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(VarmeNaa, c);
		
		VarmeEffekt = new JLabel(" " + (Class_Akvariet.VarmekolbeW/Class_Akvariet.VarmeNaa));
		VarmeEffekt.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(VarmeEffekt, c);
		
		Temperatur = new JLabel(" " + Class_Akvariet.Temperatur);
		//Temperatur.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(Temperatur, c);
		
		Tekst1 = new JLabel(" grader");
		//Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		VarmePotensial = new JLabel("");
		VarmePotensial.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(VarmePotensial, c);
		VarmePotensial.show(false);
		
		SkiftKolbe =  new JButton("Bytt kolbe");
		SkiftKolbe.setFont(UnBold);
		SkiftKolbe.setActionCommand("Bytt kolbe");
		SkiftKolbe.addActionListener(this);
	    c.gridx = 8;
		c.gridy = 5;
		c.gridwidth = 1;
	    pane.add(SkiftKolbe, c);
	    
	    NyKolbe = new JButton("Oppgrader varmekolbe");
	    NyKolbe.setFont(UnBold);
	    NyKolbe.setActionCommand("Oppgrader varmekolbe");
	    NyKolbe.addActionListener(this);
	    c.gridx = 9;
		c.gridy = 5;
		c.gridwidth = 1;
	    pane.add(NyKolbe, c);
	    NyKolbe.show(false);
		
		//---------------
		
	    /*
		Levelighet = new JLabel("0");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 6;
		c.gridwidth = 1;
		pane.add(Levelighet, c);
		
		Tekst1 = new JLabel("%");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 6;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		*/
		
		LifeBar = new JProgressBar(0, (int)Class_Akvariet.Levelighet);
		LifeBar.setValue((int)Class_Akvariet.Levelighet);
		LifeBar.setStringPainted(true);
		LifeBar.setString("Boforhold: " + LifeBar.getValue());
    	c.gridx = 8;
		c.gridy = 6;
    	pane.add(LifeBar, c);
		
		String Plass = "__________";
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		Tekst1 = new JLabel(Plass);
		Tekst1.setFont(UnBold);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 10;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		
		//-----------------
		
		for ( int x = 1 ; x <= 2 ; x++ ){
		
			//Fiskenavn[x] = new JLabel("Fischk Size " + x);
			Fiskenavn[x] = new JLabel("-");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 7+x;
			c.gridwidth = 2;
			pane.add(Fiskenavn[x], c);
			Fiskenavn[x].show(true);
			//System.out.println("Fiskenavn#"+x+" x="+1+" y="+(7+x));
		
			Tekst1 = new JLabel("Helse %");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3;
			c.gridy = 7;
			c.gridwidth = 1;
			pane.add(Tekst1, c);
			
			FiskeHelse[x] = new JLabel("");
			FiskeHelse[x].setFont(UnBold);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3;
			c.gridy = 7+x;
			c.gridwidth = 1;
			pane.add(FiskeHelse[x], c);
			FiskeHelse[x].show(true);
		
			Tekst1 = new JLabel("Sult ");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 4;
			c.gridy = 7;
			c.gridwidth = 1;
			pane.add(Tekst1, c);
			
			FiskeSult[x] = new JLabel("");
			FiskeSult[x].setFont(UnBold);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 4;
			c.gridy = 7+x;
			c.gridwidth = 1;
			pane.add(FiskeSult[x], c);
			FiskeSult[x].show(true);
		
			Tekst1 = new JLabel("Antall ");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 5;
			c.gridy = 7;
			c.gridwidth = 1;
			pane.add(Tekst1, c);
		
			FiskeAntall[x] = new JLabel("");
			FiskeAntall[x].setFont(UnBold);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 5;
			c.gridy = 7+x;
			c.gridwidth = 1;
			pane.add(FiskeAntall[x], c);
			FiskeAntall[x].show(true);
			
			
		}
		
		//
		
		
		
		
		/*
		Tekst1 = new JLabel(Plass);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		pane.add(Tekst1, c);
		*/
		
		MatFisk = new JButton("Mat fisk");
		MatFisk.setFont(UnBold);
		MatFisk.setActionCommand("Mat fisk");
		MatFisk.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 8;
		c.gridwidth = 1;
		MatFisk.show(false);
		pane.add(MatFisk, c);
		
		SelgFisk = new JButton("Selg fisk");
		SelgFisk.setFont(UnBold);
		SelgFisk.setActionCommand("Selg fisk");
		SelgFisk.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 9;
		c.gridwidth = 1;
		SelgFisk.show(false);
		pane.add(SelgFisk, c);
		

		//---- MENU
		
		
		ActionListener printListener = new ActionListener(  ) {
			public void actionPerformed(ActionEvent event) {

			}
		};
		
		
		menuBar = new JMenuBar();
		
		menu = new JMenu("Menu");
		//menu.addMenuListener(listener);
		menu.addActionListener(this);
		menuBar.add(menu);
		
		JMenu LevelMenu = new JMenu("Level");
		LevelMenu.addMenuListener(listener);
		menuBar.add(LevelMenu);
		
		/*
		for (int i=0; i < HoppLevler.length; i++) {
			HoppKnapper[i] = new JMenuItem(HoppLevler[i]);
			HoppKnapper[i].addActionListener(printListener);
			HoppKnapper[i].addActionListener(this);
			LevelMenu.add(HoppKnapper[i]);
			//menuBar.add(item);
		}
		*/
		
		NesteLevelKnapp = new JMenuItem("Neste level");
		NesteLevelKnapp.addActionListener(printListener);
		NesteLevelKnapp.addActionListener(this);
		LevelMenu.add(NesteLevelKnapp);
		
		JMenu HastighetsMenu = new JMenu("Hastighet");
		HastighetsMenu.addMenuListener(listener);
		menuBar.add(HastighetsMenu);
		
		for (int i=0; i < Hastigheter.length; i++) {
			HastighetsKnapper[i] = new JMenuItem(Hastigheter[i]);
			HastighetsKnapper[i].addActionListener(printListener);
			HastighetsKnapper[i].addActionListener(this);
			HastighetsMenu.add(HastighetsKnapper[i]);
		}
		
		for (int i=0; i < MenuItems.length; i++) {
			if ( MenuItems[i] == "Pause" ) {
				Pauseknapp = new JCheckBoxMenuItem("Pause");
				//Pauseknapp.addActionListener(printListener);
				Pauseknapp.addActionListener(this);
				//menu.add(Pauseknapp);
				menuBar.add(Pauseknapp);
			} else {
				JMenuItem item = new JMenuItem(MenuItems[i]);
				//item.addActionListener(printListener);
				item.addActionListener(this);
				menu.add(item);
				//menuBar.add(item);
			}
		}

		setJMenuBar(menuBar);

		//----MENU
	      
		 //---AKVARIE
		
		JPanel MainPanel = new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.PAGE_AXIS));
		MainPanel.setLocation(0, 0);
		MainPanel.setBackground(Color.cyan);
		
		Luft = new Canvas();
		Luft.setSize(TegningsStoerrelse.width, 0);
		Luft.setBackground(Color.white);
		MainPanel.add(Luft);
		
		Vann = new Canvas();
		Vann.setSize(TegningsStoerrelse.width, TegningsStoerrelse.height);
		Vann.setBackground(Color.cyan);
		MainPanel.add(Vann);
		
    	//add(MainPanel);
    	c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 10;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 10;
		pane.add(MainPanel, c);
    	
//    	ARGB = transparent
		TotalBilde = new BufferedImage((int) (TegningsStoerrelse.getWidth()+0.99), (int) (TegningsStoerrelse.getHeight()+0.99), BufferedImage.TYPE_INT_ARGB);
		
    	for (int k=0; k<myControl.Fisker; k++){
    		if ( myControl.Fiskene[k] != null ){
    			myControl.Fiskene[k].SettBevegelsesOmraade(0, TotalBilde.getWidth(), 0, TotalBilde.getHeight());
    			//System.out.println("Lengde " + myControl.Fiskene[k].FiskLengde);
    			//System.out.println("Høyde " + myControl.Fiskene[k].FiskHoeyde);
    		}
    	}
		
		pack();
        setVisible(true);
	}
	
	public void ShowFish(int Fisker){
		

	}
	
	public void actionPerformed(ActionEvent e) {
		
		//System.out.println("E");
        
		if ( e.getActionCommand().equals(SkiftKolbe.getActionCommand()) ) {
			myControl.SkiftKolbe();
		}
		if ( e.getActionCommand().equals(SkiftLys.getActionCommand()) ) {
			myControl.SkiftLys();
		}
		if ( e.getActionCommand().equals(FyllAkvarie.getActionCommand()) ) {
			myControl.FyllAkvarie();
		}
		if ( e.getActionCommand().equals(RensPumpe.getActionCommand()) ) {
			myControl.RensPumpe();
		}
		if ( e.getActionCommand().equals(MatFisk.getActionCommand()) ) {
			myControl.FeedFish();
		}
		if ( e.getActionCommand().equals(Hjelp.getActionCommand()) ) {
			myControl.Hjelp();
		}
		if ( e.getActionCommand().equals(SelgFisk.getActionCommand()) ) {
			myControl.SelgFisk();
		}
		if ( e.getActionCommand().equals(NyttAkvarie.getActionCommand()) ) {
			myControl.NyttAkvarie();
		}
		if ( e.getActionCommand().equals(NyPumpe.getActionCommand()) ) {
			myControl.NyPumpe();
		}
		if ( e.getActionCommand().equals(NyttLys.getActionCommand()) ) {
			myControl.NyttLys();
		}
		if ( e.getActionCommand().equals(NyKolbe.getActionCommand()) ) {
			myControl.NyKolbe();
		}
		
		/*
		for (int i=0; i < HoppLevler.length; i++) {
			//System.out.println("HoppKnapper[i]:" + e.getActionCommand());
			if ( e.getActionCommand().equals(HoppKnapper[i].getActionCommand()) ) {
				//System.out.println("Oppfattet " + HoppKnapper[i].getActionCommand());
			}
		}
		*/
		
		for (int i=0; i < HastighetsKnapper.length; i++) {
			if ( e.getActionCommand().equals(HastighetsKnapper[i].getActionCommand()) ) {
				//System.out.println("Oppfattet " + HastighetsKnapper[i].getActionCommand());
				myControl.Hastighet(Integer.parseInt(HastighetsKnapper[i].getActionCommand()));
			}
		}
		
		if ( e.getActionCommand() == "Pause" ){
			//System.out.println("C Oppfattet Pause");
			myControl.Pause();
		}
		
		if ( e.getActionCommand() == "Exit" ){
			System.exit(0);
		}
		
		if ( e.getActionCommand() == NesteLevelKnapp.getActionCommand()){
			myControl.NyttLevel(0);
		}
		
		ShowFish(1);
		
	}
	
	public void UpdateFish(){
		//paint(getGraphics());
		//System.out.println("Timer A");
		
		AkvarieLengde = (int)( myControl.Class_Akvariet.AkvarieStoerrelse*(1*3) );
		AkvarieHoeyde = (int)( myControl.Class_Akvariet.AkvarieStoerrelse*(2*3)/3 );
		
		double Hoeyde = ((myControl.Class_Akvariet.AkvarieVann/myControl.Class_Akvariet.AkvarieStoerrelse)*AkvarieHoeyde);
		TegningsStoerrelse = new Dimension(AkvarieLengde,(int)Hoeyde);
		
		Vann.setSize(TegningsStoerrelse.width, TegningsStoerrelse.height);
		Hoeyde = AkvarieHoeyde - Hoeyde;
		Luft.setSize(new Dimension(TegningsStoerrelse.width,(int)Hoeyde));
		
		TotalBilde = new BufferedImage((int) (TegningsStoerrelse.getWidth()+0.99), (int) (TegningsStoerrelse.getHeight()+0.99), BufferedImage.TYPE_INT_ARGB);
		
		MainPanel.setSize(TegningsStoerrelse.width, TegningsStoerrelse.height);
		
		pack();
		
		for (int k=0; k<myControl.Fiskene.length; k++){
			//System.out.println("Timer B");
			//System.out.println("myControl.Fiskene.length="+myControl.Fiskene.length);
			if ( myControl.Fiskene[k] != null ){
				myControl.Fiskene[k].SettBevegelsesOmraade(0, TegningsStoerrelse.width, 0, TegningsStoerrelse.height);
				myControl.Fiskene[k].Flytt();
			}
		}
		
		Graphics g = TotalBilde.getGraphics();
		int Fisker = 0;
		int FiskLengde = 1;
		int FiskHoeyde = 1;
		//System.out.println("A");
		for ( int x = 0 ; x <= 9 ; x++ ){
			Fisker = 0;
			//System.out.println("B"+x);
			//paint(getGraphics());
			for (int k=0; k<myControl.Fiskene.length; k++){
				if ( myControl.Fiskene[k] != null ){
					if ( myControl.Fiskene[k].Helse > 0) {
						g.setColor(myControl.Fiskene[k].Fiskefarge);
						g.fillOval(myControl.Fiskene[k].Location_x, myControl.Fiskene[k].Location_y, myControl.Fiskene[k].FiskLengde, myControl.Fiskene[k].FiskHoeyde);
						//System.out.println("A="+x+" B="+k);
						//System.out.println("Tegner fisk #"+k+": Lengde="+myControl.Fiskene[k].FiskLengde+", Høyde="+myControl.Fiskene[k].FiskHoeyde+" Alder="+myControl.Fiskene[k].Alder);
						Fisker ++;
						//paint(getGraphics());
					}
				}
			}
			//System.out.println("Fisker="+Fisker);
			//paint(getGraphics());
			//System.out.println("Flytter fisk");
			for (int k=0; k<myControl.Fiskene.length; k++){
				if ( myControl.Fiskene[k] != null ){
					//System.out.println("k="+k);
					//System.out.println("Timer D");
					g.setColor(Water);
					g.fillOval(myControl.Fiskene[k].Location_x, myControl.Fiskene[k].Location_y, FiskLengde, FiskHoeyde);
					//g.fillOval(myControl.Fiskene[k].Location_x, myControl.Fiskene[k].Location_y, myControl.Fiskene[k].FiskLengde, myControl.Fiskene[k].FiskHoeyde);
					myControl.Fiskene[k].Flytt();
					//paint(getGraphics());
				}
			}
			//paint(getGraphics());
		}
	}
	
  	public void paint(Graphics g){
  		super.paint(g);
  		//System.out.println("Oppdaterer grafikk");
  		Vann.getGraphics().drawImage(TotalBilde, 0, 0, this);
  	}
	
	public void mouseMoved(MouseEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
    }
	
}
