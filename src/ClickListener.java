import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {

    private PanneauDeCartes panneau;

    public ClickListener(PanneauDeCartes panneau) {
        this.panneau = panneau;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panneau.carteClicked((Carte) e.getComponent());
    }

}
