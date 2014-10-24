package test_echequier;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// La classe Case gère les cases du damier
// l'attribut couleur donne la couleur de base de la case : grise ou blanche 
// la case change de couleur lorsque l'on clique dessus 
// ou lorsqu'elle est survolée par la souris avec le clique enclenché
// elle reprend sa couleur d'origine lorsque l'on relâche le clique
// ou que la souris quitte la case
// le booléen click est vrai si la souris a cliqué sur la case
// le booléen clicked permet de savoir si le click est enclenché
// l'attribut E contient une réference vers l'échiquier 
// l'attribut P contient une réference vers une pièce 
// si la case est occupée par une pièce
// les attributs x et y sont les coordonnées de la case dans le damier
// l'attribut destination sauvegarde la réference de la case 
// vers laquelle on souhaite déplacer un pion
// Case hérite de JPanel qui est une sorte d'aire de dessin
// Case implémente Mouselistener: l'écouteur de souris 
// pour récuperer les évènements dus à la souris 

class Case extends JPanel implements MouseListener{ 
    private final Color couleur;
    private boolean click;
    private static boolean clicked=false;
    public final Echiquier E;
    public Piece P;
    public final int x,y;
    private static Case destination=null;
    
    Case(Color c, Echiquier E, int x, int y, Piece P){
	// setBackground affecte la couleur de fond
    	setBackground(c);	
	// setPreferredSize et setSize permettent de fixer
	// les dimensions de la case
	setPreferredSize(new Dimension(50,50)); 
	setSize(getPreferredSize());
	// les évènements souris sur la case sont écoutés par la case elle-même
	addMouseListener(this);
	
	couleur=c;
	click=false;
	this.E=E;
	this.x=x;
	this.y=y;
	this.P=P;
    }
    
    // cette fonction remet la case à sa couleur d'origine
    public void ResetColor(){
	setBackground(couleur);
    }
    
    // comme la case écoute les évènements souris sur elle-mếme
    // elle doit implementer les fonctions suivantes
    
    // lorsqu'on enclenche le clique sur la case
    public void mousePressed(MouseEvent e){
	click=true; // la case se souvient que la souris a cliqué sur elle
	clicked=true; // on informe les autres cases que le clique est enclenché
	setBackground(Color.green);  // on change la couleur de la case
        for(Case c : this.P.listAllDestination()){
            c.setBackground(Color.green);
        }
        // on sauvegarde la référence de la case 
        // que la souris survole actuellement avec le clique enclenche
	destination=this; 
    }
    
    // évènement "sur clique" non utilisé
    public void mouseClicked(MouseEvent e) {}
    
    // lorsqu'on entre dans une case avec le clique enclenché
    public void mouseEntered(MouseEvent e) {
	if(!click && clicked) setBackground(Color.cyan); // on change la couleur 
	if( clicked)  destination=this; // et on met à jour la destination
    }
    
    // �v�nement lorsqu'on rel�che le clique 
    // cet �v�nement est capt� par la case qui a subit le clique
    // c'est pourquoi on a besoin de la variable destination
    // elle permet de savoir ou est la souris lorsque le clique est rel�ch�
    public void mouseReleased(MouseEvent e) {
	// on remet la couleur d'origine de la case
	ResetColor();
	
	click=false;
	clicked=false;
	
	// on remet la couleur d'origine de la case destination
	destination.ResetColor();
	for(Case c : this.P.listAllDestination()){
            c.ResetColor();
        }
	// si la case d'origine contient une piece 
	// on essaie de bouger cette pièce vers la case destination
	// ce déplacement réussit si on respecte les contraintes
	
	if(P!=null)
	    P.moveTo(destination);	
    }
    
    
    // évènement sur sortie de la souris d'une case
    public void mouseExited(MouseEvent e) {
	if(!click) ResetColor();
    }
    
    // la méthode paintComponent gère 
    // l'affichage de la case
    
    public void paintComponent(Graphics g)
    {
	// on affiche d'abord la case
       	super.paintComponent(g); 
	
	// puis on affiche la pièce 
	// à l'int�rieur
	// si la case contient une pièce
	if(P!=null){
	    if(P.isBlue)
                g.setColor(Color.blue);
            else
                g.setColor(Color.red);
	    Font f = new Font("Comic sans MS",Font.BOLD,20);
	    g.setFont(f);
	    g.drawImage(P.toImage(),0,0,null);
	}
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Case other = (Case) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Case{" + "P=" + P + ", x=" + x + ", y=" + y + '}';
    }
    
    
}