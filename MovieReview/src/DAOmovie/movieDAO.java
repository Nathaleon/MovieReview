/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOmovie;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.movieimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nathaleon
 */
public class movieDAO implements movieimplement{
    Connection connection;
    
    final String select = "SELECT * FROM `movie`;";
    final String insert = "INSERT INTO `movie`(`judul`, `alur`, `penokohan`, `akting`, `nilai`) VALUES( ?,?,?,?,?);";
    final String update = "update movie set alur=?, penokohan=?, akting=?, nilai=? where judul=?";
    final String delete = "delete from movie where judul=?";
    public movieDAO(){
        connection = connector.connection();
    }
    
    @Override
    public void insert(movie p) {
        PreparedStatement statement = null;
        try{
            double nilai = (p.getAlur() + p.getPenokohan() + p.getAkting()) / 3.0;
            
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString( 1, p.getJudul());
            statement.setDouble( 2, p.getAlur());
            statement.setDouble( 3, p.getPenokohan());
            statement.setDouble( 4, p.getAkting());
            statement.setDouble( 5, nilai);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(movie p) {
        PreparedStatement statement = null;
        try{
            double nilai = (p.getAlur() + p.getPenokohan() + p.getAkting()) / 3.0;
            
            statement = connection.prepareStatement(update);
            statement.setDouble( 1, p.getAlur());
            statement.setDouble( 2, p.getPenokohan());
            statement.setDouble( 3, p.getAkting());
            statement.setDouble( 4, nilai);
            statement.setString( 5, p.getJudul());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1,judul);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<movie> getAll() {
        List<movie> dp = null;
        try{
            dp = new ArrayList<movie>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                movie film = new movie();
                film.setJudul(rs.getString("judul"));
                film.setAlur(rs.getDouble("alur"));
                film.setPenokohan(rs.getDouble("penokohan"));
                film.setAkting(rs.getDouble("akting"));
                film.setNilai(rs.getDouble("nilai"));
                dp.add(film);
            }
        }catch(SQLException ex){
             Logger.getLogger(movieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dp;
    }
}
