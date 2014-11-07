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
class Reine extends Piece {

    public Reine(Echiquier aThis, Case aCase, boolean b) {
        super(aThis, aCase, b);
        try {
            if(b) { 
                my_image = ImageIO.read(new File("reine_blanc.png"));
            } else {
                my_image = ImageIO.read(new File("reine_noir.png"));
            }
        } catch (IOException e) {
        }
    }

    @Override
    public ArrayList<Case> listAllDestination() {
        Tour tour = new Tour(this.echiquier, this.maCase, this.isBlue);
        Fou fou = new Fou(this.echiquier, this.maCase, this.isBlue);
        ArrayList<Case> list = new ArrayList<>();
        list.addAll(tour.listAllDestination());
        list.addAll(fou.listAllDestination());
        
        return list;
    }
    
}
