package ds.example.dsproject.controllers;


import ds.example.dsproject.dto.MetDTO;
import ds.example.dsproject.entities.Dessert;
import ds.example.dsproject.entities.Entree;
import ds.example.dsproject.entities.Plat;
import ds.example.dsproject.services.MetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/met")
public class MetController {
    private MetService metService;

    @GetMapping
    public List<MetDTO> MatList(){
        return metService.getMets();
    }

    @PostMapping("/plat")
    public Plat PlatAdd(@RequestBody Plat plat){
        return metService.addPlat(plat);
    }

    @PutMapping("/plat")
    @ResponseBody
    public Plat PlatUpdate(@RequestBody Plat plat) { return metService.updatePlat(plat); }

    @DeleteMapping("/plat/{id}")
    public List<MetDTO> PlatDelete(@PathVariable("id") Long id){ return metService.deletePlat(id); }

    @PostMapping("/entree")
    public Entree EntreeAdd(@RequestBody Entree entree){
        return metService.addEntree(entree);
    }

    @PutMapping("/entree")
    @ResponseBody
    public Entree EntreeUpdate(@RequestBody Entree entree) { return metService.updateEntree(entree); }

    @DeleteMapping("/entree/{id}")
    public List<MetDTO> EntreeDelete(@PathVariable("id") Long id) { return metService.deleteEntree(id); }

    @PostMapping("/dessert")
    public Dessert DessertAdd(@RequestBody Dessert dessert){
        return metService.addDessert(dessert);
    }

    @PutMapping("/dessert")
    @ResponseBody
    public Dessert DessertUpdate(@RequestBody Dessert dessert) { return metService.updateDessert(dessert); }

    @DeleteMapping("/dessert/{id}")
    public List<MetDTO> DessertDelete(@PathVariable Long id) { return metService.deleteDessert(id); }

    @GetMapping("/bestplate")
    @ResponseBody
    public MetDTO MostBuyedMetByDate (@RequestParam String d1, @RequestParam String d2 ){
        LocalDate m1 = LocalDate.parse(d1);
        LocalDate m2 = LocalDate.parse(d2);

        return metService.MostBuyedMet(m1,m2);
    }

}
