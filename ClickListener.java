import java.awt.event.*;
public class ClickListener extends MouseAdapter{
    PanneauDeCartes panneau;
    public void mouseCliked(MouseEvent e){
	panneau.runGame((Carte) e.getComponent());
	
    }

    public ClickListener(PanneauDeCartes panneau){
	this.panneau = panneau;
    }

}
