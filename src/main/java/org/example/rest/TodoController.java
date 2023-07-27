package org.example.rest;

import org.example.orm.Todo;
import org.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("todos")
public class TodoController {

    @Autowired private TodoRepository todoRepository;
    @CrossOrigin(origins="http://192.168.4.54:4300")
    @GetMapping("/")
    public List<Todo> getAllTodos(){

        return todoRepository.findAll();
    }
    @CrossOrigin(origins="http://192.168.4.54:4300")
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodosById(@PathVariable(value="id") Long todoId) throws ResourceNotFoundException {

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + todoId));


        return ResponseEntity.ok().body(todo);
    }
    @CrossOrigin(origins="http://192.168.4.54:4300")
    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo){

        return (Todo) todoRepository.save(todo);
    }

    @CrossOrigin(origins="http://192.168.4.54:4300")
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value="id") Long todoId, @Valid @RequestBody Todo todoDetails) throws ResourceNotFoundException {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + todoId));

        todo.setText(todoDetails.getText());
        todo.setDay(todoDetails.getDay());
        todo.setReminder(todoDetails.getReminder());
        final Todo updatedTodo = (Todo) todoRepository.save(todo);

        return ResponseEntity.ok(updatedTodo);
    }

    @CrossOrigin(origins="http://192.168.4.54:4300")
    @DeleteMapping("/{id}")
    public Map <String, Boolean> deleteTodo(@PathVariable(value="id") Long todoId) throws ResourceNotFoundException {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + todoId));
        todoRepository.delete(todo);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}

