package pe.edu.i202220224.EF_Spring_Resful_Incacutipa.service.impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDetailDto;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto.CarDto;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.entity.Car;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.repository.CarRepository;
import pe.edu.i202220224.EF_Spring_Resful_Incacutipa.service.ManageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    //-------------------------LIST------------------------------
    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cards = new ArrayList<>();
        carRepository.findAll();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(cars -> {
            //armamos un objeto de tipo dto
            CarDto cardto = new CarDto(cars.getCarId(),
                    cars.getModel(),
                    cars.getYear(),
                    cars.getVin(),
                    cars.getLicensePlate(),
                    cars.getOwnerName());
            //agregamos a la lisa el obj dto
            cards.add(cardto);
        });
        return cards;
    }

    //---------Trae especificamente un reg, a traves del id-------
    public Optional<CarDto> getAllCarsById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCarId(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName()
        ));
    }

    //---------------------------DETAIL--------------------------------------------------
    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        //CONSULTANDO UN CARRO POR ID
        Optional<Car> optional = carRepository.findById(id);
        //map es como un conversor, si esat presente retornara un nuevo dto con data
        return optional.map(car -> new CarDetailDto(car.getCarId(), car.getMake(), car.getModel(),
                car.getYear(), car.getVin(), car.getLicensePlate(),
                car.getOwnerName(), car.getOwnerContact(), car.getPurchaseDate(),
                car.getMileage(), car.getEngineType(), car.getColor(), car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(), car.getRegistrationExpirationDate(),
                car.getServiceDueDate()
        ));
    }
    //------------------------------UPDATE--------------------------------------------
    @Override
    public boolean updateCar(CarDto carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(cars -> {
            cars.setModel(carDto.model());
            cars.setYear(carDto.year());
            cars.setOwnerName(carDto.ownerName());
            carRepository.save(cars);
            return true;
        }).orElse(false);

    }
    //-----------------------------DELETE--------------------------------------------
    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }
    //------------------------------ ADD -------------------------------------------
    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.carId());
        if (optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(carDetailDto.purchaseDate());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
            car.setServiceDueDate(carDetailDto.serviceDueDate());
            carRepository.save(car);
            return true;
        }
    }


}
