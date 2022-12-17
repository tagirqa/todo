import data.Priority
import data.Task

fun createTask(taskId: Int, taskName: String, taskPriority: Priority): Task {
    return Task(taskId, taskName, taskPriority)
}