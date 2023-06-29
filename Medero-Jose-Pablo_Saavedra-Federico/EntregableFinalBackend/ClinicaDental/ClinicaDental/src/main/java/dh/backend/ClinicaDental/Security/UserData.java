package dh.backend.ClinicaDental.Security;

import dh.backend.ClinicaDental.Entity.RolUsuario;
import dh.backend.ClinicaDental.Entity.Usuario;
import dh.backend.ClinicaDental.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserData implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();

        String passSinCifrarUsuario = "12345678";
        String passCifradoUsuario = encriptador.encode(passSinCifrarUsuario);
        Usuario usuarioAInsertar = new Usuario("Sara", "SaraBC", "SaraBC@gmail.com", passCifradoUsuario, UsuarioRol.ROL_USUARIO);
        System.out.println("Contraseña cifrada del usuario: " + passCifradoUsuario);
        usuarioRepository.save(usuarioAInsertar);

        String passSinCifrarAdmin = "123456789456";
        String passCifradoAdmin = encriptador.encode(passSinCifrarAdmin);
        Usuario usuarioAdmin = new Usuario("Karen", "Perez", "KarenP@gmail.com", passCifradoAdmin, UsuarioRol.ROL_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }
