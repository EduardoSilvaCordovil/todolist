package br.com.eduardocordovil.todolist.task;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import br.com.eduardocordovil.todolist.utils.Utils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private InterfaceTaskRepository taskRepository;
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        
        System.out.println("CHEGOU NO CONTROLLER, CARAIO!!!" + request.getAttribute("idUser"));
        
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        
        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A DATA DE INÍCIO E TÉRMINO DEVEM SER MAIORES DO QUE A DATA ATUAL!!!");
        }
        
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A DATA DE INÍCIO DEVE SER MENOR DO QUE A DATA TÉRMINO!!!");
        }
        
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    
    @GetMapping
    public List<TaskModel> list(HttpServletRequest request) {
        
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        
        var task = this.taskRepository.findById(id).orElse(null);
        
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TAREFA NÃO ENCONTRADA!!!");
        }
        
        var idUser = request.getAttribute("idUser");
        
        if (!task.getIdUser().equals("idUser")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USUÁRIO NÃO TEM PERMISSÃO PARA ALTERAR ESSA TAREFA!!!");
        }
        
        Utils.copyNonNullsProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);        
        return ResponseEntity.ok().body(taskUpdated);
    }
}
