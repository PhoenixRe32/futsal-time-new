package com.futsaltime.facility

import spock.lang.Specification

class FacilityControllerTest extends Specification {

    private FacilityController controller = new FacilityController();

    def "GET facilities/list returns a collection of facilities"() {
        given:
        //nothing to set up

        when:
        def result = controller.listFacilities()

        then:
        result
        result.size() == 3
    }
}
