package com.logologo.api.config;

import com.logologo.api.model.*;
import com.logologo.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    CommandLineRunner loadData(
            ProdutoRepository produtoRepository,
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            CartaoRepository cartaoRepository,
            CarrinhoRepository carrinhoRepository,
            ComprarRepository comprarRepository
    ) {
        return args -> {
            if (produtoRepository.count() == 0) {
                System.out.println("🚀 Iniciando carga de dados de teste...");

                Produto pCamiseta = produtoRepository.save(Produto.builder()
                        .nome("Camiseta Feminina LogoLogo")
                        .descricao("Camiseta básica feminina 100% algodão com estampa da LogoLogo.")
                        .preco(BigDecimal.valueOf(79.90)).desconto(10).quantidade(30).cor("black").tamanho("M").categoria(Categoria.FEMININO)
                        .imageUrl("https://acdn-us.mitiendanube.com/stores/001/286/352/products/camiseta-oversized-feminina-estampada-1-aa6f110f46bcebbc9517207170027695-640-0.webp").build());

                Produto pCalca = produtoRepository.save(Produto.builder()
                        .nome("Calça Jeans Masculina")
                        .descricao("Calça jeans slim masculina com lavagem escura.")
                        .preco(BigDecimal.valueOf(149.90)).desconto(0).quantidade(25).cor("gray").tamanho("42").categoria(Categoria.MASCULINO)
                        .imageUrl("https://lojaviego.com.br/cdn/shop/files/Calca_Jeans_Masculina_Preta_Com_Elastano_Viego_1.jpg?v=1732901482").build());

                Produto pTenis = produtoRepository.save(Produto.builder()
                        .nome("Tênis Casual Unissex")
                        .descricao("Tênis casual unissex confortável e estiloso.")
                        .preco(BigDecimal.valueOf(199.90)).desconto(15).quantidade(50).cor("black").tamanho("39").categoria(Categoria.CALCADOS)
                        .imageUrl("https://grupooscar.vteximg.com.br/arquivos/ids/2409147/17509733747780.jpg").build());

                Produto pBolsa = produtoRepository.save(Produto.builder()
                        .nome("Bolsa Feminina Couro Ecológico")
                        .descricao("Bolsa feminina em couro ecológico com alça ajustável.")
                        .preco(BigDecimal.valueOf(129.90)).desconto(5).quantidade(12).cor("brown").tamanho("Único").categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://newsunset.com.br/cdn/shop/products/Hc10c025a24754b1695401fdc82e2602cy_800x.jpg?v=1628829412").build());

                produtoRepository.save(Produto.builder()
                        .nome("Vestido Floral Verão")
                        .descricao("Vestido leve e soltinho, ideal para dias quentes.")
                        .preco(BigDecimal.valueOf(89.90)).desconto(0).quantidade(20).cor("blue").tamanho("P").categoria(Categoria.FEMININO)
                        .imageUrl("https://divamoderna.com.br/cdn/shop/files/SHEIN_VCAY_Vestido_De_Alas_De_Espaguete_Com_Decote_Em_V_E_Estampa_De_Flores__SHEIN_Brasil_main_5_1340x.jpg?v=1757384695").build());

                produtoRepository.save(Produto.builder()
                        .nome("Jaqueta Puffer Preta")
                        .descricao("Jaqueta acolchoada resistente ao frio e vento.")
                        .preco(BigDecimal.valueOf(299.90)).desconto(20).quantidade(15).cor("black").tamanho("G").categoria(Categoria.MASCULINO)
                        .imageUrl("https://img.lojasrenner.com.br/item/881463856/original/3.jpg").build());

                Produto pRelogioEsgotado = produtoRepository.save(Produto.builder()
                        .nome("Relógio Digital Esportivo")
                        .descricao("Relógio à prova d'água com cronômetro e luz noturna.")
                        .preco(BigDecimal.valueOf(159.90)).desconto(10).quantidade(0).cor("black").tamanho("Único").categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://cdn.eclock.com.br/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/r/e/relogio-digital-x-watch-masculino-esportivo-xmppd707bxpx-04.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Bota Coturno Tratorada")
                        .descricao("Coturno estiloso com solado tratorado e fechamento em cadarço.")
                        .preco(BigDecimal.valueOf(229.90)).desconto(0).quantidade(18).cor("black").tamanho("37").categoria(Categoria.CALCADOS)
                        .imageUrl("https://img.irroba.com.br/fit-in/1000x1000/filters:fill(fff):quality(80)/vittaiee/catalog/humanizadas/vittal-524.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Camisa Polo Azul Marinho")
                        .descricao("Camisa polo clássica em algodão piquet.")
                        .preco(BigDecimal.valueOf(69.90)).desconto(5).quantidade(60).cor("blue").tamanho("M").categoria(Categoria.MASCULINO)
                        .imageUrl("https://m.media-amazon.com/images/I/31Lr+9p0dsL._AC_.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Saia Plissada Midi")
                        .descricao("Saia elegante com caimento fluido, perfeita para ocasiões formais.")
                        .preco(BigDecimal.valueOf(119.90)).desconto(0).quantidade(22).cor("pink").tamanho("M").categoria(Categoria.FEMININO)
                        .imageUrl("https://images.veria.com.br/cdn-cgi/image/width=683,quality=85,fit=cover,format=auto,format=auto/https://images.veria.com.br/upload/7ee412cb-727d-4738-9ec3-301bd67119a1/82862RSQSV%20-%20A87882OW%20(2).jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Óculos de Sol Aviador")
                        .descricao("Óculos de sol modelo aviador com proteção UV400.")
                        .preco(BigDecimal.valueOf(89.90)).desconto(15).quantidade(35).cor("gold").tamanho("Único").categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://images.tcdn.com.br/img/img_prod/647611/oculos_de_sol_aviador_redondo_metal_mackage_liam_4331_variacao_3187_2_4d747ef5920149be3a6b8385d71b075a.jpg").build());

                Produto pTenisCorrida = produtoRepository.save(Produto.builder()
                        .nome("Tênis Running Performance")
                        .descricao("Tênis leve com amortecimento para corridas e caminhadas.")
                        .preco(BigDecimal.valueOf(259.90)).desconto(20).quantidade(25).cor("gray").tamanho("41").categoria(Categoria.CALCADOS)
                        .imageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQOYZhWXszn6gxua0jOrqC1SPhPmxfLcGBKWQZKT0Aivv6NMHfnuVBeNextdsCxAgosXdxQAcc0ieb1KPhiJdeybZZ6LDcvy1suXu8NWV1ZAQQ1v5y6ofp11fkRA58OgNDuQGJiKw&usqp=CAc").build());

                produtoRepository.save(Produto.builder()
                        .nome("Moletom Canguru Cinza")
                        .descricao("Blusa de moletom com capuz e bolso canguru.")
                        .preco(BigDecimal.valueOf(139.90)).desconto(0).quantidade(40).cor("gray").tamanho("GG").categoria(Categoria.MASCULINO)
                        .imageUrl("https://www.lojamirante.com.br/uploads/produtos/moletom-canguru-com-capuz-feminino-cinza-mescla-649ee1093e608.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Shorts Jeans Desfiado")
                        .descricao("Shorts jeans cintura alta com barra desfiada.")
                        .preco(BigDecimal.valueOf(69.90)).desconto(10).quantidade(30).cor("blue").tamanho("38").categoria(Categoria.FEMININO)
                        .imageUrl("https://http2.mlstatic.com/D_NQ_NP_903303-MLB90295417787_082025-O-shorts-jeans-curto-feminino-cintura-alta-moda-destroyde.webp").build());

                produtoRepository.save(Produto.builder()
                        .nome("Boné Aba Curva Bordado")
                        .descricao("Boné estilo beisebol com ajuste traseiro.")
                        .preco(BigDecimal.valueOf(49.90)).desconto(0).quantidade(50).cor("gray").tamanho("Único").categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://img.lojasrenner.com.br/item/929263504/original/2.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Sandália Rasteira Pedraria")
                        .descricao("Rasteirinha delicada com detalhes em pedras.")
                        .preco(BigDecimal.valueOf(59.90)).desconto(5).quantidade(20).cor("gold").tamanho("36").categoria(Categoria.CALCADOS)
                        .imageUrl("https://images.tcdn.com.br/img/img_prod/1313426/sandalia_rasteira_pedraria_503_1_94c681c78301b038c8dc86bd117c7778.jpeg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Blazer Slim Fit")
                        .descricao("Blazer corte moderno para compor looks sociais ou casuais.")
                        .preco(BigDecimal.valueOf(289.90)).desconto(10).quantidade(15).cor("blue").tamanho("50").categoria(Categoria.MASCULINO)
                        .imageUrl("https://images.tcdn.com.br/img/img_prod/1289039/blazer_masculino_malha_slim_azul_royal_docthos_1027_1_98b28028e5cae9a0dcf20aadf28bc8dd.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Blusa de Tricô Gola Alta")
                        .descricao("Blusa quentinha e confortável para o inverno.")
                        .preco(BigDecimal.valueOf(99.90)).desconto(0).quantidade(25).cor("white").tamanho("M").categoria(Categoria.FEMININO)
                        .imageUrl("https://www.tigstore.com.br/cdn/shop/files/0101699__5_800x.jpg?v=1696525812").build());

                produtoRepository.save(Produto.builder()
                        .nome("Chinelo Slide Urbano")
                        .descricao("Chinelo slide confortável para o dia a dia.")
                        .preco(BigDecimal.valueOf(49.90)).desconto(0).quantidade(0).cor("black").tamanho("40").categoria(Categoria.CALCADOS)
                        .imageUrl("https://images.tcdn.com.br/img/img_prod/1226749/chinelo_vans_slide_la_costa_vn0a5h_2697_1_656ee840756b304ed3f6812d08f80d82.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Carteira Couro Legítimo")
                        .descricao("Carteira compacta com compartimentos para cartões e notas.")
                        .preco(BigDecimal.valueOf(89.90)).desconto(10).quantidade(35).cor("brown").tamanho("Único").categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://imgmarketplace.lojasrenner.com.br/20001/6821/7010706086140/7510713116079/1.jpeg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Calça Jogger Sarja")
                        .descricao("Calça estilo jogger com elástico na barra e cintura.")
                        .preco(BigDecimal.valueOf(109.90)).desconto(15).quantidade(28).cor("green").tamanho("42").categoria(Categoria.MASCULINO)
                        .imageUrl("https://conscienciajeans.vtexassets.com/arquivos/ids/549209-800-auto?v=638501380289300000&width=800&height=auto&aspect=true").build());

                produtoRepository.save(Produto.builder()
                        .nome("Macacão Pantacourt")
                        .descricao("Macacão soltinho e elegante, peça única prática.")
                        .preco(BigDecimal.valueOf(139.90)).desconto(0).quantidade(18).cor("black").tamanho("G").categoria(Categoria.FEMININO)
                        .imageUrl("https://cdn.awsli.com.br/800x800/1416/1416067/produto/138553864d9997b7cf5.jpg").build());

                produtoRepository.save(Produto.builder()
                        .nome("Sapatilha Bico Fino")
                        .descricao("Sapatilha clássica, confortável para trabalho.")
                        .preco(BigDecimal.valueOf(79.90)).desconto(5).quantidade(40).cor("nude").tamanho("35").categoria(Categoria.CALCADOS)
                        .imageUrl("https://cdn.dooca.store/5791/products/sapatilha-bico-fino-em-couro-dina-mirtz-nude-5_640x640+fill_ffffff.jpg?v=1732650094&webp=0").build());

                produtoRepository.save(Produto.builder()
                        .nome("Cinto Dupla Face")
                        .descricao("Cinto versátil preto e marrom com fivela giratória.")
                        .preco(BigDecimal.valueOf(59.90)).desconto(0).quantidade(45).cor("brown").tamanho("100cm").categoria(Categoria.ACESSORIOS)
                        .imageUrl("https://img.irroba.com.br/fit-in/600x600/filters:fill(fff):quality(80)/rambourg/catalog/produtos/cintos/dupla-face/img-8116.jpg").build());

                System.out.println("✅ Produtos salvos.");

                Cliente cliente = new Cliente();
                cliente.setNome("Cliente Teste");
                cliente.setEmail("teste@email.com");
                cliente.setSenha("123456");
                cliente.setTelefone("11999998888");

                cliente.setImageUrl("http://localhost:8080/uploads/user.png");

                Carrinho carrinhoNovo = new Carrinho();
                carrinhoNovo.setCliente(cliente);
                carrinhoNovo.setItens(new ArrayList<>());
                cliente.setCarrinho(carrinhoNovo);

                cliente = clienteRepository.save(cliente);
                System.out.println("✅ Cliente salvo: " + cliente.getEmail());

                Endereco endereco = Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero(1000)
                        .bairro("Bela Vista")
                        .cidade("São Paulo")
                        .estado("SP")
                        .cep("01310-100")
                        .complemento("Apto 50")
                        .cliente(cliente)
                        .build();

                enderecoRepository.save(endereco);

                Cartao card1 = Cartao.builder()
                        .numero("5500000000000000")
                        .nomeTitular("CLIENTE TESTE")
                        .validade("12/30")
                        .cvv("123")
                        .tipo(TipoCartao.CREDITO)
                        .bandeira(BandeiraCartao.MASTERCARD)
                        .cliente(cliente)
                        .build();
                cartaoRepository.save(card1);

                Cartao card2 = Cartao.builder()
                        .numero("4111111111111111")
                        .nomeTitular("CLIENTE TESTE")
                        .validade("10/28")
                        .cvv("456")
                        .tipo(TipoCartao.DEBITO)
                        .bandeira(BandeiraCartao.VISA)
                        .cliente(cliente)
                        .build();
                cartaoRepository.save(card2);

                Carrinho carrinho = cliente.getCarrinho();
                if (carrinho == null) {
                    carrinho = carrinhoRepository.findByClienteId(cliente.getId());
                }

                CarrinhoItem item1 = new CarrinhoItem();
                item1.setCarrinho(carrinho);
                item1.setProduto(pCamiseta);
                item1.setQuantidade(1);
                item1.setPrecoNoMomento(pCamiseta.getPreco().doubleValue());

                CarrinhoItem itemEsgotado = new CarrinhoItem();
                itemEsgotado.setCarrinho(carrinho);
                itemEsgotado.setProduto(pRelogioEsgotado);
                itemEsgotado.setQuantidade(1);
                itemEsgotado.setPrecoNoMomento(pRelogioEsgotado.getPreco().doubleValue());

                carrinho.getItens().add(item1);
                carrinho.getItens().add(itemEsgotado);

                carrinhoRepository.save(carrinho);
                System.out.println("✅ Carrinho populado (inclusive item esgotado).");

                List<Produto> prodsCompra1 = new ArrayList<>();
                prodsCompra1.add(pTenis);
                prodsCompra1.add(pBolsa);

                Comprar c1 = Comprar.builder()
                        .cliente(cliente)
                        .produtos(prodsCompra1)
                        .dataCompra(LocalDateTime.now().minusDays(10))
                        .status(StatusCompra.ENTREGUE)
                        .formaPagamento(FormaPagamento.CARTAO)
                        .cartao(card1)
                        .build();
                c1.calcularValorTotal();
                comprarRepository.save(c1);

                List<Produto> prodsCompra2 = new ArrayList<>();
                prodsCompra2.add(pTenisCorrida);

                Comprar c2 = Comprar.builder()
                        .cliente(cliente)
                        .produtos(prodsCompra2)
                        .dataCompra(LocalDateTime.now().minusDays(2))
                        .status(StatusCompra.ENVIADO)
                        .formaPagamento(FormaPagamento.PIX)
                        .build();
                c2.calcularValorTotal();
                comprarRepository.save(c2);

                List<Produto> prodsCompra3 = new ArrayList<>();
                prodsCompra3.add(pCalca);

                Comprar c3 = Comprar.builder()
                        .cliente(cliente)
                        .produtos(prodsCompra3)
                        .dataCompra(LocalDateTime.now())
                        .status(StatusCompra.PENDENTE)
                        .formaPagamento(FormaPagamento.CARTAO)
                        .cartao(card2)
                        .build();
                c3.calcularValorTotal();
                comprarRepository.save(c3);

                System.out.println("✅ Compras de histórico salvas!");
                System.out.println("🎉 --- DADOS DE TESTE CARREGADOS --- 🎉");
                System.out.println("👉 Login: teste@email.com / Senha: 123456");
            } else {
                System.out.println("ℹ️ O banco de dados já contém dados.");
            }
        };
    }
}