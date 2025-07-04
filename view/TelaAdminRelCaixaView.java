package view;

import controller.Caixa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

/**
 * Tela de relatório do caixa da cantina.
 * 
 * <p>
 * Exibe, em uma tabela, a quantidade de cada cédula/moeda disponível no caixa,
 * além do saldo total em dinheiro. Possui um botão para recarregar os dados da tabela em tempo real.
 * </p>
 * 
 * <ul>
 *   <li>Recebe o objeto {@link Caixa} para obter as informações financeiras atuais.</li>
 *   <li>Mostra a situação do caixa (quantidade por valor e saldo total).</li>
 *   <li>Permite atualizar os dados manualmente através do botão "Recarregar".</li>
 * </ul>
 * 
 * @author Seu Nome
 * @version 1.0
 */

public class TelaAdminRelCaixaView extends JFrame {
    /** Referência ao caixa da cantina. */
    private Caixa caixa;

    /** Modelo da tabela de valores e quantidades. */
    private DefaultTableModel tableModel;

    /** Tabela que exibe os dados do caixa. */
    private JTable table;

    /** Label que exibe o saldo total do caixa. */
    private JLabel lblTotal;

    /**
     * Construtor da tela de relatório do caixa.
     *
     * @param caixa o caixa da cantina a ser analisado
     */
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

    /**
     * Atualiza a tabela e o saldo total com os valores mais recentes do caixa.
     */
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
