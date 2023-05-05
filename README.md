### Projeto Final de disciplina Testes Automatizados

## Decrição
Esse projeto foi desenvolvido durante a disciplina de Testes Automatizados, com o objetivo de criar um CRUD de um processo de compra em uma livraria utilizando o framework Spring.

O intuito era criar testes unitários e de integração utilizando as bibliotecas Junit5 e Mockito, além de utilizar o JaCoCo para gerar relatórios de cobertura de código e o SonarLint com o SonarCloud para medir a qualidade do software.

## Tecnologias Utilizadas
* **Java**
* **Spring Framework**
* **Junit5**
* **Mockito**
* **JaCoCo**
* **SonarLint**
* **SonarCloud**

## Funcionalidades
O sistema desenvolvido consiste em um CRUD de um processo de compra em uma livraria, onde é possível realizar as seguintes operações:

* Cadastrar um cliente, livro
* Listar todos os livros Cadastrados
* Buscar um livro e cliente pelo respectivo Id
* Atualiza informações de Livros e Cliente
* Excluir um livro do Sistemas
* Criar um transação simulando to total de livros e subtraindo do saldo cliente (WIP)

### Testes
Foram desenvolvidos testes unitários e de integração utilizando as bibliotecas Junit5 e Mockito. Os testes unitários foram responsáveis por verificar o comportamento esperado de cada componente do sistema de forma isolada. Já os testes de integração foram responsáveis por verificar o comportamento do sistema como um todo, incluindo a interação entre os diferentes componentes.
Além disso, foi utilizado o JaCoCo para gerar relatórios de cobertura de código, que mostraram quais partes do sistema foram testadas e quais ainda precisavam ser testadas. O SonarLint com o SonarCloud foram utilizados para medir a qualidade do código, indicando possíveis problemas de legibilidade, duplicação, complexidade e segurança.

### Como executar o projeto
Para executar o projeto, é necessário ter o Java e o Maven instalados na máquina. Em seguida, basta clonar o repositório, acessar a pasta do projeto e executar o seguinte comando no terminal:

> mvn clean install

fazendo isso o programa rodara todos os testes e gerará o relatório do JaCoCo
na pasta Target

> path: target/site/jacoco/index.html






