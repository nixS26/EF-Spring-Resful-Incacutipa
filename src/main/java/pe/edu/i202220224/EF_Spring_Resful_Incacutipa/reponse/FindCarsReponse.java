package pe.edu.i202220224.EF_Spring_Resful_Incacutipa.reponse;

import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDto;

public record FindCarsReponse(String code,
                              String error,
                              Iterable<CarDto> cars) {
}
