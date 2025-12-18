package com.example.demo.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	UsuarioRepository usuarioRepository;

	@InjectMocks
	UsuarioService usuarioService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveRetornarUsuarioQuandoIdExistir() {
		// given
		when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario(1L, "Erica", "erica@email.com")));
		// when
		var ret = usuarioService.buscarUsuarioPorId(1L);
		// then
		assertNotNull(ret);
	}

	@Test
	void deveLancarExcecaoQuandoUsuarioNaoExistir() {
		// given
		when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
		// when
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
				() -> usuarioService.buscarUsuarioPorId(1L));
		Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
	}
	
	@Test
	void deveSalvarUsuarioComSucesso() {
		// given
		var usuario = new Usuario(1L, "Erica", "erica@email.com");
		when(usuarioRepository.save(usuario)).thenReturn(usuario);
		// when
		var ret = usuarioService.salvarUsuario(usuario);
		// then
		assertEquals(usuario, ret);
	}

}
