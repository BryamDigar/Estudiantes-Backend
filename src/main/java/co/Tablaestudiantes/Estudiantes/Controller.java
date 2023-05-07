package co.Tablaestudiantes.Estudiantes;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    public String Status = "";

    List<Estudiante> estudianteList;

    public Controller() {
        this.estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Daniel", 100000, 3, "Ingenieria"));
        estudianteList.add(new Estudiante("Samuel", 100002, 5, "Comunicacion"));
        estudianteList.add(new Estudiante("Andrea", 100003, 4, "Derecho"));
        estudianteList.add(new Estudiante("Fulana", 100004, 3, "Ingenieria"));
        estudianteList.add(new Estudiante("Jaime", 100005, 3, "Ingenieria"));
    }

    @PostMapping(path = "/estudiante/crear")
    public String crearEstudiante(@RequestBody Estudiante estudiante) {
        String estado = "";
        if ((String.valueOf(estudiante.getSemestre()).length() >= 1) && (String.valueOf(estudiante.getFacultad()).length() >= 1) && (String.valueOf(estudiante.getNombre()).length() >= 3)) {
            estudiante.setId(filtrarID());
            estudianteList.add(estudiante);
            estado = "Estudiante ingresado correctamente";
        } else {
            estado = "Fallo en los datos";
        }
        return estado;
    }

    @GetMapping(path = "/estudiantes/todos")
    public List<Estudiante> obtenerEstudiante() {
        return estudianteList;
    }

    @GetMapping(path = "/estudiantes")
    public List<Estudiante> obtenerEstudiantePorFacultad(@RequestParam String facultad, int numMostrar){
        int contador = 1;
        List<Estudiante> busqueda = new ArrayList<>();
        for(Estudiante estudiante : estudianteList){
            if((estudiante.getFacultad().equals(facultad)) && (contador <= numMostrar)){
                busqueda.add(estudiante);
                contador +=1;
            }
        }
        return busqueda;
    }

    @DeleteMapping(path = "/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable(name = "id") Integer id){
        int index = 0;
        for(Estudiante estu : estudianteList){
            if(estu.getId() == id){
                estudianteList.remove(index);
                break;
            }
            index++;
        }
        return "Estudiante eliminado";
    }

    private Estudiante checkStudentParams(Estudiante estudiante, Estudiante estudian){
        //(String.valueOf(estudiante.getSemestre()).length() >= 1) && (String.valueOf(estudiante.getFacultad()).length() >= 1) && (String.valueOf(estudiante.getNombre()).length() >= 3)) {

        if(estudian.getFacultad() != null){
            estudiante.setFacultad(estudian.getFacultad());
        }
        else{
            this.Status = this.Status + "facultad, ";
        }
        if(estudian.getSemestre() != 0){
            estudiante.setSemestre(estudian.getSemestre());
        }
        else{
            this.Status = this.Status + "semestre, ";
        }
        if(String.valueOf(estudian.getNombre()).length() >= 3){
            estudiante.setNombre(estudian.getNombre());
        }
        else{
            this.Status = this.Status + "nombre, ";
        }
        if(estudian.getId() != 0){
            estudiante.setId(estudian.getId());
        }
        else{
            this.Status = this.Status + "id, ";
        }
        return estudiante;
    }

    @PutMapping(path = "/estudiantes/actualizar/{id}")
    public String actualizarEstudiante(@PathVariable Integer id,@RequestBody Estudiante estudian){
        Estudiante newEstu = null;
        for (Estudiante estudiante : estudianteList){
            if (estudiante.getId() == id){
                newEstu = checkStudentParams(estudiante, estudian);
                estudianteList.remove(estudiante);
                estudianteList.add(newEstu);
                break;
            }
        }
        return "Estudiante actualizado, variables no actualizadas: "+this.Status+"; si actualizó alguna de estas, revise sus datos y intente otra vez.";
    }


    public int filtrarID() {
        int id = 100000;
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getId() > id) {
                id = estudiante.getId();
            }
        }
        return id + 1;
    }
}
