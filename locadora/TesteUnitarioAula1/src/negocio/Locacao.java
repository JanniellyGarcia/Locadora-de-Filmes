package negocio;

public class Locacao {

	protected Cliente cliente;
	protected Filme filme;
	protected double valorAluguel;
	protected String data;
	protected String hora;
	
	
	public void alugar(Cliente c, Filme f, String data, String hora) {
		if(c.status == true) {
			this.cliente = c;
			this.filme = f;
			this.data = data;
			this.hora = hora;
			
		}
		else {
			System.out.println("Cliente Inativo. Operação não pode ser realizada!");
			
		}
		
		
	}
	public void setValorAluguel(double valorAluguel) {
		this.valorAluguel=valorAluguel;
	}
	public void locacaoComDesconto(Cliente c, Filme f, String data, String hora) {
		
		if(c.status == true) {
			this.cliente = c;
			this.filme = f;
			this.data = data;
			this.hora = hora;
			switch (f.genero) {
			case ROMANCE: 
				this.valorAluguel = f.valorAluguel * 0.8;
				break;
			case DRAMA: 
				this.valorAluguel = f.valorAluguel * 0.9;
				break;
			case COMEDIA:
				this.valorAluguel = f.valorAluguel * 0.85;
				break;
			default:
				break;
			}
			
		}
		else {
			System.out.println("Cliente Inativo. Operação não pode ser realizada!");
		}
		
	}
	
}
