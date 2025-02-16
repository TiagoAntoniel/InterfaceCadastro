🚀 Controle de Gastos e Ganhos - Projeto de Estudo
Este projeto é uma implementação simples de um sistema de controle de clientes usando uma interface de DAO (Data Access Object) e um armazenamento em memória baseado em HashMap. O foco é demonstrar conceitos importantes de persistência de dados, CRUD (Create, Read, Update, Delete), e interfaces em Java.

📖 Fundamentos Abordados no Exercício
Este exercício abrange uma série de conceitos importantes para estudantes de Java que estão aprendendo sobre como estruturar sistemas que manipulam dados de maneira eficaz. Abaixo estão os principais fundamentos abordados:

1. Uso de Interfaces em Java
A interface IClienteDAO define um contrato que estabelece os métodos que a classe ClienteMapDAO deve implementar. Estudantes devem entender como interfaces são utilizadas para abstrair a lógica de acesso a dados, permitindo que diferentes implementações (neste caso, a implementação em memória com HashMap) possam ser utilizadas.

Métodos da interface: cadastrar(), excluir(), alterar(), consultar(), e buscarTodos() são definidos na interface e implementados na classe ClienteMapDAO.
2. Estrutura de Dados: HashMap
O HashMap é utilizado para armazenar os clientes, onde o CPF do cliente é usado como chave única para mapear ao objeto Cliente.

Benefício: O HashMap permite uma busca rápida por chave, o que torna as operações de consulta, alteração, exclusão e cadastramento muito eficientes.

Explicação de uso: O CPF é utilizado como chave para garantir que cada cliente tenha uma identificação única no sistema.

3. Operações CRUD
As operações de CRUD (Create, Read, Update, Delete) são fundamentais para manipulação de dados em sistemas de software. Este exercício foca na implementação dessas operações para clientes:

Create (cadastrar()): Como adicionar um novo cliente ao sistema.
Read (consultar(), buscarTodos()): Como consultar informações de um cliente ou buscar todos os clientes.
Update (alterar()): Como atualizar os dados de um cliente já cadastrado.
Delete (excluir()): Como excluir um cliente do sistema.
4. Validação e Condicionais
O código inclui validações para garantir que o sistema seja robusto. Por exemplo:

Ao cadastrar um cliente, é verificado se o CPF já está registrado.
Ao excluir ou alterar, é verificado se o cliente realmente existe no sistema.
Essas validações ajudam a evitar erros e falhas na manipulação dos dados.

5. Uso de Collection
No método buscarTodos(), o código retorna uma coleção de objetos Cliente. A coleção é obtida através do método .values() do HashMap, que retorna todos os valores armazenados no mapa.

Coleções são uma maneira eficiente de armazenar e iterar sobre um conjunto de objetos.
6. Abstração e Separação de Responsabilidades
A interface IClienteDAO separa a lógica de acesso aos dados da lógica de negócios. Isso facilita a manutenção e a evolução do código.
A classe ClienteMapDAO implementa a persistência dos dados em memória com um HashMap. Isso pode ser facilmente substituído por outra implementação (como um banco de dados relacional ou outro tipo de armazenamento) sem afetar o restante do sistema.
💡 O Que Estudantes Devem Prestar Atenção
Compreensão das Interfaces: É importante entender como e por que usar interfaces para definir contratos que outras classes podem implementar. Estudantes devem focar no fato de que as interfaces permitem diferentes implementações de um mesmo conjunto de métodos.

Operações Básicas de Manipulação de Dados: O exercício aborda como manipular dados no sistema. Entender as operações CRUD é crucial para trabalhar com sistemas que armazenam dados de qualquer tipo, seja em memória ou em banco de dados.

Como Funciona o HashMap: Estudantes devem prestar atenção em como o HashMap é utilizado como estrutura de dados para armazenar e acessar rapidamente os objetos Cliente. A chave (CPF) é fundamental para garantir a unicidade dos dados.

Validação de Dados: A implementação de verificações antes de realizar operações (como a verificação de duplicação de CPF) é uma boa prática para garantir a integridade do sistema. Isso previne erros de lógica, como a tentativa de adicionar um cliente com CPF já existente.

Simplicidade e Manutenção: A implementação em memória é simples e eficaz para um projeto inicial, mas os conceitos podem ser aplicados a sistemas mais complexos, como aqueles que utilizam bancos de dados.
