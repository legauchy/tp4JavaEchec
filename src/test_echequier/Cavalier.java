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
class Cavalier extends Piece {

    public Cavalier(Echiquier aThis, Case aCase, boolean b) {
        super(aThis, aCase, b);
        try {
            if(b) { 
                my_image = ImageIO.read(new File("cheval_blanc.png"));
            } else {
                my_image = ImageIO.read(new File("cheval_noir.png"));
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
        
        for (int i : new int[] {-1, 1}) {
            for (int j : new int[] {-2, 2}) {
                tmp = this.echiquier.getCase(x+i, y+j);
                if (tmp != null && (tmp.P == null || tmp.P.isBlue != this.maCase.P.isBlue)) {
                    cases.add(tmp);
                }
            }
        }
        
        for (int i : new int[] {-2, 2}) {
            for (int j : new int[] {-1, 1}) {
                tmp = this.echiquier.getCase(x+i, y+j);
                if (tmp != null && (tmp.P == null || tmp.P.isBlue != this.maCase.P.isBlue)) {
                    cases.add(tmp);
                }
            }
        }
        
        return cases;
    }
    
}
