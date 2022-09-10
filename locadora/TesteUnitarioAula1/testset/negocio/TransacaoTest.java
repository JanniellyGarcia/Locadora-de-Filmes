package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.Cliente;
import negocio.Filme;
import negocio.Genero;
import negocio.Locacao;
import negocio.Transacao;

public class TransacaoTest {

	Transacao transacao;
	Locacao locacao1;
	Locacao locacao2;
	Filme filme1;
	Filme filme2;

	@Before
	public void setUp() throws Exception {
		
		locacao1 = new Locacao();
		locacao2 = new Locacao();
		filme1 = new Filme("Java", Genero.ROMANCE);
		filme1.valorCompra = 100;

		filme2 = new Filme("JavaScript", Genero.ROMANCE);
		filme2.valorCompra = 50;
		filme2.id=20;

		locacao1.alugar(new Cliente("Izaias", 2, true), filme1, "21/06/2022", "19:00");
		locacao2.alugar(new Cliente("Thiago", 3, true), filme2, "20/06/2022", "22:00");
		

		transacao = new Transacao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void valorLocacaoTotalTest() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		assertEquals(transacao.alugueis.get(0).cliente.nome, "Izaias");
		assertTrue("Filme deveria ser selecionado corretamente",transacao.alugueis.get(1).filme.id==20);
		assertEquals(150, transacao.valorLocacaoTotal(), 0.1);
	}

	@Test
	public void valorLocacaoTotalTest2() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao2);
		assertEquals(200, transacao.valorLocacaoTotal(), 0.1);
	}

	@Test 
	public void buscaClienteIdTest() {
		
		transacao.alugueis.add(locacao2);
		assertEquals("Thiago",transacao.buscaCliente(3).nome);
	}
	
	@Test 
	//public void calculoLucroTest() {
		
		//locacao2.setValorAluguel(25);
		//transacao.alugueis.add(locacao2);
		
		//assertEquals(40,transacao.calculoLucro(20),0.01);
	//}
	public void test1() {

		assertEquals(Math.PI, 3.14, 0.01);
		assertTrue("java".equalsIgnoreCase("Java"));
		Filme f =new Filme("a", Genero.ROMANCE);
		assertNull(f); // assertNotNull();
		Filme f2 =new Filme("a", Genero.ROMANCE);

		assertTrue(f==f2);
		
		assertSame(f, f2); // asserNotSame)();

		assertTrue("Comparacao de objetos", f == f2);

	}
	
	//Test questão 1:
	@Test
	public void dataTest() {
		
		locacao2.data = "19/06/2022";
		
		
		assertEquals("19/06/2022", locacao2.data);
		
	}
	
	@Test
	public void horatest() {
		
		locacao2.hora = "07:00";
		
		assertEquals("07:00", locacao2.hora);
		
	}
	
	//Test questão 2:
	@Test
	public void statusClienteAtivoTest () {
		
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		locacao.alugar(cliente3, filme2, "20/06/2022", "22:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao);
		
		
		assertEquals("João", transacao.buscaCliente(3).nome);
	}
	
	//Test Questão 3:
	@Test
	public void GeneroMaisAlugadoTest() {
		
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Locacao locacao1 = new Locacao();
		Locacao locacao2 = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
		Filme filme2 = new Filme("CSharp", Genero.ROMANCE);
		Filme filme3 = new Filme("Java", Genero.DRAMA);
		locacao.alugar(cliente3, filme1, "20/06/2022", "22:00");
		locacao1.alugar(cliente3, filme2, "20/06/2022", "22:00");
		locacao2.alugar(cliente3, filme3, "20/06/2022", "22:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao);
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		
		
		assertEquals("O gênero mais alugado é Romance!", transacao.retornaOGeneroMaisAlugado(transacao.alugueis));
	}
	
	//Teste Questão 4:
	@Test
	public void aluguelComDescontoTest() {
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
		filme1.valorAluguel = 80;
		locacao.locacaoComDesconto(cliente3, filme1, "20/06/2022", "22:00");
		

		assertEquals(64 ,locacao.valorAluguel, 0.1);
	}
	
	//Teste Questão 5:
		@Test
		public void alugarFavoritosTest() {
			Cliente cliente3 = new Cliente("João", 3, true);
			Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
			Filme filme2 = new Filme("Csharp", Genero.ROMANCE);
			cliente3.filmesFavoritos.add(filme1);
			cliente3.filmesFavoritos.add(filme2);
			Locacao locacao = new Locacao();
			
			filme1.valorAluguel = 80;
			locacao.locacaoComDesconto(cliente3, cliente3.buscaFilmeFavorito(cliente3,"Csharp"), "20/06/2022", "22:00");
			Transacao transacao = new Transacao();
			transacao.alugueis.add(locacao);

			assertEquals("Csharp" , transacao.buscaCliente(3).buscaFilmeFavorito(cliente3,"Csharp").nome );
		}

}
