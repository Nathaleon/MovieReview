/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOmovie.movieDAO;
import DAOImplement.movieimplement;
import model.*;
import view.MainView;

/**
 *
 * @author Nathaleon
 */
public class moviecontroller {
    MainView frame;
    movieimplement implmovie;
    List<movie> dp;
    
    public moviecontroller(MainView frame){
        this.frame = frame;
        implmovie = new movieDAO();
        dp = implmovie.getAll();
    }
    public void isitabel(){
        dp = implmovie.getAll();
        modeltabelmovie mp = new modeltabelmovie(dp);
        frame.getTabelmovie().setModel(mp);
    }
    
    public void insert(){
         movie dp = new movie();
         dp.setJudul(frame.getJTxtjudul().getText());
         dp.setAlur(Double.parseDouble(frame.getJTxtalur().getText()));
         dp.setPenokohan(Double.parseDouble(frame.getJTxtpenokohan().getText()));
         dp.setAkting(Double.parseDouble(frame.getJTxtakting().getText()));
         implmovie.insert(dp);
    }
    
    public void update(){
        movie dp = new movie();
         dp.setJudul(frame.getJTxtjudul().getText());
         dp.setAlur(Double.parseDouble(frame.getJTxtalur().getText()));
         dp.setPenokohan(Double.parseDouble(frame.getJTxtpenokohan().getText()));
         dp.setAkting(Double.parseDouble(frame.getJTxtakting().getText()));
         implmovie.update(dp);
    }
    
    public void delete(){
        String judul = frame.getJTxtjudul().getText();
        implmovie.delete(judul);
    }
    
    public void clearFields() {
        frame.getJTxtjudul().setText("");
        frame.getJTxtalur().setText("");
        frame.getJTxtpenokohan().setText("");
        frame.getJTxtakting().setText("");
    }   
    
}
