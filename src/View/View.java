import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;


public class View {
    private JLabel[][] celdas;
    private JFrame     ventana;
    private JPanel     panelTeclado, panelCeldas, lettersPanel;

    public View(){
        this.celdas       = new JLabel[6][5];
        this.panelTeclado = new JPanel();
        this.panelCeldas  = new JPanel();
        this.ventana      = new JFrame();
    }

    public void confi(){
        //Configuración de la ventana
        ventana.setTitle("Wordle");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600,650);
        ventana.setLayout(new BorderLayout()); // Nos permitira acomodar los paneles con NOTH y SOUTH
        ventana.getContentPane().setBackground(Color.BLACK);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        //Configuración del panel de la Celda
        panelCeldas.setLayout(new GridLayout(6,5,5,5));
        panelCeldas.setBackground(new Color(30, 30, 30));
        panelCeldas.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //Configuración de las Etiquetas que almaceneran las letras
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 5; y++){
                JLabel cuad = new JLabel("",SwingConstants.CENTER); //SwingConstants.CENTER -> Centra el contenido 
                cuad.setPreferredSize(new Dimension(60, 60));
                cuad.setOpaque(true);
                cuad.setBackground(Color.WHITE);
                cuad.setForeground(Color.BLACK);
                cuad.setFont(new Font("Arial", Font.BOLD, 24));
                cuad.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


                panelCeldas.add(cuad);
                celdas[x][y] = cuad;
            }
        }

        panelTeclado.setLayout(new GridLayout(3, 1, 10, 10));
        panelTeclado.setBackground(new Color(245, 245, 245));
        panelTeclado.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

    }

    public void armar(){
        ventana.add(panelCeldas,BorderLayout.CENTER);
        ventana.add(panelTeclado,BorderLayout.SOUTH);
    }

    public void lanzar(){
        crearTeclado();
        ventana.setVisible(true);
    }

    public void run(){
        confi();
        armar();
        lanzar();
    }

    private void crearTeclado(){
        String [] letters = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM" };
        
         for (int i = 0; i < letters.length; i++) {
            lettersPanel = new JPanel();
            lettersPanel.setBackground(new Color(245, 245, 245));
            lettersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));

            if (i == 2) {
                // Botón ENTER
                JButton enterButton = crearButton("",new Color(100, 149, 237));
                enterButton.setIcon(ajustarTamanio(new ImageIcon("C:\\Users\\Alan0\\IdeaProjects\\WordleStatement\\src\\View\\Recursos\\Enter.png")));
                lettersPanel.add(enterButton);
            }

            for (char c : letters[i].toCharArray()) {
                JButton letterButton = crearButton(String.valueOf(c),new Color(230, 230, 230));
                lettersPanel.add(letterButton);
            }

            if (i == 2) {
                // Botón BORRAR
                JButton deleteButton = crearButton("",new Color(220, 20, 60));
                deleteButton.setIcon(ajustarTamanio(new ImageIcon("C:\\Users\\Alan0\\IdeaProjects\\WordleStatement\\src\\View\\Recursos\\Delete.png")));
                lettersPanel.add(deleteButton);
            }

            panelTeclado.add(lettersPanel);
        }
    }

    private JButton crearButton(String text, Color colorBoton) {
        JButton boton = new JButton(text);
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setFocusPainted(false);
        boton.setBackground(colorBoton);
        boton.setForeground(Color.BLACK);
        boton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public ImageIcon ajustarTamanio(ImageIcon x){
        Image y = x.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(y);
    }
}
