package com.futsaltime.bookable

import com.futsaltime.database.models.Bookable
import com.futsaltime.database.models.Facility
import com.futsaltime.exceptions.FacilityNotFoundException
import org.springframework.http.HttpStatus
import spock.lang.Specification

class BookableControllerTest extends Specification {

    private BookableService bookableService

    private BookableController bookableController

    def setup() {
        bookableService = Mock()
        bookableController = new BookableController(bookableService)
    }

    def "GET 'bookables/list' responds with OK and all bookables for a facility"() {
        given:
        def facilityId = 1
        def capacity = 5

        def facility = new Facility(id: facilityId, facilityName: 'Facility 1')
        def bookable1 = new Bookable(capacity, facility)
        def bookable2 = new Bookable(capacity, facility)

        1 * bookableService.list(facilityId) >> [bookable1, bookable2]

        when:
        def result = bookableController.list(facilityId)

        then:
        assert result
        assert result.statusCode == HttpStatus.OK
        assert !result.body.isEmpty()
        result.body.each { bookable ->
            assert bookable instanceof Bookable
            assert bookable.capacity == capacity
            assert bookable.facility.id == facilityId
        }
    }

    def "GET 'bookables/list' responds with OK and an empty list if facility doesn't have bookables"() {
        given:
        def facilityId = 1

        1 * bookableService.list(facilityId) >> []

        when:
        def result = bookableController.list(facilityId)

        then:
        assert result
        assert result.statusCode == HttpStatus.OK
        assert result.body.isEmpty()
    }

    def "GET 'bookables/list' responds with NOT_FOUND and no body if facility doesn't exist"() {
        given:
        def facilityId = 1
        def exceptionMessage = "Facility not found."

        1 * bookableService.list(facilityId) >> { throw new FacilityNotFoundException(exceptionMessage) }

        when:
        def result = bookableController.list(facilityId)

        then:
        assert result
        assert result.statusCode == HttpStatus.NOT_FOUND
        assert !result.body
    }
}
