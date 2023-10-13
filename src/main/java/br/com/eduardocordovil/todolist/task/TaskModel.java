package br.com.eduardocordovil.todolist.task;

import lombok.Data;
import java.util.UUID;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID idUser;

    @Column(length = 50)
    private String title;

    private String priority;
    private String description;
    private LocalDateTime endAt;
    private LocalDateTime startAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTilte(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O CAMPO 'title', DEVE CONTER NO MÁXIMO, 50 CARACTERES");
        }
        this.title = title;
    }
}
