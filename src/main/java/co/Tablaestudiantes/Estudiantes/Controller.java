package co.Tablaestudiantes.Estudiantes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    List<Estudiante> estudianteList;

    public Controller(){
        this.estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Daniel",1,3,"Ingenieria"));
        estudianteList.add(new Estudiante("Samuel",2,5,"Masculino"));
        estudianteList.add(new Estudiante("Andrea",3,4,"Femenino"));
        estudianteList.add(new Estudiante("Fulana",4,7,"Femenino"));
        estudianteList.add(new Estudiante("Jaime",5,3,"Masculino"));
    }

    @GetMapping(path = "/estudiantes/todos")
    public List<Estudiante> obtenerEstudiante(){
        return estudianteList;
    }
    @GetMapping(path = "")
    public String hola(){
        return "Hola";
    }


    @PostMapping(path = "/estudiante/crear")
    public String crearEstudiante(@RequestBody Estudiante estudiante) {
        estudiante.setId(filtrarID());
        estudianteList.add(estudiante);
        return "Estudiante ingresado correctamente";
    }

    public int filtrarID() {
        int id = 0;
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getId() > id) {
                id = estudiante.getId();
            }
        }
        return id + 1;
    }
}
