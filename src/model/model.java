/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import view.view;

/**
 *
 * @author LAB 2 PC 13
 */
public class model implements controller{
    String jk;
    @Override
    public void simpan(view siswa) throws SQLException {
         if (siswa.CbL.isSelected()){
        jk = "Laki-Laki";
        }
        else {
        jk = "Perempuan";
        }
        try {
        Connection con = koneksi.getcon();
        String sql = "Insert Into siswa Values(?,?,?,?,?,?)";
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setString(1, siswa.TxtNis.getText());
        prepare.setString(2, siswa.TxtDepan.getText());
        prepare.setString(3, siswa.TxtBelakang.getText());
        prepare.setString(4, jk);
        prepare.setString(5, siswa.TxtNomor.getText());
        prepare.setString(6, siswa.TxtAlamat.getText());
        prepare.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        prepare.close();
            reset(siswa);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
        tampil(siswa);
        siswa.setLebarKolom();
                }
    }

    @Override
    public void ubah(view siswa) throws SQLException {         
        if (siswa.CbL.isSelected()){
        jk = "Laki-Laki";
        }
        else {
        jk = "Perempuan";
        }
        try {
        Connection con = koneksi.getcon();
        String sql = "UPDATE siswa SET nama_depan=?,nama_belakang=?, jenis_kelamin=?,nomor_telepon=?,alamat=?, NIS=?";
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setString(6, siswa.TxtNis.getText());
        prepare.setString(1, siswa.TxtDepan.getText());
        prepare.setString(2, siswa.TxtBelakang.getText());
        prepare.setString(3, jk);
        prepare.setString(4, siswa.TxtNomor.getText());
        prepare.setString(5, siswa.TxtAlamat.getText());
        prepare.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            reset(siswa);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
        tampil(siswa);
        siswa.setLebarKolom();
                }
    }

    @Override
    public void hapus(view siswa) throws SQLException {
               try {
            Connection con = koneksi.getcon();
            String sql = "DELETE FROM siswa WHERE NIS =?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.TxtNis.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
        tampil(siswa);
        siswa.setLebarKolom();
                   reset(siswa);
        }
    }

    @Override
    public void kliktabel(view siswa) throws SQLException {
           try{
    int pilih = siswa.Tbl.getSelectedRow();
    if(pilih == -1){
    return;
    }
    siswa.TxtNis.setText(siswa.tblmodel.getValueAt(pilih,0).toString());
    siswa.TxtDepan.setText(siswa.tblmodel.getValueAt(pilih,1).toString());
    siswa.TxtBelakang.setText(siswa.tblmodel.getValueAt(pilih,2).toString());
    jk = String.valueOf(siswa.tblmodel.getValueAt(pilih, 3));
    siswa.TxtNomor.setText(siswa.tblmodel.getValueAt(pilih,4).toString());
    siswa.TxtAlamat.setText(siswa.tblmodel.getValueAt(pilih,5).toString());
    }
    catch (Exception e){
            }
    if (siswa.CbL.getText().equals(jk)){
    siswa.CbL.setSelected(true);
    }
    else{
    siswa.CbP.setSelected(true);
    }
    }

    @Override
    public void tampil(view siswa) throws SQLException {
    siswa.tblmodel.getDataVector().removeAllElements();
    siswa.tblmodel.fireTableDataChanged();
    try{
    Connection con = koneksi.getcon();
    Statement stt = con.createStatement();
    String sql = "SELECT * FROM siswa ORDER BY NIS ASC";
    ResultSet res = stt.executeQuery(sql);
    while (res.next()){
        Object[] ob = new Object[8];
        ob[0] = res.getString(1);
        ob[1] = res.getString(2);
        ob[2] = res.getString(3);
        ob[3] = res.getString(4);
        ob[4] = res.getString(5);
        ob[5] = res.getString(6);
        siswa.tblmodel.addRow(ob);
    }
    }catch(Exception e){
        System.out.println(e);
    }
    }

    @Override
    public void reset(view siswa) throws SQLException {
    siswa.TxtNis.setText("");
    siswa.TxtDepan.setText("");
    siswa.TxtBelakang.setText("");
    siswa.CbL.setSelected(true);
    siswa.TxtNomor.setText("");
    siswa.TxtAlamat.setText("");
    }
    
}
