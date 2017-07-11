package com.futsaltime.facility

import com.futsaltime.database.models.Facility
import com.futsaltime.database.repositories.IFacilityRepository
import spock.lang.Specification

class FacilityServiceTest extends Specification {

    FacilityService facilityService

    IFacilityRepository facilityRepository

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
}