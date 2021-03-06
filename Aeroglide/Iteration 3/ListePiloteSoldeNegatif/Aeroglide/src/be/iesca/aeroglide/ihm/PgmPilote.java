/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.ihm;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;


@SuppressWarnings("serial")
public class PgmPilote extends JFrame {
	private PiloteModel piloteModel;
	private GestionnaireUseCases gestionnaireUseCases;
	private Action actEnregistrerPilote;
	private Action actListerPilotes;
	private Action actEnregistrerVol;
	private Action actListerVols;
	private Action actListerPilotesCompteNegatif;
	
	private JToolBar jtb;
	
	public PgmPilote(){
		super("Aeroglide");
		//model
		piloteModel=new PiloteModel();
		this.gestionnaireUseCases = GestionnaireUseCases.getINSTANCE();
		
		//actions
		Icon iconEnregistrerPilote=new ImageIcon("images/addPilot.png");
		actEnregistrerPilote=new AbstractAction("Enregistrer pilote",iconEnregistrerPilote){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enregistrerPilote();			
			}
		};
		
		Icon iconListerPilotes=new ImageIcon("images/pilotList.png");
		actListerPilotes=new AbstractAction("Lister pilotes",iconListerPilotes){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listerPilotes();			
			}
		};
		
		Icon iconEnregistrerVol=new ImageIcon("images/addAirplane.png");
		actEnregistrerVol=new AbstractAction("Enregistrer vol",iconEnregistrerVol){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enregistrerVol();			
			}
		};
		
		Icon iconListerVols=new ImageIcon("images/listeVols.png");
		actListerVols=new AbstractAction("Lister vols",iconListerVols){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listerVols();			
			}
		};
		
		Icon iconListerPilotesCompteNegatif=new ImageIcon("images/pilotNegatifList.png");
		actListerPilotesCompteNegatif=new AbstractAction("Lister pilote compte negatif",iconListerPilotesCompteNegatif){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listerPilotesCompteNegatif();			
			}
		};
	
		// Jmenubar
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		
		// JMenu
		JMenu menuPilote = new JMenu("Pilote");
		menuPilote.add(actEnregistrerPilote);
		menuPilote.add(actListerPilotes);
		menuPilote.add(actListerPilotesCompteNegatif);
		
		JMenu menuVol = new JMenu("Vol");
		menuVol.add(actEnregistrerVol);
		menuVol.add(actListerVols);
		
		jmb.add(menuPilote);
		jmb.add(menuVol);
		
		//JtoolBar
		jtb=new JToolBar();
		jtb.add(actEnregistrerPilote);
		jtb.add(actListerPilotes);
		jtb.add(actListerPilotesCompteNegatif);
		jtb.add(actEnregistrerVol);
		jtb.add(actListerVols);
		
		this.add(jtb,BorderLayout.NORTH);
		jtb.setFloatable(false);
		
		VueMessage vm=new VueMessage(piloteModel);
		this.add(vm,BorderLayout.SOUTH);		
	}
	
	public void enregistrerPilote(){
		majFrame();
		
		PiloteVueController jp=new PiloteVueController(piloteModel);
		this.add(jp);
		SwingUtilities.updateComponentTreeUI(this);
	}
	

	public void listerPilotes(){
		majFrame();
		Bundle bundle = new Bundle();
		this.gestionnaireUseCases.listerPilotes(bundle);
		this.piloteModel.setBundle(bundle);
		
		if((Boolean) bundle.get(Bundle.OPERATION_REUSSIE)){
			PiloteVueListe pvl = new PiloteVueListe(this.piloteModel);
			this.add(pvl);
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
	
	public void enregistrerVol(){
		majFrame();
	}
	
	public void listerVols(){
		majFrame();
	}
	
	public void listerPilotesCompteNegatif(){
		majFrame();
		Bundle bundle = new Bundle();
		this.gestionnaireUseCases.listerPilotesSoldeNegatif(bundle);
		this.piloteModel.setBundle(bundle);

		if((Boolean) bundle.get(Bundle.OPERATION_REUSSIE)){
			PiloteVueListe pvl = new PiloteVueListe(this.piloteModel);
			this.add(pvl);
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
	
	private void majFrame(){
		this.getContentPane().removeAll();
		this.add(jtb, BorderLayout.NORTH);
		this.add(new VueMessage(this.piloteModel), BorderLayout.SOUTH);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public static void main(String[] args) {
		PgmPilote frame = new PgmPilote();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(1000,500);
		frame.setVisible( true );
	}

}
