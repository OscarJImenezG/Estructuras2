package Pruebas;

import javax.swing.*;
import java.awt.*;

public class Page1 extends JPanel {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;

    public Page1() {
        initComponents();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jScrollPane2 = new JScrollPane();
        jTable2 = new JTable();

        setBackground(new Color(216, 255, 237));
        setPreferredSize(new Dimension(490, 258));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane2.setViewportView(jTable2);

        setLayout(new BorderLayout());
        add(jScrollPane2, BorderLayout.CENTER);
    }
}