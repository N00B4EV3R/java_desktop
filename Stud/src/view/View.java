package view;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import SQL.MySql;

public class View extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                View frame = new View();
                frame.setVisible(true);

            }
        });
    }

    private String[] collumns = {"Denumire", "Pret", "Varsta_minima", "Cantitate", "Produse_vandute", "Tara"};
    private MySql sql = new MySql();

    public View(){
        initUI();
    }

    private void initUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 450);
        setTitle("Jucarii");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        getContentPane().add(new PanelMain());
        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        homeMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PanelMain pm = new PanelMain();
                getContentPane().removeAll();
                getContentPane().add(pm, BorderLayout.CENTER);
                getContentPane().doLayout();
            }
        });
//        //Panel1
//        JMenuItem menu1 = new JMenuItem("Menu 1 - Panel 1");
//        menu1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                PanelFrame1 panel1= new PanelFrame1();
//                panel1.setBorder(BorderFactory.createTitledBorder("Panel 1"));
//                getContentPane().removeAll();
//                getContentPane().add(panel1, BorderLayout.CENTER);
//                getContentPane().doLayout();
//            }
//        });


        //Afisare
        JMenu display = new JMenu("Afisare");

        JMenuItem allToys = new JMenuItem("Toate jucariile");
        allToys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllToysFrame allToysFrame = new AllToysFrame();
            //    allToysFrame.setBorder(BorderFactory.createTitledBorder("Afisarea tuturor jucariilor"));
                getCont(allToysFrame, "Afisarea tuturor jucariilor");
            }
        });

        JMenuItem cheapestExpensiveToy = new JMenuItem("Cea mai scumpa si ieftina jucarie");
        cheapestExpensiveToy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheapestExpensiveToyFrame cheapestExpensiveToyFrame = new CheapestExpensiveToyFrame();
      //          cheapestExpensiveToyFrame.setBorder(BorderFactory.createTitledBorder("Afisarea celei mai scumpe si ieftine jucarii"));
                getCont(cheapestExpensiveToyFrame, "Afisarea celei mai scumpe si ieftine jucarii");
            }
        });

        JMenuItem allDolls = new JMenuItem("Toate papusile");
        allDolls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllDollsFrame allDollsFrame = new AllDollsFrame();
           //     allDollsFrame.setBorder(BorderFactory.createTitledBorder("Afisarea tuturor papusilor"));
                getCont(allDollsFrame, "Afisarea tuturor papusilor");
            }
        });

        JMenuItem avgPriceByCountry = new JMenuItem("Pretul mediu dupa tara");
        avgPriceByCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AvgPriceByCountryFrame avgPriceByCountryFrame = new AvgPriceByCountryFrame();
             //   avgPriceByCountryFrame.setBorder(BorderFactory.createTitledBorder("Afisarea pretului mediu dupa tara precizata"));
                getCont(avgPriceByCountryFrame, "Afisarea pretului mediu dupa tara precizata");
            }
        });

        JMenuItem n1n2X = new JMenuItem("Dupa pretul si varsta specificata");
        n1n2X.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                N1N2XFrame n1N2XFrame = new N1N2XFrame();
            //    n1N2X.setBorder(BorderFactory.createTitledBorder("Afisarea jucariilor cu pretul si varsta specificata"));
                getCont(n1N2XFrame, "Afisarea jucariilor cu pretul si varsta specificata");
            }
        });

        JMenuItem countToys = new JMenuItem("Numarul de produse vandute");
        countToys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountToysFrame ct = new CountToysFrame();
                getCont(ct, "Afisarea numarului de produse vandute");
            }
        });
        display.add(allToys);
        display.add(cheapestExpensiveToy);
        display.add(allDolls);
        display.add(avgPriceByCountry);
        display.add(n1n2X);
        display.add(countToys);



        //Actiuni jucarii
        JMenu actions = new JMenu("Actiuni jucarii");
        JMenuItem addToy = new JMenuItem("Adauga");
        addToy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddToyFrame at = new AddToyFrame();
                getCont(at, "Adaugare jucarie");
            }
        });

        JMenuItem editToy = new JMenuItem("Editeaza");
        editToy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditToyFrame editToyFrame = new EditToyFrame();
                getCont(editToyFrame, "Editare jucarie");
            }
        });
        JMenuItem delToy = new JMenuItem("Sterge");
        delToy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteToyFrame deleteToyFrame = new DeleteToyFrame();
                getCont(deleteToyFrame, "Stergere jucarie");
            }
        });
        actions.add(addToy);
        actions.add(editToy);
        actions.add(delToy);



        //TABELE
        JMenu tables = new JMenu("Creare tabele");
        JMenuItem mdTable = new JMenuItem("Moldova");
        mdTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MdTableFrame mdTableFrame = new MdTableFrame();
                getCont(mdTableFrame, "Crearea si afisarea unui tabel cu toate produsele fabricate in Moldova");
            }
        });

        JMenuItem popularityTable = new JMenuItem("Produse populare");
        popularityTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PopularityTableFrame popularityTableFrame = new PopularityTableFrame();
                getCont(popularityTableFrame, "Crearea si afisarea unui tabel in care jucariile sunt ordonate dupa popularitate");
            }
        });
        tables.add(mdTable);
        tables.add(popularityTable);

        JMenu exportMenu = new JMenu("Export");
        exportMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(exportMenu, "Exportare in fisier txt");
            }
        });

//        menuMain.add(menu1);
//        menuMain.add(menu2);
//        menuMain.add(menu3);
        menuBar.add(homeMenu);
        menuBar.add(display);
        menuBar.add(actions);
        menuBar.add(tables);
        menuBar.add(exportMenu);

        setJMenuBar(menuBar);

   //     pack();
        setVisible(true);
    }

    private void getCont(JPanel panel, String str){
        panel.setBorder(BorderFactory.createTitledBorder(str));
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        ImageIcon logo = new ImageIcon("images/logo.png");
        int rap = (panel.getWidth() - logo.getIconWidth()) / 2;
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(rap,20, logo.getIconWidth(), logo.getIconHeight());
        panel.add(logoLabel);
    }

    public class PanelMain extends JPanel{
        public PanelMain(){
            setLayout(null);
//
//            JLabel lblPanelMain = new JLabel("Panel Main");
//            lblPanelMain.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            lblPanelMain.setBounds(10, 10, 200, 31);
//            add(lblPanelMain);
            ImageIcon icon = new ImageIcon("images/home.png");
            JLabel poza = new JLabel(icon);
            poza.setBounds(10, 55, icon.getIconWidth(), icon.getIconHeight());
            add(poza);
        }
    }

    public class AllToysFrame extends JPanel{
        AllToysFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
           // label.setHorizontalAlignment(SwingConstants.NORTH);
            label.setFont(new Font("Tahoma", Font.PLAIN, 2));
            label.setBounds(111, 43, 239, 31);
            add(label);
            JTable table;
            Connection conn = sql.createConnection();
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("select den, pret, varsta, cantitate, pr_vandute, tara from datele");
                DefaultTableModel model = new DefaultTableModel();



                model.setColumnIdentifiers(collumns);
                while(rs.next()){
                   // data.add(new ArrayList<>());
                    ArrayList<String> row = new ArrayList<>();
                    row.add(rs.getString("den"));
                    row.add(rs.getString("pret"));
                    row.add(rs.getString("varsta"));
                    row.add(rs.getString("cantitate"));
                    row.add(rs.getString("pr_vandute"));
                    row.add(rs.getString("tara"));
                    data.add(row);
                }

                rs.close();
                stm.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            String[][] dataArray = new String[data.size()][];
//            for (int i = 0; i < data.size(); i++) {
//                ArrayList<String> rowA = data.get(i);
//                dataArray[i] = rowA.toArray(new String[0]);
//            }
//            table=new JTable(dataArray,collumns);
//          //  add(table);
//            JScrollPane sp = new JScrollPane(table);
//            add(sp);

            String[][] dataTest = {
                    { "Kundan Kumar Jha", "4031", "CSE" },
                    { "Anand Jha", "6014", "IT" }
            };

            // Column Names
            String[] columnNames = { "Name", "Roll Number", "Department" };

            // Initializing the JTable
            JTable j = new JTable(dataTest, columnNames);
            j.setBounds(30, 40, 200, 300);

            // adding it to JScrollPane
            JScrollPane sp = new JScrollPane(j);
            add(sp);
    setVisible(true);

        }
    }
    public class CheapestExpensiveToyFrame extends JPanel{
        CheapestExpensiveToyFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }
    public class AllDollsFrame extends JPanel{
        AllDollsFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public class AvgPriceByCountryFrame extends JPanel{
        AvgPriceByCountryFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public class N1N2XFrame extends JPanel{
        N1N2XFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public class CountToysFrame extends JPanel{
        CountToysFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public class AddToyFrame extends JPanel{
        AddToyFrame(){
            setLayout(null);
            JPanel formular = new JPanel();
            formular.setLayout(new GridLayout(6,2,10,10));
            formular.setBounds(getMiddle(230),110,230,200);
            JTextField nameTF = new JTextField(20);
            JTextField priceTF = new JTextField(20);
            JTextField ageTF = new JTextField(20);
            JTextField quantityTF = new JTextField(20);
            JTextField productsSoldTF = new JTextField(20);
            JTextField countryTF = new JTextField(20);
            formular.add(new JLabel("Denumire: ")); formular.add(nameTF);
            formular.add(new JLabel("Pret: ")); formular.add(priceTF);
            formular.add(new JLabel("Varsta: ")); formular.add(ageTF);
            formular.add(new JLabel("Cantitate: ")); formular.add(quantityTF);
            formular.add(new JLabel("Produse vandute: ")); formular.add(productsSoldTF);
            formular.add(new JLabel("Tara: ")); formular.add(countryTF);
            JButton editButton = new JButton("Adauga");
            editButton.setBounds(getMiddle(100), 320, 100, 30);
            formular.setVisible(true);
            add(formular);
            add(editButton);
        }
    }

    public class EditToyFrame extends JPanel{
        EditToyFrame(){
            setLayout(null);
//            JLabel label = new JLabel(getClass().getName());
//            label.setHorizontalAlignment(SwingConstants.CENTER);
//            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            label.setBounds(111, 43, 239, 31);
//            add(label);
            int latime=0;
            latime = getMiddle(230);
            JPanel formular = new JPanel();
            formular.setLayout(new GridLayout(6,2,10,10));
            formular.setBounds(latime,110,230,200);
            JTextField nameTF = new JTextField(20);
            JTextField priceTF = new JTextField(20);
            JTextField ageTF = new JTextField(20);
            JTextField quantityTF = new JTextField(20);
            JTextField productsSoldTF = new JTextField(20);
            JTextField countryTF = new JTextField(20);
            formular.add(new JLabel("Denumire: ")); formular.add(nameTF);
            formular.add(new JLabel("Pret: ")); formular.add(priceTF);
            formular.add(new JLabel("Varsta: ")); formular.add(ageTF);
            formular.add(new JLabel("Cantitate: ")); formular.add(quantityTF);
            formular.add(new JLabel("Produse vandute: ")); formular.add(productsSoldTF);
            formular.add(new JLabel("Tara: ")); formular.add(countryTF);
            JButton editButton = new JButton("Editeaza");
            editButton.setBounds(getMiddle(100), 320, 100, 30);
            formular.setVisible(true);
            add(formular);
            add(editButton);
        }
    }

    public class DeleteToyFrame extends JPanel{
        DeleteToyFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public class MdTableFrame extends JPanel{
        MdTableFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public class PopularityTableFrame extends JPanel{
        PopularityTableFrame(){
            setLayout(null);
            JLabel label = new JLabel(getClass().getName());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            label.setBounds(111, 43, 239, 31);
            add(label);
        }
    }

    public int getLatime(){
        return getWidth();
    }

    public int getMiddle(int i){
        return (getWidth()-i)/2;
    }
}