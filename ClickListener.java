import java.awt.event.*;
public class ClickListener extends MouseAdapter{

    private PanneauDeCartes panneau;

    @Override
    public void mouseClicked(MouseEvent e){
	panneau.carteClicked((Carte) e.getComponent());
    }

    public ClickListener(PanneauDeCartes panneau){
	this.panneau = panneau;
    }

}
