package com.futsaltime.facility

import com.futsaltime.database.models.Facility
import org.springframework.http.HttpStatus
import spock.lang.Specification

class FacilityControllerTest extends Specification {

    private IFacilityService facilityService = Mock()
    private FacilityController facilityController = new FacilityController(facilityService)

    def "GET 'facilities/list' returns Success and the collection of facilities returned from the service"() {
        given:

        when:
        def result = facilityController.list()

        then:
        1 * facilityService.getFacilities() >> [
                new Facility('Facility 1'),
                new Facility('Facility 2')
        ]

        assert result
        assert result.statusCode == HttpStatus.OK
        assert result.body.size() == 2
        assert result.body[0].facilityName.equals('Facility 1')
        assert result.body[1].facilityName.equals('Facility 2')
    }

    def "GET 'facilities/list' returns Success and an empty collection if service returns an empty collection"() {
        given:

        when:
        def result = facilityController.list()

        then:
        1 * facilityService.getFacilities() >> responseFromService

        assert result
        assert result.statusCode == HttpStatus.OK
        assert result.body.size() == 0

        where:
        responseFromService     | _
        []                      | _
        Collections.emptyList() | _
        Collections.emptySet()  | _
    }
}
