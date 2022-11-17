/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.view;

/**
 *
 * @author LAB 2 PC 13
 */
public interface controller {
        public void simpan(view siswa)throws SQLException;
        public void ubah(view siswa)throws SQLException;
        public void hapus(view siswa)throws SQLException;
        public void reset(view siswa)throws SQLException;
        public void kliktabel(view siswa)throws SQLException;
        public void tampil(view siswa)throws SQLException;
        
}
