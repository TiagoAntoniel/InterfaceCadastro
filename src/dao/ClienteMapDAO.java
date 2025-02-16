package dao;

import br.com.tiago.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO{

    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }

    /**
     * Atributo map: A classe usa um Map (no caso, um HashMap) para armazenar os clientes. A chave do mapa é o CPF do
     * cliente (do tipo Long), e o valor é o próprio objeto Cliente. Isso significa que cada cliente é identificado de
     * forma única pelo seu CPF.
     * Construtor ClienteMapDAO(): Ao criar um novo objeto da classe ClienteMapDAO, o mapa (map) é inicializado como um
     * novo HashMap, pronto para armazenar os clientes.
     */

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }
    
    /**
     * Argumento cliente: O método recebe um objeto Cliente que contém as informações do cliente a ser cadastrado.
     * Processo: O método verifica se já existe um cliente com o mesmo CPF no mapa:
     * Se o CPF já existe (this.map.containsKey(cliente.getCpf())), retorna false (não pode cadastrar, pois o CPF já está registrado).
     * Se o CPF não existe, o cliente é adicionado ao mapa com o CPF como chave e o objeto Cliente como valor
     * (this.map.put(cliente.getCpf(), cliente)),
     * e o método retorna true.
     * @param cpf
     */

    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);

        if (clienteCadastrado != null) {
            this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
        }

    }

    /**
     * Argumento cpf: O método recebe o CPF do cliente a ser excluído.
     * Processo: O método tenta buscar o cliente usando o CPF (this.map.get(cpf)):
     * Se o cliente é encontrado, o método o remove do mapa usando this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado),
     * ou seja, ele remove o par chave-valor (CPF - Cliente).
     * Se o cliente não for encontrado, o método não faz nada.
     * @param cliente
     */

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if (clienteCadastrado != null) {

            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
        }

    }

    /**
     * PArgumento cliente: O método recebe um objeto Cliente com os novos dados para atualização.
     * Processo: O método busca o cliente pelo CPF (this.map.get(cliente.getCpf())):
     * Se o cliente já existe, os dados do cliente são atualizados com os valores fornecidos no novo objeto cliente (exemplo:
     * clienteCadastrado.setNome(cliente.getNome())).
     * Se o cliente não for encontrado, o método não faz nada (nenhuma alteração é realizada).
     * @param cpf
     * @return
     */

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    /**
     * Argumento cpf: O método recebe o CPF de um cliente que deseja consultar.
     * Processo: O método tenta buscar e retornar o cliente correspondente ao CPF usando this.map.get(cpf). Se o CPF existir no mapa,
     * ele retorna o objeto Cliente associado. Caso contrário, retorna null.
     * @return
     */

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }

    /**
     * Sem argumento: Este método não recebe nenhum argumento.
     * Processo: O método retorna todos os valores armazenados no mapa (ou seja, todos os objetos Cliente).
     * O this.map.values() retorna uma coleção de todos os clientes registrados no mapa.
     */
}
