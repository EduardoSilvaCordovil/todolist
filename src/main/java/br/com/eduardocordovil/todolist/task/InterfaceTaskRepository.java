package br.com.eduardocordovil.todolist.task;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfaceTaskRepository extends JpaRepository<TaskModel, UUID> {

    List<TaskModel> findByIdUser(UUID idUser);
}
