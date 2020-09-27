package ModeloDAO;

import Connection.ConexionBD;
import Intefaces.IUsuario;
import Modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO implements IUsuario {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private ConexionBD conexionBD;
    private Usuarios usuarios;

    private Connection connection;

    private int result;

    public UsuariosDAO() {
        conexionBD = new ConexionBD();
        usuarios = new Usuarios();
        this.result = 0;
    }

    @Override
    public List listarUsuarios() {
        ArrayList<Usuarios> list = new ArrayList<>();
        String sql = "select * from usuarios";
        try {
            connection = conexionBD.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Usuarios usuarios = new Usuarios();
                usuarios.setId(resultSet.getString("Id"));
                usuarios.setNombre(resultSet.getString("nombre"));
                usuarios.setApellido(resultSet.getString("apellido"));
                usuarios.setEmail(resultSet.getString("email"));
                usuarios.setUser(resultSet.getString("user"));
                usuarios.setPassWord(resultSet.getString("passWord"));
                list.add(usuarios);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConnection(connection);
            closepreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return list;
    }

    @Override
    public Usuarios listUsuarios(String id) {
        String sql = "select * from usuarios a where Id=" + id;
        try {
            connection = conexionBD.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuarios.setId(resultSet.getString("Id"));
                usuarios.setNombre(resultSet.getString("nombre"));
                usuarios.setApellido(resultSet.getString("apellido"));
                usuarios.setEmail(resultSet.getString("email"));
                usuarios.setUser(resultSet.getString("user"));
                usuarios.setPassWord(resultSet.getString("passWord"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConnection(connection);
            closepreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return usuarios;
    }

    @Override
    public boolean registrar(Usuarios usuarios) {
        result = 0;
        try {
            connection = conexionBD.getConnection();
            String sql = "insert into usuarios (nombre, apellido, email, user, passWord) "
                    + "Values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuarios.getNombre());
            preparedStatement.setString(2, usuarios.getApellido());
            preparedStatement.setString(3, usuarios.getEmail());
            preparedStatement.setString(4, usuarios.getUser());
            preparedStatement.setString(5, usuarios.getPassWord());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConnection(connection);
            closepreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return result > 0;
    }

    @Override
    public boolean editar(Usuarios usuarios) {
        result = 0;
        try {
            connection = conexionBD.getConnection();
            String sql = "update usuarios set nombre = ?, apellido = ?, "
                    + "email = ?, user = ?, passWord = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuarios.getNombre());
            preparedStatement.setString(2, usuarios.getApellido());
            preparedStatement.setString(3, usuarios.getEmail());
            preparedStatement.setString(4, usuarios.getUser());
            preparedStatement.setString(5, usuarios.getPassWord());
            preparedStatement.setString(6, usuarios.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConnection(connection);
            closepreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        } 
        return result > 0;
    }

    @Override
    public boolean eliminar(String id) {
        result = 0;
        try {
            connection = conexionBD.getConnection();
            String sql = "delete from usuarios where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConnection(connection);
            closepreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return result > 0;
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closepreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
