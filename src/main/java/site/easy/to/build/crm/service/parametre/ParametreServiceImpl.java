package site.easy.to.build.crm.service.parametre;

import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Parametre;
import site.easy.to.build.crm.repository.ParametreRepository;

import java.util.List;

@Service
public class ParametreServiceImpl implements ParametreService {

    private final ParametreRepository parametreRepository;

    public ParametreServiceImpl(ParametreRepository parametreRepository) {
        this.parametreRepository = parametreRepository;
    }

    @Override
    public Parametre findById(int id) {
        return parametreRepository.findById(id);
    }

    @Override
    public List<Parametre> findAll() {
        return parametreRepository.findAll();
    }

    @Override
    public Parametre save(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    @Override
    public void delete(Parametre parametre) {
        parametreRepository.delete(parametre);
    }

    @Override
    public Parametre findByNom(String nom) {
        return parametreRepository.findByNom(nom);
    }
}