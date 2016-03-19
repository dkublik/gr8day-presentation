import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskActionListener
import org.gradle.api.internal.tasks.execution.DefaultTaskExecutionContext
import org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class CustomTaskSpec extends Specification {

    TaskActionListener taskActionListener = new TaskActionListener() {
        void beforeActions(Task task) {
        }
        void afterActions(Task task) {
        }
    }

    ExecuteActionsTaskExecuter executer = new ExecuteActionsTaskExecuter(taskActionListener)

    def "should work"() {
        given:
        Project project = ProjectBuilder.builder().build()
        Task task = project.task('myTask')
        task.doLast({println '--- hello from custom task'})

        when:
        executer.execute(task, task.getState(), new DefaultTaskExecutionContext())

        then:
        task.state.executed
    }


}
