import java.awt.event.*;
public class ClickListener extends MouseAdapter{
    PanneauDeCartes panneau;
    public void mouseCliked(MouseEvent e){
	//une instruction de ce genre là:
	//panneau.retourneCarte(e.getComponent());
	
    }

    public ClickListener(PanneauDeCartes panneau){
	this.panneau = panneau;
    }

}
