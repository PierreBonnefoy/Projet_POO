package wargame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuAide extends JPanel implements IConfig{
	MenuAide(){
		this.setPreferredSize(new Dimension(HAUTEUR_FENETRE,LARGEUR_FENETRE));
		GridLayout grille = new GridLayout(0,1,HAUTEUR_FENETRE/10,LARGEUR_FENETRE/10);
		grille.preferredLayoutSize(this);
		this.setLayout(grille);
		JLabel Explication = new JLabel("<html> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing <br> nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. <br> Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu </html>");
		JButton retour = new JButton ("retour");
		this.setBackground(new Color(127, 127, 127));
		Explication.setForeground(new Color(251, 243, 124));
		Explication.setFont(new Font("Tahoma", Font.PLAIN, 30));
		retour.setBackground(new Color(100, 100, 100));
		retour.setForeground(new Color(251, 243, 124));
		retour.setFont(new Font("Tahoma", Font.PLAIN, 30));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGeneral menu = new MenuGeneral();
				remove(Explication);
				remove(retour);
				add(menu);
				validate();
			}
		}
		);
		add(Explication);
		add(retour);
	}
}
