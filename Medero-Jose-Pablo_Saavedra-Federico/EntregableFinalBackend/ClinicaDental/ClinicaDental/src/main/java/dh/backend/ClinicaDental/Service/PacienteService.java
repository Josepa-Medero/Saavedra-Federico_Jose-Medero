package dh.backend.ClinicaDental.Service;

import dh.backend.ClinicaDental.Entity.Odontologo;
import dh.backend.ClinicaDental.Entity.Paciente;
import dh.backend.ClinicaDental.Repository.PacienteRepository;
import dh.backend.ClinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crearPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }

    public Paciente actualizarPaciente(Paciente paciente){
      return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAeliminar = buscarPaciente(id);

        if (pacienteAeliminar.isPresent()) {pacienteRepository.deleteById(id);
        } else {throw new ResourceNotFoundException("El paciente con ID: " + id + " no ha sido encontrado");
        }
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

}




