package dao;

import br.com.tiago.Cliente;

import java.util.Collection;

public interface IClienteDAO {

    public Boolean cadastrar(Cliente cliente);

    /**
     * Argumento: Cliente cliente:
     * Tipo: Cliente
     * Descrição: O método cadastrar recebe um objeto do tipo Cliente como argumento.
     * Propósito: O objetivo desse método é cadastrar um novo cliente. O objeto cliente contém todas as informações que precisam
     * ser salvas no banco de dados ou outro sistema de armazenamento, como nome, CPF, endereço, etc.
     * Retorno: O método retorna um valor booleano (Boolean), que indica se o cadastro foi bem-sucedido (true) ou não (false).
     * @param cpf
     * ______________________________________________________________________________________________________
     */

    public void excluir(Long cpf);

    /**
     * Argumento: Long cpf:
     * Tipo: Long
     * Descrição: O método excluir recebe um número do tipo Long, que representa o CPF do cliente.
     * Propósito: O método serve para excluir um cliente com base no seu CPF. O CPF é um identificador único que
     * será utilizado para localizar o cliente no sistema ou banco de dados e, assim, remover suas informações.
     * Retorno: Não há retorno (o método é void), pois o objetivo é realizar a exclusão sem devolver um valor.
     * ______________________________________________________________________________________________________
     * @param cliente
     */

    public void alterar(Cliente cliente);

    /**
     * Argumento: Cliente cliente:
     * Tipo: Cliente
     * Descrição: O método alterar recebe um objeto Cliente como argumento.
     * Propósito: O método alterar é utilizado para modificar os dados de um cliente que já existe no sistema. O objeto cliente que
     * é passado contém as informações atualizadas do cliente, e o método deve procurar esse cliente no banco de dados (normalmente
     * pelo CPF ou ID) para atualizar seus dados.
     * Retorno: Assim como o método excluir, o método alterar não retorna nada (void).
     * @param cpf
     * ______________________________________________________________________________________________________
     * @return
     */

    public Cliente consultar(Long cpf);

    /**
     * Argumento: Long cpf:
     * Tipo: Long
     * Descrição: O método consultar recebe o CPF de um cliente (do tipo Long) como argumento.
     * Propósito: O método consultar busca e retorna um objeto Cliente com base no CPF fornecido.
     * O CPF serve como identificador único para localizar o cliente no sistema ou banco de dados.
     * Retorno: O método retorna um objeto do tipo Cliente, que contém os dados do cliente encontrado
     * com o CPF fornecido. Caso o cliente não seja encontrado, o método pode retornar null (dependendo da implementação).
     * ______________________________________________________________________________________________________
     * @return
     */

    public Collection<Cliente> buscarTodos();

    /**
     * Argumento: Não há argumentos.
     * Este método não recebe nenhum argumento.
     * Retorno: O método retorna uma coleção de objetos Cliente, ou seja, uma lista, conjunto ou qualquer outro tipo de
     * coleção que contenha vários objetos Cliente.
     * Tipo de retorno: Collection<Cliente>
     * Descrição: O método buscarTodos tem como propósito recuperar todos os clientes armazenados no sistema. Ele retorna
     * uma coleção com todos os objetos Cliente
     * presentes no banco de dados ou sistema de persistência.
     * ______________________________________________________________________________________________________
     */
}
