package dao;

import br.com.tiago.Cliente;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * O que é o HashSet?
 * Um HashSet é uma coleção que não permite elementos duplicados e não garante ordem de inserção.
 * Ele se baseia em uma tabela de hash para armazenar os elementos. Ou seja, ele armazena os objetos
 * de maneira eficiente em termos de tempo de acesso, mas não preserva a ordem de inserção dos elementos,
 * como o ArrayList faria.
 *
 * Características do HashSet:
 * Não permite duplicados: Se você tentar adicionar um elemento que já existe na coleção, ele não será adicionado novamente.
 * Não garante ordem: A ordem dos elementos no HashSet não é garantida (não importa a ordem em que você os inseriu).
 * É mais eficiente para buscas e verificações de presença de elementos do que listas, pois usa uma tabela de hash.
 */

public class ClienteSetDAO implements IClienteDAO{

    private Set<Cliente> set;

    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }

    /**
     *
     * @param cliente
     * @return
     * Atributo set: A coleção set é declarada como um Set<Cliente>, e é inicializada como um HashSet. A principal
     * característica de um Set é garantir que não existam elementos duplicados.
     * O uso de Set ao invés de List significa que o sistema não permitirá que dois clientes com os mesmos dados sejam armazenados.
     */

    @Override
    public Boolean cadastrar(Cliente cliente) {
        return this.set.add(cliente);
    }

    /**
     * @param cpf
     * O método cadastrar tenta adicionar um cliente ao set usando o método add().
     * Se o cliente não existir no conjunto (baseado na implementação do método equals() da classe Cliente), o cliente será adicionado e o
     * método retorna true.
     * Se o cliente já existir, o método add() retornará false, pois o HashSet não permite duplicados. Isso significa que clientes com
     * o mesmo CPF (presumindo
     * que o método equals() seja implementado considerando o CPF) não serão adicionados ao conjunto.
     * ______________________________________________________________________________________________________
     */


    @Override
    public void excluir(Long cpf) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : this.set) {
            if (cliente.getCpf().equals(cpf)){
                clienteEncontrado = cliente;
                break;

            }
        }

        if (clienteEncontrado != null) {
            this.set.remove(clienteEncontrado);
        }
    }

    /**
     * Para excluir um cliente, o código percorre o set para encontrar o cliente com o CPF correspondente.
     * O método não pode remover diretamente pelo CPF, então é necessário primeiro localizar o objeto completo
     * . Após encontrar o cliente correspondente, ele é removido do set usando o método remove().
     * Nota importante: Como o HashSet não permite duplicados, a remoção de um elemento funciona sem problemas,
     * porque ele garante que, caso o cliente tenha sido encontrado, há apenas um único cliente com aquele CPF.
     * @param cliente
     */


    @Override
    public void alterar(Cliente cliente) {
        if (this.set.contains(cliente)) {
            for (Cliente clienteCadastrado : this.set) {
                if (clienteCadastrado.equals(cliente)) {
                    clienteCadastrado.setNome(cliente.getNome());
                    clienteCadastrado.setTel(cliente.getTel());
                    clienteCadastrado.setNumero(cliente.getNumero());
                    clienteCadastrado.setEnd(cliente.getEnd());
                    clienteCadastrado.setCidade(cliente.getCidade());
                    clienteCadastrado.setEstado(cliente.getEstado());
                    break;
                }
            }
        }
    }

    /**
     * O método alterar verifica se o set contém o cliente usando o método contains(), que utiliza o
     * método equals() da classe Cliente para comparar.
     * Se o cliente for encontrado, ele é atualizado com os novos dados (como nome, telefone, etc.).
     * Nota: A implementação do método equals() em Cliente deve garantir que a comparação seja feita
     * corretamente com base no CPF ou em todos
     * os campos do cliente, dependendo do seu objetivo.
     * @param cpf
     * ______________________________________________________________________________________________________
     * @return
     */

    @Override
    public Cliente consultar(Long cpf) {
        for (Cliente clienteCadastrado : this.set) {
            if (clienteCadastrado.getCpf().equals(cpf)) {
                return clienteCadastrado;
            }
        }
        return null;
    }

    /**
     * O método consultar percorre o set e tenta localizar um cliente com o CPF fornecido.
     * Se encontrar o cliente, retorna o objeto Cliente. Caso contrário, retorna null.
     * @return
     */

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.set;
    }

    /**
     * O método buscarTodos simplesmente retorna o set completo, ou seja, todos os clientes armazenados.
     * Como o HashSet não garante uma ordem de inserção, a ordem dos elementos no conjunto retornado pode
     * ser diferente de como eles foram adicionados.
     */
}
/**
 * Principais Diferenças entre HashSet e HashMap
 * Para entender melhor o que está acontecendo, é importante perceber as diferenças principais entre um HashSet
 * e um HashMap, já que ambos se baseiam em tabelas de hash, mas são usados de maneira diferente:
 *
 * HashSet:
 *
 * Armazena somente os valores (objetos), sem associar a uma chave.
 * Não permite elementos duplicados.
 * A principal operação é verificar se o objeto já existe ou adicionar um novo.
 * HashMap:
 *
 * Armazena pares chave-valor.
 * Permite associar um valor a uma chave única, e a chave é usada para recuperar ou modificar o valor.
 * O exemplo anterior de ClienteMapDAO usa um HashMap onde o CPF é a chave e o objeto Cliente é o valor.
 * Como o HashSet é útil neste caso?
 * Armazenamento sem duplicatas: O HashSet garante que não haverá dois clientes com o mesmo conjunto de dados,
 * desde que a implementação do método equals() de Cliente considere um cliente como "igual" a outro caso os dados
 * (como CPF) sejam os mesmos.
 * Eficiência nas operações: O HashSet proporciona acesso rápido para verificação de existência de elementos
 * (com o método contains()), adição de novos elementos (com o método add()) e remoção de elementos (com o método remove()).
 * Resumo das Operações no ClienteSetDAO
 * Operação	Como Funciona no HashSet
 * Cadastrar	Adiciona o cliente no set se ele não existir (não duplicado).
 * Excluir	Percorre o set para encontrar o cliente pelo CPF e o remove.
 * Alterar	Encontra o cliente e atualiza seus dados no set.
 * Consultar	Percorre o set para encontrar um cliente pelo CPF.
 * Buscar Todos	Retorna todos os clientes armazenados no set.
 * Conclusão
 * A utilização do HashSet garante que não haja duplicação de clientes no sistema e proporciona uma maneira
 * eficiente de realizar as operações CRUD. Porém, ao contrário do HashMap, o HashSet não usa chaves específicas
 * (como o CPF), mas depende do método equals() para garantir a unicidade dos elementos, o que pode ser uma vantagem
 * ou desvantagem, dependendo de como você deseja manipular os dados.
 */
