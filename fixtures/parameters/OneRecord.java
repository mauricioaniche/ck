import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record OneRecord(String aString, Integer anInteger, Long aLong) implements ActionListener {

    public int simpleSum(int parcela){
        return anInteger + parcela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}