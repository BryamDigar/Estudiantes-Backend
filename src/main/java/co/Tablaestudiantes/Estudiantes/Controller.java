package co.Tablaestudiantes.Estudiantes;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList;

    public Controller() {
        this.estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Daniel", 100000, 3, "Ingenieria"));
        estudianteList.add(new Estudiante("Samuel", 100002, 5, "Comunicacion"));
        estudianteList.add(new Estudiante("Andrea", 100003, 4, "Derecho"));
        estudianteList.add(new Estudiante("Fulana", 100004, 7, "Ingenieria"));
        estudianteList.add(new Estudiante("Jaime", 100005, 3, "Ingenieria"));
    }

    @GetMapping(path = "/estudiantes/todos")
    public List<Estudiante> obtenerEstudiante() {
        return estudianteList;
    }

    @GetMapping(path = "")
    public String hola() {
        return "Hola";
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
