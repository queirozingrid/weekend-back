package com.squirtle.weekend.security;

import com.squirtle.weekend.dto.output.estabelecimento.EstabelecimentoDTO;
import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public AutenticacaoService(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Estabelecimento> estabelecimento;
        if(username.contains("@")){
            estabelecimento = estabelecimentoRepository.findByEmail(username);
        }
        else {
            estabelecimento = estabelecimentoRepository.findByCnpj(username);
        }
        if(estabelecimento.isPresent()){
            return (UserDetails) estabelecimento.get();
        } else {
            throw new UsernameNotFoundException("Dados inválidos!");
        }
    }
}
