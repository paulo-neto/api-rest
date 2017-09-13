package com.apirest.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.apirest.mesages.KeyMesages;
import com.apirest.mesages.MesagesProperties;
import com.apirest.models.Usuario;
import com.apirest.repository.impl.UsuarioRepository;
import com.apirest.util.AssertUtils;
import com.apirest.util.BeanCDILocator;
import com.apirest.util.HashPasswordUtil;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsuarioRepository usuarioRepository = BeanCDILocator.getBean(UsuarioRepository.class);
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        password = HashPasswordUtil.generateHash(password);
        Usuario usuEncontrado = usuarioRepository.buscarPorLoginSenha(name, password);
        if (!AssertUtils.isEmpty(usuEncontrado)) {
        	Collection<? extends GrantedAuthority> authorities = usuEncontrado.getPerfis();
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        }else {
			throw new UsernameNotFoundException(MesagesProperties.getInstancia().getMesage(KeyMesages.BAD_CREDENCIALS));
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
