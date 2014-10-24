/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_echequier;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author goum
 */
class Pion extends Piece {
   
    
    public Pion(Echiquier aThis, Case aCase, boolean b) {
        super(aThis, aCase, b);
        try {
            if(b) { 
                my_image = ImageIO.read(new File("pion_blanc.png"));
            } else {
                my_image = ImageIO.read(new File("pion_noir.png"));
            }
        } catch (IOException e) {
        }
    }

    @Override
    public ArrayList<Case> listAllDestination() {
        ArrayList<Case> cases = new ArrayList<>();
        Case tmp;
        int x = this.maCase.x;
        int y = this.maCase.y;
        int sens;
        if(this.isBlue){
            sens = 1;
        } else {
            sens = -1;
        }
        tmp = this.echiquier.getCase(x + sens, y);
        if(tmp != null && tmp.P == null) {
            cases.add(tmp);
            tmp = this.echiquier.getCase(x + 2* sens, y);
            if(tmp != null && 
                (this.isBlue && this.maCase.x == 1 || !this.isBlue && this.maCase.x == 6 ) && 
                tmp.P == null) {
                cases.add(tmp);
            }
        }
        
        tmp = this.echiquier.getCase(x + sens, y+1);
        if(tmp != null && tmp.P != null && tmp.P.isBlue != this.maCase.P.isBlue) {
            cases.add(tmp);
        }
        
        tmp = this.echiquier.getCase(x + sens, y-1);
        if(tmp != null && tmp.P != null &&  tmp.P.isBlue != this.maCase.P.isBlue) {
            cases.add(tmp);
        }
        
        return cases;
    }
    
}
