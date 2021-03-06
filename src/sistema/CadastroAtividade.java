
package sistema;

import java.util.List;

import basicas.AtividadeDiaria;
import interfaces.IRepositorioAtividadeDiaria;
import repositorios.RepositorioAtividadeDiariaArray;

public class CadastroAtividade {
	
	private IRepositorioAtividadeDiaria repositorio;
	
	public CadastroAtividade() {
		repositorio = RepositorioAtividadeDiariaArray.getInstance();
	}
	
	public void inserir(AtividadeDiaria atividade) {
		repositorio.inserir(atividade);
		RepositorioAtividadeDiariaArray.salvarArquivo();
	}
	
	public AtividadeDiaria procurar(String cpf) {
		return repositorio.procurar(cpf);
	}
	
	public List listar(String cpf) {
		return repositorio.listar(cpf);
	}
	
	public List listarHoras(String cpf) {
		return repositorio.listarHoras(cpf);
	}

}
