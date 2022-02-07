package com.ncongwanegeorgecoding.creche.database;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Connection {
    public java.sql.Connection connection() throws SQLException{
    java.sql.Connection con = null;
    try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/creche","root","");
}catch(SQLException exception){
        JOptionPane.showMessageDialog(null, "Error: "+exception,"Error Occured!",JOptionPane.ERROR);
}
     return con;
}
}
