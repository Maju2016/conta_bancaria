package conta_bancaria.repository;

import conta_bancaria.model.Conta;

public interface ContaRepository {
	
	//Metodos do CRUD
	
	public void listarTodas();
	public void cadastrar(Conta conta);
	public void atualizar(Conta conta);
	public void procurarPorNumero(int numero);
	public void deletar(int numero);
	
}
