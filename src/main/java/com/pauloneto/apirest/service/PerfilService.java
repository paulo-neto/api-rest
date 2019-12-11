package com.pauloneto.apirest.service;

import com.pauloneto.apirest.enums.SimNaoEnum;
import com.pauloneto.apirest.models.Perfil;
import com.pauloneto.apirest.repository.PerfilRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

public class PerfilService {

    @Inject
    private PerfilRepository perfilRepository;

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Perfil save(Perfil perfil) {
        perfilRepository.save(perfil);
        return perfil;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Perfil inativar(Long id) {
        Perfil perfilBD = perfilRepository.findBy(id);
        if(Objects.isNull(perfilBD))
            throw new IllegalArgumentException("Perfil não encontrado!");

        perfilBD.setAtivo(SimNaoEnum.N);
        return perfilBD;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Perfil ativar(Long id) {
        Perfil perfilBD = perfilRepository.findBy(id);
        if(Objects.isNull(perfilBD))
            throw new IllegalArgumentException("Perfil não encontrado!");

        perfilBD.setAtivo(SimNaoEnum.S);
        return perfilBD;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void remove(Long id) {
        Perfil perfilBD = perfilRepository.findBy(id);
        if(Objects.isNull(perfilBD))
            throw new IllegalArgumentException("Perfil não encontrado!");

        perfilRepository.attachAndRemove(perfilBD);
    }
}
