package methods;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record OneRecord(String aString, Integer anInteger, Long aLong) implements ActionListener {

	public OneRecord(String aString, Long aLong) {
		this(aString, 0, aLong);
	}
	
    public int simpleSum(int parcela) {
        return anInteger + parcela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    private static void privateStaticMethod(){}
    
    void defaultMethod() {}
    
    protected void protectedMethod() {}

    public synchronized void syncMethod(){}
    
    public final void finalMethod() {}
}