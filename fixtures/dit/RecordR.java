package dit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

record RecordR() implements ActionListener, UmaInterface {

    @Override
    public void actionPerformed(ActionEvent e) {
        String x = e.getActionCommand();
        if(x.isEmpty()){
            x = "teste";
        }
        else{
            x = x;
        }
    }

    public int a(){
        return 0;
    }
}