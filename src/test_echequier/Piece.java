package test_echequier;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Gauchy Anthony - Lefevre Henry
 */
public abstract class Piece {
    public boolean isBlue;
    public Echiquier echiquier;
    public Case maCase;
    protected Image my_image;
    
    public Piece(Echiquier aThis, Case aCase, boolean b) {
        this.isBlue = b;
        this.echiquier = aThis;
        this.maCase = aCase;
    }
    
    public void moveTo(Case destination) {
        if(this.isCoupOK(destination)) {
            this.play(destination);
            this.echiquier.changeTour();
        }
    };
    
    public  boolean isCoupOK(Case destination) {
        if(this.echiquier.getTour() != this.isBlue) {
            
            return false;
        }
        for(Case c : listAllDestination()) {
            if(c.equals(destination)){
                Case tmp_case = this.maCase;
                Piece tmp_pion = destination.P;
                this.play(destination);
                
                if(this.echiquier.echec()) {
                    this.cancel(tmp_case, destination, tmp_pion);
                    
                    return false;
                }
                this.cancel(tmp_case, destination, tmp_pion);
                
                return true;
            }
        }
        
        return false;
    }
    
    private void play(Case destination) {
        this.maCase.P = null;
        this.maCase = destination;
        destination.P = this;
    }
    
    private void cancel(Case tmp_case, Case destination, Piece tmp_piece) {
        destination.P = tmp_piece;
        this.maCase = tmp_case;
        this.maCase.P = this;
    }
    
    public abstract ArrayList<Case> listAllDestination();
    
    public Image toImage() {
        return this.my_image;
    }
}
