package com.logologo.api.config;

import com.logologo.api.model.Categoria;
import com.logologo.api.model.Produto;
import com.logologo.api.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProdutoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Produto.builder()
                        .nome("Camiseta Feminina LogoLogo")
                        .descricao("Camiseta básica feminina 100% algodão com estampa da LogoLogo.")
                        .preco(BigDecimal.valueOf(79.90))
                        .desconto(10)
                        .quantidade(30)
                        .cor("Branca")
                        .tamanho("M")
                        .categoria(Categoria.FEMININO)
                        .imageUrl("https://exemplo.com/imagens/camiseta-feminina.jpg")
                        .build());

                repository.save(Produto.builder()
                        .nome("Calça Jeans Masculina")
                        .descricao("Calça jeans slim masculina com lavagem escura.")
                        .preco(BigDecimal.valueOf(149.90))
                        .desconto(0)
                        .quantidade(25)
                        .cor("Azul escuro")
                        .tamanho("42")
                        .categoria(Categoria.MASCULINO)
                        .imageUrl("https://exemplo.com/imagens/calca-jeans.jpg")
                        .build());

                repository.save(Produto.builder()
                        .nome("Tênis Casual Unissex")
                        .descricao("Tênis casual unissex confortável e estiloso.")
                        .preco(BigDecimal.valueOf(199.90))
                        .desconto(15)
                        .quantidade(50)
                        .cor("Preto")
                        .tamanho("39")
                        .categoria(Categoria.CALCADOS)
                        .imageUrl("https://exemplo.com/imagens/tenis-unissex.jpg")
                        .build());

                repository.save(Produto.builder()
                        .nome("Bolsa Feminina Couro Ecológico")
                        .descricao("Bolsa feminina em couro ecológico com alça ajustável.")
                        .preco(BigDecimal.valueOf(129.90))
                        .desconto(5)
                        .quantidade(12)
                        .cor("Marrom")
                        .tamanho("Único")
                        .categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://exemplo.com/imagens/bolsa-feminina.jpg")
                        .build());

                System.out.println("✅ Produtos inseridos com sucesso!");
            } else {
                System.out.println("ℹ️ Produtos já existem no banco, nenhum dado inserido.");
            }
        };
    }
}
