package test_echequier;

// le programme principal se contente de cr�er une fen�tre Echiquier
// et de la rendre visible

public class Test_Echiquier
{
    public static void main(String [] arg)
    {
	Echiquier E=new Echiquier();
        E.pack();
	E.setVisible(true);
    }
}
