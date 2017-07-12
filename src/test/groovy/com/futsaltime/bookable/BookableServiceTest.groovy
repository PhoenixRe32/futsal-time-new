package com.futsaltime.bookable

import com.futsaltime.database.models.Bookable
import com.futsaltime.database.models.Facility
import com.futsaltime.database.repositories.IBookableRepository
import com.futsaltime.database.repositories.IFacilityRepository
import com.futsaltime.exceptions.FacilityNotFoundException
import spock.lang.Specification

class BookableServiceTest extends Specification {

    private IFacilityRepository facilityRepository

    private IBookableService bookableService

    void setup() {
        facilityRepository = Mock()
        bookableService = new BookableService(facilityRepository)
    }

    def "returns a list of bookables for the specific facility when it exists"() {
        given:
        def facilityId = 1L
        def capacity = 5

        def facility = new Facility(id: facilityId, facilityName: 'Facility 1')
        def bookable1 = new Bookable(capacity, facility)
        def bookable2 = new Bookable(capacity, facility)
        facility.setBookables(Arrays.asList(bookable1, bookable2))

        1 * facilityRepository.findById(facilityId) >> Optional.of(facility)

        when:
        def result = bookableService.list(facilityId)

        then:
        assert result
        assert result instanceof Iterable

        def bookables = result.asList()
        assert bookables.size() == 2
        assert bookables[0] == bookable1
        assert bookables[1] == bookable2
    }

    def "throws an exception if the facility doesn't exist"() {
        given:
        def facilityId = 1

        1 * facilityRepository.findById(facilityId) >> Optional.empty()

        when:
        bookableService.list(facilityId)

        then:
        FacilityNotFoundException ex = thrown()
        ex.message == 'Facility not found.'
    }
}
