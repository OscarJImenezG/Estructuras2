package Pruebas;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanelManager {
    private JPanel contentPanel;
    private Map<String, JPanel> panels;

    public PanelManager(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        this.panels = new HashMap<>();
    }

    public void addPanel(String panelName, JPanel panel) {
        panels.put(panelName, panel);
    }

    public void showPanel(String panelName) {
        if (panels.containsKey(panelName)) {
            JPanel panelToShow = panels.get(panelName);
            contentPanel.removeAll();
            contentPanel.add(panelToShow, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        } else {
            System.out.println("El panel '" + panelName + "' no está registrado.");
        }
    }

    public JPanel createAndAddPanel(String panelName) {
        JPanel newPanel = new JPanel();
        panels.put(panelName, newPanel);
        return newPanel;
    }
}