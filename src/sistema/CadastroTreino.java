package sistema;

import java.util.List;

import basicas.Cliente;
import basicas.Treino;
import excecoes.InstrutorLotadoException;
import excecoes.MatriculaNaoEncontradaException;
import interfaces.IRepositorioTreino;
import repositorios.RepositorioClienteArray;
import repositorios.RepositorioTreinoArray;

public class CadastroTreino {
	
	private IRepositorioTreino repositorio;

	public CadastroTreino() {
		
		repositorio = RepositorioTreinoArray.getInstance();
	}

	public void inserir(Treino treino) throws MatriculaNaoEncontradaException, InstrutorLotadoException {
		if(repositorio.contaInstrutorPorCliente(treino.getInstrutor().getMatricula()) < 10 
				 ) {
			this.repositorio.inserir(treino);
			RepositorioTreinoArray.salvarArquivo();	
		}
	}
	
	public Treino procurarClienteTreino(String matricula, String categoria) {
		return this.repositorio.procurarClienteTreino(matricula, categoria);
	}
	
	public void atualizar(Treino treino) {
		this.repositorio.atualizar(treino);
		RepositorioTreinoArray.salvarArquivo();	
	}
	
	public List listar(String matricula, String categoria) {
		return repositorio.listar(matricula, categoria);
	}
	
	public int contaInstrutorPorCliente(String matriculaInstrutor) {
		return repositorio.contaInstrutorPorCliente(matriculaInstrutor);
	}
	
	

}
