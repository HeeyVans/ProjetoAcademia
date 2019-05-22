package repositorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import basicas.Cliente;
import excecoes.ClienteJaCadastradoException;
import excecoes.NaoEncontradoException;
import excecoes.ParametroNuloException;
import interfaces.IRepositorioCliente;

public class RepositorioClienteArquivo implements IRepositorioCliente, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente[] cliente;
	private int indice;
	
	private final static int TAMANHO = 100;
	public static RepositorioClienteArquivo instance;
	
	public RepositorioClienteArquivo() {
		this.cliente = new Cliente[TAMANHO];
		this.indice = 0;
	}
	
	public static RepositorioClienteArquivo getInstance() {
	    if (instance == null) {
	      instance = lerDoArquivo();
	    }
	    return instance;
	  }
	
	public static RepositorioClienteArquivo lerDoArquivo() {
		RepositorioClienteArquivo instanciaLocal = null;

	    File in = new File("clientes.dat");
	    FileInputStream fis = null;
	    ObjectInputStream ois = null;
	    try {
	      fis = new FileInputStream(in);
	      ois = new ObjectInputStream(fis);
	      Object o = ois.readObject();
	      instanciaLocal = (RepositorioClienteArquivo) o;
	    } catch (Exception e) {
	      instanciaLocal = new RepositorioClienteArquivo();
	    } finally {
	      if (ois != null) {
	        try {
	          ois.close();
	        } catch (IOException e) {
	        }
	      }
	    }

	    return instanciaLocal;
	  }

	public void salvarArquivo() {
	    if (instance == null) {
	      return;
	    }
	    File out = new File("clientes.dat");
	    FileOutputStream fos = null;
	    ObjectOutputStream oos = null;

	    try {
	      fos = new FileOutputStream(out);
	      oos = new ObjectOutputStream(fos);
	      oos.writeObject(instance);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (oos != null) {
	        try {
	          oos.close();
	        } catch (IOException e) {
	        }
	      }
	    }
	}
	
	@Override
	public void inserir(Cliente clientes) throws ClienteJaCadastradoException {
		for(int i = 0; i < indice; i++) {
			if(cliente[i] == null) {
				cliente[i] = clientes;
				return;
			}
		}
		
	}

	@Override
	public Cliente procurar(String matricula) throws ParametroNuloException {
		if(matricula.equals("")) {
			throw new ParametroNuloException(matricula);
		}
		for (int i = 0; i < indice; i++) {
			if (cliente != null && cliente[i].getMatricula().equals(matricula)) {
				return cliente[i];
			}
		} 
		throw new NaoEncontradoException("O Cliente " +matricula);
	} 

	@Override
	public void remover(String matricula) throws NaoEncontradoException {
		for (int i = 0; i < indice; i++) {
			if (cliente[i] != null && cliente[i].getMatricula().equals(matricula)) {
				cliente[i] = null;
				return;
			}
		}
		throw new NaoEncontradoException(matricula);
	}

	@Override
	public void atualizar(Cliente clienteAtualizado) throws NaoEncontradoException, ParametroNuloException {
         for (int i = 0; i < indice; i++) {
        	 if(clienteAtualizado.getMatricula().equals(cliente[i].getMatricula())) {
        		 cliente[i] = clienteAtualizado;
        	 }
			
		}		
	}
	
	public Cliente[] listar() {
		return cliente;
	}

}