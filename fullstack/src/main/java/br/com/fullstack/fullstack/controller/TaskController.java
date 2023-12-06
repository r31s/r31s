package br.com.fullstack.fullstack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fullstack.fullstack.model.Task;
import br.com.fullstack.fullstack.repository.TaskRepository;
import lombok.Data;

@Data
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

     // Endpoint para listar todas as tarefas (tasks)
    @GetMapping
    public ResponseEntity<List<Task>> listarTasks(){
        List<Task> connection =  taskRepository.findAll();
        return ResponseEntity.ok(connection);
    }
    
    // Endpoint para buscar uma tarefa pelo ID
    @GetMapping("/id")
     public ResponseEntity<Task> buscarTasksPorId(@PathVariable Long id){
        Task task = taskRepository.findById(id).orElse(null);
        if (task !=null) {
            return ResponseEntity.ok(task);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

        // Endpoint para adicionar uma nova tarefa
    @PostMapping
    public ResponseEntity<Task> adicionarTask(@RequestBody Task task){
        Task novaTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTask);
    }
    // Endpoint para atualizar uma tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Task> atualizarTasks(@PathVariable Long id, @RequestBody Task tasks) {
    Task tasksExistente = taskRepository.findById(id).orElse(null);
            if (tasksExistente != null) {
                tasksExistente.setTitle(tasks.getTitle());
                tasksExistente.setCreated_at(tasks.getCreated_at());
                tasksExistente.setStatus(tasks.getStatus());
            
            

                taskRepository.save(tasksExistente);
                return ResponseEntity.ok(tasksExistente);
        } else {
           return ResponseEntity.notFound().build();
        }
    }
    
    // Endpoint para excluir um produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTasks(@PathVariable Long id) {
        Task taskExistente = taskRepository.findById(id).orElse(null);
        if (taskExistente != null) {
            taskRepository.delete(taskExistente);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}