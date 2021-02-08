package ds.example.dsproject.services;

import ds.example.dsproject.dto.MetDTO;
import ds.example.dsproject.entities.Dessert;
import ds.example.dsproject.entities.Entree;
import ds.example.dsproject.entities.Met;
import ds.example.dsproject.entities.Plat;

import java.time.LocalDate;
import java.util.List;

public interface MetService{
    Plat addPlat(Plat plat);
    Plat updatePlat(Plat plat);
    List<MetDTO> deletePlat(Long id);
    Entree addEntree(Entree entree);
    Entree updateEntree(Entree entree);
    List<MetDTO> deleteEntree(Long id);
    Dessert addDessert(Dessert dessert);
    Dessert updateDessert(Dessert dessert);
    List<MetDTO> deleteDessert(Long id);
    List<MetDTO> getMets();
    Met findMet(Met met);
    Met metMapper(MetDTO met);
    MetDTO MostBuyedMet(LocalDate d1 , LocalDate d2);

}
