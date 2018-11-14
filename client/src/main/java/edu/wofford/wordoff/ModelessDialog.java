package edu.wofford.wordoff;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.table.AbstractTableModel;

public class ModelessDialog extends JDialog{
  private JDialog modelessDialog;

  public ModelessDialog(JFrame parent) {
    modelessDialog = new JDialog(parent);
    modelessDialog.add(Box.createRigidArea(new Dimension(200, 200)));

    TableModel dataModel = new AbstractTableModel() {
          public int getColumnCount() { return 4; }
          public int getRowCount() { return 6;}
          public Object getValueAt(int row, int col) { return new Integer(row*col); }
      };

    JTable leaderboard = new JTable(dataModel);

    leaderboard.setName("leaderboard");
    modelessDialog.setTitle("Leaderboard");
    modelessDialog.add(leaderboard);
    modelessDialog.pack();
    modelessDialog.setVisible(true);
  }

}
