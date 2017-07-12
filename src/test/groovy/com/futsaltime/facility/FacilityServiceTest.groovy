package com.futsaltime.facility

import com.futsaltime.database.models.Facility
import com.futsaltime.database.repositories.IFacilityRepository
import spock.lang.Specification

class FacilityServiceTest extends Specification {

    private FacilityService facilityService

    private IFacilityRepository facilityRepository

    def setup() {
        facilityRepository = Mock()
        facilityService = new FacilityService(facilityRepository)
    }

    def "retrieves all the facilities saved in the database"() {
        given:
        def facility1 = new Facility(id: 100, facilityName: 'facilityName1')
        def facility2 = new Facility(id: 101, facilityName: 'facilityName2')
        facilityRepository.findAll() >> [facility1, facility2]

        when:
        def result = facilityService.list()

        then:
        def facilities = result.asList()
        assert !facilities.isEmpty()
        assert facilities[0] == facility1
        assert facilities[1] == facility2

    }

    def "retrieves all the facilities saved in the database - no entries"() {
        given:
        facilityRepository.findAll() >> []

        when:
        def result = facilityService.list()

        then:
        def facilities = result.asList()
        assert facilities.isEmpty()
    }

    def "successfully adds a facility"() {
        given:
        def facilityName = 'Facility1'
        def facilityDto = new FacilityDto(facilityName: facilityName)

        1 * facilityRepository.save(*_) >> { arguments ->
            assert arguments.size() == 1
            final Facility facility = arguments[0] as Facility
            assert facility.facilityName == facilityName
            new Facility(facilityName: facilityName)
        }

        when:
        def result = facilityService.add(facilityDto)

        then:
        assert result.class == Facility.class

        def facilityAdded = result as Facility
        assert facilityAdded.facilityName == facilityName
        assert facilityAdded.id != 0
    }

    def "fails to add facility because repository throws exception"() {
        given:
        def facilityName = 'Facility1'
        def facilityDto = new FacilityDto(facilityName: facilityName)

        1 * facilityRepository.save(*_) >> { arguments ->
            assert arguments.size() == 1
            final Facility facility = arguments[0] as Facility
            assert facility.facilityName == facilityName
            throw new Exception()
        }

        when:
        facilityService.add(facilityDto)

        then:
        Exception ex = thrown()
    }
}