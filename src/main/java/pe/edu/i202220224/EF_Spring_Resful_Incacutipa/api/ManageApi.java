package pe.edu.i202220224.EF_Spring_Resful_Incacutipa.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDetailDto;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDto;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.reponse.*;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageApi {

    //INYECCIÓN DE DEPENDENCIA
    @Autowired
    ManageService manageService;

    //------------------ API - MANEJO DE EXCEPCIONES ---------------------
    //******************************** ALL *********************************************
    @GetMapping("/all")
    public FindCarsReponse findCars(@RequestParam(value = "id", defaultValue = "0") String id){
        try {
            if(Integer.parseInt(id)>0){
                Optional<CarDto> optional = manageService.getAllCarsById(Integer.parseInt(id));
                if(optional.isPresent()){
                    CarDto carDto = optional.get();
                    return new FindCarsReponse("01","",List.of(carDto));
                }else{
                    return new FindCarsReponse("02","Car Not Found",null);
                }
            }else {
                List<CarDto> cars = manageService.getAllCars();
                if(!cars.isEmpty())
                    return new FindCarsReponse("01","",cars);
                else
                    return new FindCarsReponse("04","Car List Not Found",cars);


            }


        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsReponse("99","Service Not Found",null);
        }

    }

    //****************************** DETAIL**********************************************

    @GetMapping("/detail")
    public FindCarByIdReponse findUserById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdReponse("01", "", carDetailDto);
                } else {
                    return new FindCarByIdReponse("02", "User Not Found", null);
                }

            } else
                return new FindCarByIdReponse("04", "Parameter Not Found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdReponse("99", "Service Not Found", null);

        }

    }
    //************************************ UPDATE *******************************************

    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {

        try {
            if (manageService.updateCar(carDto)) {
                return new UpdateCarResponse("01", "");
            } else {
                return new UpdateCarResponse("02", "User not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Service not found");

        }

    }

    //********************************** DELETE *********************************************

    @PostMapping("/delete")
    public DeleteCarByIdResponse deleteCarById(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            int carId = Integer.parseInt(id);
            if (carId > 0) {
                boolean isDeleted = manageService.deleteCarById(carId);
                if (isDeleted) {
                    return new DeleteCarByIdResponse("01", "");
                } else {
                    return new DeleteCarByIdResponse("02", "Car not found");
                }
            } else {
                return new DeleteCarByIdResponse("03", "Invalid parameter");
            }
        } catch (NumberFormatException e) {
            return new DeleteCarByIdResponse("04", "Invalid ID format");
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarByIdResponse("99", "Service error");
        }
    }
    //************************************ ADD *******************************************************

    @PostMapping("/add")
    public AddCarResponse addCar(@RequestBody CarDetailDto carDetailDto) {
        try {
            if (manageService.addCar(carDetailDto)) {
                return new AddCarResponse("01", "Car added");
            } else {
                return new AddCarResponse("02", "Car with the same ID already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AddCarResponse("99", "Service not found");
        }
    }




}
