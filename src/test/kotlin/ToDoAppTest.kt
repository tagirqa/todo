import data.Priority
import data.Task
import data.TasksRepositoryMemory
import org.junit.jupiter.api.*

class ToDoAppTest {

    private lateinit var tasksRepository : TasksRepositoryMemory

    @BeforeEach
    fun init() {
        tasksRepository = TasksRepositoryMemory()
    }

    @Test
    @DisplayName("Create task and found task in tasks list")
    fun checkCreateTaskTest() {
        val taskId = 1
        val taskName = "Not completed task test"
        val taskPriority = Priority.HIGH
        val countTasksInList = 1
        tasksRepository.addTask(Task(name = taskName, priority = taskPriority))
        val taskList = tasksRepository.getTasks()
        val task = taskList.first()

        Assertions.assertEquals(countTasksInList, taskList.size)
        Assertions.assertEquals(Task(taskId, taskName, taskPriority, false), task)
    }

    @Test
    @DisplayName("Completed taks and check task in completed task list")
    fun checkCompleteTaskTest() {
        val taskId = 1
        val taskName = "Not completed task test"
        val taskPriority = Priority.HIGH
        val isCompleted = true
        val countTasksInList = 1

        tasksRepository.addTask(Task(name = taskName, priority = taskPriority))
        tasksRepository.completeTask(taskId)
        val tasksList = tasksRepository.getTasks()
        val task = tasksList.first()
        Assertions.assertEquals(countTasksInList, tasksList.size)
        Assertions.assertEquals(Task(taskId, taskName, taskPriority, isCompleted), task)

        val tasksCompletedList = tasksRepository.getTasks(isCompleted)
        val taskCompleted = tasksCompletedList.first()

        Assertions.assertEquals(countTasksInList, tasksList.size)
        Assertions.assertEquals(Task(taskId, taskName, taskPriority, isCompleted), taskCompleted)
    }

    @Test
    @Disabled("Not implemented yet")
    fun checkSortTasksByDate() {
        TODO("Not implemented yet")
    }

    @Test
    @Disabled("Not implemented yet")
    fun checkSortTasksByName() {
        TODO("Not implemented yet")
    }

}