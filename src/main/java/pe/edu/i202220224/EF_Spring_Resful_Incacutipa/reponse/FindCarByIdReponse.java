package pe.edu.i202220224.EF_Spring_Resful_Incacutipa.reponse;

import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDetailDto;

public record FindCarByIdReponse(String code,
                                 String error,
                                 CarDetailDto car) {
}
