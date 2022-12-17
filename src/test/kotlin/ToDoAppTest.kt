import data.Priority
import data.Task
import data.TasksRepositoryMemory
import menu.sortedByDeadlineDate
import menu.sortedByName
import org.junit.jupiter.api.*
import org.junit.jupiter.api.DisplayName

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
        val tasksCompletedList = tasksRepository.getTasks(isCompleted)
        val taskCompleted = tasksCompletedList.first()

        Assertions.assertEquals(countTasksInList, tasksList.size)
        Assertions.assertEquals(Task(taskId, taskName, taskPriority, isCompleted), task)
        Assertions.assertEquals(countTasksInList, tasksList.size)
        Assertions.assertEquals(Task(taskId, taskName, taskPriority, isCompleted), taskCompleted)
    }

    @Test
    @Disabled("Not implemented yet")
    fun checkSortTasksByDeadlineDate() {

        TODO("Add deadline date to task")
        val task1 = createTask(1, "test task 1", Priority.LOW)
        val task2 = createTask(2, "test task 2", Priority.MEDIUM)
        val task3 = createTask(3, "test task 3", Priority.LOW)
        val task4 = createTask(4, "test task 4", Priority.HIGH)
        val task5 = createTask(5, "test task 5", Priority.LOW)
        val taskListSortedByDeadLine = mutableListOf(task1, task2, task3, task4, task5)
        /* TODO("Add parameter after implementation")
        taskListSortedByDeadLine.sortBy { it.deadlineDate }
        * */
        tasksRepository.addTask(task1)
        tasksRepository.addTask(task2)
        tasksRepository.addTask(task3)
        tasksRepository.addTask(task4)
        tasksRepository.addTask(task5)

        sortedByDeadlineDate(tasksRepository)
        val tasksList = tasksRepository.getTasks()
        Assertions.assertEquals(taskListSortedByDeadLine, tasksList)
    }

    @Test
    @Disabled("Not implemented yet")
    fun checkSortTasksByName() {
        val task1 = createTask(1, "zero", Priority.LOW)
        val task2 = createTask(2, "london", Priority.MEDIUM)
        val task3 = createTask(3, "abc", Priority.LOW)
        val task4 = createTask(4, "moscow", Priority.HIGH)
        val task5 = createTask(5, "34 hello", Priority.LOW)
        val taskListSortedByDate = mutableListOf(task1, task2, task3, task4, task5)
        taskListSortedByDate.sortBy { it.name }

        tasksRepository.addTask(task1)
        tasksRepository.addTask(task2)
        tasksRepository.addTask(task3)
        tasksRepository.addTask(task4)
        tasksRepository.addTask(task5)

        sortedByName(tasksRepository)
        val tasksList = tasksRepository.getTasks()
        Assertions.assertEquals(taskListSortedByDate, tasksList)
    }

}