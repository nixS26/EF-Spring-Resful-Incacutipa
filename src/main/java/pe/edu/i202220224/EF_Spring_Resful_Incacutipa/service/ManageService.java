package pe.edu.i202220224.EF_Spring_Resful_Incacutipa.service;

import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDetailDto;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<CarDto> getAllCars() throws Exception;
    Optional<CarDto> getAllCarsById(int id) throws Exception;

    Optional<CarDetailDto> getCarById(int id) throws Exception;

    boolean deleteCarById(int id) throws Exception;

    boolean addCar(CarDetailDto carDetailDto) throws Exception;

    boolean updateCar(CarDto carDto) throws Exception;
}
