package dh.backend.ClinicaDental.Controller;

import dh.backend.ClinicaDental.Entity.Turno;
import dh.backend.ClinicaDental.Service.TurnoService;
import dh.backend.ClinicaDental.exceptions.BadRequestExeception;
import dh.backend.ClinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<?> guardarTurno (@RequestBody Turno turno) throws BadRequestExeception {

        if (turno.getPaciente() != null && turno.getOdontologo() != null){return ResponseEntity.ok(turnoService.crearTurno(turno));
        } else {
            throw new BadRequestExeception("No se ha encontrado a un odontologo o paciente");
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException, BadRequestExeception {
        Optional<Turno> optionalTurno = turnoService.buscarTurno(turno.getId());

        if (optionalTurno.isPresent()){
            turnoService.crearTurno(turno);
        } else {throw new ResourceNotFoundException("Turno no encontrado");
        }throw new BadRequestExeception("La consulta tuvo un error.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) throws BadRequestExeception {
        Optional<Turno> optionalTurno = turnoService.buscarTurno(id);

        if (optionalTurno.isPresent()){return ResponseEntity.ok(optionalTurno.get());
        } else {throw new BadRequestExeception("No se encontró");}

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        if (turnoService.buscarTurno(id) == null) {return ResponseEntity.badRequest().body("No existe el turno");
        } else {turnoService.eliminarTurno(id);
        }return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){return ResponseEntity.ok(turnoService.listarTurnos());
    }

}
