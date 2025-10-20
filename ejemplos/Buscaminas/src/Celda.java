import javax.swing.JButton;

public class Celda extends JButton {
    
    private int valor;
    private boolean esVisible;

    public Celda(int v) {
        super();
        this.valor = v;
        this.esVisible = false;
    }

    // Getter
    public int getValor() {
        return this.valor;
    }

    // Setter
    public void setValor(int v) {
        this.valor = v;
    }

    // Getter
    public boolean getEsVisible() {
        return this.esVisible;
    }

    // Setter
    public void setEsVisible(boolean ev) {
        this.esVisible = ev;
    }

}