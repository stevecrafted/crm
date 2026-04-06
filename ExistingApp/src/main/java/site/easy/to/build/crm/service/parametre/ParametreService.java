package site.easy.to.build.crm.service.parametre;

import site.easy.to.build.crm.entity.Parametre;

import java.util.List;

public interface ParametreService {
    public Parametre findById(int id);

    public List<Parametre> findAll();

    public Parametre save(Parametre parametre);

    public void delete(Parametre parametre);

    public Parametre findByNom(String nom);
}