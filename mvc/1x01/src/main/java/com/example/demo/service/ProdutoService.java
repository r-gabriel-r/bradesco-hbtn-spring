package com.example.demo.service;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
	private List<Produto> produtos = new ArrayList<>();
	private Long contadorId = 1L;

	public List<Produto> listarProdutos() {
		return this.produtos;
	}

	public Produto adicionarProduto(Produto produto) {
		this.produtos.add(produto);
		return produto;
	}

	public Produto atualizarProduto(Long id, Produto produtoAtualizado) {

		this.produtos.stream().filter(p -> p.getId() == id).findFirst().ifPresent(p -> {
			p.setNome(produtoAtualizado.getNome());
			p.setPreco(produtoAtualizado.getPreco());
		});
		return produtoAtualizado;

	}

	public boolean deletarProduto(Long id) {
		List<Produto> newList= this.produtos.stream().filter(p -> p.getId() != id).collect(Collectors.toList());
		
		boolean excluiu = false;
		if(newList.size() < this.produtos.size()) {
			excluiu = true;
		}
		
		this.produtos = newList;
		return excluiu;

	}
}
