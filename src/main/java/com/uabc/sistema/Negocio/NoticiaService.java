package com.uabc.sistema.Negocio;

import com.uabc.sistema.Entidad.Noticia;
import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import java.sql.Connection;

public class NoticiaService {
    public boolean registrarNoticia(Noticia noticia) {
        try (Connection conexion = db.conectar()) {
            NoticiaDao dao = new NoticiaDao(conexion);
            return dao.registrarNoticia(noticia);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}