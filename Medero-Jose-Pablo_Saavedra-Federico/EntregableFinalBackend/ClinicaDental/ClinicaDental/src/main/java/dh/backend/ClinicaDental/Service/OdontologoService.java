package dh.backend.ClinicaDental.Service;

import dh.backend.ClinicaDental.Entity.Odontologo;
import dh.backend.ClinicaDental.Repository.OdontologoRepository;
import dh.backend.ClinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository odontologoRepository;
    public Odontologo crearOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
       return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoAEliminar = buscarOdontologo(id);
        if (odontologoAEliminar.isPresent()){
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("El odontologo con ID: " + id + " no ha sido encontrado");
        }
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoRepository.findAll();
    }


}
