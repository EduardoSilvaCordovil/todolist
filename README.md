# todolist
Utilize o Postman para fazer os POST e GET no link: https://todolist-rocket-hp5s.onrender.com

Para cadastrar usuários:
**POST**: https://todolist-rocket-hp5s.onrender.com/users/
```json
{
    "username": "usertest",
    "password": "123456",
    "name": "User Test"
}
```

Para cadastrar tarefa:
**POST**: https://todolist-rocket-hp5s.onrender.com/tasks/
```json
{
    "description": "Última aula do curso de Java",
    "title": "Assistir aula",
    "priority": "ALTA",
    "startAt": "2023-10-06T12:00:00",
    "endAt": "2023-10-06T16:00:00"
}
```

Para retornar tarefas:
**GET**: https://todolist-rocket-hp5s.onrender.com/tasks/
```json
{
    "timestamp": "2023-10-13T19:11:37.282+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/tasks/"
}
```

Para alterar tarefas:
**PUT**: https://todolist-rocket-hp5s.onrender.com/tasks/
```json
{
    "title": "Alterando o título",
}
```
