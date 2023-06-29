package dh.backend.ClinicaDental;

import dh.backend.ClinicaDental.Entity.Domicilio;
import dh.backend.ClinicaDental.Entity.Odontologo;
import dh.backend.ClinicaDental.Entity.Paciente;
import dh.backend.ClinicaDental.Service.OdontologoService;
import dh.backend.ClinicaDental.Service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional
public class TestOdontologo {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    void crearOdontologo(){
        Odontologo odontologoUno = new Odontologo("Pedro","Perez","1234");
        odontologoService.crearOdontologo(odontologoUno);

        Odontologo prueba = odontologoService.buscarOdontologo(odontologoUno.getId()).orElse(null);
        Assertions.assertTrue(prueba != null);

    }

    @Test
    void crearPaciente(){
        Domicilio domicilio = new Domicilio("Ramon ortiz",5663,"bea","colonia");
        Paciente p1 = new Paciente("Juana","Lopez","jlopez@gmail.com",12345, LocalDate.now(),domicilio);

        Paciente pArchivado = pacienteService.crearPaciente(p1);
        Paciente test = pacienteService.buscarPacienteEmail(pArchivado.getEmail()).orElse(null);

        Assertions.assertTrue(test != null);
    }
}
