import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.gradle.testkit.runner.TaskOutcome.UP_TO_DATE

class InvestigatingSpec extends Specification {

    @Rule
    TemporaryFolder testProjectDir

    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "should execute task"() {
        given:
        buildFile << """

        task backupBuild(type: Copy) {
            from 'build.gradle'
            into 'backup'
        }

        backupBuild {
            into 'backup2'
        }

        """

        when:
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('backupBuild')
                .withDebug(true)
                .build()

        then:
        result.task(':probablyInproper').outcome in [SUCCESS, UP_TO_DATE]
    }


}
