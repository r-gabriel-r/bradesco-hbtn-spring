package com.example.demo.service;


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
		Assertions.assertEquals(expect, prod);

	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExistir() {
		// given
		Mockito.when(produtoRepository.findById(1L)).thenThrow(new RuntimeException("Produto não encontrado"));
		// then
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
				() -> produtoService.buscarPorId(1L));
		Assertions.assertEquals("Produto não encontrado", exception.getMessage());

	}
}