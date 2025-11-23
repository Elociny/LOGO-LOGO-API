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
                        .cor("black")
                        .tamanho("M")
                        .categoria(Categoria.FEMININO)
                        .imageUrl("https://acdn-us.mitiendanube.com/stores/001/286/352/products/camiseta-oversized-feminina-estampada-1-aa6f110f46bcebbc9517207170027695-640-0.webp")
                        .build());

                repository.save(Produto.builder()
                        .nome("Calça Jeans Masculina")
                        .descricao("Calça jeans slim masculina com lavagem escura.")
                        .preco(BigDecimal.valueOf(149.90))
                        .desconto(0)
                        .quantidade(25)
                        .cor("gray")
                        .tamanho("42")
                        .categoria(Categoria.MASCULINO)
                        .imageUrl("https://lojaviego.com.br/cdn/shop/files/Calca_Jeans_Masculina_Preta_Com_Elastano_Viego_1.jpg?v=1732901482")
                        .build());

                repository.save(Produto.builder()
                        .nome("Tênis Casual Unissex")
                        .descricao("Tênis casual unissex confortável e estiloso.")
                        .preco(BigDecimal.valueOf(199.90))
                        .desconto(15)
                        .quantidade(50)
                        .cor("black")
                        .tamanho("39")
                        .categoria(Categoria.CALCADOS)
                        .imageUrl("https://grupooscar.vteximg.com.br/arquivos/ids/2409147/17509733747780.jpg")
                        .build());

                repository.save(Produto.builder()
                        .nome("Bolsa Feminina Couro Ecológico")
                        .descricao("Bolsa feminina em couro ecológico com alça ajustável.")
                        .preco(BigDecimal.valueOf(129.90))
                        .desconto(5)
                        .quantidade(12)
                        .cor("brown")
                        .tamanho("Único")
                        .categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://newsunset.com.br/cdn/shop/products/Hc10c025a24754b1695401fdc82e2602cy_800x.jpg?v=1628829412")
                        .build());

                System.out.println("✅ Produtos inseridos com sucesso!");
            } else {
                System.out.println("ℹ️ Produtos já existem no banco, nenhum dado inserido.");
            }
        };
    }
}
