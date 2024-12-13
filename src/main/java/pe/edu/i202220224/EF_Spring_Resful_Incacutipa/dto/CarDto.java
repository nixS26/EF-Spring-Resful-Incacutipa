package pe.edu.i202220224.EF_Spring_Resful_Incacutipa.dto;

public record CarDto(Integer carId,
                     String model,
                     Integer year,
                     String vin,
                     String licensePlate,
                     String ownerName) {
}
