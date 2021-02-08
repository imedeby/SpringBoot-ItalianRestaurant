package ds.example.dsproject.implementations;

import ds.example.dsproject.dto.MetDTO;
import ds.example.dsproject.entities.*;
import ds.example.dsproject.repositories.*;
import ds.example.dsproject.services.MetService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MetServiceImp<p> implements MetService {

    private MetRepository metRepository;
    private PlatRepository platRepository;
    private EntreeRepository entreeRepository;
    private DessertRepository dessertRepository;
    private ModelMapper mapper;
    private TicketRepository ticketRepository;

    @Override
    public Plat addPlat(Plat plat) {
        return platRepository.save(plat);
    }

    @Override
    public Plat updatePlat(Plat plat){
        Plat p = platRepository.findById(plat.getId()).orElseThrow(() -> new EntityNotFoundException("Plat not found"));
        p.setName(plat.getName());
        p.setPrice(plat.getPrice());
        p.setTickets(plat.getTickets());
        return platRepository.save(p);
    }
    @Override
    public List<MetDTO> deletePlat(Long id){
        Plat p = platRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plat not found"));
        platRepository.delete(p);
        return this.getMets();
    }
    @Override
    public Entree addEntree(Entree entree) {
        return entreeRepository.save(entree);
    }

    @Override
    public Entree updateEntree(Entree entree){
        Entree ent = entreeRepository.findById(entree.getId()).orElseThrow(() -> new EntityNotFoundException("Entree not found"));
        ent.setName(entree.getName());
        ent.setPrice(entree.getPrice());
        ent.setTickets(entree.getTickets());
        return entreeRepository.save(ent);
    }

    @Override
    public List<MetDTO> deleteEntree(Long id){
        Entree entree = entreeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entree not found"));
        entreeRepository.delete(entree);
        return this.getMets();
    }
    @Override
    public Dessert addDessert(Dessert dessert) {
        return dessertRepository.save(dessert);
    }

    @Override
    public Dessert updateDessert(Dessert dessert){
        Dessert des = dessertRepository.findById(dessert.getId()).orElseThrow(() -> new EntityNotFoundException("Dessert not found"));
        des.setName(dessert.getName());
        des.setPrice(dessert.getPrice());
        des.setTickets(dessert.getTickets());
        return dessertRepository.save(des);
    }

    @Override
    public List<MetDTO> deleteDessert(Long id){
        Dessert des = dessertRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dessert not found"));
        dessertRepository.delete(des);
        return this.getMets();
    }

    @Override
    public List<MetDTO> getMets() {
        List<Met> liste = metRepository.findAll();
        List<MetDTO> lis = liste.stream().map((m) -> this.mapper.map(m, MetDTO.class))
                .collect(Collectors.toList());
        return lis;
    }

    @Override
    public Met findMet(Met met) {
        Met m = metRepository.findById(met.getId()).orElseThrow(() -> new EntityNotFoundException("Met not found"));
        return m;
    }

    public MetDTO MostBuyedMet(LocalDate d1 , LocalDate d2){

        List<Ticket> tickets = ticketRepository.findAll().stream()
                .filter( t -> (t.getDate().isAfter(d1)  || t.getDate().equals(d1)) && (t.getDate().isBefore(d2) || t.getDate().equals(d2)))
                .collect(Collectors.toList());

        List<List<Met>> mets = tickets.stream().map(m -> m.getMets()).collect(Collectors.toList());
        Plat plat = new Plat();
        Integer Max = 0;
        HashMap<Plat, Integer> plats = new HashMap<>();
        for (List<Met> met: mets) {
            for (Met m : met){
                if(m instanceof  Plat) {
                    if (plats.containsKey((Plat) m)) {
                        plats.put((Plat) m, plats.get((Plat) m) + 1);
                        if (plats.get((Plat) m) > Max) {
                            Max = plats.get((Plat) m);
                            plat = (Plat) m;
                        }
                    } else{
                        plats.put((Plat) m, 1);
                    }
                }

            }
        }
        return mapper.map(plat, MetDTO.class);
    }

    @Override
    public Met metMapper(MetDTO met){
        String s = met.getType();
        Class c = null;
        try {
            c = Class.forName("ds.example.dsproject.entities."+s);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Met m = (Met) mapper.map(met, c);
        return m;
    }
}
