package dh.backend.ClinicaDental.Controller;


import dh.backend.ClinicaDental.Entity.Paciente;
import dh.backend.ClinicaDental.Service.PacienteService;
import dh.backend.ClinicaDental.exceptions.BadRequestExeception;
import dh.backend.ClinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<Paciente> guardarPaciente (@RequestBody Paciente paciente) throws BadRequestExeception {
        if (paciente.getId() == null){
            return ResponseEntity.ok(pacienteService.crearPaciente(paciente));
        } else {throw new BadRequestExeception("El id es incorrecto");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPacientePorId (@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscar = pacienteService.buscarPaciente(id);

        if (pacienteBuscar.isPresent()){ return ResponseEntity.ok(pacienteBuscar.get());
        } else {throw new ResourceNotFoundException("Paciente no encontrado.");}
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPacienteEmail(@PathVariable("email") String email) throws BadRequestExeception {

        Optional<Paciente> pacienteOptional = pacienteService.buscarPorEmail(email);
        if (pacienteOptional.isPresent()){return ResponseEntity.ok(pacienteOptional.get());
        } else {if (email == null){throw new BadRequestExeception("Email incompleto.");}
            throw new BadRequestExeception("Paciente no encontrado.");
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestExeception {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPaciente(paciente.getId());

        if (optionalPaciente.isPresent()) {
            Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } else {
            throw new BadRequestExeception("Falta información. No se encontró el paciente");
        }
    }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        if (pacienteService.buscarPaciente(id).isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado");
        } else {
            return ResponseEntity.badRequest().body("No se encontró el paciente");
        }
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok().body(pacienteService.listarPacientes());
    }
}
