package dh.backend.ClinicaDental.Service;
import dh.backend.ClinicaDental.Entity.Odontologo;
import dh.backend.ClinicaDental.Entity.Paciente;
import dh.backend.ClinicaDental.Entity.Turno;
import dh.backend.ClinicaDental.Repository.TurnoRepository;
import dh.backend.ClinicaDental.exceptions.BadRequestExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    public Turno crearTurno(Turno turno) throws BadRequestExeception {

       if (turno.getOdontologo() != null){if (turno.getPaciente() != null){
               Optional<Paciente> pacienteOptional = pacienteService.buscarPaciente(turno.getPaciente().getId());
               Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologo(turno.getOdontologo().getId());

               if (pacienteOptional.isPresent() && odontologoOptional.isPresent()){turnoRepository.save(turno);
               } else {throw new BadRequestExeception("El odontologo o el paciente no existe.");
               }
           }
       }throw new RuntimeException();
    }

    public Optional<Turno> buscarTurno(Long id){
        return turnoRepository.findById(id);
    }

    public List<Turno> listarTurnos(){
       return turnoRepository.findAll();
    }

    public void eliminarTurno(Long id) {
        eliminarTurno(id);
    }
}
