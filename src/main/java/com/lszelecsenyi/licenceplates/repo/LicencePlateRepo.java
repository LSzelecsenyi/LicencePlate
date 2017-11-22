package com.lszelecsenyi.licenceplates.repo;

import com.lszelecsenyi.licenceplates.model.LicencePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicencePlateRepo extends CrudRepository<LicencePlate, String> {
    List<LicencePlate> findAllByPlateLike(String fragment);
    List<LicencePlate> findAllByPlateContaining(String fragment);
    List<LicencePlate> findAllByPlateStartingWith(String fragment);
    List<LicencePlate> findAllByCarBrand(String brand);
    @Query(value = "SELECT * FROM LICENCE_PLATES WHERE PLATE LIKE ?%1", nativeQuery = true)
    List<LicencePlate> findByPrefix(String prefix);
}
