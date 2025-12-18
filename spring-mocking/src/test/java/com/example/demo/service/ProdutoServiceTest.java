package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@InjectMocks
	private ProdutoService produtoService;

	@Test
	void deveRetornarProdutoQuandoIdExistir() {
		// givena
		var expect = new Produto(1L, "Azeite", 25l);
		Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(expect));
		// when
		var prod = produtoService.buscarPorId(1L);
		// then
		assertThat(prod).isEqualTo(expect);

	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExistir() {
		// given
		Mockito.when(produtoRepository.findById(1L)).thenThrow(new RuntimeException("Produto não encontrado"));
		// then
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
				() -> produtoService.buscarPorId(1L));

		// Optionally, assert the exception's message
		assertThat(exception.getMessage()).isEqualTo("Produto não encontrado");

	}
}