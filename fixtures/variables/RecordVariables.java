package variables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record RecordVariables(String aString, Integer anInteger, Long aLong) implements ActionListener {

    public int simpleSum(int parcela) {
        return anInteger + parcela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	int x = 4;
    	int y = 5;
    }

    private static void privateStaticMethod(){
    	int x = 5;
    	long z = 80;
    }
    
    void defaultMethod() {}
}