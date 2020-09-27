
package Intefaces;

import Modelo.Usuarios;
import java.util.List;


public interface IUsuario {
    public List listarUsuarios();
    public Usuarios listUsuarios(String id);
    public boolean registrar(Usuarios usuarios);
    public boolean editar(Usuarios usuarios);
    public boolean eliminar(String id);
}
