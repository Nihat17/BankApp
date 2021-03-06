/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marshall
 */
public class MainMenu extends javax.swing.JFrame implements Serializable{

    private Bank bank;
    private String saveLocation = null;
    
    public MainMenu() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        bank = new Bank();
        reloadTable();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        AddAccountButton = new javax.swing.JButton();
        RemoveAccountButton = new javax.swing.JButton();
        DepositButton = new javax.swing.JButton();
        WithdrawalButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        MenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        ExitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bank Application");
        setName(""); // NOI18N

        AddAccountButton.setText("Add Account");
        AddAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAccountButtonActionPerformed(evt);
            }
        });

        RemoveAccountButton.setText("Remove Account");
        RemoveAccountButton.setEnabled(false);
        RemoveAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAccountButtonActionPerformed(evt);
            }
        });

        DepositButton.setText("Deposit");
        DepositButton.setEnabled(false);
        DepositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepositButtonActionPerformed(evt);
            }
        });

        WithdrawalButton.setText("Withdrawal");
        WithdrawalButton.setEnabled(false);
        WithdrawalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithdrawalButtonActionPerformed(evt);
            }
        });

        accountTable.setAutoCreateRowSorter(true);
        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Account Number", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accountTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accountTable.getTableHeader().setReorderingAllowed(false);
        accountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(accountTable);
        if (accountTable.getColumnModel().getColumnCount() > 0) {
            accountTable.getColumnModel().getColumn(0).setResizable(false);
            accountTable.getColumnModel().getColumn(1).setResizable(false);
            accountTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(AddAccountButton)
                        .addGap(18, 18, 18)
                        .addComponent(RemoveAccountButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DepositButton)
                        .addGap(18, 18, 18)
                        .addComponent(WithdrawalButton)))
                .addGap(36, 36, 36))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoveAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WithdrawalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        ExitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bankapp/if_icons_exit_1564505.png"))); // NOI18N
        ExitMenuItem.setText("Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(ExitMenuItem);

        MenuBar.add(fileMenu);

        editMenu.setText("Edit");
        MenuBar.add(editMenu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_ExitMenuItemActionPerformed

    private void AddAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAccountButtonActionPerformed
        AddAccountMenu menu = new AddAccountMenu(this, true, bank);
        menu.setVisible(true);
        
        if(menu.getCustomer() != null)
            addCustomerToTable(menu.getCustomer());
    }//GEN-LAST:event_AddAccountButtonActionPerformed
    
    private void RemoveAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAccountButtonActionPerformed
       int selectedRow = accountTable.getSelectedRow();
       
       if(selectedRow  >= 0){
           Customer customer = getSelectedCustomer(selectedRow);
           bank.closeAccount(customer.getAccount().getAccountNumber()); 
           removeCustomerFromTable(selectedRow);
       }
       
    }//GEN-LAST:event_RemoveAccountButtonActionPerformed

    private void DepositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepositButtonActionPerformed
      int selectedRow = checkIfFieldIsChosen();
      Customer customer = null;
      if(selectedRow >= 0){
          customer = getSelectedCustomer(selectedRow);          
          double customerBalance = (double) customer.getAccount().getBalance();
          
          DepositMenu menu = new DepositMenu(this, true, bank,customer);
          menu.setVisible(true);         
          
      }      
      double customerBalance = (double) customer.getAccount().getBalance();
      customer = bank.getCustomer(customer.getAccount().getAccountNumber());      
      updateCustomerList(customerBalance, customer);
          
    }//GEN-LAST:event_DepositButtonActionPerformed

    private void WithdrawalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithdrawalButtonActionPerformed
      int selectedRow = checkIfFieldIsChosen();
      if(selectedRow >= 0){
          Customer customer = getSelectedCustomer(selectedRow);          
          double customerBalance = (double) customer.getAccount().getBalance();
          
          WithdrawMenu menu = new WithdrawMenu(this, true, customer, bank);
          menu.setVisible(true);         
          customer = bank.getCustomer(customer.getAccount().getAccountNumber());
          updateCustomerList(customerBalance, customer);
      }
    }//GEN-LAST:event_WithdrawalButtonActionPerformed

    private void accountTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountTableMouseClicked
       setAccountButtonsActive(true);
       
       Customer customer = getSelectedCustomer(accountTable.getSelectedRow());
       
       if(evt.getClickCount() == 2){
           AccountDetailsPage page = new AccountDetailsPage(this, true, customer, bank);
           page.setVisible(true);
       }
    }//GEN-LAST:event_accountTableMouseClicked

     private boolean saveFile(String fileName){
         try {
               FileOutputStream fOut = new FileOutputStream(fileName);                             
               ObjectOutputStream objOut = new ObjectOutputStream(fOut);               
               objOut.writeObject(bank);
               objOut.close();
               fOut.close();              
               return true;
           } catch (FileNotFoundException ex) {
               return false;
           } catch (IOException ex) {
               return false;
           }
     }
     
     private void reloadTable() throws SQLException {
         for(Customer c : bank.getCustomers())
             addCustomerToTable(c);
    }
     
    private void setAccountButtonsActive(boolean active){
        DepositButton.setEnabled(active);
        WithdrawalButton.setEnabled(active);
        RemoveAccountButton.setEnabled(active);
    }
    
    private void updateCustomerList(double customerBalance, Customer customer) {
        if(customerBalance != customer.getAccount().getBalance()){
            DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
            int selectedRow = accountTable.getSelectedRow();
            model.setValueAt(customer.getFirstName(), selectedRow, 0);
            model.setValueAt(customer.getLastName(), selectedRow, 1);
            model.setValueAt(customer.getAccount().getAccountNumber(), selectedRow, 2);
            model.setValueAt(String.format("%.2f", customer.getAccount().getBalance()), selectedRow, 3);
        }
    }
    
    private int checkIfFieldIsChosen() {
       int selectedRow = accountTable.getSelectedRow();       
       if(selectedRow < 0){           
           JOptionPane.showMessageDialog(this,"Please select an account first!", "Warning!",
                   JOptionPane.WARNING_MESSAGE);                      
       }      
       return selectedRow;
    }
    
    private Customer getSelectedCustomer(int selectedRow){        
        Customer customer = null;
        if(selectedRow >= 0){
            int accountNumber = (int) accountTable.getValueAt(selectedRow, 2);
            customer = bank.getCustomer(accountNumber);
        }
        return customer;
    } 
    
    private void removeCustomerFromTable(int row){
       DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
       model.removeRow(row);
    }
        
    private void addCustomerToTable(Customer customer){
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();         
        model.addRow(new Object [] {customer.getFirstName(), customer.getLastName(),
            customer.getAccount().getAccountNumber(), customer.getAccount().getBalance()});
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainMenu().setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAccountButton;
    private javax.swing.JButton DepositButton;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JButton RemoveAccountButton;
    private javax.swing.JButton WithdrawalButton;
    private javax.swing.JTable accountTable;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
