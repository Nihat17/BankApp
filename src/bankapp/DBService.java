package bankapp;


import bankapp.Account;
import bankapp.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {
   
    String url = "jdbc:mysql://localhost:3306/bankdb";
    String user = "bank";
    String password = "securepassword";
    
    private Connection connect(){
        Connection connection = null;  
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            Properties info = new Properties();
            info.put("user", "bank");
            info.put("password", "securepassword");
            info.put("useSSL", "false");
            info.put("autoReconnect", "true");
            
            connection = DriverManager.getConnection(url, info);
        } catch (SQLException ex) {
            connection = null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;                     
    }
    int AddAccount(String firstName, String lastName, String ssn, AccountType accountType, Double balance){
        int userId = -1;
        int accountId = -1;
        Connection connection = connect();
        try {
            //Add User
            connection.setAutoCommit(false);
            String addUserSql = "INSERT INTO Users(FirstName, LastName, SSN) VALUES(?,?,?)";
            PreparedStatement addUser = connection
                    .prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
            addUser.setString(1, firstName);
            addUser.setString(2, lastName);
            addUser.setString(3, ssn);
            addUser.executeUpdate();
            ResultSet addUserResults = addUser.getGeneratedKeys();
            if(addUserResults.next()){
                userId = addUserResults.getInt(1);
            }
            //Add Account
            String addAccountSql = "INSERT INTO Accounts(Type, Balance) VALUES(?,?)";
            PreparedStatement addAccount = connection
                    .prepareStatement(addAccountSql, Statement.RETURN_GENERATED_KEYS);
            addAccount.setString(1, accountType.name());
            addAccount.setDouble(2, balance);
            addAccount.executeUpdate();
            ResultSet addAccountResults = addAccount.getGeneratedKeys();
            if(addAccountResults.next()){
                accountId = addAccountResults.getInt(1);
            }
            //Link User to Account
            if(userId > 0 && accountId > 0){
                String addMappingSql = "INSERT INTO mappings (UserId, AccountID) VALUES (?,?)";
                PreparedStatement addMappings = connection.
                        prepareStatement(addMappingSql, Statement.RETURN_GENERATED_KEYS);
                addMappings.setInt(1, userId);
                addMappings.setInt(2, accountId);
                addMappings.executeUpdate();
                connection.commit();
                
            }   
            else{
                connection.rollback();
            }
            connection.close();
        } catch (SQLException ex) {
            System.err.println("An error has occured: " + ex.getMessage());
        }
        return accountId;
    }
    
    Customer getAccount(int accountId){
        Customer customer = null;
        Connection connection = connect();
        
            String findUserSql = "SELECT FirstName, LastName, SSN, Type, Balance "
            + "FROM Users a JOIN mappings b ON a.ID = b.UserId "
            + "JOIN Accounts c on c.ID = b.AccountID "
            + "WHERE c.ID = ?;";
            
        try {
            PreparedStatement getUser = connection.prepareStatement(findUserSql);
            getUser.setInt(1, accountId);
            ResultSet findUserResults = getUser.executeQuery();
            
            if(findUserResults.next()){
              String firstName = findUserResults.getString("FirstName");
              String lastName = findUserResults.getString("LastName");
              String ssn = findUserResults.getString("SSN");
              AccountType accountType = AccountType.valueOf(findUserResults.getString("Type"));
              double balance = findUserResults.getDouble("Balance");
              Account account;
              if(accountType == AccountType.Checking){
                  account = new Checking(accountId, balance);
              }
              else if(accountType == AccountType.Savings){
                  account = new Savings(accountId, balance);
              }
              else{
                  throw new Exception("Unvalid account type.");
              }
              customer = new Customer(firstName, lastName, ssn, account);
            }
            else{
                System.err.println("No user account was found for ID " + accountId);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return customer;
    }
    
    //UPDATE
    boolean updateAccount (int accountId, double balance){
        boolean success = false;
        Connection connection = connect();
        String updateSql = "UPDATE Accounts SET Balance = ? WHERE ID = ?";
        try {
            PreparedStatement updateBalance = connection.prepareStatement(updateSql);
            updateBalance.setDouble(1, balance);
            updateBalance.setInt(2, accountId);
            updateBalance.executeUpdate();
            success = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }
    //DELETE
     boolean deleteAccount (int accountId){
        boolean success = false;
        Connection connection = connect();
        String deleteSql = "DELETE Users, Accounts "
            + "FROM Users JOIN mappings ON Users.ID = mappings.UserId "
            + "JOIN Accounts on Accounts.ID = mappings.AccountID "
            + "WHERE Accounts.ID = ?";
        try {
            PreparedStatement deleteAccount = connection.prepareStatement(deleteSql);            
            deleteAccount.setInt(1, accountId);
            deleteAccount.executeUpdate();
            success = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }
     //Get All accounts
     ArrayList<Customer> getAllAccounts() throws SQLException{
         ArrayList<Customer> customers = new ArrayList<>();
         Connection connection = connect();
         Customer customer;
         String findAllUserSql = "SELECT accountId, FirstName, LastName, SSN, Type, Balance "
            + "FROM Users a JOIN mappings b ON a.ID = b.UserId "
            + "JOIN Accounts c on c.ID = b.AccountID;";
           
       
            PreparedStatement findAllUsers = null;
        try {
            findAllUsers = connection.prepareStatement(findAllUserSql);
        
            ResultSet findUserResults = findAllUsers.executeQuery();
            
           while(findUserResults.next()){
              String firstName = findUserResults.getString("FirstName");
              String lastName = findUserResults.getString("LastName");
              String ssn = findUserResults.getString("SSN");
              AccountType accountType = AccountType.valueOf(findUserResults.getString("Type"));
              double balance = findUserResults.getDouble("Balance");
              int accountId = findUserResults.getInt("AccountId");
              Account account;
              if(accountType == AccountType.Checking){
                  account = new Checking(accountId, balance);
              }
              else if(accountType == AccountType.Savings){
                  account = new Savings(accountId, balance);
              }
              else{
                  throw new Exception("Unvalid account type.");
              }
              customers.add(new Customer(firstName, lastName, ssn, account));
           }
           }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
         return customers;
     }
}
