package dh.backend.ClinicaDental.Controller;

import dh.backend.ClinicaDental.Entity.Odontologo;
import dh.backend.ClinicaDental.Service.OdontologoService;
import dh.backend.ClinicaDental.exceptions.BadRequestExeception;
import dh.backend.ClinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> crearUnOdontologo (@RequestBody Odontologo odontologo) throws BadRequestExeception {
       if (odontologo == null){
           throw new BadRequestExeception("No cuenta con toda la informacion");
       } else {if (odontologo.getId() == null) {
               ResponseEntity.ok(odontologoService.crearOdontologo(odontologo));
           } throw new BadRequestExeception("La consulta dio un error");
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologo(id);

        return odontologoOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Id no encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarLosOdontologos(){return ResponseEntity.ok(odontologoService.listarOdontologos());}

    @PutMapping()
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestExeception {
        Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologo(odontologo.getId());

        return odontologoOptional.map(o -> ResponseEntity.ok(odontologoService.crearOdontologo(odontologo)))
                .orElseThrow(() -> new BadRequestExeception("No encontro el Id"));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable("id") Long id) throws ResourceNotFoundException {
        try {odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se ha eliminado al odontologo");
        } catch (ResourceNotFoundException ex) {throw ex;
        }
    }
}
