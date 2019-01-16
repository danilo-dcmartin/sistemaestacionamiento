/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionpanel;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import vista.DiagramaEstaciona;

/**
 *
 * @author Danilo Martinez
 */
public class CambioJornada implements Runnable
{
    private static byte jornadaBtn = 0; // 1 = diurno - 2 = vespertino

    public byte getJornadaBtn() {
        return jornadaBtn;
    }
    private DiagramaEstaciona diag = null;
    public CambioJornada(DiagramaEstaciona d)
    {
        this.diag = d;
    }

    public CambioJornada() {
    }
    
    @Override
    public void run()
    {   
        LocalTime hora1 = LocalTime.parse("07:00:00");
        LocalTime hora2 = LocalTime.parse("19:20:00");
        LocalTime local = null;
        Date ahora = null; 
        DateFormat formatohora = new SimpleDateFormat("HH:mm:ss");
        try
        {
            while(true)
            {
                ahora = new Date();
                local = LocalTime.now();
                diag.lblHora.setText(formatohora.format(ahora));
                if(local.compareTo(hora1) >= 0 && local.compareTo(hora2) <= 0)
                {
                    //Diurno
                    this.jornadaBtn = 1;
                    diag.lbl43a64.setBackground(new Color(91,255,83));
                    diag.lbl43a64.setText("ADMINISTRATIVOS");
                    diag.lbl65a86.setBackground(new Color(255,61,61));
                    diag.lbl65a86.setText("DOCENTES");
                    diag.lbl109a118.setBackground(new Color(251,217,66));
                    diag.lbl109a118.setText("ALUMNOS DIURNOS");
                    diag.lbl131a152.setBackground(new Color(251,217,66));
                    diag.lbl131a152.setText("ALUMNOS DIURNOS");
                    diag.lbl153a176.setBackground(new Color(251,217,66));
                    diag.lbl153a176.setText("ALUMNOS DIURNOS");
                    diag.lbl1a20.setBackground(new Color(251,217,66));
                    diag.lbl1a20.setText("<html>ALUM.<br>DIURNO</html>");
                    diag.lbl185a177.setBackground(new Color(251,217,66));
                    diag.lbl185a177.setText("<html>ALUM.<br>DIURNO</html>");
                    diag.lbl192a186.setBackground(new Color(91,255,83));
                    diag.lbl192a186.setText("ADMIN.");  
                    diag.lbl194a193.setBackground(new Color(153, 153, 153));
                    diag.lbl194a193.setText("VISITA");
                }
                else
                {
                    //Vespertino
                    this.jornadaBtn = 2;
                    diag.lbl43a64.setBackground(new Color(100, 118, 255));
                    diag.lbl43a64.setText("ALUMNOS VESPERTINOS");
                    diag.lbl65a86.setBackground(new Color(100, 118, 255));
                    diag.lbl65a86.setText("ALUMNOS VESPERTINOS");
                    diag.lbl109a118.setBackground(new Color(255,61,61));
                    diag.lbl109a118.setText("DOCENTES");
                    diag.lbl131a152.setBackground(new Color(100, 118, 255));
                    diag.lbl131a152.setText("ALUMNOS VESPERTINOS");
                    diag.lbl153a176.setBackground(new Color(100, 118, 255));
                    diag.lbl153a176.setText("ALUMNOS VESPERTINOS");
                    diag.lbl1a20.setBackground(new Color(100, 118, 255));
                    diag.lbl1a20.setText("<html>ALUM.<br>VESP.</html>");
                    diag.lbl185a177.setBackground(new Color(100, 118, 255));
                    diag.lbl185a177.setText("<html>ALUM.<br>VESP.</html>");
                    diag.lbl192a186.setBackground(new Color(100, 118, 255));
                    diag.lbl192a186.setText("<html>ALUM.<br>VESP.</html>");  
                    diag.lbl194a193.setBackground(new Color(100, 118, 255));
                    diag.lbl194a193.setText("<html>ALUM.<br>VESP.</html>");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex)
        {
            // ex
        }
    }
    
}
