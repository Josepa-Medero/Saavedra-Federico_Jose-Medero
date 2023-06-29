package dh.backend.ClinicaDental.Repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

@Repository
public class DBConexion {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DBConexion.class));
    static Connection connection;

    public static Connection getConnection() {
        LOGGER.info("Iniciando conexión");

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("Conexión exitosa");
            return connection;
        } catch (ClassNotFoundException e) {
            LOGGER.error("No se encontró el controlador de la base de datos");
        } catch (SQLException e) {
            LOGGER.error("Error al establecer la conexión con la base de datos");
        }

        return null;
    }

    public static void crearTablas() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (ClassNotFoundException e) {
            LOGGER.error("No se encontró el controlador de la base de datos");
        } catch (SQLException e) {
            LOGGER.error("Error al establecer la conexión con la base de datos");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexión de la base de datos");
                }
            }
        }
    }


}
