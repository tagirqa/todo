import data.Priority
import data.Task
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import data.TasksRepositoryMemory
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object ToDoAppTest : Spek({
    Feature("Init repository") {
        val tasksRepository by memoized {
            TasksRepositoryMemory()
        }

        Scenario("Create task and found task in tasks list") {
            val taskId = 1
            val taskName = "Not completed task test"
            val taskPriority = Priority.HIGH
            val countTasksInList = 1

            When("Create task") {
                tasksRepository.addTask(Task(name = taskName, priority = taskPriority))
            }

            lateinit var taskList: List<Task>

            Then("Get tasks list") {
                taskList = tasksRepository.getTasks()
                assertEquals(countTasksInList, taskList.size)
            }
            And("Check if the task is in the list") {
                assertEquals(Task(taskId, taskName, taskPriority, false), taskList.first())
            }
        }

        Scenario("Completed tasks and check task in completed task list") {
            val taskId = 1
            val taskName = "completed task test"
            val taskPriority = Priority.HIGH
            val isCompleted = true
            val countTasksInList = 1

            When("Create task") {
                tasksRepository.addTask(Task(name = taskName, priority = taskPriority))
            }
            And("Completed this task") {
                tasksRepository.completeTask(taskId)
            }

            lateinit var taskList: List<Task>
            lateinit var tasksCompletedList: List<Task>

            Then("Get tasks list") {
                taskList = tasksRepository.getTasks()
                assertEquals(countTasksInList, taskList.size)
            }
            And("Get completed tasks list") {
                tasksCompletedList = tasksRepository.getTasks(isCompleted)
                assertEquals(countTasksInList, tasksCompletedList.size)
            }
            And("Check if the task is in the list") {
                assertEquals(Task(taskId, taskName, taskPriority, isCompleted), taskList.first())
                assertEquals(Task(taskId, taskName, taskPriority, isCompleted), tasksCompletedList.first())
            }
        }
    }

    describe("A task") {
        val taskId = 1
        val taskName = "task test"
        val taskPriority = Priority.HIGH
        val isCompleted = true
        val countTasksInList = 1

        val tasksRepository by memoized { TasksRepositoryMemory() }

        it("Check the task in list") {
            tasksRepository.addTask(Task(name = taskName, priority = taskPriority))
            val taskList = tasksRepository.getTasks()

            assertEquals(countTasksInList, taskList.size)
            assertEquals(Task(taskId, taskName, taskPriority, false), taskList.first())
        }

        it("Check the task in completed list") {
            tasksRepository.addTask(Task(name = taskName, priority = taskPriority))
            tasksRepository.completeTask(taskId)
            val taskList = tasksRepository.getTasks()
            val tasksCompletedList = tasksRepository.getTasks(isCompleted)

            assertEquals(countTasksInList, taskList.size)
            assertEquals(Task(taskId, taskName, taskPriority, isCompleted), taskList.first())
            assertEquals(countTasksInList, tasksCompletedList.size)
            assertEquals(Task(taskId, taskName, taskPriority, isCompleted), tasksCompletedList.first())
        }

    }
})
