package gui;

import main.WyszukajLoty;
import model.Lot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OknoLoty extends JFrame {

    private DefaultListModel<Lot> lotyListModel;
    private JTextField poleId;
    private JTextField poleSkad;
    private JTextField poleDokad;
    private JTextField poleDataWylotu;
    private JTextField poleCena;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

        lotyListModel.removeAllElements();
        for (Lot lot : loty) {
            lotyListModel.addElement(lot);
        }

    }


    private JPanel stworzPanelWynikowWyszukiwania() {

        JPanel panelWynikowWyszukiwania = new JPanel();

        lotyListModel = new DefaultListModel<>();

        final JList<Lot> listaLotow = new JList<>(lotyListModel);
        listaLotow.setVisibleRowCount(10);
        listaLotow.setPreferredSize(new Dimension(100, 150));
        listaLotow.addMouseListener(new MouseListener() {
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int wybranyIndeks = listaLotow.locationToIndex(e.getPoint());
                    Lot lot = lotyListModel.getElementAt(wybranyIndeks);
                    pokazWybranyLot(lot);
                }
            }
        });

        panelWynikowWyszukiwania.add(new JScrollPane(listaLotow));
        // panelWynikowWyszukiwania.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Znalezione loty"));

        return panelWynikowWyszukiwania;
    }

    private JPanel stworzPanelEdycji() {
        JPanel panelEdycji = new JPanel();
        panelEdycji.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Edycja lotu"));
        panelEdycji.setLayout(new GridLayout(6, 2, 5, 5));

        panelEdycji.add(new JLabel("Id:", JLabel.RIGHT));
        poleId = new JTextField(20);
        poleId.setEnabled(false);
        panelEdycji.add(poleId);

        panelEdycji.add(new JLabel("Skąd:", JLabel.RIGHT));
        poleSkad = new JTextField(20);
        panelEdycji.add(poleSkad);

        panelEdycji.add(new JLabel("Dokąd:", JLabel.RIGHT));
        poleDokad = new JTextField(20);
        panelEdycji.add(poleDokad);

        panelEdycji.add(new JLabel("Data wylotu:", JLabel.RIGHT));
        poleDataWylotu = new JTextField(20);
        panelEdycji.add(poleDataWylotu);

        panelEdycji.add(new JLabel("Cena:", JLabel.RIGHT));
        poleCena = new JTextField(20);
        panelEdycji.add(poleCena);

        panelEdycji.add(new JLabel(""));
        panelEdycji.add(new JButton("Zapisz"));

        return panelEdycji;

    }


    private void pokazWybranyLot(Lot lot) {
        poleId.setText(lot.getId().toString());
        poleSkad.setText(lot.getSkad());
        poleDokad.setText(lot.getDokad());

        poleDataWylotu.setText(dateFormat.format(lot.getDataWylotu()));
        poleCena.setText(NumberFormat.getCurrencyInstance().format(lot.getCena()));
    }


    public static void main(String[] args) {
        new OknoLoty();
    }
}
