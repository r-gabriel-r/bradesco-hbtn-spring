package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario buscarUsuarioPorId(long id) {
		
		var usuario = this.usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new RuntimeException("Usuario não encontrado");
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

}
