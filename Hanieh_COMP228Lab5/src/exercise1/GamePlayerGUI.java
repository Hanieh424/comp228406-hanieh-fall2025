package exercise1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;

public class GamePlayerGUI extends JFrame {

    private JTextField txtFirst, txtLast, txtAddress, txtPostal, txtProvince, txtPhone, txtScore;
    private JComboBox<String> cmbGames;
    private JTable table;
    private JButton btnInsert, btnUpdate, btnReport, btnFilter;

    public GamePlayerGUI() {
        setTitle("Player & Game Registration");
        setSize(800, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Form Panel
        JPanel form = new JPanel(new GridLayout(10, 2,5,5));

        form.add(new JLabel("First Name:"));
        txtFirst = new JTextField(); form.add(txtFirst);

        form.add(new JLabel("Last Name:"));
        txtLast = new JTextField(); form.add(txtLast);

        form.add(new JLabel("Address:"));
        txtAddress = new JTextField(); form.add(txtAddress);

        form.add(new JLabel("Postal Code:"));
        txtPostal = new JTextField(); form.add(txtPostal);

        form.add(new JLabel("Province:"));
        txtProvince = new JTextField(); form.add(txtProvince);

        form.add(new JLabel("Phone Number:"));
        txtPhone = new JTextField(); form.add(txtPhone);

        form.add(new JLabel("Select Game:"));
        cmbGames = new JComboBox<>(); form.add(cmbGames);

        form.add(new JLabel("Score:"));
        txtScore = new JTextField(); form.add(txtScore);

        // Buttons
        btnInsert = new JButton("Register Player");
        btnInsert.addActionListener(e -> insertPlayer());
        form.add(btnInsert);

        btnUpdate = new JButton("Update Player");
        btnUpdate.addActionListener(e -> updateSelectedPlayer());
        form.add(btnUpdate);

        btnReport = new JButton("Show All Report");
        btnReport.addActionListener(e -> loadReport());
        form.add(btnReport);

        btnFilter = new JButton("Filter by Player ID");
        btnFilter.addActionListener(e -> filterByPlayerId());
        form.add(btnFilter);

        add(form, BorderLayout.NORTH);

        // Table
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load games and report
        loadGames();
        loadReport();

        // Populate fields when row selected
        table.getSelectionModel().addListSelectionListener(e -> {
            int r = table.getSelectedRow();
            if(r >= 0) {
                txtFirst.setText(table.getValueAt(r,1).toString());
                txtLast.setText(table.getValueAt(r,2).toString());
                txtAddress.setText(table.getValueAt(r,3) == null ? "" : table.getValueAt(r,3).toString());
                txtPostal.setText(table.getValueAt(r,4) == null ? "" : table.getValueAt(r,4).toString());
                txtProvince.setText(table.getValueAt(r,5) == null ? "" : table.getValueAt(r,5).toString());
                txtPhone.setText(table.getValueAt(r,6) == null ? "" : table.getValueAt(r,6).toString());
                txtScore.setText(table.getValueAt(r,9) == null ? "" : table.getValueAt(r,9).toString());
            }
        });
    }

    /** Load games into combo box **/
    private void loadGames() {
        cmbGames.removeAllItems();
        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT game_title FROM Game ORDER BY game_title");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cmbGames.addItem(rs.getString("game_title"));
            }
        } catch(Exception e){ e.printStackTrace(); }
    }

    /** Insert player and link game with score **/
    private void insertPlayer() {
        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Player(first_name,last_name,address,postal_code,province,phone_number) VALUES(?,?,?,?,?,?)",
                    new String[]{"player_id"}
            );
            ps.setString(1, txtFirst.getText());
            ps.setString(2, txtLast.getText());
            ps.setString(3, txtAddress.getText());
            ps.setString(4, txtPostal.getText());
            ps.setString(5, txtProvince.getText());
            ps.setString(6, txtPhone.getText());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int playerId = rs.getInt(1);

            int score;
            if(txtScore.getText().trim().isEmpty())
                score = (int)(Math.random()*100);
            else
                score = Integer.parseInt(txtScore.getText().trim());

            PreparedStatement ps2 = conn.prepareStatement(
                    "INSERT INTO PlayerAndGame(player_id, game_id, playing_date, score) " +
                            "VALUES(?, (SELECT game_id FROM Game WHERE game_title=?), SYSDATE, ?)"
            );
            ps2.setInt(1, playerId);
            ps2.setString(2, cmbGames.getSelectedItem().toString());
            ps2.setInt(3, score);
            ps2.executeUpdate();

            JOptionPane.showMessageDialog(this, "Player Registered! Score: " + score);
            loadReport();
        } catch(Exception e){ e.printStackTrace(); }
    }

    /** Update player and score by selected row **/
    private void updateSelectedPlayer() {
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1) { JOptionPane.showMessageDialog(this,"Select a player first!"); return; }

        Object value = table.getValueAt(selectedRow, 0);
        int playerId = 0;
        if (value instanceof BigDecimal) {
            playerId = ((BigDecimal) value).intValue();
        } else if (value instanceof Integer) {
            playerId = (Integer) value;
        }


        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE Player SET first_name=?, last_name=?, address=?, postal_code=?, province=?, phone_number=? WHERE player_id=?"
            );
            ps.setString(1, txtFirst.getText());
            ps.setString(2, txtLast.getText());
            ps.setString(3, txtAddress.getText());
            ps.setString(4, txtPostal.getText());
            ps.setString(5, txtProvince.getText());
            ps.setString(6, txtPhone.getText());
            ps.setInt(7, playerId);
            ps.executeUpdate();

            // Update score in PlayerAndGame for the selected game
            PreparedStatement ps2 = conn.prepareStatement(
                    "UPDATE PlayerAndGame SET score=? WHERE player_id=? AND game_id=(SELECT game_id FROM Game WHERE game_title=?)"
            );
            ps2.setInt(1, Integer.parseInt(txtScore.getText().trim()));
            ps2.setInt(2, playerId);
            ps2.setString(3, cmbGames.getSelectedItem().toString());
            ps2.executeUpdate();

            JOptionPane.showMessageDialog(this, "Player Updated!");
            loadReport();
        } catch(Exception e){ e.printStackTrace(); }
    }

    /** Load report into JTable **/
    private void loadReport() {
        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, " +
                            "p.province, p.phone_number, g.game_title, pag.playing_date, pag.score " +
                            "FROM Player p " +
                            "JOIN PlayerAndGame pag ON p.player_id = pag.player_id " +
                            "JOIN Game g ON g.game_id = pag.game_id"
            );
            ResultSet rs = ps.executeQuery();
            table.setModel(buildTableModel(rs));
        } catch(Exception e){ e.printStackTrace(); }
    }

    /** Filter report by player_id **/
    private void filterByPlayerId() {
        String input = JOptionPane.showInputDialog(this, "Enter Player ID:");
        if(input == null || input.trim().isEmpty()) return;

        try {
            int playerId = Integer.parseInt(input);
            try(Connection conn = DBConnection.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, " +
                                "p.province, p.phone_number, g.game_title, pag.playing_date, pag.score " +
                                "FROM Player p " +
                                "JOIN PlayerAndGame pag ON p.player_id = pag.player_id " +
                                "JOIN Game g ON g.game_id = pag.game_id " +
                                "WHERE p.player_id=?"
                );
                ps.setInt(1, playerId);
                ResultSet rs = ps.executeQuery();
                table.setModel(buildTableModel(rs));
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Invalid Player ID!");
        } catch(Exception e){ e.printStackTrace(); }
    }

    /** Helper to convert ResultSet to TableModel **/
    public static DefaultTableModel buildTableModel(ResultSet rs) throws Exception {
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();

        Vector<String> colNames = new Vector<>();
        for(int i=1;i<=colCount;i++) colNames.add(meta.getColumnName(i));

        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()) {
            Vector<Object> row = new Vector<>();
            for(int i=1;i<=colCount;i++) row.add(rs.getObject(i));
            data.add(row);
        }

        return new DefaultTableModel(data,colNames);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GamePlayerGUI().setVisible(true));
    }
}
