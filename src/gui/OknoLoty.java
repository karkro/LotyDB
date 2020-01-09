package gui;

import main.WyszukajLoty;
import model.Lot;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OknoLoty extends JFrame {

    private OknoLoty() {
        JPanel panelWyszukiwania = stworzPanelWyszukiwania();
        add(panelWyszukiwania, BorderLayout.NORTH);

        JPanel panelWynikowWyszukiwania = stworzPanelWynikowWyszukiwania();
        add(panelWynikowWyszukiwania, BorderLayout.CENTER);

        JPanel panelEdycji = stworzPanelEdycji();
        add(panelEdycji, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    private JPanel stworzPanelWyszukiwania() {
        JPanel panelWyszukiwania = new JPanel();
        panelWyszukiwania.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Wyszukiwanie"));
        JLabel etykietaSkad = new JLabel("Skąd");
        panelWyszukiwania.add(etykietaSkad);
        JTextField poleTekstoweSkad = new JTextField(25);
        panelWyszukiwania.add(poleTekstoweSkad);
        JLabel etykietaDokad = new JLabel("Dokąd");
        panelWyszukiwania.add(etykietaDokad);
        JTextField poleTekstoweDokad = new JTextField(25);
        panelWyszukiwania.add(poleTekstoweDokad);

        JButton przyciskSzukaj = new JButton("Szukaj");
        przyciskSzukaj.addActionListener(e -> {
            /*JOptionPane.showConfirmDialog(panelWyszukiwania, "Lot z: " + poleTekstoweSkad.getText() + " do: " +
                    poleTekstoweDokad.getText(), "Uwaga!", JOptionPane.PLAIN_MESSAGE);*/
            aktualizujListeLotow(poleTekstoweSkad.getText(), poleTekstoweDokad.getText());


        });


        panelWyszukiwania.add(przyciskSzukaj);

        return panelWyszukiwania;
    }

    private boolean niepusty(String skad) {
        return skad != null && !skad.isEmpty();
    }


    private void aktualizujListeLotow(String skad, String dokad) {
        WyszukajLoty wyszukajLoty = new WyszukajLoty();
        Map<String, Object> context = new HashMap<>();

        if (niepusty(skad)) {
            context.put("skad", skad);
        }
        if (niepusty(dokad)) {
            context.put("dokad", dokad);
        }

        wyszukajLoty.execute(context);
        List<Lot> loty = (List<Lot>) context.get("wynik");

        for (Lot lot : loty) {

        }

    }


    private JPanel stworzPanelWynikowWyszukiwania() {
        JPanel panelWynikowWyszukiwania = new JPanel();
        JList<Lot> listaLotow = new JList<>();
        listaLotow.setVisibleRowCount(10);
        panelWynikowWyszukiwania.add(new JScrollPane(listaLotow));
        panelWynikowWyszukiwania.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Znalezione loty"));

        return panelWynikowWyszukiwania;
    }

    private JPanel stworzPanelEdycji() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Edycja lotu"));
        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Id:", JLabel.RIGHT));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Skąd:", JLabel.RIGHT));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Dokąd:", JLabel.RIGHT));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Data wylotu:", JLabel.RIGHT));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Cena:", JLabel.RIGHT));
        panel.add(new JTextField(20));
        panel.add(new JLabel(""));
        panel.add(new JButton("Zapisz"));

        return panel;

    }




    public static void main(String[] args) {
        new OknoLoty();
    }
}
