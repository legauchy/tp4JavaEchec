/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_echequier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author goum
 */
class Tour extends Piece {

    public Tour(Echiquier aThis, Case aCase, boolean b) {
        super(aThis, aCase, b);
        try {
            if(b) { 
                my_image = ImageIO.read(new File("tour_blanc.png"));
            } else {
                my_image = ImageIO.read(new File("tour_noir.png"));
            }
        } catch (IOException e) {
        }
    }

    @Override
    public String toString() {
        return "Tour";
    }

    @Override
    public ArrayList<Case> listAllDestination() {
        ArrayList<Case> cases = new ArrayList<>();
        Case tmp;
        int x;
        int y;
        // Ligne à gauche de la tour
        x = this.maCase.x;
        y = this.maCase.y-1;
        tmp = this.echiquier.getCase(x, y);
        while (tmp != null && (tmp.P == null || tmp.P.isBlue != this.maCase.P.isBlue)) {
            cases.add(tmp);
            if (tmp.P != null) {
                tmp = null;
            } else {
                y--;
                tmp = this.echiquier.getCase(x, y);
            }
        }
        // Ligne à droite de la tour
        x = this.maCase.x;
        y = this.maCase.y+1;
        tmp = this.echiquier.getCase(x, y);
        while (tmp != null && (tmp.P == null || tmp.P.isBlue != this.maCase.P.isBlue)) {
            cases.add(tmp);
            if (tmp.P != null) {
                tmp = null;
            } else {
               y++;
               tmp = this.echiquier.getCase(x, y);
            }
        }
        // Colonne au dessus de la tour
        x = this.maCase.x+1;
        y = this.maCase.y;
        tmp = this.echiquier.getCase(x, y);
        while (tmp != null && (tmp.P == null || tmp.P.isBlue != this.maCase.P.isBlue)) {
            cases.add(tmp);
            if (tmp.P != null) {
                tmp = null;
            } else {
                x++;
                tmp = this.echiquier.getCase(x, y);
            }
        }
        // En dessous de la tour
        x = this.maCase.x - 1;
        y = this.maCase.y;
        tmp = this.echiquier.getCase(x, y);
        while (tmp != null && (tmp.P == null || tmp.P.isBlue != this.maCase.P.isBlue)) {
            cases.add(tmp);
            if (tmp.P != null) {
                tmp = null;
            } else {
                x--;
                tmp = this.echiquier.getCase(x, y);
            }
        }
        
        return cases;
    }
    
}
