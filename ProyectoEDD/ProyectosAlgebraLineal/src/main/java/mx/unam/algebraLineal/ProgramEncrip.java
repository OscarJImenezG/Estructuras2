package mx.unam.algebraLineal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;

public class ProgramEncrip extends JFrame {

    private JLabel lblEtiqueta1;
    private JTextField txtEmisor;
    private JTextField txtReceptor;
    private JLabel lblEtiqueta2;
    private JButton botonEncriptar;
    private JButton botonDesencriptar;
    private JButton botonIntroducirMatriz;
    private ProgramEncrip print;
    private ProgramEncrip print2;
    private int[] matriz;
    private Matrices met = met = new Matrices();

    public ProgramEncrip() throws HeadlessException {
        super("INDEX");
        this.setSize(300, 150);
        setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(211, 237, 248));
        lblEtiqueta1 = new JLabel("¿Que desea hacer?");
        this.getContentPane().add(lblEtiqueta1);
        botonEncriptar = new JButton("Encriptar");
        this.getContentPane().add(botonEncriptar);
        botonDesencriptar = new JButton("Desencriptar");
        this.getContentPane().add(botonDesencriptar);

        botonEncriptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    print = new ProgramEncrip("Encriptar");
                }catch (IllegalArgumentException iae){
                }
            }
        });

        botonDesencriptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    PintarPanel.panelDes();
                }catch (IllegalArgumentException iae){
                    System.out.println(iae.toString());
                }

            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ProgramEncrip(String title) throws HeadlessException {
        super(title);
        this.setSize(300, 200);
        setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(199, 178, 203));
        lblEtiqueta1 = new JLabel("Escribe el codigo:");
        this.getContentPane().add(lblEtiqueta1);
        txtEmisor = new JTextField(25);
        this.getContentPane().add(txtEmisor);
        lblEtiqueta2 = new JLabel("Aqui se mostrara tu matriz encriptada:");
        this.getContentPane().add(lblEtiqueta2);
        txtReceptor = new JTextField(25);
        this.getContentPane().add(txtReceptor);
        botonEncriptar = new JButton("Encriptar");
        this.getContentPane().add(botonEncriptar);
        botonIntroducirMatriz = new JButton("Cambiar la matriz");
        this.getContentPane().add(botonIntroducirMatriz);
        botonEncriptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                met.setCodigo(txtEmisor.getText());
                txtReceptor.setText(Arrays.toString(met.encriptar()));

            }

        });

        botonIntroducirMatriz.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    PintarPanel.PanelChico("Matrices");
                }catch (IllegalArgumentException iae){
                }

            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String args[]) {
        ProgramEncrip ventana = new ProgramEncrip();
    }
}
