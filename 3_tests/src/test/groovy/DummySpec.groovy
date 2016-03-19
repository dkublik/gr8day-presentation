import spock.lang.Specification

class DummySpec extends Specification {

    def "same integers should be equal"() {
        given:
            int i = 1

        expect:
            i == 1
    }
}
