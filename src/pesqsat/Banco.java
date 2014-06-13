/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pesqsat;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author cpd
 */
public class Banco {
    final String url = "jdbc:mysql://172.16.111.91:3306/iesi-pesquisa-satisfacao";
    final String usuario = "cpd";
    final String senha = "*(iesifunc(*";
    Connection con = null;
    Statement comando;
    ResultSet resultado;
        
    public boolean abrir(){
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection(url,usuario,senha);
            comando = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            return true;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver de conexão não encontrado!");
            return false;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados!");
            return false;
        }
    }
    
    public boolean executar(String query, Object... o){
        try {
            return comando.execute(String.format(Locale.US, query, o));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a consulta!");
            return false;
        }
    }
    
    public ResultSet consultar(String query, Object... o){
        try {
            return comando.executeQuery(String.format(Locale.US, query, o));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a consulta!");
            return null;
        }
    }
    
    public void fechar(){
        try {
            comando.close();
            con.close();
        } catch (SQLException e) {
        }
    }
}
