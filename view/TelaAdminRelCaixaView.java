package view;

import controller.Caixa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class TelaAdminRelCaixaView extends JFrame {

    private Caixa caixa;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel lblTotal;

    public TelaAdminRelCaixaView(Caixa caixa) {
        this.caixa = caixa;
        setTitle("Relatório do Caixa");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] colunas = { "Valor", "Quantidade" };
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        lblTotal = new JLabel("Saldo total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton btnRecarregar = new JButton("Recarregar");
        btnRecarregar.addActionListener(e -> atualizarTabela());

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(lblTotal, BorderLayout.WEST);
        panelBottom.add(btnRecarregar, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        atualizarTabela();
    }

    private void atualizarTabela() 
    {
        tableModel.setRowCount(0);
        Map<Double, Integer> dinheiro = caixa.getDinheiro();

        dinheiro.entrySet().stream()
            .sorted((a, b) -> Double.compare(b.getKey(), a.getKey()))
            .forEach(entry -> {
                tableModel.addRow(new Object[] {
                    String.format("R$ %.2f", entry.getKey()),
                    entry.getValue()
                });
            });

        lblTotal.setText(String.format("Saldo total: R$ %.2f", caixa.getSaldo()));
    }
}
