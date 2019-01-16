/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionpanel;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Danilo Martinez
 */
public class ElementosLimite
{
    public void soloNumeros(KeyEvent evt, JTextField text, int max)
    {
        char c = evt.getKeyChar();
        if((c < '0' || c > '9') || Character.isWhitespace(c))
        {
            evt.consume();
        }
        if(text.getText().length() == max)
        {
            evt.consume();
        }
    }
    
    public void limiteCaracteres(KeyEvent evt, JTextField text, int max)
    {
        if(text.getText().length() == max)
        {
            evt.consume();
        }
}
}
