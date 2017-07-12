package com.futsaltime.facility

import com.futsaltime.database.models.Facility
import org.springframework.http.HttpStatus
import spock.lang.Specification

class FacilityControllerTest extends Specification {

    private IFacilityService facilityService = Mock()
    private FacilityController facilityController = new FacilityController(facilityService)

    def "GET 'facilities/list' responds with OK and the collection of facilities returned from the service"() {
        given:
        1 * facilityService.list() >> [
                new Facility('Facility 1'),
                new Facility('Facility 2')
        ]

        when:
        def result = facilityController.list()

        then:
        assert result
        assert result.statusCode == HttpStatus.OK
        assert result.body.size() == 2
        assert result.body[0].facilityName.equals('Facility 1')
        assert result.body[1].facilityName.equals('Facility 2')
    }

    def "GET 'facilities/list' responds with OK and an empty collection if service returns an empty collection"() {
        given:
        1 * facilityService.list() >> []

        when:
        def result = facilityController.list()

        then:
        assert result
        assert result.statusCode == HttpStatus.OK
        assert result.body.isEmpty()
    }
}
